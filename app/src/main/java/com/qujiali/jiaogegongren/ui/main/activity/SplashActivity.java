package com.qujiali.jiaogegongren.ui.main.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.facebook.stetho.common.LogUtil;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.BannerEntity;
import com.qujiali.jiaogegongren.common.base.ActivityManager;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.common.dialog.ConfirmAgreementDialog;
import com.qujiali.jiaogegongren.common.dialog.DialogManage;
import com.qujiali.jiaogegongren.common.global.Constants;
import com.qujiali.jiaogegongren.common.server.LocationService;
import com.qujiali.jiaogegongren.common.utils.ACache;
import com.qujiali.jiaogegongren.common.utils.CheckNetwork;
import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;
import com.qujiali.jiaogegongren.common.utils.PreferenceUtil;
import com.qujiali.jiaogegongren.common.utils.ToastUtils;
import com.qujiali.jiaogegongren.ui.banner.presenter.BannerPresenter;
import com.qujiali.jiaogegongren.ui.banner.view.activity.IBannerView;

import java.text.MessageFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Date on 2019/1/10.
 * @Author by xrf05.
 */
public class SplashActivity extends BaseActivity implements IBannerView {
    BannerPresenter bannerPresenter = new BannerPresenter(this);

    private int mTime = 3;
    private Handler mHandler;
    @BindView(R.id.tv_time)
    TextView mTimeView;
    @BindView(R.id.welcome_ad)
    ImageView mWelcome_ad;
    private ACache mCache;

    /**
     * Activity, Fragment 公共方法处理类
     */
    public DialogManage mApp;
    private boolean isFirst;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        mHandler = new Handler();
        mCache = ACache.get(this);
        ButterKnife.bind(this);

        mApp = new DialogManage(this);
        isFirst = PreferenceUtil.getBoolean("isFirst", true);
        if (!CheckNetwork.isNetworkConnected(this)) {//没有网络
            String asString = mCache.getAsString(Constants.SPLASHACT_IMAGE);
            if (TextUtils.isEmpty(asString)) {
                mWelcome_ad.setBackgroundResource(R.mipmap.networkerror);
            } else {
                goHomePage(asString);
            }
        } else {//有网的情况
            bannerPresenter.queryBannerDataList(UserInfo.getCityCode(), "1", "");
        }

        if (isFirst) {
            mApp.getConfirmAgreementDialog().show("服务协议和隐私政策", new ConfirmAgreementDialog.ConfirmCallback() {
                @Override
                public void onOk() {
                    PreferenceUtil.put("isFirst", false);
                    initPermission();
                }
                @Override
                public void onCancel() {
                    ActivityManager.getInstance().kill();
                }
            });
        } else {
            mTimeView.setVisibility(View.VISIBLE);
            descTime();
        }
        mTimeView.setOnClickListener(v -> goToOtherPage());
    }

    private void descTime() {
        if (mTime < 1) {
            goToOtherPage();
        } else {
            mTimeView.setText(MessageFormat.format("{0}{1}", getResources().getString(R.string.splash_btn_text), mTime));
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    --mTime;
                    descTime();
                }
            }, 1000);
        }
    }

    private void goToOtherPage() {
        if (mHandler != null)
            mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initPermission() {
        requestRunPermission(DevicePermissionsUtils.autoObtainNeedAllPermission(this), new BaseActivity.PermissionListener() {
            @Override
            public void onGranted() {
                Logger.d("权限允许--cityCode->" + UserInfo.getCityCode());
                if (UserInfo.getCityCode() == null || UserInfo.getCityCode().equals("")) {
                    Intent service = new Intent(SplashActivity.this, LocationService.class);
                    startService(service);
                }
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                Logger.d("权限拒绝");
//                if (isFirst) {
//                    mApp.getConfirmAgreementDialog().show("服务协议和隐私政策", new ConfirmAgreementDialog.ConfirmCallback() {
//                        @Override
//                        public void onOk() {
//                            PreferenceUtil.put("isFirst", false);
//                            mTimeView.setVisibility(View.VISIBLE);
//                            descTime();
//                        }
//
//                        @Override
//                        public void onCancel() {
//                            ActivityManager.getInstance().kill();
//                        }
//                    });
//                } else {
//                    mTimeView.setVisibility(View.VISIBLE);
//                    descTime();
//                }
//                mTimeView.setOnClickListener(v -> goToOtherPage());
            }
        });
        PreferenceUtil.put("isFirst", false);
        mTimeView.setVisibility(View.VISIBLE);
        descTime();
    }


    @Override
    public void queryBannerDataListSuccess(String str) {
        if (!TextUtils.isEmpty(str)) {
            goHomePage(str);
        } else {
            ToastUtils.showTextToas(GoHomeApplication.getContext(), "200:服务器出错，请重试");
        }
    }

    private void goHomePage(String image) {
        Glide.with(GoHomeApplication.getContext()).load(image).into(mWelcome_ad);
        // mTimeView.setOnClickListener(v -> goToOtherPage());
    }

    @Override
    public void queryBannerDataListFail(int code, String msg) {
        ToastUtils.showTextToas(GoHomeApplication.getContext(), msg);
        mWelcome_ad.setBackgroundResource(R.mipmap.networkerror);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        bannerPresenter.queryBannerDataList("", "1", "");
    }

}
