package com.qujiali.jiaogegongren.bean;

import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;

import java.io.Serializable;
import java.util.List;

public class RecruitmentEntity implements Serializable {


    /**
     * id : 218
     * title : 代码工
     * workType : 代码工
     * priceMargin : 200
     * location : 150102
     * describe : 奖学金多久继续继续
     * imags : http://dev.jiaogegongren.com/db3726284ac14f93a623d935a4f1cca6179
     * userId : null
     * labels : 支持异地,业务熟练
     * enabled : 1
     * status : 1
     * refuseReason : null
     * delFlag : 0
     * createTime : 2020-05-26 16:10:07
     * updateTime : 2020-05-26 16:10:59
     * phone : 18247158483
     * userName : 先生
     * phoneHide : null
     * profile : null
     * areaList : ["内蒙古自治区","呼和浩特市","新城区"]
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
    private Object phoneHide;
    private Object profile;
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

    public String getStatusName() {
        String statusName = "";
        if (status == 0) {
            statusName = "待审核";
        } else if (status == 1) {
            statusName = "已审核";
        } else if (status == 2) {
            statusName = "已拒绝";
        }
        return statusName;
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

    public List<String> getImagsList() {
        List<String> strings = DevicePermissionsUtils.stringToList(imags);
        return strings;
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

    public Object getPhoneHide() {
        return phoneHide;
    }

    public void setPhoneHide(Object phoneHide) {
        this.phoneHide = phoneHide;
    }

    public Object getProfile() {
        return profile;
    }

    public void setProfile(Object profile) {
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
