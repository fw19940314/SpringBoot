package com.cache.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/11
 * @Description:
 */
public class SendMessageParams implements Serializable {
    @NotNull
    private String deviceNo;
    private Integer channelId;
    private Integer commandType;
    private String message;

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

    public Integer getCommandType() {
        return commandType;
    }

    public void setCommandType(Integer commandType) {
        this.commandType = commandType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SendMessageParams{" +
                "deviceNo='" + deviceNo + '\'' +
                ", channelId=" + channelId +
                ", commandType=" + commandType +
                ", message='" + message + '\'' +
                '}';
    }

}
