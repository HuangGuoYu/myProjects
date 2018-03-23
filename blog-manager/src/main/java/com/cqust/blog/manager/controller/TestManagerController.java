package com.cqust.blog.manager.controller;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.dao.manager.TestHibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/3/22.
 */
@Controller
@RequestMapping("/hibernate")
public class TestManagerController {

    @Autowired
    TestHibernate dao;

    @RequestMapping("/h")
    @ResponseBody
    public User getUser() {
        return dao.getUser();
    }

}
