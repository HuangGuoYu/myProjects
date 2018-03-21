package com.cqust.blog.common.entity;

public class Province {
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