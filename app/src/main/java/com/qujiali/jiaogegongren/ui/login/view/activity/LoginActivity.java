package com.qujiali.jiaogegongren.ui.login.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;

import com.facebook.stetho.common.LogUtil;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.UserInfoEntity;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.ui.login.presenter.LoginPresenter;
import com.qujiali.jiaogegongren.ui.login.view.ILoginView;
import com.qujiali.jiaogegongren.ui.other.view.WebActivity;
import com.qujiali.jiaogegongren.wxapi.WXEntryActivity;
import com.qujiali.jiaogegongren.wxapi.WXPayUtils;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.debug.E;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

import static com.qujiali.jiaogegongren.common.global.Constants.WEB_KEY_FLAG;
import static com.qujiali.jiaogegongren.common.global.Constants.WEB_KEY_URL;

/**
 * 登陆页
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {

    private LoginPresenter mLoginPresenter = new LoginPresenter(this);
    public static LoginActivity instance;
    private IWXAPI mWxApi; //微信支付api

    private LinearLayout ValidateCode;
    private EditText mPhone, mValidateCode, mInviteCode;
    private TextView mLoginAgreement, mLoginAgreementPrivacy;
    private Button mSendValidateCode, mLogin;
    private ImageView mWXLogin;

    private Timer mTimer;
    private TimerTask mTimerTask;
    private int counter; // 计数器
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.ib_close)
    ImageButton mClose;
    @BindView(R.id.checkbox)
    AppCompatCheckBox appCompatCheckBox;

    private String captcha_key;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_login;
    }

    public int getCounter() { // 获取计数时间
        return counter;
    }

    public void setCounter(int counter) { // 设置计数器数值，并反馈给用户
        if (counter <= 0) {
            mSendValidateCode.setEnabled(true);
            mSendValidateCode.setText("重发");
            mTimer.cancel();
            mTimer = null;
            mTimerTask = null;
        } else {
            mSendValidateCode.setText(counter + "秒");
        }
        this.counter = counter;
    }

    public void initView() {
        mTitle.setText("登录");
        mClose.setVisibility(View.GONE);
        instance = this;
        ValidateCode = findViewById(R.id.ll_yaoqingma);
        mPhone = findViewById(R.id.et_phone);
        mValidateCode = findViewById(R.id.et_validate_code);
        mInviteCode = findViewById(R.id.et_invite_code);
        mSendValidateCode = findViewById(R.id.b_send_validate_code);
        mLoginAgreement = findViewById(R.id.tv_login_agreement_user_agreement);
        mLoginAgreementPrivacy = findViewById(R.id.tv_login_agreement_privacyPolicy);
        mLogin = findViewById(R.id.b_login);
        mWXLogin = findViewById(R.id.b_wx_login);
        mSendValidateCode.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mWXLogin.setOnClickListener(this);
        mLoginAgreement.setOnClickListener(this);
        mLoginAgreementPrivacy.setOnClickListener(this);
        initPhoneEditText();

        appCompatCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mLogin.setEnabled(true);
                }else {
                    mLogin.setEnabled(false);
                }
            }
        });
    }

    private void initPhoneEditText() {
        mPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence == null || charSequence.length() == 0) {
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < charSequence.length(); i++) {
                    if (i != 3 && i != 8 && charSequence.charAt(i) == ' ') {
                        continue;
                    } else {
                        stringBuilder.append(charSequence.charAt(i));
                        if ((stringBuilder.length() == 4 || stringBuilder.length() == 9)
                                && stringBuilder.charAt(stringBuilder.length() - 1) != ' ') {
                            stringBuilder.insert(stringBuilder.length() - 1, ' ');
                        }
                    }
                }
                if (!stringBuilder.toString().equals(charSequence.toString())) {
                    int index = start + 1;
                    if (stringBuilder.charAt(start) == ' ') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    mPhone.setText(stringBuilder.toString());
                    mPhone.setSelection(index);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b_send_validate_code:
                sendValidate();
                break;
            case R.id.b_login:
                boolean checked = appCompatCheckBox.isChecked();
                if (checked){
                    loginBefore();
                }else {
                    mApp.shortToast("请勾选用户协议");
                }
                //mApp.shortToast("阅读并同意底部相关协议才能登录");
                break;
            case R.id.b_wx_login:
                toWXLogin();
                break;
            case R.id.tv_login_agreement_user_agreement:
                Intent intent = new Intent(this, WebActivity.class);
                intent.putExtra(WEB_KEY_URL, GlobalConstants.AGREEMENT_URL);
                intent.putExtra(WEB_KEY_FLAG, "1");
                startActivity(intent);
                break;
            case R.id.tv_login_agreement_privacyPolicy:
                Intent intent1 = new Intent(this, WebActivity.class);
                intent1.putExtra(WEB_KEY_URL, GlobalConstants.PRIVACYPOLICY_URL);
                intent1.putExtra(WEB_KEY_FLAG, "1");
                startActivity(intent1);
                break;
        }
    }

    public void sendValidate() {
        String msg = mLoginPresenter.sendValidateCode(mPhone.getText().toString().replaceAll(" ", ""));
        if (msg != null) {
            mApp.shortToast(msg);
        } else {
            mSendValidateCode.setEnabled(false);
            mSendValidateCode.setText("...");
        }
    }

    /**
     * 吊起微信登陆
     */
    public void toWXLogin() {
        if (WXPayUtils.isWeixinAvilible(getContext())) {
            mApp.getLoadingDialog().show();
            mWxApi = WXAPIFactory.createWXAPI(this, null); //初始化微信api
            mWxApi.registerApp(WXPayUtils.APP_ID); //注册appid  appid可以在开发平台获取
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo";
            mWxApi.sendReq(req);
            WXEntryActivity.setResultCallback((errCode, code) -> {
                mApp.getLoadingDialog().hide();

                Logger.d(errCode);
                Logger.d(code);
                if (errCode == BaseResp.ErrCode.ERR_OK && code != null)
                    mLoginPresenter.toWXLogin(code);
            });
        } else {
            mApp.longToast("未检测到微信，建议去应用市场下载安装！");
            mApp.getLoadingDialog().hide();
        }
    }

    /**
     * 普通登陆
     */
    public void loginBefore() {
        String s = mPhone.getText().toString().replaceAll(" ", "");
        String msg = mLoginPresenter.toLogin(
                s,
                mValidateCode.getText().toString(),
                captcha_key
        );
        if (msg != null) {
            mApp.shortToast(msg);
        } else {
            mApp.getLoadingDialog().show();
        }
    }

    @Override
    public void sendValidateCodeSuccess(String key) {
        captcha_key = key;
        mApp.shortToast("验证码已发送，请留意您的手机！");
        setCounter(120);
        if (mTimer == null) mTimer = new Timer();
        if (mTimerTask == null) mTimerTask = new TimerTask() {
            @Override
            public void run() {
                getContext().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (getCounter() <= 0) {
                            mTimer.cancel();
                            mTimerTask.cancel();
                            mTimer = null;
                            mTimerTask = null;
                        }
                        setCounter(getCounter() - 1);
                    }
                });
            }
        };
        this.mTimer.schedule(mTimerTask, 1000, 1000);

    }

    @Override
    public void sendValidateCodeFail(String info) {
        mApp.shortToast(info);
        mSendValidateCode.setEnabled(true);
        mSendValidateCode.setText("重送");
    }

    @Override
    public void loginSuccess(String token) {
        mApp.getLoadingDialog().hide();
        UserInfo.setToken(token);
        finish();
        mApp.shortToast("登录成功~");
    }

    @Override
    public void loginFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }


    @Override
    public void toWXLoginSuccess(UserInfoEntity data) {
        mApp.getLoadingDialog().hide();
        if (data.getPhone() != null) {
            loginSuccess(data.getToken());
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("Token", "Bearer " + data.getToken());
            startActivity(BindWXActivity.class, bundle);
        }
    }

    @Override
    public void toWXLoginFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }

}
