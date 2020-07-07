package com.qujiali.jiaogegongren.ui.me.presenter;

import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.me.activity.IPostTabView;
import com.qujiali.jiaogegongren.ui.me.model.PostTabModel;
import com.qujiali.jiaogegongren.ui.me.model.impl.IPostTabModel;

import java.util.Map;

public class PostTabPresenter extends BasePresenter {


    private IPostTabView iPostTabView;

    private IPostTabModel iPostTabModel = new PostTabModel();

    public PostTabPresenter(IPostTabView iPostTabView) {
        this.iPostTabView = iPostTabView;
    }

    public void queryPostData(String status, Map<String, Object> stringObjectMap) {
        iPostTabModel.queryPostData(status, stringObjectMap, res -> {
            if (HttpProvider.isSuccessful(res.getCode())) {
                iPostTabView.queryMyAttentionSuccess(res.getRows());
            } else {
                iPostTabView.queryMyAttentionFail(res.getMsg());
            }
        });
    }

    public void setOfficeData(int id) {
        iPostTabModel.setOfficeData(id, res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iPostTabView.setOfflineSuccess();
            }else {
                iPostTabView.setOfflineFail(res.getMsg());
            }
        });
    }
}

