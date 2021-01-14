package com.qujiali.jiaogegongren.common.citypicker.adapter;

import com.qujiali.jiaogegongren.common.citypicker.model.City;

public interface InnerListener {
    void dismiss(int position, City data);
    void locate();
}
