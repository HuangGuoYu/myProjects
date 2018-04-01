package com.cqust.blog.common.common;

/**
 * Created by Administrator on 2018/3/30.
 */
public enum ConstantEnum {
    FIRST("ok"),SECONED("error"),THIRD("yes");
    private String realName;
    ConstantEnum(String str) {
        this.realName = str;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
