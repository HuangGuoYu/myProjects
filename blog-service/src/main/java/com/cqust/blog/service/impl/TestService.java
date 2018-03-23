package com.cqust.blog.service.impl;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.dao.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/3/22.
 */
@Service
public class TestService {
    @Autowired private UserMapper userMapperDao;

    public User getUser() {
        return userMapperDao.selectByPrimaryKey(1);
    }
}
