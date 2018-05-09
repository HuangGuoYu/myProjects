package com.cqust.blog.dao.mappers;

import com.cqust.blog.common.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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

    /**
     * 查询用户关注的人
     * @param uid
     * @return
     */
    List<Map<String,Object>> queryUserAttention(@Param("uid") Integer uid);

    /**
     * 查询用户收藏的文章
     * @param uid 当前用户
     * @return 数据
     */
    List<Map<String,Object>> queryUserLikeArticle(@Param("uid") Integer uid);

    List<Map<String,Object>> findIncomeRank();

    List<Map<String,Object>> findPersonalCate(@Param("id") Integer id);

    User findByAccount(@Param("account") String account);

    /**
     * 查找用户收入数据
     * @param id 用户id
     * @return
     */
    List<Map<String,Object>> findIncomeData(Integer id);
}