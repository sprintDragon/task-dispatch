package org.sprintdragon.task.dispatch.node.scheduled;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.sprintdragon.task.dispatch.node.biz.taskinfo.service.TaskInfoExecuteService;

import javax.annotation.Resource;

/**
 * Created by wangdi on 16-12-19.
 */
@Component("taskExecuteJob")
@Slf4j
public class TaskExecuteJob implements SimpleJob {

    @Resource
    TaskInfoExecuteService taskInfoExecuteService;

    @Override
    public void execute(ShardingContext shardingContext) {
        try {
//            System.out.println("******name=" + shardingContext.getJobName() + ",shard=" + shardingContext.getShardingParameter() + "," + shardingContext.getShardingItem());
            long startTime = System.currentTimeMillis();
            log.debug("popTaskFromRedisByScore begin thread={}", Thread.currentThread().getName());
            long fetchNum = taskInfoExecuteService.batchPop();
            log.debug("popTaskFromRedisByScore end thread={},fetchNum={},cost={}", Thread.currentThread().getName(), fetchNum, System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.error("popTaskFromRedisByScore error", e);
        }
    }

}
