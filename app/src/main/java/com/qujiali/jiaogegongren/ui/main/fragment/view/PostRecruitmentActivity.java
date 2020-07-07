package com.qujiali.jiaogegongren.ui.main.fragment.view;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.AdressDataEntity;
import com.qujiali.jiaogegongren.bean.LabelEntity;
import com.qujiali.jiaogegongren.bean.PostRecruitEntity;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.bean.YwpAddressBean;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.MyAppGlideModule;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.common.dialog.SelectPicDialog;
import com.qujiali.jiaogegongren.common.model.MyGridView;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.common.model.address.AddressPickerView;
import com.qujiali.jiaogegongren.common.model.takePhoto.GridViewAddImgesAdpter;
import com.qujiali.jiaogegongren.common.model.takePhoto.PhotoUtils;
import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;
import com.qujiali.jiaogegongren.ui.main.activity.PersonalActivity;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.PostRecruitmentPresenter;
import com.qujiali.jiaogegongren.ui.other.presenter.UploadFilePresenter;
import com.qujiali.jiaogegongren.ui.other.view.AddressActivity;
import com.qujiali.jiaogegongren.ui.other.view.IUploadFileView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import butterknife.BindView;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class PostRecruitmentActivity extends BaseActivity implements IPostRecruitmentView, IUploadFileView {
    private static final String MAX_LENGTH = "256";


    private PostRecruitmentPresenter postRecruitmentPresenter = new PostRecruitmentPresenter(this);
    private UploadFilePresenter uploadFilePresenter = new UploadFilePresenter(this);

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.et_title)
    EditText et_title;
    @BindView(R.id.et_work_type)
    EditText et_work_type;
    @BindView(R.id.tv_choose_city)
    TextView tv_choose_city;
    @BindView(R.id.et_price)
    EditText et_price;
    @BindView(R.id.recycler_label)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_number)
    TextView mNumber;
    @BindView(R.id.et_content)
    EditText mContent;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    @BindView(R.id.gridview)
    MyGridView mGridView;
    @BindView(R.id.ll_base)
    LinearLayout ll_base;
    @BindView(R.id.tv_denial_reason)
    TextView tv_denial_reason;
    @BindView(R.id.rel_refuse)
    RelativeLayout rel_refuse;
    @BindView(R.id.riv_headImage)
    RoundImageView riv_headImage;
    @BindView(R.id.et_nickName)
    EditText et_nickName;
    @BindView(R.id.et_phone)
    EditText et_phone;

    private ArrayList<String> mPathList = new ArrayList<>();
    private int picNum = 0;
    PostRecruitmentAdapter mAdapter;
    private GridViewAddImgesAdpter mGridViewAddImgesAdpter;
    private PostRecruitEntity recruitmentEntity;
    private ArrayList<AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean> cityString = new ArrayList<>();
    private String cityName;
    private int selectIndex = 1;
    private String imageUrlPath = "";
    private String mDistrictCode;
    private YwpAddressBean mYwpAddressBean;
    String labels = "";
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_post_recruitment;
    }


    @Override
    protected void initView() {
        mTitle.setText("招工信息");
        int id = getIntent().getIntExtra("RecruitmentEntity_id", -1);
        Logger.d("数据：" + id);
        postRecruitmentPresenter.queryLabel();

        et_phone.setText(UserInfo.getUserPhone());
        Glide.with(this).load(UserInfo.getUserImage()).into(riv_headImage);
        imageUrlPath=UserInfo.getUserImage();
        if (id != -1)
            postRecruitmentPresenter.queryWorkersData(id);
        initRecycler();
        initGridView();


        tv_choose_city.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {

                showAddressPickerPop();
            }
        });

        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String content = mContent.getText().toString();
                mNumber.setText("(" + content.length() + "/"
                        + MAX_LENGTH + ")");
            }
        });
        btn_submit.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                submitData();
            }
        });

        riv_headImage.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                mApp.getSelectPicDialog()
                        .show(new SelectPicDialog.Callback() {
                            @Override
                            public void clickItem(int position) {
                                selectIndex = 1;
                                if (position == 0) {
                                    PhotoUtils.autoObtainCameraPermission(getContext(), true);
                                } else if (position == 1) {
                                    PhotoUtils.autoObtainStoragePermission(getContext(), true);
                                }
                            }
                        });
            }
        });

    }

    private void initGridView() {
        mGridViewAddImgesAdpter = new GridViewAddImgesAdpter(mPathList, this);
        mGridView.setAdapter(mGridViewAddImgesAdpter);
        mGridView.setOnItemClickListener((adapterView, view, i, l) -> mApp.getSelectPicDialog()
                .show(new SelectPicDialog.Callback() {
                    @Override
                    public void clickItem(int position) {
                        selectIndex = 2;
                        if (position == 0) {
                            PhotoUtils.autoObtainCameraPermission(getContext(), false);
                        } else if (position == 1) {
                            PhotoUtils.autoObtainStoragePermission(getContext(), false);
                        }
                    }
                }));
    }

    private void initData() {
        if (recruitmentEntity != null) {
            if (recruitmentEntity.getStatus() != 2) {
                et_title.setEnabled(false);
                et_work_type.setEnabled(false);
                tv_choose_city.setEnabled(false);
                et_price.setEnabled(false);
                mContent.setEnabled(false);
                et_title.setEnabled(false);
                riv_headImage.setOnClickListener(null);
                et_nickName.setEnabled(false);
                et_phone.setEnabled(false);
                mNumber.setVisibility(View.GONE);
                btn_submit.setVisibility(View.GONE);
            }
            Glide.with(GoHomeApplication.getContext())
                    .applyDefaultRequestOptions(MyAppGlideModule.getRequestOptions())
                    .load(recruitmentEntity.getProfile())
                    .into(riv_headImage);
            et_phone.setText(recruitmentEntity.getPhone());
            et_nickName.setText(recruitmentEntity.getUserName());
            et_title.setText(recruitmentEntity.getTitle());
            et_work_type.setText(recruitmentEntity.getWorkType());
            tv_choose_city.setText(recruitmentEntity.getAreaList());
            et_price.setText(recruitmentEntity.getPriceMargin());
            mContent.setText(recruitmentEntity.getDescribe());
            String imags = recruitmentEntity.getImags();
            if (!TextUtils.isEmpty(imags)) {
                String[] split1 = imags.split(",");
                mGridViewAddImgesAdpter.notifyDataSetChanged(Arrays.asList(split1), true);
            } else {
                if (recruitmentEntity.getStatus() != 2) {
                    mGridView.setVisibility(View.GONE);
                } else {//0  1
                    mGridView.setVisibility(View.VISIBLE);
                }
            }
//            AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean childBean = new AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean();
//            childBean.setAdCode(recruitmentEntity.getLocation());
//            cityString.add(childBean);

            mDistrictCode = recruitmentEntity.getLocation();

            if (!TextUtils.isEmpty(recruitmentEntity.getRefuseReason())) {
                rel_refuse.setVisibility(View.VISIBLE);
                tv_denial_reason.setText(recruitmentEntity.getRefuseReason());
            }
        }
        String labels = recruitmentEntity.getLabels();
        if (!TextUtils.isEmpty(labels)) {
            String[] split = labels.split(",");
            ArrayList<String> strings = new ArrayList<>(Arrays.asList(split));
            ArrayList<LabelEntity> labelEntities = new ArrayList<>();
            LabelEntity labelEntity = null;
            for (int i = 0; i < strings.size(); i++) {
                labelEntity = new LabelEntity();
                labelEntity.setDictLabel(strings.get(i));
                labelEntity.setSelected(true);
                labelEntities.add(labelEntity);
            }
            mAdapter.addAll(labelEntities, recruitmentEntity.getStatus());
        } else {
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    private void showAddressPickerPop() {
        final PopupWindow popupWindow = new PopupWindow(this);
        View rootView = LayoutInflater.from(this).inflate(R.layout.pop_address_picker, null, false);

        AddressPickerView addressView = rootView.findViewById(R.id.apvAddress);
        addressView.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {
            @Override
            public void onSureClick(String allAddress,String address, String provinceCode, String cityCode, String districtCode) {
                String mProvinceCode = provinceCode;
                String mCityCode = cityCode;
                mDistrictCode = districtCode;
                cityName = allAddress;
                Log.e("onSureClick: ", mProvinceCode + "---" + mCityCode + "---" + mDistrictCode);
                tv_choose_city.setText(address);
                popupWindow.dismiss();
                tv_choose_city.setEnabled(true);
            }
        });
        popupWindow.setContentView(rootView);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAsDropDown(tv_choose_city);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        StringBuilder jsonSB = new StringBuilder();
        try {
            BufferedReader addressJsonStream = new BufferedReader(new InputStreamReader(getContext().getAssets().open("address.json")));
            String line;
            while ((line = addressJsonStream.readLine()) != null) {
                jsonSB.append(line);
            }
        } catch (IOException e) {
        }
        // 将数据转换为对象
        mYwpAddressBean = new Gson().fromJson(jsonSB.toString(), YwpAddressBean.class);
        addressView.initData(mYwpAddressBean);

    }


    private void submitData() {
        if (TextUtils.isEmpty(imageUrlPath)) {
            mApp.shortToast("请选择头像");
            return;
        }
        if (TextUtils.isEmpty(et_nickName.getText().toString().trim())) {
            mApp.shortToast("请输入昵称");
            return;
        }
        if (TextUtils.isEmpty(et_phone.getText().toString().trim())) {
            mApp.shortToast("请输入联系电话");
            return;
        }

        if (TextUtils.isEmpty(et_title.getText().toString().trim())) {
            mApp.shortToast("请输入标题");
            return;
        }
        if (TextUtils.isEmpty(et_work_type.getText().toString().trim())) {
            mApp.shortToast("请输入工种");
            return;
        }
        if (TextUtils.isEmpty(mContent.getText().toString().trim())) {
            mApp.shortToast("请输入详情描述");
            return;
        }

        if (TextUtils.isEmpty(cityName)) {
            mApp.shortToast("请选择位置");
            return;
        }

        ArrayList<String> selectedItem = mAdapter.getSelectedItem();

        if (selectedItem.size() > 0) {
            StringBuilder strBuilder = new StringBuilder();
            for (String letter : mAdapter.getSelectedItem()) {
                strBuilder.append(letter);
                strBuilder.append(",");
            }
            labels = strBuilder.toString();
            Log.e("submitData--------: ", labels);
            labels = labels.substring(0, labels.length() - ",".length());
        }
        System.out.println(labels);

        if (imageUrlPath.startsWith("http")){
            //上传图片
            if (mPathList!=null){
                ArrayList<String> strings = new ArrayList<>(mPathList);
                mApp.getLoadingDialog().show();
                uploadFilePresenter.uploadMultipleImage(strings);
            }else {
                Map<String, Object> objectMap = new HashMap<>();
                objectMap.put("profile",imageUrlPath);
                objectMap.put("phone", et_phone.getText().toString().trim());
                objectMap.put("userName", et_nickName.getText().toString().trim());
                objectMap.put("title", et_title.getText().toString().trim());
                objectMap.put("workType", et_work_type.getText().toString().trim());
                objectMap.put("describe", mContent.getText().toString().trim());
                objectMap.put("location", mDistrictCode);
                objectMap.put("priceMargin", et_price.getText().toString().trim());
                objectMap.put("labels", labels);
                objectMap.put("imags","");
                postRecruitmentPresenter.submitWorkersData(objectMap);
            }
        }else {
            //上传图片
            ArrayList<String> strings = new ArrayList<>(mPathList);
            strings.add(imageUrlPath);
            mApp.getLoadingDialog().show();
            uploadFilePresenter.uploadMultipleImage(strings);
        }

    }


    private void initRecycler() {
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mAdapter = new PostRecruitmentAdapter();
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

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
                    tv_choose_city.setText("全国-不限-不限");
                } else {
                    tv_choose_city.setText(cityName);
                }
            }
        } else {
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
                                        if (selectIndex == 1) {
                                            Glide.with(GoHomeApplication.getContext())
                                                    .applyDefaultRequestOptions(MyAppGlideModule.getRequestOptions())
                                                    .load(uri.getPath()).into(riv_headImage);
                                            imageUrlPath = uri.getPath();
                                        } else if (selectIndex == 2) {
                                            mPathList.add(file.getAbsolutePath());
                                            mGridViewAddImgesAdpter.notifyDataSetChanged(mPathList);
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

    }

    @Override
    public void queryLabelSuccess(ArrayList<LabelEntity> dataList) {

        if (recruitmentEntity != null) {
            if (!TextUtils.isEmpty(recruitmentEntity.getLabels())) {

                if (recruitmentEntity.getStatus() != 2) {
                    String[] split = recruitmentEntity.getLabels().split(",");
                    ArrayList<String> strings = new ArrayList<>(Arrays.asList(split));
                    ArrayList<LabelEntity> labelEntities = new ArrayList<>();
                    LabelEntity labelEntity = null;
                    for (int i = 0; i < strings.size(); i++) {
                        labelEntity = new LabelEntity();
                        labelEntity.setDictLabel(strings.get(i));
                        labelEntity.setSelected(true);
                        labelEntities.add(labelEntity);
                    }
                    mAdapter.addAll(labelEntities, recruitmentEntity.getStatus());
                } else { //拒绝
                    String[] split = recruitmentEntity.getLabels().split(",");
                    ArrayList<String> strings = new ArrayList<>(Arrays.asList(split));
                    for (int i = 0; i < dataList.size(); i++) {
                        for (int j = 0; j < strings.size(); j++) {
                            if (dataList.get(i).getDictLabel().equals(strings.get(j))) {
                                dataList.get(i).setSelected(true);
                            }
                        }
                    }
                    mAdapter.addAll(dataList, recruitmentEntity.getStatus());
                }

            } else {
                mAdapter.addAll(dataList, recruitmentEntity.getStatus());
            }
        } else {
            mAdapter.addAll(dataList, 2);
        }

    }

    @Override
    public void queryLabelFail(String info) {
        mApp.shortToast(info);
    }

    @Override
    public void submitSuccess() {
        mApp.getLoadingDialog().hide();
        mApp.shortToast("提交成功！");
        finish();
    }

    @Override
    public void submitWorkersFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }

    @Override
    public void queryWorkersSuccess(PostRecruitEntity recruitmentEntity) {
        this.recruitmentEntity = recruitmentEntity;
        initData();
    }


    @Override
    public void queryWorkersFail(String info) {
    }

    @Override
    public void UploadMultipleFileSuccess(List<String> strings) {
        // String labels = String.join(",",mAdapter.getSelectedItem());
        String profile="";
        if (imageUrlPath.startsWith("http")){
             profile=imageUrlPath;
        }else {
             profile=strings.get(strings.size() - 1);
        }
        LinkedList<String> images = new LinkedList<>(strings);
        images.removeLast();
        Log.e("UploadMultiple 头像: ", strings.get(strings.size() - 1));
        Log.e("UploadMultiple 多图: ", new Gson().toJson(images));
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("profile", profile);
        objectMap.put("phone", et_phone.getText().toString().trim());
        objectMap.put("userName", et_nickName.getText().toString().trim());
        objectMap.put("title", et_title.getText().toString().trim());
        objectMap.put("workType", et_work_type.getText().toString().trim());
        objectMap.put("describe", mContent.getText().toString().trim());
        objectMap.put("location", mDistrictCode);
        objectMap.put("priceMargin", et_price.getText().toString().trim());
        objectMap.put("labels", labels);
        objectMap.put("imags", DevicePermissionsUtils.listToString(images));
        postRecruitmentPresenter.submitWorkersData(objectMap);
    }

    @Override
    public void UploadMultipleFileFail(String msg) {

    }
}
