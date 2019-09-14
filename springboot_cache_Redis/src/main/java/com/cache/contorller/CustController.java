package com.cache.contorller;

import com.cache.domain.Cust;
import com.cache.mapper.CustMapper;
import com.cache.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/9
 * @Description:
 */
@Controller
public class CustController {
    @Autowired
    CustMapper custMapper;

    @Autowired
    RedisService redisService;

    @ResponseBody
    @GetMapping("/findCustById/{id}")
    public Cust custfinById(@PathVariable("id") int id) {
        Cust cust = custMapper.findCustById(id);
        return cust;
    }

    @ResponseBody
    @GetMapping("/insertCust")
    public int insertCust(Cust cust) {
        int total = custMapper.insertCust(cust);
        return total;
    }

    @ResponseBody
    @GetMapping("/getlock")
    public boolean getlock(String key) {
        System.out.println("key:" + key);
        boolean getlock = redisService.getlock(key);
        return getlock;
    }


    @ResponseBody
    @GetMapping("/unlock")
    public void unlock(String key) {
        System.out.println("key:" + key);
        redisService.unlock(key);
    }
}
