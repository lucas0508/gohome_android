package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface ISettleInCancelIdentityModel extends IBaseModel {


    void getCancelIdentityWorkerData(OnCallbackListener onCallbackListener);

    void getCancelIdentityExperienceData(OnCallbackListener onCallbackListener);



}
