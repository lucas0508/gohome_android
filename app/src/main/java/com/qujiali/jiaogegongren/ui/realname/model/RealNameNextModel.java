package com.qujiali.jiaogegongren.ui.realname.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.NewsEntity;
import com.qujiali.jiaogegongren.bean.RealNameEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.realname.model.impl.IRealNameNextModel;

import java.util.Map;

/**
 * Created by QiZai on 2018/10/13.
 */

public class RealNameNextModel extends BaseModel implements IRealNameNextModel {

    @Override
    public void queryCertificationInfo(final IBaseModel.OnCallbackListener listener) {
        HttpProvider.doGet(GlobalConstants.APP_USER_AUTHENTICATION, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText,  new TypeToken<ResponseEntity<RealNameEntity>>() {
                }.getType(), listener);
            }
        });
    }

    @Override
    public void uploadCertificationInfo(Map<String, Object> data, final IBaseModel.OnCallbackListener listener) {
        HttpProvider.doPost(GlobalConstants.APP_USER_AUTHENTICATION, data, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, null, listener);
            }
        });
    }
}
