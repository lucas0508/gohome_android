package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model;

import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.IProjectExperienceModel;

import java.util.Map;

public class ProjectExperienceModel extends BaseModel implements IProjectExperienceModel {


    @Override
    public void sendPProjectExperienceData(Map<String, Object> objectMap, OnCallbackListener onCallbackListener) {
        HttpProvider.doPost(GlobalConstants.APP_WORKER_ADD_PROJECT,objectMap,responseText -> {
            executeCallback(responseText,null,onCallbackListener);
        });
    }

    @Override
    public void sendCompanyProjectExperienceData(Map<String, Object> objectMap, OnCallbackListener onCallbackListener) {
        HttpProvider.doPost(GlobalConstants.APP_ENTERPRISE_ADD_PROJECT,objectMap,responseText -> {
            executeCallback(responseText,null,onCallbackListener);
        });
    }
}
