package com.qujiali.jiaogegongren.bean;

public class VersionUpdateEntity {


    /**
     * searchValue : null
     * createBy : 1
     * createTime : 2020-06-22 10:17:35
     * updateBy : 1
     * updateTime : null
     * remark : null
     * dataScope : null
     * params : {}
     * id : 1
     * appId : 1
     * appName : 叫个工人
     * clientVersion : 2.0
     * downloadUrl : http://dev.jiaogegongren.com/叫个工人.apk
     * updateLog : null
     * updateInstall : 0
     * delFlag : 0
     * remarks : null
     */

    private Object searchValue;
    private String createBy;
    private String createTime;
    private String updateBy;
    private Object updateTime;
    private Object remark;
    private Object dataScope;
    private ParamsBean params;
    private int id;
    private String appId;
    private String appName;
    private String clientVersion;
    private String downloadUrl;
    private Object updateLog;
    private int updateInstall;
    private int delFlag;
    private Object remarks;

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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Object getUpdateLog() {
        return updateLog;
    }

    public void setUpdateLog(Object updateLog) {
        this.updateLog = updateLog;
    }

    public int getUpdateInstall() {
        return updateInstall;
    }

    public void setUpdateInstall(int updateInstall) {
        this.updateInstall = updateInstall;
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
