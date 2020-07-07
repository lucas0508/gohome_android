package com.qujiali.jiaogegongren.ui.login.presenter;

import com.google.gson.Gson;
import com.qujiali.jiaogegongren.bean.UserInfoEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.common.utils.ValidateUtils;
import com.qujiali.jiaogegongren.common.utils.encrypt.SecurityUtil;
import com.qujiali.jiaogegongren.ui.login.model.ILoginModel;
import com.qujiali.jiaogegongren.ui.login.model.impl.LoginModel;
import com.qujiali.jiaogegongren.ui.login.view.ILoginView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author QiZai
 * @desc
 * @date 2018/4/10
 */

public class LoginPresenter extends BasePresenter {

    private ILoginView mLoginView;
    private ILoginModel mLoginModel = new LoginModel();
    private Map<String, Object> mLoginFormData = new HashMap<>();

    public LoginPresenter(ILoginView loginView) {
        this.mLoginView = loginView;
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    public String sendValidateCode(String phone) {
        if (!ValidateUtils.validatePhone(phone)) return "手机号输入不正确！";
        sendNormalValidateCode(phone,"login");
        return null;
    }

    /**
     * 普通验证码发送
     *
     * @param phone
     */
    private void sendNormalValidateCode(String phone ,String type) {
        mLoginModel.sendValidateCode(phone,type, new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())) {
                    mLoginView.sendValidateCodeSuccess((String) res.getData());
                } else {
                    mLoginView.sendValidateCodeFail(res.getMsg());
                }
            }
        });
    }

    /**
     * 普通登陆
     *
     * @param phone
     * @param validateCode
     * @param inviteCode
     * @return
     */
    public String toLogin(String phone, String validateCode, String inviteCode) {
        if (!ValidateUtils.validatePhone(phone))
            return "手机号输入不正确！";
        if (!ValidateUtils.validateCode(validateCode))
            return "验证码为4位纯数字！";

//        String finalPhone = SecurityUtil.encryptRSAPublic(phone);
//        String finalValidateCode = SecurityUtil.encryptRSAPublic(validateCode);
//        String finalInviteCode = SecurityUtil.encryptRSAPublic(inviteCode);

        mLoginFormData.put("username", phone);
        mLoginFormData.put("code", validateCode);
        mLoginFormData.put("uuid", inviteCode);
//      mLoginFormData.put("cityCode", UserInfo.getUserHadCity() != null ? UserInfo.getUserHadCity().get("HADCITYCODE") : "");

        mLoginModel.toLogin(mLoginFormData, new ILoginModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())) {
                    mLoginView.loginSuccess((String) res.getData());
                } else {
                    mLoginView.loginFail(res.getMsg());
                }
            }
        });
        return null;
    }

    /**
     * 微信登陆
     *
     * @param code
     */
    public void toWXLogin(String code) {
        mLoginModel.toWXLogin(code, new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())) {
                    mLoginView.toWXLoginSuccess((UserInfoEntity) res.getData());
                } else {
                    mLoginView.toWXLoginFail(res.getMsg());
                }
            }
        });
    }
}
