package com.qujiali.jiaogegongren.ui.main.activity;

import com.qujiali.jiaogegongren.bean.RecruitmentEntity;

public interface IRecruitmentDetailView {

    void queryRecruitmentDetailDataSuccess(RecruitmentEntity recruitmentEntity);

    void queryRecruitmentDetailDataFail(String info);

    void postApplyDataSuccess();

    void postApplyDataFail(String info);


}
