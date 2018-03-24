package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.ArticleCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleCategory record);

    int insertSelective(ArticleCategory record);

    ArticleCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleCategory record);

    int updateByPrimaryKey(ArticleCategory record);

    ArticleCategory checkIsExists(String name);

    ArticleCategory editCheckIsExists(@Param("cid")Integer categoryId, @Param("uid") Integer userInfoId);
}