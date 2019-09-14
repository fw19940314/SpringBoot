package com.cache.RedisLock;

import java.util.UUID;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/10
 * @Description:
        */
public class GuidUtil {
    public static String newGuid() {
        UUID uuid = UUID.randomUUID();
        String guid = uuid.toString();
        guid = guid.toUpperCase();
        guid = guid.replaceAll("-", "");
        return guid;
    }
}
