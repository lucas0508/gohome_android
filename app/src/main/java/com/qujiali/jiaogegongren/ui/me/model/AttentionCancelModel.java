package com.qujiali.jiaogegongren.ui.me.model;

import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.me.model.impl.IAttentionCancelModel;

public class AttentionCancelModel extends BaseModel implements IAttentionCancelModel {


    @Override
    public void cancelAttention(String id, String role, OnCallbackListener listener) {
        String url = "";
        if (role.equals("WORKER")) {
            url = GlobalConstants.APP_WORKER_CANCELATTENTION;
        } else if (role.equals("ENTERPRISE")) {
            url = GlobalConstants.APP_ENTERPRISE_CANCELATTENTION;
        }
        HttpProvider.doDelete(url + id, null, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, null, listener);
            }
        });
    }

    @Override
    public void addAttention(int id, String role, OnCallbackListener listener) {
        String url = "";
        if (role.equals("WORKER")) {
            url = GlobalConstants.APP_WORKER_CANCELATTENTION;
        } else if (role.equals("ENTERPRISE")) {
            url = GlobalConstants.APP_ENTERPRISE_CANCELATTENTION;
        }
        HttpProvider.doPost(url + id, null, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, null, listener);
            }
        });
    }
}
