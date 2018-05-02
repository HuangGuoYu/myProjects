package com.cqust.blog.service_api.web;

import com.cqust.blog.common.dto.RegisterUserDTO;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.entity.UserDetail;
import com.cqust.blog.common.resp.GeneralResult;

import java.util.Map;

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

    /**
     * 查看是否存在未读消息
     * @param sessionUser 用户
     * @return true 存在
     */
    GeneralResult findIsExistsUnReadMessage(User sessionUser);

    /**
     * 根据id 查找用户
     * @param fromUser 用户id
     * @return 用户信息
     */
    User findUserById(Integer fromUser);

    /**
     * 查找用户所关注的人
     * @param sessionUser 当前用户
     * @return 数据集
     */
    GeneralResult queryUserAttention(User sessionUser);

    /**
     * 查询用户收藏的文章
     * @param sessionUser 当前用户
     * @return 数据
     */
    GeneralResult queryUserLikeArticle(User sessionUser);

    /**
     * 获得用户的详细信息
     * @param sessionUser 当前用户
     * @return 结果
     */
    UserDetail findUserDetailInfo(User sessionUser);

    /**
     * 保存用户信息
     * @param detail 用户详细信息
     * @return 处理结果
     */
    GeneralResult saveUserDetail(UserDetail detail);

    /**
     * 获得用户详细信息
     * @param id id
     * @return 结果
     */
    UserDetail queryUserDetail(Integer id);
}
