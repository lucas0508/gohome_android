package com.qujiali.jiaogegongren.ui.realname.activity;

import com.qujiali.jiaogegongren.bean.RealNameEntity;

public interface IRealNameView {

    /**
     * 上传实名信息 - 成功
     */
    void uploadCertificationInfoSuccess();

    /**
     * 上传实名信息 - 失败
     *
     * @param info
     */
    void uploadCertificationInfoFail(String info);



    void queryCertificationInfoSuccess(RealNameEntity realNameEntity);


    void queryCertificationInfoFail(String info);
}
