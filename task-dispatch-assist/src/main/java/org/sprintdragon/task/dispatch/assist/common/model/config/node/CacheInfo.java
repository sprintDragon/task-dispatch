package org.sprintdragon.task.dispatch.assist.common.model.config.node;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by wangdi on 17-2-20.
 */
@Data
@ToString
public class CacheInfo {

    private String domain;                                   // 域名
    private List<ServerInfo> serverInfos;

}
