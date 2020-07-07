package com.qujiali.jiaogegongren.ui.main.fragment.presenter;

import com.qujiali.jiaogegongren.bean.LabelEntity;
import com.qujiali.jiaogegongren.bean.PostRecruitEntity;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseDataEntity;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.PostRecruitmentModel;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IPostRecruitmentModel;
import com.qujiali.jiaogegongren.ui.main.fragment.view.IPostRecruitmentView;

import java.util.ArrayList;
import java.util.Map;

public class PostRecruitmentPresenter extends BasePresenter {

    private IPostRecruitmentView iPostRecruitmentView;

    private IPostRecruitmentModel iPostRecruitmentModel = new PostRecruitmentModel();

    public PostRecruitmentPresenter(IPostRecruitmentView iPostRecruitmentView) {
        this.iPostRecruitmentView = iPostRecruitmentView;
    }


    public void queryLabel(){
        iPostRecruitmentModel.queryLabelData(new IBaseModel.OnCallbackDataListener() {
            @Override
            public void callback(ResponseDataEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    iPostRecruitmentView.queryLabelSuccess((ArrayList<LabelEntity>) res.getData());
                }else {
                    iPostRecruitmentView.queryLabelFail(res.getMsg());
                }
            }
        });
    }

    public void submitWorkersData(Map<String,Object> stringObjectMap){
        iPostRecruitmentModel.submitWorkersData(stringObjectMap,new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    iPostRecruitmentView.submitSuccess();
                }else {
                    iPostRecruitmentView.submitWorkersFail(res.getMsg());
                }
            }
        });
    }

    public void queryWorkersData(int id ){
        iPostRecruitmentModel.queryWorkersData(id,new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    iPostRecruitmentView.queryWorkersSuccess((PostRecruitEntity) res.getData());
                }else {
                    iPostRecruitmentView.queryWorkersFail(res.getMsg());
                }
            }
        });
    }
}
