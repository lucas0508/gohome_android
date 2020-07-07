package com.qujiali.jiaogegongren.bean;

import java.util.List;

public class AttentionEntity {


    /**
     * id : 50
     * name : 吃吃吃
     * profile : http://dev.jiaogegongren.com/c100ff945e964628be8f872b506bfe8b2160
     * serviceArea : 150000
     * phone : null
     * profession : 茶余饭后超级超级创新意识
     * workType : 木工/水工
     * degree : null
     * userId : 101
     * status : null
     * refuseReason : null
     * skillsIntroduce : null
     * skillsImages : null
     * checkTime : 2
     * delFlag : 2
     * enabled : 0
     * createTime : 2020-05-28 16:40:52
     * age : 0
     * areaList : ["内蒙古自治区"]
     * birthday : null
     * sex : 1
     * updateTime : null
     * authentication : 2
     * certificate : null
     * role : WORKER
     * loginTime : null
     * keyword : null
     * phoneHide : null
     * focusId : 38
     * shortName : null
     */

    private int id;
    private String name;
    private String profile;
    private String serviceArea;
    private Object phone;
    private String profession;
    private String workType;
    private String degree;
    private int userId;
    private Object status;
    private Object refuseReason;
    private Object skillsIntroduce;
    private Object skillsImages;
    private int checkTime;
    private int delFlag;
    private int enabled;
    private String createTime;
    private int age;
    private Object birthday;
    private int sex;
    private Object updateTime;
    private int authentication;
    private String certificate;
    private String role;
    private Object loginTime;
    private Object keyword;
    private Object phoneHide;
    private int focusId;
    private String shortName;
    private List<String> areaList;

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

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(Object refuseReason) {
        this.refuseReason = refuseReason;
    }

    public Object getSkillsIntroduce() {
        return skillsIntroduce;
    }

    public void setSkillsIntroduce(Object skillsIntroduce) {
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

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public String getAuthentication() {
        String authentication2 = "";
        if (authentication==1){
            authentication2="已实名";
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

    public Object getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Object loginTime) {
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

    public int getFocusId() {
        return focusId;
    }

    public void setFocusId(int focusId) {
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
