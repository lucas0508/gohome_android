package com.qujiali.jiaogegongren.ui.apply.presenter;

import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.apply.model.ApplyModel;
import com.qujiali.jiaogegongren.ui.apply.model.impl.IApplyModel;
import com.qujiali.jiaogegongren.ui.apply.view.IApplyView;

import java.util.Map;

public class ApplyPresenter extends BasePresenter {



    private IApplyView iApplyView ;

    private IApplyModel  iApplyModel = new ApplyModel();


    public ApplyPresenter(IApplyView iApplyView) {
        this.iApplyView = iApplyView;
    }


    public void queryApplyData(int id, Map<String,Object> objectMap){
        iApplyModel.queryApplyInfo(id, objectMap, new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    iApplyView.queryApplyDataSuccess(res.getRows());
                }else {
                    iApplyView.queryApplyDataFail(res.getMsg());
                }
            }
        });
    }
}
