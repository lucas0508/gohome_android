package com.qujiali.jiaogegongren.ui.me.activity;

import com.qujiali.jiaogegongren.bean.AttentionEntity;

import java.util.List;

public interface IMyAttentionView {


    void queryMyAttentionSuccess(List<AttentionEntity> data);

    void queryMyAttentionFail(String info);


}
