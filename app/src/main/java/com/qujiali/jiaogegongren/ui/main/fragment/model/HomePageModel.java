package com.qujiali.jiaogegongren.ui.main.fragment.model;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IHomePageModel;

import java.util.HashMap;
import java.util.Map;

public class HomePageModel extends BaseModel implements IHomePageModel {

    @Override
    public void getHomePageData(Map<String, Object> map, String cityCode, String searchContent, IBaseModel.OnCallbackListener listener) {
        String url="";
        if (!TextUtils.isEmpty(searchContent)) {
            url = GlobalConstants.App_HOME_PAGE_DATA + HttpProvider.getUrlDataByMap(map)
                    +"&serviceArea="+cityCode +"&keyword="+searchContent;
        }else {
            url = GlobalConstants.App_HOME_PAGE_DATA + HttpProvider.getUrlDataByMap(map)
                    +"&serviceArea="+cityCode;
        }
        HttpProvider.doGet(url, responseText -> {
            executeCallback(responseText, new TypeToken<ResponseEntity<HomeEntity>>() {
            }.getType(), listener);
        });
    }
}