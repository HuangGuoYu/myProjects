package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User checkAcountIsExists(String account);

    Map<String, Object> selectByPrimaryKeyForArticleDetail(@Param("uid") Integer userId);
}