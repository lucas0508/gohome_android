package com.qujiali.jiaogegongren.ui.apply.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.common.base.Adapter;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.ViewHolder;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.ui.apply.presenter.ApplyPresenter;
import com.qujiali.jiaogegongren.ui.main.activity.HomeDetailActivity;
import com.qujiali.jiaogegongren.ui.main.activity.RecruitmentDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

public class ApplyActivity extends BaseActivity implements IApplyView {

    private ApplyPresenter applyPresenter = new ApplyPresenter(this);

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.recycler)
    EasyRecyclerView mEasyRecyclerView;
    private Adapter<RecruitmentEntity.ApplyVosBean> mAdapter;
    private int id;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_apply;
    }

    @Override
    protected void initView() {
        mTitle.setText("报名列表");

        id = getIntent().getIntExtra("id", -1);
        initRecyclerView();
        mApp.getLoadingDialog().show();
        applyPresenter.queryApplyData(id,mAdapter.refreshPage());

    }

    private void initRecyclerView() {
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(this);
        //添加Android自带的分割线
        mEasyRecyclerView.setLayoutManager(myLinearLayoutManager);
        mEasyRecyclerView.setAdapterWithProgress(mAdapter = new Adapter<RecruitmentEntity.ApplyVosBean>(this) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {

                return new ViewHolder<RecruitmentEntity.ApplyVosBean>(parent, R.layout.activity_apply_item) {

                    private TextView  tv_name;
                    private RoundImageView riv_image;


                    @Override
                    public void initView() {
                        tv_name=$(R.id.tv_name);
                        riv_image=$(R.id.riv_image);
                    }

                    @Override
                    public void setData(RecruitmentEntity.ApplyVosBean data) {
                        super.setData(data);
                        tv_name.setText(data.getRoleName());
                        Picasso.with(ApplyActivity.this).load(data.getRoleProfile()).into(riv_image);
                    }
                };
            }
        });

        mEasyRecyclerView.setRefreshListener(() -> applyPresenter.queryApplyData(id,mAdapter.refreshPage()));
        mAdapter.setMore(new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                applyPresenter.queryApplyData(id,mAdapter.getNextPage());
            }

            @Override
            public void onMoreClick() {
            }
        });
        mAdapter.setOnItemClickListener(position -> {
            Intent intent = new Intent(this, HomeDetailActivity.class);
            intent.putExtra("id", mAdapter.getItem(position).getWorkResultId());
            intent.putExtra("role", mAdapter.getItem(position).getWorkType());
            startActivity(intent);
        });
    }


    @Override
    public void queryApplyDataSuccess(List<RecruitmentEntity.ApplyVosBean> applyVosBean) {
        mApp.getLoadingDialog().hide();
        mAdapter.addAll(applyVosBean);
    }

    @Override
    public void queryApplyDataFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }
}
