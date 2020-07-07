package com.qujiali.jiaogegongren.ui.main.fragment.presenter;

import com.qujiali.jiaogegongren.bean.UserInfoEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.MineFragmentModel;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IMineFragmentModel;
import com.qujiali.jiaogegongren.ui.main.fragment.view.IMineFragmentView;

import java.util.Map;

public class MinePresenter extends BasePresenter {

    private IMineFragmentView iMineFragmentView;

    private IMineFragmentModel iMineFragmentModel = new MineFragmentModel();

    public MinePresenter(IMineFragmentView iMineFragmentView) {
        this.iMineFragmentView = iMineFragmentView;
    }

    public void queryUserInfo(){
        iMineFragmentModel.queryUserInfoData(new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    iMineFragmentView.getUserInfoSuccess((UserInfoEntity) res.getData());
                }else {
                    iMineFragmentView.getUserInfoFail(res.getMsg(),res.getCode());
                }
            }
        });
    }

    public void submitUserInfo(Map<String,Object> objectMap){

        iMineFragmentModel.submitUserInfoData(objectMap,new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    iMineFragmentView.submitUserInfoSuccess();
                }else {
                    iMineFragmentView.submitUserInfoFail(res.getMsg());
                }
            }
        });
    }
}
