package com.qujiali.jiaogegongren.common.base;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.qujiali.jiaogegongren.R;

/**
 * @Date on 2019/1/18.
 * @Author by xrf05.
 */

@GlideModule
public class MyAppGlideModule extends AppGlideModule {


    public static RequestOptions getRequestOptions() {
        return new RequestOptions()
                .placeholder(R.mipmap.head_default)
                .error(R.mipmap.head_default);
    }
}
