package com.qujiali.jiaogegongren.ui.callphone.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.CallPhoneEntity;
import com.qujiali.jiaogegongren.bean.HomeWorkerTypeEntity;
import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseDataEntity;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.callphone.model.impl.ICallPhoneModel;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IHomeDetailModel;

public class CallPhoneModel extends BaseModel implements ICallPhoneModel {


    @Override
    public void queryCallPhone(String cityCode, OnCallbackListener onCallbackListener) {
        HttpProvider.doGet(GlobalConstants.APP_CALLFORWORK + cityCode, responseText -> {
            executeCallback(responseText, new TypeToken<ResponseEntity<CallPhoneEntity>>() {
            }.getType(), onCallbackListener);
        });
    }
}
