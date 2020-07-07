package com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter;

import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.PerfectInformationModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.IPerfectInformationModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.IPerfectInformationView;

import java.util.Map;

public class PerfectInformationPresenter extends BasePresenter {

    private IPerfectInformationView iPerfectInformationView;

    private IPerfectInformationModel iPerfectInformationModel = new PerfectInformationModel();


    public PerfectInformationPresenter(IPerfectInformationView iPerfectInformationView) {
        this.iPerfectInformationView = iPerfectInformationView;
    }

    public void sendPerfectInformation(Map<String,Object> objectMap){
        iPerfectInformationModel.sendPerfectInformationData(objectMap,res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iPerfectInformationView.sendPerfectInformationDataSuccess();
            }else {
                iPerfectInformationView.sendPerfectInformationDataFail(res.getMsg());
            }
        });
    }


    public void sendCompanyPerfectInformation(Map<String,Object> objectMap){
        iPerfectInformationModel.sendCompanyPerfectInformationData(objectMap,res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iPerfectInformationView.sendCompanyPerfectInformationDataSuccess();
            }else {
                iPerfectInformationView.sendCompanyPerfectInformationDataFail(res.getMsg());
            }
        });
    }
}
