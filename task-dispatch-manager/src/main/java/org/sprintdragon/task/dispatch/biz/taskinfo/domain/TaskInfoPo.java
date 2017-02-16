package org.sprintdragon.task.dispatch.biz.taskinfo.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wangdi on 16-11-3.
 */
@Data
@ToString
@Entity
@Table(name = "pop_task_dispatch_taskinfo")
public class TaskInfoPo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false)
    private Long id;

    /**
     * 系统主键
     */
    @Column(name = "SYS_KEY", updatable = false)
    @Id
    private Long sysKey;
    /**
     * 命名空间
     */
    @Column(name = "NAMESPACE", updatable = false)
    private String namespace;
    /**
     * 任务类型
     */
    @Column(name = "BIZ_TYPE", updatable = false)
    private String bizType;
    /**
     * 客户端时间
     */
    @Column(name = "CLIENT_TIME")
    private Long clientTime;
    /**
     * 执行时间
     */
    @Column(name = "EXECUTE_TIME")
    private Long executeTime;
    /**
     * 重试策略 (1.等时长一直重试，2.递进时长，3.自定义策略)
     */
    @Column(name = "STRATEGY")
    private Integer strategy;
    /**
     * 执行类型
     */
    @Column(name = "EXECUTE_TYPE")
    private Integer executeType;
    /**
     * 执行topic
     */
    @Column(name = "EXECUTE_TOPIC")
    private String executeTopic;
    /**
     * 业务号
     */
    @Column(name = "EXECUTE_ID", updatable = false)
    private String executeId;
    /**
     * 执行内容
     */
    @Column(name = "EXECUTE_CONTEXT")
    private String executeContext;
    /**
     * 入库时间
     */
    @Column(name = "CREATED")
    private Date created;

    /**
     * 修改时间
     */
    @Column(name = "MODIFIED")
    private Date modified;

}
