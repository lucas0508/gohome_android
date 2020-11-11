package com.qujiali.jiaogegongren.ui.other.view;

import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qujiali.jiaogegongren.R;
import com.qujiali.jiaogegongren.bean.AdressDataEntity;
import com.qujiali.jiaogegongren.common.base.BaseActivity;
import com.qujiali.jiaogegongren.common.dialog.CustomPopWindow;
import com.qujiali.jiaogegongren.common.model.ListPop2Adapter;
import com.qujiali.jiaogegongren.common.model.ListPop3Adapter;
import com.qujiali.jiaogegongren.common.model.ListPopAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AddressActivity extends BaseActivity {


    @BindView(R.id.tv_province)
    TextView mProvince;
    @BindView(R.id.tv_city)
    TextView mCity;
    @BindView(R.id.tv_area)
    TextView mArea;
    @BindView(R.id.tv_chooseAddress)
    TextView mChosseAddress;
    @BindView(R.id.iv_cancel)
    ImageView mCancel;


    //存储用户选择的地区
    List<AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean> mapsData = new ArrayList<>();
    CustomPopWindow mListPopWindow;
    private AdressDataEntity adressEntity;
    private List<AdressDataEntity.RowsBean.ChildBeanXX> citybeanlist = new ArrayList<>();
    private List<AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX> areabeanlist = new ArrayList<>();
//    private List<AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean> checkList;//第三级选中的数据
    private AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean provinceList;//第一级选中的数据
    private AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean cityList;//第二级选中的数据
    private AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean arealist;//第一级选中的数据

    @Override
    protected void initView() {
        mProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopListView(mProvince, "1");
                mCity.setText("");
                mArea.setText("");
                cityList = null;
                if (null != arealist)
                    arealist=null;
            }
        });
        mCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mProvince.getText().toString().trim().equals("全国")) {
                    mCity.setText("不限");
                    cityList = null;
                } else {
                    showPopListView(mCity, "2");
                    mArea.setText("");
                }
                if (null != arealist)
                    arealist=null;
            }
        });

        mArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mProvince.getText().toString().trim().equals("全国")) {
                    mArea.setText("不限");
                } else {
                    showPopListView(mArea, "3");
                }
            }
        });

        mChosseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (provinceList == null) {
                    mApp.shortToast("请选择省");
                    return;
                }
                if (cityList == null) {
                    mApp.shortToast("请选择市");
                    return;
                }
                passByValue();
            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        StringBuilder jsonSB = new StringBuilder();
        try {
            BufferedReader addressJsonStream = new BufferedReader(new InputStreamReader(getContext().getAssets().open("data.json")));
            String line;
            while ((line = addressJsonStream.readLine()) != null) {
                jsonSB.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        adressEntity = new Gson().fromJson(jsonSB.toString(), AdressDataEntity.class);
    }

    public void passByValue() {
        Log.e("onClick", "第一极: " + new Gson().toJson(provinceList));
        Log.e("onClick", "第二极: " + new Gson().toJson(cityList));
        Log.e("onClick", "第三极: " + new Gson().toJson(arealist));

        List<AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean> result = new ArrayList<>();
        Intent intent = new Intent();

        if (null == arealist) {//区
            if (null == cityList) {//市
                if (null == provinceList) {//省
                    mApp.shortToast("请选择地区");
                    return;
                } else {
                    result.add(provinceList);
                }
            } else {
                result.add(cityList);
            }
        } else {
            result.add(arealist);
        }
        if (TextUtils.isEmpty(mArea.getText())){
            mArea.setText("不限");
        }
        if (TextUtils.isEmpty(mCity.getText())){
            mCity.setText("不限");
        }
        intent.putExtra("CityName", mProvince.getText() + "-" + mCity.getText() + "-" + mArea.getText());
        intent.putParcelableArrayListExtra("string", (ArrayList<? extends Parcelable>) result);
        setResult(0X4444, intent);
        finish();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_address;
    }


    private void showPopListView(TextView view, String type) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_list, null);
        //处理popWindow 显示内容
        handleListView(contentView, type, view);
        //创建并显示popWindow
        mListPopWindow = new CustomPopWindow.PopupWindowBuilder(this).setOutsideTouchable(true).setFocusable(false)
                .setView(contentView)
                .size(view.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()
                .showAsDropDown(view, 0, 0);
    }

    private void handleListView(View contentView, String type, TextView textView) {
        ListView listView = (ListView) contentView.findViewById(R.id.lv_pop);
        if (type.equals("1")) {
//            List<AdressDataEntity.RowsBean.ChildBeanXX> child = adressEntity.getRows().get(0).getChild();
//            AdressDataEntity.RowsBean.ChildBeanXX childBeanXX = new AdressDataEntity.RowsBean.ChildBeanXX();
//            childBeanXX.setAdCode("100000");
//            childBeanXX.setEnable("1");
//            childBeanXX.setName("全国");
//            child.set(0, childBeanXX);

            ListPopAdapter listAdapter = new ListPopAdapter(adressEntity.getRows(), this);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    provinceList = new AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean();
                    citybeanlist = adressEntity.getRows().get(position).getChild();
                    provinceList.setAdCode(adressEntity.getRows().get(position).getAdCode());
                    provinceList.setEnable(adressEntity.getRows().get(position).getEnable());
                    provinceList.setName(adressEntity.getRows().get(position).getName());
                    textView.setText(adressEntity.getRows().get(position).getName());
                    mListPopWindow.dissmiss();
                }
            });

        } else if (type.equals("2")) {
            ListPop2Adapter listAdapter = new ListPop2Adapter(citybeanlist, this);
            listView.setAdapter(listAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    areabeanlist = citybeanlist.get(position).getChild();
                    cityList = new AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean();
                    cityList.setEnable(citybeanlist.get(position).getEnable());
                    cityList.setAdCode(citybeanlist.get(position).getAdCode());
                    cityList.setAddresscode(provinceList.getAdCode() + "-" + citybeanlist.get(position).getAdCode());
                    cityList.setName(citybeanlist.get(position).getName());
                    textView.setText(citybeanlist.get(position).getName());
                    mListPopWindow.dissmiss();
                }
            });
        } else if (type.equals("3")) {
            /** 存储点击选中的数据*/
            arealist = new AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX.ChildBean();
           // checkList = new ArrayList<AdressDataEntity.RowsBean.ChildBeanXX.ChildBeanX>();
            ListPop3Adapter listPop3Adapter = new ListPop3Adapter(areabeanlist, this);
            listView.setAdapter(listPop3Adapter);

            textView.setText("");
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    arealist.setEnable(areabeanlist.get(position).getEnable());
                    arealist.setAdCode(areabeanlist.get(position).getAdCode());
                    arealist.setName(areabeanlist.get(position).getName());
                    textView.setText(areabeanlist.get(position).getName());
                    mListPopWindow.dissmiss();
//                    ListPop3Adapter.MyViewHoler holder = (ListPop3Adapter.MyViewHoler) view.getTag();
//
//                    holder.tvCbx.toggle();// 在每次获取点击的item时改变checkbox的状态
//
//                    listPop3Adapter.isSelected.put(position, holder.tvCbx.isChecked()); // 同一时候改动map的值保存状态
//
//                    areabeanlist.get(position).setAddresscode(cityList.getAddresscode() + "-" + areabeanlist.get(position).getAdCode());
//                    if (holder.tvCbx.isChecked() == true) {
//                        checkList.add(areabeanlist.get(position));
//                        areabeanlist.get(position).setIscheck(true);
//                    } else {
//                        areabeanlist.get(position).setIscheck(false);
//                        checkList.remove(areabeanlist.get(position));
//                    }
//                    if (checkList.size() > 0) {
//                        textView.setText("");
//                        String aa = "";
//                        for (int i = 0; i < checkList.size(); i++) {
//                            if (i == 0) {
//                                aa = "";
//                            } else {
//                                aa = ",";
//                            }
//                            textView.append(aa + checkList.get(i).getName());
//                        }
//                    } else {
//                        textView.setText("");
//                    }
                }
            });
        }
    }
}
