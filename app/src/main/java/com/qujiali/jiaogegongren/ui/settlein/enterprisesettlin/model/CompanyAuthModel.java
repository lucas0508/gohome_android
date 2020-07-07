package com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.CompanyEntity;
import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.model.impl.ICompanyAuthModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISkillCertificationModel;

import java.util.HashMap;
import java.util.Map;

public class CompanyAuthModel extends BaseModel implements ICompanyAuthModel {


    @Override
    public void sendCompanyAuth(String name,String address, String picture, OnCallbackListener onCallbackListener) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("name", name);
        objectMap.put("address", address);
        objectMap.put("picture", picture);
        HttpProvider.doPost(GlobalConstants.APP_USER_COMPANY_AUTHENTICATION, objectMap, responseText -> {
            executeCallback(responseText,null,onCallbackListener);
        });
    }

    @Override
    public void queryCompanyAuth( OnCallbackListener onCallbackListener) {
        HttpProvider.doGet(GlobalConstants.APP_USER_COMPANY_AUTHENTICATION,  responseText -> {
            executeCallback(responseText,new TypeToken<ResponseEntity<CompanyEntity>>() {
            }.getType(),onCallbackListener);

        });
    }
}
