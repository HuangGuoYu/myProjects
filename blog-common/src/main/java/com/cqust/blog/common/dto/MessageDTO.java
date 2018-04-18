package com.cqust.blog.common.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Administrator on 2018/4/18.
 */
@Getter
@Setter
public class MessageDTO {
    private String username;
    private String avatar = "/resource/imgs/headimg.jpg";
    private Integer id;//消息的来源ID（如果是私聊，则是用户id，如果是群聊，则是群组id）
    private String type;
    private String content;
    private Integer cid;
    private boolean mine;
    private Integer fromid;//消息的发送者id（比如群组中的某个消息发送者），可用于自动解决浏览器多窗口时的一些问题
    private Long timestamp;
}
