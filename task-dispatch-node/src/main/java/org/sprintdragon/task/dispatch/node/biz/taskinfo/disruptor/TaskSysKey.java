package org.sprintdragon.task.dispatch.node.biz.taskinfo.disruptor;

import lombok.Data;
import lombok.ToString;

/**
 * Created by wangdi on 16-12-20.
 */
@Data
@ToString
public class TaskSysKey {

    private String sysKey;

    public TaskSysKey() {
    }

    public TaskSysKey(String sysKey) {
        this.sysKey = sysKey;
    }

}
