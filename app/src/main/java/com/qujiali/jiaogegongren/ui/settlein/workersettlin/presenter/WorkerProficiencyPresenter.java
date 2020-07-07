package com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter;

import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.WorkerProficiencyModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.IWorkerProficiencyModel;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.IWorkerProficiencyView;

public class WorkerProficiencyPresenter extends BasePresenter {

    private IWorkerProficiencyView iworkerProficiencyView;

    private IWorkerProficiencyModel iWorkerProficiencyModel = new WorkerProficiencyModel();

    public WorkerProficiencyPresenter(IWorkerProficiencyView iworkerProficiencyView) {
        this.iworkerProficiencyView = iworkerProficiencyView;
    }

    public void queryWorkerProficiency(){

        iWorkerProficiencyModel.queryWorkerProficiency(res -> {

           if (HttpProvider.isSuccessful(res.getCode())){
                iworkerProficiencyView.queryWorkerProficiencySuccess(res.getData());
            }else{
               iworkerProficiencyView.queryWorkerProficiencyFail();
            }
        });
    }



}
