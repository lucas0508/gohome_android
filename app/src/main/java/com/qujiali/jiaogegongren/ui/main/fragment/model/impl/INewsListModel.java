package com.qujiali.jiaogegongren.ui.main.fragment.model.impl;

import com.qujiali.jiaogegongren.common.base.IBaseModel;

import java.util.Map;

/**
 * @author QiZai
 * @desc
 * @date 2018/7/20 17:25
 */

public interface INewsListModel extends IBaseModel {

    /**
     * 查询新闻
     *
     * @param data
     * @param listener
     */
    void queryNews(Map<String, Object> data, OnCallbackListener listener);
}
