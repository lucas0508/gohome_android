package com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.view;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.dialog.SelectPicDialog;
import com.qujiali.jiaogegongren.common.model.MyGridView;
import com.qujiali.jiaogegongren.common.model.takePhoto.GridViewAddImgesAdpter;
import com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils;
import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;
import com.qujiali.jiaogegongren.ui.other.presenter.UploadFilePresenter;
import com.qujiali.jiaogegongren.ui.other.view.IUploadFileView;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter.SkillIntroductionPresenter;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.ISkillIntroductionView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class SkillIntroductionEnterpriseActivity extends BaseActivity implements ISkillIntroductionView, IUploadFileView {


    private SkillIntroductionPresenter skillIntroductionPresenter = new SkillIntroductionPresenter(this);

    private UploadFilePresenter uploadFilePresenter = new UploadFilePresenter(this);


    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_common_right)
    TextView mCommonRight;
    @BindView(R.id.tv_introduction)
    TextView tv_introduction;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.gridview)
    MyGridView mGridView;
    private List<String> mPathList = new ArrayList<>();
    private GridViewAddImgesAdpter mGridViewAddImgesAdpter;
    private List<String> mUploadPathList; //新选择的粘片需要上传
    private List<String> mPostPathList; // 带http的旧图片

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_skill_introduction;
    }

    @Override
    protected void initView() {

        String skillsIntroduce = getIntent().getStringExtra("skillsIntroduce");
        String skillsImages = getIntent().getStringExtra("skillsImages");


        mTitle.setVisibility(View.GONE);
        mCommonRight.setText("确定");
        tv_introduction.setText("公司介绍");
        et_content.setHint("请输入公司介绍详情......");
        initGridView();
        if (!TextUtils.isEmpty(skillsIntroduce)) {
            if (skillsIntroduce.startsWith("这个家伙")){
                et_content.setText("");
            }else {
                et_content.setText(skillsIntroduce);
            }
            List<String> strings = DevicePermissionsUtils.stringToList(skillsImages);
            mPathList = strings;
            mGridViewAddImgesAdpter.notifyDataSetChanged(mPathList);
        }
        mCommonRight.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                postData();
            }
        });
    }

    private void postData() {
        if (TextUtils.isEmpty(et_content.getText().toString().trim())) {
            mApp.shortToast("请输入技能介绍详情...");
            return;
        }
        mApp.getLoadingDialog().show();

        Logger.d("原始数据:" + mPathList);

        mUploadPathList = new ArrayList<>();
        mPostPathList= new ArrayList<>();
        for (String image : mPathList) {
            if (!image.startsWith("http")) {
                mUploadPathList.add(image);
            } else {
                mPostPathList.add(image);
            }
        }
        if (mUploadPathList.size()>0){
            uploadFilePresenter.uploadMultipleImage(mUploadPathList);
        }else {
            goSendServerData(DevicePermissionsUtils.listToString(mPostPathList));
        }
    }

    private void initGridView() {
        mGridViewAddImgesAdpter = new GridViewAddImgesAdpter(mPathList, this);
        mGridViewAddImgesAdpter.setMaxImages(4);
        mGridView.setAdapter(mGridViewAddImgesAdpter);
        mGridView.setOnItemClickListener((adapterView, view, i, l) -> mApp.getSelectPicDialog()
                .show(new SelectPicDialog.Callback() {
                    @Override
                    public void clickItem(int position) {
                        if (position == 0) {
                            PhotoUtils.autoObtainCameraPermission(getContext(), false);
                        } else if (position == 1) {
                            PhotoUtils.autoObtainStoragePermission(getContext(), false);
                        }
                    }
                }));
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
                                    mPathList.add(file.getAbsolutePath());
                                    mGridViewAddImgesAdpter.notifyDataSetChanged(mPathList);
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
    public void sendSkillIntroductionDataSuccess() {

    }

    @Override
    public void sendSkillIntroductionFail(String info) {

    }

    @Override
    public void sendCompanySkillIntroductionSuccess() {
        mApp.getLoadingDialog().hide();
        mApp.shortToast("提交成功");
        finish();
    }

    @Override
    public void sendCompanySkillIntroductionFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }

    @Override
    public void UploadMultipleFileSuccess(List<String> strings) {
        for (String image: strings ) {
            mPostPathList.add(image);
        }
        String imageString = DevicePermissionsUtils.listToString(mPostPathList);
        goSendServerData(imageString);
    }

    private void goSendServerData(String s) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("skillsIntroduce", et_content.getText().toString().trim());
        objectMap.put("skillsImages", s);
        skillIntroductionPresenter.sendCompanySkillIntroduction(objectMap);

    }

    @Override
    public void UploadMultipleFileFail(String msg) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(msg);
    }
}
