package com.qujiali.jiaogegongren.ui.callphone.view;

import com.qujiali.jiaogegongren.bean.QuotationEntity;

import java.util.List;

public interface IQuotationView {

    void queryQuotationSuccess(List<QuotationEntity> quotationEntity);

    void queryQuotationFail(String info);


}
