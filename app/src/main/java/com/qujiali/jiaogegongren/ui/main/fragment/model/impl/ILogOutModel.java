package com.qujiali.jiaogegongren.ui.main.fragment.model.impl;


import com.qujiali.jiaogegongren.common.base.IBaseModel;

public interface ILogOutModel extends IBaseModel {


    /**
     * 退出登录
     * @param listener
     */
    void logout(IBaseModel.OnCallbackListener listener);


}
