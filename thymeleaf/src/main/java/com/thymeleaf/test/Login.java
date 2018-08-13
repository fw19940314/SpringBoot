package com.thymeleaf.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/7
 * @Description:
 */
@Controller
public class Login {
    Logger logger = LoggerFactory.getLogger(Long.class);

    @RequestMapping(value = {"/","/index.html"})
    public String index(Map<String,Object> map, HttpServletRequest request){
        logger.debug("index====",request);
//        map.put("msg","");
        return "login";
    }

    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        logger.debug("param= userName{}", username,password);
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser",username);//向请求域中加入登录对象
            return "redirect:/main.html";
        }else{
            //登陆失败
            map.put("msg","用户名密码错误");
            return  "login";
        }

    }
}
