package com.qujiali.jiaogegongren.ui.me.activity;

import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;

import java.util.List;

public interface IPostTabView {

    void queryMyAttentionSuccess(List<RecruitmentEntity> data);

    void queryMyAttentionFail(String info);

    void setOfflineSuccess();

    void setOfflineFail(String info);


}
