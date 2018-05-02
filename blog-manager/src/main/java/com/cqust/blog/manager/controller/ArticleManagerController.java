package com.cqust.blog.manager.controller;

import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.manager.controller.common.BaseController;
import com.cqust.blog.manager.controller.service.ArticleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.GeneratedValue;

/**
 * Created by Administrator on 2018/4/29.
 */
@Controller
@RequestMapping("/article")
public class ArticleManagerController extends BaseController {

    @Autowired private ArticleManagerService managerService;

    /**
     * 文章列表数据
     * @return 结果集
     */
    @RequestMapping("/list")
    @ResponseBody
    public GeneralResult articleList() {
        GeneralResult res = managerService.findAllArticle();
        return res;
    }

    /**
     * 文章管理页面
     * @return 处理结果
     */
    @RequestMapping("/articleManager")
    public String articleManager() {
        return "articleAuth";
    }


    /**
     * 通过审核
     * @param aid 文章id
     * @param uid 用户id
     * @return 处理结果
     */
    @RequestMapping("/approval")
    @ResponseBody
    public GeneralResult approval(Integer aid, Integer uid) {
        return managerService.execApproval(aid, uid);
    }

    /**
     * 审核失败
     * @param aid 文章id
     * @param note 说明
     * @return 处理结果
     */
    @RequestMapping("/forbid")
    @ResponseBody
    public GeneralResult<?> forbid(Integer aid, String note) {
        return managerService.forbid(aid, getSessionSysUser(), note);
    }
}
