package org.sprintdragon.task.dispatch.assist.common.model.config.node;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * Created by wangdi on 17-2-20.
 */
@Data
@ToString
public class SysInfo {

    private List<ServerInfo> serverInfos;
    private List<DbInfo> dbInfos;
    private List<CacheInfo> cacheInfos;

}
