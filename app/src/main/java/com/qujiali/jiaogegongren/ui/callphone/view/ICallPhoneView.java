package com.qujiali.jiaogegongren.ui.callphone.view;

import com.qujiali.jiaogegongren.bean.CallPhoneEntity;
import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;

public interface ICallPhoneView {

    void queryCallPhoneSuccess(CallPhoneEntity callPhoneEntity);

    void queryCallPhoneFail(String info);


}
