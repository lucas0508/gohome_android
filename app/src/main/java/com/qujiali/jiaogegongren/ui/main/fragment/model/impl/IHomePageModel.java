package com.qujiali.jiaogegongren.ui.main.fragment.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface IHomePageModel {


    void getHomePageData(Map<String,Object> map, String cityCode, String searchContent, String orderBy,IBaseModel.OnCallbackListener listener);

    void getHomeWorkTypeData(IBaseModel.OnCallbackDataListener onCallbackDataListener);

}
