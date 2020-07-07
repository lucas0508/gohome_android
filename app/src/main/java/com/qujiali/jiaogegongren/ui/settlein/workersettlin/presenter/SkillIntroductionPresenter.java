package com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter;

import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.SkillIntroductionModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISkillIntroductionModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.ISkillIntroductionView;

import java.util.Map;

public class SkillIntroductionPresenter extends BasePresenter {

    private ISkillIntroductionView iSkillIntroductionView;

    private ISkillIntroductionModel iSkillIntroductionModel = new SkillIntroductionModel();

    public SkillIntroductionPresenter(ISkillIntroductionView iSkillIntroductionView) {
        this.iSkillIntroductionView = iSkillIntroductionView;
    }

    public void sendSkillIntroduction(Map<String,Object> objectMap){
        iSkillIntroductionModel.sendSkillIntroductionData(objectMap,res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iSkillIntroductionView.sendSkillIntroductionDataSuccess();
            }else {
                iSkillIntroductionView.sendSkillIntroductionFail(res.getMsg());
            }
        });
    }

    public void sendCompanySkillIntroduction(Map<String,Object> objectMap){
        iSkillIntroductionModel.sendCompanySkillIntroductionData(objectMap,res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iSkillIntroductionView.sendCompanySkillIntroductionSuccess();
            }else {
                iSkillIntroductionView.sendCompanySkillIntroductionFail(res.getMsg());
            }
        });
    }

}
