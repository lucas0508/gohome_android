package com.qujiali.jiaogegongren.ui.main.fragment.presenter;

import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.activity.IRecruitmentDetailView;
import com.qujiali.jiaogegongren.ui.main.fragment.model.RecruitmentDetailModel;
import com.qujiali.jiaogegongren.ui.main.fragment.model.RecruitmentModel;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IRecruitmentDetailModel;

public class RecruitmentDetailPresenter extends BasePresenter {

    private IRecruitmentDetailView iRecruitmentDetailView;

    private IRecruitmentDetailModel iRecruitmentDetailModel = new RecruitmentDetailModel();

    public RecruitmentDetailPresenter(IRecruitmentDetailView iRecruitmentDetailView) {
        this.iRecruitmentDetailView = iRecruitmentDetailView;
    }

    public void queryRecruitmentDetailData(int id) {
        iRecruitmentDetailModel.loadRecruitmentData(id, new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())) {
                    iRecruitmentDetailView.queryRecruitmentDetailDataSuccess((RecruitmentEntity) res.getData());
                } else {
                    iRecruitmentDetailView.queryRecruitmentDetailDataFail(res.getMsg());
                }
            }
        });

    }
}
