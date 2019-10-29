package com.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cache.common.HttpUtil;
import com.cache.config.MyRedisConfig;
import com.cache.domain.*;
import com.cache.mapper.UserMapper;
import com.cache.service.RedisService;
import com.cache.service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        sendMessageParams.setChannelId(0);
        sendMessageParams.setCommandType(3);
        sendMessageParams.setDeviceNo("84E0F4200D8402FA");
//        sendMessageParams.setMessage("{\\\"tx\\\":\\\"2\\\"}");
        String s1 = JSON.toJSONString(sendMessageParams);
        String s = HttpUtil.HttpPostWithJson("http://192.168.1.141:8012/lookdns/sendMsg", s1);
        ResultIotResponse<JSON> resultIotResponse = JSON.parseObject(s, ResultIotResponse.class);
        if ("1000".equals(resultIotResponse.getCode())) {
            System.out.println("code==="+resultIotResponse.getCode());
            System.out.println("re==="+JSON.toJSONString(resultIotResponse));
            DeviceBaise deviceBaise = JSON.parseObject(resultIotResponse.getData().toJSONString(), DeviceBaise.class);
            System.out.println("data==="+deviceBaise.getDeviceNo());
        }
    }

    @Test
    public void deviceOnline() throws IOException {
        HashMap<String, Boolean> objectObjectHashMap = new HashMap<>();
        DeviceOnLineCheckParams deviceOnLineCheckParams = new DeviceOnLineCheckParams();
        deviceOnLineCheckParams.setChannelId(0);
        deviceOnLineCheckParams.setDeviceNo("84E0F4226079607A");
        String s1 = JSON.toJSONString(deviceOnLineCheckParams);
        String result = HttpUtil.HttpPostWithJson("http://192.168.1.141:8012/lookdns/onlineCheck", s1);
        ResponseModel<JSON> responseModel = JSON.parseObject(result, ResponseModel.class);
        System.out.println(responseModel.getData().toJSONString());
        DeviceOnlineStateView deviceOnlineStateView = JSON.parseObject(responseModel.getData().toJSONString(), DeviceOnlineStateView.class);

    }

    @Test
    public void devieListonLine(){
        DeviceOnLineCheckListParams deviceOnLineCheckListParams = new DeviceOnLineCheckListParams();
        List<DeviceOnLineCheckParams> list = new ArrayList<>();
        DeviceOnLineCheckParams deviceOnLineCheckParams = new DeviceOnLineCheckParams();
        deviceOnLineCheckParams.setChannelId(0);
        deviceOnLineCheckParams.setDeviceNo("84E0F4221A0E30FA");
//        DeviceOnLineCheckParams deviceOnLineCheckParams1 = new DeviceOnLineCheckParams();
//        deviceOnLineCheckParams1.setChannelId(0);
//        deviceOnLineCheckParams1.setDeviceNo("84E0F4200D8402FA");
        list.add(deviceOnLineCheckParams);
//        list.add(deviceOnLineCheckParams1);
        System.out.println(list.toString());
        deviceOnLineCheckListParams.setList(list);
        String s1 = JSON.toJSONString(deviceOnLineCheckListParams);
        String result = HttpUtil.HttpPostWithJson("http://192.168.12.61:8012/lookdns/onlineCheckList", s1);
        ResponseModel<JSON> responseModel = JSON.parseObject(result, ResponseModel.class);
        if (responseModel.getCode().equals("1000")){
            System.out.println("json="+responseModel.getData().toJSONString());
            List<DeviceOnlineStateView> deviceOnlineStateViews1 = JSON.parseArray(responseModel.getData().toJSONString()).toJavaList(DeviceOnlineStateView.class);
            List<DeviceOnlineStateView> deviceOnlineStateViews = JSON.parseArray(responseModel.getData().toJSONString(), DeviceOnlineStateView.class);

            System.out.println("deviceOnlineStateViews="+deviceOnlineStateViews);
            System.out.println("deviceOnlineStateViews1="+deviceOnlineStateViews1);
//            deviceOnlineStateViews.stream().filter()
        }

    }

    @Test
    public void getvalue() {
//        84E0F4219C3B01FA,84E0F420014302F8,84E0F420014302FA,84E0F4200CA602FA,84E0F4200DD102FA,84E0F4200D8402FA,84E0F4221A0E30FA 84E0F4214B2502FA
        String test_device = stringRedisTemplate.opsForValue().get("deviceVersion:84E0F4226079607A");
        System.out.println(test_device);
        DeviceRedisInfo deviceRedisInfo = JSONObject.parseObject(test_device, DeviceRedisInfo.class);
        System.out.println(deviceRedisInfo.getDeviceKey());

    }

    @Test
    public void incr() {
        Long incr1 = stringRedisTemplate.opsForValue().increment("incr1", 1);
        System.out.println(incr1);

    }

    @Test
    public void getCurrent() {
        String incr1 = stringRedisTemplate.opsForValue().get("incr1123");
        System.out.println(incr1);

    }

    @Test
    public void te() {
        String str = "{" + "deviceStatus" + ":0," + "lastUpdateTime" + ":" + "1569401365719" + "}";
        DeviceOnlineStateView deviceOnlineStateView = JSONObject.parseObject(str, DeviceOnlineStateView.class);
        System.out.println(deviceOnlineStateView.toString());
    }
}
