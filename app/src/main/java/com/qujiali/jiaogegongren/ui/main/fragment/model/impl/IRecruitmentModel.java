package com.qujiali.jiaogegongren.ui.main.fragment.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

public interface IRecruitmentModel {


    void loadRecruitmentData(Map<String, Object> map, String cityCode, String searchContent,String related, IBaseModel.OnCallbackListener listener);

}
