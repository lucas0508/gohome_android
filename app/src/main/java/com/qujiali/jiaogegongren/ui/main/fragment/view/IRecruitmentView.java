package com.qujiali.jiaogegongren.ui.main.fragment.view;

import com.qujiali.jiaogegongren.bean.HomeEntity;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;

import java.util.ArrayList;

public interface IRecruitmentView {


    void loadRecruitmentDataSuccess(ArrayList<RecruitmentEntity> recruitmentEntities);


    void loadRecruitmentDataFail(String info);

}
