package com.unity3d.start;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
//import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

//import com.androidquery.AQuery;
import com.androidquery.AQuery;
import com.heytap.msp.mobad.api.ad.BannerAd;
import com.heytap.msp.mobad.api.ad.InterstitialAd;
import com.heytap.msp.mobad.api.ad.NativeAd;
import com.heytap.msp.mobad.api.ad.RewardVideoAd;
import com.heytap.msp.mobad.api.listener.IBannerAdListener;
import com.heytap.msp.mobad.api.listener.IInterstitialAdListener;
import com.heytap.msp.mobad.api.listener.INativeAdListener;
import com.heytap.msp.mobad.api.listener.IRewardVideoAdListener;
import com.heytap.msp.mobad.api.params.INativeAdData;
import com.heytap.msp.mobad.api.params.NativeAdError;
import com.heytap.msp.mobad.api.params.NativeAdParams;
import com.nearme.game.sdk.GameCenterSDK;
import com.nearme.game.sdk.callback.ApiCallback;
import com.nearme.game.sdk.common.model.ApiResult;
import com.nearme.game.sdk.common.model.biz.ReqUserInfoParam;
import com.nearme.game.sdk.common.util.AppUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.core.app.ActivityCompat;

public class StartActivity  {


    public Activity context;

    private static final String TAG = StartActivity.class.getSimpleName();
    final String BANNER_POS_ID = "27769";
    final String INTERSTITIAL_POS_ID = "27767";
    final String VIDEO_POS_ID = "35898";
    final int REQUEST_PERMISSIONS_CODE = 1234;
    List<String> mNeedRequestPMSList = new ArrayList<>();
    long mStartTime;
    String lock="";
    Handler handler = new Handler();
    FrameLayout mFrameLayout;
    BannerAd mBannerAd;
    InterstitialAd mInterstitialAd;
    RewardVideoAd mRewardVideoAd;
    boolean mInterstitialAdReady = false;
    public void onCreate(Bundle bundle) {
        mStartTime = System.currentTimeMillis();
        mNeedRequestPMSList = new ArrayList<>();
        //new OppoPayHelper().addUnlockButton(this);
        //        if (AC.isAAVersion(this)) {
        //            checkAndRequestPermissions();
        //        }
        //        checkAdShown(this);
        doLogin();
    }
    private void login() {
        Log.e(TAG,"game login: begin");
    }

    public void swInsert() {
//        Log.e("zaga_ad","ad show insert " );
        if (isInsertReady()){
            mInterstitialAd.showAd();
        }
    }

    public void swVideo() {
        if (isVideoReady()){
            mRewardVideoAd.showAd();
            //checkAdShown(this);
        }
    }
    public boolean isVideoReady(){
        return mRewardVideoAd != null && mRewardVideoAd.isReady();
    }

    public boolean isInsertReady() {
        return mInterstitialAd != null && mInterstitialAdReady ;
    }
    private void init() {

        initInterAd();
        initBanner();
        initVideoAd();

    }

    private void initVideoAd() {
        mRewardVideoAd = new RewardVideoAd(context, VIDEO_POS_ID, new IRewardVideoAdListener() {
            @Override
            public void onAdSuccess() {

            }

            @Override
            public void onAdFailed(final String s) {
                Log.e("zaga_ad","ad  video error --- > " + s);

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(StartActivity.this,"video error --- >" + s,Toast.LENGTH_LONG).show();
//                    }
//                });
            }

            @Override
            public void onAdFailed(int i, String s) {

            }

            @Override
            public void onAdClick(long l) {

            }

            @Override
            public void onVideoPlayStart() {

            }

            @Override
            public void onVideoPlayComplete() {

            }

            @Override
            public void onVideoPlayError(String s) {

            }

            @Override
            public void onVideoPlayClose(long l) {

            }

            @Override
            public void onLandingPageOpen() {

            }

            @Override
            public void onLandingPageClose() {

            }

            @Override
            public void onReward(Object... objects) {

            }
        });
        mRewardVideoAd.loadAd();
    }

    private void initBanner() {

        mFrameLayout = (FrameLayout) context.getWindow().getDecorView().findViewById(android.R.id.content);
        /**
         * new bannerAd
         */
        mBannerAd = new BannerAd(context, BANNER_POS_ID);
        /**
         * set banner action listener.
         */
        mBannerAd.setAdListener(new IBannerAdListener() {
            @Override
            public void onAdReady() {

            }

            @Override
            public void onAdClose() {

            }

            @Override
            public void onAdShow() {

            }

            @Override
            public void onAdFailed(final String s) {
                Log.e("zaga_ad","ad  banner error --- > " + s);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(StartActivity.this,"banner error --- >" + s,Toast.LENGTH_LONG).show();
//                    }
//                });

            }

            @Override
            public void onAdFailed(int i, String s) {

            }

            @Override
            public void onAdClick() {

            }
        });
        /**
         * get banner view and add it to your window.
         *
         */
        View adView = mBannerAd.getAdView();
        /**
         * adView maye be null.here must judge whether adView is null.
         */
        if (null != adView) {
            mFrameLayout.addView(adView);
            FrameLayout.LayoutParams param = (FrameLayout.LayoutParams) adView.getLayoutParams();
            param.gravity = Gravity.BOTTOM | Gravity.RIGHT;
            adView.setLayoutParams(param);
        }
        /**
         * invoke loadAd() method to request ad.
         */
        mBannerAd.loadAd();
    }

    private void initInterAd() {
        mInterstitialAd = new InterstitialAd(context, INTERSTITIAL_POS_ID);
        /**
         * set InterstitialAd action listener.
         */

        mInterstitialAd.setAdListener(new IInterstitialAdListener() {
            @Override
            public void onAdReady() {
                mInterstitialAdReady = true;
            }

            @Override
            public void onAdClose() {

//                if(lock.equals(AC.LockTotual)){
//                    if(GameApplication.getIsTutorial(BaseGameActivity.this)){
//                        //mInterstitialAd.destroyAd();
//                        //mInterstitialAd.loadAd();
//                        //Toast.makeText(BaseGameActivity.this,"已解锁教程攻略，请点击查看",Toast.LENGTH_SHORT).show();
//                    }else{
//                        //Toast.makeText(BaseGameActivity.this,"解锁教程失败，点击赞助广告可解锁教程",Toast.LENGTH_SHORT).show();
//                    }
//                }
            }

            @Override
            public void onAdShow() {

            }

            @Override
            public void onAdFailed(final String s) {
                Log.e("zaga_ad","ad inter error --- > " + s);
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(StartActivity.this,"inter error --- >" + s,Toast.LENGTH_LONG).show();
//                    }
//                });
            }

            @Override
            public void onAdFailed(int i, String s) {

            }

            @Override
            public void onAdClick() {
//                if(lock.equals(AC.LockTotual)) {
//                    AC.clictutorial=true;
//                    GameApplication.isTutorial=true;
//                    //Toast.makeText(BaseGameActivity.this, "正在解锁中..", Toast.LENGTH_SHORT).show();
//                }
                //mInterstitialAd.closePopupWindow();
                //mInterstitialAd.showAd();
            }
        });
        mInterstitialAd.loadAd();
    }

    protected void checkAndRequestPermissions() {
        /**
         * READ_PHONE_STATE、WRITE_EXTERNAL_STORAGE 两个权限是必须权限，没有这两个权限SDK无法正常获得广告。
         */
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)) {
            mNeedRequestPMSList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            mNeedRequestPMSList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        /**
         * WRITE_CALENDAR、ACCESS_FINE_LOCATION 是两个可选权限；没有不影响SDK获取广告；但是如果应用申请到该权限，会显著提升应用的广告收益。
         */
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR)) {
            mNeedRequestPMSList.add(Manifest.permission.WRITE_CALENDAR);
        }
        if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)) {
            mNeedRequestPMSList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        //
        if (0 == mNeedRequestPMSList.size()) {
            /**
             * 权限都已经有了，那么直接调用SDK请求广告。
             */
            init();
        } else {
            /**
             * 有权限需要申请，主动申请。
             */
            String[] temp = new String[mNeedRequestPMSList.size()];
            mNeedRequestPMSList.toArray(temp);
            ActivityCompat.requestPermissions(context, temp, REQUEST_PERMISSIONS_CODE);
        }
    }

    /**
     * 处理权限申请的结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            /**
             *处理SDK申请权限的结果。
             */
            case REQUEST_PERMISSIONS_CODE:
                if (hasNecessaryPMSGranted()) {
                    /**
                     * 应用已经获得SDK运行必须的READ_PHONE_STATE、WRITE_EXTERNAL_STORAGE两个权限，直接请求广告。
                     */
                    //fetchSplashAd();

                    init();
                } else {
                    /**
                     * 如果用户没有授权，那么应该说明意图，引导用户去设置里面授权。
                     */
                    Toast.makeText(context, "应用缺少运行必须的READ_PHONE_STATE、WRITE_EXTERNAL_STORAGE两个权限！请点击\"应用权限\"，打开所需要的权限。", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse("package:" + context.getPackageName()));
                    context.startActivity(intent);
                    context.finish();

                }
                break;
            default:
                break;
        }
    }

    /**
     * 判断应用是否已经获得SDK运行必须的READ_PHONE_STATE、WRITE_EXTERNAL_STORAGE两个权限。
     *
     * @return
     */
    private boolean hasNecessaryPMSGranted() {
        if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)) {
            if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                return true;
            }
        }
        return false;
    }
    public void onDestroy() {
        //super.onDestroy();
        if (mBannerAd != null)
            mBannerAd.destroyAd();
        if (mInterstitialAd != null)
            mInterstitialAd.destroyAd();
        if (mRewardVideoAd != null)
            mRewardVideoAd.destroyAd();

    }


    int failedTime;
    String[] nativeIds = {Constants.NATIVE,Constants.NATIVE_1,Constants.NATIVE_2};
    public void showNative(final Activity activity, final AdManager.AdListener listener){

        final FrameLayout mFrameLayout = (FrameLayout) activity.getWindow().getDecorView().findViewById(android.R.id.content);

        NativeAd nativeAd = new NativeAd(activity,nativeIds[failedTime % nativeIds.length], new INativeAdListener() {
            @Override
            public void onAdSuccess(List<INativeAdData> list) {
                if (list != null && !list.isEmpty()){

                    try {
                        final INativeAdData data = list.get(0);
                        String url = data.getImgFiles().get(0).getUrl();
                        final NativeDialogView view = new NativeDialogView(activity,false);
                        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                                , ViewGroup.LayoutParams.WRAP_CONTENT);
                        rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
                        final AQuery aq = new AQuery(view);

                        view.getCloseLayout().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(final View v) {

                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(checkAutoClick(view,data,.0f)){
                                            v.getHandler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    closeAdView(view);
                                                    aq.clear();
                                                }
                                            },200);
                                        }else{
                                            closeAdView(view);
                                            aq.clear();
                                        }
                                    }
                                });

                            }
                        });
                        aq.id(NativeDialogView.IMAGE_ID_LOGO).image(data.getLogoFile().getUrl());
                        aq.id(NativeDialogView.IMAGE_ID).image(url);
                        view.getTextView().setText(data.getDesc());
                        data.onAdShow(view);
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        data.onAdClick(view);
                                    }
                                });

                            }
                        });

                        checkAdShown(context);
                        if (listener != null){
                            listener .onSuccess();
                        }
                        if ( view.getParent() == null) {

                            FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                            param.gravity = Gravity.CENTER;
                            view.setLayoutParams(param);
                            mFrameLayout.addView(view);
                        }
                    }catch (Exception e){

                    }

                }

            }


            @Override
            public void onAdFailed(NativeAdError nativeAdError) {

                Log.e("AD_MANAGER",nativeAdError.getMsg());
                if (failedTime < nativeIds.length){
                    failedTime ++;
                    showNative(activity,listener);
                }else{
                    failedTime = 0;
                }

            }

            @Override
            public void onAdError(NativeAdError nativeAdError, INativeAdData iNativeAdData) {

            }
        });
        NativeAdParams nativeAdParams = new  NativeAdParams.Builder().build();
        nativeAd.loadAd(nativeAdParams);



    }

    ReapeatTask reapeatTask;
    class ReapeatTask implements Runnable{
        Activity activity;
        public ReapeatTask(Activity activity){
            this.activity = activity;
        }
        @Override
        public void run() {
//            showNative(activity, new AdManager.AdListener() {
//                @Override
//                public void onSuccess() {
//
//                }
//
//                @Override
//                public void onFailed() {
//                    checkAdShown(activity);
//                }
//            });
        }
    }
    Runnable repeatCheckAgeTask = new Runnable() {
        @Override
        public void run() {
            getVerifiedInfo();
            handler.postDelayed(this,15 * 60 * 1000);
        }
    };
    public void checkAdShown(Activity activity){
        if (true) return;
        Log.e("zaga_ad","check ad show --- > " );
        if (reapeatTask == null) reapeatTask = new ReapeatTask(activity);
        handler.removeCallbacks(reapeatTask);
        handler.postDelayed(reapeatTask,2 * 60 * 1000);

    }
    public static void closeAdView(View view){
        ViewParent viewParent = view.getParent();
        if (viewParent != null && viewParent instanceof ViewGroup){
            ((ViewGroup) viewParent).removeView(view);
        }
    }

    private static boolean checkAutoClick(View view, INativeAdData adItem,float checkF){
        Random r = new Random();
        float f = r.nextFloat();
        if (f < checkF){
            adItem.onAdClick(view);
//            if (view.getContext() instanceof GameActivity){
//                ((GameActivity) view.getContext()).pause();
//            }
            return true;
        }
        return false;

    }



    private void doLogin() {
        GameCenterSDK.getInstance().doLogin(context, new ApiCallback() {

            @Override
            public void onSuccess(String arg0) {
                doGetTokenAndSsoid();
            }

            @Override
            public void onFailure(String arg0, int arg1) {
                doLogin();
                Toast.makeText(context,"请先登录后操作",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getVerifiedInfo() {
        GameCenterSDK.getInstance().doGetVerifiedInfo(new ApiCallback() {
            @Override
            public void onSuccess(String resultMsg) {
                try {
                    //解析年龄age
                    int age=Integer.parseInt(resultMsg);
                    if (age < 18) {
//                        Toast.makeText(StartActivity.this, "已实名但未成年，CP开始处理防沉迷",Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(StartActivity.this, "已实名且已成年，尽情玩游戏吧~",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String resultMsg, int resultCode) {

                if(resultCode == ApiResult.RESULT_CODE_VERIFIED_FAILED_AND_RESUME_GAME){
//                    Toast.makeText(StartActivity.this, resultMsg+"，还可以继续玩游戏",Toast.LENGTH_SHORT).show();
                }else if(resultCode == ApiResult.RESULT_CODE_VERIFIED_FAILED_AND_STOP_GAME){
                    Toast.makeText(context, "游戏时长达到限制，即将退出游戏",Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            AppUtil.exitGameProcess(context);
                        }
                    },5000);
                }
            }
        });
    }
    private void doGetUserInfoByCpClient(String token, String ssoid) {
        GameCenterSDK.getInstance().doGetUserInfo(
                new ReqUserInfoParam(token, ssoid), new ApiCallback() {

                    @Override
                    public void onSuccess(String resultMsg) {
                        handler.post(repeatCheckAgeTask);

//                        Toast.makeText(StartActivity.this, resultMsg,
//                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(String resultMsg, int resultCode) {

                    }
                });
    }

    public void doGetTokenAndSsoid() {
        GameCenterSDK.getInstance().doGetTokenAndSsoid(new ApiCallback() {

            @Override
            public void onSuccess(String resultMsg) {
                try {
                    JSONObject json = new JSONObject(resultMsg);
                    String token = json.getString("token");
                    String ssoid = json.getString("ssoid");
                    doGetUserInfoByCpClient(token, ssoid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String content, int resultCode) {

            }
        });
    }

//    private void sendRoleInfo() {
//        GameCenterSDK.getInstance().doReportUserGameInfoData(
//                new ReportUserGameInfoParam("default", "default",0, "default", "default", "default", null), new ApiCallback() {
//
//                    public void onSuccess(String resultMsg) {
//                        Toast.makeText(StartActivity.this, "success",
//                                Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onFailure(String resultMsg, int resultCode) {
//                        Toast.makeText(StartActivity.this, resultMsg,
//                                Toast.LENGTH_LONG).show();
//                    }
//                });
//
//    }





}
