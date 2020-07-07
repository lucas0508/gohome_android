package com.qujiali.jiaogegongren.ui.me.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface IMyAttentionModel extends IBaseModel{

    void queryMyAttention(Map<String,Object> stringObjectMap, IBaseModel.OnCallbackListener onCallbackListener);

}
