package com.dr.mybatiscase.entity;

import java.io.Serializable;

public class My implements Serializable {
    private String a;

    private String b;

    private static final long serialVersionUID = 1L;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a == null ? null : a.trim();
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b == null ? null : b.trim();
    }
}