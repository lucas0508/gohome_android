package com.qujiali.jiaogegongren.ui.other.view;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.VersionUpdateEntity;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.dialog.VersionUpdateDialog;
import com.qujiali.jiaogegongren.common.global.GlobalConstants;
import com.qujiali.jiaogegongren.common.utils.CompareVersions;
import com.qujiali.jiaogegongren.common.utils.DevicePermissionsUtils;
import com.qujiali.jiaogegongren.ui.main.activity.MainActivity;
import com.qujiali.jiaogegongren.ui.main.fragment.presenter.VersionUpdatePresenter;
import com.qujiali.jiaogegongren.ui.main.fragment.view.IVersionUpdateView;

import butterknife.BindView;

import static com.qujiali.jiaogegongren.common.global.Constants.WEB_KEY_FLAG;
import static com.qujiali.jiaogegongren.common.global.Constants.WEB_KEY_URL;

public class AboutActivity extends BaseActivity implements IVersionUpdateView {
    private VersionUpdatePresenter versionUpdate = new VersionUpdatePresenter(this);
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_version)
    TextView tv_version;
    @BindView(R.id.ll_product_brief)
    LinearLayout ll_product_brief;
    @BindView(R.id.company_profile)
    LinearLayout company_profile;
    @BindView(R.id.ll_update_version)
    LinearLayout ll_update_version;
    @BindView(R.id.tv_login_agreement_user_agreement)
    TextView mLoginAgreement;
    @BindView(R.id.tv_login_agreement_privacyPolicy)
    TextView mLoginAgreementPrivacy;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        mTitle.setText("关于我们");
        tv_version.setText("版本  v" + DevicePermissionsUtils.getAppCurrentVersion());
        mLoginAgreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, WebActivity.class);
                intent.putExtra(WEB_KEY_URL, GlobalConstants.AGREEMENT_URL);
                intent.putExtra(WEB_KEY_FLAG, 1);
                startActivity(intent);
            }
        });
        mLoginAgreementPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AboutActivity.this, WebActivity.class);
                intent1.putExtra(WEB_KEY_URL, GlobalConstants.PRIVACYPOLICY_URL);
                intent1.putExtra(WEB_KEY_FLAG, 1);
                startActivity(intent1);
            }
        });

        ll_update_version.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                mApp.getLoadingDialog().show();
                versionUpdate.updateVersion();
            }
        });

        ll_product_brief.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                startActivity(ProductBriefActivity.class);
            }
        });
        company_profile.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                startActivity(CompanyProfileActivity.class);
            }
        });
    }

    @Override
    public void updateVersionSuccess(VersionUpdateEntity versionUpdateEntity) {
        mApp.getLoadingDialog().hide();
        String clientVersion = versionUpdateEntity.getClientVersion();
        String downloadUrl = versionUpdateEntity.getDownloadUrl();
        int updateInstall = versionUpdateEntity.getUpdateInstall();
        if (CompareVersions.compare(clientVersion, DevicePermissionsUtils.getAppCurrentVersion())) {
            VersionUpdateDialog versionUpdateDialog = new VersionUpdateDialog(AboutActivity.this);
            versionUpdateDialog.showNoticeDialog(clientVersion, String.valueOf(updateInstall), downloadUrl);
        }else {
            mApp.shortToast("当前是最新版本");
        }
    }

    @Override
    public void updateVersionFail(String info) {
        mApp.shortToast(info);
    }
}
