package com.el.controller;

import com.el.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/14
 * @Description:
 */
@RestController
public class Helloworld {
    @Autowired
    AsyncService asyncService;

    @RequestMapping("/hello")
    public String hello(){
        asyncService.asnyc();
        return "success";
    }
    @RequestMapping("/scheduled")
    public String scheduled(){
        asyncService.shceduled();
        return "success";
    }


}
