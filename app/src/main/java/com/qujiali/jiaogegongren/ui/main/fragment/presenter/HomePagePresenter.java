package com.qujiali.jiaogegongren.ui.main.fragment.presenter;

import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.bean.HomeWorkerTypeEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseDataEntity;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.HomePageModel;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IHomePageModel;
import com.qujiali.jiaogegongren.ui.main.fragment.view.IHomePageView;

import java.util.ArrayList;
import java.util.Map;

public class HomePagePresenter extends BasePresenter {

    private IHomePageView iHomePageView;

    private IHomePageModel iHomePageModel = new HomePageModel();

    public HomePagePresenter(IHomePageView iHomePageView) {
        this.iHomePageView = iHomePageView;
    }

    public void getHomePageData(Map<String,Object> map, String cityCode, String searchContent,String orderBy ){
        iHomePageModel.getHomePageData(map, cityCode, searchContent,orderBy, new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    iHomePageView.getHomePageDataSuccess((ArrayList<HomeEntity>) res.getRows());
                }else {
                    iHomePageView.getHomePageDataFail(res.getMsg());
                }
            }
        });


    }


    public void getHomeWorkerType(){
            iHomePageModel.getHomeWorkTypeData(new IBaseModel.OnCallbackDataListener() {
                @Override
                public void callback(ResponseDataEntity res) {
                    if (HttpProvider.isSuccessful(res.getCode())){
                        iHomePageView.getHomeWorkTypeSuccess((ArrayList<HomeWorkerTypeEntity>) res.getData());
                    }else {
                        iHomePageView.getHomeWorkTypeFail(res.getMsg());
                    }
                }
            });
    }
}
