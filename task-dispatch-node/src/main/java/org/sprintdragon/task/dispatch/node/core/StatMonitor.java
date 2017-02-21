package org.sprintdragon.task.dispatch.node.core;

import com.lmax.disruptor.dsl.Disruptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.timer.Timer;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by wangdi on 16-12-27.
 */
@Service
@Slf4j
public class StatMonitor {

    private static long publishOpm;
    private static long executeOpm;
    public static AtomicLong publishCount = new AtomicLong();
    public static AtomicLong executeCount = new AtomicLong();
    public static AtomicLong executeErrorCount = new AtomicLong();
    @Resource
    Disruptor disruptor;
    @Resource
    ThreadPoolTaskExecutor traceThreadPool;

    static {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long start = System.currentTimeMillis();
                    executeOpm = executeCount.getAndSet(0);
                    publishOpm = publishCount.getAndSet(0);
                    log.info("StatMonitor running 每分钟生产数量={},每分钟处理数量={}", publishOpm, executeOpm);
                    try {
                        Thread.sleep(60 * Timer.ONE_SECOND - (System.currentTimeMillis() - start));
                    } catch (InterruptedException e) {
                        log.error("StatMonitor thread error", e);
                    }
                }
            }
        }).start();
    }

    public MonitorModel getMonitor() {
        MonitorModel monitor = new MonitorModel();
        monitor.setActiveCount(traceThreadPool.getActiveCount());
        monitor.setTaskCount(traceThreadPool.getThreadPoolExecutor().getTaskCount());
        monitor.setBufferSize(disruptor.getRingBuffer().getBufferSize());
        monitor.setRemainingCapacity(disruptor.getRingBuffer().remainingCapacity());
        monitor.setExecuteOpm(executeOpm);
        monitor.setExecuteError(executeErrorCount.get());
        return monitor;
    }

}
