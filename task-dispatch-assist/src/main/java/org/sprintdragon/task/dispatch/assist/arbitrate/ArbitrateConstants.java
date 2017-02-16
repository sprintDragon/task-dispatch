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

package org.sprintdragon.task.dispatch.assist.arbitrate;

/**
 * 仲裁器相关常量定义
 * 
 * @author jianghang
 */
public interface ArbitrateConstants {

    /**
     * otter的根节点
     */
    public String NODE_OTTER_ROOT         = "/taskdispatch";

    /**
     * otter的node机器的根节点
     */
    public String NODE_NID_ROOT           = NODE_OTTER_ROOT + "/node";

    /**
     * otter中node节点的format格式,接受nid做为参数
     */
    public String NODE_NID_FORMAT         = NODE_NID_ROOT + "/{0}";


}
