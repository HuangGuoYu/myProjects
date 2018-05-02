package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "tbl_article_comment")
@Getter
@Setter
public class ArticleComment implements Serializable{

    private static final long serialVersionUID = -7179890961289419062L;
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "article_id", length = 11, nullable = false)
    private Integer articleId;

    @Column(name = "cuser_id", length = 11)
    private Integer cuserId;

    @Column(name = "comment_time")
    private Date commentTime;

    @Column(name = "content", length = 200, nullable = false)
    private String content;

    @Column(name = "state", length = 4)
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

}