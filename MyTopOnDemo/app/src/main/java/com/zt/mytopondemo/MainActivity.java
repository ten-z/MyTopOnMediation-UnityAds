package com.zt.mytopondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anythink.core.api.ATAdConst;
import com.anythink.core.api.ATAdInfo;
import com.anythink.core.api.ATNetworkConfirmInfo;
import com.anythink.core.api.AdError;
import com.anythink.interstitial.api.ATInterstitial;
import com.anythink.interstitial.api.ATInterstitialExListener;
import com.anythink.rewardvideo.api.ATRewardVideoAd;
import com.anythink.rewardvideo.api.ATRewardVideoExListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Teng.Demo";

    private ATInterstitial mInterstitialAd;
    private ATRewardVideoAd mRewardVideoAd;

    private int mRewardCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInterstitial("b5c21a303c25e0");
        initReward("b5b449f809139c");

        findViewById(R.id.tv_initialize).setOnClickListener(view -> {
            Toast.makeText(MainActivity.this, "According to the TopOn documentation, the initialization process has been done in the DemoApplication" , Toast.LENGTH_SHORT).show();
        });
        findViewById(R.id.tv_load_interstitial_ad).setOnClickListener(view -> {
            mInterstitialAd.load();
        });
        findViewById(R.id.tv_show_interstitial_ad).setOnClickListener(view -> {
            mInterstitialAd.show(MainActivity.this);
        });
        findViewById(R.id.tv_load_rewarded_video_ad).setOnClickListener(view -> {
            mRewardVideoAd.load();
        });
        findViewById(R.id.tv_show_rewarded_video_ad).setOnClickListener(view -> {
            mRewardVideoAd.show(MainActivity.this);
        });
    }

    private void initInterstitial(String placementId) {
        mInterstitialAd = new ATInterstitial(this, placementId);
        mInterstitialAd.setAdListener(new ATInterstitialExListener() {

            @Override
            public void onDeeplinkCallback(ATAdInfo adInfo, boolean isSuccess) {
                Log.i(TAG, "onDeeplinkCallback:" + adInfo.toString() + "--status:" + isSuccess);
            }

            @Override
            public void onDownloadConfirm(Context context, ATAdInfo adInfo, ATNetworkConfirmInfo networkConfirmInfo) {

            }

            @Override
            public void onInterstitialAdLoaded() {
                Log.i(TAG, "onInterstitialAdLoaded");
                Toast.makeText(MainActivity.this, "onInterstitialAdLoaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdLoadFail(AdError adError) {
                Log.i(TAG, "onInterstitialAdLoadFail:\n" + adError.getFullErrorInfo());
                Toast.makeText(MainActivity.this, "onInterstitialAdLoadFail:" + adError.getFullErrorInfo(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdClicked(ATAdInfo entity) {
                Log.i(TAG, "onInterstitialAdClicked:\n" + entity.toString());
                Toast.makeText(MainActivity.this, "onInterstitialAdClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdShow(ATAdInfo entity) {
                Log.i(TAG, "onInterstitialAdShow:\n" + entity.toString());
                Toast.makeText(MainActivity.this, "onInterstitialAdShow", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdClose(ATAdInfo entity) {
                Log.i(TAG, "onInterstitialAdClose:\n" + entity.toString());
                Toast.makeText(MainActivity.this, "onInterstitialAdClose", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdVideoStart(ATAdInfo entity) {
                Log.i(TAG, "onInterstitialAdVideoStart:\n" + entity.toString());
                Toast.makeText(MainActivity.this, "onInterstitialAdVideoStart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdVideoEnd(ATAdInfo entity) {
                Log.i(TAG, "onInterstitialAdVideoEnd:\n" + entity.toString());
                Toast.makeText(MainActivity.this, "onInterstitialAdVideoEnd", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterstitialAdVideoError(AdError adError) {
                Log.i(TAG, "onInterstitialAdVideoError:\n" + adError.getFullErrorInfo());
                Toast.makeText(MainActivity.this, "onInterstitialAdVideoError", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void initReward(String placementId) {
        mRewardVideoAd = new ATRewardVideoAd(this, placementId);
        String userid = "test_userid_001";
        String userdata = "test_userdata_001";
        Map<String, Object> localMap = new HashMap<>();
        localMap.put(ATAdConst.KEY.USER_ID, userid);
        localMap.put(ATAdConst.KEY.USER_CUSTOM_DATA, userdata);
        mRewardVideoAd.setLocalExtra(localMap);
        mRewardVideoAd.setAdListener(new ATRewardVideoExListener() {

            @Override
            public void onDeeplinkCallback(ATAdInfo adInfo, boolean isSuccess) {
                Log.i(TAG, "onDeeplinkCallback:" + adInfo.toString() + "--status:" + isSuccess);
            }

            @Override
            public void onDownloadConfirm(Context context, ATAdInfo adInfo, ATNetworkConfirmInfo networkConfirmInfo) {

            }

            //-------------------------- Only for CSJ --------------------------
            @Override
            public void onRewardedVideoAdAgainPlayStart(ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdAgainPlayStart:\n" + entity.toString());
                Toast.makeText(MainActivity.this, "onRewardedVideoAdAgainPlayStart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdAgainPlayEnd(ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdAgainPlayEnd:\n" + entity.toString());
                Toast.makeText(MainActivity.this, "onRewardedVideoAdAgainPlayEnd", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdAgainPlayFailed(AdError errorCode, ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdAgainPlayFailed error: " + errorCode.getFullErrorInfo());
                Toast.makeText(MainActivity.this, "onRewardedVideoAdAgainPlayFailed:" + errorCode.getFullErrorInfo(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdAgainPlayClicked(ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdAgainPlayClicked:\n" + entity.toString());
                Toast.makeText(MainActivity.this, "onRewardedVideoAdAgainPlayClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAgainReward(ATAdInfo entity) {
                Log.i(TAG, "onAgainReward:\n" + entity.toString());
                Toast.makeText(MainActivity.this, "onAgainReward", Toast.LENGTH_SHORT).show();
            }
            //-------------------------- Only for CSJ --------------------------

            @Override
            public void onRewardedVideoAdLoaded() {
                Log.i(TAG, "onRewardedVideoAdLoaded");
                Toast.makeText(MainActivity.this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdFailed(AdError errorCode) {
                Log.i(TAG, "onRewardedVideoAdFailed error:" + errorCode.getFullErrorInfo());
                Toast.makeText(MainActivity.this, "onRewardedVideoAdFailed:" + errorCode.getFullErrorInfo(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdPlayStart(ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdPlayStart:\n" + entity.toString());
                Toast.makeText(MainActivity.this, "onRewardedVideoAdPlayStart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdPlayEnd(ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdPlayEnd:\n" + entity.toString());
                Toast.makeText(MainActivity.this, "onRewardedVideoAdPlayEnd", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdPlayFailed(AdError errorCode, ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdPlayFailed error:" + errorCode.getFullErrorInfo());
                Toast.makeText(MainActivity.this, "onRewardedVideoAdPlayFailed:" + errorCode.getFullErrorInfo(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdClosed(ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdClosed:\n" + entity.toString());
//                Toast.makeText(MainActivity.this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRewardedVideoAdPlayClicked(ATAdInfo entity) {
                Log.i(TAG, "onRewardedVideoAdPlayClicked:\n" + entity.toString());
                Toast.makeText(MainActivity.this, "onRewardedVideoAdPlayClicked", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onReward(ATAdInfo entity) {
                Log.e(TAG, "onReward:\n" + entity.toString());
                mRewardCount ++;
                Toast.makeText(MainActivity.this, "onReward, mRewardCount = " + mRewardCount, Toast.LENGTH_LONG).show();
            }
        });
    }
}