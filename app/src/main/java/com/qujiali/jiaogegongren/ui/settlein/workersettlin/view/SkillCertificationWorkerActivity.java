package com.qujiali.jiaogegongren.ui.settlein.workersettlin.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.SkillCertificationEntity;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.MyAppGlideModule;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.dialog.SelectPicDialog;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils;
import com.qujiali.jiaogegongren.ui.other.presenter.UploadFilePresenter;
import com.qujiali.jiaogegongren.ui.other.view.IUploadFileView;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter.SkillCertificationPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils.CAMERA_PERMISSIONS_REQUEST_CODE;
import static com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils.openCamera;


public class SkillCertificationWorkerActivity extends BaseActivity implements ISkillCertificationView, IUploadFileView {

    private SkillCertificationPresenter skillCertificationPresenter = new SkillCertificationPresenter(this);
    private UploadFilePresenter uploadFilePresenter = new UploadFilePresenter(this);

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_common_right)
    TextView mCommonRight;
    @BindView(R.id.et_certification_name)
    EditText et_certification_name;
    @BindView(R.id.riv_Image)
    RoundImageView riv_Image;
    private String imageUrlPath = "";
    private String id="";

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_skillcertificationworker;
    }

    @Override
    protected void initView() {
        mTitle.setText("技能认证");
        mCommonRight.setText("确定");


        Intent intent = getIntent();

        if (null != intent) {
            SkillCertificationEntity item = getIntent().getParcelableExtra("item");
            if (null != item) {
                et_certification_name.setText(item.getName());
                Glide.with(this).load(item.getPictrues()).into(riv_Image);
                imageUrlPath = item.getPictrues();
                id = String.valueOf(item.getId());
            }
        }

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
    }

    private void postData() {
        if (TextUtils.isEmpty(et_certification_name.getText().toString().trim())) {
            mApp.shortToast("请输入技能证书简称");
            return;
        }
        if (TextUtils.isEmpty(imageUrlPath)) {
            mApp.shortToast("请上传证书");
            return;
        }
        mApp.getLoadingDialog().show();

        if (imageUrlPath.contains("http")) {
            skillCertificationPresenter.sendSkillCertification(et_certification_name.getText().toString().trim(), imageUrlPath, id);
        } else {
            List<String> strings = new ArrayList<>();
            strings.add(imageUrlPath);
            uploadFilePresenter.uploadMultipleImage(strings);
        }
    }


    @Override
    public void sendSkillCertificationSuccess() {
        mApp.getLoadingDialog().hide();
        mApp.shortToast("提交成功");
        finish();
    }

    @Override
    public void sendSkillCertificationFail(String info) {
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
    public void UploadMultipleFileSuccess(List<String> strings) {
        skillCertificationPresenter.sendSkillCertification(et_certification_name.getText().toString().trim(), strings.get(0),id);
    }

    @Override
    public void UploadMultipleFileFail(String msg) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(msg);
    }
}
