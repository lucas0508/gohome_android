package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISettleInViewModel;

import java.util.Map;

public class SettleInViewModel extends BaseModel implements ISettleInViewModel {

    @Override
    public void getSettleInWorkerData(OnCallbackListener onCallbackListener) {
        HttpProvider.doGet(GlobalConstants.APP_WORKER_GETINFO, responseText -> {
            executeCallback(responseText,new TypeToken<ResponseEntity<SettlelnEntity>>() {
            }.getType(),onCallbackListener);
        });
    }

    @Override
    public void getSettleInExperienceData(OnCallbackListener onCallbackListener) {
        HttpProvider.doGet(GlobalConstants.APP_ENTERPRISE_GETINFO,responseText -> {
            executeCallback(responseText,new TypeToken<ResponseEntity<SettlelnCompanyEntity>>() {
            }.getType(),onCallbackListener);
        });
    }
}
