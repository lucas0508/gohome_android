package com.qujiali.jiaogegongren.ui.main.fragment.view;

public interface ILogOutView {
    /**
     * 退出登录 - 成功
     */
    void logoutSuccess();

    /**
     * 退出登录 - 失败
     *
     * @param info
     */
    void logoutFail(String info);
}
