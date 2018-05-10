package com.cqust.blog.manager.controller.service;

import com.cqust.blog.common.resp.GeneralResult;

/**
 * Created by Administrator on 2018/5/1.
 */
public interface UserManagerService {

    /**
     * 查找所有用户
     * @return 数据集
     */
    GeneralResult findAllUser();

    /**
     * 禁用用户
     * @param uid 用户id
     * @param note 原因
     * @return 处理结果
     */
    GeneralResult forbidUser(Integer uid, String note);

    /**
     * 解除冻结
     * @param uid 用户id
     * @return 处理结果
     */
    GeneralResult approvalUser(Integer uid);

    /**
     * 查找所有的评论数据
     * @return 处理结果
     */
    GeneralResult findAllComment();

    /**
     * 查找所有的聊天数据
     * @return 处理结果
     */
    GeneralResult findAllmsg();

    /**
     * 删除评论
     * @param id 评论记录id
     * @return
     */
    GeneralResult execDelComment(Integer id);

    /**
     * 删除消息
     * @param id 消息id
     * @return 处理结果
     */
    GeneralResult execDelMsg(Integer id);

    GeneralResult findUserIncomeList();

    /**
     * 切换用户提现状态
     * @param id
     * @param state
     * @return
     */
    GeneralResult switchWithdrawState(Integer id, Byte state);
}
