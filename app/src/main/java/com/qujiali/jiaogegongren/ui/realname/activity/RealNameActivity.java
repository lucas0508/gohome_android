package com.qujiali.jiaogegongren.ui.realname.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.RealNameEntity;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.dialog.SelectPicDialog;
import com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils;
import com.qujiali.jiaogegongren.ui.other.presenter.UploadFilePresenter;
import com.qujiali.jiaogegongren.ui.other.view.IUploadFileView;
import com.qujiali.jiaogegongren.ui.realname.presenter.RealNamePresenter;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.view.PerfectInformationEnterpriseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class RealNameActivity extends BaseActivity implements IRealNameView, IUploadFileView {

    private RealNamePresenter mRealNamePresenter = new RealNamePresenter(this);
    private UploadFilePresenter uploadFilePresenter = new UploadFilePresenter(this);
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_common_right)
    TextView mCommonRight;
    @BindView(R.id.iv_id_card_front)
    ImageView iv_id_card_front;
    @BindView(R.id.iv_id_id_card_back)
    ImageView iv_id_card_back;
    @BindView(R.id.tv_denial_reason)
    TextView tv_denial_reason;
    @BindView(R.id.rel_refuse)
    RelativeLayout rel_refuse;
    @BindView(R.id.tv_auth)
    TextView tv_auth;
    private int type = 0;
    private String id_card_front, id_card_back;

    @Override
    protected void initView() {
        mTitle.setText("实名认证");
        mCommonRight.setText("确定");


        mRealNamePresenter.queryRealNameData();

        iv_id_card_front.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                mApp.getSelectPicDialog().show(position -> {
                    type = 0;
                    if (position == 0) {
                        PhotoUtils.autoObtainCameraPermission(getContext(), false);
                    } else if (position == 1) {
                        PhotoUtils.autoObtainStoragePermission(getContext(), false);
                    }
                });
            }
        });
        iv_id_card_back.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                mApp.getSelectPicDialog().show(position -> {
                    type = 1;
                    if (position == 0) {
                        PhotoUtils.autoObtainCameraPermission(getContext(), false);
                    } else if (position == 1) {
                        PhotoUtils.autoObtainStoragePermission(getContext(), false);
                    }
                });
            }
        });

        mCommonRight.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                postData();
            }
        });
    }


    private void postData() {
        if (TextUtils.isEmpty(id_card_front)) {
            mApp.shortToast("请上传身份证正面照");
            return;
        }
        if (TextUtils.isEmpty(id_card_back)) {
            mApp.shortToast("请上传身份证背面照");
            return;
        }
        List<String> strings = new ArrayList<>();
        if (!id_card_front.contains("http")) {
            strings.add(id_card_front);
        }
        if (!id_card_back.contains("http")) {
            strings.add(id_card_back);
        }
        mApp.getLoadingDialog().show();
        uploadFilePresenter.uploadMultipleImage(strings);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_real_name;
    }


    @Override
    public void uploadCertificationInfoSuccess() {
        mApp.getLoadingDialog().hide();
        mApp.shortToast("提交成功");
        finish();
    }

    @Override
    public void uploadCertificationInfoFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }

    @Override
    public void queryCertificationInfoSuccess(RealNameEntity realNameEntity) {
        if (null != realNameEntity) {
            if (realNameEntity.getStatus() == 0) {
                tv_auth.setText("待审核");
                tv_auth.setTextColor(Color.parseColor("#FC8419"));
                mCommonRight.setVisibility(View.GONE);
                iv_id_card_front.setOnClickListener(null);
                iv_id_card_back.setOnClickListener(null);
            } else if (realNameEntity.getStatus() == 1) {
                tv_auth.setText("已实名");
                tv_auth.setTextColor(Color.parseColor("#21B11D"));

                iv_id_card_front.setOnClickListener(null);
                iv_id_card_back.setOnClickListener(null);
                mCommonRight.setVisibility(View.GONE);
            } else if (realNameEntity.getStatus() == 2) {
                mCommonRight.setText("重新提交");
                tv_auth.setText("已拒绝");
                tv_auth.setTextColor(Color.parseColor("#E63636"));
                if (!TextUtils.isEmpty(realNameEntity.getRefuseReason())) {
                    rel_refuse.setVisibility(View.VISIBLE);
                    tv_denial_reason.setText(realNameEntity.getRefuseReason());
                }
            }

            id_card_front = realNameEntity.getPictureOne();
            id_card_back = realNameEntity.getPictureTwo();
            Glide.with(RealNameActivity.this).applyDefaultRequestOptions(new RequestOptions().centerCrop()).load(realNameEntity.getPictureOne()).into(iv_id_card_front);
            Glide.with(RealNameActivity.this).applyDefaultRequestOptions(new RequestOptions().centerCrop()).load(realNameEntity.getPictureTwo()).into(iv_id_card_back);

        }


    }

    @Override
    public void queryCertificationInfoFail(String info) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoUtils.onActivityResult(requestCode, resultCode, data, getContext(), new PhotoUtils.CropHandler() {
            @Override
            public void handleCropResult(final Uri uri, int tag) {
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
                                    if (type == 0) {
                                        id_card_front = file.getAbsolutePath();
                                        Glide.with(RealNameActivity.this).applyDefaultRequestOptions(new RequestOptions().centerCrop()).load(uri.getPath()).into(iv_id_card_front);
                                    } else if (type == 1) {

                                        id_card_back = file.getAbsolutePath();
                                        Glide.with(RealNameActivity.this).applyDefaultRequestOptions(new RequestOptions().centerCrop()).load(uri.getPath()).into(iv_id_card_back);
                                    }
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
    public void UploadMultipleFileSuccess(List<String> strings) {
        if (!id_card_front.contains("http")) {
            id_card_front = strings.get(0);
        }
        if (!id_card_back.contains("http")) {
            id_card_back = strings.get(1);
        }
        mRealNamePresenter.uploadCertificationInfo(id_card_front, id_card_back);
    }

    @Override
    public void UploadMultipleFileFail(String msg) {
        mApp.shortToast(msg);
        mApp.getLoadingDialog().hide();
    }
}






