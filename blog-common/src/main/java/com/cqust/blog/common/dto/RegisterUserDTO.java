package com.cqust.blog.common.dto;

import com.cqust.blog.common.common.TestFile;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/24.
 */
@Getter
@Setter
public class RegisterUserDTO {

    @Column(name = "id", length = 11, nullable = true)
    private Integer id;

    @Column(name = "account", length = 50, nullable = false)
    private String account;

    @Column(name = "pwd", length = 35, nullable = false)
    private String pwd;

    @Column(name = "blog_name", length = 50, nullable = false)
    private String blogName;

    @Column(name = "email", length = 50, nullable = true)
    private String email;

    @Column(name = "cate_career", length = 30, nullable = true)
    private String cateCareer;

    @Column(name = "user_name", length = 30, nullable = true)
    private String userName;

    @Column(name = "pid", length = 4, nullable = true)
    private Byte pid;

    @Column(name = "gender", length = 4, nullable = true)
    private Byte gender;

    @Column(name = "age", length = 4, nullable = true)
    private Byte age;

    @Column(name = "reg_time", nullable = true)
    private Date regTime;

    @Column(name = "fans_num", nullable = true)
    private Integer fansNum;

    @Column(name = "attention_num", nullable = true)
    private Integer attentionNum;

    @Column(name = "state", nullable = true)
    private Byte state;

    @Column(name = "banlance", nullable = true)
    private BigDecimal banlance;

    @Column(name = "verifyCode", length = 6, nullable = false)
    private String verifyCode;

    @Column(name = "ackPwd", length = 50, nullable = false)
    private String ackPwd;

    @Column(name = "sessionId", nullable = true )
    private String sessionId;
}
