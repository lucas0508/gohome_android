package com.qujiali.jiaogegongren.ui.settlein.workersettlin.view;

import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;

public interface ISettleInView {

    void getSettleInDataSuccess(SettlelnEntity settlelnEntity);

    void getSettleInDataFail(String info);

    void getSettleInCompanyDataSuccess(SettlelnCompanyEntity settlelnEntity);

    void getSettleInCompanyDataFail(String info);


}
