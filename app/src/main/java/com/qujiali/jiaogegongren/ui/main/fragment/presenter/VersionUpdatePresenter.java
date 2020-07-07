package com.qujiali.jiaogegongren.ui.main.fragment.presenter;

import com.qujiali.jiaogegongren.bean.VersionUpdateEntity;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.VersionUpdateModel;
import com.qujiali.jiaogegongren.ui.main.fragment.view.IVersionUpdateModel;
import com.qujiali.jiaogegongren.ui.main.fragment.view.IVersionUpdateView;

import java.util.Map;

public class VersionUpdatePresenter {

    private IVersionUpdateModel mAboutMeActivityModel = new VersionUpdateModel();

    private IVersionUpdateView iVersionUpdateView;

    public VersionUpdatePresenter(IVersionUpdateView iVersionUpdateView) {
        this.iVersionUpdateView = iVersionUpdateView;
    }

    public void updateVersion() {
        mAboutMeActivityModel.updateVersion(new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())) {
                    iVersionUpdateView.updateVersionSuccess((VersionUpdateEntity) res.getData());
                } else {
                    iVersionUpdateView.updateVersionFail(res.getMsg());
                }
            }
        });
    }
}
