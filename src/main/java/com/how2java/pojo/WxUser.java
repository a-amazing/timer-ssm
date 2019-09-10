package com.how2java.pojo;

public class WxUser {
    private Integer id;

    private String username;

    private String mobilePhone;

    public WxUser(Integer id, String username, String mobilePhone) {
        this.id = id;
        this.username = username;
        this.mobilePhone = mobilePhone;
    }

    public WxUser() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }
}