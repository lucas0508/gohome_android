package com.qujiali.jiaogegongren.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class SkillCertificationEntity implements Parcelable {


    /**
     * searchValue : null
     * createBy : null
     * createTime : 2020-06-04 14:54:12
     * updateBy : null
     * updateTime : null
     * remark : null
     * dataScope : null
     * params : {}
     * id : 46
     * name : 程序员
     * pictrues : http://dev.jiaogegongren.com/0d431644d0384dc998b89ec464bd1eb6767
     * sysUserId : null
     * auditTime : null
     * status : 0
     * refuseReason : null
     * settledWorkerId : 64
     * delFlag : 0
     * remarks : null
     */

    private Object searchValue;
    private Object createBy;
    private String createTime;
    private Object updateBy;
    private Object updateTime;
    private Object remark;
    private Object dataScope;
    private ParamsBean params;
    private int id;
    private String name;
    private String pictrues;
    private Object sysUserId;
    private Object auditTime;
    private int status;
    private String refuseReason;
    private int settledWorkerId;
    private int delFlag;
    private Object remarks;

    public Object getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(Object searchValue) {
        this.searchValue = searchValue;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public Object getDataScope() {
        return dataScope;
    }

    public void setDataScope(Object dataScope) {
        this.dataScope = dataScope;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
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

    public String getPictrues() {
        return pictrues;
    }

    public void setPictrues(String pictrues) {
        this.pictrues = pictrues;
    }

    public Object getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Object sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Object getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Object auditTime) {
        this.auditTime = auditTime;
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

    public int getSettledWorkerId() {
        return settledWorkerId;
    }

    public void setSettledWorkerId(int settledWorkerId) {
        this.settledWorkerId = settledWorkerId;
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

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public static class ParamsBean {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.pictrues);
        dest.writeInt(this.status);
        dest.writeString(this.refuseReason);
        dest.writeInt(this.settledWorkerId);
        dest.writeInt(this.delFlag);
    }

    public SkillCertificationEntity() {
    }

    protected SkillCertificationEntity(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.pictrues = in.readString();
        this.status = in.readInt();
        this.refuseReason = in.readString();
        this.settledWorkerId = in.readInt();
        this.delFlag = in.readInt();
    }

    public static final Parcelable.Creator<SkillCertificationEntity> CREATOR = new Parcelable.Creator<SkillCertificationEntity>() {
        @Override
        public SkillCertificationEntity createFromParcel(Parcel source) {
            return new SkillCertificationEntity(source);
        }

        @Override
        public SkillCertificationEntity[] newArray(int size) {
            return new SkillCertificationEntity[size];
        }
    };
}
