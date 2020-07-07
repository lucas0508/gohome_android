package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.LabelEntity;
import com.qujiali.jiaogegongren.bean.SkillCertificationEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseDataEntity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISkillCertificationListModel;

public class SkillCertificationListModel extends BaseModel implements ISkillCertificationListModel {


    @Override
    public void getSkillCertificationList(OnCallbackDataListener onCallbackListener) {
        HttpProvider.doGet(GlobalConstants.APP_SKILLCERTIFICATIONLIST, responseText -> {
            executeDataCallback(responseText, new TypeToken<ResponseDataEntity<SkillCertificationEntity>>() {
            }.getType(), onCallbackListener);
        });
    }
}
