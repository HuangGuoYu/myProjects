package com.cqust.blog.common.entity;

public class Advertisement {
    private Integer id;

    private String url;

    private String imageUrl;

    private String content;

    private Byte state;

    public Advertisement(Integer id, String url, String imageUrl, String content, Byte state) {
        this.id = id;
        this.url = url;
        this.imageUrl = imageUrl;
        this.content = content;
        this.state = state;
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

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}