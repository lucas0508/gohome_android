package com.qujiali.jiaogegongren.ui.main.fragment.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.marquee.dingrui.marqueeviewlib.MarqueeView;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.bean.NewsEntity;
import com.qujiali.jiaogegongren.bean.YwpAddressBean;
import com.qujiali.jiaogegongren.common.base.Adapter;
import com.qujiali.jiaogegongren.common.base.BaseFragment;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.base.ViewHolder;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.common.model.address.AddressPickerView;
import com.qujiali.jiaogegongren.ui.banner.presenter.BannerPresenter;
import com.qujiali.jiaogegongren.ui.banner.view.activity.IBannerView;
import com.qujiali.jiaogegongren.ui.main.activity.HomeDetailActivity;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.HomePagePresenter;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.NewsListPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomePageFragment extends BaseFragment implements IHomePageView, INewsListView, IBannerView {
    private BannerPresenter bannerPresenter = new BannerPresenter(this);
    private HomePagePresenter homePagePresenter = new HomePagePresenter(this);
    private NewsListPresenter mNewsListPresenter = new NewsListPresenter(this);
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

    private Adapter<HomeEntity> homeBeanAdapter;
    private YwpAddressBean mYwpAddressBean;
    private String mDistrictCode;

    public static HomePageFragment instance;
    private String key;

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

        chooseCity.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                showAddressPickerPop();
            }
        });
        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                /*判断是否是“搜索”键*/
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    key = et_search.getText().toString().trim();
                    //  下面就是大家的业务逻辑
//                    searchPoi(key);
                    //  这里记得一定要将键盘隐藏了
//                    hideKeyBoard();
                    homePagePresenter.getHomePageData(homeBeanAdapter.refreshPage(), TextUtils.isEmpty(mDistrictCode) ? UserInfo.getCityCode() : mDistrictCode, key);
                    hideSystemKeyBoard();
                    return true;
                }
                return false;
            }
        });
        chooseCity.setText(UserInfo.getCityCodeName());
    }

    public void setData() {
        chooseCity.setText(UserInfo.getCityCodeName());
        homePagePresenter.getHomePageData(homeBeanAdapter.refreshPage(), TextUtils.isEmpty(mDistrictCode) ? UserInfo.getCityCode() : mDistrictCode, null);
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
                            tv_home_auth, tv_home_technicalAbility, tv_home_age, tv_type_name;
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
                        tv_home_technicalAbility = $(R.id.tv_home_technicalAbility);
                        riv_headImage = $(R.id.riv_headImage);
                        iv_home_sex = $(R.id.iv_home_sex);
                        tv_type_name = $(R.id.tv_type_name);
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
                        } else if (data.getRole().equals("ENTERPRISE")) {
                            riv_home_name.setText(data.getShortName());
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
                        tv_home_technicalAbility.setText(data.getDegree());
                        Glide.with(GoHomeApplication.getContext()).load(data.getProfile()).into(riv_headImage);

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
                homePagePresenter.getHomePageData(homeBeanAdapter.refreshPage(), TextUtils.isEmpty(mDistrictCode) ? UserInfo.getCityCode() : mDistrictCode, key);
            }
        });
        homeBeanAdapter.setMore(new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                homePagePresenter.getHomePageData(homeBeanAdapter.getNextPage(), TextUtils.isEmpty(mDistrictCode) ? UserInfo.getCityCode() : mDistrictCode, key);
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
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_address_picker, null, false);

        AddressPickerView addressView = rootView.findViewById(R.id.apvAddress);
        addressView.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {
            @Override
            public void onSureClick(String allAddress, String address, String provinceCode, String cityCode, String districtCode) {
                String mProvinceCode = provinceCode;
                String mCityCode = cityCode;
                mDistrictCode = districtCode;
                Log.e("onSureClick: ", mProvinceCode + "---" + mCityCode + "---" + mDistrictCode);
                chooseCity.setText(address);
                popupWindow.dismiss();
                chooseCity.setEnabled(true);
                homePagePresenter.getHomePageData(homeBeanAdapter.refreshPage(), mDistrictCode, null);
            }
        });
        popupWindow.setContentView(rootView);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAsDropDown(chooseCity);
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

}
