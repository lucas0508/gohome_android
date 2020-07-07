package com.qujiali.jiaogegongren.ui.main.fragment.view;


import com.qujiali.jiaogegongren.common.base.IBaseModel;

public interface IVersionUpdateModel extends IBaseModel {


    /**
     *  获取版本号
     *
     * @param onCallbackListener
     */
    void  updateVersion(OnCallbackListener onCallbackListener);

}
