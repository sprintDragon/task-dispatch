package org.sprintdragon.task.dispatch.biz.taskinfo.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by wangdi on 16-7-4.
 */
public class TaskInfoFactory implements EventFactory<TaskSysKey> {

    @Override
    public TaskSysKey newInstance() {
        return new TaskSysKey();
    }

}
