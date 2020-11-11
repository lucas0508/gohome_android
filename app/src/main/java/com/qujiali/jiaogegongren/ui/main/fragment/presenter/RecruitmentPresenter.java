package com.qujiali.jiaogegongren.ui.main.fragment.presenter;

import android.util.Log;

import com.qujiali.jiaogegongren.bean.RecruitmentEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.RecruitmentModel;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.IRecruitmentModel;
import com.qujiali.jiaogegongren.ui.main.fragment.view.IRecruitmentView;

import java.util.ArrayList;
import java.util.Map;

public class RecruitmentPresenter extends BasePresenter {


    private IRecruitmentView iRecruitmentView ;

    private IRecruitmentModel iRecruitmentModel = new RecruitmentModel();


    public RecruitmentPresenter(IRecruitmentView iRecruitmentView) {
        this.iRecruitmentView = iRecruitmentView;
    }

    public void loadRecruitmentData(Map<String,Object> map, String cityCode, String searchContent,String related){

        Log.e("TAG", "onMultiClick loadRecruitmentData : "+searchContent );

        iRecruitmentModel.loadRecruitmentData(map, cityCode, searchContent, related, new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())){
                    iRecruitmentView.loadRecruitmentDataSuccess((ArrayList<RecruitmentEntity>) res.getRows());
                }else {
                    iRecruitmentView.loadRecruitmentDataFail(res.getMsg());
                }
            }
        });
    }
}
