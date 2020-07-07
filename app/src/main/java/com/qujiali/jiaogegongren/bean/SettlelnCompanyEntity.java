package com.qujiali.jiaogegongren.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

public class SettlelnCompanyEntity {


    /**
     * bSettledEnterpriseVo : {"id":35,"name":"去家里企业","profile":"http://dev.jiaogegongren.com/22a18c2bdf5e42fc9a12ecb092a0c2e5918","serviceArea":"150102","shortName":"去家里建成2","phone":"17180106555","profession":"公司业务","workType":"附近打瞌睡","degree":null,"userId":125,"status":0,"refuseReason":null,"skillsIntroduce":"想想轻轻解放路电视剧付款了解但是考虑觉得付款洛杉矶付款领导撒娇；发动机卡死减肥；啊觉得付款了；就阿斯附近的啊减肥大事记；浪费发达是发达是范德萨","skillsImages":"http://dev.jiaogegongren.com/7643fc40d5ad4b249c4b4c85ce0e9a938275,http://dev.jiaogegongren.com/402128cdc6f84bf6a7834fd7e8ae42687278,http://dev.jiaogegongren.com/47b88bc307d34ad2958222375ac8a5938667","checkTime":0,"delFlag":0,"enabled":0,"createTime":null,"updateTime":null,"areaList":["内蒙古自治区","呼和浩特市","新城区"],"authentication":2,"phoneHide":null,"focusId":null}
     * bProjectEnterprise : [{"searchValue":null,"createBy":null,"createTime":"2020-06-04 10:58:54","updateBy":null,"updateTime":null,"remark":null,"dataScope":null,"params":{},"id":1,"name":"项目名称","startDate":"2020-06-04","endDate":"2020-06-04","content":"回家付款电视剧咖啡回到家拉屎健康发达是复活节抵抗力撒范德萨发达是","settledEnterpriseId":35,"delFlag":0,"remarks":null}]
     */

    private BSettledEnterpriseVoBean bSettledEnterpriseVo;
    private List<BProjectEnterpriseBean> bProjectEnterprise;

    public BSettledEnterpriseVoBean getBSettledEnterpriseVo() {
        return bSettledEnterpriseVo;
    }

    public void setBSettledEnterpriseVo(BSettledEnterpriseVoBean bSettledEnterpriseVo) {
        this.bSettledEnterpriseVo = bSettledEnterpriseVo;
    }

    public List<BProjectEnterpriseBean> getBProjectEnterprise() {
        return bProjectEnterprise;
    }

    public void setBProjectEnterprise(List<BProjectEnterpriseBean> bProjectEnterprise) {
        this.bProjectEnterprise = bProjectEnterprise;
    }

    public static class BSettledEnterpriseVoBean implements Serializable {
        /**
         * id : 35
         * name : 去家里企业
         * profile : http://dev.jiaogegongren.com/22a18c2bdf5e42fc9a12ecb092a0c2e5918
         * serviceArea : 150102
         * shortName : 去家里建成2
         * phone : 17180106555
         * profession : 公司业务
         * workType : 附近打瞌睡
         * degree : null
         * userId : 125
         * status : 0
         * refuseReason : null
         * skillsIntroduce : 想想轻轻解放路电视剧付款了解但是考虑觉得付款洛杉矶付款领导撒娇；发动机卡死减肥；啊觉得付款了；就阿斯附近的啊减肥大事记；浪费发达是发达是范德萨
         * skillsImages : http://dev.jiaogegongren.com/7643fc40d5ad4b249c4b4c85ce0e9a938275,http://dev.jiaogegongren.com/402128cdc6f84bf6a7834fd7e8ae42687278,http://dev.jiaogegongren.com/47b88bc307d34ad2958222375ac8a5938667
         * checkTime : 0
         * delFlag : 0
         * enabled : 0
         * createTime : null
         * updateTime : null
         * areaList : ["内蒙古自治区","呼和浩特市","新城区"]
         * authentication : 2
         * phoneHide : null
         * focusId : null
         */

        private int id;
        private String name;
        private String profile;
        private String serviceArea;
        private String shortName;
        private String phone;
        private String profession;
        private String workType;
        private Object degree;
        private int userId;
        private int status;
        private String refuseReason;
        private String skillsIntroduce;
        private String skillsImages;
        private int checkTime;
        private int delFlag;
        private int enabled;
        private Object createTime;
        private Object updateTime;
        private int authentication;
        private Object phoneHide;
        private String focusId;
        private List<String> areaList;

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

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public String getServiceArea() {
            return serviceArea;
        }

        public void setServiceArea(String serviceArea) {
            this.serviceArea = serviceArea;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getWorkType() {
            return workType;
        }

        public void setWorkType(String workType) {
            this.workType = workType;
        }

        public Object getDegree() {
            return degree;
        }

        public void setDegree(Object degree) {
            this.degree = degree;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public String getSkillsIntroduce() {
            if (TextUtils.isEmpty(skillsIntroduce)){
                return "这个家伙很懒，什么也没留下~";
            }
            return skillsIntroduce;
        }

        public void setSkillsIntroduce(String skillsIntroduce) {
            this.skillsIntroduce = skillsIntroduce;
        }

        public String getSkillsImages() {
            return skillsImages;
        }

        public void setSkillsImages(String skillsImages) {
            this.skillsImages = skillsImages;
        }

        public int getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(int checkTime) {
            this.checkTime = checkTime;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public int getEnabled() {
            return enabled;
        }

        public void setEnabled(int enabled) {
            this.enabled = enabled;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getAuthentication() {
            String authentication2 = "";
            if (authentication == 1) {
                authentication2 = "已认证";
            }
            return authentication2;
        }

        public void setAuthentication(int authentication) {
            this.authentication = authentication;
        }

        public Object getPhoneHide() {
            return phoneHide;
        }

        public void setPhoneHide(Object phoneHide) {
            this.phoneHide = phoneHide;
        }

        public String getFocusId() {
            return focusId;
        }

        public void setFocusId(String focusId) {
            this.focusId = focusId;
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

    public static class BProjectEnterpriseBean implements Serializable{
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2020-06-04 10:58:54
         * updateBy : null
         * updateTime : null
         * remark : null
         * dataScope : null
         * params : {}
         * id : 1
         * name : 项目名称
         * startDate : 2020-06-04
         * endDate : 2020-06-04
         * content : 回家付款电视剧咖啡回到家拉屎健康发达是复活节抵抗力撒范德萨发达是
         * settledEnterpriseId : 35
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
        private String startDate;
        private String endDate;
        private String content;
        private int settledEnterpriseId;
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

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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
}
