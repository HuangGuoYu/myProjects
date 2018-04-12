package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.ArticleUserRel;
import org.apache.ibatis.annotations.Param;

public interface ArticleUserRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleUserRel record);

    int insertSelective(ArticleUserRel record);

    ArticleUserRel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleUserRel record);

    int updateByPrimaryKey(ArticleUserRel record);

    ArticleUserRel queryByUidAndAid(@Param("uid") Integer uid,@Param("aid") Integer aid);
}