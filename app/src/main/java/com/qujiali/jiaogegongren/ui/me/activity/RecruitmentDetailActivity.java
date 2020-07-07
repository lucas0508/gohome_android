package com.qujiali.jiaogegongren.ui.me.activity;

import android.widget.TextView;

import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.common.base.BaseActivity;

import butterknife.BindView;

public class RecruitmentDetailActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView mTitle;

    @Override
    protected void initView() {
        mTitle.setText("详情");
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_recruitmentdetail;
    }
}
