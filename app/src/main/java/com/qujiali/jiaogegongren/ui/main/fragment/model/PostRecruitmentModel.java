package com.qujiali.jiaogegongren.ui.main.fragment.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.LabelEntity;
import com.qujiali.jiaogegongren.bean.PostRecruitEntity;
import com.qujiali.jiaogegongren.bean.RealNameEntity;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseDataEntity;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IPostRecruitmentModel;

import java.util.Map;

public class PostRecruitmentModel extends BaseModel implements IPostRecruitmentModel {

    @Override
    public void queryLabelData(IBaseModel.OnCallbackDataListener listener) {
        HttpProvider.doGet(GlobalConstants.APP_RECRUITMENT_LABEL_LIST, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeDataCallback(responseText, new TypeToken<ResponseDataEntity<LabelEntity>>() {
                }.getType(), listener);
            }
        });
    }

    @Override
    public void submitWorkersData(Map<String, Object> stringObjectMap, IBaseModel.OnCallbackListener listener) {
        HttpProvider.doPost(GlobalConstants.APP_RECRUITMENT_POST_WORKER_INFORMATION, stringObjectMap,new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, new TypeToken<ResponseEntity<RecruitmentEntity>>() {
                }.getType() , listener);
            }
        });
    }

    @Override
    public void queryWorkersData(int id, IBaseModel.OnCallbackListener listener) {
        HttpProvider.doGet(GlobalConstants.APP_RECRUITMENT_POST_WORKER_INFORMATION+"/"+id ,new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, new TypeToken<ResponseEntity<PostRecruitEntity>>() {
                }.getType(), listener);
            }
        });
    }


}
