package com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.view;

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
import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.bean.WorkerProficiencyEntity;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.MyAppGlideModule;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
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
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.IPerfectInformationView;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.IWorkerProficiencyView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils.CAMERA_PERMISSIONS_REQUEST_CODE;
import static com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils.openCamera;

public class PerfectInformationEnterpriseActivity extends BaseActivity implements  IPerfectInformationView, IUploadFileView {

    PerfectInformationPresenter perfectInformationPresenter = new PerfectInformationPresenter(this);
    UploadFilePresenter uploadFilePresenter = new UploadFilePresenter(this);
    /**
     * 1 ： 个人入驻
     * 2： 企业入驻
     */
    private String type;
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.scroll_personalsettlement)
    ScrollView scroll_personalsettlement;
    @BindView(R.id.scroll_enterprisesettledsn)
    ScrollView scroll_enterprisesettledsn;

    private ArrayList<AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean> cityString = new ArrayList<>();
    private String cityName;
    //sex 1  男
    private String imageUrlPath = null, userSex, birthday, degree;
    private String workerSkill = "";
    //-------------企业入驻-------------------------
    @BindView(R.id.et_company_name)
    EditText et_company_name;
    @BindView(R.id.et_company_abbreviation)
    EditText et_company_abbreviation;
    @BindView(R.id.et_company_phone)
    EditText et_company_phone;
    @BindView(R.id.et_company_business)
    EditText et_company_business;
    @BindView(R.id.et_company_skill1)
    EditText et_company_skill1;
    @BindView(R.id.et_company_skill2)
    EditText et_company_skill2;
    @BindView(R.id.et_company_skill3)
    EditText et_company_skill3;
    @BindView(R.id.et_company_skill4)
    EditText et_company_skill4;
    @BindView(R.id.tv_company_address)
    TextView tv_company_address;
    @BindView(R.id.btn_company_submit)
    Button btn_company_submit;
    @BindView(R.id.riv_company_logo)
    RoundImageView riv_company_logo;

    private SettlelnCompanyEntity.BSettledEnterpriseVoBean bSettledEnterpriseVo;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_perfect_information;
    }


    @Override
    protected void initView() {
        mTitle.setText("完善信息");
        bSettledEnterpriseVo = (SettlelnCompanyEntity.BSettledEnterpriseVoBean) getIntent().getSerializableExtra("bSettledEnterpriseVo");
        scroll_personalsettlement.setVisibility(View.GONE);
        scroll_enterprisesettledsn.setVisibility(View.VISIBLE);

        initData();
        initOnClick();
    }

    private void initData() {
        if (bSettledEnterpriseVo != null) {
            Glide.with(this).load(bSettledEnterpriseVo.getProfile()).into(riv_company_logo);
            et_company_name.setText(bSettledEnterpriseVo.getName());
            et_company_phone.setText(bSettledEnterpriseVo.getPhone());
            et_company_abbreviation.setText(bSettledEnterpriseVo.getShortName());
            et_company_business.setText(bSettledEnterpriseVo.getProfession());
            tv_company_address.setText(bSettledEnterpriseVo.getAreaList());
            List<String> strings = DevicePermissionsUtils.stringToListByXG(bSettledEnterpriseVo.getWorkType());
            int length = strings.size();
            if (length > 0) {
                if (length == 1) {
                    et_company_skill1.setText(strings.get(0));
                }
                if (length == 2) {
                    et_company_skill1.setText(strings.get(0));
                    et_company_skill2.setText(strings.get(1));
                }
                if (length == 3) {
                    et_company_skill1.setText(strings.get(0));
                    et_company_skill2.setText(strings.get(1));
                    et_company_skill3.setText(strings.get(2));
                }
                if (length == 4) {
                    et_company_skill1.setText(strings.get(0));
                    et_company_skill2.setText(strings.get(1));
                    et_company_skill3.setText(strings.get(2));
                    et_company_skill4.setText(strings.get(3));
                }
            }
            AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean childBean = new AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean();
            childBean.setAdCode(bSettledEnterpriseVo.getServiceArea());
            cityString.add(childBean);
            imageUrlPath =bSettledEnterpriseVo.getProfile();
        }
    }

    private void initOnClick() {
        riv_company_logo.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                SelectPicDialog selectPicDialog = new SelectPicDialog(PerfectInformationEnterpriseActivity.this);
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
        tv_company_address.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                showAddressPickerPop();
            }
        });

        btn_company_submit.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                postCompanyData();
            }
        });
    }

    /**
     * 企业完善信息
     */
    private void postCompanyData() {

        if (TextUtils.isEmpty(et_company_name.getText().toString().trim())) {
            mApp.shortToast("请输入公司名称");
            return;
        }
        if (TextUtils.isEmpty(et_company_abbreviation.getText().toString().trim())) {
            mApp.shortToast("请输入公司简称");
            return;
        }
        if (TextUtils.isEmpty(et_company_business.getText().toString().trim())) {
            mApp.shortToast("请简短描述公司主营12个字内");
            return;
        }
        if (TextUtils.isEmpty(et_company_phone.getText().toString().trim())) {
            mApp.shortToast("请输入联系方式");
            return;
        }
        if (TextUtils.isEmpty(et_company_skill1.getText().toString().trim()) && TextUtils.isEmpty(et_company_skill2.getText().toString().trim()) &&
                TextUtils.isEmpty(et_company_skill3.getText().toString().trim()) && TextUtils.isEmpty(et_company_skill4.getText().toString().trim())) {
            mApp.shortToast("请输入技能");
            return;
        }
        if (!TextUtils.isEmpty(et_company_skill1.getText().toString().trim())) {
            workerSkill = et_company_skill1.getText().toString().trim();
        }
        if (!TextUtils.isEmpty(et_company_skill2.getText().toString().trim())) {
            if (!TextUtils.isEmpty(workerSkill)) {
                workerSkill = workerSkill + "/" + et_company_skill2.getText().toString().trim();
            } else {
                workerSkill = et_company_skill2.getText().toString().trim();
            }
        }
        if (!TextUtils.isEmpty(et_company_skill3.getText().toString().trim())) {
            if (!TextUtils.isEmpty(workerSkill)) {
                workerSkill = workerSkill + "/" + et_company_skill3.getText().toString().trim();
            } else {
                workerSkill = et_company_skill3.getText().toString().trim();
            }
        }
        if (!TextUtils.isEmpty(et_company_skill4.getText().toString().trim())) {
            if (!TextUtils.isEmpty(workerSkill)) {
                workerSkill = workerSkill + "/" + et_company_skill4.getText().toString().trim();
            } else {
                workerSkill = et_company_skill4.getText().toString().trim();

            }
        }
        if (cityString.size() > 0) {
        } else {
            mApp.shortToast("请选择服务地区");
            return;
        }
        mApp.getLoadingDialog().show();
        List<String> strings = new ArrayList<>();
        strings.add(imageUrlPath);
        if (imageUrlPath.startsWith("http")){
            goSendDataToServer(strings);
        }else {
            uploadFilePresenter.uploadMultipleImage(strings);
        }
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
                    tv_company_address.setText("全国-不限-不限");
                } else {
                    tv_company_address.setText(cityName);
                }
            }
        } else {
            PhotoUtils.onActivityResult(requestCode, resultCode, data, getContext(), new PhotoUtils.CropHandler() {
                @Override
                public void handleCropResult(final Uri uri, int tag) {
                    Glide.with(GoHomeApplication.getContext()).applyDefaultRequestOptions(MyAppGlideModule.getRequestOptions())
                            .load(uri.getPath()).into(riv_company_logo);
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
        objectMap.put("name", et_company_name.getText().toString().trim());
        objectMap.put("shortName", et_company_abbreviation.getText().toString().trim());
        objectMap.put("profile", strings.get(0));
        objectMap.put("serviceArea", cityString.get(0).getAdCode());
        objectMap.put("phone", et_company_phone.getText().toString().trim());
        objectMap.put("profession", et_company_business.getText().toString().trim());
        objectMap.put("workType", workerSkill);
        Logger.d("提交数据:" + objectMap);
        mApp.getLoadingDialog().show();
        perfectInformationPresenter.sendCompanyPerfectInformation(objectMap);
    }

    @Override
    public void UploadMultipleFileFail(String msg) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(msg);
    }
}
