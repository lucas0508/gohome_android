package com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.presenter;

import com.qujiali.jiaogegongren.bean.CompanyEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.model.CompanyAuthModel;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.model.impl.ICompanyAuthModel;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.view.ICompanyAuthView;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.SkillCertificationModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.ISkillCertificationModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.ISkillCertificationView;

public class CompanyAuthPresenter extends BasePresenter {

    private ICompanyAuthView iCompanyAuthView ;

    private ICompanyAuthModel iCompanyAuthModel = new CompanyAuthModel();

    public CompanyAuthPresenter(ICompanyAuthView iCompanyAuthView) {
        this.iCompanyAuthView = iCompanyAuthView;
    }

    public void  sendCompanyAuth(String name ,String address,String picture){
        iCompanyAuthModel.sendCompanyAuth(name,address,picture,res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iCompanyAuthView.sendCompanyAuthSuccess();
            }else {
                iCompanyAuthView.sendCompanyAuthFail(res.getMsg());
            }
        });
    }

    public void  queryCompanyAuth(){
        iCompanyAuthModel.queryCompanyAuth(res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iCompanyAuthView.queryCompanyAuthSuccess((CompanyEntity) res.getData());
            }else {
                iCompanyAuthView.queryCompanyAuthFail(res.getMsg());
            }
        });
    }
}
