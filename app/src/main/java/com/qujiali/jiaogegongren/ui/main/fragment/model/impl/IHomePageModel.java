package com.qujiali.jiaogegongren.ui.main.fragment.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface IHomePageModel {


    void getHomePageData(Map<String,Object> map, String cityCode, String searchContent, IBaseModel.OnCallbackListener listener);

}
