package com.qujiali.jiaogegongren.ui.main.fragment.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.VersionUpdateEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.view.IVersionUpdateModel;

public class VersionUpdateModel extends BaseModel implements IVersionUpdateModel {


    @Override
    public void updateVersion(final OnCallbackListener onCallbackListener) {


        HttpProvider.doGet(GlobalConstants.APP_VERSION_UPDATE, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                delayedExecuteCallback(responseText, new TypeToken<ResponseEntity<VersionUpdateEntity>>() {
                }.getType(), onCallbackListener);
            }
        });
    }
}