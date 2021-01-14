package com.qujiali.jiaogegongren.ui.callphone.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.CallPhoneEntity;
import com.qujiali.jiaogegongren.bean.QuotationEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseDataEntity;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.callphone.model.impl.ICallPhoneModel;
import com.qujiali.jiaogegongren.ui.callphone.model.impl.IQuotationModel;

public class QuotationModel extends BaseModel implements IQuotationModel {


    @Override
    public void queryQuotationData(String cityCode, OnCallbackDataListener onCallbackListener) {
        HttpProvider.doGet(GlobalConstants.APP_QUOTATION , responseText -> {
            executeDataCallback(responseText, new TypeToken<ResponseDataEntity<QuotationEntity>>() {
            }.getType(), onCallbackListener);
        });
    }
}
