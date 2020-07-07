package com.qujiali.jiaogegongren.ui.realname.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

/**
 * Created by QiZai on 2018/10/13.
 */

public interface IRealNameNextModel extends IBaseModel {

    /**
     * 查询实名认证
     *
     * @param listener
     */
    void queryCertificationInfo(OnCallbackListener listener);

    /**
     * 上传实名信息
     *
     * @param data
     * @param listener
     */
    void uploadCertificationInfo(Map<String, Object> data, OnCallbackListener listener);
}
