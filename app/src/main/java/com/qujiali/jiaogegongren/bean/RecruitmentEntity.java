package com.qujiali.jiaogegongren.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecruitmentEntity implements Parcelable {


    /**
     * id : 258
     * title : 发达是
     * workType : 打算
     * priceMargin : 面议
     * location : 110101
     * describe : 发达是发达是
     * imags : null
     * userId : 125
     * labels : 态度端正,包食宿
     * enabled : 1
     * status : 1
     * refuseReason : null
     * delFlag : 0
     * createTime : 2020-07-21 11:01:34
     * updateTime : null
     * phone : 17180106555
     * userName : 先生
     * phoneHide : null
     * profile : http://dev.jiaogegongren.com/ae24d0f3c9f74df3a99985e02546f0a93260
     * areaList : ["北京市","北京市","东城区"]
     * applyVos : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"dataScope":null,"params":{},"id":6,"recruitId":258,"workResultId":39,"workType":"ENTERPRISE","roleProfile":"http://dev.jiaogegongren.com/34145ac1c1c94dc999036f22e31dfae67954","roleName":"去家里证企业公司"}]
     */

    private int id;
    private String title;
    private String workType;
    private String priceMargin;
    private String location;
    private String describe;
    private String imags;
    private int userId;
    private String labels;
    private int enabled;
    private int status;
    private String refuseReason;
    private int delFlag;
    private String createTime;
    private String updateTime;
    private String phone;
    private String userName;
    private String phoneHide;
    private String profile;
    private List<String> areaList;
    private List<ApplyVosBean> applyVos;
    private String applyType;

    public String getApplyType() {
        return applyType == null ? "" : applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getPriceMargin() {
        return priceMargin;
    }

    public void setPriceMargin(String priceMargin) {
        this.priceMargin = priceMargin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImags() {
        return imags;
    }

    public List<String> getImagsList() {
        List<String> strings = DevicePermissionsUtils.stringToList(imags);
        return strings;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getStatusName() {
        String statusName = "";
        if (status == 0) {
            statusName = "待审核";
        } else if (status == 1) {
            statusName = "已审核";
        } else if (status == 2) {
            statusName = "已拒绝";
        }
        return statusName;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
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

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Object getPhoneHide() {
        return phoneHide;
    }

    public void setPhoneHide(String phoneHide) {
        this.phoneHide = phoneHide;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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

    public List<ApplyVosBean> getApplyVos() {
        return applyVos;
    }

    public void setApplyVos(List<ApplyVosBean> applyVos) {
        this.applyVos = applyVos;
    }

    public static class ApplyVosBean implements Parcelable {
        /**
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * dataScope : null
         * params : {}
         * id : 6
         * recruitId : 258
         * workResultId : 39
         * workType : ENTERPRISE
         * roleProfile : http://dev.jiaogegongren.com/34145ac1c1c94dc999036f22e31dfae67954
         * roleName : 去家里证企业公司
         */

        private String searchValue;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String remark;
        private String dataScope;
        private ParamsBean params;
        private int id;
        private int recruitId;
        private int workResultId;
        private String workType;
        private String roleProfile;
        private String roleName;

        public String getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(String searchValue) {
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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getDataScope() {
            return dataScope;
        }

        public void setDataScope(String dataScope) {
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

        public int getRecruitId() {
            return recruitId;
        }

        public void setRecruitId(int recruitId) {
            this.recruitId = recruitId;
        }

        public int getWorkResultId() {
            return workResultId;
        }

        public void setWorkResultId(int workResultId) {
            this.workResultId = workResultId;
        }

        public String getWorkType() {
            return workType;
        }

        public void setWorkType(String workType) {
            this.workType = workType;
        }

        public String getRoleProfile() {
            return roleProfile;
        }

        public void setRoleProfile(String roleProfile) {
            this.roleProfile = roleProfile;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public static class ParamsBean {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.searchValue );
            dest.writeString(this.createBy );
            dest.writeString(this.createTime );
            dest.writeString(this.updateBy );
            dest.writeString(this.updateTime );
            dest.writeString(this.remark );
            dest.writeString(this.dataScope );
            dest.writeInt(this.id);
            dest.writeInt(this.recruitId);
            dest.writeInt(this.workResultId);
            dest.writeString(this.workType);
            dest.writeString(this.roleProfile);
            dest.writeString(this.roleName);
        }

        public ApplyVosBean() {
        }

        protected ApplyVosBean(Parcel in) {
            this.searchValue = in.readParcelable(String.class.getClassLoader());
            this.createBy = in.readParcelable(String.class.getClassLoader());
            this.createTime = in.readParcelable(String.class.getClassLoader());
            this.updateBy = in.readParcelable(String.class.getClassLoader());
            this.updateTime = in.readParcelable(String.class.getClassLoader());
            this.remark = in.readParcelable(String.class.getClassLoader());
            this.dataScope = in.readParcelable(String.class.getClassLoader());
            this.id = in.readInt();
            this.recruitId = in.readInt();
            this.workResultId = in.readInt();
            this.workType = in.readString();
            this.roleProfile = in.readString();
            this.roleName = in.readString();
        }

        public static final Creator<ApplyVosBean> CREATOR = new Creator<ApplyVosBean>() {
            @Override
            public ApplyVosBean createFromParcel(Parcel source) {
                return new ApplyVosBean(source);
            }

            @Override
            public ApplyVosBean[] newArray(int size) {
                return new ApplyVosBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.workType);
        dest.writeString(this.priceMargin);
        dest.writeString(this.location);
        dest.writeString(this.describe);
        dest.writeString(this.imags);
        dest.writeInt(this.userId);
        dest.writeString(this.labels);
        dest.writeInt(this.enabled);
        dest.writeInt(this.status);
        dest.writeString(this.refuseReason);
        dest.writeInt(this.delFlag);
        dest.writeString(this.createTime);
        dest.writeString(this.updateTime);
        dest.writeString(this.phone);
        dest.writeString(this.userName);
        dest.writeString(this.phoneHide);
        dest.writeString(this.profile);
        dest.writeStringList(this.areaList);
        dest.writeList(this.applyVos);
    }

    public RecruitmentEntity() {
    }

    protected RecruitmentEntity(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.workType = in.readString();
        this.priceMargin = in.readString();
        this.location = in.readString();
        this.describe = in.readString();
        this.imags = in.readString();
        this.userId = in.readInt();
        this.labels = in.readString();
        this.enabled = in.readInt();
        this.status = in.readInt();
        this.refuseReason = in.readParcelable(Object.class.getClassLoader());
        this.delFlag = in.readInt();
        this.createTime = in.readString();
        this.updateTime = in.readParcelable(Object.class.getClassLoader());
        this.phone = in.readString();
        this.userName = in.readString();
        this.phoneHide = in.readParcelable(Object.class.getClassLoader());
        this.profile = in.readString();
        this.areaList = in.createStringArrayList();
        this.applyVos = new ArrayList<ApplyVosBean>();
        in.readList(this.applyVos, ApplyVosBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<RecruitmentEntity> CREATOR = new Parcelable.Creator<RecruitmentEntity>() {
        @Override
        public RecruitmentEntity createFromParcel(Parcel source) {
            return new RecruitmentEntity(source);
        }

        @Override
        public RecruitmentEntity[] newArray(int size) {
            return new RecruitmentEntity[size];
        }
    };
}
