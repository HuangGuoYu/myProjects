package com.cqust.blog.common.entity;

import java.io.Serializable;

public class ArticleUserRel implements Serializable{

    private static final long serialVersionUID = 7381401945879745539L;
    private Integer id;

    private Integer userId;

    private Integer articleId;

    public ArticleUserRel(Integer id, Integer userId, Integer articleId) {
        this.id = id;
        this.userId = userId;
        this.articleId = articleId;
    }

    public ArticleUserRel() {
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

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}