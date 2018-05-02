package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tbl_article_auth_record")
public class ArticleAuthRecord {
    @Id
    @Column(name = "id", length = 11, nullable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "auth_time")
    private Date authTime;

    @Column(name = "auth_user")
    private Integer authUser;

    @Column(name = "note")
    private String note;

    public ArticleAuthRecord(Integer id, Integer articleId, Date authTime, Integer authUser, String note) {
        this.id = id;
        this.articleId = articleId;
        this.authTime = authTime;
        this.authUser = authUser;
        this.note = note;
    }

    public ArticleAuthRecord() {
        super();
    }

}