package com.qujiali.jiaogegongren.ui.settlein.workersettlin.view;

import com.qujiali.jiaogegongren.bean.SkillCertificationEntity;

import java.util.List;

public interface ISkillCertificationListView {


    void getSkillCertificationListSuccess(List<SkillCertificationEntity> list);

    void getSkillCertificationListFail(String info);
}
