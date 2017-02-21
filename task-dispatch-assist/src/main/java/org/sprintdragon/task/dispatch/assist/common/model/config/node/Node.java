package org.sprintdragon.task.dispatch.assist.common.model.config.node;

import lombok.Data;
import lombok.ToString;
import org.sprintdragon.task.dispatch.assist.common.model.config.autokeeper.AutoKeeperCluster;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangdi on 17-2-16.
 */
@Data
@ToString
public class Node implements Serializable {

    private static final long serialVersionUID = 1427704645257914286L;
    private String sysId;                                     // 唯一标示id
    private String name;                                   // 机器名字

    private NodeStatus status;                                 // 对应状态

    private AutoKeeperCluster zkCluster;                               // zk的集群
    private ServerInfo serverInfo;

    private Date gmtCreate;                              // 创建时间
    private Date gmtModified;                            // 修改时间

}
