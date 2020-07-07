package com.qujiali.jiaogegongren.bean;

import android.text.TextUtils;

import java.util.List;

public class HomeEntity {


    /**
     * id : 17
     * name : 传诚文化传媒有限责任公司
     * profile : http://dev.jiaogegongren.com/0a9334c87ba146198296832044e1aeb84850
     * serviceArea : 150100
     * phone : null
     * profession : 主要是装修工程装修等业务
     * workType : 装修/工程装修
     * degree : null
     * userId : null
     * status : null
     * refuseReason : null
     * skillsIntroduce : 克隆空间距离噜啦啦噜啦啦骷髅精灵理解理解斤斤计较几个号回老家了紧急集合垃圾食品咯莫物理胡考虑考虑来咯来咯李经理可口可乐了路噜啦啦噜啦啦米开朗基罗
     * skillsImages : null
     * checkTime : 0
     * delFlag : null
     * enabled : null
     * createTime : 2020-05-21 18:12:29
     * age : 0
     * areaList : ["内蒙古自治区","呼和浩特市"]
     * birthday : null
     * sex : null
     * updateTime : null
     * authentication : 1
     * certificate : null
     * role : ENTERPRISE
     * loginTime : 2020-05-21 18:43:15
     * keyword : null
     * phoneHide : null
     * focusId : null
     * shortName : 传诚文化
     */

    private int id;
    private String name;
    private String profile;
    private String serviceArea;
    private Object phone;
    private String profession;
    private String workType;
    private String degree;
    private Object userId;
    private String status;
    private Object refuseReason;
    private String skillsIntroduce;
    private Object skillsImages;
    private int checkTime;
    private Object delFlag;
    private Object enabled;
    private String createTime;
    private int age;
    private Object birthday;
    private String sex;
    private Object updateTime;
    private int authentication;
    private String certificate;
    private String role;
    private String loginTime;
    private Object keyword;
    private Object phoneHide;
    private Object focusId;
    private String shortName;
    private List<String> areaList;
    private String title;

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getDegree() {
        return degree;
    }

    public String getDegreeText() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }



    public void setStatus(String status) {
        this.status = status;
    }

    public Object getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(Object refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getSkillsIntroduce() {
        if (TextUtils.isEmpty(skillsIntroduce)){
            return "这个家伙很懒,什么也没留下~";
        }
        return skillsIntroduce;
    }

    public void setSkillsIntroduce(String skillsIntroduce) {
        this.skillsIntroduce = skillsIntroduce;
    }

    public Object getSkillsImages() {
        return skillsImages;
    }

    public void setSkillsImages(Object skillsImages) {
        this.skillsImages = skillsImages;
    }

    public int getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(int checkTime) {
        this.checkTime = checkTime;
    }

    public Object getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Object delFlag) {
        this.delFlag = delFlag;
    }

    public Object getEnabled() {
        return enabled;
    }

    public void setEnabled(Object enabled) {
        this.enabled = enabled;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    // 1:实名 2：未实名
    public String getAuthentication() {
        String authentication2 = "";
        if (authentication == 1) {
            if (role.equals("WORKER")) {
                authentication2 = "已实名";
            } else if (role.equals("ENTERPRISE")) {
                authentication2 = "已认证";
            }
        }
        return authentication2;
    }

    public void setAuthentication(int authentication) {
        this.authentication = authentication;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getRoleName() {
        String roleName="";
        if (role.equals("WORKER")) {
            roleName="个人";
        } else if (role.equals("ENTERPRISE")) {
            roleName="公司";
        }
        return roleName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public Object getKeyword() {
        return keyword;
    }

    public void setKeyword(Object keyword) {
        this.keyword = keyword;
    }

    public Object getPhoneHide() {
        return phoneHide;
    }

    public void setPhoneHide(Object phoneHide) {
        this.phoneHide = phoneHide;
    }

    public Object getFocusId() {
        return focusId;
    }

    public void setFocusId(Object focusId) {
        this.focusId = focusId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


    public String getAreaList() {
        String area = "";
        for (String a : areaList) {
            area = area + a;
        }
        return area;
    }
    public void setAreaList(List<String> areaList) {
        this.areaList = areaList;
    }
}
