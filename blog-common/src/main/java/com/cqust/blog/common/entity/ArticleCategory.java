package com.cqust.blog.common.entity;

public class ArticleCategory {
    private Integer id;

    private String name;

    private Integer userId;

    private Integer ord;

    public ArticleCategory(Integer id, String name, Integer userId, Integer ord) {
        this.id = id;
        this.name = name;
        this.userId = userId;
        this.ord = ord;
    }

    public ArticleCategory() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }
}