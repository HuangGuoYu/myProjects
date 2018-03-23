package com.cqust.blog.common.entity;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable{

    private static final long serialVersionUID = -5920246555245672297L;
    private Integer id;

    private Integer userId;

    private String title;

    private Date postTime;

    private Date certTime;

    private Integer browseNum;

    private Integer commentNum;

    private Integer likeNum;

    private Integer cateId;

    private Byte isOriginal;

    private Byte isDelete;

    private Byte state;

    private String content;

    public Article(Integer id, Integer userId, String title, Date postTime, Date certTime, Integer browseNum, Integer commentNum, Integer likeNum, Integer cateId, Byte isOriginal, Byte isDelete, Byte state, String content) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.postTime = postTime;
        this.certTime = certTime;
        this.browseNum = browseNum;
        this.commentNum = commentNum;
        this.likeNum = likeNum;
        this.cateId = cateId;
        this.isOriginal = isOriginal;
        this.isDelete = isDelete;
        this.state = state;
        this.content = content;
    }

    public Article() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Date getCertTime() {
        return certTime;
    }

    public void setCertTime(Date certTime) {
        this.certTime = certTime;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Byte getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(Byte isOriginal) {
        this.isOriginal = isOriginal;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}