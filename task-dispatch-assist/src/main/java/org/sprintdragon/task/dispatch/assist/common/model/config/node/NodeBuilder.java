package org.sprintdragon.task.dispatch.assist.common.model.config.node;

import org.sprintdragon.task.dispatch.assist.common.model.config.autokeeper.AutoKeeperCluster;

/**
 * Created by wangdi on 17-2-21.
 */
public class NodeBuilder {

    private Long sysId;                                     // 单元唯一标示id
    private String name;                                   // 单元名字
    private NodeStatus status;

    private AutoKeeperCluster zkCluster;
    private ServerInfo serverInfo;

    public NodeBuilder sysId(Long sysId) {
        this.sysId = sysId;
        return this;
    }

    public NodeBuilder name(String name) {
        this.name = name;
        return this;
    }

    public NodeBuilder status(NodeStatus status) {
        this.status = status;
        return this;
    }

    public NodeBuilder zkCluster(AutoKeeperCluster autoKeeperCluster) {
        this.zkCluster = autoKeeperCluster;
        return this;
    }

    public NodeBuilder serverInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
        return this;
    }

    public Node build() {
        Node node = new Node();
        node.setName(name);
        node.setSysId(sysId);
        node.setStatus(status);
        node.setZkCluster(zkCluster);
        node.setServerInfo(serverInfo);
        return node;
    }

}
