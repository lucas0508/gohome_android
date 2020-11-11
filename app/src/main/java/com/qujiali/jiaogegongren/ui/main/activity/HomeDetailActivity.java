package com.qujiali.jiaogegongren.ui.main.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.AttentionStautsEntity;
import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.common.base.Adapter;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.MyAppGlideModule;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.base.ViewHolder;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.common.model.captchaview.SwipeCaptchaView;
import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;
import com.qujiali.jiaogegongren.ui.banner.presenter.BannerPresenter;
import com.qujiali.jiaogegongren.ui.banner.view.activity.IBannerView;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.HomeDetailPresenter;
import com.qujiali.jiaogegongren.ui.main.fragment.view.IHomeDetailView;
import com.qujiali.jiaogegongren.ui.me.activity.IQueryAttentionView;
import com.qujiali.jiaogegongren.ui.me.model.impl.IAttentionCancelView;
import com.qujiali.jiaogegongren.ui.me.presenter.AttentionCancelPresenter;
import com.qujiali.jiaogegongren.ui.me.presenter.QueryAttentionPresenter;
import com.qujiali.jiaogegongren.ui.other.view.ViewBigImageActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.ProjectExperienceActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeDetailActivity extends BaseActivity implements IHomeDetailView, IBannerView, IAttentionCancelView, IQueryAttentionView {

    private HomeDetailPresenter homeDetailPresenter = new HomeDetailPresenter(this);
    private BannerPresenter bannerPresenter = new BannerPresenter(this);
    private AttentionCancelPresenter attentionCancelPresenter = new AttentionCancelPresenter(this);
    private QueryAttentionPresenter queryAttentionPresenter = new QueryAttentionPresenter(this);

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_home_age)
    TextView tv_home_age;
    @BindView(R.id.tv_work_type)
    TextView tv_work_type;
    @BindView(R.id.tv_home_auth)
    TextView tv_home_auth;
    @BindView(R.id.tv_home_title)
    TextView tv_home_title;
    @BindView(R.id.tv_home_skill)
    TextView tv_home_skill;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_look_number)
    TextView tv_look_number;
    @BindView(R.id.tv_call_number)
    TextView tv_call_number;
    @BindView(R.id.tv_home_city)
    TextView tv_home_city;
    @BindView(R.id.tv_home_attention)
    TextView tv_home_attention;
    @BindView(R.id.tv_skill_introduction)
    TextView tv_skill_introduction;
    @BindView(R.id.tv_introduce)
    TextView tv_introduce;
    @BindView(R.id.tv_project)
    TextView tv_project;
    @BindView(R.id.recycler_project)
    RecyclerView recycler_project;
    @BindView(R.id.recycler_view)
    EasyRecyclerView mEasyRecyclerView;
    @BindView(R.id.iv_banner)
    ImageView iv_banner;
    @BindView(R.id.riv_image)
    RoundImageView riv_image;
    @BindView(R.id.scrollview)
    NestedScrollView scrollview;
    @BindView(R.id.iv_back)
    ImageView iv_back;


    @BindView(R.id.rel)
    RelativeLayout relativeLayout;


    private String phoneAll;
    private Adapter<String> mAdapter;
    private Adapter<SettlelnEntity.BprojectWorkerListBean> mAdapterProject;
    /**
     * 0： 点击查看电话
     * 1： 底部广告图
     */
    private int bannerType = 1;
    private String focusIdWorker="" ;
    private String focusIdEnterprise="" ;
    private String role;
    private int id;
    private SettlelnCompanyEntity.BSettledEnterpriseVoBean bSettledEnterpriseVo;
    private SettlelnEntity.BsettledWorkerVoBean bsettledWorkerVo;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_homedetail;
    }

    @Override
    protected void initView() {
        tvTitle.setText("详情");

        id = getIntent().getIntExtra("id", -1);
        role = getIntent().getStringExtra("role");

        initRecyclerView();
        assert role != null;

        if (role.equals("WORKER")) {
            mApp.getLoadingDialog().show();
            homeDetailPresenter.queryWorker(String.valueOf(id));
        } else if (role.equals("ENTERPRISE")) {
            mApp.getLoadingDialog().show();
            homeDetailPresenter.queryEnterprise(String.valueOf(id));
        }
        queryAttentionPresenter.queryAttention(id, role);
        bannerPresenter.queryBannerDataList(UserInfo.getCityCode(), "4", "2");

        initListeners();

//        scrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                    // 滚动到底
//                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
//                    Log.i("TAG", "BOTTOM SCROLL");
//                    iv_back.setVisibility(View.VISIBLE);
//                }else {
//                    iv_back.setVisibility(View.GONE);
//                }
//            }
//        });

//        iv_back.setOnClickListener(new OnMultiClickListener() {
//            @Override
//            public void onMultiClick(View v) {
//                scrollview.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        scrollview.fullScroll(ScrollView.FOCUS_UP);
//                    }
//                });
//            }
//        });
    }

    private void initListeners() {

        relativeLayout.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (tv_look_number.getVisibility()==View.VISIBLE){
                    bannerType = 0;
                    bannerPresenter.queryBannerDataList(UserInfo.getCityCode(), "3", "1");
                    return;
                }
                if (tv_call_number.getVisibility()==View.VISIBLE){
                    callPhone(phoneAll);
                }
            }
        });

     /*   tv_look_number.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {

            }
        });
        tv_call_number.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {

            }
        });*/

        tv_home_attention.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                if (mApp.isLoginToDialog()) {
                    if (role.equals("WORKER")) {
                        if (TextUtils.isEmpty(focusIdWorker)) {
                            attentionCancelPresenter.addAttention(id, role);
                            queryAttentionPresenter.queryAttention(id, role);
                        }
//                        else {
//                            attentionCancelPresenter.cancelAttention(focusIdWorker, role);
//                            queryAttentionPresenter.queryAttention(id, role);
//                        }
                    } else if (role.equals("ENTERPRISE")) {
                        if (TextUtils.isEmpty(focusIdEnterprise)) {
                            attentionCancelPresenter.addAttention(id, role);
                        }
//                        else {
//                            attentionCancelPresenter.cancelAttention(focusIdEnterprise, role);
//                        }
                    }
                }
            }
        });
    }

    private void initData(SettlelnEntity settlelnEntity) {
        bsettledWorkerVo = settlelnEntity.getBsettledWorkerVo();
        tv_name.setText(settlelnEntity.getBsettledWorkerVo().getName());
        tv_sex.setText(settlelnEntity.getBsettledWorkerVo().getSexText());
        tv_home_age.setText(String.valueOf(settlelnEntity.getBsettledWorkerVo().getAge()));
        tv_work_type.setText(settlelnEntity.getBsettledWorkerVo().getDegreeText());
        if (TextUtils.isEmpty(settlelnEntity.getBsettledWorkerVo().getAuthentication())) {
            tv_home_auth.setVisibility(View.GONE);
        } else {
            tv_home_auth.setText(settlelnEntity.getBsettledWorkerVo().getAuthentication());
        }
        Glide.with(this).applyDefaultRequestOptions(MyAppGlideModule.getRequestOptions())
                .load(settlelnEntity.getBsettledWorkerVo().getProfile()).into(riv_image);
        tv_home_title.setText(settlelnEntity.getBsettledWorkerVo().getProfession());
        tv_home_skill.setText(settlelnEntity.getBsettledWorkerVo().getWorkType());
        phoneAll = settlelnEntity.getBsettledWorkerVo().getPhone();
        String phone = phoneAll.substring(0, 3) + "****" + phoneAll.substring(7, phoneAll.length());
        tv_number.setText(phone);
        tv_home_city.setText(settlelnEntity.getBsettledWorkerVo().getAreaList());

        if (TextUtils.isEmpty(focusIdWorker)) {
            tv_home_attention.setText("关注");
            tv_home_attention.setBackgroundResource(R.drawable.common_button_selector_blue);
        } else {
            tv_home_attention.setText("已关注");
            tv_home_attention.setBackgroundResource(R.drawable.background_shape_drak);
        }

        //技能数据
        String skillsIntroduce = settlelnEntity.getBsettledWorkerVo().getSkillsIntroduce();
        if (!TextUtils.isEmpty(skillsIntroduce)) {
            String skillsImages = settlelnEntity.getBsettledWorkerVo().getSkillsImages();
//            ll_jineng.setVisibility(View.VISIBLE);
//            tv_introduction.setVisibility(View.GONE);
            tv_introduce.setText(skillsIntroduce);
            if (!TextUtils.isEmpty(skillsImages)) {
                List<String> strings = DevicePermissionsUtils.stringToList(skillsImages);
                mAdapter.update(strings);
            }
        }

        //项目经验
        List<SettlelnEntity.BprojectWorkerListBean> workerList = settlelnEntity.getBprojectWorkerList();
        if (workerList != null && workerList.size() > 0) {
            tv_project.setVisibility(View.GONE);
            mAdapterProject.update(workerList);
        } else {
            tv_project.setVisibility(View.VISIBLE);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
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
                        Picasso.with(HomeDetailActivity.this).load(data).into(iv_image);
                        // Glide.with(SettleInActivity.this).load(data).into(iv_image);
                        iv_image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<String> strings = new ArrayList<>();
                                strings.add(data);
                                ViewBigImageActivity.startImageList(HomeDetailActivity.this, 0, strings, null);
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
                    private View line;

                    @Override
                    public void initView() {
                        tv_project_name = $(R.id.tv_project_name);
                        tv_project_time = $(R.id.tv_project_time);
                        tv_project_type = $(R.id.tv_project_type);
                        tv_project_content = $(R.id.tv_project_content);
                        line = $(R.id.line);
                    }

                    @Override
                    public void setData(SettlelnEntity.BprojectWorkerListBean data) {
                        super.setData(data);
                        tv_project_name.setText(data.getName());
                        tv_project_time.setText(data.getStartDate() + "-" + data.getEndDate());
                        if (TextUtils.isEmpty(data.getJobs())) {
                            tv_project_type.setVisibility(View.GONE);
                        } else {
                            tv_project_type.setText(data.getJobs());
                        }
                        tv_project_content.setText(data.getContent());
                        line.setVisibility(View.GONE);
                    }
                };
            }
        });
        mAdapterProject.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Bundle bundle1 = new Bundle();
//                bundle1.putSerializable("workerList", mAdapterProject.getItem(position));
//                startActivity(ProjectExperienceActivity.class, bundle1);
            }
        });
    }

    @Override
    public void queryWorkerDetailSuccess(SettlelnEntity settlelnEntity) {
        mApp.getLoadingDialog().hide();
        scrollview.setVisibility(View.VISIBLE);
        initData(settlelnEntity);
    }


    @Override
    public void queryWorkerDetailFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }

    @Override
    public void queryEnterpriseDetailSuccess(SettlelnCompanyEntity settlelnCompanyEntity) {
        mApp.getLoadingDialog().hide();
        scrollview.setVisibility(View.VISIBLE);
        initDataCompany(settlelnCompanyEntity);
    }

    private void initDataCompany(SettlelnCompanyEntity settlelnCompanyEntity) {
        bSettledEnterpriseVo = settlelnCompanyEntity.getBSettledEnterpriseVo();
        tv_name.setText(settlelnCompanyEntity.getBSettledEnterpriseVo().getName());
        tv_sex.setVisibility(View.GONE);
        tv_home_age.setVisibility(View.GONE);
        tv_work_type.setVisibility(View.GONE);
        if (TextUtils.isEmpty(settlelnCompanyEntity.getBSettledEnterpriseVo().getAuthentication())) {
            tv_home_auth.setVisibility(View.GONE);
        } else {
            tv_home_auth.setText(settlelnCompanyEntity.getBSettledEnterpriseVo().getAuthentication());
        }
        Glide.with(this).applyDefaultRequestOptions(MyAppGlideModule.getRequestOptions())
                .load(settlelnCompanyEntity.getBSettledEnterpriseVo().getProfile()).into(riv_image);
        if (TextUtils.isEmpty(focusIdEnterprise)) {
            tv_home_attention.setText("关注");
            tv_home_attention.setBackgroundResource(R.drawable.common_button_selector_blue);
        } else {
            tv_home_attention.setText("已关注");
            tv_home_attention.setBackgroundResource(R.drawable.background_shape_drak);
        }

        tv_home_title.setText(settlelnCompanyEntity.getBSettledEnterpriseVo().getProfession());
        tv_skill_introduction.setText("公司介绍");
        tv_home_skill.setText(settlelnCompanyEntity.getBSettledEnterpriseVo().getWorkType());
        phoneAll = settlelnCompanyEntity.getBSettledEnterpriseVo().getPhone();
        String phone = phoneAll.substring(0, 3) + "****" + phoneAll.substring(7, phoneAll.length());
        tv_number.setText(phone);
        tv_home_city.setText(settlelnCompanyEntity.getBSettledEnterpriseVo().getAreaList());

        //技能数据
        String skillsIntroduce = settlelnCompanyEntity.getBSettledEnterpriseVo().getSkillsIntroduce();
        if (!TextUtils.isEmpty(skillsIntroduce)) {
            String skillsImages = settlelnCompanyEntity.getBSettledEnterpriseVo().getSkillsImages();
//            ll_jineng.setVisibility(View.VISIBLE);
//            tv_introduction.setVisibility(View.GONE);
            tv_introduce.setText(skillsIntroduce);
            List<String> strings = DevicePermissionsUtils.stringToList(skillsImages);
            mAdapter.update(strings);
        }

        //项目经验
        List<SettlelnCompanyEntity.BProjectEnterpriseBean> workerList = settlelnCompanyEntity.getBProjectEnterprise();


        if (workerList != null && workerList.size() > 0) {
            tv_project.setVisibility(View.GONE);
            List<SettlelnEntity.BprojectWorkerListBean> settlelnCompanylist = new ArrayList<>();
            for (int i = 0; i < workerList.size(); i++) {
                SettlelnEntity.BprojectWorkerListBean bprojectWorkerListBean = new SettlelnEntity.BprojectWorkerListBean();
                bprojectWorkerListBean.setContent(workerList.get(i).getContent());
                bprojectWorkerListBean.setStartDate(workerList.get(i).getStartDate());
                bprojectWorkerListBean.setEndDate(workerList.get(i).getEndDate());
                bprojectWorkerListBean.setName(workerList.get(i).getName());
                settlelnCompanylist.add(bprojectWorkerListBean);
            }
            mAdapterProject.update(settlelnCompanylist);
        } else {
            tv_project.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void queryEnterpriseDetailFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }

    @Override
    public void queryBannerDataListSuccess(String str) {
        if (bannerType == 0) {
            showCaptchaDialog(str);
        } else {
            Glide.with(GoHomeApplication.getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(
                    R.mipmap.banner_default).error(R.mipmap.banner_default)).load(str).into(iv_banner);
        }
    }

    @Override
    public void queryBannerDataListFail(int code, String msg) {
        mApp.shortToast(msg);
    }


    @SuppressLint("CheckResult")
    private void showCaptchaDialog(String url) {
        Logger.t("图片路径：：：：").e(url + "");
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.ActionSheetDialogStyle);
        View view = LayoutInflater.from(this).inflate(R.layout.dailog_captcha, null);
        final SwipeCaptchaView mSwipeCaptchaView = (SwipeCaptchaView) view.findViewById(R.id.swipeCaptchaView);
        final SeekBar mSeekBar = (SeekBar) view.findViewById(R.id.dragBar);
        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog alertDialog = builder.show();

        //获取当前Activity所在的窗体
        Window dialogWindow = alertDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);

        alertDialog.setCanceledOnTouchOutside(false);
        view.findViewById(R.id.btnChange).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeCaptchaView.createCaptcha();
                mSeekBar.setEnabled(true);
                mSeekBar.setProgress(0);
            }
        });

        mSwipeCaptchaView.setOnCaptchaMatchCallback(new SwipeCaptchaView.OnCaptchaMatchCallback() {
            @Override
            public void matchSuccess(SwipeCaptchaView swipeCaptchaView) {
                //Toast.makeText(TaskDetailActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                //swipeCaptcha.createCaptcha();
                tv_look_number.setVisibility(View.GONE);
                tv_call_number.setVisibility(View.VISIBLE);
                tv_number.setText(phoneAll);
                mSeekBar.setEnabled(false);
                alertDialog.dismiss();
            }

            @Override
            public void matchFailed(SwipeCaptchaView swipeCaptchaView) {
                Toast.makeText(HomeDetailActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                swipeCaptchaView.resetCaptcha();
                mSeekBar.setProgress(0);
            }
        });
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSwipeCaptchaView.setCurrentSwipeValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //随便放这里是因为控件
                mSeekBar.setMax(mSwipeCaptchaView.getMaxSwipeValue());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("zxt", "onStopTrackingTouch() called with: seekBar = [" + seekBar + "]");
                mSwipeCaptchaView.matchCaptcha();
            }
        });

        Glide.with(this).asBitmap().listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                Bitmap resource = BitmapFactory.decodeResource(getResources(), R.mipmap.captcha_icon);
                mSwipeCaptchaView.setImageBitmap(resource);
                mSwipeCaptchaView.createCaptcha();
                mSwipeCaptchaView.setBackgroundResource(R.mipmap.captcha_icon);
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                mSwipeCaptchaView.setImageBitmap(resource);
                mSwipeCaptchaView.createCaptcha();
                return false;
            }
        }).load(url).into(mSwipeCaptchaView);
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void cancelAttentionSuccess() {
        mApp.getLoadingDialog().hide();
        mApp.shortToast("取消成功");
        tv_home_attention.setText("关注");
        tv_home_attention.setBackgroundResource(R.drawable.common_button_selector_blue);
    }

    @Override
    public void cancelAttentionFail(String msg) {
        mApp.shortToast(msg);
        mApp.getLoadingDialog().hide();
    }

    @Override
    public void addAttentionSuccess() {
        mApp.getLoadingDialog().hide();
        mApp.shortToast("关注成功");
        tv_home_attention.setText("取消关注");
        tv_home_attention.setBackgroundResource(R.drawable.background_shape_drak);
    }

    @Override
    public void addAttentionFail(String msg) {
        mApp.shortToast(msg);
        mApp.getLoadingDialog().hide();
    }


    @Override
    public void queryAttentionSuccess(AttentionStautsEntity attentionStautsEntity) {
        if (null!=attentionStautsEntity){
            if (role.equals("WORKER")) {
                focusIdWorker = String.valueOf(attentionStautsEntity.getId());
            } else if (role.equals("ENTERPRISE")) {
                focusIdEnterprise = String.valueOf(attentionStautsEntity.getId());
            }
        }
    }

    @Override
    public void queryAttentionFail(String msg) {

    }
}
