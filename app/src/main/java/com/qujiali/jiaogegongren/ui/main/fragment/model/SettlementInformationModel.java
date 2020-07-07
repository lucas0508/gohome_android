package com.qujiali.jiaogegongren.ui.main.fragment.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.AttentionEntity;
import com.qujiali.jiaogegongren.bean.SettlementEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.ISettlementInformationModel;

import java.util.Map;

public class SettlementInformationModel extends BaseModel implements ISettlementInformationModel {


    @Override
    public void loadSettlementInformationData(OnCallbackListener listener) {
        HttpProvider.doGet(GlobalConstants.APP_QUEERY_ROLE, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText,new TypeToken<ResponseEntity<SettlementEntity>>() {
                }.getType(),listener);
            }
        });
    }
}
