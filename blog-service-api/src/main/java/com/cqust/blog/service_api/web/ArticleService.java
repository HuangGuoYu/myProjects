package com.cqust.blog.service_api.web;

import com.cqust.blog.common.dto.ArticleCategoryDTO;
import com.cqust.blog.common.dto.PageEntityDTO;
import com.cqust.blog.common.entity.Article;
import com.cqust.blog.common.entity.ArticleCategory;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;

import java.util.List;

/**
 * Created by Administrator on 2018/3/24.
 */
public interface ArticleService {
    /**
     * 添加文章分类
     * @param category 分类数据
     * @param userInfo 用户信息
     * @return 处理结果
     */
    GeneralResult<Boolean> addCategory(ArticleCategory category, User userInfo);

    /**
     * 编辑分类信息
     * @param category 分类信息
     * @param userInfo 用户信息
     * @return 处理结果
     */
    GeneralResult<?> editCategory(ArticleCategory category, User userInfo);

    /**
     * 删除分类
     * @param category 分类信息
     * @param userInfo 用户信息
     * @return 处理结果
     */
    GeneralResult<?> delCategory(ArticleCategory category, User userInfo);

    /**
     * 添加博客文章
     * @param article 文章实体
     * @param sessionUser 用户信息
     * @return 处理结果
     */
    GeneralResult<?> addArticle(Article article, User sessionUser);

    /**
     * 编辑文章
     * @param article 文章实体
     * @param sessionUser 操作用户
     * @return 处理结果
     */
    GeneralResult<?> editArticle(Article article, User sessionUser);

    /**
     * 根据id 查找实体
     * @param aid 文章id
     * @param uid 用户ID
     * @return 处理结果
     */
    GeneralResult findArticlecDataById(Integer aid, Integer uid);

    /**
     * 删除文章
     * @param aid 文章id
     * @param uid 用户id
     * @return 处理结果
     */
    GeneralResult delArticle(Integer aid, Integer uid);

    /**
     * 删除文章重数据库
     * @param aid 文章id
     * @param uid 用户id
     * @return 处理结果
     */
    GeneralResult delArticleFromDB(Integer aid, Integer uid);

    /**
     * 重回收站中恢复
     * @param aid 文章id
     * @param uid 用户id
     * @return 处理结果
     */
    GeneralResult renewArticle(Integer aid, Integer uid);

    /**
     *
     * @param sessionUser
     */
    List<ArticleCategory> queryCateByUserId(User sessionUser);

    /**
     * 获得所有分类
     * @param sessionUser 当前用户信息
     * @return 处理结果
     */
    GeneralResult<List<ArticleCategoryDTO>> queryAllCategoryByUser(User sessionUser);

    /**
     * 根据状态和当前页查询文章
     * @param state 状态 0=》审核中，状态未删除  1=》已发表，未删除 2=》回收站，已删除
     * @param curPage 当前页 默认每页10
     * @return 数据集
     */
    GeneralResult<PageEntityDTO<Article>> queryArticleByState(Integer state, Integer curPage);

    /**
     * 查询文章信息
     * @param id 文章ID
     * @return 处理结果
     */
    GeneralResult queryArticleData(Integer id);

    /**
     * 文章点赞
     * @param aid 文章id
     * @param sessionUser 操作用户
     * @return 处理结果
     */
    GeneralResult likeArticle(Integer aid, User sessionUser);

    /**
     * 执行是增加浏览数
     * @param sessionUser 当前用户
     * @param clientIpAddr 请求地址
     * @param aid 文章id
     */
    void execBrowseNum(User sessionUser, String clientIpAddr, Integer aid);

    /**
     * 删除文章从磁盘
     * @param sessionUser
     * @param id
     * @return
     */
    GeneralResult delArticleFromDisk(User sessionUser, Integer id);
}
