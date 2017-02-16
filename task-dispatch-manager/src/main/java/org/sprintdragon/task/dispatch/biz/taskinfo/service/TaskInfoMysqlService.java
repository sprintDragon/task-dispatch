package org.sprintdragon.task.dispatch.biz.taskinfo.service;

import org.springframework.data.domain.Page;
import org.sprintdragon.task.dispatch.biz.taskinfo.domain.TaskInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by wangdi on 16-12-13.
 */
public interface TaskInfoMysqlService {

    TaskInfo findOne(Long sysKey);

    boolean save(@Valid @NotNull TaskInfo taskInfo);

    void deleteById(Long id);

    Page<TaskInfo> findByExecuteTimeBetween(Long begin, Long end, int pageNo, int pageSizes);

}
