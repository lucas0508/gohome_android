package com.qujiali.jiaogegongren.ui.main.fragment.view;

import com.qujiali.jiaogegongren.bean.SettlementEntity;

public interface ISettlementInformationView {

    void loadSettlementInformationSuccess(SettlementEntity settlementEntity);


    void loadSettlementInformationFail(int code, String info);

}
