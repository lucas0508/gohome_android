package com.qujiali.jiaogegongren.ui.me.activity;

import android.widget.TextView;

import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.common.base.BaseActivity;

import butterknife.BindView;

public class PersonalActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTitle;
    @Override
    protected void initView() {
        mTitle.setText("个人信息");
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_personal;
    }
}
