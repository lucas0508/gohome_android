package com.qujiali.jiaogegongren.ui.other.view;

import android.widget.TextView;

import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.common.base.BaseActivity;

import butterknife.BindView;

public class ProductBriefActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTitle;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_product_brief;
    }

    @Override
    protected void initView() {
        mTitle.setText("产品简介");
    }
}
