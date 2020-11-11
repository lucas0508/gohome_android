package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

public interface ISkillCertificationModel extends IBaseModel {


    void sendSkillCertification(String name, String picture,String id,OnCallbackListener onCallbackListener);


}
