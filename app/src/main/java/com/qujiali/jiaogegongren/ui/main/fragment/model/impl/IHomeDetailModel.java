package com.qujiali.jiaogegongren.ui.main.fragment.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

public interface IHomeDetailModel extends IBaseModel{

    void queryWorker(String id,IBaseModel.OnCallbackListener onCallbackListener);

    void queryEnterprise(String id,IBaseModel.OnCallbackListener onCallbackListener);

}
