package com.qujiali.jiaogegongren.ui.main.fragment.view;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.GoHomeApplication;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.LabelEntity;

import java.util.ArrayList;
import java.util.List;

public class PostRecruitmentAdapter extends RecyclerView.Adapter<PostRecruitmentAdapter.MyViewHolder> {
    private int lastSelectedPosition = -1;  // declare this variable
    private ArrayList<LabelEntity> mModelList;
    private int status;


    public void addAll(ArrayList<LabelEntity> modelList, int status) {
        this.mModelList = modelList;
        this.status = status;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        LabelEntity dataBean = mModelList.get(position);
        Log.e( "onBindViewHolder: ", new Gson().toJson(dataBean));
        holder.textView.setText(dataBean.getDictLabel());
//        holder.view.setBackgroundColor(dataBean.isSelected() ? Color.CYAN : Color.WHITE);
        holder.textView.setTextColor(dataBean.isSelected() ? Color.parseColor("#068BEE") : Color.DKGRAY);
        holder.view.setBackgroundResource(dataBean.isSelected() ? R.drawable.background_shape_blue : R.drawable.background_shape_drak);
       if (status==2){
           holder.textView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   ArrayList<String> selectList = new ArrayList<>();
                   for (int i = 0; i < mModelList.size(); i++) {
                       if (mModelList.get(i).isSelected()) {
                           selectList.add(mModelList.get(i).getDictLabel());
                       }
                   }
                   if (selectList.size() > 3 && !dataBean.isSelected()) {
                       Toast.makeText(GoHomeApplication.getContext(), "最多可以选择4个", Toast.LENGTH_SHORT).show();
                   } else {
                       dataBean.setSelected(!dataBean.isSelected());
                       holder.textView.setTextColor(dataBean.isSelected() ? Color.parseColor("#068BEE") : Color.DKGRAY);
                       holder.view.setBackgroundResource(dataBean.isSelected() ? R.drawable.background_shape_blue : R.drawable.background_shape_drak);
                   }
               }
           });
       }else {
           holder.textView.setOnClickListener(null);
       }

    }


    @Override
    public int getItemCount() {
        return mModelList == null ? 0 : mModelList.size();
    }

    //获得选中条目的结果
    public ArrayList<String> getSelectedItem() {
        ArrayList<String> selectList = new ArrayList<>();
        if (mModelList == null) {
            return selectList;
        }
        for (int i = 0; i < mModelList.size(); i++) {
            if (mModelList.get(i).isSelected()) {
                selectList.add(mModelList.get(i).getDictLabel());
            }
        }
        return selectList;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private TextView textView;

        private MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
