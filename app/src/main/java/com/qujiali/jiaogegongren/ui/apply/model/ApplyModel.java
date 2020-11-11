package com.qujiali.jiaogegongren.ui.apply.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.bean.UserInfoEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.apply.model.impl.IApplyModel;

import java.util.HashMap;
import java.util.Map;

import static com.qujiali.jiaogegongren.common.global.GlobalConstants.APP_APPLY_ADD;
import static com.qujiali.jiaogegongren.common.global.GlobalConstants.APP_APPLY_LIST;

public class ApplyModel extends BaseModel implements IApplyModel {

    @Override
    public void queryApplyInfo(int id, Map<String, Object> objectMap, OnCallbackListener listener) {
        objectMap.put("recruitId", id);
        HttpProvider.doGet(APP_APPLY_LIST + HttpProvider.getUrlDataByMap(objectMap), new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, new TypeToken<ResponseEntity<RecruitmentEntity.ApplyVosBean>>() {
                }.getType(), listener);
            }
        });
    }
}
