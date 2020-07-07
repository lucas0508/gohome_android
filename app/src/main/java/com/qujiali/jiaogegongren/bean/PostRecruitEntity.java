package com.qujiali.jiaogegongren.bean;

import java.util.List;

public class PostRecruitEntity {


    /**
     * id : 257
     * title : iOS6
     * workType : 破陪
     * priceMargin : 12
     * location : 150000
     * describe : 公共关系
     * imags : null
     * userId : null
     * labels : 态度端正,业务熟练,工资日结
     * enabled : 1
     * status : 0
     * refuseReason : null
     * delFlag : 0
     * createTime : 2020-06-18 17:23:07
     * updateTime : null
     * phone : 够你
     * userName : 17180106555
     * phoneHide : 够你
     * profile : http://media.jiaogegongren.com/fb2b46a824544ce7ba80b6a0e069d1328012
     * areaList : ["内蒙古自治区"]
     */

    private int id;
    private String title;
    private String workType;
    private String priceMargin;
    private String location;
    private String describe;
    private String imags;
    private Object userId;
    private String labels;
    private int enabled;
    private int status;
    private String refuseReason;
    private int delFlag;
    private String createTime;
    private String updateTime;
    private String phone;
    private String userName;
    private String phoneHide;
    private String profile;
    private List<String> areaList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getPriceMargin() {
        return priceMargin;
    }

    public void setPriceMargin(String priceMargin) {
        this.priceMargin = priceMargin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImags() {
        return imags;
    }

    public void setImags(String imags) {
        this.imags = imags;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneHide() {
        return phoneHide;
    }

    public void setPhoneHide(String phoneHide) {
        this.phoneHide = phoneHide;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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
