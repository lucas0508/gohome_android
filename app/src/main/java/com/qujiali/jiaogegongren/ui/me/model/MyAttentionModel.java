package com.qujiali.jiaogegongren.ui.me.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.AttentionEntity;
import com.qujiali.jiaogegongren.bean.UserInfoEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IMineFragmentModel;
import com.qujiali.jiaogegongren.ui.me.model.impl.IMyAttentionModel;

import java.util.Map;

public class MyAttentionModel extends BaseModel implements IMyAttentionModel {


    @Override
    public void queryMyAttention(Map<String,Object> stringObjectMap, IBaseModel.OnCallbackListener onCallbackListener) {
        HttpProvider.doGet(GlobalConstants.APP_QUERY_WATCH_LIST +HttpProvider.getUrlDataByMap(stringObjectMap), new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText,new TypeToken<ResponseEntity<AttentionEntity>>() {
                }.getType(),onCallbackListener);
            }
        });
    }
}
