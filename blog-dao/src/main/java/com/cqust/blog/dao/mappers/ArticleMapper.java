package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    /**
     * 查询文章实体根据用户id和文章id
     * @param articleId 文章id
     * @param uId 用户ID
     * @return 文章实体
     */
    Article selectByUserIdAndArticleId(@Param("aid") Integer articleId, @Param("uid") Integer uId);
}