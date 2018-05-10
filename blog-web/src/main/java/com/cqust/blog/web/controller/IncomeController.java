package com.cqust.blog.web.controller;

import com.cqust.blog.common.entity.UserDetail;
import com.cqust.blog.common.entity.Withdraw;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.ServletUtils;
import com.cqust.blog.service.impl.web.UserServiceImpl;
import com.cqust.blog.service_api.web.UserService;
import com.cqust.blog.web.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/5/9.
 */
@RequestMapping("/income")
@Controller
public class IncomeController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String incomePage() {
        request.setAttribute("data", ServletUtils.getUserInfo(request));
        UserDetail parameter = userService.findUserDetailInfo(getSessionUser());
        request.setAttribute("uinfo", parameter);
        return "incomeManager";
    }

    /**
     * 用户收入数据
     * @return
     */
    @RequestMapping("/chart")
    @ResponseBody
    public GeneralResult incomeData() {
        return userService.findIncomeData(getSessionUser());
    }

    /**
     * 提现
     * @param withdraw
     * @return
     */
    @RequestMapping("/withdraw")
    @ResponseBody
    public GeneralResult withdraw(Withdraw withdraw) {
        return userService.execWithdraw(getSessionUser(), withdraw);
    }

    /**
     * 提现记录
     * @return
     */
    @RequestMapping("/withdrawRecord")
    @ResponseBody
    public GeneralResult withdrawRecord() {
        return userService.findWithdrawRecord(getSessionUser());
    }
}
