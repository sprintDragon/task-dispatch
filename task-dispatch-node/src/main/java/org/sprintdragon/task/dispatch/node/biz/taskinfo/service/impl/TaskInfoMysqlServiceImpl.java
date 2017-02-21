package org.sprintdragon.task.dispatch.node.biz.taskinfo.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.sprintdragon.task.dispatch.node.biz.taskinfo.domain.TaskInfo;
import org.sprintdragon.task.dispatch.node.biz.taskinfo.domain.TaskInfoPo;
import org.sprintdragon.task.dispatch.node.biz.taskinfo.repository.TaskInfoRepository;
import org.sprintdragon.task.dispatch.node.biz.taskinfo.service.TaskInfoMysqlService;
import org.sprintdragon.task.dispatch.assist.common.utils.CopyPropertyUtils;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wangdi on 16-12-27.
 */
@Service
@Validated
public class TaskInfoMysqlServiceImpl implements TaskInfoMysqlService {

    @Resource
    TaskInfoRepository taskInfoRepository;

    @Override
    public TaskInfo findOne(Long sysKey) {
        return changeToDto(taskInfoRepository.findOne(sysKey));
    }

    @Override
    public boolean save(@Valid @NotNull TaskInfo taskInfo) {
        return taskInfoRepository.save(changeToPo(taskInfo)) != null;
    }

    @Override
    public void deleteById(Long id) {
        taskInfoRepository.delete(id);
    }

    @Override
    public Page<TaskInfo> findByExecuteTimeBetween(Long begin, Long end, int pageNo, int pageSizes) {
        PageRequest pageRequest = new PageRequest(pageNo, pageSizes, Sort.Direction.ASC, "executeTime");
        Page<TaskInfoPo> pos = taskInfoRepository.findByExecuteTimeBetween(begin, end, pageRequest);
        List<TaskInfo> l = new ArrayList();
        for (TaskInfoPo po : pos) {
            l.add(changeToDto(po));
        }
        Page<TaskInfo> page = new PageImpl(l, pageRequest, pos.getTotalElements());
        return page;
    }

    private TaskInfoPo changeToPo(TaskInfo taskInfo) {
        TaskInfoPo po = CopyPropertyUtils.copyPropertiesAndInstance(taskInfo, TaskInfoPo.class);
        po.setCreated(new Date());
        po.setModified(new Date());
        return po;
    }

    private TaskInfo changeToDto(TaskInfoPo po) {
        TaskInfo dto = CopyPropertyUtils.copyPropertiesAndInstance(po, TaskInfo.class);
        return dto;
    }

}
