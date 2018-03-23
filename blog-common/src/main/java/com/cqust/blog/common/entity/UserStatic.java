package com.cqust.blog.common.entity;

import java.io.Serializable;

public class UserStatic extends UserStaticKey {

    private static final long serialVersionUID = 4011554722881008626L;
    private Integer postNum;

    private Integer originalNum;

    private Integer likeNum;

    private Integer browseNum;

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