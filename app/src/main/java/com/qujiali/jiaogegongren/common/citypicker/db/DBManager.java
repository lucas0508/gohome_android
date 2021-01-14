package com.qujiali.jiaogegongren.common.citypicker.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;


import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.tinypinyin.lexicons.android.cncity.CnCityDict;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.bean.YwpAddressBean;
import com.qujiali.jiaogegongren.common.citypicker.model.City;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.qujiali.jiaogegongren.GoHomeApplication.getContext;
import static com.qujiali.jiaogegongren.common.citypicker.db.DBConfig.COLUMN_C_CODE;
import static com.qujiali.jiaogegongren.common.citypicker.db.DBConfig.COLUMN_C_NAME;
import static com.qujiali.jiaogegongren.common.citypicker.db.DBConfig.COLUMN_C_PINYIN;
import static com.qujiali.jiaogegongren.common.citypicker.db.DBConfig.COLUMN_C_PROVINCE;
import static com.qujiali.jiaogegongren.common.citypicker.db.DBConfig.DB_NAME_V1;
import static com.qujiali.jiaogegongren.common.citypicker.db.DBConfig.LATEST_DB_NAME;
import static com.qujiali.jiaogegongren.common.citypicker.db.DBConfig.TABLE_NAME;

/**
 * Author Bro0cL on 2016/1/26.
 */
public class DBManager {
    private static final int BUFFER_SIZE = 1024;
    private YwpAddressBean mYwpAddressBean; // 总数据
    private String DB_PATH;
    private Context mContext;
    private List<YwpAddressBean.AddressItemBean> city1;

    public DBManager(Context context) {
        this.mContext = context;
//        DB_PATH = File.separator + "data"
//                + Environment.getDataDirectory().getAbsolutePath() + File.separator
//                + context.getPackageName() + File.separator + "databases" + File.separator;
//        copyDBFile();
    }

    private void copyDBFile() {
        File dir = new File(DB_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //如果旧版数据库存在，则删除
        File dbV1 = new File(DB_PATH + DB_NAME_V1);
        if (dbV1.exists()) {
            dbV1.delete();
        }
        //创建新版本数据库
        File dbFile = new File(DB_PATH + LATEST_DB_NAME);
        if (!dbFile.exists()) {
            InputStream is;
            OutputStream os;
            try {
                is = mContext.getResources().getAssets().open(LATEST_DB_NAME);
                os = new FileOutputStream(dbFile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int length;
                while ((length = is.read(buffer, 0, buffer.length)) > 0) {
                    os.write(buffer, 0, length);
                }
                os.flush();
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<City> getAllCities() {

        List<City> result = new ArrayList<>();
        City city;
        StringBuilder jsonSB = new StringBuilder();
        try {
            BufferedReader addressJsonStream = new BufferedReader(new InputStreamReader(getContext().getAssets().open("address.json")));
            String line;
            while ((line = addressJsonStream.readLine()) != null) {
                jsonSB.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将数据转换为对象
        mYwpAddressBean = new Gson().fromJson(jsonSB.toString(), YwpAddressBean.class);
        List<YwpAddressBean.AddressItemBean> city1 = mYwpAddressBean.getCity();
        Pinyin.init(Pinyin.newConfig().with(CnCityDict.getInstance(mContext)));
        for (YwpAddressBean.AddressItemBean str : city1) {

            String name = str.getN();
            String province = "";
            String pinyin = Pinyin.toPinyin(name, "").toLowerCase();
            String code = str.getI();

            city = new City(name, province, pinyin, code);
            result.add(city);
        }
        Collections.sort(result, new CityComparator());
        return result;
    }

/*    public List<City> getAllCities(){
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + LATEST_DB_NAME, null);
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        List<City> result = new ArrayList<>();
        City city;
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_C_NAME));
            String province = cursor.getString(cursor.getColumnIndex(COLUMN_C_PROVINCE));
            String pinyin = cursor.getString(cursor.getColumnIndex(COLUMN_C_PINYIN));
            String code = cursor.getString(cursor.getColumnIndex(COLUMN_C_CODE));
            city = new City(name, province, pinyin, code);
            result.add(city);
        }
        cursor.close();
        db.close();
        Collections.sort(result, new CityComparator());
        return result;
    }*/

    public List<City> searchCity(final String keyword) {
      /*  String sql = "select * from " + TABLE_NAME + " where "
                + COLUMN_C_NAME + " like ? " + "or "
                + COLUMN_C_PINYIN + " like ? ";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(DB_PATH + LATEST_DB_NAME, null);
        Cursor cursor = db.rawQuery(sql, new String[]{"%" + keyword + "%", keyword + "%"});

        List<City> result = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_C_NAME));
            String province = cursor.getString(cursor.getColumnIndex(COLUMN_C_PROVINCE));
            String pinyin = cursor.getString(cursor.getColumnIndex(COLUMN_C_PINYIN));
            String code = cursor.getString(cursor.getColumnIndex(COLUMN_C_CODE));
            City city = new City(name, province, pinyin, code);
            result.add(city);
        }
        cursor.close();
        db.close();*/

        List<City> result = new ArrayList<>();
        City city = null;
        StringBuilder jsonSB = new StringBuilder();
        try {
            BufferedReader addressJsonStream = new BufferedReader(new InputStreamReader(getContext().getAssets().open("address.json")));
            String line;
            while ((line = addressJsonStream.readLine()) != null) {
                jsonSB.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将数据转换为对象
        mYwpAddressBean = new Gson().fromJson(jsonSB.toString(), YwpAddressBean.class);
        city1 = mYwpAddressBean.getCity();
        Pinyin.init(Pinyin.newConfig().with(CnCityDict.getInstance(mContext)));
        for (YwpAddressBean.AddressItemBean str : city1) {

            String name = str.getN();
            String pinyin = Pinyin.toPinyin(name, "");
            // city = new City(name, province, pinyin, code);
            if ((keyword != null && str.getN().toLowerCase().contains(keyword.toLowerCase()))) {
                String province = "";

                String code = str.getI();
                city = new City(name, province, pinyin, code);
                result.add(city);

            }

            Log.e( "searchCity: ",Pinyin.toPinyin(keyword, "") );
//            if (keyword != null && pinyin.contains(Pinyin.toPinyin(keyword, ""))) {
//                String province = "";
//                String code = str.getI();
//                city = new City(name, province, pinyin, code);
//                result.add(city);
//            }
            if (result.size() > 0) {
                CityComparator comparator = new CityComparator();
                Collections.sort(result, comparator);
            }
        }

        return result;
    }

    /**
     * sort by a-z
     */
    private class CityComparator implements Comparator<City> {
        @Override
        public int compare(City lhs, City rhs) {
//            Log.e("拼音", "compare-lhs--> " + lhs.getPinyin());
//            Log.d("拼音", "compare-rhs--> " + rhs.getPinyin());
            String a = lhs.getPinyin().substring(0, 1);
            String b = rhs.getPinyin().substring(0, 1);
            return a.compareTo(b);
        }
    }

}
