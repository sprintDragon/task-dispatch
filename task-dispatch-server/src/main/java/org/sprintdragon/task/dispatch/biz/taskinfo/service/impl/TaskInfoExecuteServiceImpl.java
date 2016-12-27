package org.sprintdragon.task.dispatch.biz.taskinfo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.sprintdragon.task.dispatch.biz.taskinfo.disruptor.TaskInfoEventTranslator;
import org.sprintdragon.task.dispatch.biz.taskinfo.disruptor.TaskSysKey;
import org.sprintdragon.task.dispatch.biz.taskinfo.domain.TaskInfo;
import org.sprintdragon.task.dispatch.biz.taskinfo.service.TaskInfoExecuteService;
import org.sprintdragon.task.dispatch.biz.taskinfo.service.TaskInfoMysqlService;
import org.sprintdragon.task.dispatch.biz.taskinfo.service.TaskInfoRedisService;
import org.sprintdragon.task.dispatch.client.constants.ExecuteTypeEnum;
import org.sprintdragon.task.dispatch.client.constants.StrategyEnum;
import org.sprintdragon.task.dispatch.common.utils.IdGenUtil;
import org.sprintdragon.task.dispatch.core.StatMonitor;
import org.sprintdragon.task.dispatch.disruptor.IDisruptorFacade;

import javax.annotation.Resource;
import javax.management.timer.Timer;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by wangdi on 16-12-27.
 */
@Validated
@Service
@Slf4j
public class TaskInfoExecuteServiceImpl implements TaskInfoExecuteService {

    @Resource
    TaskInfoMysqlService taskInfoMysqlService;
    @Resource
    TaskInfoRedisService taskInfoRedisService;
    @Resource
    IDisruptorFacade disruptorFacade;
    //每次取出多少个
    static final int FETCH_PER_TIME_COUNT = 100;
    //重试任务最迟一周内执行
    static final long LIMIT_TO_TIME = 30 * Timer.ONE_DAY;
    //恢复表中数据时间线
    long TIME_OUT_BEFORE = Timer.ONE_MINUTE;
    int PAGE_SIZE = 1000;

    @Override
    public boolean execute(@Valid @NotNull TaskInfo taskInfo) throws Exception {
        try {
            long startTime = System.currentTimeMillis();
            ExecuteTypeEnum executeTypeEnum = ExecuteTypeEnum.getValue(taskInfo.getExecuteType());
            switch (executeTypeEnum) {
                case MQ:
                    break;
                case TASK_ENGINE:
                    break;
                case HTTP:
                    break;
                default:
                    throw new RuntimeException("executeTaskInfo executeTypeEnum not suppost");
            }
            return true;
        } catch (Exception e) {
            log.error("executeTaskInfo error taskInfo={}", taskInfo, e);
            StatMonitor.executeErrorCount.incrementAndGet();
            return true;
        } finally {
            StatMonitor.executeCount.incrementAndGet();
        }
    }

    private void asyncExecute(String sysKeyStr) {
        log.debug("executeTaskInfo execute sysKeyStr={}", sysKeyStr);
        disruptorFacade.getDisruptor().publishEvent(new TaskInfoEventTranslator(new TaskSysKey(sysKeyStr)));
    }

    @Override
    public long batchPop() throws Exception {
        Long score = System.currentTimeMillis();
        Set<String> set = taskInfoRedisService.listDistanceByScore(0l, score, 0, FETCH_PER_TIME_COUNT);
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            String sysKeyStr = iterator.next();
            if (taskInfoRedisService.removeByKey(sysKeyStr)) {
                asyncExecute(sysKeyStr);
            } else {
                log.debug("redis zrm 竞争失败 sysKeyStr=" + sysKeyStr);
            }
        }
        return set.size();
    }

    @Override
    public boolean save(TaskInfo taskInfo) throws Exception {
        setupSysKey(taskInfo);
        setupTimeUp(taskInfo);
        checkParams(taskInfo);
        taskInfoMysqlService.save(taskInfo);
        boolean ret = taskInfoRedisService.addKey(taskInfo.getExecuteTime(), String.valueOf(taskInfo.getSysKey()));
        return ret;
    }

    private void checkParams(TaskInfo taskInfo) throws Exception {
        if (taskInfo.getExecuteTime() != null) {
            Assert.isTrue(taskInfo.getExecuteTime() < (System.currentTimeMillis() + LIMIT_TO_TIME), "重试任务最迟30天内执行");
        }
    }

    @Override
    public long resumeTimeout() {
        long count = 0;
        Page<TaskInfo> taskInfoList = taskInfoMysqlService.findByExecuteTimeBetween(0l, System.currentTimeMillis() - TIME_OUT_BEFORE, 0, PAGE_SIZE);
        for (int i = 0; i < taskInfoList.getTotalPages(); i++) {
            taskInfoList = taskInfoMysqlService.findByExecuteTimeBetween(0l, System.currentTimeMillis() - TIME_OUT_BEFORE, i, PAGE_SIZE);
            for (TaskInfo taskInfo : taskInfoList) {
                String sysKey = String.valueOf(taskInfo.getSysKey());
                if (taskInfoRedisService.addKey(taskInfo.getExecuteTime(), sysKey)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 生成系统主键
     *
     * @param taskInfo
     */
    private void setupSysKey(TaskInfo taskInfo) {
        taskInfo.setSysKey(IdGenUtil.genId());
    }

    /**
     * 设置执行时间
     *
     * @param taskInfo
     */
    private void setupTimeUp(TaskInfo taskInfo) {
        //用户未设置执行时间
        if (taskInfo.getExecuteTime() == null) {
            long timeUp = taskInfo.getClientTime() + getTimeDistance(0l, StrategyEnum.getValue(taskInfo.getStrategy()));
            taskInfo.setExecuteTime(timeUp);
        }
    }

    private long getTimeDistance(Long count, StrategyEnum strategyEnum) {
        long distance = 0;
        switch (strategyEnum) {
            case GROW:
                break;
            default:
                distance = strategyEnum.getDistance();
        }
        return distance;
    }

}
