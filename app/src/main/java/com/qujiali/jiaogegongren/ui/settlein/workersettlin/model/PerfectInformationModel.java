package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model;

import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HeaderUtils;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.IPerfectInformationModel;

import java.util.Map;

public class PerfectInformationModel extends BaseModel implements IPerfectInformationModel {


    @Override
    public void sendPerfectInformationData(Map<String, Object> objectMap, OnCallbackListener onCallbackListener) {
        HttpProvider.doPost(GlobalConstants.APP_WORKER_PERFECT_INFORMATION, objectMap, responseText -> {
            executeCallback(responseText,null,onCallbackListener);
        });
    }

    @Override
    public void sendCompanyPerfectInformationData(Map<String, Object> objectMap, OnCallbackListener onCallbackListener) {
        HttpProvider.doPost(GlobalConstants.APP_ENTERPRISE_PERFECT_INFORMATION,objectMap,responseText -> {
            executeCallback(responseText,null,onCallbackListener);
        });
    }
}
