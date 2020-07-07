package com.qujiali.jiaogegongren.bean;

import com.google.gson.annotations.SerializedName;

public class WorkerProficiencyEntity {

    /**
     * searchValue : null
     * createBy : admin
     * createTime : 2020-04-23 17:19:04
     * updateBy : null
     * updateTime : null
     * remark : 初级工
     * dataScope : null
     * params : {}
     * dictCode : 100
     * dictSort : 0
     * dictLabel : 初级工
     * dictValue : 1
     * dictType : sys_proficiency
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
    private String remark;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
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
