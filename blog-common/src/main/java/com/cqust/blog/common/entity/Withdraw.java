package com.cqust.blog.common.entity;

import java.util.Date;

public class Withdraw {
    private Integer id;

    private Date applyTime;

    private Integer userId;

    private String alipayName;

    private String alipayAccount;

    private Integer money;

    private Byte state;

    public Withdraw(Integer id, Date applyTime, Integer userId, String alipayName, String alipayAccount, Integer money, Byte state) {
        this.id = id;
        this.applyTime = applyTime;
        this.userId = userId;
        this.alipayName = alipayName;
        this.alipayAccount = alipayAccount;
        this.money = money;
        this.state = state;
    }

    public Withdraw() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAlipayName() {
        return alipayName;
    }

    public void setAlipayName(String alipayName) {
        this.alipayName = alipayName == null ? null : alipayName.trim();
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount == null ? null : alipayAccount.trim();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }
}