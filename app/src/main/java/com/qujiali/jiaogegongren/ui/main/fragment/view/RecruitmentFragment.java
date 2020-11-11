package com.qujiali.jiaogegongren.ui.main.fragment.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
import com.orhanobut.logger.Logger;
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
import com.qujiali.jiaogegongren.common.model.address.Address2PickerView;
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
    @BindView(R.id.tv_search)
    TextView tv_search;
    @BindView(R.id.tv_related_to_me)
    TextView tv_related_to_me;
    @BindView(R.id.tv_currentCity)
    TextView tv_currentCity;


    private Adapter<RecruitmentEntity> mAdapter;
    private String mCityCode = "";
    private YwpAddressBean mYwpAddressBean;
    private String key = "";

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
                if (mApp.isLoginToDialog()) {
                    startActivity(PostRecruitmentActivity.class);
                }
            }
        });

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                key = s.toString();
                Log.e("TAG", "onMultiClick: " + key);
            }
        });

        tv_search.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                recruitmentPresenter.loadRecruitmentData(mAdapter.refreshPage(), mCityCode, et_search.getText().toString(),"");
            }
        });

        tv_related_to_me.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                //与我相关
                recruitmentPresenter.loadRecruitmentData(mAdapter.refreshPage(), UserInfo.getCityCode(), et_search.getText().toString(),"String");
            }
        });
        tv_currentCity.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                mCityCode = UserInfo.getCityCode();
                recruitmentPresenter.loadRecruitmentData(mAdapter.refreshPage(), UserInfo.getCityCode(), et_search.getText().toString(),"");
                tv_currentCity.setText(UserInfo.getCityCodeName());
            }
        });
        initRecyclerView();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
//        chooseCity.setText(UserInfo.getCityCodeName());
    }

    @Override
    public void onResume() {
        super.onResume();
        recruitmentPresenter.loadRecruitmentData(mAdapter.refreshPage(), mCityCode, null,"");
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
                    private ImageView iv_registered;


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
                        iv_registered = $(R.id.iv_registered);

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
                        //动态生成标签
                       /* if (!TextUtils.isEmpty(data.getLabels())) {
                            String[] split = data.getLabels().split(",");
                            addviewlayout.removeAllViews();
                            for (int i = 0; i < split.length; i++) {
                                View viewItemParent = LayoutInflater.from(getActivity()).inflate(R.layout.item_dynamic_textview, addviewlayout, false);
                                addviewlayout.addView(viewItemParent);
                                final View viewItem = addviewlayout.getChildAt(i);
                                TextView mTaskTitle = viewItem.findViewById(R.id.tv_addview);
                                mTaskTitle.setText(split[i]);
                            }
                        }*/
                        if (TextUtils.isEmpty(data.getApplyType()) || data.getApplyType().equals("0")) {
                            iv_registered.setVisibility(View.GONE);
                        } else {
                            iv_registered.setVisibility(View.VISIBLE);
                        }
                    }
                };
            }
        });

        mEasyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Logger.e("--------2点击搜索数据--->", key);
                recruitmentPresenter.loadRecruitmentData(mAdapter.refreshPage(), mCityCode, key,"");
            }
        });
        mAdapter.setMore(new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                recruitmentPresenter.loadRecruitmentData(mAdapter.getNextPage(), mCityCode, key,"");
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
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_addresstwo_picker, null, false);

        Address2PickerView addressView = rootView.findViewById(R.id.apvAddress);


        addressView.setOnAddressPickerSure(new Address2PickerView.OnAddressPickerSureListener() {
            @Override
            public void onSureClick(String allAddress, String address, String provinceCode, String cityCode, String districtCode) {
                String mProvinceCode = provinceCode;
                mCityCode = cityCode;
                Log.e("onSureClick: ", mProvinceCode + "---" + cityCode);
                chooseCity.setText(address);
                popupWindow.dismiss();
                chooseCity.setEnabled(true);
                tv_currentCity.setText("当前城市");
                recruitmentPresenter.loadRecruitmentData(mAdapter.refreshPage(), mCityCode, null,"");
            }
        }, true);
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
        addressView.initData(mYwpAddressBean, 2);

    }

}
