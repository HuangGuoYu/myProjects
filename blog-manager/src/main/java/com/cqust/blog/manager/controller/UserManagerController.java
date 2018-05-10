package com.cqust.blog.manager.controller;

import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.manager.controller.common.BaseController;
import com.cqust.blog.manager.controller.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/5/1.
 */
@Controller
@RequestMapping("/userManager")
public class UserManagerController extends BaseController {

    @Autowired private UserManagerService userManagerService;


    /**
     * 用户管理列表页面
     * @return 数据集
     */
    @RequestMapping("/list")
    public String userManager() {
        return "userManager";
    }

    /**
     * 用户列表数据
     * @return 数据集
     */
    @RequestMapping("/listData")
    @ResponseBody
    public GeneralResult listData() {
        return userManagerService.findAllUser();
    }

    /**
     * 禁用账户
     * @param uid 用户id
     * @return 数据集
     */
    @RequestMapping("/forbid")
    @ResponseBody
    public GeneralResult forbidUser(Integer uid, String note) {
        return userManagerService.forbidUser(uid, note);
    }

    /**
     * 用户账户管理--通过审核
     * @param uid
     * @return
     */
    @RequestMapping("/approval")
    @ResponseBody
    public GeneralResult approvalUser(Integer uid) {
        return userManagerService.approvalUser(uid);
    }


    /**
     * 用户评论管理
     * @return 视图
     */
    @RequestMapping("/commentManager")
    public String commentManager() {
        return "commentManager";
    }

    /**
     * 用户评论数据
     * @return
     */
    @RequestMapping("/commentList")
    @ResponseBody
    public GeneralResult commentList() {
        return userManagerService.findAllComment();
    }


    /**
     * 用户评论管理
     * @return 视图
     */
    @RequestMapping("/msgManager")
    public String msgManager() {
        return "msgManager";
    }

    /**
     * 用户评论数据
     * @return
     */
    @RequestMapping("/msgList")
    @ResponseBody
    public GeneralResult msgList() {
        return userManagerService.findAllmsg();
    }

    /**
     * 删除评论
     * @param id 评论id
     * @return
     */
    @RequestMapping("/delComment")
    @ResponseBody
    public  GeneralResult delComment(Integer id) {
        return userManagerService.execDelComment(id);
    }

    /**
     * 删除消息
     * @param id 消息id
     * @return
     */
    @RequestMapping("/delMsg")
    @ResponseBody
    public  GeneralResult delMsg(Integer id) {
        return userManagerService.execDelMsg(id);
    }

    /**
     * 用户收入列表
     * @return
     */
    @RequestMapping("/userIncomeList")
    @ResponseBody
    public GeneralResult userIncomeList() {
        return userManagerService.findUserIncomeList();
    }

    /**
     * 用户收入管理页面
     * @return
     */
    @RequestMapping("/userIncomePage")
    public String userIncomePage() {
        return "userWithdrawManager";
    }

    @RequestMapping("/switchWithdrawState")
    @ResponseBody
    public GeneralResult switchWithdrawState(Integer id, Byte state) {
        return userManagerService.switchWithdrawState(id, state);
    }
}
