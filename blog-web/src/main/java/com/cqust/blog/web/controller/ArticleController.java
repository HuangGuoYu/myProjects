package com.cqust.blog.web.controller;

import com.cqust.blog.common.entity.ArticleCategory;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.ServletUtils;
import com.cqust.blog.service_api.web.ArticleService;
import com.cqust.blog.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/3/24.
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired private ArticleService articleService;

    @RequestMapping("/addCategory")
    @ResponseBody
    public GeneralResult<Boolean> addCategory(ArticleCategory category) {
        User userInfo = ServletUtils.getUserInfo(request);
        return articleService.addCategory(category, userInfo);
    }

    @RequestMapping("/editCategory")
    @ResponseBody
    public GeneralResult<?> editCategory(ArticleCategory category) {
        User userInfo = ServletUtils.getUserInfo(request);
        return articleService.editCategory(category, userInfo);
    }

    @RequestMapping("/delCategory")
    @ResponseBody
    public GeneralResult<?> delCategory(ArticleCategory category) {
        User userInfo = ServletUtils.getUserInfo(request);
        return articleService.delCategory(category, userInfo);
    }
}
