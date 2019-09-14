package com.cache.contorller;


import com.cache.domain.User;
import com.cache.mapper.UserMapper;
import com.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/9
 * @Description:
 */
@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;

    @GetMapping("/findUserById/{id}")
    public User userfinById(@PathVariable("id") Integer id) {
        User user = userService.findUserById(id);
        return user;
    }

    @PostMapping("/insertUser")
    public User save(@RequestBody User user) {
        System.out.println("user:"+user.toString());
        int total = userMapper.insertUser(user);
        return user;
    }

    @GetMapping("/updateUser")
    public User update(User user) {
        User user1 = userService.updateUser(user);
        return user1;
    }

    @GetMapping("/deleteUser")
    public String deleteUser(Integer id) {
        userService.deleteUser(id);
        return "删除成功";
    }

    @GetMapping("/findUserByName")
    public User findUserByName(String userName) {
        User user = userService.findUserByUser(userName);
        return user;
    }

}
