package com.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cache.common.HttpUtil;
import com.cache.config.MyRedisConfig;
import com.cache.domain.DeviceRedisInfo;
import com.cache.domain.SendMessageParams;
import com.cache.domain.User;
import com.cache.mapper.UserMapper;
import com.cache.service.RedisService;
import com.cache.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import sun.jvm.hotspot.HelloWorld;

import java.io.IOException;

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

    @Autowired
    RedisService redisService;


    @Test
    public void contextLoads() {
        stringRedisTemplate.opsForValue().set("key1", "James");
        String value = stringRedisTemplate.opsForValue().get("key1");
        System.out.println(value);
    }


    @Test
    public void contextLoads3() {
//        User user = userMapper.getUserById(2);
        MyRedisTemplate.opsForValue().set("user:02", "niiiiiiii");
        Object user1 = MyRedisTemplate.opsForValue().get("user:02");
        System.out.println(user1);
    }

    @Test
    public void contextLoads2() {
        redisTemplate.opsForValue().setIfAbsent("jerry", "3123121");
        Object o = redisTemplate.opsForValue().get("jerry");
        System.out.println("msg:" + o.toString());
    }

    @Test
    public void getlock() {
        String key = "key1";
        boolean getlock = redisService.getlock(key);
        System.out.println(getlock);
    }

    @Test
    public void sendMsg() throws IOException {
        SendMessageParams sendMessageParams = new SendMessageParams();
        sendMessageParams.setChannelId(1);
        sendMessageParams.setCommandType(3);
        sendMessageParams.setDeviceNo("faceS_1234567890ab");
        sendMessageParams.setMessage("{\\\"tx\\\":\\\"2\\\"}");
        String s1 = JSON.toJSONString(sendMessageParams);
        String s = HttpUtil.HttpPostWithJson("http://192.168.12.61:8012/lookdns/sendMsg", s1);
        System.out.println("result "+s);
    }

    @Test
    public void getvalue(){
        String test_device = stringRedisTemplate.opsForValue().get("deviceVersion:84E0F420014302FA");
        System.out.println(test_device);
        DeviceRedisInfo deviceRedisInfo = JSONObject.parseObject(test_device, DeviceRedisInfo.class);
        System.out.println(deviceRedisInfo.getDeviceKey());

    }

    @Test
    public void incr(){
        Long incr1 = stringRedisTemplate.opsForValue().increment("incr1", 1);
        System.out.println(incr1);

    }
    @Test
    public void getCurrent(){
        String incr1 = stringRedisTemplate.opsForValue().get("incr1123");
        System.out.println(incr1);

    }
}
