package org.sprintdragon.task.dispatch.config;

import org.springframework.stereotype.Component;
import org.sprintdragon.task.dispatch.assist.arbitrate.manager.NodeArbitrateEvent;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by wangdi on 17-2-13.
 */
@Component
public class RegZkConfig {

    @Resource
    NodeArbitrateEvent nodeArbitrateEvent;

    @PostConstruct
    public void installZkConfig() throws Exception {
        nodeArbitrateEvent.init(1l);
    }

}
