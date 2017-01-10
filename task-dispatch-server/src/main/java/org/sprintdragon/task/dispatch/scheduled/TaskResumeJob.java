package org.sprintdragon.task.dispatch.scheduled;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.sprintdragon.task.dispatch.biz.taskinfo.service.TaskInfoExecuteService;

import javax.annotation.Resource;

/**
 * Created by wangdi on 16-12-19.
 */
@Component("taskResumeJob")
@Slf4j
public class TaskResumeJob implements SimpleJob {

    @Resource
    TaskInfoExecuteService taskInfoExecuteService;

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("******name=" + shardingContext.getJobName() + ",shard=" + shardingContext.getShardingParameter() + "," + shardingContext.getShardingItem());
//        try {
//            long startTime = System.currentTimeMillis();
//            log.info("resumeDbToRedis begin threadName={}", Thread.currentThread().getName());
//            long fetchNum = taskInfoExecuteService.resumeTimeout();
//            log.info("resumeDbToRedis end threadName{},fetchNum={},cost={}", Thread.currentThread().getName(), fetchNum, System.currentTimeMillis() - startTime);
//        } catch (Exception e) {
//            log.error("resumeDbToRedis error", e);
//        }
    }

}
