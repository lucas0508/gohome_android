package com.qujiali.jiaogegongren.ui.callphone.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

public interface IQuotationModel extends IBaseModel{

    void queryQuotationData(String cityCode, OnCallbackDataListener onCallbackListener);


}
