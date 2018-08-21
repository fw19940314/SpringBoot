package com.el.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/14
 * @Description:
 */
@Service
public class AsyncService {

    @Async
    public void asnyc(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello world.....");
    }

    @Scheduled(cron = "* * * * * ?")
    public void shceduled(){
        System.out.println("hello shceduled.....");
    }
}
