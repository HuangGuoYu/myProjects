package com.cqust.blog.manager.controller.service;

import com.cqust.blog.common.resp.GeneralResult;

/**
 * Created by Administrator on 2018/5/1.
 */
public interface StaticService {
    /**
     * 近一周用户注册分析
     * @return 数据集
     */
    GeneralResult findUserRegData();

    /**
     * 查找文章发布数据
     * @return 结果
     */
    GeneralResult findArticleData();

    /**
     * 文章审核数统计
     * @return 结果
     */
    GeneralResult findArticleCertData();

    /**
     * 文章审核失败统计
     * @return
     */
    GeneralResult articleDeafStatic();

    /**
     * 平台支出分析
     * @return
     */
    GeneralResult expensesData();

}
