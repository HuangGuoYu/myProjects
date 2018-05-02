package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "tbl_message")
@Getter
@Setter
public class Message implements Serializable{

    private static final long serialVersionUID = 7950224149912651538L;
    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "type")
    private Byte type;

    @Column(name = "from_user")
    private Integer fromUser;

    @Column(name = "to_user")
    private Integer toUser;

    @Column(name = "send_time")
    private Date sendTime;

    @Column(name = "from_ip")
    private String fromIp;

    @Column(name = "article_id")
    private Integer articleId;

    @Column(name = "content")
    private String content;

    @Column(name = "is_look")
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

}