package com.qujiali.jiaogegongren.ui.main.fragment.view;


import com.qujiali.jiaogegongren.bean.NewsEntity;

import java.util.ArrayList;

/**
 * @author QiZai
 * @desc
 * @date 2018/7/20 17:25
 */

public interface INewsListView {

    /**
     * 查询新闻 - 成功
     *
     * @param dataList
     */
    void queryNewsSuccess(ArrayList<NewsEntity> dataList);

    /**
     * 查询新闻 - 失败
     *
     * @param info
     */
    void queryNewsFail(String info);
}
