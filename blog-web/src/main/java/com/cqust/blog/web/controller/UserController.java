package com.cqust.blog.web.controller;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.ServletUtils;
import com.cqust.blog.dao.mappers.ArticleCategoryMapper;
import com.cqust.blog.service_api.web.UserService;
import com.cqust.blog.web.common.BaseController;
import com.cqust.blog.common.dto.RegisterUserDTO;
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
public class UserController extends BaseController{

    @Autowired private UserService userService;


    @RequestMapping("/register")
    @ResponseBody
    public GeneralResult<? extends Serializable> register(RegisterUserDTO user) {
        user.setSessionId(request.getSession().getId());
        GeneralResult result = userService.register(user);
        return result;
    }

    @RequestMapping("/checkAcountIsExists")
    @ResponseBody
    public GeneralResult<Boolean> checkAcountIsExists(String account) {
        GeneralResult<Boolean> result = userService.checkAcountIsExists(account);
        return result;
    }

    @RequestMapping("/sendVerifyCode")
    @ResponseBody
    public GeneralResult<?> sendVerifyCode(String account) {
        String sessionId = request.getSession().getId();
      return  userService.sendVerifyCode(account, sessionId);
    }

    @RequestMapping("/login")
    @ResponseBody
    public GeneralResult<?> login(String account, String pwd) {
        GeneralResult<?> result = userService.login(account, pwd);
        if (result.getCode() == 200) {
            ServletUtils.setUserInfo(request, (User) result.getData());
        }
        return result;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public GeneralResult<?> logout() {
        GeneralResult<User> result = new GeneralResult<>();
        User user = ServletUtils.clearUserInfo(request);
        result.setData(user);
        return result;
    }


    @RequestMapping("/registerPage")
    public String registerPage() {
        return "register";
    }

    @RequestMapping("/loginPage")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/userIndex")
    public String index() {
        System.out.println(ServletUtils.getUserInfo(request));
        request.setAttribute("data", ServletUtils.getUserInfo(request));
        return "index";
    }


}
