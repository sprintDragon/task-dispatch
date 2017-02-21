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

package org.sprintdragon.task.dispatch.assist.arbitrate.manager.helper;

import org.sprintdragon.task.dispatch.assist.arbitrate.ArbitrateConstants;
import org.sprintdragon.task.dispatch.assist.common.model.config.node.Node;

import java.text.MessageFormat;

/**
 * 对应zookeeper path构建的helper类
 *
 * @author jianghang
 */
public class ManagePathUtils {

    /**
     * 返回对应的otter root path
     */
    public static String getRoot() {
        return ArbitrateConstants.NODE_OTTER_ROOT;
    }

    /**
     * 返回对应的node root path
     */
    public static String getNodeRoot() {
        return ArbitrateConstants.NODE_NID_ROOT;
    }

    /**
     * 返回对应的node path
     */
    public static String getNidPath(Node node) {
        // 根据nodeId构造path
        return MessageFormat.format(ArbitrateConstants.NODE_NID_FORMAT, String.valueOf(node.getSysId()), String.valueOf(node.getServerInfo().getIp()));
    }

    public static String getSysPath(Node node){
        return MessageFormat.format(ArbitrateConstants.NODE_SYS_FORMAT, String.valueOf(node.getSysId()));
    }

}
