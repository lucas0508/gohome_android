package com.qujiali.jiaogegongren.ui.me.presenter;

import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.me.model.AttentionCancelModel;
import com.qujiali.jiaogegongren.ui.me.model.impl.IAttentionCancelModel;
import com.qujiali.jiaogegongren.ui.me.model.impl.IAttentionCancelView;

/**
 * 取消关注
 */
public class AttentionCancelPresenter extends BasePresenter {


    private IAttentionCancelView iattentionCancelView;

    private IAttentionCancelModel iAttentionCancelModel =new AttentionCancelModel();

    public AttentionCancelPresenter(IAttentionCancelView iattentionCancelView) {
        this.iattentionCancelView = iattentionCancelView;
    }

    public void cancelAttention(String id,String role){
        iAttentionCancelModel.cancelAttention(id, role, new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    iattentionCancelView.cancelAttentionSuccess();
                }else {
                    iattentionCancelView.cancelAttentionFail(res.getMsg());
                }
            }
        });
    }

    public void addAttention(int id,String role){
        iAttentionCancelModel.addAttention(id, role, new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    iattentionCancelView.addAttentionSuccess();
                }else {
                    iattentionCancelView.addAttentionFail(res.getMsg());
                }
            }
        });
    }
}
