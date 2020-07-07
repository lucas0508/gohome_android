package com.qujiali.jiaogegongren.ui.me.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

public interface IAttentionQueryModel extends IBaseModel {


    void queryAttention(int id, String role, OnCallbackListener listener);

}
