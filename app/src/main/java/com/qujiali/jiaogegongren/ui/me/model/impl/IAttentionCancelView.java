package com.qujiali.jiaogegongren.ui.me.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

public interface IAttentionCancelView extends IBaseModel {

    void cancelAttentionSuccess();

    void cancelAttentionFail(String msg);

    void addAttentionSuccess();

    void addAttentionFail(String msg);

}
