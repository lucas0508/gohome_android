package com.qujiali.jiaogegongren.common.cache.SharedPreferences;

import android.content.SharedPreferences;

import com.qujiali.jiaogegongren.GoHomeApplication;


/**
 * 缓存文件类 - 用户信息相关
 *
 * @author QiZai
 * @date 2018/4/12
 */

public class UserInfo {

    /**
     * 缓存对象
     */
    private static SharedPreferences sharedPreferences = SharedPreferencesProvider.getInstance(
            GoHomeApplication.getContext(),
            "USER_INFO" // 缓存文件名称
    );
    /**
     * KEY token
     */
    private static final String KEY_TOKEN = "KEY_TOKEN";

    /**
     * KEY 用户手机号
     */
    private static final String KEY_USER_PHONE = "KEY_USER_PHONE";

    /**
     * KEY 真实姓名
     */
    private static final String KEY_REAL_NAME = "USER_REAL_NAME";


    /**
     * KEY 用户头像
     */
    private static final String KEY_AVATAR = "USER_AVATAR";

    /**
     * KEY 用户性别
     */
    private static final String KEY_SEX = "USER_SEX";


    /**
     * KEY 用户等级
     */
    private static final String KEY_GRADETYPE = "GRADETYPE";

    /**
     * KEY 用户收入
     */
    private static final String KEY_USER_IMAGE = "USER_IMAGE";

    /**
     * KEY 用户总收入
     */
    private static final String KEY_TOTALCASHOUT = "TOTALCASHOUT";

    /**
     * KEY 用户是否设置支付密码
     */
    private static final String KEY_USERNAME = "USERNAME";



    /**
     * KEY 用户修改支付密码生成随机数校验
     */
    private static final String KEY_TIMESTAMP = "TIMESTAMP";

    /**
     * KEY 城市编码
     */
    private static final String KEY_CITY_CODE = "CITY_CODE";

    private static final String KEY_CITY_CODE_NAME = "CITY_CODE_NAME";

    /**
     * KEY 城市编码
     */
    private static final String KEY_RATIO = "KEY_RATIO";

    private static final String KEY_MEMBERTOTALMONEY = "KEY_MEMBERTOTALMONEY";


    public static boolean setRatio(Float Ratio) {
        return sharedPreferences.edit().putFloat(KEY_RATIO, Ratio).commit();
    }

    public static Float getRatio() {
        return sharedPreferences.getFloat(KEY_RATIO, 0f);
    }


    public static boolean setCityCode(String city) {
        return sharedPreferences.edit().putString(KEY_CITY_CODE, city).commit();
    }

    public static String getCityCode() {
        return sharedPreferences.getString(KEY_CITY_CODE, null);
    }

    public static boolean setCityCodeName(String city) {
        return sharedPreferences.edit().putString(KEY_CITY_CODE_NAME, city).commit();
    }

    public static String getCityCodeName() {
        return sharedPreferences.getString(KEY_CITY_CODE_NAME, null);
    }

    public static boolean setUserImage(String imageStr) {
        return sharedPreferences.edit().putString(KEY_USER_IMAGE, imageStr).commit();
    }

    public static String getUserImage() {
        return sharedPreferences.getString(KEY_USER_IMAGE, "0");
    }


    public static boolean setUserName(String userName) {
        return sharedPreferences.edit().putString(KEY_USERNAME, userName).commit();
    }

    public static String getUserName() {
        return sharedPreferences.getString(KEY_USERNAME, "0");
    }

    public static boolean setUserPhone(String userPhone) {
        return sharedPreferences.edit().putString(KEY_USER_PHONE, userPhone).commit();
    }

    public static String getUserPhone() {
        return sharedPreferences.getString(KEY_USER_PHONE, null);
    }


    public static String getUserInfoImage() {
        return sharedPreferences.getString(KEY_AVATAR, null);
    }


    public static String getUserNiceName() {
        return sharedPreferences.getString(KEY_REAL_NAME, null);
    }

    public static Integer getUserGradeType() {
        return sharedPreferences.getInt(KEY_GRADETYPE, 0);
    }


    public static String getUserMemberTotalMoney() {
        return sharedPreferences.getString(KEY_MEMBERTOTALMONEY, null);
    }

    public static boolean setTimestamp(long timestamp) {
        return sharedPreferences.edit().putLong(KEY_TIMESTAMP, timestamp).commit();
    }

    public static Long getTimestamp() {
        return sharedPreferences.getLong(KEY_TIMESTAMP, -1);
    }


    /**
     * 获取用户id
     *
     * @return Long，没有返回0
     */
    public static String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, "");
    }

    public static void setToken(String token) {
        String base_token = "Bearer " + token;
        sharedPreferences.edit().putString(KEY_TOKEN, base_token).apply();
    }

    /**
     * 删除用户缓存
     */
    public static boolean removeUserInfo() {
        return sharedPreferences.edit()
                .remove(KEY_TOKEN)
                .remove(KEY_USER_IMAGE)
                .remove(KEY_USER_PHONE)
                .remove(KEY_USERNAME)
                .commit();
    }
}

