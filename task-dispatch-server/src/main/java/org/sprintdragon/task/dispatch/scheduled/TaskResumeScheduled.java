package org.sprintdragon.task.dispatch.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.sprintdragon.task.dispatch.biz.taskinfo.service.TaskInfoExecuteService;

import javax.annotation.Resource;
import javax.management.timer.Timer;

/**
 * Created by wangdi on 16-12-19.
 */
@Component
@Slf4j
public class TaskResumeScheduled {

    @Resource
    TaskInfoExecuteService taskInfoExecuteService;

    @Async
    @Scheduled(fixedRate = Timer.ONE_HOUR, initialDelay = 10000)
    public void resumeDbToRedis() throws InterruptedException {
        try {
            long startTime = System.currentTimeMillis();
            log.info("resumeDbToRedis begin threadName={}", Thread.currentThread().getName());
            long fetchNum = taskInfoExecuteService.resumeTimeout();
            log.info("resumeDbToRedis end threadName{},fetchNum={},cost={}", Thread.currentThread().getName(), fetchNum, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.error("resumeDbToRedis error", e);
        }
    }

}
