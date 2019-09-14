package com.cache.service;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/10
 * @Description:
 */
public interface RedisService {
    boolean getlock(String key);
    boolean unlock(String key);
}
