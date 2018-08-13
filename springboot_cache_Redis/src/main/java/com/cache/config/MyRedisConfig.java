package com.cache.config;

import com.cache.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/11
 * @Description:
 */
@Configuration
public class MyRedisConfig {

    @Bean("MyRedisTemplate")
    public RedisTemplate<Object, User> myRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, User> template = new RedisTemplate<Object, User>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<User> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<User>(User.class);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }


    /**
     * @param MyRedisTemplate
     * @return
     * 定制自己的序列化器
     */
    @Bean
    public RedisCacheManager myCacheManager(RedisTemplate<Object, User> MyRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(MyRedisTemplate);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

}
