package org.sprintdragon.task.dispatch.listener;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.sprintdragon.task.dispatch.biz.taskinfo.service.TaskInfoExecuteService;

import javax.annotation.Resource;

/**
 * Created by wangdi on 16-11-22.
 */
@Slf4j
@Service("taskElasticJob")
public class TaskElasticJob implements SimpleJob {

    @Resource
    TaskInfoExecuteService taskInfoExecuteService;

    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("##########execute x={},i={}", JSON.toJSONString(shardingContext), shardingContext.getShardingItem());
    }
}
