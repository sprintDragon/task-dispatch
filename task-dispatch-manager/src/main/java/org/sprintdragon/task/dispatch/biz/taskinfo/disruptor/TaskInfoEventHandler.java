package org.sprintdragon.task.dispatch.biz.taskinfo.disruptor;

import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;
import org.sprintdragon.task.dispatch.biz.taskinfo.domain.TaskInfo;
import org.sprintdragon.task.dispatch.biz.taskinfo.service.TaskInfoExecuteService;
import org.sprintdragon.task.dispatch.biz.taskinfo.service.TaskInfoMysqlService;

import javax.annotation.Resource;

/**
 * Created by wangdi on 16-7-4.
 */
@Slf4j
public class TaskInfoEventHandler implements WorkHandler<TaskSysKey> {

    @Resource
    TaskInfoExecuteService taskInfoExecuteService;
    @Resource
    TaskInfoMysqlService taskInfoMysqlService;

    @Override
    public void onEvent(TaskSysKey taskSysKey) throws Exception {
        try {
            long startTime = System.currentTimeMillis();
            TaskInfo taskInfo = taskInfoMysqlService.findOne(Long.valueOf(taskSysKey.getSysKey()));
            if (taskInfoExecuteService.execute(taskInfo)) {
                taskInfoMysqlService.deleteById(taskInfo.getSysKey());
            }
            log.debug("######onEvent thread={},cost={},taskSysKey={}", Thread.currentThread().getName(), System.currentTimeMillis() - startTime, taskSysKey.getSysKey());
        } catch (Exception e) {
            log.error("onEvent error taskSysKey={}", taskSysKey, e);
        }
    }
}
