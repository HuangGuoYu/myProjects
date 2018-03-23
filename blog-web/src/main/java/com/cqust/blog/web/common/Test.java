package com.cqust.blog.web.common;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.service.impl.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/3/22.
 */
@Controller
@RequestMapping("/hello")
public class Test {
    @Autowired private TestService service;

    @Autowired private JavaMailSender sender;

    @RequestMapping("/h")
    public String hello() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("838937070@qq.com");
        message.setSubject("测试");
        message.setText("hello world");
        message.setFrom("huangguoyu1106@163.com");
        sender.send(message);
        return "hello";
    }
}
