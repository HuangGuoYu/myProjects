package com.cqust.blog.common.resp;

import com.cqust.blog.common.common.ConstantCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2018/3/23.
 */
@Getter
@Setter
public class GeneralResult<T> {

    private Integer code;

    private String msg;

    private T data;

    private String url;

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

    public GeneralResult<?> error(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public GeneralResult<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public GeneralResult<?> ok(T data) {
        this.data = data;
        return this;
    }
}
