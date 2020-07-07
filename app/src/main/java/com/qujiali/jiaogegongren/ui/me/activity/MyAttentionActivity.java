package com.qujiali.jiaogegongren.ui.me.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.AttentionEntity;
import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.common.base.Adapter;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.base.ViewHolder;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.ui.login.view.activity.LoginActivity;
import com.qujiali.jiaogegongren.ui.main.activity.HomeDetailActivity;
import com.qujiali.jiaogegongren.ui.me.model.impl.IAttentionCancelView;
import com.qujiali.jiaogegongren.ui.me.presenter.AttentionCancelPresenter;
import com.qujiali.jiaogegongren.ui.me.presenter.MyAttentionPresenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

public class MyAttentionActivity extends BaseActivity implements IMyAttentionView, IAttentionCancelView {

    MyAttentionPresenter myAttentionPresenter = new MyAttentionPresenter(this);
    AttentionCancelPresenter attentionCancelPresenter = new AttentionCancelPresenter(this);

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.recycler)
    EasyRecyclerView mEasyRecyclerView;
    private Adapter<AttentionEntity> attentionEntityAdapter;
    private int position;


    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_my_attention;
    }

    @Override
    protected void initView() {
        mTitle.setText("我的关注");
        mApp.getLoadingDialog().show();
        initRecyclerView();
        myAttentionPresenter.queryAttentionData(attentionEntityAdapter.refreshPage());
    }

    private void initRecyclerView() {
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        mEasyRecyclerView.setLayoutManager(myLinearLayoutManager);
        mEasyRecyclerView.setAdapterWithProgress(attentionEntityAdapter = new Adapter<AttentionEntity>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder<AttentionEntity>(parent, R.layout.activity_attention_item) {
                    private TextView tv_home_title, tv_home_skill, tv_home_skill_type,
                            tv_home_city, tv_home_review, riv_home_name, tv_type_name,
                            tv_home_auth, tv_home_technicalAbility, tv_home_age;
                    private RoundImageView riv_headImage;
                    private ImageView iv_home_sex, iv_zhuxiao;

                    @Override
                    public void initView() {
                        tv_home_title = $(R.id.tv_home_title);
                        tv_home_skill = $(R.id.tv_home_skill);
                        tv_home_skill_type = $(R.id.tv_home_skill_type);
                        tv_home_city = $(R.id.tv_home_city);
                        tv_home_review = $(R.id.tv_home_review);
                        riv_home_name = $(R.id.riv_home_name);
                        tv_home_auth = $(R.id.tv_home_auth);
                        tv_home_age = $(R.id.tv_home_age);
                        tv_home_technicalAbility = $(R.id.tv_home_technicalAbility);
                        riv_headImage = $(R.id.riv_headImage);
                        iv_home_sex = $(R.id.iv_home_sex);
                        tv_type_name = $(R.id.tv_type_name);
                        iv_zhuxiao = $(R.id.iv_zhuxiao);
                    }

                    @Override
                    public void setData(AttentionEntity data) {
                        super.setData(data);
                        if (data.getDelFlag() != 0 || data.getEnabled() != 0) {
                            iv_zhuxiao.setVisibility(View.VISIBLE);
                            tv_home_title.setTextColor(Color.parseColor("#DDDDDD"));
                            tv_home_skill.setTextColor(Color.parseColor("#DDDDDD"));
                            tv_home_skill_type.setTextColor(Color.parseColor("#DDDDDD"));
                            tv_home_review.setTextColor(Color.parseColor("#DDDDDD"));
                            riv_home_name.setTextColor(Color.parseColor("#DDDDDD"));
                            tv_home_city.setTextColor(Color.parseColor("#DDDDDD"));
                            tv_home_age.setTextColor(Color.parseColor("#DDDDDD"));
                            tv_home_auth.setTextColor(Color.parseColor("#DDDDDD"));
                            tv_type_name.setBackgroundResource(R.drawable.background_shape_dark);
                            ColorMatrix cm = new ColorMatrix();
                            cm.setSaturation(0); // 设置饱和度
                            ColorMatrixColorFilter grayColorFilter = new ColorMatrixColorFilter(cm);
                            riv_headImage.setColorFilter(grayColorFilter); // 如果想恢复彩色显示，设置为null即可
                            iv_home_sex.setColorFilter(grayColorFilter);
                        }
                        tv_home_title.setText(data.getProfession());
                        tv_home_skill.setText(data.getWorkType());
                        tv_home_skill_type.setText(data.getCertificate());
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
                        if (TextUtils.isEmpty(data.getAuthentication())) {
                            tv_home_auth.setVisibility(View.GONE);
                        } else {
                            tv_home_auth.setText(data.getAuthentication());
                        }
                        if (1 == data.getSex()) {
                            Glide.with(GoHomeApplication.getContext()).load(R.mipmap.sex_nan).into(iv_home_sex);
                        } else if (2 == data.getSex()) {
                            Glide.with(GoHomeApplication.getContext()).load(R.mipmap.sex_nv).into(iv_home_sex);
                        } else {
                            iv_home_sex.setVisibility(View.GONE);
                        }
                        tv_type_name.setText(data.getRoleName());
                        Glide.with(GoHomeApplication.getContext()).load(data.getProfile()).into(riv_headImage);
                        tv_home_technicalAbility.setOnClickListener(new OnMultiClickListener() {
                            @Override
                            public void onMultiClick(View v) {
                                mApp.getLoadingDialog().show();
                                position = attentionEntityAdapter.getPosition(data);
                                attentionCancelPresenter.cancelAttention(String.valueOf(data.getFocusId()), data.getRole());
                            }
                        });
                    }
                };
            }
        });

        mEasyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myAttentionPresenter.queryAttentionData(attentionEntityAdapter.refreshPage());
            }
        });

        attentionEntityAdapter.setMore(new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                myAttentionPresenter.queryAttentionData(attentionEntityAdapter.getNextPage());
            }

            @Override
            public void onMoreClick() {
            }
        });

        attentionEntityAdapter.setOnItemClickListener(position -> {

            if (attentionEntityAdapter.getItem(position).getEnabled() != 0 || attentionEntityAdapter.getItem(position).getDelFlag() != 0) {
                mApp.shortToast("此公司已注销~");
            } else {
                Intent intent = new Intent(this, HomeDetailActivity.class);
                intent.putExtra("id", attentionEntityAdapter.getItem(position).getId());
                intent.putExtra("role", attentionEntityAdapter.getItem(position).getRole());
                startActivity(intent);
            }
        });
    }

    @Override
    public void queryMyAttentionSuccess(List<AttentionEntity> data) {
        mApp.getLoadingDialog().hide();
        Logger.d(data);
        attentionEntityAdapter.addAll(data);
    }

    @Override
    public void queryMyAttentionFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }

    @Override
    public void cancelAttentionSuccess() {
        attentionEntityAdapter.remove(position);
        attentionEntityAdapter.notifyDataSetChanged();
        mApp.getLoadingDialog().hide();
        mApp.shortToast("取消成功");
    }

    @Override
    public void cancelAttentionFail(String msg) {
        mApp.shortToast(msg);
        mApp.getLoadingDialog().hide();
    }

    @Override
    public void addAttentionSuccess() {

    }

    @Override
    public void addAttentionFail(String msg) {

    }
}
