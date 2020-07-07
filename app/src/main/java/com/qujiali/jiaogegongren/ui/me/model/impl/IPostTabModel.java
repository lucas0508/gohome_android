package com.qujiali.jiaogegongren.ui.me.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface IPostTabModel extends IBaseModel {


    void queryPostData(String status, Map<String, Object> stringObjectMap,
                       IBaseModel.OnCallbackListener onCallbackListener);

    void setOfficeData(int id, IBaseModel.OnCallbackListener onCallbackListener);

}
