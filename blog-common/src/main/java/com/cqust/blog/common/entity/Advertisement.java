package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_ad")
@Getter
@Setter
public class Advertisement {
    @Column(name = "id", length = 11)
    private Integer id;

    @Column(name = "url", length = 200, nullable = false)
    private String url;

    @Column(name = "image_url", length = 200, nullable = false)
    private String imageUrl;

    @Column(name = "content", length = 30)
    private String content;

    @Column(name = "state", length = 4)
    private Byte state;

    @Column(name = "ord", length = 11, nullable = false)
    private Integer ord;

    public Advertisement(Integer id, String url, String imageUrl, String content, Byte state, Integer ord) {
        this.id = id;
        this.url = url;
        this.imageUrl = imageUrl;
        this.content = content;
        this.state = state;
        this.ord = ord;
    }

    public Advertisement() {
        super();
    }

}