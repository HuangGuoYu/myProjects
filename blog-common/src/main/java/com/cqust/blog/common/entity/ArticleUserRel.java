package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "tbl_article_rel")
@Getter
@Setter
public class ArticleUserRel implements Serializable{

    private static final long serialVersionUID = 7381401945879745539L;
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "article_id")
    private Integer articleId;

    public ArticleUserRel(Integer id, Integer userId, Integer articleId) {
        this.id = id;
        this.userId = userId;
        this.articleId = articleId;
    }

    public ArticleUserRel() {
        super();
    }

}