package com.cqust.blog.common.entity;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{

    private static final long serialVersionUID = 7950224149912651538L;
    private Integer id;

    private Byte type;

    private Integer fromUser;

    private Integer toUser;

    private Date sendTime;

    private String fromIp;

    private Integer articleId;

    private String content;

    private Byte isLook;

    public Message(Integer id, Byte type, Integer fromUser, Integer toUser, Date sendTime, String fromIp, Integer articleId, String content, Byte isLook) {
        this.id = id;
        this.type = type;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.sendTime = sendTime;
        this.fromIp = fromIp;
        this.articleId = articleId;
        this.content = content;
        this.isLook = isLook;
    }

    public Message() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getFromUser() {
        return fromUser;
    }

    public void setFromUser(Integer fromUser) {
        this.fromUser = fromUser;
    }

    public Integer getToUser() {
        return toUser;
    }

    public void setToUser(Integer toUser) {
        this.toUser = toUser;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getFromIp() {
        return fromIp;
    }

    public void setFromIp(String fromIp) {
        this.fromIp = fromIp == null ? null : fromIp.trim();
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getIsLook() {
        return isLook;
    }

    public void setIsLook(Byte isLook) {
        this.isLook = isLook;
    }
}