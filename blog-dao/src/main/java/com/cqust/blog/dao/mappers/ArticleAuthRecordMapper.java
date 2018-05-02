package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.ArticleAuthRecord;

public interface ArticleAuthRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleAuthRecord record);

    int insertSelective(ArticleAuthRecord record);

    ArticleAuthRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleAuthRecord record);

    int updateByPrimaryKey(ArticleAuthRecord record);
}