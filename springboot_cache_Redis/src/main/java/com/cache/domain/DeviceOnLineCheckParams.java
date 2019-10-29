package com.cache.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/17
 * @Description:
 */
public class DeviceOnLineCheckParams implements Serializable {
    @NotNull
    private String deviceNo;
    @NotNull
    private Integer channelId;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    @Override
    public String toString() {
        return "SendMessageParams{" +
                "deviceNo='" + deviceNo + '\'' +
                ", channelId=" + channelId +
                '}';
    }
}
