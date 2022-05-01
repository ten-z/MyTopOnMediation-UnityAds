package com.zt.mytopondemo;

import android.app.Application;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;


import com.anythink.core.api.ATSDK;
import com.anythink.core.api.NetTrafficeCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DemoApplication extends Application {
    public static final String appid = "a5aa1f9deda26d";
    public static final String appKey = "4f7b9ac17decb9babec83aac078742c7";

    @Override
    public void onCreate() {
        super.onCreate();

        //Android 9 or above must be set
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = getProcessName();
            if (!getPackageName().equals(processName)) {
                WebView.setDataDirectorySuffix(processName);
            }
        }

        ATSDK.setNetworkLogDebug(true);
        ATSDK.integrationChecking(this);

        ATSDK.checkIsEuTraffic(this, new NetTrafficeCallback() {

            @Override
            public void onResultCallback(boolean isEU) {
                if (isEU && ATSDK.getGDPRDataLevel(DemoApplication.this) == ATSDK.UNKNOWN) {
                    ATSDK.showGdprAuth(DemoApplication.this);
                }

                Log.i("Demoapplication", "onResultCallback:" + isEU);
            }

            @Override
            public void onErrorCallback(String errorMsg) {
                Log.i("Demoapplication", "onErrorCallback:" + errorMsg);
            }
        });
        List excludelist = new ArrayList();
        excludelist.add("com.exclude.myoffer1");
        excludelist.add("com.exclude.myoffer2");
        ATSDK.setExcludeMyOfferPkgList(excludelist);

        Log.i("Demoapplication", "isChinaSDK:" + ATSDK.isCnSDK());
        Log.i("Demoapplication", "SDKVersionName:" + ATSDK.getSDKVersionName());

        Map<String, Object> custommap = new HashMap<String, Object>();
        custommap.put("key1","initCustomMap1");
        custommap.put("key2","initCustomMap2");
        ATSDK.initCustomMap(custommap);

        Map<String, Object> subcustommap = new HashMap<String, Object>();
        subcustommap.put("key1","initPlacementCustomMap1");
        subcustommap.put("key2","initPlacementCustomMap2");
        ATSDK.initPlacementCustomMap("b5aa1fa4165ea3",subcustommap);//native  facebook


        ATSDK.setChannel("testChannle");
        ATSDK.setSubChannel("testSubChannle");
        ATSDK.init(DemoApplication.this, appid, appKey);


    }


}
