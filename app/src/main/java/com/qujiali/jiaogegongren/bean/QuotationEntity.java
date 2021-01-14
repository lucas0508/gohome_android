package com.qujiali.jiaogegongren.bean;

import java.util.List;

public class QuotationEntity {


    /**
     * searchValue : null
     * createBy : null
     * createTime : 2020-09-14 14:56:43
     * updateBy : null
     * updateTime : 2020-09-14 14:56:43
     * remark : null
     * dataScope : null
     * params : {}
     * serviceTypeId : 1
     * serviceName : 家政
     * icon : null
     * parentId : 0
     * delFlag : 0
     * serviceTypeSonList : [{"searchValue":null,"createBy":null,"createTime":"2020-09-14 14:44:53","updateBy":null,"updateTime":"2020-09-15 11:54:44","remark":null,"dataScope":null,"params":{},"serviceTypeId":2,"serviceName":"洗玻璃","icon":"http://dev.jiaogegongren.com/f1a52c2e380d4deea265cb725e35a5cc6636","parentId":1,"delFlag":0,"serviceTypeSonList":null},{"searchValue":null,"createBy":null,"createTime":"2020-09-14 14:45:11","updateBy":null,"updateTime":"2020-09-15 11:54:59","remark":null,"dataScope":null,"params":{},"serviceTypeId":3,"serviceName":"吸油烟机","icon":"http://dev.jiaogegongren.com/bf11ef95740a434ab31c3418dc6e6c498381","parentId":1,"delFlag":0,"serviceTypeSonList":null}]
     */

    private Object searchValue;
    private Object createBy;
    private String createTime;
    private Object updateBy;
    private String updateTime;
    private Object remark;
    private Object dataScope;
    private ParamsBean params;
    private int serviceTypeId;
    private String serviceName;
    private Object icon;
    private int parentId;
    private int delFlag;
    private int viewType = 1;

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    private List<ServiceTypeSonListBean> serviceTypeSonList;

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

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public List<ServiceTypeSonListBean> getServiceTypeSonList() {
        return serviceTypeSonList;
    }

    public void setServiceTypeSonList(List<ServiceTypeSonListBean> serviceTypeSonList) {
        this.serviceTypeSonList = serviceTypeSonList;
    }

    public static class ParamsBean {
    }

    public static class ServiceTypeSonListBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2020-09-14 14:44:53
         * updateBy : null
         * updateTime : 2020-09-15 11:54:44
         * remark : null
         * dataScope : null
         * params : {}
         * serviceTypeId : 2
         * serviceName : 洗玻璃
         * icon : http://dev.jiaogegongren.com/f1a52c2e380d4deea265cb725e35a5cc6636
         * parentId : 1
         * delFlag : 0
         * serviceTypeSonList : null
         */

        private Object searchValue;
        private Object createBy;
        private String createTime;
        private Object updateBy;
        private String updateTime;
        private Object remark;
        private Object dataScope;
        private ParamsBeanX params;
        private int serviceTypeId;
        private String serviceName;
        private String icon;
        private int parentId;
        private int delFlag;
        private Object serviceTypeSonList;
        private int viewType = 2;

        public int getViewType() {
            return viewType;
        }

        public void setViewType(int viewType) {
            this.viewType = viewType;
        }

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

        public ParamsBeanX getParams() {
            return params;
        }

        public void setParams(ParamsBeanX params) {
            this.params = params;
        }

        public int getServiceTypeId() {
            return serviceTypeId;
        }

        public void setServiceTypeId(int serviceTypeId) {
            this.serviceTypeId = serviceTypeId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public Object getServiceTypeSonList() {
            return serviceTypeSonList;
        }

        public void setServiceTypeSonList(Object serviceTypeSonList) {
            this.serviceTypeSonList = serviceTypeSonList;
        }

        public static class ParamsBeanX {
        }
    }
}
