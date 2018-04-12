package com.cqust.blog.web.controller;

import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.service_api.web.UserRelService;
import com.cqust.blog.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/4/12.
 */
@Controller
@RequestMapping("/userRel")
public class UserRelController extends BaseController {

    @Autowired
    UserRelService userRelService;

    /**
     * 关注用户
     * @param toUser 被关注用户id
     * @return 处理结果
     */
    @RequestMapping("/add")
    @ResponseBody
    public GeneralResult add(Integer toUser) {
        return userRelService.add(getSessionUser(), toUser);
    }

}
