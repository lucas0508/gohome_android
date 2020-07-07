package com.qujiali.jiaogegongren.ui.main.fragment.view;

import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;

public interface IHomeDetailView {

    void queryWorkerDetailSuccess(SettlelnEntity settlelnEntity);

    void queryWorkerDetailFail(String info);

    void queryEnterpriseDetailSuccess(SettlelnCompanyEntity settlelnCompanyEntity);

    void queryEnterpriseDetailFail(String info);
}
