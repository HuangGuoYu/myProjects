package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetail {
    private Integer userId;

    private String expertField;

    private String expertTech;

    private String education;

    private String phone;

    private String qq;

    private String wx;

    private String headIcon;

    public UserDetail(Integer userId, String expertField, String expertTech, String education, String phone, String qq, String wx, String headIcon) {
        this.userId = userId;
        this.expertField = expertField;
        this.expertTech = expertTech;
        this.education = education;
        this.phone = phone;
        this.qq = qq;
        this.wx = wx;
        this.headIcon = headIcon;
    }

    public UserDetail() {
        super();
    }

}