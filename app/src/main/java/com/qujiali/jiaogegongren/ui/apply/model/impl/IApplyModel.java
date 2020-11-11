package com.qujiali.jiaogegongren.ui.apply.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface IApplyModel extends IBaseModel {


    void queryApplyInfo(int id, Map<String, Object> objectMap, OnCallbackListener listener);

}
