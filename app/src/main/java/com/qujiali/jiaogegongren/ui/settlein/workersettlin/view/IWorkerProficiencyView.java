package com.qujiali.jiaogegongren.ui.settlein.workersettlin.view;

import com.qujiali.jiaogegongren.bean.WorkerProficiencyEntity;

import java.util.List;

public interface IWorkerProficiencyView {

    void queryWorkerProficiencySuccess(List<WorkerProficiencyEntity> workerProficiencyEntities);


    void queryWorkerProficiencyFail();
}
