package com.cache.mapper;

import com.cache.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/9
 * @Description:
 */
public interface UserMapper {

    @Select("select * from USER where id=#{id}")
    User getUserById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into USER (USER_NAME,USER_AGE) VALUES (#{userName},#{userAge})")
    int insertUser(User user);

    @Update("update USER SET USER_NAME = #{userName}, USER_AGE = #{userAge} where ID = #{id}")
    int updateUser(User user);

    @Delete("delete from user where ID = #{id}")
    int deleteUser(Integer id);

    @Select("select * from USER where USER_NAME=#{userName}")
    User getUserByName(String userName);
}
