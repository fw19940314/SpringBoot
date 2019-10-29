package com.cache.domain;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/25
 * @Description:
 */
public class ResultIotResponse<T> {
    private String code;

    private String msg;

    private Boolean success;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultIotResponse{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
