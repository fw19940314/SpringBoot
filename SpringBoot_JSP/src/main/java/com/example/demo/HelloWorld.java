package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/8
 * @Description:
 */
@Controller
public class HelloWorld {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/hello")
    public String hello(Map<String,Object> map){
        map.put("msg","成功返回");
        return "success";
    }

    @ResponseBody
    @RequestMapping("/query")
    public Map<String,Object> query(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * FROM USER");
        return list.get(0);
    }
}
