package com.qujiali.jiaogegongren.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author : Xrf
 * @date : 2019/4/816:27
 * desc   :
 */
public class BannerEntity {


    /**
     * cityCode :
     * createTime : null
     * keyword :
     * linkUrl : https://www.jiaogegongren.com
     * orderBy :
     * pictureUrl :
     * remark :
     * updateTime : null
     */

    private String cityCode;
    private Object createTime;
    private String keyword;
    private String linkUrl;
    private String orderBy;
    private String pictureUrl;
    private String remark;
    private Object updateTime;
    /**
     * searchValue : null
     * createBy : 1
     * createTime : 2020-05-25 15:09:47
     * updateBy : 1
     * updateTime : null
     * remark : null
     * dataScope : null
     * params : {}
     * id : 1
     * content : 内容消息
     * delFlag : 0
     * remarks : null
     */

    private Object searchValue;
    private String createBy;
    @SerializedName("createTime")
    private String createTimeX;
    private String updateBy;
    @SerializedName("updateTime")
    private Object updateTimeX;
    @SerializedName("remark")
    private Object remarkX;
    private Object dataScope;
    private ParamsBean params;
    private int id;
    private String content;
    private int delFlag;
    private Object remarks;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
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

    public String getCreateTimeX() {
        return createTimeX;
    }

    public void setCreateTimeX(String createTimeX) {
        this.createTimeX = createTimeX;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateTimeX() {
        return updateTimeX;
    }

    public void setUpdateTimeX(Object updateTimeX) {
        this.updateTimeX = updateTimeX;
    }

    public Object getRemarkX() {
        return remarkX;
    }

    public void setRemarkX(Object remarkX) {
        this.remarkX = remarkX;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
