package com.qujiali.jiaogegongren.ui.main.fragment.presenter;

import com.qujiali.jiaogegongren.bean.NewsEntity;
import com.qujiali.jiaogegongren.common.base.BasePresenter;
import com.qujiali.jiaogegongren.common.base.IBaseModel;
import com.qujiali.jiaogegongren.common.net.HttpProvider;
import com.qujiali.jiaogegongren.common.net.ResponseEntity;
import com.qujiali.jiaogegongren.ui.main.fragment.model.NewsListModel;
import com.qujiali.jiaogegongren.ui.main.fragment.model.impl.INewsListModel;
import com.qujiali.jiaogegongren.ui.main.fragment.view.INewsListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author QiZai
 * @desc
 * @date 2018/7/20 17:29
 */

public class NewsListPresenter extends BasePresenter {
    private INewsListView mNewsListView;
    private INewsListModel mNewsListModel = new NewsListModel();

    public NewsListPresenter(INewsListView newsListView) {
        mNewsListView = newsListView;
    }

    /**
     * 查询新闻
     *
     * @param data
     */
    public void queryNews(Map<String, Object> data) {
        data=new HashMap<>();
        mNewsListModel.queryNews(data, new IBaseModel.OnCallbackListener() {
            @Override
            public void callback(ResponseEntity res) {
                if (HttpProvider.isSuccessful(res.getCode())) {
                    mNewsListView.queryNewsSuccess((ArrayList<NewsEntity>) res.getRows());
                } else {
                    mNewsListView.queryNewsFail(res.getMsg());
                }
            }
        });
    }
}
