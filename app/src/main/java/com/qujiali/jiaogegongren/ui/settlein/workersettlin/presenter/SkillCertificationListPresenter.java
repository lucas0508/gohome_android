package com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter;

import com.qujiali.jiaogegongren.bean.SkillCertificationEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.SkillCertificationListModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISkillCertificationListModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.ISkillCertificationListView;

import java.util.List;

public class SkillCertificationListPresenter extends BasePresenter {


    private ISkillCertificationListView iskillCertificationListView;

    private ISkillCertificationListModel iSkillCertificationListModel = new SkillCertificationListModel();

    public SkillCertificationListPresenter(ISkillCertificationListView iskillCertificationListView) {
        this.iskillCertificationListView = iskillCertificationListView;
    }

    public void getSkillCertificationList(){
        iSkillCertificationListModel.getSkillCertificationList(res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iskillCertificationListView.getSkillCertificationListSuccess((List<SkillCertificationEntity>) res.getData());
            }else {
                iskillCertificationListView.getSkillCertificationListFail(res.getMsg());
            }
        });
    }

}
