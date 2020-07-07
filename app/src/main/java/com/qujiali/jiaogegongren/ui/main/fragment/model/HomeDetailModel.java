package com.qujiali.jiaogegongren.ui.main.fragment.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IHomeDetailModel;

public class HomeDetailModel extends BaseModel implements IHomeDetailModel {


    @Override
    public void queryWorker(String id, IBaseModel.OnCallbackListener onCallbackListener) {
        HttpProvider.doGet(GlobalConstants.APP_MOBILE_ANON_WORKER + id, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, new TypeToken<ResponseEntity<SettlelnEntity>>() {
                }.getType(), onCallbackListener);
            }
        });
    }

    @Override
    public void queryEnterprise(String id, IBaseModel.OnCallbackListener onCallbackListener) {
        HttpProvider.doGet(GlobalConstants.APP_MOBILE_ANON_ENTERPRISE + id, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, new TypeToken<ResponseEntity<SettlelnCompanyEntity>>() {
                }.getType(), onCallbackListener);
            }
        });
    }
}
