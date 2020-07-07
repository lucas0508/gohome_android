package com.qujiali.jiaogegongren.ui.me.presenter;

import com.qujiali.jiaogegongren.bean.AttentionStautsEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.me.activity.IQueryAttentionView;
import com.qujiali.jiaogegongren.ui.me.model.AttentionQueryModel;
import com.qujiali.jiaogegongren.ui.me.model.impl.IAttentionQueryModel;

/**
 * 查询是否关注
 */
public class QueryAttentionPresenter extends BasePresenter {

    private IQueryAttentionView iQueryAttentionView ;

    private IAttentionQueryModel iAttentionQueryModel = new AttentionQueryModel();

    public QueryAttentionPresenter(IQueryAttentionView iQueryAttentionView) {
        this.iQueryAttentionView = iQueryAttentionView;
    }


    public void queryAttention(int id,String role){
        iAttentionQueryModel.queryAttention(id, role, new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    iQueryAttentionView.queryAttentionSuccess((AttentionStautsEntity) res.getData());
                }else {
                    iQueryAttentionView.queryAttentionFail(res.getMsg());
                }
            }
        });
    }
}
