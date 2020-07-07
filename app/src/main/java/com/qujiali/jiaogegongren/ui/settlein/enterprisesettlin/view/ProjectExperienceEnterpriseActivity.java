package com.qujiali.jiaogegongren.ui.settlein.enterprisesettlin.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.model.widget.DoubleTimeSelectDialog;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter.ProjectExperiencePresenter;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.IProjectExperienceView;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.view.ProjectExperienceActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class ProjectExperienceEnterpriseActivity extends BaseActivity implements IProjectExperienceView {
    private ProjectExperiencePresenter projectExperiencePresenter = new ProjectExperiencePresenter(this);


    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.tv_common_right)
    TextView mCommonRight;
    @BindView(R.id.et_project_name)
    EditText et_project_name;
    @BindView(R.id.et_project_type)
    EditText et_project_type;
    @BindView(R.id.tv_project_time)
    TextView tv_project_time;
    @BindView(R.id.et_content)
    EditText et_content;
    @BindView(R.id.ll_project_type)
    LinearLayout ll_project_type;
    @BindView(R.id.tv_time_view)
    TextView tv_time_view;
    private String startDate,endDate;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_project_experience;
    }

    @Override
    protected void initView() {
        mTitle.setText("项目经验");
        mCommonRight.setText("确定");
        tv_time_view.setText("项目时间");
        ll_project_type.setVisibility(View.GONE);
        mCommonRight.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                postData();
            }
        });
        tv_project_time.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                DoubleTimeSelectDialog doubleTimeSelectDialog = new DoubleTimeSelectDialog(ProjectExperienceEnterpriseActivity.this, "", "1970-01-01");
                doubleTimeSelectDialog.show();
                doubleTimeSelectDialog.setOnDateSelectFinished(new DoubleTimeSelectDialog.OnDateSelectFinished() {
                    @Override
                    public void onSelectFinished(String startTime, String endTime) {
                        Logger.d("开始时间---" + startTime);
                        Logger.d("结束时间---" + endTime);
                        startDate =startTime;
                        endDate=endTime;
                        tv_project_time.setText(startTime  + "  -  " +  endTime);
                    }
                });
            }
        });
    }

    private void postData() {
        Map<String, Object> objectMap = new HashMap<>();

        if (TextUtils.isEmpty(et_project_name.getText().toString().trim())) {
            mApp.shortToast("请输入项目名称");
            return;
        }

        if (TextUtils.isEmpty(et_content.getText().toString().trim())) {
            mApp.shortToast("请输入项目介绍");
            return;
        }

        objectMap.put("name", et_project_name.getText().toString().trim());
        objectMap.put("content", et_content.getText().toString().trim());
        objectMap.put("startDate", startDate);
        objectMap.put("endDate", endDate);
        mApp.getLoadingDialog().show();
        projectExperiencePresenter.sendCompanyProjectExperience(objectMap);
    }

    @Override
    public void sendProjectExperienceSuccess() {

    }

    @Override
    public void sendProjectExperienceFail(String info) {

    }

    @Override
    public void sendCompanyProjectExperienceSuccess() {
        mApp.getLoadingDialog().hide();
        mApp.shortToast("提交成功");
        finish();
    }

    @Override
    public void sendCompanyProjectExperienceFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
    }
}
