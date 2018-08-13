package com.cache;

import com.cache.config.MyRedisConfig;
import com.cache.domain.User;
import com.cache.mapper.UserMapper;
import com.cache.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCacheApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate MyRedisTemplate;


    @Test
    public void contextLoads() {
//        stringRedisTemplate.opsForValue().set("key1", "James");
//        String value = stringRedisTemplate.opsForValue().get("key1");
//        System.out.println(value);
    }


    @Test
    public void contextLoads3() {
//        User user = userMapper.getUserById(2);
//        MyRedisTemplate.opsForValue().set("user:02",user);
        Object user1 = MyRedisTemplate.opsForValue().get("user:02");
        System.out.println(user1);
    }

//    @Test
//    public void contextLoads2() {
//        User user = userMapper.getUserById(1);
//        System.out.println(user);
//    }

}
