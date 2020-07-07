package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface ISkillIntroductionModel extends IBaseModel {

    void sendSkillIntroductionData(Map<String, Object> objectMap, OnCallbackListener onCallbackListener);

    void sendCompanySkillIntroductionData(Map<String, Object> objectMap,OnCallbackListener onCallbackListener);


}
