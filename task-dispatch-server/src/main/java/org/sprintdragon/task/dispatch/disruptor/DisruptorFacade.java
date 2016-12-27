package org.sprintdragon.task.dispatch.disruptor;

import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * Created by wangdi on 16-7-4.
 */
public class DisruptorFacade<T> implements IDisruptorFacade {

    Disruptor<T> disruptor;

    public DisruptorFacade(Disruptor<T> disruptor, WorkHandler... workHandlers) {
        this.disruptor = disruptor;
        disruptor.handleEventsWithWorkerPool(workHandlers);
    }

    public Disruptor<T> getDisruptor() {
        return disruptor;
    }

    public void start() {
        disruptor.start();
    }

    public void shutdown() {
        disruptor.shutdown();
    }
}
