package com.cqust.blog.common.entity;

import java.io.Serializable;

public class UserDetail implements Serializable{


    private static final long serialVersionUID = 5468176343033268059L;
    private Integer userId;

    private String expertField;

    private String expertTech;

    private String education;

    private String phone;

    private String qq;

    private String wx;

    public UserDetail(Integer userId, String expertField, String expertTech, String education, String phone, String qq, String wx) {
        this.userId = userId;
        this.expertField = expertField;
        this.expertTech = expertTech;
        this.education = education;
        this.phone = phone;
        this.qq = qq;
        this.wx = wx;
    }

    public UserDetail() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getExpertField() {
        return expertField;
    }

    public void setExpertField(String expertField) {
        this.expertField = expertField == null ? null : expertField.trim();
    }

    public String getExpertTech() {
        return expertTech;
    }

    public void setExpertTech(String expertTech) {
        this.expertTech = expertTech == null ? null : expertTech.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx == null ? null : wx.trim();
    }
}