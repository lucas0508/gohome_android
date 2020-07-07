package com.qujiali.jiaogegongren.common.cache.SharedPreferences;

import android.content.SharedPreferences;

import com.qujiali.jiaogegongren.GoHomeApplication;

/**
 * 缓存文件类 - 用户信息相关
 *
 * @author QiZai
 * @date 2018/4/12
 */

public class CommonConfig {

    /**
     * 缓存对象
     */
    private static SharedPreferences sharedPreferences = SharedPreferencesProvider.getInstance(
            GoHomeApplication.getContext(),
            "COMMON_CONFIG_BANNER_INFO" // 缓存文件名称
    );


    /**
     * KEY Banner状态
     */
    private static final String KEY_LOCATION_STATUS_BANNER = "LOCATION_STATUS_BANNER";


    /**
     * 存储Banner状态
     *
     * @param status{ ture(已刷新首页), false(未刷新首页) }
     */
    public static boolean setLocationStatusBanner(boolean status) {
        return sharedPreferences.edit().putBoolean(KEY_LOCATION_STATUS_BANNER, status).commit();
    }

    /**
     * 获取Banner状态
     *
     * @return int，没有返回false
     */
    public static boolean getLocationStatusBanner() {
        return sharedPreferences.getBoolean(KEY_LOCATION_STATUS_BANNER, false);
    }

    /**
     * 删除Banner缓存
     *
     * @return 是否保存成功
     */
    public static boolean removeInfo() {
        return sharedPreferences.edit()
                .remove(KEY_LOCATION_STATUS_BANNER)
                .commit();
    }

    /**
     * KEY- 缓存 闪屏页图片
     */
    public static final String SPLASHACT_IMAGE = "SplashAct_Image";


    /**
     * KEY- 缓存 首页新闻list
     */
    public static final String HOMEPAGE_NEWS = "HomePage_News";

    /**
     * KEY- 缓存 首页Banner
     */
    public static final String HOMEPAGE_BANNER = "HomePage_Banner";
}
