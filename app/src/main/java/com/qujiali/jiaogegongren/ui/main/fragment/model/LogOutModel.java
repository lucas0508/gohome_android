package com.qujiali.jiaogegongren.ui.main.fragment.model;


import com.qujiali.jiaogegongren.common.base.BaseModel;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.ILogOutModel;

public class LogOutModel extends BaseModel implements ILogOutModel {


    @Override
    public void logout(OnCallbackListener listener) {
        String url = GlobalConstants.APP_LOGOUT;
        HttpProvider.doGet(url,  responseText -> {
            executeCallback(responseText,null, listener);
        });
    }
}
