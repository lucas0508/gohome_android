package com.qujiali.jiaogegongren.bean;

public class CallPhoneEntity {


    /**
     * searchValue : null
     * createBy : 1
     * createTime : 2020-09-14 12:25:28
     * updateBy : 1
     * updateTime : 2020-09-14 12:25:28
     * remark : null
     * dataScope : null
     * params : {}
     * callInfoId : 1
     * cityCode : 150100
     * callWorkerNumber : 18847145915
     * complaintNumber : 18847145916
     * state : 1
     * delFlag : 0
     */

    private Object searchValue;
    private String createBy;
    private String createTime;
    private String updateBy;
    private String updateTime;
    private Object remark;
    private Object dataScope;
    private ParamsBean params;
    private int callInfoId;
    private String cityCode;
    private String callWorkerNumber;
    private String complaintNumber;
    private int state;
    private int delFlag;
    private String franchiseeName;

    public String getFranchiseeName() {
        return franchiseeName;
    }

    public void setFranchiseeName(String franchiseeName) {
        this.franchiseeName = franchiseeName;
    }

    public Object getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(Object searchValue) {
        this.searchValue = searchValue;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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

    public int getCallInfoId() {
        return callInfoId;
    }

    public void setCallInfoId(int callInfoId) {
        this.callInfoId = callInfoId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCallWorkerNumber() {
        return callWorkerNumber;
    }

    public void setCallWorkerNumber(String callWorkerNumber) {
        this.callWorkerNumber = callWorkerNumber;
    }

    public String getComplaintNumber() {
        return complaintNumber;
    }

    public void setComplaintNumber(String complaintNumber) {
        this.complaintNumber = complaintNumber;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public static class ParamsBean {
    }
}
