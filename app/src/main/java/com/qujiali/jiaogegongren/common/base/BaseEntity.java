package com.qujiali.jiaogegongren.common.base;



import com.qujiali.jiaogegongren.common.utils.TimeUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @author QiZai
 * @desc
 * @date 2018/4/11
 */

public class BaseEntity implements Serializable {

    private String id;
    private Integer enable;
    private String remark;
    private Long createBy;
    private Long createTime;
    private Long updateBy;
    private Long updateTime;

    private String keyword;
    private String orderBy;
    private List<Long> ids;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public String getCreateTimeLabel() {
        if (createTime != null)
            return TimeUtils.formatDateByTimeMillis(createTime, TimeUtils.TIME_TYPE_YMD_HM);
        return null;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", enable=" + enable +
                ", remark='" + remark + '\'' +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", keyword='" + keyword + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", ids=" + ids +
                '}';
    }
}
