package com.cqust.blog.web.controller;

import com.cqust.blog.web.common.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/4/12.
 */
@Controller
@RequestMapping("/chat")
public class ChatController extends BaseController {

    @RequestMapping("/page")
    public String chatPage() {
        return "chat";
    }
}
