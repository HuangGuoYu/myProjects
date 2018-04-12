package com.cqust.blog.service_api.web;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;

/**
 * Created by Administrator on 2018/4/12.
 */
public interface UserRelService {
    /**
     * 添加用户关系
     * @param sessionUser 当前用户
     * @param toUser 被关注用户
     * @return 处理结果
     */
    GeneralResult add(User sessionUser, Integer toUser);
}
