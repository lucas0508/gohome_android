package com.qujiali.jiaogegongren.ui.settlein.workersettlin.model;

import com.google.gson.reflect.TypeToken;
import com.qujiali.jiaogegongren.bean.WorkerProficiencyEntity;
import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseDataEntity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.model.impl.IWorkerProficiencyModel;

public class WorkerProficiencyModel extends BaseModel implements IWorkerProficiencyModel {


    @Override
    public void queryWorkerProficiency(IBaseModel.OnCallbackDataListener onCallbackListener) {
        HttpProvider.doGet(GlobalConstants.APP_WORKER_PROFICIENCY, responseText -> {
            executeDataCallback(responseText, new TypeToken<ResponseDataEntity<WorkerProficiencyEntity>>() {
            }.getType(),onCallbackListener);
        });
    }
}
