package com.qujiali.jiaogegongren.ui.other.model;

import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.other.model.impl.IIUploadFileModel;

import java.io.File;
import java.util.List;

public class UploadFileModel extends BaseModel implements IIUploadFileModel {


    @Override
    public void postUploadFile(File file, OnCallbackListener onCallbackListener) {
        HttpProvider.doPost(GlobalConstants.APP_UPLOAD + file, null, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, null, onCallbackListener);
            }
        });
    }

    @Override
    public void postMultiUploadFile(List<String> file, OnCallbackListener onCallbackListener) {
        HttpProvider.doFile(GlobalConstants.APP_MULTIUPLOAD  , file, new HttpProvider.ResponseCallback() {
            @Override
            public void callback(String responseText) {
                executeCallback(responseText, null, onCallbackListener);
            }
        });
    }
}
