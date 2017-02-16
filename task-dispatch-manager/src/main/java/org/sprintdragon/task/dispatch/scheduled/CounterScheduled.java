package org.sprintdragon.task.dispatch.scheduled;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.sprintdragon.task.dispatch.core.StatMonitor;

import javax.annotation.Resource;

/**
 * Created by wangdi on 16-12-19.
 */
@Component
@Slf4j
public class CounterScheduled {

    @Resource
    StatMonitor statMonitor;

    @Async
    @Scheduled(fixedRate = 60000, initialDelay = 2000)
    public void countOnEvent() throws InterruptedException {
        try {
            long startTime = System.currentTimeMillis();
            log.info("countOnEvent stat={}", JSON.toJSONString(statMonitor.getMonitor()));
//            log.info("countOnEvent begin threadName={},countOnEvent={}", Thread.currentThread().getName(), statCounterService.get(StatisticsConstant.JIMCOUNT_ONEVENT_PERMIN));
//            statCounterService.del(StatisticsConstant.JIMCOUNT_ONEVENT_PERMIN);
//            log.info("countOnEvent end threadName={},cost={}", Thread.currentThread().getName(), System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.error("countOnEvent error!!!", e);
        }
    }

}
