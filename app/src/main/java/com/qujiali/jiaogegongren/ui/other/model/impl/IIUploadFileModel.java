package com.qujiali.jiaogegongren.ui.other.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.io.File;
import java.util.List;

public interface IIUploadFileModel extends IBaseModel {


    /**
     * 单张
     */
    void postUploadFile(File file, OnCallbackListener onCallbackListener);


    /**
     * 多张
     */
    void postMultiUploadFile(List<String> file, OnCallbackListener onCallbackListener);


}
