package com.qujiali.jiaogegongren.ui.settlein.workersettlin.view;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.SettlelnCompanyEntity;
import com.qujiali.jiaogegongren.bean.SettlelnEntity;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.OnMultiClickListener;
import com.qujiali.jiaogegongren.common.model.widget.DoubleTimeSelectDialog;
import com.qujiali.jiaogegongren.common.utils.TimeUtil;
import com.qujiali.jiaogegongren.ui.settlein.workersettlin.presenter.ProjectExperiencePresenter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;

public class ProjectExperienceActivity extends BaseActivity implements IProjectExperienceView {

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
    private String startDate, endDate;
    private SettlelnEntity.BprojectWorkerListBean workerListBean;
    private SettlelnCompanyEntity.BProjectEnterpriseBean enterpriseListbean;
    private String role;

    @Override
    protected void initView() {
        mTitle.setText("项目经验");
        mCommonRight.setText("确定");

        role = getIntent().getStringExtra("role");

        if (Objects.equals(role, "WORKER")) {
            workerListBean = (SettlelnEntity.BprojectWorkerListBean) getIntent().getParcelableExtra("workerList");

            if (null != workerListBean) {
                et_project_name.setText(workerListBean.getName());
                et_project_type.setText(workerListBean.getJobs());
                tv_project_time.setText(workerListBean.getStartDate() + " - " + workerListBean.getEndDate());
                et_content.setText(workerListBean.getContent());
            }
        } else if (Objects.equals(role, "ENTERPRISE")) {
            enterpriseListbean = (SettlelnCompanyEntity.BProjectEnterpriseBean) getIntent().getSerializableExtra("workerList");
            if (null != enterpriseListbean) {
                et_project_type.setVisibility(View.GONE);
                et_project_name.setText(enterpriseListbean.getName());
                tv_project_time.setText(enterpriseListbean.getStartDate() + " - " + enterpriseListbean.getEndDate());
                et_content.setText(enterpriseListbean.getContent());
            }
        }
        mCommonRight.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                postData();
            }
        });
        tv_project_time.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View v) {
                DoubleTimeSelectDialog doubleTimeSelectDialog = new DoubleTimeSelectDialog(ProjectExperienceActivity.this, "", "1970-01-01");
                doubleTimeSelectDialog.show();
                doubleTimeSelectDialog.setOnDateSelectFinished(new DoubleTimeSelectDialog.OnDateSelectFinished() {
                    @Override
                    public void onSelectFinished(String startTime, String endTime) {
                        Logger.d("开始时间---" + startTime);
                        Logger.d("结束时间---" + endTime);
                        startDate = startTime;
                        endDate = endTime;
                        tv_project_time.setText(startTime + "  -  " + endTime);
                    }
                });
            }
        });
    }

    private void postData() {

        if (Objects.equals(role, "WORKER")) {
            postWorker();
        } else if (Objects.equals(role, "ENTERPRISE")) {
            postentErprise();
        }
    }

    private void postentErprise() {
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

    private void postWorker() {
        Map<String, Object> objectMap = new HashMap<>();

        if (TextUtils.isEmpty(et_project_name.getText().toString().trim())) {
            mApp.shortToast("请输入项目名称");
            return;
        }

        if (TextUtils.isEmpty(et_project_type.getText().toString().trim())) {
            mApp.shortToast("请输入所在职位类型");
            return;
        }

        if (TextUtils.isEmpty(et_content.getText().toString().trim())) {
            mApp.shortToast("请输入项目介绍");
            return;
        }

        objectMap.put("name", et_project_name.getText().toString().trim());
        objectMap.put("jobs", et_project_type.getText().toString().trim());
        objectMap.put("content", et_content.getText().toString().trim());
        objectMap.put("startDate", startDate);
        objectMap.put("endDate", endDate);
        mApp.getLoadingDialog().show();
        projectExperiencePresenter.sendProjectExperience(objectMap);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_project_experience;
    }


    @Override
    public void sendProjectExperienceSuccess() {
        mApp.getLoadingDialog().hide();
        mApp.shortToast("提交成功");
        finish();
    }

    @Override
    public void sendProjectExperienceFail(String info) {
        mApp.getLoadingDialog().hide();
        mApp.shortToast(info);
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
