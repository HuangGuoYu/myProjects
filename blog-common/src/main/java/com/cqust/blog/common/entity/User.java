package com.cqust.blog.common.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @Column(name = "id", length = 11, nullable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public User(Integer id, String account, String pwd, String blogName, String email, String cateCareer, String userName, Byte pid, Byte gender, Byte age, Date regTime, Integer fansNum, Integer attentionNum, Byte state, BigDecimal banlance) {
        this.id = id;
        this.account = account;
        this.pwd = pwd;
        this.blogName = blogName;
        this.email = email;
        this.cateCareer = cateCareer;
        this.userName = userName;
        this.pid = pid;
        this.gender = gender;
        this.age = age;
        this.regTime = regTime;
        this.fansNum = fansNum;
        this.attentionNum = attentionNum;
        this.state = state;
        this.banlance = banlance;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName == null ? null : blogName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCateCareer() {
        return cateCareer;
    }

    public void setCateCareer(String cateCareer) {
        this.cateCareer = cateCareer == null ? null : cateCareer.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Byte getPid() {
        return pid;
    }

    public void setPid(Byte pid) {
        this.pid = pid;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public Integer getAttentionNum() {
        return attentionNum;
    }

    public void setAttentionNum(Integer attentionNum) {
        this.attentionNum = attentionNum;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public BigDecimal getBanlance() {
        return banlance;
    }

    public void setBanlance(BigDecimal banlance) {
        this.banlance = banlance;
    }
}