package com.cache.service;

import com.cache.domain.User;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/10
 * @Description:
 */
public interface UserService {
    User findUserById(Integer id);

    User updateUser(User user);

    void deleteUser(Integer id);

    User findUserByUser(String userName);

}
