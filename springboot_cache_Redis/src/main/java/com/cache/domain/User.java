package com.cache.domain;

import java.io.Serializable;

/**
 * @Auther: jerry.feng
 * @Date: 2018/8/9
 * @Description:
 */
public class User implements Serializable {
    private Integer id;
    private String userName;
    private String userAge;
    private String userVersion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserVersion() {
        return userVersion;
    }

    public void setUserVersion(String userVersion) {
        this.userVersion = userVersion;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userAge='" + userAge + '\'' +
                ", version=" + userVersion +
                '}';
    }
}
