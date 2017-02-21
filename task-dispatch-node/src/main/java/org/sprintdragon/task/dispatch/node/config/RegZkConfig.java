package org.sprintdragon.task.dispatch.node.config;

import org.springframework.stereotype.Component;
import org.sprintdragon.task.dispatch.assist.arbitrate.manager.NodeArbitrateEvent;
import org.sprintdragon.task.dispatch.assist.common.model.config.node.Node;
import org.sprintdragon.task.dispatch.assist.common.model.config.node.NodeBuilder;
import org.sprintdragon.task.dispatch.assist.common.model.config.node.NodeStatus;
import org.sprintdragon.task.dispatch.assist.common.model.config.node.ServerInfo;
import org.sprintdragon.task.dispatch.assist.common.utils.SystemIpUtil;

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
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setName("本机名1");
        serverInfo.setDescription("描述1");
        serverInfo.setIp(SystemIpUtil.getRealIp());
        Node n = new NodeBuilder().sysId(11l).name("test1").status(NodeStatus.NORMAL).serverInfo(serverInfo).build();

        nodeArbitrateEvent.init(n);
        nodeArbitrateEvent.watch();
    }

}
