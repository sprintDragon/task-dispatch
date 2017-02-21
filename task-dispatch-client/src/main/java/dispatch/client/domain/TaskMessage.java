package dispatch.client.domain;

/**
 * Created by wangdi on 16-12-13.
 */
public class TaskMessage {

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
     * 客户端时间
     */
    private Long clientTime;
    /**
     * 执行时间
     */
    private Long executeTime;
    /**
     * 选填
     */
    /**
     * 重试策略
     */
    private Integer strategy;

    public TaskMessage() {
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public Long getClientTime() {
        return clientTime;
    }

    public void setClientTime(Long clientTime) {
        this.clientTime = clientTime;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public Integer getStrategy() {
        return strategy;
    }

    public void setStrategy(Integer strategy) {
        this.strategy = strategy;
    }

    public Integer getExecuteType() {
        return executeType;
    }

    public void setExecuteType(Integer executeType) {
        this.executeType = executeType;
    }

    public String getExecuteTopic() {
        return executeTopic;
    }

    public void setExecuteTopic(String executeTopic) {
        this.executeTopic = executeTopic;
    }

    public String getExecuteId() {
        return executeId;
    }

    public void setExecuteId(String executeId) {
        this.executeId = executeId;
    }

    public String getExecuteContext() {
        return executeContext;
    }

    public void setExecuteContext(String executeContext) {
        this.executeContext = executeContext;
    }
}
