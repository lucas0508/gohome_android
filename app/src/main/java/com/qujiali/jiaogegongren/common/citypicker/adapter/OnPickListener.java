package com.qujiali.jiaogegongren.common.citypicker.adapter;


import com.qujiali.jiaogegongren.common.citypicker.model.City;

public interface OnPickListener {
    void onPick(int position, City data);
    void onLocate();
    void onCancel();
}
