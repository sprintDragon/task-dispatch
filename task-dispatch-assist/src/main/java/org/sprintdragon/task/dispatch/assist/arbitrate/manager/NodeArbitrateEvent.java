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

package org.sprintdragon.task.dispatch.assist.arbitrate.manager;

import com.alibaba.fastjson.JSON;
import lombok.extern.java.Log;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.sprintdragon.task.dispatch.assist.arbitrate.ArbitrateConstants;
import org.sprintdragon.task.dispatch.assist.arbitrate.ArbitrateEvent;
import org.sprintdragon.task.dispatch.assist.arbitrate.ArbitrateException;
import org.sprintdragon.task.dispatch.assist.arbitrate.manager.helper.ManagePathUtils;
import org.sprintdragon.task.dispatch.assist.common.model.config.node.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 机器node节点的相关信号
 *
 * @author jianghang 2011-8-31 下午07:26:02
 */
@Log
public class NodeArbitrateEvent implements ArbitrateEvent {

    private static CuratorFramework zookeeper;

    static {
        String zookeeperConnectionString = "192.168.1.2:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        zookeeper = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        zookeeper.start();
    }

    /**
     * 创建相应的node节点，说明：node节点的生命周期为EPHEMERAL
     * <p>
     * <pre>
     * 1. 是个同步调用
     * </pre>
     */
    public void init(Node node) {
        String path = ManagePathUtils.getNidPath(node);
        try {
            zookeeper.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, JSON.toJSONString(node).getBytes());// 创建为临时节点
        } catch (Exception e) {
            throw new ArbitrateException("Node_init", node.toString(), e);
        }
    }

    public void watch(){
        PathChildrenCache watcher = new PathChildrenCache(zookeeper, ManagePathUtils.getNodeRoot(), true);
        watcher.getListenable().addListener((client, event) -> {
            ChildData data = event.getData();
            if (data == null) {
                log.info("##############No data in event[" + event + "]");
            } else {
                log.info("################Receive event: "
                        + "type=[" + event.getType() + "]"
                        + ", path=[" + data.getPath() + "]"
                        + ", data=[" + new String(data.getData()) + "]"
                        + ", stat=[" + data.getStat() + "]");
            }
        });
        try {
            watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Register zk watcher successfully!");
    }

    /**
     * 销毁的node节点
     * <p>
     * <pre>
     * 1. 是个同步调用
     * </pre>
     */
    public void destory(Node node) {
        String path = ManagePathUtils.getNidPath(node);
        try {
            zookeeper.delete().forPath(path); // 删除节点，不关心版本
        } catch (Exception e) {
            throw new ArbitrateException("Node_destory", node.getName(), e);
        }
    }

    /**
     * 获取当前存活的节点列表
     */
    public List<Long> liveNodes() {
        String path = ArbitrateConstants.NODE_NID_ROOT;
        try {
            List<String> nids = zookeeper.getChildren().forPath(path);
            List<Long> result = new ArrayList<Long>();
            for (String nid : nids) {
                result.add(Long.valueOf(nid));
            }

            return result;
        } catch (Exception e) {
            throw new ArbitrateException("liveNodes", e);
        }
    }

}
