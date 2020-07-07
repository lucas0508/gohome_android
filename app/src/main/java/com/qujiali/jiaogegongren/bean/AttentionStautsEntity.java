package com.qujiali.jiaogegongren.bean;

public class AttentionStautsEntity {

    /**
     * searchValue : null
     * createBy : null
     * createTime : 2020-06-18 10:10:54
     * updateBy : null
     * updateTime : null
     * remark : null
     * dataScope : null
     * params : {}
     * id : 45
     * userId : 2987
     * settledEnterpriseId : 41
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
    private int userId;
    private int settledEnterpriseId;
    private int settledWorkerId ;

    public int getSettledWorkerId() {
        return settledWorkerId;
    }

    public void setSettledWorkerId(int settledWorkerId) {
        this.settledWorkerId = settledWorkerId;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSettledEnterpriseId() {
        return settledEnterpriseId;
    }

    public void setSettledEnterpriseId(int settledEnterpriseId) {
        this.settledEnterpriseId = settledEnterpriseId;
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
}
