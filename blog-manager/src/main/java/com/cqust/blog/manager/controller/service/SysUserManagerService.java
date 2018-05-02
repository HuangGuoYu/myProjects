package com.cqust.blog.manager.controller.service;

import com.cqust.blog.common.entity.SysUser;
import com.cqust.blog.common.resp.GeneralResult;

/**
 * Created by Administrator on 2018/5/1.
 */
public interface SysUserManagerService {
    /**
     * 查找所有的系统用户
     * @return 处理结果
     */
    GeneralResult findAllSysUser(SysUser user);

    /**
     * 修改用户的密码
     * @param id 用户的id
     * @param pwd 用户密码
     * @return 处理结果
     */
    GeneralResult editUserPwd(Integer id, String pwd);

    /**
     * 删除用户
     * @param sessionSysUser 当前用户
     * @return 处理结果
     */
    GeneralResult delUser(SysUser sessionSysUser, Integer id);

    /**
     * 添加用户
     * @param name 用户姓名
     * @param pwd 面
     * @return 处理结果
     */
    GeneralResult addUser(String name, String pwd);

    /**
     * 用户登录
     * @param account 账户
     * @param pwd 密码
     * @return 处理结果
     */
    GeneralResult login(Integer account, String pwd);
}
