package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tbl_article")
public class Article implements Serializable{

    private static final long serialVersionUID = -5920246555245672297L;
    @Id
    @Column(name = "id", length = 11, nullable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id", length = 11, nullable = false)
    private Integer userId;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Column(name = "post_time")
    private Date postTime;

    @Column(name = "cert_time")
    private Date certTime;

    @Column(name = "browse_num")
    private Integer browseNum;

    @Column(name = "comment_num")
    private Integer commentNum;

    @Column(name = "like_num")
    private Integer likeNum;

    @Column(name = "cate_id", nullable = false, length = 11)
    private Integer cateId;
    /**
     * 是否原创 0 不是  1 是的
     */
    @Column(name = "is_original", nullable = false, length = 4)
    private Byte isOriginal;

    /**
     * 是否删除 0 未删除  1 删除
     */
    @Column(name = "is_delete")
    private Byte isDelete;

    /**
     *  审核状态0 审核中 1 审核通过 2 未通过
     */
    @Column(name = "state")
    private Byte state;

    /**
     * 博客内容
     */
    @Column(name = "content", nullable = false, length = 5000)
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


}