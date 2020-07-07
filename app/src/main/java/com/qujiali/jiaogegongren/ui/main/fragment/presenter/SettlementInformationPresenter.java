package com.qujiali.jiaogegongren.ui.main.fragment.presenter;

import com.qujiali.jiaogegongren.bean.SettlementEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.SettlementInformationModel;
import com.qujiali.jiaogegongren.ui.main.fragment.view.ISettlementInformationView;

public class SettlementInformationPresenter extends BasePresenter {

    private ISettlementInformationView iSettlementInformationView;

    private SettlementInformationModel settlementInformationModel = new SettlementInformationModel();

    public SettlementInformationPresenter(ISettlementInformationView iSettlementInformationView) {
        this.iSettlementInformationView = iSettlementInformationView;
    }

    public void querySettlementInformation(){
        settlementInformationModel.loadSettlementInformationData(new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    iSettlementInformationView.loadSettlementInformationSuccess((SettlementEntity) res.getData());
                }else {
                    iSettlementInformationView.loadSettlementInformationFail(res.getCode(),res.getMsg());
                }
            }
        });
    }
}
