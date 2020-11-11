package com.qujiali.jiaogegongren.ui.main.fragment.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.CompanyEntity;
import com.qujiali.jiaogegongren.bean.RealNameEntity;
import com.qujiali.jiaogegongren.bean.SettlementEntity;
import com.qujiali.jiaogegongren.bean.UserInfoEntity;
import com.qujiali.jiaogegongren.common.base.BaseFragment;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.common.model.RoundImageView;
import com.qujiali.jiaogegongren.ui.login.view.activity.LoginActivity;
import com.qujiali.jiaogegongren.ui.main.activity.PersonalActivity;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.MinePresenter;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.SettlementInformationPresenter;
import com.qujiali.jiaogegongren.ui.me.activity.MyAttentionActivity;
import com.qujiali.jiaogegongren.ui.me.activity.MyPostActivity;
import com.qujiali.jiaogegongren.ui.other.view.AboutActivity;
import com.qujiali.jiaogegongren.ui.other.view.AccessFeedbackActivity;
import com.qujiali.jiaogegongren.ui.realname.activity.CompanyVerifiedActivity;
import com.qujiali.jiaogegongren.ui.realname.activity.IRealNameView;
import com.qujiali.jiaogegongren.ui.realname.presenter.RealNamePresenter;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.presenter.CompanyAuthPresenter;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.view.ICompanyAuthView;
import com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.view.SettleInCompanyActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.SettleInActivity;
import com.qujiali.jiaogegongren.ui.realname.activity.RealNameActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.SkillCertificationListActivity;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.SkillCertificationWorkerActivity;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MineFragment extends BaseFragment implements IMineFragmentView, ISettlementInformationView, IRealNameView, ICompanyAuthView {

    private MinePresenter minePresenter = new MinePresenter(this);
    private SettlementInformationPresenter settlementInformationPresenter = new SettlementInformationPresenter(this);
    private RealNamePresenter mRealNamePresenter = new RealNamePresenter(this);
    private CompanyAuthPresenter companyAuthPresenter = new CompanyAuthPresenter(this);

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.ib_close)
    ImageButton mClose;
    @BindView(R.id.riv_login_headImage)
    RoundImageView riv_login_headImage;
    @BindView(R.id.tv_login_name)
    TextView tv_login_name;

    @BindView(R.id.ll_skill_certification_view)
    LinearLayout ll_skill_certification;
    @BindView(R.id.ll_company_verified_view)
    LinearLayout ll_company_verified;
    @BindView(R.id.ll_verified_view)
    LinearLayout ll_verified;

    @BindView(R.id.tv_certification)
    TextView tv_certification;
    @BindView(R.id.tv_checkIn)
    TextView tv_checkIn;
    @BindView(R.id.tv_auth)
    TextView tv_auth;
    @BindView(R.id.tv_company_auth)
    TextView tv_company_auth;


    private String role = "NONE";

    @Override
    protected void initView() {
        mTitle.setText("我的");
        mClose.setVisibility(View.GONE);
        initData();

    }

    @Override
    public void onResume() {
        super.onResume();
        minePresenter.queryUserInfo();
        settlementInformationPresenter.querySettlementInformation();
/*        if (TextUtils.isEmpty(UserInfo.getToken())) {
            tv_login_name.setText("去登录");
            riv_login_headImage.setBackgroundResource(R.mipmap.common_head_picture);
        }*/

    }

    private void initData() {
    }

    @OnClick({R.id.ll_login, R.id.ll_my_post, R.id.ll_my_attention,
            R.id.ll_settlement_platform_view, R.id.ll_verified_view,
            R.id.ll_skill_certification_view, R.id.ll_company_verified_view, R.id.ll_about_us,
            R.id.ll_feedback})
    public void initOnClick(View view) {
        switch (view.getId()) {
            case R.id.ll_login:
                if (mApp.isLogin()) {
                    startActivity(PersonalActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }
                break;
            case R.id.ll_my_post:
                if (mApp.isLoginToDialog()) {
                    startActivity(MyPostActivity.class);
                }
                break;
            case R.id.ll_my_attention:
                if (mApp.isLoginToDialog()) {
                    startActivity(MyAttentionActivity.class);
                }
                break;
            case R.id.ll_settlement_platform_view:
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
                break;
            case R.id.ll_verified_view:
                if (mApp.isLoginToDialog()) {
                    startActivity(RealNameActivity.class);//工人实名
                }
                break;
            case R.id.ll_skill_certification_view:
                if (mApp.isLoginToDialog()) {
                    startActivity(SkillCertificationListActivity.class);
                }
                break;
            case R.id.ll_company_verified_view:
                if (mApp.isLoginToDialog()) {
                    startActivity(CompanyVerifiedActivity.class);
                }
                break;
            case R.id.ll_feedback:
                startActivity(AccessFeedbackActivity.class);
                break;
            case R.id.ll_about_us:
                startActivity(AboutActivity.class);
                break;
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_mine;
    }

    @Override
    public void getUserInfoSuccess(UserInfoEntity userInfoEntity) {
        tv_login_name.setText(userInfoEntity.getName());
        Glide.with(GoHomeApplication.getContext()).applyDefaultRequestOptions(new RequestOptions()
                .error(R.mipmap.head_default))
                .load(userInfoEntity.getProfile()).into(riv_login_headImage);
        UserInfo.setUserImage(userInfoEntity.getProfile());
        UserInfo.setUserPhone(userInfoEntity.getPhone());
        UserInfo.setUserName(userInfoEntity.getName());
    }

    @Override
    public void getUserInfoFail(String info, int code) {
        if (code == 401) {
            tv_login_name.setText("去登录");
            Glide.with(GoHomeApplication.getContext()).load(R.mipmap.head_default).into(riv_login_headImage);
        } else {
            mApp.shortToast(info);
        }

    }

    @Override
    public void submitUserInfoSuccess() {

    }

    @Override
    public void submitUserInfoFail(String info) {

    }


    @Override
    public void loadSettlementInformationSuccess(SettlementEntity settlementEntity) {
        role = settlementEntity.getRole();
        if (role.equals("NONE")) {
            ll_verified.setVisibility(View.GONE);
            ll_skill_certification.setVisibility(View.GONE);
            ll_company_verified.setVisibility(View.GONE);
            tv_checkIn.setText("");
        } else if (role.equals("WORKER")) {
            tv_checkIn.setText("工人入驻");
            mRealNamePresenter.queryRealNameData();
            ll_skill_certification.setVisibility(View.VISIBLE);
            ll_verified.setVisibility(View.VISIBLE);
            ll_company_verified.setVisibility(View.GONE);
        } else if (role.equals("ENTERPRISE")) {
            companyAuthPresenter.queryCompanyAuth();
            tv_checkIn.setText("企业入驻");
            ll_company_verified.setVisibility(View.VISIBLE);
            ll_skill_certification.setVisibility(View.GONE);
            ll_verified.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadSettlementInformationFail(int code,String info) {
        if (code == 401) {
            ll_verified.setVisibility(View.GONE);
            ll_skill_certification.setVisibility(View.GONE);
            ll_company_verified.setVisibility(View.GONE);
            tv_checkIn.setText("");
            tv_auth.setText("");
        }
    }

    @Override
    public void uploadCertificationInfoSuccess() {

    }

    @Override
    public void uploadCertificationInfoFail(String info) {

    }

    @Override
    public void queryCertificationInfoSuccess(RealNameEntity realNameEntity) {
        if (null != realNameEntity) {
            int status = realNameEntity.getStatus();
            if (status == 0) {
                tv_auth.setText("待审核");
                tv_auth.setTextColor(Color.parseColor("#FC8419"));
            } else if (status == 1) {
                tv_auth.setText("已实名");
                tv_auth.setTextColor(Color.parseColor("#21B11D"));
            } else if (status == 2) {
                tv_auth.setText("已拒绝");
                tv_auth.setTextColor(Color.parseColor("#E63636"));
            }
        } else {
            tv_auth.setText("");
        }
    }

    @Override
    public void queryCertificationInfoFail(String info) {

    }

    @Override
    public void sendCompanyAuthSuccess() {

    }

    @Override
    public void sendCompanyAuthFail(String info) {

    }

    @Override
    public void queryCompanyAuthSuccess(CompanyEntity companyEntity) {
        if (null != companyEntity) {
            int status = companyEntity.getStatus();
            if (status == 0) {
                tv_company_auth.setText("待审核");
                tv_company_auth.setTextColor(Color.parseColor("#FC8419"));
            } else if (status == 1) {
                tv_company_auth.setText("已实名");
                tv_company_auth.setTextColor(Color.parseColor("#21B11D"));
            } else if (status == 2) {
                tv_company_auth.setText("已拒绝");
                tv_company_auth.setTextColor(Color.parseColor("#E63636"));
            }
        } else {
            tv_company_auth.setText("");
        }
    }

    @Override
    public void queryCompanyAuthFail(String info) {

    }
}
