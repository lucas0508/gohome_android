package com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.view;

import com.qujiali.jiaogegongren.bean.CompanyEntity;

public interface ICompanyAuthView {


    void sendCompanyAuthSuccess();

    void sendCompanyAuthFail(String info);


    void queryCompanyAuthSuccess(CompanyEntity companyEntity);

    void queryCompanyAuthFail(String info);

}
