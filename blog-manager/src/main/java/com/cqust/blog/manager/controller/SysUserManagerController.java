package com.cqust.blog.manager.controller;

import com.cqust.blog.common.entity.SysUser;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.manager.controller.common.BaseController;
import com.cqust.blog.manager.controller.service.SysUserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/5/1.
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserManagerController extends BaseController {

    @Autowired private SysUserManagerService sysUserManagerService;

    /**
     * 所有用户数据
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public GeneralResult list() {
        SysUser sessionSysUser = getSessionSysUser();
        return sysUserManagerService.findAllSysUser(sessionSysUser);
    }

    /**
     * 管理页面
     * @return 视图
     */
    @RequestMapping("/listPage")
    public String sysUserManager() {
        return "sysUserManager";
    }

    /**
     * 修改用户的密码
     * @param id 用户id
     * @param pwd 新密码
     * @return 处理结果
     */
    @RequestMapping("/editPwd")
    @ResponseBody
    public GeneralResult editPwd(Integer id, String pwd) {
        return sysUserManagerService.editUserPwd(id, pwd);
    }

    /**
     * 删除用户
     * @param id 用户id
     * @return 数据集
     */
    @RequestMapping("delUser")
    @ResponseBody
    public GeneralResult delUser(Integer id) {
        SysUser sessionSysUser = getSessionSysUser();
        return sysUserManagerService.delUser(sessionSysUser, id);
    }

    @RequestMapping("addUser")
    @ResponseBody
    public GeneralResult addUser(String name, String pwd) {
        return sysUserManagerService.addUser(name, pwd);
    }
}
