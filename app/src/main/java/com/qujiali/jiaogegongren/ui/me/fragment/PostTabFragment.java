package com.qujiali.jiaogegongren.ui.me.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.common.base.Adapter;
import com.qujiali.jiaogegongren.common.base.BaseFragment;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.base.ViewHolder;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.ui.apply.view.ApplyActivity;
import com.qujiali.jiaogegongren.ui.main.fragment.view.PostRecruitmentActivity;
import com.qujiali.jiaogegongren.ui.me.activity.IPostTabView;
import com.qujiali.jiaogegongren.ui.me.presenter.PostTabPresenter;
import com.qujiali.jiaogegongren.ui.other.view.ViewBigImageActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.SettleInActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PostTabFragment extends BaseFragment implements IPostTabView {

    private PostTabPresenter postTabPresenter = new PostTabPresenter(this);


    @BindView(R.id.tab_recycler)
    EasyRecyclerView mEasyRecyclerView;

    private String mQueryType;
    private Adapter<RecruitmentEntity> mAdapter;
    private Adapter<RecruitmentEntity.ApplyVosBean> mAdapterImage;

    @Override
    protected void initView() {
        mQueryType = getArguments().getString("mQueryType");
        initRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        postTabPresenter.queryPostData(mQueryType, mAdapter.refreshPage());
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_post_tab;
    }

    public void initRecyclerView() {
        LinearLayoutManager myLinearLayoutManager = new LinearLayoutManager(getActivity());
        mEasyRecyclerView.setLayoutManager(myLinearLayoutManager);
        mEasyRecyclerView.setAdapterWithProgress(mAdapter = new Adapter<RecruitmentEntity>(getActivity()) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder<RecruitmentEntity>(parent, R.layout.item_fragment_post) {
                    private TextView mTitle, tv_status, mTime, tv_skill,
                            tv_home_skill_type, tv_content, recruitment_city,
                            tv_home_technicalAbility;
                    private LinearLayout addviewlayout, layout_apply;
                    private EasyRecyclerView applyRecyView;
                    private View layout_apply_view;

                    @Override
                    public void initView() {
                        mTitle = $(R.id.tv_title);
                        tv_status = $(R.id.tv_status);
                        mTime = $(R.id.tv_time);
                        tv_skill = $(R.id.tv_skill);
                        tv_home_skill_type = $(R.id.tv_home_skill_type);
                        tv_content = $(R.id.tv_content);
                        recruitment_city = $(R.id.recruitment_city);
                        tv_home_technicalAbility = $(R.id.tv_home_technicalAbility);
                        addviewlayout = $(R.id.addviewlayout);
                        applyRecyView = $(R.id.applyRecyView);
                        layout_apply = $(R.id.layout_apply);
                        layout_apply_view = $(R.id.layout_apply_view);

//                        if ("1".equals(mQueryType) || ) {
//                            layout_apply.setVisibility(View.VISIBLE);
//                            layout_apply_view.setVisibility(View.VISIBLE);
//                            LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//                            layoutManager1.setAutoMeasureEnabled(true);
//                            applyRecyView.setLayoutManager(layoutManager1);
//                            applyRecyView.setAdapter(mAdapterImage = new Adapter<RecruitmentEntity.ApplyVosBean>(getActivity(), false) {
//
//                                @Override
//                                public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
//                                    return new ViewHolder<RecruitmentEntity.ApplyVosBean>(parent, R.layout.item_post_tab_image) {
//                                        private RoundImageView iv_image;
//
//                                        @Override
//                                        public void initView() {
//                                            iv_image = $(R.id.riv_headImage);
//                                        }
//
//                                        @Override
//                                        public void setData(RecruitmentEntity.ApplyVosBean data) {
//                                            super.setData(data);
//                                            Picasso.with(getActivity()).load(data.getRoleProfile()).into(iv_image);
//                                        }
//                                    };
//                                }
//                            });
//                            mAdapterImage.setOnItemClickListener(position -> {
//                                Bundle bundle = new Bundle();
//                                startActivity(PostRecruitmentActivity.class, bundle);
//                            });
//                        }else {
//                            layout_apply.setVisibility(View.GONE);
//                            layout_apply_view.setVisibility(View.GONE);
//                        }
                    }

                    @Override
                    public void setData(RecruitmentEntity data) {
                        super.setData(data);
                        mTitle.setText(data.getTitle());
                        mTime.setText(data.getCreateTime());
                        tv_status.setText(data.getStatusName());
                        if (data.getStatus() == 0) {
                            tv_status.setBackgroundColor(Color.parseColor("#F98836"));
                        } else if (data.getStatus() == 1) {

                            tv_status.setBackgroundColor(Color.parseColor("#23AE1C"));
                        } else if (data.getStatus() == 2) {
                            tv_status.setBackgroundColor(Color.parseColor("#EB4343"));
                        }
                        tv_home_skill_type.setText(data.getWorkType());
                        tv_content.setText(data.getDescribe());
                        tv_skill.setText(data.getWorkType());
                        recruitment_city.setText(data.getAreaList() + "");
                        if (data.getStatus() == 1) {
                            if (data.getEnabled() == 1) {
                                tv_home_technicalAbility.setText("立即下线");
                                tv_home_technicalAbility.setBackgroundColor(Color.parseColor("#068BEE"));
                                tv_home_technicalAbility.setOnClickListener(new OnMultiClickListener() {
                                    @Override
                                    public void onMultiClick(View v) {
                                        postTabPresenter.setOfficeData(data.getId());
                                    }
                                });
                            } else if (data.getEnabled() == 2) {
                                tv_home_technicalAbility.setText("已下线");
                                tv_home_technicalAbility.setBackgroundColor(Color.parseColor("#BBBBBB"));
                            }
                        }
                      /*  addviewlayout.removeAllViews();
                        String labels = data.getLabels();
                        Logger.d("数据：" + data.getLabels());
                        if (!TextUtils.isEmpty(labels)) {
                            String[] split = labels.split(",");
                            for (int i = 0; i < split.length; i++) {
                                View viewItemParent = LayoutInflater.from(getActivity()).inflate(R.layout.item_dynamic_textview, addviewlayout, false);
                                addviewlayout.addView(viewItemParent);
                                final View viewItem = addviewlayout.getChildAt(i);
                                TextView mTaskTitle = viewItem.findViewById(R.id.tv_addview);
                                mTaskTitle.setText(data.getLabels().split(",")[i]);
                            }
                        }*/

                        Logger.d("------>" + data.getApplyVos());

                        if (data.getApplyVos().size() > 0) {
                            layout_apply.setVisibility(View.VISIBLE);
                            layout_apply_view.setVisibility(View.VISIBLE);

                            LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            layoutManager1.setAutoMeasureEnabled(true);
                            applyRecyView.setLayoutManager(layoutManager1);
                            applyRecyView.setAdapter(mAdapterImage = new Adapter<RecruitmentEntity.ApplyVosBean>(getActivity(), false) {

                                @Override
                                public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                                    return new ViewHolder<RecruitmentEntity.ApplyVosBean>(parent, R.layout.item_post_tab_image) {
                                        private RoundImageView iv_image;

                                        @Override
                                        public void initView() {
                                            iv_image = $(R.id.riv_headImage);
                                        }

                                        @Override
                                        public void setData(RecruitmentEntity.ApplyVosBean data) {
                                            super.setData(data);
                                            Picasso.with(getActivity()).load(data.getRoleProfile()).into(iv_image);
                                        }
                                    };
                                }


                            });
                            mAdapterImage.update(data.getApplyVos());


                            // 通过下面的方法，开启LinearLayout 的点击事件，使LinearLayout 可以调用OnClick()
                            applyRecyView.setOnTouchListener((v, event) -> {
                                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                   // myLayout.performClick();  //模拟父控件的点击
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("id",data.getId());
                                    startActivity(ApplyActivity.class, bundle);
                                }
                                return false;
                            });

//                            mAdapterImage.setOnItemClickListener(new OnItemClickListener() {
//                                @Override
//                                public void onItemClick(int position) {
//                                    Bundle bundle = new Bundle();
//                                    bundle.putInt("id",data.getId());
//                                    startActivity(ApplyActivity.class, bundle);
//                                }
//                            });

                        } else {
                            layout_apply.setVisibility(View.GONE);
                            layout_apply_view.setVisibility(View.GONE);
                        }
                    }
                };
            }

            @Override
            public int getViewType(int position) {
                return position;
            }
        });
        mAdapter.setOnItemClickListener(position -> {
            Bundle bundle = new Bundle();
            bundle.putInt("RecruitmentEntity_id", mAdapter.getItem(position).getId());
            startActivity(PostRecruitmentActivity.class, bundle);
        });

        mEasyRecyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postTabPresenter.queryPostData(mQueryType, mAdapter.refreshPage());
            }
        });
        mAdapter.setMore(new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                postTabPresenter.queryPostData(mQueryType, mAdapter.getNextPage());
            }

            @Override
            public void onMoreClick() {
            }
        });

        mEasyRecyclerView.setEmptyView(R.layout.recycler_nomore);


    }

    @Override
    public void queryMyAttentionSuccess(List<RecruitmentEntity> data) {
        mAdapter.addAll(data);

    }

    @Override
    public void queryMyAttentionFail(String info) {
        mApp.shortToast(info);
    }

    @Override
    public void setOfflineSuccess() {
        postTabPresenter.queryPostData(mQueryType, mAdapter.refreshPage());
        mApp.shortToast("下线成功！");
    }

    @Override
    public void setOfflineFail(String info) {
        mApp.shortToast(info);
    }
}
