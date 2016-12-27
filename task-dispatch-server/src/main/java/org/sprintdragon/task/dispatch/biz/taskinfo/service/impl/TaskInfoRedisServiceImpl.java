package org.sprintdragon.task.dispatch.biz.taskinfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.sprintdragon.task.dispatch.biz.taskinfo.service.TaskInfoRedisService;

import java.util.Set;

/**
 * Created by wangdi on 16-12-27.
 */
@Service
public class TaskInfoRedisServiceImpl implements TaskInfoRedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Set<String> listDistanceByScore(long fromScore, long toScore, long offset, long count) {
        return stringRedisTemplate.opsForZSet().rangeByScore(FULL_ZSET_NAME, fromScore, toScore, offset, count);
    }

    @Override
    public boolean removeByKey(String sysKeyStr) {
        return stringRedisTemplate.opsForZSet().remove(FULL_ZSET_NAME, sysKeyStr) >= 1;
    }

    @Override
    public boolean addKey(long upTime, String sysKeyStr) {
        return stringRedisTemplate.opsForZSet().add(FULL_ZSET_NAME, sysKeyStr, upTime);
    }

    @Override
    public long countTotal() {
        return stringRedisTemplate.opsForZSet().count(FULL_ZSET_NAME, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
