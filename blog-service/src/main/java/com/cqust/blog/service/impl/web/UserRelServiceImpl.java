package com.cqust.blog.service.impl.web;

import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.entity.UserRel;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.dao.mappers.UserMapper;
import com.cqust.blog.dao.mappers.UserRelMapper;
import com.cqust.blog.service_api.web.UserRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/4/12.
 */
@Service
public class UserRelServiceImpl implements UserRelService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRelMapper userRelMapper;



    @Override
    public GeneralResult add(User sessionUser, Integer toUser) {
        GeneralResult result = new GeneralResult();
        //检车当前用户是否存在
        User toUserDb = userMapper.selectByPrimaryKey(toUser);
        if (toUserDb == null || toUserDb.getState() != 2) {
            return result.error(404, "被关注用户不存在");
        }
        //检车是否已经存在关注关系
        UserRel userRel = userRelMapper.queryByUidToUid(sessionUser.getId(), toUser);
        if (userRel != null) {
            return result.error(401, "你已经关注该用户");
        }
        //添加悲观用用户的关注数
        toUserDb.setAttentionNum(toUserDb.getAttentionNum() == null ? 1 : (toUserDb.getAttentionNum() + 1));
        userMapper.updateByPrimaryKey(toUserDb);
        //添加关联关系
        UserRel rel = new UserRel();
        rel.setToUserId(toUser);
        rel.setUserId(sessionUser.getId());
        userRelMapper.insert(rel);
        result.setMsg("关注成功，到个人中心查看");
        return result.ok("");
    }
}
