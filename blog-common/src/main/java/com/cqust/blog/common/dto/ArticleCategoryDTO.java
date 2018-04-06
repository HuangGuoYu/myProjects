package com.cqust.blog.common.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tbl_article_categoty")
@Getter
@Setter
public class ArticleCategoryDTO implements Serializable{

    private static final long serialVersionUID = -6308246825866190249L;
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "user_id", length = 11, nullable = false)
    private Integer userId;

    @Column(name = "ord", length = 11)
    private Integer ord;

    private Integer articleNum;

    public ArticleCategoryDTO(String name, Integer userId, Integer ord, Integer articleNum) {
        this.name = name;
        this.userId = userId;
        this.ord = ord;
        this.articleNum = articleNum;
    }

    public ArticleCategoryDTO() {
        super();
    }

}