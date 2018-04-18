package com.cqust.blog.service_api.web;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;

/**
 * Created by Administrator on 2018/4/18.
 */
public interface ChatService {
    /**
     * 发送消息建立好友关系
     * @param uid 用户id
     * @return 处理结果
     */
    GeneralResult addMessage(User sessionUser, Integer uid);

    /**
     * 查找好友列表
     * @param sessionUser 当前用户
     * @return 处理结果
     */
    GeneralResult findFriendList(User sessionUser);
}
