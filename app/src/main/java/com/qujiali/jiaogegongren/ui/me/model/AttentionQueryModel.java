package com.qujiali.jiaogegongren.ui.me.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.AttentionStautsEntity;
import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.me.model.impl.IAttentionCancelModel;
import com.qujiali.jiaogegongren.ui.me.model.impl.IAttentionQueryModel;

public class AttentionQueryModel extends BaseModel implements IAttentionQueryModel {


    @Override
    public void queryAttention(int id, String role, OnCallbackListener listener) {
        String url = "";
        if (role.equals("WORKER")) {
            url= GlobalConstants.APP_WORKER_WORKER_GETINFO;
        } else if (role.equals("ENTERPRISE")) {
            url=GlobalConstants.APP_WORKER_ENTERPRISE_GETINFO;
        }
        HttpProvider.doGet(url+id,new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText,new TypeToken<ResponseEntity<AttentionStautsEntity>>() {
                }.getType(),listener);
            }
        });
    }
}
