package com.qujiali.jiaogegongren.ui.settlein.workersettlin.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.bean.SkillCertificationEntity;
import com.qujiali.jiaogegongren.common.base.Adapter;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.base.ViewHolder;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.ui.login.view.activity.LoginActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter.SkillCertificationListPresenter;

import java.util.List;

import butterknife.BindView;

public class SkillCertificationListActivity extends BaseActivity implements ISkillCertificationListView {

    private SkillCertificationListPresenter skillCertificationListPresenter = new SkillCertificationListPresenter(this);

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_common_right)
    TextView mCommonRight;
    @BindView(R.id.recycler)
    EasyRecyclerView mEasyRecyclerView;


    private Adapter<SkillCertificationEntity> skillCertificationEntityAdapter;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_skillcertificationlist;
    }

    @Override
    protected void initView() {
        mTitle.setText("技能认证");
        mCommonRight.setText("添加证书");
        mCommonRight.setTextColor(Color.parseColor("#068BEE"));
        mCommonRight.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                startActivity(SkillCertificationWorkerActivity.class);
            }
        });
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        skillCertificationListPresenter.getSkillCertificationList();
    }

    private void initRecyclerView() {
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        mEasyRecyclerView.setLayoutManager(myLinearLayoutManager);
        mEasyRecyclerView.setAdapterWithProgress(skillCertificationEntityAdapter = new Adapter<SkillCertificationEntity>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder<SkillCertificationEntity>(parent, R.layout.item_activity_skillcertificationlist) {
                    private TextView tv_skill, tv_status, tv_time, tv_refuseReason;
                    private ImageView iv_image;
                    private View view_refuseReason;

                    @Override
                    public void initView() {
                        tv_skill = $(R.id.tv_skill);
                        tv_status = $(R.id.tv_status);
                        tv_time = $(R.id.tv_time);
                        tv_refuseReason = $(R.id.tv_refuseReason);
                        view_refuseReason = $(R.id.view_refuseReason);
                        iv_image = $(R.id.iv_image);
                    }

                    @Override
                    public void setData(SkillCertificationEntity data) {
                        super.setData(data);
                        tv_skill.setText(data.getName());
                        tv_time.setText(data.getCreateTime());
                        Glide.with(SkillCertificationListActivity.this).applyDefaultRequestOptions(new RequestOptions()
                                .error(R.mipmap.head_default).placeholder(R.mipmap.head_default)).load(data.getPictrues()).into(iv_image);
                        if (data.getStatus() == 0) {
                            tv_status.setText("待审核");
                            tv_status.setTextColor(Color.parseColor("#FC8419"));
                            view_refuseReason.setVisibility(View.GONE);
                            tv_refuseReason.setVisibility(View.GONE);
                        } else if (data.getStatus() == 1) {
                            tv_status.setText("已通过");
                            tv_status.setTextColor(Color.parseColor("#21B11D"));
                            view_refuseReason.setVisibility(View.GONE);
                            tv_refuseReason.setVisibility(View.GONE);
                        } else if (data.getStatus() == 2) {
                            tv_status.setText("已拒绝");
                            tv_refuseReason.setText("拒绝原因：" + data.getRefuseReason());
                            view_refuseReason.setVisibility(View.VISIBLE);
                            tv_refuseReason.setVisibility(View.VISIBLE);
                            tv_status.setTextColor(Color.parseColor("#E63636"));
                        }
                    }
                };
            }
        });

        mEasyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                skillCertificationListPresenter.getSkillCertificationList();
            }
        });

        skillCertificationEntityAdapter.setOnItemClickListener(position -> {
            SkillCertificationEntity item = skillCertificationEntityAdapter.getItem(position);
            if (item.getStatus() == 2) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("item",item);
                startActivity(SkillCertificationWorkerActivity.class,bundle);
            }
        });
    }


    @Override
    public void getSkillCertificationListSuccess(List<SkillCertificationEntity> list) {
        skillCertificationEntityAdapter.update(list);
    }

    @Override
    public void getSkillCertificationListFail(String info) {
        mApp.shortToast(info);
    }
}
