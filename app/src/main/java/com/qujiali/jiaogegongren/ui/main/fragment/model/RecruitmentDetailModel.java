package com.qujiali.jiaogegongren.ui.main.fragment.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IRecruitmentDetailModel;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IRecruitmentModel;

import java.util.HashMap;
import java.util.Map;

import static com.qujiali.jiaogegongren.common.global.GlobalConstants.APP_APPLY_ADD;
import static com.qujiali.jiaogegongren.common.global.GlobalConstants.APP_RECRUITMENT_DETAIL_LIST;

public class RecruitmentDetailModel extends BaseModel implements IRecruitmentDetailModel {


    @Override
    public void loadRecruitmentData(int id, IBaseModel.OnCallbackListener listener) {

        HttpProvider.doGet(APP_RECRUITMENT_DETAIL_LIST + id, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, new TypeToken<ResponseEntity<RecruitmentEntity>>() {
                }.getType(), listener);
            }
        });
    }

    @Override
    public void postApplyData(int recruitId, IBaseModel.OnCallbackListener listener) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("recruitId", recruitId);
        HttpProvider.doPost(APP_APPLY_ADD + HttpProvider.getUrlDataByMap(objectMap), null, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, null, listener);
            }
        });
    }
}
