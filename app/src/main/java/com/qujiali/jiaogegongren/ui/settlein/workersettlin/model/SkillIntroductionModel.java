package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model;

import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISkillIntroductionModel;

import java.util.Map;

public class SkillIntroductionModel extends BaseModel implements ISkillIntroductionModel {


    @Override
    public void sendSkillIntroductionData(Map<String, Object> objectMap, OnCallbackListener onCallbackListener) {
        HttpProvider.doPost(GlobalConstants.APP_WORKER_SKILL,objectMap,responseText -> {
            executeCallback(responseText,null,onCallbackListener);
        });
    }

    @Override
    public void sendCompanySkillIntroductionData(Map<String, Object> objectMap, OnCallbackListener onCallbackListener) {
        HttpProvider.doPost(GlobalConstants.APP_ENTERPRISE_SKILL,objectMap,responseText -> {
            executeCallback(responseText,null,onCallbackListener);
        });
    }
}
