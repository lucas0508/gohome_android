package com.qujiali.jiaogegongren.ui.me.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.model.ScaleTransitionPagerTitleView;
import com.qujiali.jiaogegongren.common.model.TabFragmentAdapter;
import com.qujiali.jiaogegongren.ui.main.fragment.view.PostRecruitmentActivity;
import com.qujiali.jiaogegongren.ui.me.fragment.PostTabFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyPostActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_common_right)
    TextView mCommonRight;
    @BindView(R.id.tab_home)
    MagicIndicator magicIndicator;
    @BindView(R.id.vp_home)
    ViewPager mVpHome;

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> mTitleArray = new ArrayList<>();
    private List<String> status = new ArrayList<>();

    @Override
    protected void initView() {
        mTitle.setText("我的发布");
        mCommonRight.setText("发布招工");
        mCommonRight.setTextColor(Color.parseColor("#068BEE"));
        mCommonRight.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                startActivity(PostRecruitmentActivity.class);
            }
        });
        initMagicIndicator();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_mypost;
    }

    private void initMagicIndicator() {
        if (mTitleArray.size()>0){
        }else {
            mTitleArray.add("全部");
            mTitleArray.add("待审核");
            mTitleArray.add("已审核");
            mTitleArray.add("已拒绝");
            status.add("");
            status.add("0");
            status.add("1");
            status.add("2");
            for (int i = 0; i < mTitleArray.size(); i++) {
                fragments.add(addFragmentNewInstance(status.get(i), i));
            }
        }
        TabFragmentAdapter mAdapter = new TabFragmentAdapter(getSupportFragmentManager(), fragments, mTitleArray);
        mVpHome.setAdapter(mAdapter);
        mVpHome.setCurrentItem(0);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return fragments == null ? 0 : fragments.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mTitleArray.get(index));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.parseColor("#068BEE"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVpHome.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            /**
             * 设置下划线
             * @param context
             * @return
             */
            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setLineHeight(UIUtil.dip2px(context, 3));
                indicator.setLineWidth(UIUtil.dip2px(context, 10));
                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
                indicator.setStartInterpolator(new AccelerateInterpolator());
                indicator.setEndInterpolator(new DecelerateInterpolator(1.0f));
                indicator.setColors(Color.parseColor("#068BEE"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mVpHome);
    }

    private PostTabFragment addFragmentNewInstance(String status, int position) {
        PostTabFragment postTabFragment = new PostTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mQueryType", status);
        bundle.putInt("pageNumber", 1);
        postTabFragment.setArguments(bundle);
        return postTabFragment;
    }
}
