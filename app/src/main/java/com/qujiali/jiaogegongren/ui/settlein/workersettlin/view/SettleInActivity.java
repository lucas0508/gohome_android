package com.qujiali.jiaogegongren.ui.settlein.workersettlin.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.BoolRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.AttentionEntity;
import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.bean.SettlementEntity;
import com.qujiali.jiaogegongren.common.base.Adapter;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.GlideOptions;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.base.ViewHolder;
import com.qujiali.jiaogegongren.common.dialog.ConfirmDialog;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;
import com.qujiali.jiaogegongren.ui.login.view.activity.LoginActivity;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.SettlementInformationPresenter;
import com.qujiali.jiaogegongren.ui.main.fragment.view.ISettlementInformationView;
import com.qujiali.jiaogegongren.ui.other.view.ViewBigImageActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter.SettleInCancelIdentityPresenter;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter.SettleInPresenter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SettleInActivity extends BaseActivity implements ISettleInView, ISettleInCancelIdentityView, ISettlementInformationView {
    private SettlementInformationPresenter settlementInformationPresenter = new SettlementInformationPresenter(this);

    private SettleInPresenter settleInPresenter = new SettleInPresenter(this);

    private SettleInCancelIdentityPresenter settleInCancelIdentityPresenter = new SettleInCancelIdentityPresenter(this);


    @BindView(R.id.tv_title)
    TextView mTitle;

    private String role;

    //------------完善信息----------
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_cancel_identity)
    TextView tv_cancel_identity;
    @BindView(R.id.tv_wanshan)
    TextView tv_wanshan;
    @BindView(R.id.ll_wanshan)
    LinearLayout ll_wanshan;
    @BindView(R.id.tv_description)
    TextView tv_description;
    @BindView(R.id.tv_skill)
    TextView tv_skill;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.riv_image)
    RoundImageView riv_image;
    @BindView(R.id.tv_certificate1)
    TextView tv_certificate1;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_certificate2)
    TextView tv_certificate2;
    @BindView(R.id.tv_certificate3)
    TextView tv_certificate3;
    @BindView(R.id.ll_certificate)
    LinearLayout ll_certificate;

    @BindView(R.id.rel_refuse)
    RelativeLayout rel_refuse;
    @BindView(R.id.tv_denial_reason)
    TextView tv_denial_reason;


    //--------技能介绍---------------
    @BindView(R.id.ll_jineng)
    LinearLayout ll_jineng;
    @BindView(R.id.tv_introduce)
    TextView tv_introduce;
    @BindView(R.id.recycler_view)
    EasyRecyclerView mEasyRecyclerView;
    @BindView(R.id.tv_introduction)
    TextView tv_introduction;
    private Adapter<String> mAdapter;
    //------------项目经验---------
    @BindView(R.id.recycler_project)
    RecyclerView recycler_project;
    private Adapter<SettlelnEntity.BprojectWorkerListBean> mAdapterProject;
    private int status = -1;
    private SettlelnEntity.BsettledWorkerVoBean bsettledWorkerVo;
    private String skillsIntroduce, skillsImages;
    private List<SettlelnEntity.BprojectWorkerListBean> workerList;

    @Override
    protected void initView() {
        mTitle.setText("工人入驻");
        role = getIntent().getStringExtra("role");
    }

    private void initData() {
        if (role.equals("WORKER")) {
            tv_wanshan.setVisibility(View.GONE);
            tv_cancel_identity.setVisibility(View.VISIBLE);
            ll_wanshan.setVisibility(View.VISIBLE);
            settleInPresenter.querySettleInWorker();
            initRecyclerView();
            tv_cancel_identity.setOnClickListener(new OnMultiClickListener() {
                @Override
                public void onMultiClick(View v) {
                    mApp.getConfirmDialog().show("确定注销身份？", new ConfirmDialog.ConfirmCallback() {
                        @Override
                        public void onOk() {
                            mApp.getLoadingDialog().show();
                            settleInCancelIdentityPresenter.cancelIdentityWorker();
                        }

                        @Override
                        public void onCancel() {
                        }
                    });
                }
            });
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_settlein;
    }


    @Override
    protected void onResume() {
        super.onResume();
        settlementInformationPresenter.querySettlementInformation();


    }

    @OnClick({R.id.tv_perfect_information, R.id.tv_skill_introduction, R.id.tv_project_experience})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_perfect_information:
                if (status == 0) {
                    mApp.shortToast("信息审核中,暂不可修改信息");
                } else {
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("bsettledWorkerVo", bsettledWorkerVo);
                    startActivity(PerfectInformationActivity.class, bundle1);
                }
                break;
            case R.id.tv_skill_introduction:
                if (role.equals("WORKER")) {
                    if (status == 0) {
                        mApp.shortToast("技能审核中,暂不可修改信息");
                    } else {
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("skillsIntroduce", skillsIntroduce);
                        bundle1.putString("skillsImages", skillsImages);
                        startActivity(SkillIntroductionActivity.class, bundle1);
                    }
                } else {
                    mApp.shortToast("请先完善信息");
                }
                break;
            case R.id.tv_project_experience:
                if (role.equals("WORKER")) {
                    if (status == 0) {
                        mApp.shortToast("入驻信息审核中,暂不可修改信息");
                    } else {
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("role", role);
                        startActivity(ProjectExperienceActivity.class, bundle1);
                    }
                } else {
                    mApp.shortToast("请先完善信息");
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void getSettleInDataSuccess(SettlelnEntity settlelnEntity) {

        bsettledWorkerVo = settlelnEntity.getBsettledWorkerVo();
        status = bsettledWorkerVo.getStatus();
        if (status == 0) {//待审核
            rel_refuse.setVisibility(View.GONE);
            tv_denial_reason.setVisibility(View.GONE);
            tv_status.setText("待审核");
            tv_status.setVisibility(View.VISIBLE);
            tv_status.setBackgroundColor(Color.parseColor("#FE9500"));
        } else if (status == 2) {//拒绝
            tv_status.setText("已拒绝");
            tv_status.setVisibility(View.VISIBLE);
            tv_status.setBackgroundColor(Color.parseColor("#EC4949"));
            if (!TextUtils.isEmpty(bsettledWorkerVo.getRefuseReason())) {
                rel_refuse.setVisibility(View.VISIBLE);
                tv_denial_reason.setText(bsettledWorkerVo.getRefuseReason());
            }
        }

        tv_name.setText(bsettledWorkerVo.getName());
        tv_description.setText(bsettledWorkerVo.getProfession());
        tv_skill.setText(bsettledWorkerVo.getWorkType());
        tv_phone.setText(bsettledWorkerVo.getPhone());
        tv_city.setText(bsettledWorkerVo.getAreaList());
        Glide.with(this).
                applyDefaultRequestOptions(new RequestOptions().
                        circleCrop()).load(bsettledWorkerVo.getProfile()).into(riv_image);
        if (TextUtils.isEmpty(bsettledWorkerVo.getCertificate())) {
            ll_certificate.setVisibility(View.GONE);
        } else {
            ll_certificate.setVisibility(View.VISIBLE);
            String[] split = bsettledWorkerVo.getCertificate().split(",");
            if (TextUtils.isEmpty(split[0])) {
                tv_certificate1.setVisibility(View.GONE);
            } else {
                tv_certificate1.setText(split[0]);
                if (TextUtils.isEmpty(split[1])) {
                    tv_certificate2.setVisibility(View.GONE);
                    tv_certificate3.setVisibility(View.GONE);
                } else {
                    tv_certificate2.setText(split[1]);
                    if (TextUtils.isEmpty(split[2])) {
                        tv_certificate3.setVisibility(View.GONE);
                    } else {
                        tv_certificate3.setText(split[1]);
                    }
                }
            }
        }

        //技能数据
        skillsIntroduce = settlelnEntity.getBsettledWorkerVo().getSkillsIntroduce();
        if (!TextUtils.isEmpty(skillsIntroduce)) {
            skillsImages = settlelnEntity.getBsettledWorkerVo().getSkillsImages();
            ll_jineng.setVisibility(View.VISIBLE);
            tv_introduction.setVisibility(View.GONE);
            tv_introduce.setText(skillsIntroduce);
            List<String> strings = DevicePermissionsUtils.stringToList(skillsImages);
            mAdapter.update(strings);
        }

        //项目经验
        workerList = settlelnEntity.getBprojectWorkerList();
        if (workerList != null && workerList.size() > 0) {
            mAdapterProject.update(workerList);
        }

    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mEasyRecyclerView.setLayoutManager(layoutManager1);
        mEasyRecyclerView.setAdapter(mAdapter = new Adapter<String>(this, false) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder<String>(parent, R.layout.item_settlein_image) {
                    private ImageView iv_image;

                    @Override
                    public void initView() {
                        iv_image = $(R.id.iv_steps_image);
                    }

                    @Override
                    public void setData(String data) {
                        super.setData(data);
                        Picasso.with(SettleInActivity.this).load(data).into(iv_image);
                        iv_image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<String> strings = new ArrayList<>();
                                strings.add(data);
                                ViewBigImageActivity.startImageList(SettleInActivity.this, 0, strings, null);
                            }
                        });
                    }
                };
            }
        });

        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        recycler_project.setLayoutManager(myLinearLayoutManager);
        ((SimpleItemAnimator) recycler_project.getItemAnimator()).setSupportsChangeAnimations(false);
        recycler_project.getItemAnimator().setChangeDuration(0);
        recycler_project.setAdapter(mAdapterProject = new Adapter<SettlelnEntity.BprojectWorkerListBean>(this, false) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder<SettlelnEntity.BprojectWorkerListBean>(parent, R.layout.item_settlein_project) {
                    private TextView tv_project_name, tv_project_time,
                            tv_project_type, tv_project_content;

                    @Override
                    public void initView() {
                        tv_project_name = $(R.id.tv_project_name);
                        tv_project_time = $(R.id.tv_project_time);
                        tv_project_type = $(R.id.tv_project_type);
                        tv_project_content = $(R.id.tv_project_content);
                    }

                    @Override
                    public void setData(SettlelnEntity.BprojectWorkerListBean data) {
                        super.setData(data);
                        tv_project_name.setText(data.getName());
                        tv_project_time.setText(data.getStartDate() + "-" + data.getEndDate());
                        tv_project_type.setText(data.getJobs());
                        tv_project_content.setText(data.getContent());
                    }
                };
            }
        });
        mAdapterProject.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("role", role);
                bundle1.putSerializable("workerList", mAdapterProject.getItem(position));
                startActivity(ProjectExperienceActivity.class, bundle1);
            }
        });
    }

    @Override
    public void getSettleInDataFail(String info) {
        mApp.shortToast(info);
        mApp.getLoadingDialog().hide();
    }

    @Override
    public void getSettleInCompanyDataSuccess(SettlelnCompanyEntity settlelnEntity) {

    }

    @Override
    public void getSettleInCompanyDataFail(String info) {

    }


    @Override
    public void getCancelIdentitySuccess() {
        mApp.shortToast("注销成功");
        mApp.getLoadingDialog().hide();
        finish();
    }

    @Override
    public void getCancelIdentityFail(String info) {
        mApp.shortToast(info);
        mApp.getLoadingDialog().hide();
    }

    @Override
    public void getCancelIdentityCompanySuccess() {
    }

    @Override
    public void getCancelIdentityCompanyFail(String info) {

    }

    @Override
    public void loadSettlementInformationSuccess(SettlementEntity settlementEntity) {
        role = settlementEntity.getRole();
        initData();
    }

    @Override
    public void loadSettlementInformationFail(int code,String info) {
    }
}
