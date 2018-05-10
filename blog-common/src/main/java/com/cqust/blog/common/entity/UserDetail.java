package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tbl_user_detail")
public class UserDetail {
    @Id
    @Column(name = "user_id", length = 11, nullable = true)
    private Integer userId;

    @Column(name = "expert_field", length = 30)
    private String expertField;

    @Column(name = "expert_tech", length = 60)
    private String expertTech;

    @Column(name = "education", length = 60)
    private String education;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "qq", length = 20)
    private String qq;

    @Column(name = "wx", length = 30)
    private String wx;

    @Column(name = "head_icon", length = 160)
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