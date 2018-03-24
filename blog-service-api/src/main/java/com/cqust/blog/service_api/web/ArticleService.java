package com.cqust.blog.service_api.web;

import com.cqust.blog.common.entity.ArticleCategory;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;

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
}
