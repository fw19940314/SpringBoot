package com.rabbitmq.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/13
 * @Description:
 */
@Service
public class ListenReceive {

    @RabbitListener(queues = "huoshan.news")
    public void listenReceive(String msg){
        System.out.println("接收到的消息为："+msg);
    }
}
