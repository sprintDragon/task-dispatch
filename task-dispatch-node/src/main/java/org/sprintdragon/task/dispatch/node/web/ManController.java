package org.sprintdragon.task.dispatch.node.web;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import dispatch.client.constants.ExecuteTypeEnum;
import dispatch.client.constants.StrategyEnum;
import dispatch.client.domain.TaskMessage;
import dispatch.client.domain.TaskMessageBuilder;
import org.sprintdragon.task.dispatch.node.listener.TaskReceiveListener;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangdi on 16-12-19.
 */
@RestController
@RequestMapping("/man")
@Slf4j
public class ManController {

    @Resource
    TaskReceiveListener taskReceiveListener;
    static final int limit = 100000;

    @RequestMapping(value = "/saveMore/{total}", method = RequestMethod.GET)
    public void saveMore(@PathVariable int total) {
        try {
            if (total >= limit) {
                log.warn("total不能大于{}", limit);
                return;
            }
            long startTime = System.currentTimeMillis();
            ExecutorService executorService = Executors.newFixedThreadPool(10);
            int count = 0;
            while (true) {
                executorService.submit(() -> {
                    String id = new Random().nextInt(100000) + "";
                    TaskMessage taskMessage = TaskMessageBuilder.builder().namespace("as").bizType("2s2").executeType(ExecuteTypeEnum.MQ).executeTopic("wd_test1").executeId(id).executeContext("bbb").strategy(StrategyEnum.AFTER_DISTANCE_1_MINUTE).build();
                    try {
                        taskReceiveListener.execute(id, JSON.toJSONString(taskMessage));
                    } catch (Exception e) {
                        log.error("saveMore inner error id={}", id, e);
                    }
                });
                if (count++ == total) {
                    break;
                }
                Thread.sleep(10);
            }
            log.info("saveMore end cost={}", System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            log.error("saveMore error", e);
        }
    }

//    @Autowired
//    CuratorFramework client;
//
//    @RequestMapping(value = "/viewZk", method = RequestMethod.GET)
//    public void viewZk() {
//        client.getChildren();
//    }

}
