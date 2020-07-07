package com.qujiali.jiaogegongren.ui.other.presenter;

import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.ui.other.model.UploadFileModel;
import com.qujiali.jiaogegongren.ui.other.model.impl.IIUploadFileModel;
import com.qujiali.jiaogegongren.ui.other.view.IUploadFileView;

import java.io.File;
import java.util.List;

public class UploadFilePresenter extends BasePresenter {


    private IUploadFileView iUploadFileView;

    private IIUploadFileModel iiUploadFileModel = new UploadFileModel();

    public UploadFilePresenter(IUploadFileView iUploadFileView) {
        this.iUploadFileView = iUploadFileView;
    }

    public void uploadImage(File files) {
        iiUploadFileModel.postUploadFile(files,res -> {
//            if (HttpProvider.isSuccessful(res.getCode())){
//                iUploadFileView.UploadFileSuccess((String) res.getData());
//            }else {
//                iUploadFileView.UploadFileFail(res.getMsg());
//            }
        });
    }

    public void uploadMultipleImage(List<String> files) {
        iiUploadFileModel.postMultiUploadFile(files,res -> {
            if (HttpProvider.isSuccessful(res.getCode())){
                iUploadFileView.UploadMultipleFileSuccess((List<String>) res.getData());
            }else {
                iUploadFileView.UploadMultipleFileFail(res.getMsg());
            }
        });
    }

}
