package com.qujiali.jiaogegongren.ui.banner.model;


import com.qujiali.jiaogegongren.common.base.IBaseModel;

public interface IBannerModel {


    /**
     * 查询banner数据
     *
     * @param cityCode
     * @param listener
     */
    void queryBannerDataList(String cityCode, String type,String banner_location, IBaseModel.OnCallbackListener listener);

}
