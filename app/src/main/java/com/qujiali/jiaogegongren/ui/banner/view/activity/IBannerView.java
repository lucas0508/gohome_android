package com.qujiali.jiaogegongren.ui.banner.view.activity;

import com.qujiali.jiaogegongren.bean.BannerEntity;

public interface IBannerView {


    /**
     * 查询banner数据 - 成功
     */
    void queryBannerDataListSuccess(String str);

    /**
     * 查询banner数据 - 失败
     *
     * @param msg
     */
    void queryBannerDataListFail(int code, String msg);


}
