package com.qujiali.jiaogegongren.ui.main.fragment.model;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IHomePageModel;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IRecruitmentModel;

import java.util.HashMap;
import java.util.Map;

public class RecruitmentModel extends BaseModel implements IRecruitmentModel {

    @Override
    public void loadRecruitmentData(Map<String, Object> map, String cityCode, String searchContent,String related, IBaseModel.OnCallbackListener listener) {
        if (!TextUtils.isEmpty(searchContent)){
            map.put("keyword",searchContent);
            map.put("related",related);
        }else {
            map.put("related",related);
            map.put("keyword","");
        }
        map.put("location",cityCode);
        Logger.d("--------点击搜索数据--->",map);
        Log.e("点击搜", "loadRecruitmentData: "+GlobalConstants.APP_RECRUITMENT_LIST+HttpProvider.getUrlDataByMap(map) );
        HttpProvider.doGet(GlobalConstants.APP_RECRUITMENT_LIST+HttpProvider.getUrlDataByMap(map), responseText -> {
            executeCallback(responseText,new TypeToken<ResponseEntity<RecruitmentEntity>>() {
            }.getType(),listener);
        });
    }
}
