package com.qujiali.jiaogegongren.ui.main.fragment.view;

import com.qujiali.jiaogegongren.bean.HomeEntity;

import java.util.ArrayList;
import java.util.List;

public interface IHomePageView {

    void getHomePageDataSuccess(ArrayList<HomeEntity> homeEntities);


    void getHomePageDataFail(String info);

}
