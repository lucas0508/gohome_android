package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model;

import com.bumptech.glide.load.ImageHeaderParser;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISkillCertificationModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISkillIntroductionModel;

import java.util.HashMap;
import java.util.Map;

public class SkillCertificationModel extends BaseModel implements ISkillCertificationModel {


    @Override
    public void sendSkillCertification(String name, String picture, OnCallbackListener onCallbackListener) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("name", name);
        objectMap.put("pictrues", picture);
        HttpProvider.doPost(GlobalConstants.APP_SETTLED_AUTHENTICATION, objectMap, responseText -> {
            executeCallback(responseText,null,onCallbackListener);
        });
    }
}
