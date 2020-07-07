package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model;

import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISettleInCancelIdentityModel;

import java.util.Map;

public class SettleInCancelIdentityModel extends BaseModel implements ISettleInCancelIdentityModel {


    @Override
    public void getCancelIdentityWorkerData(OnCallbackListener onCallbackListener) {
        HttpProvider.doDelete(GlobalConstants.APP_SETTLED_WORKER, null, responseText -> {
            executeCallback(responseText, null, onCallbackListener);
        });
    }

    @Override
    public void getCancelIdentityExperienceData(OnCallbackListener onCallbackListener) {
        HttpProvider.doDelete(GlobalConstants.APP_SETTLED_ENTERPRISE, null, responseText -> {
            executeCallback(responseText, null, onCallbackListener);
        });
    }
}
