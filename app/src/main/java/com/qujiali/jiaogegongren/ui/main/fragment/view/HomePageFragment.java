package com.qujiali.jiaogegongren.ui.main.fragment.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.BaseKeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.marquee.dingrui.marqueeviewlib.MarqueeView;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.bean.HomeWorkerTypeEntity;
import com.qujiali.jiaogegongren.bean.NewsEntity;
import com.qujiali.jiaogegongren.bean.SettlementEntity;
import com.qujiali.jiaogegongren.bean.YwpAddressBean;
import com.qujiali.jiaogegongren.common.base.Adapter;
import com.qujiali.jiaogegongren.common.base.BaseFragment;
import com.qujiali.jiaogegongren.common.base.MyAppGlideModule;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.base.ViewHolder;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.common.model.address.Address2PickerView;
import com.qujiali.jiaogegongren.common.model.address.AddressPickerView;
import com.qujiali.jiaogegongren.common.model.pop.CommonPopupWindow;
import com.qujiali.jiaogegongren.common.model.pop.PopupAdapter;
import com.qujiali.jiaogegongren.ui.banner.presenter.BannerPresenter;
import com.qujiali.jiaogegongren.ui.banner.view.activity.IBannerView;
import com.qujiali.jiaogegongren.ui.main.activity.HomeDetailActivity;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.HomePagePresenter;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.NewsListPresenter;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.SettlementInformationPresenter;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.view.SettleInCompanyActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.SettleInActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import lib.demo.spinner.MaterialSpinner;

public class HomePageFragment extends BaseFragment implements IHomePageView, INewsListView, IBannerView, ISettlementInformationView {
    private BannerPresenter bannerPresenter = new BannerPresenter(this);
    private HomePagePresenter homePagePresenter = new HomePagePresenter(this);
    private NewsListPresenter mNewsListPresenter = new NewsListPresenter(this);
    private CommonPopupWindow popupWindow;
    private SettlementInformationPresenter settlementInformationPresenter = new SettlementInformationPresenter(this);

    @BindView(R.id.recycler_home)
    EasyRecyclerView mEasyRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.marqueeView)
    MarqueeView mMarqueeView;
    @BindView(R.id.iv_banner)
    ImageView mIvBanner;
    @BindView(R.id.chooseCity)
    TextView chooseCity;
    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.iv_singin)
    ImageView iv_singin;
    @BindView(R.id.tv_search)
    TextView tv_search;

    @BindView(R.id.chooseHot)
    MaterialSpinner chooseHot;
    @BindView(R.id.tv_currentCity)
    TextView tv_currentCity;
    @BindView(R.id.chooseTypeOfWork)
    TextView chooseTypeOfWork;

    String[] typeArrays = {"最新排序", "浏览次数", "热度排序"};
    private Adapter<HomeEntity> homeBeanAdapter;
    private YwpAddressBean mYwpAddressBean;
    private String mDistrictCode="";
    public static HomePageFragment instance;
    private String key;
    private String role = "NONE";
    private String orderBy = "create_time";
    private List<String> workTypeData = new ArrayList<>();

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        instance = this;
        initRecyclerView();
        mNewsListPresenter.queryNews(null);
        bannerPresenter.queryBannerDataList(UserInfo.getCityCode(), "2", "");
        homePagePresenter.getHomeWorkerType();
        settlementInformationPresenter.querySettlementInformation();
        chooseCity.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                showAddressPickerPop();
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
                chooseTypeOfWork.setText("常用工种");
                Log.e("TAG", "onMultiClick: " + key);
            }
        });

        tv_search.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                homePagePresenter.getHomePageData(homeBeanAdapter.refreshPage(), mDistrictCode, key, orderBy);
            }
        });
//        chooseCity.setText(UserInfo.getCityCodeName());

        iv_singin.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (mApp.isLoginToDialog()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("role", role);
                    if (role.equals("NONE")) {
                        mApp.getOptionDialog().show("请选择入驻身份", new String[]{"http://media.jiaogegongren.com/abc_worker.png",
                                "http://media.jiaogegongren.com/abc_company.png"}, position -> {

                            /**
                             * type  1: 代表个人入驻
                             *       2：公司入驻
                             */
                            if (position == 0) {
                                startActivity(SettleInActivity.class, bundle);
                            } else if (position == 1) {
                                startActivity(SettleInCompanyActivity.class, bundle);
                            }
                        });
                    } else if (role.equals("WORKER")) {
                        startActivity(SettleInActivity.class, bundle);
                    } else if (role.equals("ENTERPRISE")) {
                        startActivity(SettleInCompanyActivity.class, bundle);
                    }
                }
            }
        });

        chooseHot.setItems(typeArrays);
        chooseHot.setSelectedIndex(0);
        chooseHot.setOnItemSelectedListener((MaterialSpinner.OnItemSelectedListener<String>) (view, position, id, item) -> {
            Log.e("TAG", "onItemSelected: " + item);
            if (position == 0) {
                orderBy = "create_time";
            } else if (position == 1) {
                orderBy = "check_time";
            } else if (position == 2) {
                orderBy = "attention";
            }
            homePagePresenter.getHomePageData(homeBeanAdapter.refreshPage(),  mDistrictCode, key, orderBy);

        });
        chooseHot.setOnNothingSelectedListener(new MaterialSpinner.OnNothingSelectedListener() {

            @Override
            public void onNothingSelected(MaterialSpinner spinner) {
                Log.e("TAG", "NothingonItemSelected: " + spinner.getSelectedIndex());
            }
        });

        tv_currentCity.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                mDistrictCode = UserInfo.getCityCode();
                homePagePresenter.getHomePageData(homeBeanAdapter.refreshPage(), UserInfo.getCityCode(), key, orderBy);
                tv_currentCity.setText(UserInfo.getCityCodeName());
            }
        });
        chooseTypeOfWork.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                // .setAnimationStyle(R.style.AnimDown)
                if (workTypeData.size()==0){
                    workTypeData.add("第一个工种");
                    workTypeData.add("第一个A工种");
                    workTypeData.add("第一个B工种");
                    workTypeData.add("第一个C工种");
                    workTypeData.add("第一个D工种");
                    workTypeData.add("第一个E工种");
                    workTypeData.add("第一个F工种");
                }
                if (popupWindow != null && popupWindow.isShowing()) return;
                popupWindow = new CommonPopupWindow.Builder(getActivity())
                        .setView(R.layout.popup_down)
                        .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        .setViewOnclickListener(new CommonPopupWindow.ViewInterface() {
                            @Override
                            public void getChildView(View view, int layoutResId) {
                                RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
                                recycle_view.setLayoutManager(new GridLayoutManager(getActivity(), 4));

                                PopupAdapter mAdapter = new PopupAdapter(getActivity(), workTypeData);
                                recycle_view.setAdapter(mAdapter);
                                mAdapter.setOnItemClickListener(new PopupAdapter.MyOnclickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Logger.e("选中位置--->" + position);
                                        if (popupWindow != null) {
                                            popupWindow.dismiss();
                                        }
                                        chooseTypeOfWork.setText(workTypeData.get(position));
                                        key =workTypeData.get(position);
                                        homePagePresenter.getHomePageData(homeBeanAdapter.refreshPage(),mDistrictCode,key , orderBy);
                                    }
                                });
                            }
                        })
                        .setOutsideTouchable(true)
                        .create();
                popupWindow.showAsDropDown(chooseTypeOfWork);
                //得到button的左上角坐标
//        int[] positions = new int[2];
//        view.getLocationOnScreen(positions);
//        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, 0, positions[1] + view.getHeight());
            }
        });
    }

    public void setData() {
//        chooseCity.setText(UserInfo.getCityCodeName());
        homePagePresenter.getHomePageData(homeBeanAdapter.refreshPage(), mDistrictCode, null, orderBy);
    }

    public void hideSystemKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            //相当于Fragment的onPause()
        } else {
            // 相当于Fragment的onResume()
            bannerPresenter.queryBannerDataList(UserInfo.getCityCode(), "2", "");
        }
    }

    private void setNewsData(ArrayList<NewsEntity> dataList) {
        List strcontent = new ArrayList<>();
        List strlink = new ArrayList<>();
        for (NewsEntity data : dataList) {
            strcontent.add(data.getContent());
            strlink.add(data.getLink());
        }
         mMarqueeView.setContent(strcontent);
    }


    private void initRecyclerView() {
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(getActivity());
        mEasyRecyclerView.setLayoutManager(myLinearLayoutManager);
        mEasyRecyclerView.setAdapterWithProgress(homeBeanAdapter = new Adapter<HomeEntity>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder<HomeEntity>(parent, R.layout.fragment_home_item) {
                    private TextView tv_home_title, tv_home_skill,
                            tv_home_content, tv_home_city, tv_home_review, riv_home_name,
                            tv_home_auth, tv_home_age, tv_type_name, tv_home_attention;
                    private RoundImageView riv_headImage;
                    private ImageView iv_home_sex, iv_home_skill_type;

                    @Override
                    public void initView() {
                        tv_home_title = $(R.id.tv_home_title);
                        tv_home_skill = $(R.id.tv_home_skill);
                        iv_home_skill_type = $(R.id.iv_home_skill_type);
                        tv_home_content = $(R.id.tv_home_content);
                        tv_home_city = $(R.id.tv_home_city);
                        tv_home_review = $(R.id.tv_home_review);
                        riv_home_name = $(R.id.riv_home_name);
                        tv_home_auth = $(R.id.tv_home_auth);
                        tv_home_age = $(R.id.tv_home_age);
                        riv_headImage = $(R.id.riv_headImage);
                        iv_home_sex = $(R.id.iv_home_sex);
                        tv_type_name = $(R.id.tv_type_name);
                        tv_home_attention = $(R.id.tv_home_attention);

                    }

                    @Override
                    public void setData(HomeEntity data) {
                        super.setData(data);
                        tv_home_title.setText(data.getProfession());
                        tv_home_skill.setText(data.getWorkType());
                        if (TextUtils.isEmpty(data.getCertificate())) {
                            iv_home_skill_type.setVisibility(View.GONE);
                        } else {
                            iv_home_skill_type.setVisibility(View.VISIBLE);
                        }

                        tv_home_content.setText(data.getSkillsIntroduce());
                        tv_home_city.setText(data.getAreaList());
                        tv_home_review.setText(data.getCheckTime() + "人");
                        if (data.getRole().equals("WORKER")) {
                            riv_home_name.setText(data.getName());
                            tv_type_name.setBackgroundResource(R.drawable.background_shape_task);
                        } else if (data.getRole().equals("ENTERPRISE")) {
                            riv_home_name.setText(data.getShortName());
                            tv_type_name.setBackgroundResource(R.drawable.background_shape_task_blue);
                        }

                        if (TextUtils.isEmpty(data.getAttention()) || data.getAttention().equals("0")) {
                            tv_home_attention.setVisibility(View.GONE);
                        } else {
                            tv_home_attention.setVisibility(View.VISIBLE);
                            tv_home_attention.setText(data.getAttention() + "人");
                        }
                        if (data.getAge() == 0) {
                            tv_home_age.setVisibility(View.GONE);
                        } else {
                            tv_home_age.setText(data.getAge() + "");
                        }
                        tv_type_name.setText(data.getRoleName());
                        if (TextUtils.isEmpty(data.getAuthentication())) {
                            tv_home_auth.setVisibility(View.GONE);
                        } else {
                            tv_home_auth.setText(data.getAuthentication());
                        }

                        Glide.with(GoHomeApplication.getContext()).applyDefaultRequestOptions(MyAppGlideModule.getRequestOptions()).load(data.getProfile()).into(riv_headImage);

                        if ("1".equals(data.getSex())) {
                            Glide.with(GoHomeApplication.getContext()).load(R.mipmap.sex_nan).into(iv_home_sex);
                        } else if (data.getSex().equals("2")) {
                            Glide.with(GoHomeApplication.getContext()).load(R.mipmap.sex_nv).into(iv_home_sex);
                        } else {
                            iv_home_sex.setVisibility(View.GONE);
                        }
                    }
                };
            }
        });
        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                homePagePresenter.getHomePageData(homeBeanAdapter.refreshPage(), mDistrictCode, key, orderBy);
            }
        });
        homeBeanAdapter.setMore(new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                homePagePresenter.getHomePageData(homeBeanAdapter.getNextPage(), mDistrictCode, key, orderBy);
            }

            @Override
            public void onMoreClick() {
            }
        });
        homeBeanAdapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(getActivity(), HomeDetailActivity.class);
            intent.putExtra("id", homeBeanAdapter.getItem(position).getId());
            intent.putExtra("role", homeBeanAdapter.getItem(position).getRole());
            startActivity(intent);
        });
    }


    @Override
    public void getHomePageDataSuccess(ArrayList<HomeEntity> homeEntities) {
        homeBeanAdapter.addAll(homeEntities);
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();//结束加载（加载失败）
    }

    @Override
    public void getHomePageDataFail(String info) {
        mApp.longToast(info);
        refreshLayout.finishRefresh(false);//结束刷新（刷新失败）
        refreshLayout.finishLoadMore(false);//结束加载（加载失败）
    }

    @Override
    public void getHomeWorkTypeSuccess(ArrayList<HomeWorkerTypeEntity> homeEntities) {
        workTypeData.clear();
        for (HomeWorkerTypeEntity worktype:homeEntities) {
            workTypeData.add(worktype.getDictLabel());
        }
    }

    @Override
    public void getHomeWorkTypeFail(String info) {

    }




    @Override
    public void queryNewsSuccess(ArrayList<NewsEntity> dataList) {
        setNewsData(dataList);
    }

    @Override
    public void queryNewsFail(String info) {

    }

    @Override
    public void queryBannerDataListSuccess(String str) {
        Glide.with(GoHomeApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(
                R.mipmap.banner_default).error(R.mipmap.banner_default)).load(str).into(mIvBanner);
    }

    @Override
    public void queryBannerDataListFail(int code, String msg) {
        mApp.shortToast(msg);
    }


    /**
     * 显示地址选择的pop
     */
    private void showAddressPickerPop() {
        final PopupWindow popupWindow = new PopupWindow(getActivity());
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_addresstwo_picker, null, false);
        Address2PickerView addressView = rootView.findViewById(R.id.apvAddress);
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
        addressView.setOnAddressPickerSure((allAddress, address, provinceCode, cityCode, districtCode) -> {
            String mProvinceCode = provinceCode;
            String mCityCode = cityCode;
            mDistrictCode = cityCode;
            Log.e("onSureClick: ", mProvinceCode + "---" + mCityCode + "---" + mDistrictCode);
            chooseCity.setText(address);
            popupWindow.dismiss();
            chooseCity.setEnabled(true);
            tv_currentCity.setText("当前城市");
            homePagePresenter.getHomePageData(homeBeanAdapter.refreshPage(), mDistrictCode, null, orderBy);
        }, true);
        popupWindow.setContentView(rootView);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAsDropDown(chooseCity);
      /*  StringBuilder jsonSB = new StringBuilder();
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

        YwpAddressBean.AddressItemBean addressItemBean = new YwpAddressBean.AddressItemBean();
        addressItemBean.setI("111111");
        addressItemBean.setN("全部");
        addressItemBean.setP("666666");
        mYwpAddressBean.getProvince().set(0,addressItemBean);
        YwpAddressBean.AddressItemBean addressItemBean1 = new YwpAddressBean.AddressItemBean();
        addressItemBean1.setI("666666");
        addressItemBean1.setN("全部");
        addressItemBean1.setP("111111");
        mYwpAddressBean.getCity().set(0,addressItemBean1);
        addressView.initData(mYwpAddressBean);*/
    }

    @Override
    public void loadSettlementInformationSuccess(SettlementEntity settlementEntity) {
        role = settlementEntity.getRole();
    }

    @Override
    public void loadSettlementInformationFail(int code, String info) {

    }
}
