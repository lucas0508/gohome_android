package com.qujiali.jiaogegongren.ui.login.view;


import com.qujiali.jiaogegongren.bean.UserInfoEntity;

/**
 * @author QiZai
 * @desc
 * @date 2018/4/10
 */

public interface ILoginView {

    /**
     * 登陆成功
     */
    void loginSuccess(String data);

    /**
     * 登陆失败
     *
     * @param info
     */
    void loginFail(String info);


    /**
     * 微信登陆 - 成功
     *
     * @param data
     */
    void toWXLoginSuccess(UserInfoEntity data);

    /**
     * 微信登陆 - 失败
     *
     * @param info
     */
    void toWXLoginFail(String info);

    /**
     * 验证码发送成功
     */
    void sendValidateCodeSuccess(String key);

    /**
     * 验证码发送失败
     * @param info
     */
    void sendValidateCodeFail(String info);
}
