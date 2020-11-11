package com.qujiali.jiaogegongren.ui.main.activity;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.VersionUpdateEntity;
import com.qujiali.jiaogegongren.common.base.ActivityManager;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.common.dialog.ConfirmAgreementDialog;
import com.qujiali.jiaogegongren.common.dialog.ConfirmDialog;
import com.qujiali.jiaogegongren.common.dialog.DialogManage;
import com.qujiali.jiaogegongren.common.dialog.VersionUpdateDialog;
import com.qujiali.jiaogegongren.common.model.CustomScrollViewPager;
import com.qujiali.jiaogegongren.common.server.LocationService;
import com.qujiali.jiaogegongren.common.utils.CompareVersions;
import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;
import com.qujiali.jiaogegongren.common.utils.PreferenceUtil;
import com.qujiali.jiaogegongren.common.utils.ToastUtils;
import com.qujiali.jiaogegongren.ui.callphone.view.CallPhoneFragment;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.VersionUpdatePresenter;
import com.qujiali.jiaogegongren.ui.main.fragment.view.HomePageFragment;
import com.qujiali.jiaogegongren.ui.main.fragment.view.IVersionUpdateView;
import com.qujiali.jiaogegongren.ui.main.fragment.view.MineFragment;
import com.qujiali.jiaogegongren.ui.main.fragment.view.RecruitmentFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements IVersionUpdateView {
    private VersionUpdatePresenter versionUpdatePresenter = new VersionUpdatePresenter(this);


    @BindView(R.id.vp_content)
    CustomScrollViewPager mViewPager;
    MainViewPagerAdapter mAdapter;
    List<Fragment> fragmentList = new ArrayList<>();
    private long exitTime = 0;
    public static MainActivity mainActivity;

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mApp = new DialogManage(this);
        initPermission();
        versionUpdatePresenter.updateVersion();
        initViewPager();
    }
    public void initPermission() {
        requestRunPermission(DevicePermissionsUtils.autoObtainNeedAllPermission(this), new BaseActivity.PermissionListener() {
            @Override
            public void onGranted() {
                Logger.d("权限允许--cityCode->" + UserInfo.getCityCode());
                if (UserInfo.getCityCode() == null || UserInfo.getCityCode().equals("")) {
                    Intent service = new Intent(MainActivity.this, LocationService.class);
                    startService(service);
                }
            }

            @Override
            public void onDenied(List<String> deniedPermission) {
                Logger.d("权限拒绝");
                initPermission();
                //userRefusePermissionsDialog();
            }
        });

    }
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    private void initViewPager() {
        fragmentList.add(new HomePageFragment());
        fragmentList.add(new RecruitmentFragment());
        fragmentList.add(new CallPhoneFragment());
        fragmentList.add(new MineFragment());
        mAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        mViewPager.setAdapter(mAdapter);
    }

    @OnClick({R.id.rb_tab_menu_homepage, R.id.rb_tab_menu_material,
            R.id.rb_tab_menu_mypage,R.id.rb_tab_menu_callWorker})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_tab_menu_homepage:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rb_tab_menu_material:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rb_tab_menu_callWorker:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rb_tab_menu_mypage:
                mViewPager.setCurrentItem(3);
                break;
            default:
                break;
        }
    }



    /**
     * 退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 判断2次点击事件时间
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtils.showTextToas(getApplicationContext(), "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                //   finish();
                ActivityManager.getInstance().kill();
            }
        }
        return false;
    }

    /**
     * 判断是否是同一天
     *
     * @param millis1
     * @param millis2
     * @param timeZone
     * @return
     */
    public static boolean isSameDay(long millis1, long millis2, TimeZone timeZone) {
        long interval = millis1 - millis2;
        return interval < 86400000 && interval > -86400000 && millis2Days(millis1, timeZone) == millis2Days(millis2, timeZone);
    }

    private static long millis2Days(long millis, TimeZone timeZone) {
        return (((long) timeZone.getOffset(millis)) + millis) / 86400000;
    }

    /**
     * 用户拒绝了权限申请提醒
     */
    public void userRefusePermissionsDialog() {
        mApp.getConfirmDialog().show("需要开启定位权限才能使用此功能，否则无法正常使用",
                "设置", "取消", new ConfirmDialog.ConfirmCallback() {
                    @Override
                    public void onOk() {
                        //引导用户到设置中去进行设置
                        Intent intent = new Intent();
                        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                        intent.setData(Uri.fromParts("package", getPackageName(), null));
                        startActivity(intent);
                    }

                    @Override
                    public void onCancel() {
                        ActivityManager.getInstance().kill();
                    }
                });
    }

    @Override
    public void updateVersionSuccess(VersionUpdateEntity versionUpdateEntity) {
        String clientVersion = versionUpdateEntity.getClientVersion();
        String downloadUrl = versionUpdateEntity.getDownloadUrl();
        int updateInstall = versionUpdateEntity.getUpdateInstall();
        if (CompareVersions.compare(clientVersion, DevicePermissionsUtils.getAppCurrentVersion())) {
            VersionUpdateDialog versionUpdateDialog = new VersionUpdateDialog(MainActivity.this);
            versionUpdateDialog.showNoticeDialog(clientVersion, String.valueOf(updateInstall), downloadUrl);
        }
    }

    @Override
    public void updateVersionFail(String info) {
        mApp.shortToast(info);
    }


}
