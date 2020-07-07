package com.qujiali.jiaogegongren.ui.main.fragment.presenter;

import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.main.fragment.model.HomeDetailModel;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IHomeDetailModel;
import com.qujiali.jiaogegongren.ui.main.fragment.view.IHomeDetailView;

public class HomeDetailPresenter extends BasePresenter {

    private IHomeDetailView iHomeDetailView;

    private IHomeDetailModel iHomeDetailModel = new HomeDetailModel();

    public HomeDetailPresenter(IHomeDetailView iHomeDetailView) {
        this.iHomeDetailView = iHomeDetailView;
    }

    public void queryWorker(String id){
        iHomeDetailModel.queryWorker(id,res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iHomeDetailView.queryWorkerDetailSuccess((SettlelnEntity) res.getData());
            }else {
                iHomeDetailView.queryWorkerDetailFail(res.getMsg());
            }
        });
    }

    public void queryEnterprise(String id){
        iHomeDetailModel.queryEnterprise(id,res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iHomeDetailView.queryEnterpriseDetailSuccess((SettlelnCompanyEntity) res.getData());
            }else {
                iHomeDetailView.queryEnterpriseDetailFail(res.getMsg());
            }
        });
    }
}
