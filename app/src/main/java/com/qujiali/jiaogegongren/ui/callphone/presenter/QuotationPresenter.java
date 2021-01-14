package com.qujiali.jiaogegongren.ui.callphone.presenter;

import com.qujiali.jiaogegongren.bean.CallPhoneEntity;
import com.qujiali.jiaogegongren.bean.QuotationEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.callphone.model.CallPhoneModel;
import com.qujiali.jiaogegongren.ui.callphone.model.QuotationModel;
import com.qujiali.jiaogegongren.ui.callphone.model.impl.ICallPhoneModel;
import com.qujiali.jiaogegongren.ui.callphone.model.impl.IQuotationModel;
import com.qujiali.jiaogegongren.ui.callphone.view.ICallPhoneView;
import com.qujiali.jiaogegongren.ui.callphone.view.IQuotationView;

public class QuotationPresenter extends BasePresenter {

    private IQuotationView iQuotationView;

    private IQuotationModel iQuotationModel = new QuotationModel();

    public QuotationPresenter(IQuotationView iQuotationView) {
        this.iQuotationView = iQuotationView;
    }

    public void queryQuotation(String cityCode){
        iQuotationModel.queryQuotationData(cityCode,res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iQuotationView.queryQuotationSuccess(res.getData());
            }else {
                iQuotationView.queryQuotationFail(res.getMsg());
            }
        });
    }
}
