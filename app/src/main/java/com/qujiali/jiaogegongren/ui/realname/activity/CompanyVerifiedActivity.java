package com.qujiali.jiaogegongren.ui.realname.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.CompanyEntity;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.MyAppGlideModule;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.dialog.SelectPicDialog;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils;
import com.qujiali.jiaogegongren.ui.other.presenter.UploadFilePresenter;
import com.qujiali.jiaogegongren.ui.other.view.IUploadFileView;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.model.CompanyAuthModel;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.model.impl.ICompanyAuthModel;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.presenter.CompanyAuthPresenter;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.view.ICompanyAuthView;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.SkillCertificationWorkerActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils.CAMERA_PERMISSIONS_REQUEST_CODE;
import static com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils.openCamera;

public class CompanyVerifiedActivity extends BaseActivity implements IUploadFileView, ICompanyAuthView {

    private UploadFilePresenter uploadFilePresenter = new UploadFilePresenter(this);
    private CompanyAuthPresenter companyAuthPresenter = new CompanyAuthPresenter(this);

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_common_right)
    TextView mCommonRight;
    @BindView(R.id.riv_Image)
    RoundImageView riv_Image;
    @BindView(R.id.et_companyName)
    EditText et_companyName;
    @BindView(R.id.et_companyAddress)
    EditText et_companyAddress;
    @BindView(R.id.tv_denial_reason)
    TextView tv_denial_reason;
    @BindView(R.id.rel_refuse)
    RelativeLayout rel_refuse;


    private String imageUrlPath = "";

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_company_verified;
    }

    @Override
    protected void initView() {
        mTitle.setText("企业认证");
        mCommonRight.setText("确定");
        mCommonRight.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                postData();
            }
        });


        riv_Image.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                mApp.getSelectPicDialog()
                        .show(new SelectPicDialog.Callback() {
                            @Override
                            public void clickItem(int position) {
                                if (position == 0) {
                                    PhotoUtils.autoObtainCameraPermission(getContext(), false);
                                } else if (position == 1) {
                                    PhotoUtils.autoObtainStoragePermission(getContext(), false);
                                }
                            }
                        });
            }
        });


        initData();
    }

    private void initData() {
        mApp.getLoadingDialog().show();
        companyAuthPresenter.queryCompanyAuth();
    }

    private void postData() {

        if (TextUtils.isEmpty(et_companyName.getText().toString().trim())) {
            mApp.shortToast("请输入企业名称");
            return;
        }
        if (TextUtils.isEmpty(et_companyAddress.getText().toString().trim())) {
            mApp.shortToast("请输入企业地址");
            return;
        }
        if (TextUtils.isEmpty(imageUrlPath)) {
            mApp.shortToast("请营业执照");
            return;
        }
        mApp.getLoadingDialog().show();
        List<String> strings = new ArrayList<>();
        strings.add(imageUrlPath);
        uploadFilePresenter.uploadMultipleImage(strings);
    }

    @Override
    public void UploadMultipleFileSuccess(List<String> strings) {
        companyAuthPresenter.sendCompanyAuth(et_companyName.getText().toString().trim()
                , et_companyAddress.getText().toString().trim()
                , strings.get(0));
    }

    @Override
    public void UploadMultipleFileFail(String msg) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(msg);
    }

    @Override
    public void sendCompanyAuthSuccess() {
        mApp.getLoadingDialog().hide();
        mApp.shortToast("提交成功");
        finish();
    }

    @Override
    public void sendCompanyAuthFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }

    @Override
    public void queryCompanyAuthSuccess(CompanyEntity companyEntity) {
        mApp.getLoadingDialog().hide();
        if (companyEntity != null) {
            if (companyEntity.getStatus() == 2) {
                mCommonRight.setText("重新提交");
                if (!TextUtils.isEmpty(companyEntity.getRefuseReason())){
                    rel_refuse.setVisibility(View.VISIBLE);
                    tv_denial_reason.setText(companyEntity.getRefuseReason());
                }
            } else {
                et_companyAddress.setEnabled(false);
                et_companyName.setEnabled(false);
                riv_Image.setOnClickListener(null);
                mCommonRight.setVisibility(View.GONE);
            }


            et_companyAddress.setText(companyEntity.getAddress());
            et_companyName.setText(companyEntity.getName());
            Glide.with(this).load(companyEntity.getPicture()).into(riv_Image);
        }
    }

    @Override
    public void queryCompanyAuthFail(String info) {
        mApp.getLoadingDialog().hide();
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

}
