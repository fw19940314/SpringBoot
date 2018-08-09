package com.eml.contorller;

import com.eml.domain.User;
import com.eml.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/9
 * @Description:
 */
@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;

    @ResponseBody
    @GetMapping("/findUserById/{id}")
    public User userfinById(@PathVariable("id") int id){
       User user =  userMapper.getUserById(id);
       return user;
    }

    @ResponseBody
    @GetMapping("/insertUser")
    public User save(User user){
        int total =  userMapper.insertUser(user);
        return user;
    }

}
