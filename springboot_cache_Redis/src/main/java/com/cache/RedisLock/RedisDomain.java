package com.cache.RedisLock;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/10
 * @Description:
 */
public interface RedisDomain {
   boolean lock(String key);
   boolean unlock(String key);
}
