package com.cqust.blog.service.impl.web;

import com.cqust.blog.common.entity.Message;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.dao.mappers.MessageMapper;
import com.cqust.blog.dao.mappers.UserMapper;
import com.cqust.blog.service_api.web.ChatService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/18.
 */
@Service
public class ChatServiceImpl implements ChatService {

    /**消息处理器*/
    @Autowired private MessageMapper messageMapper;

    @Autowired private UserMapper userMapper;

    @Override
    public GeneralResult saveMessage(User sessionUser, Message message) {
        message.setSendTime(new Date());
        message.setType((byte) 1);
        if (message.getId() != null) {
            messageMapper.updateByPrimaryKeySelective(message);
        } else {
            messageMapper.insert(message);
        }
        return new GeneralResult().ok(200);
    }

    @Override
    public void execMarckMsgToRead(Message message) {
        message.setIsLook((byte) 1);
        messageMapper.updateByPrimaryKey(message);
    }

    @Override
    public GeneralResult findFriendList(User sessionUser) {
        GeneralResult result = new GeneralResult();
        Map<String, Object> resData = new HashMap<>();
        List<Map<String, Object>> datas = messageMapper.findFriendList(sessionUser.getId());
        Map<String, Object> mine = new HashMap<>();
        mine.put("username", sessionUser.getBlogName());
        mine.put("id", sessionUser.getId());
        mine.put("avatar", "/resource/imgs/headimg.jpg");
        mine.put("sign", "在深邃的编码世界，做一枚轻盈的纸飞机");
        mine.put("status", "online");
        resData.put("mine", mine);
        resData.put("friends", datas);
        result.setData(resData);
        result.setCode(0);
        return result;
    }

    @Override
    public GeneralResult addMessage(User sessionUser, Integer uid) {
       GeneralResult result = new GeneralResult();
        //检查接受用户是否存在
        User user = userMapper.selectByPrimaryKey(uid);
        if (user == null) {
            return result.error(404, "没找到该用户");
        }
        //检查是否已经存在聊天记录
        Message messageDb = messageMapper.findIsExists(sessionUser.getId(), uid);
        if (messageDb != null) {
            return result.ok(200);
        }
        Message message = new Message();
        message.setFromUser(sessionUser.getId());
        message.setToUser(uid);
        message.setSendTime(new Date());
        message.setContent("");
        message.setType((byte) 1);
        try {
            messageMapper.insert(message);
        } catch (Exception e) {
            e.printStackTrace();
            return result.error(500, "操作有误");
        }
        return result.ok(200);
    }
}
