package com.qujiali.jiaogegongren.ui.me.activity;

import com.qujiali.jiaogegongren.bean.AttentionStautsEntity;

public interface IQueryAttentionView {

    void queryAttentionSuccess(AttentionStautsEntity attentionStautsEntity);

    void queryAttentionFail(String msg);

}
