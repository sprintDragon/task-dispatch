package org.sprintdragon.task.dispatch.biz.taskinfo.service;

import java.util.Set;

/**
 * Created by wangdi on 16-12-13.
 */
public interface TaskInfoRedisService {

    String PREFIX_KEY = "task_dispatch_";
    String FULL_ZSET_NAME = PREFIX_KEY + "wait_task_zset";

    /**
     * 获取任务
     *
     * @param fromScore
     * @param toScore
     * @return
     */
    Set<String> listDistanceByScore(long fromScore, long toScore, long offset, long count);

    /**
     * 删除任务
     *
     * @param sysKeyStr
     * @return
     */
    boolean removeByKey(String sysKeyStr);

    /**
     * 插入任务
     *
     * @param upTime
     * @param sysKeyStr
     * @return
     */
    boolean addKey(long upTime, String sysKeyStr);

    long countTotal();
}
