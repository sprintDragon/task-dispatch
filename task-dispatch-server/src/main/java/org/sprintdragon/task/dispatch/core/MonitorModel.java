package org.sprintdragon.task.dispatch.core;

import lombok.Data;
import lombok.ToString;

/**
 * Created by wangdi on 16-12-27.
 */
@ToString
@Data
public class MonitorModel {

    private int bufferSize;
    private long remainingCapacity;

    private long activeCount;
    private long taskCount;

    //有序队列长度
    private long redisZsetSize;
    //执行效率 opm
    private long executeOpm;
    private long executeError;

}
