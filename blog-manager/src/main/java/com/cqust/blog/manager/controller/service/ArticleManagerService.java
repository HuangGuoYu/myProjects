package com.cqust.blog.manager.controller.service;

import com.cqust.blog.common.entity.SysUser;
import com.cqust.blog.common.resp.GeneralResult;

import javax.persistence.GeneratedValue;

/**
 * Created by Administrator on 2018/4/29.
 */
public interface ArticleManagerService {
    GeneralResult findAllArticle();

    /**
     * 通过文章审核
     * @param aid 文章id
     * @param uid 用户id
     * @return 数据集
     */
    GeneralResult execApproval(Integer aid, Integer uid);

    /**
     * 不通过审核
     * @param aid 文章id
     * @param sessionSysUser 操作用户
     * @return 处理结果
     */
    GeneralResult<?> forbid(Integer aid, SysUser sessionSysUser, String note);
}
