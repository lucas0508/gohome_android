package com.qujiali.jiaogegongren.ui.main.fragment.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.bean.UserInfoEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IMineFragmentModel;

import java.util.Map;

public class MineFragmentModel extends BaseModel implements IMineFragmentModel {
    @Override
    public void queryUserInfoData(IBaseModel.OnCallbackListener onCallbackListener) {
        HttpProvider.doGet(GlobalConstants.APP_GETUSERINFO, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText,new TypeToken<ResponseEntity<UserInfoEntity>>() {
                }.getType(),onCallbackListener);
            }
        });
    }

    @Override
    public void submitUserInfoData(Map<String, Object> objectMap, IBaseModel.OnCallbackListener onCallbackListener) {
        HttpProvider.doPut(GlobalConstants.APP_MODIFY_USER_INFORMATION,objectMap, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText,null,onCallbackListener);
            }
        });
    }

}
