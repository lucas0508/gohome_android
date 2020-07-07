package com.qujiali.jiaogegongren.ui.main.fragment.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.bean.YwpAddressBean;
import com.qujiali.jiaogegongren.common.base.Adapter;
import com.qujiali.jiaogegongren.common.base.BaseFragment;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.base.ViewHolder;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.common.model.address.AddressPickerView;
import com.qujiali.jiaogegongren.ui.main.activity.RecruitmentDetailActivity;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.RecruitmentPresenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static androidx.core.content.ContextCompat.getSystemService;

public class RecruitmentFragment extends BaseFragment implements IRecruitmentView {

    private RecruitmentPresenter recruitmentPresenter = new RecruitmentPresenter(this);

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.ib_close)
    ImageButton mClose;
    @BindView(R.id.recycler)
    EasyRecyclerView mEasyRecyclerView;
    @BindView(R.id.iv_release)
    ImageView mRelease;
    @BindView(R.id.chooseCity)
    TextView chooseCity;
    @BindView(R.id.et_search)
    EditText et_search;
    private Adapter<RecruitmentEntity> mAdapter;
    private String mDistrictCode = "";
    private YwpAddressBean mYwpAddressBean;

    @Override
    protected void initView() {
        mTitle.setText("招工信息");
        mClose.setVisibility(View.GONE);

        chooseCity.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                showAddressPickerPop();
            }
        });
        mRelease.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                startActivity(PostRecruitmentActivity.class);
            }
        });
        initRecyclerView();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        chooseCity.setText(UserInfo.getCityCodeName());
    }

    @Override
    public void onResume() {
        super.onResume();
        recruitmentPresenter.loadRecruitmentData(mAdapter.refreshPage(), TextUtils.isEmpty(mDistrictCode) ? UserInfo.getCityCode() : mDistrictCode, null);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_recruitment;
    }

    private void initRecyclerView() {
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(getActivity());
        mEasyRecyclerView.setLayoutManager(myLinearLayoutManager);
        mEasyRecyclerView.setAdapterWithProgress(mAdapter = new Adapter<RecruitmentEntity>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {

                return new ViewHolder<RecruitmentEntity>(parent, R.layout.fragment_recruitment_item) {

                    private TextView tv_home_title, tv_home_skill,
                            tv_home_content, tv_home_city, tv_home_review, tv_home_name, tv_time;
                    private RoundImageView riv_headImage;
                    private LinearLayout addviewlayout;


                    @Override
                    public void initView() {
                        tv_home_title = $(R.id.tv_home_title);
                        tv_home_skill = $(R.id.tv_home_skill);
                        tv_home_content = $(R.id.tv_home_content);
                        tv_home_city = $(R.id.recruitment_city);
                        tv_home_review = $(R.id.tv_home_review);
                        tv_home_name = $(R.id.tv_home_name);
                        riv_headImage = $(R.id.riv_headImage);
                        addviewlayout = $(R.id.addviewlayout);
                        tv_time = $(R.id.tv_time);
                    }

                    @Override
                    public void setData(RecruitmentEntity data) {
                        super.setData(data);
                        tv_home_title.setText(data.getTitle());
                        tv_home_skill.setText(data.getWorkType());
                        tv_home_content.setText(data.getDescribe());
                        tv_home_city.setText(data.getAreaList());
                        tv_home_name.setText(data.getUserName());
                        tv_time.setText(data.getCreateTime());
                        Glide.with(GoHomeApplication.getContext()).load(data.getProfile()).into(riv_headImage);
                        String[] split = data.getLabels().split(",");
                        addviewlayout.removeAllViews();
                        for (int i = 0; i < split.length; i++) {
                            View viewItemParent = LayoutInflater.from(getActivity()).inflate(R.layout.item_dynamic_textview, addviewlayout, false);
                            addviewlayout.addView(viewItemParent);
                            final View viewItem = addviewlayout.getChildAt(i);
                            TextView mTaskTitle = viewItem.findViewById(R.id.tv_addview);
                            mTaskTitle.setText(split[i]);
                        }
                    }
                };
            }
        });

        mEasyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recruitmentPresenter.loadRecruitmentData(mAdapter.refreshPage(), TextUtils.isEmpty(mDistrictCode) ? UserInfo.getCityCode() : mDistrictCode, null);
            }
        });
        mAdapter.setMore(new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                recruitmentPresenter.loadRecruitmentData(mAdapter.getNextPage(), TextUtils.isEmpty(mDistrictCode) ? UserInfo.getCityCode() : mDistrictCode, null);
            }

            @Override
            public void onMoreClick() {
            }
        });
        mAdapter.setOnItemClickListener(position -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", mAdapter.getItem(position).getId());
            startActivity(RecruitmentDetailActivity.class, bundle);
        });
    }


    @Override
    public void loadRecruitmentDataSuccess(ArrayList<RecruitmentEntity> recruitmentEntities) {
        mAdapter.addAll(recruitmentEntities);
    }

    @Override
    public void loadRecruitmentDataFail(String info) {
        mApp.shortToast(info);
    }

    /**
     * 显示地址选择的pop
     */
    private void showAddressPickerPop() {
        final PopupWindow popupWindow = new PopupWindow(getActivity());
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_address_picker, null, false);

        AddressPickerView addressView = rootView.findViewById(R.id.apvAddress);

        addressView.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {
            @Override
            public void onSureClick(String allAddress,String address, String provinceCode, String cityCode, String districtCode) {
                String mProvinceCode = provinceCode;
                String mCityCode = cityCode;
                mDistrictCode = districtCode;
                Log.e("onSureClick: ", mProvinceCode + "---" + mCityCode + "---" + mDistrictCode);
                chooseCity.setText(address);
                popupWindow.dismiss();
                chooseCity.setEnabled(true);
                recruitmentPresenter.loadRecruitmentData(mAdapter.refreshPage(), mDistrictCode, null);
            }
        },true);
        popupWindow.setContentView(rootView);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
//        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//        int height = wm.getDefaultDisplay().getHeight();
//
//        popupWindow.setHeight((int) (height/3));
        popupWindow.setBackgroundDrawable(null);
        popupWindow.showAsDropDown(chooseCity);




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

}
