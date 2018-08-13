package com.thymeleaf.config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/7
 * @Description:
 */
public class MyLocalResovler implements LocaleResolver {


    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String paramater = httpServletRequest.getParameter("l");//获取前端请求参数
        Locale locale = Locale.getDefault();//设置默认区域化信息
        if (!StringUtils.isEmpty(paramater)) {
            String[] split = paramater.split("_");
            locale = new Locale(split[0], split[1]);//获取前端请求参数信息
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
