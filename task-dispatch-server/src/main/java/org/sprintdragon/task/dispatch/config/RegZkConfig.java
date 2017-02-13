package org.sprintdragon.task.dispatch.config;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by wangdi on 17-2-13.
 */
@Component
public class RegZkConfig {

    @Autowired
    CuratorFramework client;

    @PostConstruct
    public void installZkConfig() throws Exception {
        System.out.println("##################!!");
    }

}
