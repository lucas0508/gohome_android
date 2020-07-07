package com.qujiali.jiaogegongren.common.dialog;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;

import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.ui.login.view.activity.LoginActivity;


/**
 * @author QiZai
 * @desc
 * @date 2018/6/6 10:04
 */

public class DialogManage {

    private Context mContext;
    private LoadingDialog mLoadingDialog;
    private ConfirmDialog mConfirmDialog;
    private SelectPicDialog mSelectPicDialog;
    private LoginDialog mLoginDialog;
//    private PaymentDialog mPaymentDialog;
//    private OrderRejectionDialog orderRejectionDialog;
    private ConfirmAgreementDialog confirmAgreementDialog;
    private OptionDialog mOptionDialog;

    public DialogManage(Context context) {
        mContext = context;
    }



    /**
     * 获取选项弹窗
     *
     * @return
     */
    public OptionDialog getOptionDialog() {
        if (mOptionDialog == null)
            mOptionDialog = new OptionDialog(mContext);
        return mOptionDialog;
    }

    /**
     * 获取loading弹窗
     *
     * @return
     */
    public LoadingDialog getLoadingDialog() {
        if (mLoadingDialog == null)
            mLoadingDialog = new LoadingDialog(mContext);
        return mLoadingDialog;
    }


    /**
     * 获取普通弹窗
     *
     * @return
     */
    public ConfirmDialog getConfirmDialog() {
        if (mConfirmDialog == null)
            mConfirmDialog = new ConfirmDialog(mContext);
        return mConfirmDialog;
    }


    /**
     * 获取普通弹窗
     *
     * @return
     */
    public ConfirmAgreementDialog getConfirmAgreementDialog() {
        if (confirmAgreementDialog == null)
            confirmAgreementDialog = new ConfirmAgreementDialog(mContext);
        return confirmAgreementDialog;
    }


    public LoginDialog getLoginDialog() {
        if (mLoginDialog == null)
            mLoginDialog = new LoginDialog(mContext);
        return mLoginDialog;
    }
//
//    public OrderRejectionDialog getOrderRejectionDialog(){
//        if (orderRejectionDialog==null)
//            orderRejectionDialog = new OrderRejectionDialog(mContext);
//        return orderRejectionDialog;
//    }

//
//    /**
//     * 获取选项弹窗
//     *
//     * @return
//     */
//    public OptionDialog getOptionDialog() {
//        if (mOptionDialog == null)
//            mOptionDialog = new OptionDialog(mContext);
//        return mOptionDialog;
//    }

//
//    /**
//     * 获取用户信息弹窗
//     *
//     * @return
//     */
//    public UserInfoDialog getUserInfoDialog() {
//        if (mUserInfoDialog == null)
//            mUserInfoDialog = new UserInfoDialog(mContext);
//        return mUserInfoDialog;
//    }
//



    public SelectPicDialog getSelectPicDialog() {
        if (mSelectPicDialog == null)
            mSelectPicDialog = new SelectPicDialog(mContext);
        return mSelectPicDialog;
    }


    /**
     * 获取支付弹窗
     *
     * @return
     */
//    public PaymentDialog getPaymentDialog() {
//        if (mPaymentDialog == null)
//            mPaymentDialog = new PaymentDialog(mContext);
//        return mPaymentDialog;
//    }

//    /**
//     * 获取企业信息弹窗
//     *
//     * @return
//     */
//    public BusinessInfoDialog getBusinessInfoDialog() {
//        if (mBusinessInfoDialog == null)
//            mBusinessInfoDialog = new BusinessInfoDialog(mContext);
//        return mBusinessInfoDialog;
//    }
//
//    /**
//     * activity跳转
//     *
//     * @param activity
//     */
//    public void start(Class<? extends BaseActivity> activity) {
//        mContext.startActivity(new Intent(mContext, activity));
//    }
//
//

    /**
     * 判断用户是否登录，未登录弹出提醒
     *
     * @return 是否登录
     */
    public Boolean isLoginToDialog() {
        boolean flag = false;
        if (isLogin())
            flag = isLogin();
        if (!flag) {
            getLoginDialog().show();
        }
        return flag;
    }

   /**
     * 判断用户是否登录 - 无弹窗
     *
     * @return
     */
    public boolean isLogin() {
        return !TextUtils.isEmpty(UserInfo.getToken());
    }

//
//    /**
//     * 判断用户是否认证，未认证弹出提醒
//     *
//     * @return 是否认证
//     */
//    public Boolean isAuth() {
//        if (!UserInfo.getIsAuth()) {
//            if (mAuthConfirmCallback == null)
//                mAuthConfirmCallback = new ConfirmDialog.ConfirmCallback() {
//                    @Override
//                    public void onOk() {
////                        Intent intent = new Intent(mContext, AddBankCardActivity.class);
////                        intent.putExtra("KEY_OTHER_PAGE", true);
//                        Intent intent = new Intent(mContext, RealNameActivity.class);
//                        mContext.startActivity(intent);
//                    }
//
//                    @Override
//                    public void onCancel() {
//
//                    }
//                };
//            getConfirmDialog().showgo(
//                    "您之后的操作需要认证，是否立即去认证？",
//                    mAuthConfirmCallback
//            );
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 判断用户是否认证，未认证弹出提醒
//     *
//     * @return 是否认证
//     */
//    public Boolean isAuthWork() {
//       LogUtil.e("ISAUTH",UserInfo.getIsWorkAuth()+"");
//        if (!UserInfo.getIsWorkAuth()) {
//            if (mAuthConfirmCallback == null)
//                mAuthConfirmCallback = new ConfirmDialog.ConfirmCallback() {
//                    @Override
//                    public void onOk() {
//                        Intent intent = new Intent(mContext, RealNameActivity.class);
////                        intent.putExtra("KEY_OTHER_PAGE", true);
//                        mContext.startActivity(intent);
//                    }
//
//                    @Override
//                    public void onCancel() {
//
//                    }
//                };
//            getConfirmDialog().showgo(
//                    "未实名认证,无法被雇主查看",
//                    mAuthConfirmCallback
//            );
//            return false;
//        }
//        return true;
//    }
//
//

    /**
     * toast
     *
     * @param info
     */
//    public void Toast(String info) {
//        if (info != null) ToastUtils.showTextToas(mContext, info);
//    }


    /**
     * short - 吐司信息
     *
     * @param info
     */
    public void shortToast(String info) {
        if (info != null) Toast.makeText(mContext, info, Toast.LENGTH_SHORT).show();
    }

    /**
     * long - 吐司信息
     *
     * @param info
     */
    public void longToast(String info) {
        if (info != null) Toast.makeText(mContext, info, Toast.LENGTH_LONG).show();
    }
}
