package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface IPerfectInformationModel extends IBaseModel {

    void sendPerfectInformationData(Map<String, Object> objectMap,OnCallbackListener onCallbackListener);


    void sendCompanyPerfectInformationData(Map<String, Object> objectMap,OnCallbackListener onCallbackListener);

}
