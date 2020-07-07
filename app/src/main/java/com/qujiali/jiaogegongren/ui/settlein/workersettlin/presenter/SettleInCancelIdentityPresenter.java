package com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter;

import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.SettleInCancelIdentityModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISettleInCancelIdentityModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.ISettleInCancelIdentityView;

import java.util.HashMap;
import java.util.Map;

public class SettleInCancelIdentityPresenter extends BasePresenter {

    private ISettleInCancelIdentityView isettleInCancelIdentityView;


    private ISettleInCancelIdentityModel settleInCancelIdentityModel = new SettleInCancelIdentityModel();


    public SettleInCancelIdentityPresenter(ISettleInCancelIdentityView isettleInCancelIdentityView) {
        this.isettleInCancelIdentityView = isettleInCancelIdentityView;
    }

    public void cancelIdentityWorker(){

        settleInCancelIdentityModel.getCancelIdentityWorkerData(res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                isettleInCancelIdentityView.getCancelIdentitySuccess();
            }else {
                isettleInCancelIdentityView.getCancelIdentityFail(res.getMsg());
            }
        });

    }

    public void cancelIdentityExperience(){
        settleInCancelIdentityModel.getCancelIdentityExperienceData(res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                isettleInCancelIdentityView.getCancelIdentityCompanySuccess();
            }else {
                isettleInCancelIdentityView.getCancelIdentityCompanyFail(res.getMsg());
            }
        });

    }
}
