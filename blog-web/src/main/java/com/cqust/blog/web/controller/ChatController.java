package com.cqust.blog.web.controller;

import com.cqust.blog.common.dto.MessageDTO;
import com.cqust.blog.common.entity.Message;
import com.cqust.blog.common.entity.User;
import com.cqust.blog.common.resp.GeneralResult;
import com.cqust.blog.service_api.web.ChatService;
import com.cqust.blog.web.common.BaseController;
import com.cqust.blog.web.websocket.MessageHandler;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/12.
 */
@Controller
@RequestMapping("/chat")
public class ChatController extends BaseController {

    /**聊天服务*/
    @Autowired private ChatService chatService;

    @Autowired private MessageHandler messageHandler;

    /**
     * 聊天页面
     * @return 视图页面
     */
    @RequestMapping("/page")
    public String chatPage(Integer id) {
        User sessionUser = getSessionUser();
        //添加一条聊天记录，以此留存好友关系
        GeneralResult result = chatService.addMessage(sessionUser, id);
        if (result.getCode() != 200) {
            return "_404";
        }
        return "chat";
    }

    /**
     * 查找好友列表
     * @return 处理结果
     */
    @RequestMapping("/friendList")
    @ResponseBody
    public GeneralResult friendList() {
        User sessionUser = getSessionUser();
        GeneralResult result = chatService.findFriendList(sessionUser);
        //判断好友的在线状态
        Map<String, Object> data = (Map<String, Object>) result.getData();
        List<Map<String, Object>> friends = (List<Map<String, Object>>) data.get("friends");
        for (Map<String, Object> item : friends) {
            Integer id = (Integer) item.get("id");
            item.put("sign", "这些都是测试数据，实际使用请严格按照该格式返回");
            if (MessageHandler.users.get(id) != null) {
                item.put("status", "online");
            } else {
                item.put("status", "offline");
            }
        }
        List<Map<String, Object>> group = new ArrayList<>();
        data.put("group", group);
        //分组以及好友列表
        List<Map<String, Object>> groupAndFriends = new ArrayList<>();
        Map<String, Object> g1 = new HashMap<>();
        g1.put("groupname", "我的好友");
        g1.put("id","1");
        g1.put("list", friends);
        groupAndFriends.add(g1);
        data.put("friend", groupAndFriends);
        return result;
    }

    @RequestMapping("/sendMessage")
    @ResponseBody
    public GeneralResult sendMessage(Message message) {
        GeneralResult result = new GeneralResult();
        User sessionUser = getSessionUser();
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setContent(message.getContent());
        messageDTO.setFromid(sessionUser.getId());
        messageDTO.setId(sessionUser.getId());
        messageDTO.setTimestamp(System.currentTimeMillis());
        messageDTO.setMine(false);
        messageDTO.setUsername(sessionUser.getBlogName());
        messageDTO.setType("friend");
        String messageEntity = new Gson().toJson(messageDTO);
        TextMessage textMessage = new TextMessage(messageEntity);
        messageHandler.sendMessageToUser(message.getToUser(), textMessage);
        return result.ok(0);
    }


}
