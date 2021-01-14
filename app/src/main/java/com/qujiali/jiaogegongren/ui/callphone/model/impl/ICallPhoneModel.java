package com.qujiali.jiaogegongren.ui.callphone.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

public interface ICallPhoneModel extends IBaseModel{

    void queryCallPhone(String cityCode, OnCallbackListener onCallbackListener);

}
