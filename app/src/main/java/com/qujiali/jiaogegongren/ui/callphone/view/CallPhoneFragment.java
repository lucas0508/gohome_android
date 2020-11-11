package com.qujiali.jiaogegongren.ui.callphone.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.CallPhoneEntity;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.BaseFragment;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.common.citypicker.CityPicker;
import com.qujiali.jiaogegongren.common.citypicker.adapter.OnPickListener;
import com.qujiali.jiaogegongren.common.citypicker.model.City;
import com.qujiali.jiaogegongren.common.citypicker.model.LocateState;
import com.qujiali.jiaogegongren.common.citypicker.model.LocatedCity;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.server.LocationService;
import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;
import com.qujiali.jiaogegongren.ui.callphone.presenter.CallPhonePresenter;
import com.qujiali.jiaogegongren.ui.main.activity.MainActivity;
import com.qujiali.jiaogegongren.ui.main.activity.SplashActivity;
import com.qujiali.jiaogegongren.ui.other.view.WebActivity;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;

import static com.qujiali.jiaogegongren.common.global.Constants.WEB_KEY_FLAG;
import static com.qujiali.jiaogegongren.common.global.Constants.WEB_KEY_URL;
import static org.greenrobot.eventbus.EventBus.TAG;

public class CallPhoneFragment extends BaseFragment implements View.OnClickListener, ICallPhoneView {
    private CallPhonePresenter callPhonePresenter = new CallPhonePresenter(this);
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.ib_close)
    ImageButton mClose;
    @BindView(R.id.iv_callPhone)
    LinearLayout iv_callPhone;
    @BindView(R.id.tv_callPhone)
    TextView tv_callPhone;
    @BindView(R.id.tv_choose_city)
    TextView tv_choose_city;
    @BindView(R.id.tv_quotation)
    TextView tv_quotation;
    @BindView(R.id.tv_feedbackCall)
    TextView tv_feedbackCall;
    @BindView(R.id.tv_serviceType)
    TextView tv_serviceType;
    @BindView(R.id.tv_serviceType2)
    TextView tv_serviceType2;
    @BindView(R.id.ll_openBusiness)
    LinearLayout ll_openBusiness;
    @BindView(R.id.ll_NoBusiness)
    LinearLayout ll_NoBusiness;

    @BindView(R.id.tv_serviceIntroduction)
    TextView tv_serviceIntroduction;


    public static CallPhoneFragment instance;
    private String callWorkerNumber;
    private String cityCode;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_callphone;
    }

    @Override
    protected void initView() {
        instance = this;
        cityCode = UserInfo.getCityCode();
        mTitle.setText("电话叫工");
        mClose.setVisibility(View.GONE);
        Objects.requireNonNull(getActivity()).setTheme(R.style.DefaultCityPickerTheme);
        mApp.getLoadingDialog().show();
        callPhonePresenter.queryWorker(cityCode);
        initData();
    }

    private void initData() {
        //Glide.with(this).asGif().load(R.mipmap.icon_callphonegif).into(iv_callPhone);
        tv_choose_city.setOnClickListener(this);
        tv_quotation.setOnClickListener(this);
        tv_feedbackCall.setOnClickListener(this);
        iv_callPhone.setOnClickListener(this);

//        String ServiceType = "<font color=\"#0498f5\">深度清洁、家具清洗、房屋维修、家电维修、管道疏通、洗窗帘、除甲醛、搬家、企业保洁</font>";
//        SpannableString spannableString = new SpannableString(Html.fromHtml("\u3000\u3000我们叫工的服务范围包括" + ServiceType +
//                "等项目。如需更多服务，请拨打上方电话咨询，我们将竭诚为你服务。"));
        //段落缩进 \u3000 代表一个字符

        String text ="<font color=\"#01A8FD\">家政服务</font> ：擦玻璃 开荒 保洁 搬家  地暖管清洗 <br>管道疏通 瓷砖美缝  除甲醛 家电清洗 软包清洗 <br>家具美容保养 酒店大型油烟机清洗  开锁等";

        SpannableString string = new SpannableString(Html.fromHtml(text));
        tv_serviceType.setText(string);

        String text2 ="<font color=\"#01A8FD\">装修用工</font> ：油工 木工 瓦工 水电暖 设计师 杂工等";

        SpannableString string2 = new SpannableString(Html.fromHtml(text2));
        tv_serviceType2.setText(string2);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_choose_city:
                if (TextUtils.isEmpty(UserInfo.getCityCode())) {
//                    MainActivity.mainActivity.requestRunPermission(DevicePermissionsUtils.autoObtainNeedAllPermission(getActivity()), new BaseActivity.PermissionListener() {
//                        @Override
//                        public void onGranted() {
//                            Logger.d("权限允许--cityCode->" +UserInfo.getCityCode());
//                            if (UserInfo.getCityCode() == null || UserInfo.getCityCode().equals("")) {
//                                Intent service = new Intent(getActivity(), LocationService.class);
//                                getActivity().startService(service);
//                            }
//                        }
//
//                        @Override
//                        public void onDenied(List<String> deniedPermission) {
//                            Logger.d("权限拒绝");
//                            //initPermission();
//                            //userRefusePermissionsDialog();
//                        }
//                    });
                    Intent service = new Intent(getActivity(), LocationService.class);
                    getActivity().startService(service);
                }

                CityPicker.from(getActivity())
                        .enableAnimation(true)
                        .setLocatedCity(null)
                        .setOnPickListener(new OnPickListener() {
                            @Override
                            public void onPick(int position, City data) {
                                //String.format("当前城市：%s，", data.getName())
                                tv_choose_city.setText(data.getName());
//                                Toast.makeText(
//                                        GoHomeApplication.getContext(),
//                                        String.format("点击的数据：%s，%s", data.getName(), data.getCode()),
//                                        Toast.LENGTH_SHORT)
//                                        .show();
                                cityCode = data.getCode();
                                callPhonePresenter.queryWorker(cityCode);
                            }

                            @Override
                            public void onCancel() {
                                Toast.makeText(GoHomeApplication.getContext(), "取消选择", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLocate() {
                                Log.e(TAG, "onLocate: " + "zhixinglema ");
                                //开始定位，这里模拟一下定位
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        setData();
                                    }
                                }, 3000);
                            }
                        })
                        .show();
                break;
            case R.id.tv_quotation:
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra(WEB_KEY_URL, GlobalConstants.APP_QUOTATION_H5+cityCode);
                intent.putExtra(WEB_KEY_FLAG, 2);
                startActivity(intent);
                break;
            case R.id.tv_feedbackCall:

                break;
            case R.id.iv_callPhone:
                callPhone(callWorkerNumber);
                break;
        }
    }

    public void setData() {
        // TODO: 2020/9/18 开启定位
         CityPicker.from(getActivity()).locateComplete(new LocatedCity(UserInfo.getCityCodeName(), "", UserInfo.getCityCode()), LocateState.SUCCESS);
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void queryCallPhoneSuccess(CallPhoneEntity callPhoneEntity) {
        mApp.getLoadingDialog().hide();
        if (null != callPhoneEntity) {
            ll_openBusiness.setVisibility(View.VISIBLE);
            ll_NoBusiness.setVisibility(View.GONE);
            tv_quotation.setVisibility(View.VISIBLE);
            callWorkerNumber = callPhoneEntity.getCallWorkerNumber();
            tv_callPhone.setText(callWorkerNumber);
            tv_feedbackCall.setText("售后（投诉）电话：" + callPhoneEntity.getComplaintNumber());
        } else {
            tv_quotation.setVisibility(View.GONE);
            ll_openBusiness.setVisibility(View.GONE);
            ll_NoBusiness.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void queryCallPhoneFail(String info) {
        mApp.getLoadingDialog().hide();
    }
}

