package com.cache.domain;

import com.uniubi.common.enums.ErrorCode;
import com.uniubi.common.exception.ErrorCodeRTException;

import java.io.Serializable;

/**
 * @Auther: jerry.feng
 * @Date: 2019/9/17
 * @Description:
 */
public class ResponseModel<T> implements Serializable {
    private static final long serialVersionUID = -5661429081340062999L;

    public ResponseModel() {
        this(ErrorCode.SUCCESS);
    }

    public ResponseModel(ErrorCodeRTException ecrtException) {
        this.code = ecrtException.getCode();
        this.msg = ecrtException.getMsg();
    }

    public ResponseModel(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    public ResponseModel(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
        this.data = data;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMsg();
    }

    /**
     * @Title: @Description: TODO(这里用一句话描述这个方法的作用) @author:hb @param code @param msg @param memo @param data @return @throws
     */
    public ResponseModel(String code, String msg, String memo, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.memo = memo;
        this.data = data;
    }

    // 错误码
    private String code; // 这里应该尽量使用数值
    // 错误信息
    private String msg;
    // 错误描述
    private String memo;

    private T data;

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }


    public boolean isSuccess() {
        return ErrorCode.SUCCESS.getCode().equals(this.getCode());
    }
}
