package com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter;

import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.SkillCertificationModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISkillCertificationModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.ISkillCertificationView;

public class SkillCertificationPresenter extends BasePresenter {

    private ISkillCertificationView iSkillCertificationView ;

    private ISkillCertificationModel iSkillCertificationModel = new SkillCertificationModel();

    public SkillCertificationPresenter(ISkillCertificationView iSkillCertificationView) {
        this.iSkillCertificationView = iSkillCertificationView;
    }

    public void  sendSkillCertification(String name ,String picture){
        iSkillCertificationModel.sendSkillCertification(name,picture,res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iSkillCertificationView.sendSkillCertificationSuccess();
            }else {
                iSkillCertificationView.sendSkillCertificationFail(res.getMsg());
            }
        });
    }
}
