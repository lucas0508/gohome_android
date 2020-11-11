package com.qujiali.jiaogegongren.ui.apply.view;

import com.qujiali.jiaogegongren.bean.RealNameEntity;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;

import java.util.List;

public interface IApplyView {


    void queryApplyDataSuccess(List<RecruitmentEntity.ApplyVosBean> applyVosBean);


    void queryApplyDataFail(String info);
}
