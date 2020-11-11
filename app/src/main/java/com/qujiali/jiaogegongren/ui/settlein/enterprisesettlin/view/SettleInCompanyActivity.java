package com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.view;

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

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.bean.SettlementEntity;
import com.qujiali.jiaogegongren.common.base.Adapter;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.base.ViewHolder;
import com.qujiali.jiaogegongren.common.dialog.ConfirmDialog;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.SettlementInformationPresenter;
import com.qujiali.jiaogegongren.ui.main.fragment.view.ISettlementInformationView;
import com.qujiali.jiaogegongren.ui.other.view.ViewBigImageActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter.SettleInCancelIdentityPresenter;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter.SettleInPresenter;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.ISettleInCancelIdentityView;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.ISettleInView;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.PerfectInformationActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.ProjectExperienceActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.SettleInActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.SkillIntroductionActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SettleInCompanyActivity extends BaseActivity implements ISettleInView, ISettleInCancelIdentityView, ISettlementInformationView {
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
    @BindView(R.id.tv_name)
    TextView tv_name;

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
    EasyRecyclerView recycler_project;
    private Adapter<SettlelnCompanyEntity.BProjectEnterpriseBean> mAdapterProject;
    private int status = -1;
    private SettlelnCompanyEntity.BSettledEnterpriseVoBean bSettledEnterpriseVo;
    private String skillsIntroduce;
    private List<SettlelnCompanyEntity.BProjectEnterpriseBean> bProjectEnterprise;
    private String skillsImages;

    @Override
    protected void initView() {
        mTitle.setText("企业入驻");
        role = getIntent().getStringExtra("role");
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        settlementInformationPresenter.querySettlementInformation();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_settlein_company;
    }

    private void initData() {
        if (role.equals("ENTERPRISE")) {
            tv_wanshan.setVisibility(View.GONE);
            tv_cancel_identity.setVisibility(View.VISIBLE);
            ll_wanshan.setVisibility(View.VISIBLE);
            settleInPresenter.querySettleInExperience();


            tv_cancel_identity.setOnClickListener(new OnMultiClickListener() {
                @Override
                public void onMultiClick(View v) {
                    mApp.getConfirmDialog().show("确定注销身份？", new ConfirmDialog.ConfirmCallback() {
                        @Override
                        public void onOk() {
                            mApp.getLoadingDialog().show();
                            settleInCancelIdentityPresenter.cancelIdentityExperience();
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                }
            });
        }
    }


    @OnClick({R.id.tv_perfect_information, R.id.tv_skill_introduction, R.id.tv_project_experience})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_perfect_information:
                if (status == 0) {
                    mApp.shortToast("信息审核中,暂不可修改信息");
                } else {
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("bSettledEnterpriseVo", bSettledEnterpriseVo);
                    startActivity(PerfectInformationEnterpriseActivity.class, bundle1);
                }
                break;
            case R.id.tv_skill_introduction:
                if (role.equals("ENTERPRISE")) {
                    if (status == 0) {
                        mApp.shortToast("技能审核中,暂不可修改信息");
                    } else {
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("skillsIntroduce", skillsIntroduce);
                        bundle1.putString("skillsImages", skillsImages);
                        startActivity(SkillIntroductionEnterpriseActivity.class, bundle1);
                    }
                } else {
                    mApp.shortToast("请先完善信息");
                }
                break;
            case R.id.tv_project_experience:
                if (role.equals("ENTERPRISE")) {
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

    private void initRecyclerView() {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mEasyRecyclerView.setLayoutManager(layoutManager1);
        mEasyRecyclerView.setAdapter(mAdapter = new Adapter<String>(this, false) {

            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder<String>(parent, R.layout.item_settlein_image2) {
                    private ImageView iv_image;

                    @Override
                    public void initView() {
                        iv_image = $(R.id.iv_steps_image);
                    }

                    @Override
                    public void setData(String data) {
                        super.setData(data);
                        Picasso.with(SettleInCompanyActivity.this).load(data).into(iv_image);
                        // Glide.with(SettleInActivity.this).load(data).into(iv_image);
                        iv_image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<String> strings = new ArrayList<>();
                                strings.add(data);
                                ViewBigImageActivity.startImageList(SettleInCompanyActivity.this, 0, strings, null);
                            }
                        });
                    }
                };
            }
        });

        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        recycler_project.setLayoutManager(myLinearLayoutManager);
        recycler_project.setAdapter(mAdapterProject = new Adapter<SettlelnCompanyEntity.BProjectEnterpriseBean>(this, false) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder<SettlelnCompanyEntity.BProjectEnterpriseBean>(parent, R.layout.item_settlein_project) {
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
                    public void setData(SettlelnCompanyEntity.BProjectEnterpriseBean data) {
                        super.setData(data);
                        tv_project_type.setVisibility(View.GONE);
                        tv_project_name.setText(data.getName());
                        tv_project_time.setText(data.getCreateTime());
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
                bundle1.putParcelable("workerList", mAdapterProject.getItem(position));
                startActivity(ProjectExperienceActivity.class, bundle1);
            }
        });
    }


    @Override
    public void getSettleInDataSuccess(SettlelnEntity settlelnEntity) {

    }

    @Override
    public void getSettleInDataFail(String info) {

    }

    @Override
    public void getSettleInCompanyDataSuccess(SettlelnCompanyEntity settlelnEntity) {
        Logger.d("数据--->" + settlelnEntity);
        bSettledEnterpriseVo = settlelnEntity.getBSettledEnterpriseVo();

        status = bSettledEnterpriseVo.getStatus();
        if (status == 0) {//待审核
            tv_status.setText("待审核");
            tv_status.setVisibility(View.VISIBLE);
            tv_status.setBackgroundColor(Color.parseColor("#FE9500"));
        } else if (status == 2) {//拒绝
            tv_status.setText("已拒绝");
            tv_status.setVisibility(View.VISIBLE);
            tv_status.setBackgroundColor(Color.parseColor("#EC4949"));
            if (!TextUtils.isEmpty(bSettledEnterpriseVo.getRefuseReason())) {
                rel_refuse.setVisibility(View.VISIBLE);
                tv_denial_reason.setText(bSettledEnterpriseVo.getRefuseReason());
            }
        }

        tv_name.setText(bSettledEnterpriseVo.getName());
        tv_description.setText(bSettledEnterpriseVo.getProfession());
        tv_skill.setText(bSettledEnterpriseVo.getWorkType());
        tv_phone.setText(bSettledEnterpriseVo.getPhone());
        tv_city.setText(bSettledEnterpriseVo.getAreaList());
        Glide.with(this).
                applyDefaultRequestOptions(new RequestOptions().
                        circleCrop()).load(bSettledEnterpriseVo.getProfile()).into(riv_image);

        skillsIntroduce = settlelnEntity.getBSettledEnterpriseVo().getSkillsIntroduce();
        if (!TextUtils.isEmpty(skillsIntroduce)) {
            skillsImages = settlelnEntity.getBSettledEnterpriseVo().getSkillsImages();
            ll_jineng.setVisibility(View.VISIBLE);
            tv_introduction.setVisibility(View.GONE);
            tv_introduce.setText(skillsIntroduce);
            List<String> strings = DevicePermissionsUtils.stringToList(skillsImages);
            mAdapter.update(strings);
        }
        bProjectEnterprise = settlelnEntity.getBProjectEnterprise();

        if (bProjectEnterprise != null && bProjectEnterprise.size() > 0) {
            mAdapterProject.update(bProjectEnterprise);
        }

    }

    @Override
    public void getSettleInCompanyDataFail(String info) {
        mApp.shortToast(info);
        mApp.getLoadingDialog().hide();
    }

    @Override
    public void getCancelIdentitySuccess() {

    }

    @Override
    public void getCancelIdentityFail(String info) {
        mApp.shortToast(info);
        mApp.getLoadingDialog().hide();
    }

    @Override
    public void getCancelIdentityCompanySuccess() {
        mApp.shortToast("注销成功");
        mApp.getLoadingDialog().hide();
        finish();
    }

    @Override
    public void getCancelIdentityCompanyFail(String info) {
        mApp.shortToast(info);
        mApp.getLoadingDialog().hide();
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
