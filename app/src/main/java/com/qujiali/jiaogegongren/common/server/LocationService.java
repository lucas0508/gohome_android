package com.qujiali.jiaogegongren.common.server;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.orhanobut.logger.Logger;
import com.qujiali.jiaogegongren.common.cache.SharedPreferences.UserInfo;
import com.qujiali.jiaogegongren.ui.callphone.view.CallPhoneFragment;
import com.qujiali.jiaogegongren.ui.main.activity.MainActivity;
import com.qujiali.jiaogegongren.ui.main.fragment.view.HomePageFragment;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class LocationService extends Service {

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    public static LocationService instance;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Logger.d("进入定位Service");
        initMap(2000);
    }

    public void initMap(int time) {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置为单次定位
        mLocationOption.setOnceLocation(true);
        //获取一次定位结果：
        mLocationOption.setInterval(time);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {

        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            Log.e(TAG, "onLocationChanged: 开启定位");
            Logger.d("开启定位数据："+amapLocation);
            if (amapLocation.getErrorCode()==12 && android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.M){
                MainActivity.mainActivity.userRefusePermissionsDialog();
                return;
            }
            String cityCode;
            if (amapLocation.getAdCode() == null || "".equals(amapLocation.getAdCode())) {
                cityCode = "";
            } else if (amapLocation.getErrorCode() != 0) {
                cityCode = "";
            }else {
                cityCode = amapLocation.getAdCode();
            }
            UserInfo.setCityCode(cityCode);
            UserInfo.setCityCodeName(amapLocation.getCity());
            Logger.d(UserInfo.getCityCode());
            if (HomePageFragment.instance != null) {
                HomePageFragment.instance.setData();
            }
            if (CallPhoneFragment.instance != null) {
                CallPhoneFragment.instance.setData();
            }
            return;
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 停止定位
     *
     * @author
     * @since 2.8.0
     */
    public void stopLocation() {
        // 停止定位
        mLocationClient.stopLocation();
    }

    public void destroyLocation() {
        if (null != mLocationClient) {
            mLocationClient.onDestroy();
            mLocationClient = null;
            mLocationOption = null;
        }
    }
}
