package com.cqust.blog.web.controller;

import com.cqust.blog.common.entity.ArticleUserRel;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.service_api.web.CollectionArticleService;
import com.cqust.blog.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文章收藏控制器
 */
@Controller
@RequestMapping("/collect")
public class CollectArticleController extends BaseController {

    @Autowired private CollectionArticleService collectionArticleService;

    @RequestMapping("/add")
    @ResponseBody
    public GeneralResult add(Integer aid) {
        User sessionUser = getSessionUser();
        return collectionArticleService.addCollection(sessionUser, aid);
    }
}
