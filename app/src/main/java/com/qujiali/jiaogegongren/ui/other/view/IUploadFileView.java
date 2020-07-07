package com.qujiali.jiaogegongren.ui.other.view;

import java.util.List;

public interface IUploadFileView {

//    void UploadFileSuccess(String url);
//
//    void UploadFileFail(String msg);

    void UploadMultipleFileSuccess(List<String> strings);

    void UploadMultipleFileFail(String msg);


}
