package com.cqust.blog.common.entity;

import java.io.Serializable;

public class UserStaticKey implements Serializable{

    private static final long serialVersionUID = 2420530994998066724L;
    private Integer userId;

    private String time;

    public UserStaticKey(Integer userId, String time) {
        this.userId = userId;
        this.time = time;
    }

    public UserStaticKey() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}