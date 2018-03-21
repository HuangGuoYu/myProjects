package com.cqust.blog.common.entity;

import java.util.Date;

public class ArticleComment {
    private Integer id;

    private Integer articleId;

    private Integer cuserId;

    private Date commentTime;

    private String content;

    private Byte state;

    public ArticleComment(Integer id, Integer articleId, Integer cuserId, Date commentTime, String content, Byte state) {
        this.id = id;
        this.articleId = articleId;
        this.cuserId = cuserId;
        this.commentTime = commentTime;
        this.content = content;
        this.state = state;
    }

    public ArticleComment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getCuserId() {
        return cuserId;
    }

    public void setCuserId(Integer cuserId) {
        this.cuserId = cuserId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}