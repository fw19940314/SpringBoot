package com.cache.domain;

import java.io.Serializable;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/11
 * @Description:
 */
public class SendMsgRespModel implements Serializable {
    private String code;
    private String msg;
    private Boolean success;
    private DeviceModel data;

    public SendMsgRespModel(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    class DeviceModel {
        Integer channelId;
        String deviceNo;

        public Integer getChannelId() {
            return channelId;
        }

        public void setChannelId(Integer channelId) {
            this.channelId = channelId;
        }

        public String getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DeviceModel getData() {
        return data;
    }

    public void setData(DeviceModel data) {
        this.data = data;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendMsgRespModel{");
        sb.append("code='").append(code).append('\'');
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", success=").append(success);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

}
