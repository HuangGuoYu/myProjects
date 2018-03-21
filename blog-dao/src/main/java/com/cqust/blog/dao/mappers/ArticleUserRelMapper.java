package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.ArticleUserRel;

public interface ArticleUserRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleUserRel record);

    int insertSelective(ArticleUserRel record);

    ArticleUserRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleUserRel record);

    int updateByPrimaryKey(ArticleUserRel record);
}