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

package org.sprintdragon.task.dispatch.shared.arbitrate.config;

import com.sun.deploy.util.StringUtils;
import org.sprintdragon.task.dispatch.shared.common.model.config.node.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 配置操作聚合类，方便mock
 *
 * @author jianghang 2011-9-27 下午08:27:04
 * @version 4.0.0
 */
public class ArbitrateConfigUtils {

    /**
     * 获取当前节点的nid信息
     */
    public static Long getCurrentNid() {
        Node node = ArbitrateConfigRegistry.getConfig().currentNode();
        if (node != null) {
            return node.getId();
        } else {
            return null;
        }
    }

    /**
     * 获取对应Node的zk集群列表配置
     */
    public static List<String> getServerAddrs() {
        Node node = ArbitrateConfigRegistry.getConfig().currentNode();
        if (node != null) {
            String addr = StringUtils.join(node.getParameters().getZkCluster().getServerList(), ",");
            return Arrays.asList(addr);
        } else {
            return new ArrayList<String>();
        }
    }

    /**
     * 根据nid查询node信息
     */
    public static Node findNode(Long nid) {
        return ArbitrateConfigRegistry.getConfig().findNode(nid);
    }
}
