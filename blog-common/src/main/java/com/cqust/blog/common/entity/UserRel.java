package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user_rel")
@Getter
@Setter
public class UserRel {
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "to_user_id")
    private Integer toUserId;

    public UserRel(Integer id, Integer userId, Integer toUserId) {
        this.id = id;
        this.userId = userId;
        this.toUserId = toUserId;
    }

    public UserRel() {
        super();
    }
}