package org.sprintdragon.task.dispatch.listener;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.sprintdragon.task.dispatch.biz.taskinfo.domain.TaskInfo;
import org.sprintdragon.task.dispatch.biz.taskinfo.service.TaskInfoExecuteService;
import org.sprintdragon.task.dispatch.client.domain.TaskMessage;
import org.sprintdragon.task.dispatch.common.utils.CopyPropertyUtils;

import javax.annotation.Resource;

/**
 * Created by wangdi on 16-11-22.
 */
@Slf4j
@Service("taskReceiveListener")
public class TaskReceiveListener {

    @Resource
    TaskInfoExecuteService taskInfoExecuteService;

    public void execute(String businessId, String text) throws Exception {
        TaskMessage taskMessage;
        try {
            taskMessage = JSONObject.parseObject(text, TaskMessage.class);
        } catch (Exception e) {
            log.error("parseObject error businessId={},text={}", businessId, text, e);
            throw new IllegalArgumentException(e);
        }
        TaskInfo taskInfo = CopyPropertyUtils.copyPropertiesAndInstance(taskMessage, TaskInfo.class);
        taskInfoExecuteService.save(taskInfo);
    }

}
