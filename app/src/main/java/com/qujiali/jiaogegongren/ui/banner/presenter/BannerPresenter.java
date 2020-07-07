package com.qujiali.jiaogegongren.ui.banner.presenter;


import com.qujiali.jiaogegongren.bean.BannerEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.banner.model.IBannerModel;
import com.qujiali.jiaogegongren.ui.banner.model.impl.BannerModel;
import com.qujiali.jiaogegongren.ui.banner.view.activity.IBannerView;

public class BannerPresenter extends BasePresenter {

    private IBannerView iBannerView;

    private IBannerModel iHomeModel = new BannerModel();
    public BannerPresenter(IBannerView iBannerView) {
        this.iBannerView = iBannerView;
    }

    public void queryBannerDataList(String cityCode, String type ,String banner_location){
        iHomeModel.queryBannerDataList(cityCode, type, banner_location,new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())) {
                    iBannerView.queryBannerDataListSuccess((String) res.getData());
                } else {
                    iBannerView.queryBannerDataListFail(res.getCode(),res.getMsg());
                }
            }
        });
    }

}
