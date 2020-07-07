package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface IProjectExperienceModel extends IBaseModel {

    void sendPProjectExperienceData(Map<String, Object> objectMap, OnCallbackListener onCallbackListener);

    void sendCompanyProjectExperienceData(Map<String, Object> objectMap,OnCallbackListener onCallbackListener);

}
