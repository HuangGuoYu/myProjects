package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.UserDetail;

public interface UserDetailMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserDetail record);

    int insertSelective(UserDetail record);

    UserDetail selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserDetail record);

    int updateByPrimaryKey(UserDetail record);
}