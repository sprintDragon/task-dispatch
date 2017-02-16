package org.sprintdragon.task.dispatch.assist.common.model.config.node;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangdi on 17-2-16.
 */
@Data
@ToString
public class Node implements Serializable {

    private static final long serialVersionUID = 1427704645257914286L;
    private Long id;                                     // 唯一标示id
    private String name;                                   // 机器名字
    private String ip;                                     // 机器ip
    private Long port;                                   // 和manager对应的通讯端口
    private NodeStatus status;                                 // 对应状态
    private NodeParameter parameters = new NodeParameter(); // node对应参数信息
    private String description;                            // 详细描述
    private Date gmtCreate;                              // 创建时间
    private Date gmtModified;                            // 修改时间

}
