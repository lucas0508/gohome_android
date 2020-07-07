package com.qujiali.jiaogegongren.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

public class SettlelnEntity implements Serializable {


    /**
     * bsettledWorkerVo : {"id":63,"name":"老产","profile":"http://dev.jiaogegongren.com/c2e5b5c6aa2d465fa2cd3a122af551a42019","serviceArea":"110000","phone":"17180106555","profession":"喜欢健康东方航空就阿斯","workType":"反对","degree":"1","userId":125,"status":0,"refuseReason":null,"skillsIntroduce":"技能技能技能进呢个技能极少","skillsImages":"http://dev.jiaogegongren.com/d6f4e66f300643c6b8a33f02e527f23a5302,http://dev.jiaogegongren.com/b415781a03794eb2bab982f41aa39ddb9986,http://dev.jiaogegongren.com/e6d69a5a7c5447419f03884cf94fe1209728,http://dev.jiaogegongren.com/015fed8da48141f9a32febd3b08384941318","checkTime":0,"delFlag":0,"enabled":0,"createTime":null,"age":0,"areaList":["北京市"],"birthday":"2020-06-03","sex":1,"updateTime":null,"authentication":null,"certificate":null,"role":null,"loginTime":null,"keyword":null,"phoneHide":null,"focusId":null,"shortName":null}
     * bprojectWorkerList : [{"searchValue":null,"createBy":null,"createTime":"2020-06-03 18:35:06","updateBy":null,"updateTime":null,"remark":null,"dataScope":null,"params":{},"id":62,"name":"项目名称","jobs":"职位类型","startDate":"2020-06-03","endDate":"2020-06-03","content":"项目介绍","settledWorkerId":63,"delFlag":0,"remarks":null}]
     */

    private BsettledWorkerVoBean bsettledWorkerVo;
    private List<BprojectWorkerListBean> bprojectWorkerList;

    public BsettledWorkerVoBean getBsettledWorkerVo() {
        return bsettledWorkerVo;
    }

    public void setBsettledWorkerVo(BsettledWorkerVoBean bsettledWorkerVo) {
        this.bsettledWorkerVo = bsettledWorkerVo;
    }

    public List<BprojectWorkerListBean> getBprojectWorkerList() {
        return bprojectWorkerList;
    }

    public void setBprojectWorkerList(List<BprojectWorkerListBean> bprojectWorkerList) {
        this.bprojectWorkerList = bprojectWorkerList;
    }

    public static class BsettledWorkerVoBean implements Serializable{
        /**
         * id : 63
         * name : 老产
         * profile : http://dev.jiaogegongren.com/c2e5b5c6aa2d465fa2cd3a122af551a42019
         * serviceArea : 110000
         * phone : 17180106555
         * profession : 喜欢健康东方航空就阿斯
         * workType : 反对
         * degree : 1
         * userId : 125
         * status : 0
         * refuseReason : null
         * skillsIntroduce : 技能技能技能进呢个技能极少
         * skillsImages : http://dev.jiaogegongren.com/d6f4e66f300643c6b8a33f02e527f23a5302,http://dev.jiaogegongren.com/b415781a03794eb2bab982f41aa39ddb9986,http://dev.jiaogegongren.com/e6d69a5a7c5447419f03884cf94fe1209728,http://dev.jiaogegongren.com/015fed8da48141f9a32febd3b08384941318
         * checkTime : 0
         * delFlag : 0
         * enabled : 0
         * createTime : null
         * age : 0
         * areaList : ["北京市"]
         * birthday : 2020-06-03
         * sex : 1
         * updateTime : null
         * authentication : null
         * certificate : null
         * role : null
         * loginTime : null
         * keyword : null
         * phoneHide : null
         * focusId : null
         * shortName : null
         */

        private int id;
        private String name;
        private String profile;
        private String serviceArea;
        private String phone;
        private String profession;
        private String workType;
        private String degree;
        private int userId;
        private int status;
        private String refuseReason;
        private String skillsIntroduce;
        private String skillsImages;
        private int checkTime;
        private int delFlag;
        private int enabled;
        private Object createTime;
        private int age;
        private String birthday;
        private int sex;
        private Object updateTime;
        private int authentication;
        private String certificate;
        private Object role;
        private Object loginTime;
        private Object keyword;
        private Object phoneHide;
        private String focusId;
        private Object shortName;
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

        public String getDegree() {
            return degree;
        }

        public String getDegreeText() {
            String degreeText="";
            if (degree.equals("1")){
                degreeText="初级工";
            }else if (degree.equals("2")){
                degreeText="中级工";
            }else if (degree.equals("3")){
                degreeText="高级工";
            }else {
            }
            return degreeText;
        }

        public void setDegree(String degree) {
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
            if (TextUtils.isEmpty(skillsIntroduce))return "这个家伙很懒，什么也没留下~";
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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getSex() {
            return sex;
        }

        public String getSexText() {
            String sexText="";
            if (sex==1){
                sexText="男";
            }else if (sex==2){
                sexText="女";
            }else {
                sexText="保密";
            }
            return sexText;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        // 1:实名 2：未实名
        public String getAuthentication() {
            String authentication2 = "";
            if (authentication == 1) {
                authentication2 = "已实名";
            }
            return authentication2;
        }

        public void setAuthentication(int authentication) {
            this.authentication = authentication;
        }

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public Object getRole() {
            return role;
        }

        public void setRole(Object role) {
            this.role = role;
        }

        public Object getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(Object loginTime) {
            this.loginTime = loginTime;
        }

        public Object getKeyword() {
            return keyword;
        }

        public void setKeyword(Object keyword) {
            this.keyword = keyword;
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

        public Object getShortName() {
            return shortName;
        }

        public void setShortName(Object shortName) {
            this.shortName = shortName;
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

    public static class BprojectWorkerListBean implements Serializable{
        /**
         * searchValue : null
         * createBy : null
         * createTime : 2020-06-03 18:35:06
         * updateBy : null
         * updateTime : null
         * remark : null
         * dataScope : null
         * params : {}
         * id : 62
         * name : 项目名称
         * jobs : 职位类型
         * startDate : 2020-06-03
         * endDate : 2020-06-03
         * content : 项目介绍
         * settledWorkerId : 63
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
        private String jobs;
        private String startDate;
        private String endDate;
        private String content;
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

        public String getJobs() {
            return jobs;
        }

        public void setJobs(String jobs) {
            this.jobs = jobs;
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

        public static class ParamsBean implements Serializable{
        }
    }
}
