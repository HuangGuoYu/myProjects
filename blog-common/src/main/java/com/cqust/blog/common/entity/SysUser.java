package com.cqust.blog.common.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_sys_user")
public class SysUser {
    @Id
    @Column(name = "id", length = 11, nullable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "password", nullable = false, length = 16)
    private String pwd;

    public SysUser(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public SysUser() {
        super();
    }

}