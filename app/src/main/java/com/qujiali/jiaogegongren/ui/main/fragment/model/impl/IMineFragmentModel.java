package com.qujiali.jiaogegongren.ui.main.fragment.model.impl;


import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface IMineFragmentModel {


    void queryUserInfoData(IBaseModel.OnCallbackListener onCallbackListener);


    void submitUserInfoData(Map<String,Object> objectMap, IBaseModel.OnCallbackListener onCallbackListener);
}
