package com.cqust.blog.common.resp;

import com.cqust.blog.common.common.ConstantCode;

/**
 * Created by Administrator on 2018/3/23.
 */
public class GeneralResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public GeneralResult() {
        this.code = ConstantCode.SUCCESS;
    }

    public GeneralResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public GeneralResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public GeneralResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
