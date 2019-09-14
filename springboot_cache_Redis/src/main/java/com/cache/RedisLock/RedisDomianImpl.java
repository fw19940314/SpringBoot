package com.cache.RedisLock;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/10
 * @Description:
 */
@Service
public class RedisDomianImpl implements RedisDomain {
    @Resource
    RedisTemplate redisTemplate;

    @Override
    public boolean lock(String key) {
        RedisLock redisLock = new RedisLock(redisTemplate,key);
        try {
            return redisLock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean unlock(String key) {
        RedisLock redisLock = new RedisLock(redisTemplate,key);
        redisLock.unlock();
        return false;
    }
}
