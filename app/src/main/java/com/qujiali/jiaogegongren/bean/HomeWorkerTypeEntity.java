package com.qujiali.jiaogegongren.bean;

import com.google.gson.annotations.SerializedName;

public class HomeWorkerTypeEntity {


    /**
     * searchValue : null
     * createBy : admin
     * createTime : 2020-09-10 17:07:33
     * updateBy : null
     * updateTime : null
     * remark : null
     * dataScope : null
     * params : {}
     * dictCode : 112
     * dictSort : 1
     * dictLabel : 水暖工
     * dictValue : 0
     * dictType : sys_common_work
     * cssClass : null
     * listClass : null
     * isDefault : N
     * status : 0
     * default : false
     */

    private Object searchValue;
    private String createBy;
    private String createTime;
    private Object updateBy;
    private Object updateTime;
    private Object remark;
    private Object dataScope;
    private ParamsBean params;
    private int dictCode;
    private int dictSort;
    private String dictLabel;
    private String dictValue;
    private String dictType;
    private Object cssClass;
    private Object listClass;
    private String isDefault;
    private String status;
    @SerializedName("default")
    private boolean defaultX;

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

    public int getDictCode() {
        return dictCode;
    }

    public void setDictCode(int dictCode) {
        this.dictCode = dictCode;
    }

    public int getDictSort() {
        return dictSort;
    }

    public void setDictSort(int dictSort) {
        this.dictSort = dictSort;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Object getCssClass() {
        return cssClass;
    }

    public void setCssClass(Object cssClass) {
        this.cssClass = cssClass;
    }

    public Object getListClass() {
        return listClass;
    }

    public void setListClass(Object listClass) {
        this.listClass = listClass;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDefaultX() {
        return defaultX;
    }

    public void setDefaultX(boolean defaultX) {
        this.defaultX = defaultX;
    }

    public static class ParamsBean {
    }
}
