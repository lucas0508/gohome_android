package com.qujiali.jiaogegongren.common.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * Json字符串转换类
 *
 * @author QiZai
 * @date 2018/4/11
 */

public class GsonUtils {
    private static Gson gson = new Gson();

    /**
     * 将json数据转换成对应的类型数据
     */
    public static <T> T parseJsonWithClass(String jsonData, Type type) {
        return gson.fromJson(jsonData, type);
    }

    /**
     * 将其他类型数据转换成json数据
     */
    public static String parseToJson(Object jsonData) {
        return gson.toJson(jsonData);
    }

    /**
     * 将json数据转换成list集合
     */
    public static <T> List<T> parseJsonArrayWithGson(String jsonData, Class<T> type) {
        return gson.fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
    }
}
