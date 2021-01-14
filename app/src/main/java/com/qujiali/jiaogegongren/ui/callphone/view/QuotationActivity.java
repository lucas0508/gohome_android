package com.qujiali.jiaogegongren.ui.callphone.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import com.google.gson.Gson;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.OpenRecordBean;
import com.qujiali.jiaogegongren.bean.QuotationEntity;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.base.BaseRecyclerAdapter;
import com.qujiali.jiaogegongren.common.base.BaseViewHolder;
import com.qujiali.jiaogegongren.ui.callphone.presenter.QuotationPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class QuotationActivity extends BaseActivity implements IQuotationView {
    private QuotationPresenter quotationPresenter = new QuotationPresenter(this);
    public static final int ITEM_TYPE_TITLE = 1;
    public static final int ITEM_TYPE_CONTENT = 2;
    @BindView(R.id.tv_title)
    TextView mTitle;
    @BindView(R.id.recycler_view)
    EasyRecyclerView mEasyRecyclerView;
    private BaseRecyclerAdapter<Object> quotationAdapter;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_quotation;
    }

    @Override
    protected void initView() {
        mTitle.setText("参考价");


        initRecyclerView();
        quotationPresenter.queryQuotation("");
    }

    private void initRecyclerView() {

        final GridLayoutManager glm = new GridLayoutManager(getContext(), 3);
        glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mEasyRecyclerView.getAdapter().getItemViewType(position);
                if (type == 0 || type == 1) {
                    return glm.getSpanCount();
                }
                return 3;
            }
        });
        glm.setSmoothScrollbarEnabled(true);
        glm.setAutoMeasureEnabled(true);
        mEasyRecyclerView.setLayoutManager(glm);
        // mEasyRecyclerView.setNestedScrollingEnabled(false);
        quotationAdapter = new BaseRecyclerAdapter<Object>(this, null) {
            @NonNull
            @Override
            public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                if (viewType == ITEM_TYPE_TITLE) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quotaion_title, parent, false);
                    return new TitleHolder(view);
                } else if (viewType == ITEM_TYPE_CONTENT) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quotaion_content, parent, false);
                    return new ContentHolder(view);
                } else {
                    return null;
                }
            }

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                if (holder instanceof TitleHolder) {
                    TitleHolder textViewHolder = (TitleHolder) holder;
                    textViewHolder.title.setText((String) quotationAdapter.getData(position));
                } else if (holder instanceof ContentHolder) {
                    QuotationEntity.ServiceTypeSonListBean data = (QuotationEntity.ServiceTypeSonListBean) quotationAdapter.getData(position);
                    ContentHolder contentViewHolder = (ContentHolder) holder;
                    contentViewHolder.content.setText(
                            data.getServiceName()
                    );
                }
            }

            @Override
            public int getItemViewType(int position) {
                if (quotationAdapter.getData(position) instanceof String)
                    return ITEM_TYPE_TITLE;
                else if (quotationAdapter.getData(position) instanceof QuotationEntity.ServiceTypeSonListBean) {
                    return ITEM_TYPE_CONTENT;
                }
                return ITEM_TYPE_TITLE;
            }
        };
        mEasyRecyclerView.setAdapter(quotationAdapter);
    }

    @Override
    public void queryQuotationSuccess(List<QuotationEntity> quotationEntity) {
        List<Object> serviceTypeSonListBeans = new ArrayList<>();
        for (QuotationEntity data : quotationEntity) {
            List<QuotationEntity.ServiceTypeSonListBean> serviceTypeSonList = data.getServiceTypeSonList();
            serviceTypeSonListBeans.add(data.getServiceName());
            if (serviceTypeSonList != null && serviceTypeSonList.size() > 0) {
                serviceTypeSonListBeans.addAll(serviceTypeSonList);
            }
        }
        quotationAdapter.addList(serviceTypeSonListBeans);
    }

    @Override
    public void queryQuotationFail(String info) {

    }

    static final class TitleHolder extends BaseViewHolder {
        TextView title;

        public TitleHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    static final class ContentHolder extends BaseViewHolder {
        TextView content;
        ImageView icon;

        public ContentHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.tv_content);
            icon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }
    }
}
