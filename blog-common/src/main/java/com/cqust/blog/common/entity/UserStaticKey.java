package com.cqust.blog.common.entity;

public class UserStaticKey {
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