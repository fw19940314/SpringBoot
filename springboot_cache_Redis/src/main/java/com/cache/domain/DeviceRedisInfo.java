package com.cache.domain;

import java.io.Serializable;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/11
 * @Description:
 */
public class DeviceRedisInfo implements Serializable {

    private String deviceKey;

    private String versionNo;

    private String sdkVersionNo;


    public DeviceRedisInfo() {
    }

    public DeviceRedisInfo(String deviceKey, String versionNo, String sdkVersionNo) {
        this.deviceKey = deviceKey;
        this.versionNo = versionNo;
        this.sdkVersionNo = sdkVersionNo;
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public void setDeviceKey(String deviceKey) {
        this.deviceKey = deviceKey;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getSdkVersionNo() {
        return sdkVersionNo;
    }

    public void setSdkVersionNo(String sdkVersionNo) {
        this.sdkVersionNo = sdkVersionNo;
    }

    @Override
    public String toString() {
        return "DeviceRedisInfo{" +
                "deviceKey='" + deviceKey + '\'' +
                ", versionNo='" + versionNo + '\'' +
                ", sdkVersionNo='" + sdkVersionNo + '\'' +
                '}';
    }
}
