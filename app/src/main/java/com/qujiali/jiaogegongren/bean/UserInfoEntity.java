package com.qujiali.jiaogegongren.bean;

import com.qujiali.jiaogegongren.common.base.BaseEntity;

/**
 * @author QiZai
 * @desc
 * @date 2018/4/11
 */

public class UserInfoEntity {


    /**
     * searchValue : null
     * createBy : null
     * createTime : 2020-05-27 18:16:25
     * updateBy : null
     * updateTime : 2020-05-28 10:43:54
     * remark : null
     * dataScope : null
     * params : {}
     * id : 101
     * name : 先生
     * sex : 1
     * idNumber : null
     * phone : 17180106555
     * password : $2a$10$5eBRIwqC6IE3ZuXM4WjEhuct5Zy3qpMTnlm2xMZl43LNe9UotOlHy
     * birthday : null
     * nickname : null
     * profile : null
     * enabled : 0
     * openid : null
     * loginTime : 2020-05-28T10:43:54.000+0800
     * loginIp : 192.168.1.56
     * delFlag : 0
     * remarks : null
     */

    private String name;
    private int sex;
    private String phone;
    private String password;
    private String birthday;
    private String nickname;
    private String profile;
    private int enabled;
    private int delFlag;
    private String remarks;
    private String token;

    public String getToken() {
        return token == null ? "" : token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSexText() {
        String sexText="";
        if (sex==1){
            sexText="男";
        }else if (sex==2){
            sexText="女";
        }else {
            sexText="保密";
        }
        return sexText;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Object getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }


    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public static class ParamsBean {
    }
}
