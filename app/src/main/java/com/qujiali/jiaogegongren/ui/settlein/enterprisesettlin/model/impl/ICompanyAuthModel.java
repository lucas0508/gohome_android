package com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

public interface ICompanyAuthModel extends IBaseModel {


    void sendCompanyAuth(String name, String address, String picture, OnCallbackListener onCallbackListener);


    void queryCompanyAuth(OnCallbackListener onCallbackListener);

}
