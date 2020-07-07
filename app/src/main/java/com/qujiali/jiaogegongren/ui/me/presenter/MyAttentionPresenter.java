package com.qujiali.jiaogegongren.ui.me.presenter;

import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.me.activity.IMyAttentionView;
import com.qujiali.jiaogegongren.ui.me.model.MyAttentionModel;
import com.qujiali.jiaogegongren.ui.me.model.impl.IMyAttentionModel;

import java.util.Map;

public class MyAttentionPresenter extends BasePresenter {

    IMyAttentionView myAttentionView;

    IMyAttentionModel iMyAttentionModel = new MyAttentionModel();


    public MyAttentionPresenter(IMyAttentionView myAttentionView) {
        this.myAttentionView = myAttentionView;
    }

    public void queryAttentionData(Map<String,Object> stringObjectMap){
        iMyAttentionModel.queryMyAttention(stringObjectMap,new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    myAttentionView.queryMyAttentionSuccess(res.getRows());
                }else {
                    myAttentionView.queryMyAttentionFail(res.getMsg());
                }
            }
        });
    }
}
