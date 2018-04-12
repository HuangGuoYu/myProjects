package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRel {
    private Integer id;

    private Integer userId;

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