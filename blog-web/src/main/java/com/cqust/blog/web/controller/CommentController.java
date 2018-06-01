package com.cqust.blog.web.controller;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.service_api.web.ArticleService;
import com.cqust.blog.service_api.web.CommentService;
import com.cqust.blog.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/4/11.
 */
@RequestMapping("/comment")
@Controller
public class CommentController extends BaseController {


    @Autowired private CommentService commentService;

    @Autowired private ArticleService articleService;

    @RequestMapping("/add")
    @ResponseBody
    public GeneralResult addComment(String content, Integer aid) {
        GeneralResult result = new GeneralResult();
        User sessionUser = getSessionUser();
        if (articleService.isContainIllegalInfo(content)) {
            result.setMsg("不可包含非法内容");
            return result.ok(201, "不可包含非法内容");
        }
        return commentService.addComemnt(content, aid, sessionUser);
    }

    @RequestMapping("/queryDataByAid")
    @ResponseBody
    public GeneralResult queryDataByAid(String aid) {
        return commentService.queryDataByAid(aid);
    }
}
