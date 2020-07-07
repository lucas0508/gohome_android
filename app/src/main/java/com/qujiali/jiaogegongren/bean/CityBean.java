package com.qujiali.jiaogegongren.bean;

public class CityBean {

    private String name;

    private String code;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
