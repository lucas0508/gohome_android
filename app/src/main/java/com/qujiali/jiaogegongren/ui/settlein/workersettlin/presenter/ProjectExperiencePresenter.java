package com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter;

import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.ProjectExperienceModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.IProjectExperienceModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.IProjectExperienceView;

import java.util.HashMap;
import java.util.Map;

public class ProjectExperiencePresenter extends BasePresenter {

    private IProjectExperienceView iProjectExperienceView;

    private IProjectExperienceModel iProjectExperienceModel = new ProjectExperienceModel();


    public ProjectExperiencePresenter(IProjectExperienceView iProjectExperienceView) {
        this.iProjectExperienceView = iProjectExperienceView;
    }

    public void sendProjectExperience(Map<String, Object> objectMap) {
        iProjectExperienceModel.sendPProjectExperienceData(objectMap, res -> {
            if (HttpProvider.isSuccessful(res.getCode())) {
                iProjectExperienceView.sendProjectExperienceSuccess();
            } else {
                iProjectExperienceView.sendProjectExperienceFail(res.getMsg());
            }
        });
    }

    public void sendCompanyProjectExperience(Map<String, Object> objectMap) {
        iProjectExperienceModel.sendCompanyProjectExperienceData(objectMap, res -> {
            if (HttpProvider.isSuccessful(res.getCode())) {
                iProjectExperienceView.sendCompanyProjectExperienceSuccess();
            } else {
                iProjectExperienceView.sendCompanyProjectExperienceFail(res.getMsg());
            }
        });
    }
}
