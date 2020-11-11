package com.qujiali.jiaogegongren;

import androidx.multidex.MultiDexApplication;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.umeng.commonsdk.UMConfigure;

import static com.qujiali.jiaogegongren.common.global.Constants.LOGGER_TAG;

/**
 * @author QiZai
 * @desc
 * @date 2018/4/11
 */

public class GoHomeApplication extends MultiDexApplication {

    private static GoHomeApplication context;

    public static GoHomeApplication getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        /*测试数据库*/
        //Stetho.initializeWithDefaults(this);
        /*日志*/
        initLogger();
        /*初始化mob*/
        /*初始化LeakCanary*/
        // initLeakCanary();

        UMConfigure.init(this,"5f23747cd30932215473a103","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");

        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);
    }

    /**
     * 初始化日志
     * (Optional) Whether to show thread info or not. Default true
     * (Optional) How many method line to show. Default 2
     * (Optional) Custom tag for each log. Default PRETTY_LOGGER
     */
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(2)
                .tag(LOGGER_TAG)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }
}
