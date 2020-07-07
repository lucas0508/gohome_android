package com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter;

import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.SettleInViewModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISettleInViewModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.ISettleInView;

public class SettleInPresenter extends BasePresenter {

    private ISettleInView iSettleInView;

    private ISettleInViewModel iSettleInViewModel = new SettleInViewModel();

    public SettleInPresenter(ISettleInView iSettleInView) {
        this.iSettleInView = iSettleInView;
    }

    public void querySettleInWorker(){
        iSettleInViewModel.getSettleInWorkerData(res -> {
              if (HttpProvider.isSuccessful(res.getCode())){
                  iSettleInView.getSettleInDataSuccess((SettlelnEntity) res.getData());
              }else {
                  iSettleInView.getSettleInDataFail(res.getMsg());
              }
        });
    }

    public void querySettleInExperience(){
        iSettleInViewModel.getSettleInExperienceData(res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iSettleInView.getSettleInCompanyDataSuccess((SettlelnCompanyEntity) res.getData());
            }else {
                iSettleInView.getSettleInCompanyDataFail(res.getMsg());
            }
        });
    }
}
