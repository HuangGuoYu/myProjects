package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.UserStatic;
import com.cqust.blog.common.entity.UserStaticKey;

public interface UserStaticMapper {
    int deleteByPrimaryKey(UserStaticKey key);

    int insert(UserStatic record);

    int insertSelective(UserStatic record);

    UserStatic selectByPrimaryKey(UserStaticKey key);

    int updateByPrimaryKeySelective(UserStatic record);

    int updateByPrimaryKey(UserStatic record);
}