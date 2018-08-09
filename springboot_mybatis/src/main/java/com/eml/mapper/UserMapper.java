package com.eml.mapper;

import com.eml.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/9
 * @Description:
 */
public interface UserMapper {

    @Select("select * from USER where id=#{id}")
    User getUserById(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into USER (USER_NAME,USER_AGE) VALUES (#{userName},#{userAge})")
    int insertUser(User user);

}
