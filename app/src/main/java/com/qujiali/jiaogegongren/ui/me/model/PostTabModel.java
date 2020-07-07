package com.qujiali.jiaogegongren.ui.me.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.AttentionEntity;
import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.me.model.impl.IPostTabModel;

import java.util.Map;

public class PostTabModel extends BaseModel implements IPostTabModel {


    @Override
    public void queryPostData(String status, Map<String, Object> stringObjectMap, OnCallbackListener onCallbackListener) {
        stringObjectMap.put("status",status);
        HttpProvider.doGet(GlobalConstants.APP_MY_POST + HttpProvider.getUrlDataByMap(stringObjectMap), responseText -> {
            executeCallback(responseText, new TypeToken<ResponseEntity<RecruitmentEntity>>() {
            }.getType(),onCallbackListener);
        });
    }

    @Override
    public void setOfficeData(int id, OnCallbackListener onCallbackListener) {
        HttpProvider.doPost(GlobalConstants.APP_WORKERS_INSERTINGWINDING +id,null, responseText -> {
            executeCallback(responseText, null,onCallbackListener);
        });
    }
}
