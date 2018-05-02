package com.cqust.blog.manager.controller;

import com.cqust.blog.common.entity.SysUser;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.ServletUtils;
import com.cqust.blog.manager.controller.common.BaseController;
import com.cqust.blog.manager.controller.service.SysUserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/4/25.
 */
@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController {

    @Autowired
    SysUserManagerService sysUserManagerService;

    /**
     * 用户登录
     * @return 登录页
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 用户登录逻辑处理
     * @param account 账户
     * @param pwd 密码
     * @return 处理结果
     */
    @RequestMapping("/loginDeal")
    @ResponseBody
    public GeneralResult loginDeal(String account, String pwd) {
        if (!account.matches("[0-9]+")) {
            GeneralResult res = new GeneralResult();
            return res.error(401, "请输入正确格式的用户名");
        }
        GeneralResult result = sysUserManagerService.login(Integer.valueOf(account), pwd);
        if (result.getCode() == 200) {
            ServletUtils.setSysUser(request, (SysUser) result.getData());
        }
        return result;
    }

    /**
     * 首页
     * @return 首页视图
     */
    @RequestMapping("/index")
    public String index() {
        SysUser sysUser = ServletUtils.getSysUser(request);
        if (sysUser == null) {
            return "login";
        }
        request.setAttribute("isAdmin", 0);
        if (sysUser.getName().equals("admin")) {
            request.setAttribute("isAdmin", 1);
        }
        request.setAttribute("user", sysUser);
        return "index";
    }

    @RequestMapping("/logout")
    public String logout() {
        ServletUtils.clearSysUserInfo(request);
        return "login";
    }
}
