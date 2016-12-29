package org.sprintdragon.task.dispatch.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.sprintdragon.task.dispatch.biz.taskinfo.service.TaskInfoExecuteService;

import javax.annotation.Resource;

/**
 * Created by wangdi on 16-12-19.
 */
@Component
@Slf4j
public class TaskExecuteScheduled {

    @Resource
    TaskInfoExecuteService taskInfoExecuteService;

    @Async
    @Scheduled(fixedRate = 1000, initialDelay = 2000)
    public void popTaskFromRedisByScore() throws InterruptedException {
        try {
            long startTime = System.currentTimeMillis();
            log.debug("popTaskFromRedisByScore begin thread={}", Thread.currentThread().getName());
            long fetchNum = taskInfoExecuteService.batchPop();
            log.debug("popTaskFromRedisByScore end thread={},fetchNum={},cost={}", Thread.currentThread().getName(), fetchNum, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.error("popTaskFromRedisByScore error", e);
        }
    }

}
