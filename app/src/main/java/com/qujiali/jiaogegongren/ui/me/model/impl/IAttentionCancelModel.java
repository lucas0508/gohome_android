package com.qujiali.jiaogegongren.ui.me.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

public interface IAttentionCancelModel extends IBaseModel {


    void cancelAttention(String id,String role,OnCallbackListener listener);

    void addAttention(int id,String role,OnCallbackListener listener);

}
