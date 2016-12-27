package org.sprintdragon.task.dispatch.biz;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.sprintdragon.task.dispatch.DispatchTestApplication;
import org.sprintdragon.task.dispatch.client.constants.ExecuteTypeEnum;
import org.sprintdragon.task.dispatch.client.constants.StrategyEnum;
import org.sprintdragon.task.dispatch.client.domain.TaskMessage;
import org.sprintdragon.task.dispatch.client.domain.TaskMessageBuilder;
import org.sprintdragon.task.dispatch.listener.TaskReceiveListener;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangdi on 16-12-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DispatchTestApplication.class, value = "spring.profiles.active=dev")
public class TaskInfoProducerTest {

    @Resource
    TaskReceiveListener taskReceiveListener;

    @Test
    public void testSave() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int count = 0;
        while (true) {
            executorService.submit(() -> {
                String id = new Random().nextInt(100000) + "";
                TaskMessage taskMessage = TaskMessageBuilder.builder().namespace("as").bizType("2s2").executeType(ExecuteTypeEnum.MQ).executeTopic("wd_test1").executeId(id).executeContext("bbb").strategy(StrategyEnum.AFTER_DISTANCE_1_MINUTE).build();
                try {
                    taskReceiveListener.execute(id, JSON.toJSONString(taskMessage));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            System.out.println(++count);
            Thread.sleep(10);
        }
    }
}
