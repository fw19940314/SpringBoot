package com.cache.service;

import com.cache.RedisLock.RedisDomain;
import com.cache.RedisLock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/10
 * @Description:
 */
@Service
public class RedisServiceImpl implements RedisService{
    @Autowired
    RedisDomain redisDomain;
    int count = 0;
    @Override
    public boolean getlock(String key) {
        count++;
        if (count==2){
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        boolean lock = redisDomain.lock(key);
        System.out.println("第"+count+"次获取锁-》"+lock);
        return lock;
    }

    @Override
    public boolean unlock(String key) {
        redisDomain.unlock(key);
        return false;
    }
}
