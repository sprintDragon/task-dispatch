package org.sprintdragon.task.dispatch.biz.taskinfo.domain;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by wangdi on 16-12-13.
 */
@Data
@ToString
public class TaskInfo {

    /**
     * 系统主键
     */
    @NotNull(message = "[sysKey] may not be null")
    private Long sysKey;
    /**
     * 命名空间
     */
    @NotNull @Size(min = 1, max = 100)
    private String namespace;
    /**
     * 任务类型
     */
    @NotNull @Size(min = 1, max = 100)
    private String bizType;
    /**
     * 客户端时间
     */
    @NotNull(message = "[clientTime] may not be null")
    private Long clientTime;
    /**
     * 执行时间
     */
    @NotNull(message = "[executeTime] may not be null")
    private Long executeTime;
    /**
     * 重试策略 (1.等时长一直重试，2.递进时长，3.自定义策略)
     */
    @NotNull(message = "[strategy] may not be null")
    private Integer strategy;
    /**
     * 执行类型
     */
    @NotNull(message = "[executeType] may not be null")
    private Integer executeType;
    /**
     * 执行topic
     */
    private String executeTopic;
    /**
     * 执行业务号
     */
    private String executeId;
    /**
     * 执行内容
     */
    private String executeContext;

}
