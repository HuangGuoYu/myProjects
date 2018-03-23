package com.cqust.blog.service_api.web;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;

/**
 * Created by Administrator on 2018/3/23.
 */
public interface UserService {

    /**
     * 用户注册
     * @param user 用户实体信息
     * @return 处理结果
     */
    GeneralResult register(User user);
}
