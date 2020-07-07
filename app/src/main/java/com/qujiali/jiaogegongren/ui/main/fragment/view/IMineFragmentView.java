package com.qujiali.jiaogegongren.ui.main.fragment.view;

import com.qujiali.jiaogegongren.bean.UserInfoEntity;

import java.util.Map;

public interface IMineFragmentView {

    void getUserInfoSuccess(UserInfoEntity userInfoEntity);

    void getUserInfoFail(String info,int code);

    void submitUserInfoSuccess();

    void submitUserInfoFail(String info);


}
