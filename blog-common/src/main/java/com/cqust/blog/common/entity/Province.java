package com.cqust.blog.common.entity;

import java.io.Serializable;

public class Province implements Serializable{


    private static final long serialVersionUID = 5731753263189963431L;
    private Byte id;

    private String pname;

    public Province(Byte id, String pname) {
        this.id = id;
        this.pname = pname;
    }

    public Province() {
        super();
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }
}