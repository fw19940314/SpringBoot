package com.cache.service;

import com.cache.domain.User;
import com.cache.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/10
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    /**
     * @param id
     * @return
     * @Cacheable 注解属性：主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
     * cacheNames/value 使用这两个属性一样效果一样，指定缓存组件名字，将方法的返回结果放在哪个缓存中，数组的方式可以制定多个缓存
     * key 默认是参数的值，也可以自己指定,可以使用spEL表达式指定key的值。缓存在存放数据时，是根据这个key来标注。
     * keyGenerator  key的生成器，可以在自己来实现key的生成策略；指定key的生成器组件id  key/keyGenerator两者二选一
     * cacheManager  指定缓存管理器，或cacheResolver指定获取解析器；
     * condition  指定条件满足才缓存；也可以使用多个条件，用and连接；
     * unless 除非，当unless指定的条件为true时候，不缓存
     * sync 是否异步 （默认是false，在使用异步情况下，unless就不支持了）
     */
    @Cacheable(cacheNames = "user",keyGenerator = "myCacheKeyGenerator")
    @Override
    public User findUserById(Integer id) {
        User user = userMapper.getUserById(id);
        return user;
    }


    /**
     * @param user
     * @return
     * @CachePut 方法既调用，结果也会保存，修改了某条数据，缓存也会跟着更新（需要KEY保持一致）
     * value 既保存数据时指定的cacheName/value
     * key 既查询时，在进行数据保存时的key，这里取值有两种方式
     * 1. 可以通过传入的对象的属性方式拿到更改对象的key  #user.id
     * 2. 也可以通过返回值的值来进行绑定ker的值 #result.id
     */
    @CachePut(value = "user", key = "#user.id")
    @Override
    public User updateUser(User user) {
        userMapper.updateUser(user);
        return user;
    }


    /**
     * @cacheEvict 清除缓存
     * allEntity 清除所有员工，默认的是false,如果设置为true，会将缓存中的所有数据全部清空
     * beforeInvocation 缓存是否在方法执行之前，默认的是false；
     * false，方法执行之后之后执行；
     * true，方法执行之前执行；（缓存必定被清除，无论方法时候有错误）
     */
    @CacheEvict(value = "user", key = "#id")
    public void deleteUser(Integer id) {
        System.out.println("清空ID为" + id + "的缓存");
    }


    /**
     * @param userName
     * @return Caching 组合包含了Cacheable、CachePut、CacheEvict 可以以数组的形式传入多个值
     *
     * 注解的value值可以在类上，统一使用cacheConfig（cacheName="user"）形式替换，
     * 就无需再一个一个的写在具体的注解上
     */
    @Caching(cacheable = {
            @Cacheable(value = "user", key = "#userName")
    }, put = {@CachePut(value = "user", key = "#result.id")}
    )
    public User findUserByUser(String userName) {
        return userMapper.getUserByName(userName);
    }

}
