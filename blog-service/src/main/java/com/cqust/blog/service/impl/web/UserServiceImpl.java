package com.cqust.blog.service.impl.web;

import com.cqust.blog.common.common.RegexConstant;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.common.utils.DataUtils;
import com.cqust.blog.dao.mappers.UserMapper;
import com.cqust.blog.service_api.web.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired private UserMapper userDao;

    @Override
    public GeneralResult register(User user) {
        GeneralResult<Boolean> result = null;
        try {
            result = DataUtils.checkFieldByAnnotaion(user);
            //如果检测失败直接返回
            if (result.getData()) {
                return result;
            }
            if (user.getPwd().length() < 6) {
                result.setCode(201);
                result.setMsg("密码不可少于6位");
                return result;
            }
            if (!user.getAccount().matches(RegexConstant.EMAIL)) {
                result.setCode(201);
                result.setMsg("邮箱格式错误");
                return result;
            }
            userDao.insertSelective(user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        result.setCode(200);
        result.setMsg("操作成功");
        return result;
    }
}
