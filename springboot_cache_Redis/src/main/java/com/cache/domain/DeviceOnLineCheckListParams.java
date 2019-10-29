package com.cache.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/26
 * @Description:
 */
public class DeviceOnLineCheckListParams implements Serializable {

    private List<DeviceOnLineCheckParams> list;

    public List<DeviceOnLineCheckParams> getList() {
        return list;
    }

    public void setList(List<DeviceOnLineCheckParams> list) {
        this.list = list;
    }
}
