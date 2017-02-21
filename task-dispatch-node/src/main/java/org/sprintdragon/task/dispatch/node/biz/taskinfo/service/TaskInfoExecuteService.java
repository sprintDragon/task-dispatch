package org.sprintdragon.task.dispatch.node.biz.taskinfo.service;

import org.sprintdragon.task.dispatch.node.biz.taskinfo.domain.TaskInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Created by wangdi on 16-12-13.
 */
public interface TaskInfoExecuteService {

    boolean execute(@Valid @NotNull TaskInfo taskInfo) throws Exception;

    long batchPop() throws Exception;

    boolean save(@NotNull TaskInfo taskInfo) throws Exception;

    /**
     * 超时数据恢复
     */
    long resumeTimeout();

}
