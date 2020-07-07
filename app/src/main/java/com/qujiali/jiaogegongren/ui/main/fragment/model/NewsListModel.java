package com.qujiali.jiaogegongren.ui.main.fragment.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.NewsEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.INewsListModel;
import java.util.Map;

/**
 * @author QiZai
 * @desc
 * @date 2018/7/20 17:27
 */

public class NewsListModel extends BaseModel implements INewsListModel {
    @Override
    public void queryNews(Map<String, Object> data, final IBaseModel.OnCallbackListener listener) {
        String URL = GlobalConstants.NEWS_LIST + HttpProvider.getUrlDataByMap(data);
        HttpProvider.doGet(URL, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, new TypeToken<ResponseEntity<NewsEntity>>() {
                }.getType(), listener);
            }
        });
    }
}
