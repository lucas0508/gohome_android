package com.qujiali.jiaogegongren.ui.main.fragment.view;

import com.qujiali.jiaogegongren.bean.LabelEntity;
import com.qujiali.jiaogegongren.bean.NewsEntity;
import com.qujiali.jiaogegongren.bean.PostRecruitEntity;
import com.qujiali.jiaogegongren.bean.RecruitmentEntity;

import java.util.ArrayList;
import java.util.Map;

public interface IPostRecruitmentView {

    /**
     * 查询标签 - 成功
     *
     * @param dataList
     */
    void queryLabelSuccess(ArrayList<LabelEntity> dataList);

    void queryLabelFail(String info);

    void submitSuccess();

    void submitWorkersFail(String info);

    void queryWorkersSuccess(PostRecruitEntity recruitmentEntity);

    void queryWorkersFail(String info);
}
