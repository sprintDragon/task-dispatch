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

package org.sprintdragon.task.dispatch.shared.arbitrate.config.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.sprintdragon.task.dispatch.shared.arbitrate.config.ArbitrateConfig;
import org.sprintdragon.task.dispatch.shared.arbitrate.config.ArbitrateConfigRegistry;
import org.sprintdragon.task.dispatch.shared.arbitrate.config.ConfigClientService;
import org.sprintdragon.task.dispatch.shared.common.communication.NodeCommmunicationClient;
import org.sprintdragon.task.dispatch.shared.common.model.config.ConfigException;
import org.sprintdragon.task.dispatch.shared.common.model.config.node.Node;
import org.sprintdragon.task.dispatch.shared.common.utils.cache.RefreshMemoryMirror;
import org.sprintdragon.task.dispatch.shared.communication.model.config.FindNodeEvent;

import java.util.Map;

/**
 * task节点对应的config对象管理服务
 *
 * @author jianghang
 */
public class ConfigClientServiceImpl implements ConfigClientService, ArbitrateConfig,InitializingBean {

    private static final String NID_NAME = "nid";
    private static final Long DEFAULT_PERIOD = 60 * 1000L;
    private static final Logger logger = LoggerFactory.getLogger(ConfigClientService.class);

    private Long timeout = DEFAULT_PERIOD;
    private Long nid;
    private NodeCommmunicationClient nodeCommmunicationClient;
    private Map<Long, Long> channelMapping;                                                     // 将pipelineId映射为channelId
    private RefreshMemoryMirror<Long, Node> nodeCache;

    public ConfigClientServiceImpl() {
        // 注册一下事件处理
        ArbitrateConfigRegistry.regist(this);
    }

    public Node currentNode() {
        Node node = nodeCache.get(nid);
        if (node == null) {
            throw new ConfigException("nid:" + nid + " in manager[" + nodeCommmunicationClient.getManagerAddress()
                    + "]is not found!");
        }

        return node;
    }

    public Node findNode(Long nid) {
        return nodeCache.get(nid);
    }

    public void afterPropertiesSet() throws Exception {
        // 获取一下nid变量
        String nid = System.getProperty(NID_NAME);
        if (StringUtils.isEmpty(nid)) {
            throw new ConfigException("nid is not set!");
        }

        this.nid = Long.valueOf(nid);

        nodeCache = new RefreshMemoryMirror<Long, Node>(timeout, new RefreshMemoryMirror.ComputeFunction<Long, Node>() {

            public Node apply(Long key, Node oldValue) {
                FindNodeEvent event = new FindNodeEvent();
                event.setNid(key);
                try {
                    Object obj = nodeCommmunicationClient.callManager(event);
                    if (obj != null && obj instanceof Node) {
                        return (Node) obj;
                    } else {
                        throw new ConfigException("No Such Node by id[" + key + "]");
                    }
                } catch (Exception e) {
                    logger.error("call_manager_error", event.toString(), e);
                }
                // 其他情况直接返回内存中的旧值
                return oldValue;
            }
        });
    }

    // =================== setter / getter ======================

    public void setNodeCommmunicationClient(NodeCommmunicationClient nodeCommmunicationClient) {
        this.nodeCommmunicationClient = nodeCommmunicationClient;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

}
