package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "tbl_withdraw")
@Getter
@Setter
public class Withdraw implements Serializable{

    private static final long serialVersionUID = -7775938402970588204L;

    @Id
    @Column(name = "id", length = 11)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "apply_time")
    private Date applyTime;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "alipay_name", nullable = false)
    private String alipayName;

    @Column(name = "alipay_account", nullable = false)
    private String alipayAccount;

    @Column(name = "money", nullable = false)
    private Integer money;

    @Column(name = "state")
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

}