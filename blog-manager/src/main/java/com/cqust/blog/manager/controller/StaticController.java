package com.cqust.blog.manager.controller;

import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.manager.controller.common.BaseController;
import com.cqust.blog.manager.controller.service.StaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/5/1.
 */
@Controller
@RequestMapping("/static")
public class StaticController extends BaseController {


    @Autowired private StaticService staticService;

    /**
     * 用户注册统计
     * @return 视图
     */
    @RequestMapping("userRegStatic")
    public String userStatic() {
        GeneralResult result = staticService.findUserRegData();
        return "userRegisterStatic";
    }

    /**
     * 用户注册统计数据
     * @return 数据
     */
    @RequestMapping("userRegStaticData")
    @ResponseBody
    public GeneralResult userRegStaticData() {
        GeneralResult result = staticService.findUserRegData();
        return result;
    }

    /**
     * 文章发布数统计
     * @return
     */
    @RequestMapping("articlePostStatic")
    @ResponseBody
    public GeneralResult articlePostStatic() {
        return staticService.findArticleData();
    }

    /**
     * 文章审核通过统计
     * @return 数据
     */
    @RequestMapping("articleCertStatic")
    @ResponseBody
    public GeneralResult articleCertStatic() {
        return staticService.findArticleCertData();
    }

    /**
     * 文章审核失败统计
     * @return 数据
     */
    @RequestMapping("articleDeafStatic")
    @ResponseBody
    public GeneralResult articleDeafStatic() {
        return staticService.articleDeafStatic();
    }

    /**
     * 文章统计
     * @return
     */
    @RequestMapping("articleStatic")
    public String articleStatic() {
        return "articleStatic";
    }

    /**
     * 平台支出分析
     * @return 视图
     */
    @RequestMapping("/expensesStatic")
    public String expensesStatic() {
        return "expensesStatic";
    }

    /**
     * 平台支出分析
     * @return
     */
    @RequestMapping("expensesData")
    @ResponseBody
    public GeneralResult expensesData() {
        return staticService.expensesData();
    }
}
