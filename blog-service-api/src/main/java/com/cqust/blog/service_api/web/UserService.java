package com.cqust.blog.service_api.web;

import com.cqust.blog.common.dto.RegisterUserDTO;
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
    GeneralResult register(RegisterUserDTO user);

    /**
     * 检测用户是否存在
     * @param account 用户账号
     * @return 处理结果
     */
    GeneralResult<Boolean> checkAcountIsExists(String account);

    /**
     * 发送注册验证码
     * @param account 账户
     * @param sessionId sessionId
     * @return 结果
     */
    GeneralResult<?> sendVerifyCode(String account, String sessionId);

    /**
     * 登录
     * @param account 账号
     * @param pwd 密码
     * @return 处理结果
     */
    GeneralResult<?> login(String account, String pwd);
}
