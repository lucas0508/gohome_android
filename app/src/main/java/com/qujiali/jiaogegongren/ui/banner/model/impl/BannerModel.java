package com.qujiali.jiaogegongren.ui.banner.model.impl;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.BannerEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.banner.model.IBannerModel;

import java.util.HashMap;
import java.util.Map;

public class BannerModel extends BaseModel implements IBannerModel {


    @Override
    public void queryBannerDataList(String cityCode, String type,String banner_location, final IBaseModel.OnCallbackListener listener) {
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("cityCode",cityCode);
        objectMap.put("type",type);
        objectMap.put("location",banner_location);
        String URL = GlobalConstants.BANNER_LIST;
        HttpProvider.doPost(URL, objectMap, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText,null, listener);
            }
        });
    }
}
