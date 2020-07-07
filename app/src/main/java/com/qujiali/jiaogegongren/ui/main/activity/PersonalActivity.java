package com.qujiali.jiaogegongren.ui.main.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.UserInfoEntity;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.MyAppGlideModule;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.common.dialog.SelectPicDialog;
import com.qujiali.jiaogegongren.common.dialog.SelectSexDialog;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils;
import com.qujiali.jiaogegongren.ui.login.view.activity.LoginActivity;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.LogoutPresenter;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.MinePresenter;
import com.qujiali.jiaogegongren.ui.main.fragment.view.ILogOutView;
import com.qujiali.jiaogegongren.ui.main.fragment.view.IMineFragmentView;
import com.qujiali.jiaogegongren.ui.other.presenter.UploadFilePresenter;
import com.qujiali.jiaogegongren.ui.other.view.IUploadFileView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class PersonalActivity extends BaseActivity implements IMineFragmentView, IUploadFileView, ILogOutView {

    private MinePresenter minePresenter = new MinePresenter(this);
    private UploadFilePresenter uploadFilePresenter = new UploadFilePresenter(this);
    private LogoutPresenter logoutPresenter = new LogoutPresenter(this);
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_common_right)
    TextView tv_common_right;
    @BindView(R.id.ll_person_header_image)
    LinearLayout ll_person_header_image;
    @BindView(R.id.ll__person_gender)
    LinearLayout ll__person_gender;
    @BindView(R.id.ll_person_birthday)
    LinearLayout ll_person_birthday;
    @BindView(R.id.et__person_nickName)
    EditText et__person_nickName;
    @BindView(R.id.et__person_phone)
    EditText et__person_phone;
    @BindView(R.id.tv__person_birthday)
    TextView tv__person_birthday;
    @BindView(R.id.tv__person_gender)
    TextView tv__person_gender;
    @BindView(R.id.riv_headImage)
    RoundImageView riv_Image;
    @BindView(R.id.btn_logout)
    Button btn_logout;

    private String userSex = "";
    private String imageUrlPath = "";

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initView() {
        mTitle.setText("个人信息");
        tv_common_right.setText("修改");
        tv_common_right.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                submit();
            }
        });
        mApp.getLoadingDialog().show();
        minePresenter.queryUserInfo();
        initData();
    }


    private void initData() {

        ll__person_gender.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                SelectSexDialog selectSexDialog = new SelectSexDialog(PersonalActivity.this);
                selectSexDialog.show(new SelectSexDialog.SelectSexListener() {
                    @Override
                    public void setSelectSex(String str, String sex) {
                        tv__person_gender.setText(str);
                        userSex = sex;
                    }
                });
            }
        });
        ll_person_birthday.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                chooseBirthday();
            }
        });
        if (!TextUtils.isEmpty(et__person_phone.getText().toString())) {
            et__person_phone.setEnabled(false);
        }
        ll_person_header_image.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                SelectPicDialog selectPicDialog = new SelectPicDialog(PersonalActivity.this);
                selectPicDialog.show(new SelectPicDialog.Callback() {
                    @Override
                    public void clickItem(int position) {
                        if (position == 0) {
                            PhotoUtils.autoObtainCameraPermission(getContext(), true);
                        } else if (position == 1) {
                            PhotoUtils.autoObtainStoragePermission(getContext(), true);
                        }
                    }
                });
            }
        });

        btn_logout.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                mApp.getLoadingDialog().show();
                logoutPresenter.logOut();
            }
        });
    }

    private void chooseBirthday() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //选择时间之后的回掉方法，可以获取年月日
                tv__person_birthday.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                int mMoth = month + 1;
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), calendar.get(Calendar.DAY_OF_MONTH));//1990，0，01分别是默认显示的年，月，日的数据，其中的月份是从0开始的，所以一月份为0；
        //设置日期的范围
        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(new Date().getTime());//设置日期的上限日期
        //datePicker.setMinDate(...);//设置日期的下限日期，其中是参数类型是long型，为日期的时间戳
        datePickerDialog.show();
    }

    private void submit() {
        mApp.getLoadingDialog().show();
        if (TextUtils.isEmpty(userSex)) {
            String trim = tv__person_gender.getText().toString().trim();
            if (trim.equals("男")) {
                userSex = "1";
            } else if (trim.equals("女")) {
                userSex = "2";
            } else {
                userSex = "0";
            }
        }
        if (!TextUtils.isEmpty(imageUrlPath)) {
            List<String> strings = new ArrayList<>();
            strings.add(imageUrlPath);
            uploadFilePresenter.uploadMultipleImage(strings);
        } else {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("birthday", tv__person_birthday.getText().toString().trim());
            objectMap.put("name", et__person_nickName.getText().toString().trim());
            objectMap.put("phone", et__person_phone.getText().toString().trim());
            objectMap.put("sex", userSex);
            minePresenter.submitUserInfo(objectMap);
        }
    }

    @Override
    public void getUserInfoSuccess(UserInfoEntity userInfoEntity) {
        mApp.getLoadingDialog().hide();
        Glide.with(this).load(userInfoEntity.getProfile()).into(riv_Image);
        et__person_nickName.setText(userInfoEntity.getName());
        et__person_phone.setText(userInfoEntity.getPhone());
        tv__person_gender.setText(userInfoEntity.getSexText());
        tv__person_birthday.setText(userInfoEntity.getBirthday());

        if (!TextUtils.isEmpty(userInfoEntity.getPhone())){
            et__person_phone.setFocusable(false);
        }
        if(!TextUtils.isEmpty(userInfoEntity.getBirthday())){
            tv__person_birthday.setOnClickListener(null);
        }
        if(!TextUtils.isEmpty(userInfoEntity.getSexText())){
            ll__person_gender.setOnClickListener(null);
        }
    }

    @Override
    public void getUserInfoFail(String info,int code ) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }

    @Override
    public void submitUserInfoSuccess() {
        mApp.getLoadingDialog().hide();
        mApp.shortToast("修改成功");
        finish();
    }

    @Override
    public void submitUserInfoFail(String info) {
        mApp.getLoadingDialog().show();
        mApp.shortToast(info);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        PhotoUtils.onActivityResult(requestCode, resultCode, data, getContext(), new PhotoUtils.CropHandler() {
            @Override
            public void handleCropResult(final Uri uri, int tag) {
                Glide.with(GoHomeApplication.getContext()).applyDefaultRequestOptions(MyAppGlideModule.getRequestOptions())
                        .load(uri.getPath()).into(riv_Image);
                imageUrlPath = uri.getPath();
                Logger.d(uri.getPath());
            }

            @Override
            public void handleCropError(Intent data) {
                mApp.shortToast("图片错误");
            }
        });
    }

    @Override
    public void UploadMultipleFileSuccess(List<String> strings) {
        mApp.getLoadingDialog().hide();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("birthday", tv__person_birthday.getText().toString().trim());
        objectMap.put("name", et__person_nickName.getText().toString().trim());
        objectMap.put("phone", et__person_phone.getText().toString().trim());
        objectMap.put("profile", strings.get(0));
        objectMap.put("sex", userSex);
        minePresenter.submitUserInfo(objectMap);
    }

    @Override
    public void UploadMultipleFileFail(String msg) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(msg);
    }


    @Override
    public void logoutSuccess() {
        mApp.getLoadingDialog().hide();
        UserInfo.removeUserInfo();
        mApp.shortToast("退出成功");
        startActivity(LoginActivity.class);
        finish();
    }

    @Override
    public void logoutFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }

}
