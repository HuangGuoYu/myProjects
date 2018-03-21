package com.cqust.blog.common.entity;

public class Advertisement {
    private Integer id;

    private String url;

    private String imageUrl;

    private String content;

    public Advertisement(Integer id, String url, String imageUrl, String content) {
        this.id = id;
        this.url = url;
        this.imageUrl = imageUrl;
        this.content = content;
    }

    public Advertisement() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}