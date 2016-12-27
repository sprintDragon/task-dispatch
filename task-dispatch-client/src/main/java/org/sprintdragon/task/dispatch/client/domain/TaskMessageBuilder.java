package org.sprintdragon.task.dispatch.client.domain;


import org.sprintdragon.task.dispatch.client.constants.ExecuteTypeEnum;
import org.sprintdragon.task.dispatch.client.constants.StrategyEnum;

/**
 * Created by wangdi on 16-12-13.
 */
public class TaskMessageBuilder {

    /**
     * 命名空间
     */
    private String namespace;
    /**
     * 任务类型
     */
    private String bizType;
    /**
     * 执行类型
     */
    private Integer executeType;
    /**
     * 执行topic
     */
    private String executeTopic;
    /**
     * 业务号
     */
    private String executeId;
    /**
     * 执行内容
     */
    private String executeContext;
    /**
     * 重试策略
     */
    private StrategyEnum strategyEnum;
    /**
     * 执行时间
     */
    private Long executeTime;

    public TaskMessageBuilder() {
    }

    public static TaskMessageBuilder builder() {
        return new TaskMessageBuilder();
    }

    public TaskMessageBuilder namespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public TaskMessageBuilder bizType(String bizType) {
        this.bizType = bizType;
        return this;
    }

    public TaskMessageBuilder executeType(ExecuteTypeEnum executeTypeEnum) {
        this.executeType = executeTypeEnum.getCode();
        return this;
    }

    public TaskMessageBuilder executeTopic(String executeTopic) {
        this.executeTopic = executeTopic;
        return this;
    }

    public TaskMessageBuilder executeId(String executeId) {
        this.executeId = executeId;
        return this;
    }

    public TaskMessageBuilder executeContext(String executeContext) {
        this.executeContext = executeContext;
        return this;
    }

    public TaskMessageBuilder strategy(StrategyEnum strategyEnum) {
        this.strategyEnum = strategyEnum;
        return this;
    }

    public TaskMessageBuilder executeTime(Long executeTime) {
        this.executeTime = executeTime;
        return this;
    }


    public TaskMessage build() {
        TaskMessage info = new TaskMessage();
        info.setClientTime(System.currentTimeMillis());
        if (namespace == null || bizType == null || executeType == null || executeTopic == null || executeId == null || executeContext == null || strategyEnum == null) {
            throw new RuntimeException("build TaskMessage 必填字段缺失");
        }
        info.setNamespace(namespace);
        info.setBizType(bizType);
        info.setExecuteType(executeType);
        info.setExecuteTopic(executeTopic);
        info.setExecuteId(executeId);
        info.setExecuteContext(executeContext);
        info.setStrategy(strategyEnum.getCode());
        info.setExecuteTime(executeTime);
        return info;
    }

}
