package com.qujiali.jiaogegongren.ui.main.fragment.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface IPostRecruitmentModel {

    void queryLabelData(IBaseModel.OnCallbackDataListener listener);

    void submitWorkersData(Map<String, Object> stringObjectMap,IBaseModel.OnCallbackListener listener);

    void queryWorkersData(int id,IBaseModel.OnCallbackListener listener);

}
