package com.cache.domain;

import java.io.Serializable;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/17
 * @Description:
 */
public class DeviceOnlineStateView implements Serializable {
    private static final long serialVersionUID = 1;
    private String deviceNo;
    //1上线，0下线
    private Byte deviceStatus;

    private Long lastUpdateTime;

    public Byte getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(Byte deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public Long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public DeviceOnlineStateView() {
    }

    public DeviceOnlineStateView(String deviceNo, Byte deviceStatus, Long lastUpdateTime) {
        this.deviceNo = deviceNo;
        this.deviceStatus = deviceStatus;
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    @Override
    public String toString() {
        return "DeviceOnlineStateView{" +
                "deviceNo='" + deviceNo + '\'' +
                ", deviceStatus=" + deviceStatus +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }
}
