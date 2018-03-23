package com.cqust.blog.web.controller;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.service_api.web.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/23.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired private UserService userService;

    @RequestMapping("/register")
    @ResponseBody
    public GeneralResult<? extends Serializable> register(User user) {
        GeneralResult result = userService.register(user);
        return result;
    }
}
