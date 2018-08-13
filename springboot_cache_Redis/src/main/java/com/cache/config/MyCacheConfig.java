package com.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/10
 * @Description: 自定义KeyGenerator生成策略
 */
@Configuration
public class MyCacheConfig {

    /**
     * 默认组件id就是方法名，可以自己自定义
     * @return
     */
    @Bean("myCacheKeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                //返回方法名+参数
                return method.getName() + Arrays.asList(objects).toString();
            }
        };
    }
}
