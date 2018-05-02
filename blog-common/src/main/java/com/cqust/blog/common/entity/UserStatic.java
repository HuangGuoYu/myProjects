package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
//@Entity
//@Table(name = "tbl_user_static")
@Getter
@Setter
public class UserStatic extends UserStaticKey {

    private static final long serialVersionUID = 4011554722881008626L;

    @Column(name = "post_num")
    private Integer postNum;

    @Column(name = "original_num")
    private Integer originalNum;

    @Column(name = "like_num")
    private Integer likeNum;

    @Column(name = "browse_num")
    private Integer browseNum;

    @Column(name = "income")
    private Long income;

    public UserStatic(Integer userId, String time, Integer postNum, Integer originalNum, Integer likeNum, Integer browseNum, Long income) {
        super(userId, time);
        this.postNum = postNum;
        this.originalNum = originalNum;
        this.likeNum = likeNum;
        this.browseNum = browseNum;
        this.income = income;
    }

    public UserStatic() {
        super();
    }

    public Integer getPostNum() {
        return postNum;
    }

    public void setPostNum(Integer postNum) {
        this.postNum = postNum;
    }

    public Integer getOriginalNum() {
        return originalNum;
    }

    public void setOriginalNum(Integer originalNum) {
        this.originalNum = originalNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }
}