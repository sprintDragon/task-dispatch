/*
 * Copyright (C) 2010-2101 Alibaba Group Holding Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sprintdragon.task.dispatch.assist.common.model.config.node;

import lombok.Data;
import lombok.ToString;
import org.sprintdragon.task.dispatch.assist.common.model.config.autokeeper.AutoKeeperCluster;

import java.io.Serializable;

/**
 * Node节点对应的参数信息
 *
 * @author jianghang 2011-9-16 下午03:39:36
 * @version 4.0.0
 */
@Data
@ToString
public class NodeParameter implements Serializable {

    private static final long serialVersionUID = -4788966688697451950L;
    private Integer mbeanPort;                               // mbean端口
    private Integer downloadPort;                            // 下载端口
    private AutoKeeperCluster zkCluster;                               // zk的集群
    private String externalIp;                              // 外部ip
    private Boolean useExternalIp = false;                // 是否使用外部ip，此优先级高于pipeline参数，设置后包括rpc/pipe都将使用外部ip

}
