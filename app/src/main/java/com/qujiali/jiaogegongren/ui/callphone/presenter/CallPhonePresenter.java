package com.qujiali.jiaogegongren.ui.callphone.presenter;

import com.qujiali.jiaogegongren.bean.CallPhoneEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.callphone.model.CallPhoneModel;
import com.qujiali.jiaogegongren.ui.callphone.model.impl.ICallPhoneModel;
import com.qujiali.jiaogegongren.ui.callphone.view.ICallPhoneView;

public class CallPhonePresenter  extends BasePresenter {

    private ICallPhoneView iCallPhoneView;

    private ICallPhoneModel iCallPhonelModel = new CallPhoneModel();

    public CallPhonePresenter(ICallPhoneView iCallPhoneView) {
        this.iCallPhoneView = iCallPhoneView;
    }

    public void queryWorker(String cityCode){
        iCallPhonelModel.queryCallPhone(cityCode,res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iCallPhoneView.queryCallPhoneSuccess((CallPhoneEntity) res.getData());
            }else {
                iCallPhoneView.queryCallPhoneFail(res.getMsg());
            }
        });
    }
}
