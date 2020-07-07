package com.qujiali.jiaogegongren.ui.main.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.common.base.Adapter;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.base.ViewHolder;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.common.model.captchaview.SwipeCaptchaView;
import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;
import com.qujiali.jiaogegongren.ui.banner.presenter.BannerPresenter;
import com.qujiali.jiaogegongren.ui.banner.view.activity.IBannerView;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.RecruitmentDetailPresenter;
import com.qujiali.jiaogegongren.ui.other.view.ViewBigImageActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.SettleInActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecruitmentDetailActivity extends BaseActivity implements IRecruitmentDetailView, IBannerView {

    private RecruitmentDetailPresenter recruitmentDetailPresenter = new RecruitmentDetailPresenter(this);
    private BannerPresenter bannerPresenter = new BannerPresenter(this);
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_recruitment_title)
    TextView tv_recruitment_title;
    @BindView(R.id.tv_price)
    TextView tv_price;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_location)
    TextView tv_location;
    @BindView(R.id.tv_work_type)
    TextView tv_work_type;
    @BindView(R.id.riv_headImage)
    RoundImageView riv_headImage;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_look_number)
    TextView tv_look_number;
    @BindView(R.id.tv_call_number)
    TextView tv_call_number;


    @BindView(R.id.tv_detailed_description)
    TextView tv_detailed_description;
    @BindView(R.id.addviewlayout)
    LinearLayout addviewlayout;
    @BindView(R.id.scroll_view)
    ScrollView scroll_view;
    @BindView(R.id.iv_banner)
    ImageView iv_banner;
    @BindView(R.id.recycler_view)
    EasyRecyclerView mEasyRecyclerView;
    private Adapter<String> mAdapter;
    /**
     * 0： 点击查看电话
     * 1： 底部广告图
     */
    private int bannerType = 1;
    private String phoneAll;

    @Override
    protected void initView() {
        mTitle.setText("详情");
        mApp.getLoadingDialog().show();
        int id = getIntent().getIntExtra("id", -1);
        recruitmentDetailPresenter.queryRecruitmentDetailData(id);
        bannerPresenter.queryBannerDataList(UserInfo.getCityCode(), "4","1");
        initRecyclerView();
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
                        Logger.d("图片-->" + data);
                        Picasso.with(RecruitmentDetailActivity.this).load(data).into(iv_image);
                        // Glide.with(SettleInActivity.this).load(data).into(iv_image);
                        iv_image.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<String> strings = new ArrayList<>();
                                strings.add(data);
                                ViewBigImageActivity.startImageList(RecruitmentDetailActivity.this, 0, strings, null);
                            }
                        });
                    }
                };
            }
        });
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_recruitmentdetail;
    }

    @Override
    public void queryRecruitmentDetailDataSuccess(RecruitmentEntity recruitmentEntity) {
        mApp.getLoadingDialog().hide();
        initData(recruitmentEntity);
    }

    private void initData(RecruitmentEntity recruitmentEntity) {
        scroll_view.setVisibility(View.VISIBLE);
        tv_recruitment_title.setText(recruitmentEntity.getTitle());
        tv_price.setText(recruitmentEntity.getPriceMargin());
        tv_time.setText(recruitmentEntity.getCreateTime());
        tv_location.setText("位置：" + recruitmentEntity.getAreaList());
        tv_work_type.setText("工种：" + recruitmentEntity.getWorkType());
        Glide.with(this).load(recruitmentEntity.getProfile()).into(riv_headImage);
        tv_name.setText(recruitmentEntity.getUserName());
        phoneAll = recruitmentEntity.getPhone();
        String phone = phoneAll.substring(0, 3) + "****" + phoneAll.substring(7, phoneAll.length());
        tv_number.setText(phone);
        tv_detailed_description.setText(recruitmentEntity.getDescribe());
        String[] split = recruitmentEntity.getLabels().split(",");
        addviewlayout.removeAllViews();
        for (int i = 0; i < split.length; i++) {
            View viewItemParent = LayoutInflater.from(this).inflate(R.layout.item_dynamic_textview, addviewlayout, false);
            addviewlayout.addView(viewItemParent);
            final View viewItem = addviewlayout.getChildAt(i);
            TextView mTaskTitle = viewItem.findViewById(R.id.tv_addview);
            mTaskTitle.setText(split[i]);
        }

        tv_look_number.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                bannerType = 0;
                bannerPresenter.queryBannerDataList(UserInfo.getCityCode(), "3","1");
            }
        });
        tv_call_number.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                callPhone(phoneAll);
            }
        });

        List<String> strings = DevicePermissionsUtils.stringToList(recruitmentEntity.getImags());
        mAdapter.addAll(strings);

    }

    @Override
    public void queryRecruitmentDetailDataFail(String info) {
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
                Toast.makeText(RecruitmentDetailActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
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

}



