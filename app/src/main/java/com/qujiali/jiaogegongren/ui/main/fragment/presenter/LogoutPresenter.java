package com.qujiali.jiaogegongren.ui.main.fragment.presenter;


import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.LogOutModel;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.ILogOutModel;
import com.qujiali.jiaogegongren.ui.main.fragment.view.ILogOutView;

public class LogoutPresenter extends BasePresenter {


    private ILogOutView iLogOutView;

    private ILogOutModel iLogOutModel = new LogOutModel();

    public LogoutPresenter(ILogOutView iLogOutView) {
        this.iLogOutView = iLogOutView;
    }

    public void logOut() {
        iLogOutModel.logout(res -> {
            if (HttpProvider.isSuccessful(res.getCode())) {
                iLogOutView.logoutSuccess();
            } else {
                iLogOutView.logoutFail(res.getMsg());
            }
        });
    }

}
