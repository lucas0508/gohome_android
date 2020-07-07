package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface ISettleInViewModel extends IBaseModel {

    void getSettleInWorkerData(OnCallbackListener onCallbackListener);

    void getSettleInExperienceData(OnCallbackListener onCallbackListener);

}
