package com.qujiali.jiaogegongren.ui.login.presenter;

import android.text.TextUtils;

import com.qujiali.jiaogegongren.bean.UserInfoEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.common.utils.ValidateUtils;
import com.qujiali.jiaogegongren.ui.login.model.IRegisterModel;
import com.qujiali.jiaogegongren.ui.login.model.impl.RegisterModel;
import com.qujiali.jiaogegongren.ui.login.view.IRegisterView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author QiZai
 * @desc
 * @date 2018/4/10
 */

public class RegisterPresenter extends BasePresenter {

    private IRegisterView mRegisterView;
    private IRegisterModel mRegisterModel = new RegisterModel();

    public RegisterPresenter(IRegisterView view) {
        this.mRegisterView = view;
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    public String sendValidateCode(String phone) {
        if (!ValidateUtils.validatePhone(phone)) return "手机号输入不正确！";
        sendWXValidateCode(phone,"bind");
        return null;
    }

    /**
     * 注册
     *
     * @param phone
     * @param code
     * @return
     */
    public String register(String phone, String code, String uuid,String token) {
        if (TextUtils.isEmpty(phone))
            return "手机号输入不正确！";
        if (!ValidateUtils.validateCode(code))
            return "验证码为4位纯数字！";

        Map<String, Object> data = new HashMap<>();
        data.put("username", phone);
        data.put("code", code);
        data.put("uuid", uuid);
        fromWXRegister(data,token);
        return null;
    }

    /**
     * 微信绑定手机号的验证码发送
     *
     * @param phone
     */
    private void sendWXValidateCode(String phone, String type) {
        mRegisterModel.sendWXValidateCode(phone, type,new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())) {
                    mRegisterView.sendValidateCodeSuccess((String) res.getData());
                } else {
                    mRegisterView.sendValidateCodeFail(res.getMsg());
                }
            }
        });
    }

    /**
     * 微信登陆绑定手机号
     *
     * @param data
     */
    private void fromWXRegister(Map<String, Object> data,String token) {
        mRegisterModel.fromWXRegister(data,token, new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())) {
                    mRegisterView.registerSuccess((UserInfoEntity) res.getData());
                } else {
                    mRegisterView.registerFail(res.getMsg());
                }
            }
        });
    }
}
