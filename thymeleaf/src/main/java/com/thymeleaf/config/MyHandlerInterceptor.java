package com.thymeleaf.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/7
 * @Description:自定义拦截器
 */

public class MyHandlerInterceptor implements HandlerInterceptor {
    Logger logger = LoggerFactory.getLogger(MyHandlerInterceptor.class);

    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.debug("拦截登录请求。。。");
        Object loginUser = httpServletRequest.getSession().getAttribute("loginUser");
        if (loginUser == null) {//未登录返回登录页面
            httpServletRequest.setAttribute("msg","未登录");
            httpServletRequest.getRequestDispatcher("/index.html").forward(httpServletRequest,httpServletResponse);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
