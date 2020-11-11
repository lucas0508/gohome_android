package com.qujiali.jiaogegongren.ui.settlein.workersettlin.view;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.AdressDataEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.bean.WorkerProficiencyEntity;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.MyAppGlideModule;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.dialog.DateWheelPicker2;
import com.qujiali.jiaogegongren.common.dialog.DialogUtil;
import com.qujiali.jiaogegongren.common.dialog.SelectDialog;
import com.qujiali.jiaogegongren.common.dialog.SelectPicDialog;
import com.qujiali.jiaogegongren.common.dialog.SelectSexDialog;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils;
import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;
import com.qujiali.jiaogegongren.ui.other.presenter.UploadFilePresenter;
import com.qujiali.jiaogegongren.ui.other.view.AddressActivity;
import com.qujiali.jiaogegongren.ui.other.view.IUploadFileView;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter.PerfectInformationPresenter;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter.WorkerProficiencyPresenter;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.itimetraveler.widget.pickerselector.DateWheelPicker;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils.CAMERA_PERMISSIONS_REQUEST_CODE;
import static com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils.openCamera;

public class PerfectInformationActivity extends BaseActivity implements IWorkerProficiencyView, IPerfectInformationView, IUploadFileView {

    WorkerProficiencyPresenter workerProficiencyPresenter = new WorkerProficiencyPresenter(this);
    PerfectInformationPresenter perfectInformationPresenter = new PerfectInformationPresenter(this);
    UploadFilePresenter uploadFilePresenter = new UploadFilePresenter(this);

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.scroll_personalsettlement)
    ScrollView scroll_personalsettlement;
    @BindView(R.id.scroll_enterprisesettledsn)
    ScrollView scroll_enterprisesettledsn;
    //----------工人入住-------------------------
    @BindView(R.id.riv_headImage)
    ImageView riv_headImage;
    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_speciality_describe)
    EditText et_speciality_describe;
    @BindView(R.id.tv_work_proficiency)
    TextView tv_work_proficiency;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.et_skill1)
    EditText et_skill1;
    @BindView(R.id.et_skill2)
    EditText et_skill2;
    @BindView(R.id.et_skill3)
    EditText et_skill3;
    @BindView(R.id.et_skill4)
    EditText et_skill4;
    private List<WorkerProficiencyEntity> workerProficiencyEntities;
    private ArrayList<AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean> cityString = new ArrayList<>();
    private String cityName;
    //sex 1  男
    private String imageUrlPath = null, userSex, birthday, degree;
    private String workerSkill = "";

    private SettlelnEntity.BsettledWorkerVoBean bsettledWorkerVo;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_perfect_information;
    }


    @Override
    protected void initView() {
        mTitle.setText("完善信息");
        bsettledWorkerVo = (SettlelnEntity.BsettledWorkerVoBean) getIntent().getSerializableExtra("bsettledWorkerVo");
        workerProficiencyPresenter.queryWorkerProficiency();
        scroll_personalsettlement.setVisibility(View.VISIBLE);
        scroll_enterprisesettledsn.setVisibility(View.GONE);

        initData();
        initOnClick();

    }

    private void initData() {
        if (bsettledWorkerVo != null) {
            Glide.with(this).load(bsettledWorkerVo.getProfile()).into(riv_headImage);
            et_name.setText(bsettledWorkerVo.getName());
            tv_sex.setText(bsettledWorkerVo.getSexText());
            tv_birthday.setText(bsettledWorkerVo.getBirthday());
            et_phone.setText(bsettledWorkerVo.getPhone());
            et_speciality_describe.setText(bsettledWorkerVo.getProfession());
            tv_work_proficiency.setText(bsettledWorkerVo.getDegreeText());
            tv_address.setText(bsettledWorkerVo.getAreaList());
            List<String> strings = DevicePermissionsUtils.stringToListByXG(bsettledWorkerVo.getWorkType());
            int length = strings.size();
            if (length > 0) {
                if (length == 1) {
                    et_skill1.setText(strings.get(0));
                }
                if (length == 2) {
                    et_skill1.setText(strings.get(0));
                    et_skill2.setText(strings.get(1));
                }
                if (length == 3) {
                    et_skill1.setText(strings.get(0));
                    et_skill2.setText(strings.get(1));
                    et_skill3.setText(strings.get(2));
                }
                if (length == 4) {
                    et_skill1.setText(strings.get(0));
                    et_skill2.setText(strings.get(1));
                    et_skill3.setText(strings.get(2));
                    et_skill4.setText(strings.get(3));
                }
            }
            AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean childBean = new AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean();
            childBean.setAdCode(bsettledWorkerVo.getServiceArea());
            cityString.add(childBean);
            userSex = bsettledWorkerVo.getSex() + "";
            degree = bsettledWorkerVo.getDegree();
            imageUrlPath = bsettledWorkerVo.getProfile();
            birthday = bsettledWorkerVo.getBirthday();
        }
    }

    private void initOnClick() {

        riv_headImage.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                SelectPicDialog selectPicDialog = new SelectPicDialog(PerfectInformationActivity.this);
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

        tv_sex.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                SelectSexDialog selectSexDialog = new SelectSexDialog(PerfectInformationActivity.this);
                selectSexDialog.show(new SelectSexDialog.SelectSexListener() {
                    @Override
                    public void setSelectSex(String str, String sex) {
                        tv_sex.setText(str);
                        userSex = sex;
                    }
                });
            }
        });

        tv_birthday.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
//                chooseBirthday();
                ChooseAge();
            }
        });
        tv_work_proficiency.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                //选择技能熟练度
                SelectDialog selectDialog = new SelectDialog(PerfectInformationActivity.this, workerProficiencyEntities);
                selectDialog.show((str, typeId) -> {
                    tv_work_proficiency.setText(str);
                    degree = typeId;
                    Logger.d("技能熟练度ID---->" + typeId);
                });
            }
        });
        tv_address.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                showAddressPickerPop();
            }
        });
        btn_submit.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                postWorkerData();
            }
        });
    }


    /**
     * 工人完善信息
     */
    private void postWorkerData() {
        if (imageUrlPath == null) {
            mApp.shortToast("请选择头像");
            return;
        }
        if (TextUtils.isEmpty(et_name.getText().toString().trim())) {
            mApp.shortToast("请输入昵称");
            return;
        }
        if (TextUtils.isEmpty(userSex)) {
            mApp.shortToast("请选择性别");
            return;
        }
        if (TextUtils.isEmpty(birthday)) {
            mApp.shortToast("请选择出生日期");
            return;
        }
        if (TextUtils.isEmpty(et_phone.getText().toString().trim())) {
            mApp.shortToast("请输入联系方式");
            return;
        }
        if (TextUtils.isEmpty(et_speciality_describe.getText().toString().trim())) {
            mApp.shortToast("请输入特长");
            return;
        }
        if (TextUtils.isEmpty(et_skill1.getText().toString().trim()) && TextUtils.isEmpty(et_skill2.getText().toString().trim()) &&
                TextUtils.isEmpty(et_skill3.getText().toString().trim()) && TextUtils.isEmpty(et_skill4.getText().toString().trim())) {
            mApp.shortToast("请输入技能");
            return;
        }

        if (!TextUtils.isEmpty(et_skill1.getText().toString().trim())) {
            workerSkill = et_skill1.getText().toString().trim();
        }
        if (!TextUtils.isEmpty(et_skill2.getText().toString().trim())) {
            if (!TextUtils.isEmpty(workerSkill)) {
                workerSkill = workerSkill + "/" + et_skill2.getText().toString().trim();
            } else {
                workerSkill = et_skill2.getText().toString().trim();
            }
        }
        if (!TextUtils.isEmpty(et_skill3.getText().toString().trim())) {
            if (!TextUtils.isEmpty(workerSkill)) {
                workerSkill = workerSkill + "/" + et_skill3.getText().toString().trim();
            } else {
                workerSkill = et_skill3.getText().toString().trim();
            }
        }
        if (!TextUtils.isEmpty(et_skill4.getText().toString().trim())) {
            if (!TextUtils.isEmpty(workerSkill)) {
                workerSkill = workerSkill + "/" + et_skill4.getText().toString().trim();
            } else {
                workerSkill = et_skill4.getText().toString().trim();

            }
        }
        if (cityString.size()>0) {}else {
            mApp.shortToast("请选择服务地区");
            return;
        }

        Logger.d("技能：" + workerSkill);
        mApp.getLoadingDialog().show();
        List<String> strings = new ArrayList<>();
        strings.add(imageUrlPath);
        if (imageUrlPath.startsWith("http")) {
            goSendDataToServer(strings);
        } else {
            uploadFilePresenter.uploadMultipleImage(strings);
        }
    }


    private void ChooseAge(){
        DateWheelPicker2 picker= new DateWheelPicker2(PerfectInformationActivity.this);
        picker.setMaxDate(Calendar.getInstance().getTimeInMillis());
        picker.setOnDateChangedListener(new DateWheelPicker2.OnDateChangedListener() {
            @Override
            public void onDateChanged(DateWheelPicker2 view, int year, int monthOfYear, int dayOfMonth) {
                tv_birthday.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                birthday = year + "-" + monthOfYear + "-" + dayOfMonth;

                Log.e("TAG", "onDateChanged: "+birthday );
            }
        });
        showDialog("请选择出生日期", picker);
    }

        private void showDialog(String title, View v) {
        DialogUtil.showDialog(PerfectInformationActivity.this, title, v);
    }

    private void chooseBirthday() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //选择时间之后的回掉方法，可以获取年月日
                tv_birthday.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                int mMoth = month + 1;
                birthday = year + "-" + mMoth + "-" + dayOfMonth;
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONDAY), calendar.get(Calendar.DAY_OF_MONTH));//1990，0，01分别是默认显示的年，月，日的数据，其中的月份是从0开始的，所以一月份为0；
        //设置日期的范围
        DatePicker datePicker = datePickerDialog.getDatePicker();
        datePicker.setMaxDate(new Date().getTime());//设置日期的上限日期
        //datePicker.setMinDate(...);//设置日期的下限日期，其中是参数类型是long型，为日期的时间戳
        datePickerDialog.show();
    }

    private void showAddressPickerPop() {
        Intent intent = new Intent(this, AddressActivity.class);
        startActivityForResult(intent, 0x2222);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0X4444) {
            if (requestCode == 0x2222) {
                cityString = data.getParcelableArrayListExtra("string");
                cityName = (String) data.getExtras().get("CityName");
                Log.e("传值", "选择城市------>: " + cityName);
                Log.e("传值", "onActivityResult: " + new Gson().toJson(cityString));
                if (cityName.contains("全国")) {
                    tv_address.setText("全国-不限-不限");
                } else {
                    tv_address.setText(cityName);
                }
            }
        } else {
            PhotoUtils.onActivityResult(requestCode, resultCode, data, getContext(), new PhotoUtils.CropHandler() {
                @Override
                public void handleCropResult(final Uri uri, int tag) {
                    Glide.with(GoHomeApplication.getContext()).applyDefaultRequestOptions(MyAppGlideModule.getRequestOptions())
                            .load(uri.getPath()).into(riv_headImage);

                    Logger.d(uri.getPath());
                    Luban.with(GoHomeApplication.getContext())
                            .load(uri.getPath())
                            .ignoreBy(100)
                            .setCompressListener(new OnCompressListener() {
                                @Override
                                public void onStart() {
                                }

                                @Override
                                public void onSuccess(File file) {
                                    try {
                                        imageUrlPath = file.getAbsolutePath();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                }
                            }).launch();
                }

                @Override
                public void handleCropError(Intent data) {
                    mApp.shortToast("图片错误");
                }
            });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Logger.e("权限回调", "--------------------------------");
        switch (requestCode) {
            case CAMERA_PERMISSIONS_REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted 授予权限
                    //处理授权之后逻辑
                    openCamera(this);
                } else {
                    // Permission Denied 权限被拒绝.
                    Toast.makeText(this, "权限被禁用", Toast.LENGTH_SHORT).show();
                    //   userRefusePermissionsDialog();
                }
                break;
            default:
                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void queryWorkerProficiencySuccess(List<WorkerProficiencyEntity> workerProficiencyEntities) {
        this.workerProficiencyEntities = workerProficiencyEntities;
    }

    @Override
    public void queryWorkerProficiencyFail() {

    }

    @Override
    public void sendPerfectInformationDataSuccess() {
        mApp.getLoadingDialog().hide();
        mApp.shortToast("提交成功");
        finish();
    }

    @Override
    public void sendPerfectInformationDataFail(String msg) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(msg);
    }

    @Override
    public void sendCompanyPerfectInformationDataSuccess() {
        mApp.getLoadingDialog().hide();
        mApp.shortToast("提交成功");
        finish();
    }

    @Override
    public void sendCompanyPerfectInformationDataFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }


    @Override
    public void UploadMultipleFileSuccess(List<String> strings) {
        goSendDataToServer(strings);
    }

    private void goSendDataToServer(List<String> strings) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("name", et_name.getText().toString().trim());
        objectMap.put("profile", strings.get(0));
        objectMap.put("serviceArea", cityString.get(0).getAdCode());
        objectMap.put("phone", et_phone.getText().toString().trim());
        objectMap.put("profession", et_speciality_describe.getText().toString().trim());
        objectMap.put("workType", workerSkill);
        objectMap.put("degree", degree);
        objectMap.put("birthday", birthday);
        objectMap.put("sex", userSex);
        Logger.d("提交数据:" + objectMap);
        perfectInformationPresenter.sendPerfectInformation(objectMap);
    }

    @Override
    public void UploadMultipleFileFail(String msg) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(msg);
    }
}
