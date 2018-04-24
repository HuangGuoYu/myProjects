package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.dto.ArticleCategoryDTO;
import com.cqust.blog.common.entity.ArticleCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleCategory record);

    int insertSelective(ArticleCategory record);

    ArticleCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleCategory record);

    int updateByPrimaryKey(ArticleCategory record);

    ArticleCategory checkIsExists(@Param("name") String name,@Param("uid") Integer uid);

    ArticleCategory editCheckIsExists(@Param("cid")Integer categoryId, @Param("uid") Integer userInfoId);

    List<ArticleCategory> queryCateByUserId(@Param("uid") Integer id);

    List<ArticleCategoryDTO> queryCateByUserIdFroDTO(@Param("uid") Integer uid);
}