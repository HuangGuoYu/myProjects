package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user_static")
@Getter
@Setter
@IdClass(UserStaticKey.class)
public class UserStaticHibernate {

    private static final long serialVersionUID = 4011554722881008626L;

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @Column(name = "time")
    private String time;

    @Column(name = "post_num")
    private Integer postNum = 0;

    @Column(name = "original_num")
    private Integer originalNum = 0;

    @Column(name = "like_num")
    private Integer likeNum = 0;

    @Column(name = "browse_num")
    private Integer browseNum = 0;

    @Column(name = "income")
    private Long income = 0L;

}