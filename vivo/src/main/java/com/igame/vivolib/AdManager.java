package com.igame.vivolib;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.androidquery.AQuery;
import com.bumptech.glide.Glide;
import com.igame.vivolib.adutis.Utils;
import com.vivo.ad.model.AdError;
import com.vivo.ad.nativead.NativeAdListener;
import com.vivo.ad.nativead.NativeResponse;
import com.vivo.mobilead.banner.BannerAdParams;
import com.vivo.mobilead.banner.VivoBannerAd;
import com.vivo.mobilead.interstitial.InterstitialAdParams;
import com.vivo.mobilead.interstitial.VivoInterstitialAd;
import com.vivo.mobilead.listener.IAdListener;
import com.vivo.mobilead.model.BackUrlInfo;
import com.vivo.mobilead.model.VivoAdError;
import com.vivo.mobilead.nativead.NativeAdParams;
import com.vivo.mobilead.nativead.VivoNativeAd;
import com.vivo.mobilead.unified.base.AdParams;
import com.vivo.mobilead.unified.base.VideoPolicy;
import com.vivo.mobilead.unified.base.callback.MediaListener;
import com.vivo.mobilead.unified.base.view.VivoNativeAdContainer;
import com.vivo.mobilead.unified.icon.UnifiedVivoFloatIconAd;
import com.vivo.mobilead.unified.icon.UnifiedVivoFloatIconAdListener;
import com.vivo.mobilead.unified.interstitial.UnifiedVivoInterstitialAd;
import com.vivo.mobilead.unified.interstitial.UnifiedVivoInterstitialAdListener;
import com.vivo.mobilead.unified.nativead.UnifiedVivoNativeExpressAd;
import com.vivo.mobilead.unified.nativead.UnifiedVivoNativeExpressAdListener;
import com.vivo.mobilead.unified.nativead.VivoNativeExpressView;
import com.vivo.mobilead.unified.reward.UnifiedVivoRewardVideoAd;
import com.vivo.mobilead.unified.reward.UnifiedVivoRewardVideoAdListener;
import com.vivo.mobilead.util.VOpenLog;
import com.vivo.unionsdk.open.VivoExitCallback;
import com.vivo.unionsdk.open.VivoUnionSDK;
import com.xmmy.fjmnfx.vivo.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;


//import com.vivo.ad.video.VideoAD;
//import com.vivo.ad.video.VideoADResponse;
//import com.vivo.ad.video.VideoAdListener;

public class AdManager {

    private static AdManager sInstance;
    Handler handler = new Handler(Looper.getMainLooper());

    private int mRawX, mRawY;

    public void setmRawXY(int rawX, int rawY) {
        this.mRawX = rawX;
        this.mRawY = rawY;
    }

    public static AdManager getInstance() {
        if (sInstance == null) {
            sInstance = new AdManager();
        }
        return sInstance;
    }



    Activity mActivity;

    public void init(Activity activity){
        mActivity=activity;
    }

    private VivoBannerAd mVivoBanner;
    private View mVivoBannerView;
    private RelativeLayout mBannerContainer;
    static String TAG="native";
    VivoInterstitialAd mInterAd;
    public void showInterAd(Activity activity){
        if(mInterAd==null) {
            InterstitialAdParams.Builder builder = new InterstitialAdParams.Builder(VivoConstans.VIVO_INTERSTIAL_ID);
            String backUrl = "vivobrowser://browser.vivo.com";
            String btnName = "广告";
            BackUrlInfo backUrlInfo = new BackUrlInfo(backUrl, btnName);
            builder.setBackUrlInfo(backUrlInfo);
            InterstitialAdParams adParams = builder.build();
            mInterAd = new VivoInterstitialAd(activity, adParams, new IAdListener() {
                @Override
                public void onAdShow() {
                    Log.e("Native", "onAdShow");
                    mInterAd=null;
                }

                @Override
                public void onAdFailed(VivoAdError error) {
                    Log.e("Native", "onAdFailed: " + error.toString());
                    mInterAd=null;
                }

                @Override
                public void onAdReady() {
                    Log.e("Native", "onAdReady");
                    //此回调收到之后即可调用showAd展示广告
                    mInterAd.showAd();
                    mInterAd=null;
                }

                @Override
                public void onAdClick() {
                    Log.e("Native", "onAdClick");
                }

                @Override
                public void onAdClosed() {
                    Log.e("Native", "onAdClosed");
                    mInterAd=null;
                }
            });
        }
        Log.e("Native", "loadInterAd");
        mInterAd.load();
    }


//    private VivoVideoAd mVivoVideoAd;
//    private VideoAdParams mVideoAdParams;
//    private VideoAdListener mVideoAdListener;
//    public void showRewardVideo(Activity activity,VideoAdListener listener){
//        if(mVideoAdParams!=null){
//            VideoAdParams.Builder builder = new VideoAdParams.Builder(VivoConstans.REWARD_VIDEO_ID);
//            mVideoAdParams = builder.build();
//        }
//
//        if(mVideoAdListener==null){
//            mVideoAdListener=listener;
//        }
//
//
//
//        mVivoVideoAd = new VivoVideoAd(activity, mVideoAdParams, mVideoAdListener);
//        mVivoVideoAd.loadAd();
//
//    }



    UnifiedVivoRewardVideoAd mVivoRewardVideoAd;
    private AdParams mRewardVideoAdParams;
    private MediaListener mediaListener = new MediaListener() {
        @Override
        public void onVideoStart() {
            Log.i(TAG, "onVideoStart");
            //showTip("onVideoStart");
        }

        @Override
        public void onVideoPause() {
            Log.i(TAG, "onVideoPause");

        }

        @Override
        public void onVideoPlay() {
            Log.i(TAG, "onVideoPlay");

        }

        @Override
        public void onVideoError(com.vivo.mobilead.unified.base.VivoAdError error) {
            Log.i(TAG, "onVideoError: " + error.toString());
        }

        @Override
        public void onVideoCompletion() {
            Log.i(TAG, "onVideoCompletion");
        }

        @Override
        public void onVideoCached() {
            Log.i(TAG, "onVideoCached");

        }
    };
    public void loadRewardVideo(Activity activity, UnifiedVivoRewardVideoAdListener listener){

        if(mRewardVideoAdParams==null) {
            AdParams.Builder builder = new AdParams.Builder(VivoConstans.REWARD_VIDEO_ID);
            String backUrl = "vivobrowser://browser.vivo.com";
            String btnName = "ads";
            BackUrlInfo backUrlInfo = new BackUrlInfo(backUrl, btnName);
            builder.setBackUrlInfo(backUrlInfo);
            mRewardVideoAdParams = builder.build();
        }


        mVivoRewardVideoAd = new UnifiedVivoRewardVideoAd(activity, mRewardVideoAdParams, listener);
        mVivoRewardVideoAd.setMediaListener(mediaListener);
        mVivoRewardVideoAd.loadAd();

    }

    public void showRewardVideo(Activity activity){
        if (mVivoRewardVideoAd != null) {
            mVivoRewardVideoAd.showAd(activity);
        }
    }




    UnifiedVivoInterstitialAd mVivoInsertVideoAd;
    private AdParams mInsertVideoAdParams;

    UnifiedVivoInterstitialAdListener mInsertVideoAdListener = new UnifiedVivoInterstitialAdListener() {


        @Override
        public void onAdReady() {
            Log.i(TAG, "onAdReady");
            //showTip("onAdReady");
            if(mVivoInsertVideoAd!=null){
                mVivoInsertVideoAd.showVideoAd(mActivity);
            }
        }

        @Override
        public void onAdFailed(com.vivo.mobilead.unified.base.VivoAdError error) {
            Log.i(TAG, "onAdFailed: " + error.toString());
            //showTip("onAdFailed" + error.toString());
        }

        @Override
        public void onAdClick() {
            Log.i(TAG, "onAdClick");
            //showTip("onAdClick");
        }

        @Override
        public void onAdShow() {
            Log.i(TAG, "onAdShow");
            //showTip("onAdShow");
        }

        @Override
        public void onAdClose() {
            Log.i(TAG, "onAdClose");
            //showTip("onAdClose");
        }
    };




    public void showInsertVideo(Activity activity){
        if(mInsertVideoAdParams==null) {
            AdParams.Builder videoBuilder = new AdParams.Builder(VivoConstans.VIDEO_INTERSTITIAL_POSITION_ID);
            String backUrl = "vivobrowser://browser.vivo.com";
            String btnName = "testabcdteststststststtsst";
            BackUrlInfo backUrlInfo = new BackUrlInfo(backUrl, btnName);
            videoBuilder.setBackUrlInfo(backUrlInfo);
            mInsertVideoAdParams = videoBuilder.build();
        }


        mVivoInsertVideoAd = new UnifiedVivoInterstitialAd(activity, mInsertVideoAdParams, mInsertVideoAdListener);
        mVivoInsertVideoAd.setMediaListener(mediaListener);
        mVivoInsertVideoAd.loadVideoAd();

    }


    boolean isEnableBanner=true;


    private VivoBannerAd mVivoBannerAd;
    private FrameLayout mVivoBannerContainer;
    private BannerAdParams mBannerAdParams;
    public void initNativeBanner(Activity activity){

        if(!isEnableBanner){
            return;
        }

        if (mVivoBannerContainer == null) {
            mVivoBannerContainer = new FrameLayout(activity);

            boolean isLandScreen=false;

            int layout_width=isLandScreen?DimenUtils.getScreenWidth(activity)/3:ViewGroup.LayoutParams.MATCH_PARENT;

            FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(layout_width, ViewGroup.LayoutParams.WRAP_CONTENT);
            param.gravity=Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL;
            activity.addContentView(mVivoBannerContainer, param);
        }

        if (mVivoBanner != null) {
            mVivoBanner.destroy();
        }


        if(mBannerAdParams==null) {
            BannerAdParams.Builder builder = new BannerAdParams.Builder(VivoConstans.VIVO_BANNER_ID);
            builder.setRefreshIntervalSeconds(30);
            BackUrlInfo backUrlInfo = new BackUrlInfo("", "广告");
            builder.setBackUrlInfo(backUrlInfo);
            BannerAdParams adParams = builder.build();
            mBannerAdParams = adParams;
        }
            mVivoBanner = new VivoBannerAd(activity, mBannerAdParams, new IAdListener() {
                @Override
                public void onAdShow() {
                    Log.i(TAG, "onAdShow");

                }

                @Override
                public void onAdFailed(VivoAdError error) {
                    Log.i(TAG, "onAdFailed：" + error.toString());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(activity!=null&&!activity.isFinishing()) {
                                initNativeBanner(activity);
                            }
                        }
                    },60*1000);
                }

                @Override
                public void onAdReady() {
                    Log.i(TAG, "onAdReady");

                    /**
                     * 此处可以开始显示广告了，即调用showAd()
                     */
                }

                @Override
                public void onAdClick() {
                    Log.i(TAG, "onAdClick");

                }

                @Override
                public void onAdClosed() {
                    Log.d(TAG, "onAdClosed");
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(activity!=null&&!activity.isFinishing()) {
                                initNativeBanner(activity);
                            }
                        }
                    },40*1000);

                }
            });


        mVivoBannerContainer.removeAllViews();
        mVivoBannerContainer.setVisibility(View.GONE);
        View adView = mVivoBanner.getAdView();
        if (adView != null) {
            FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity=Gravity.CENTER|Gravity.TOP;
            mVivoBannerContainer.addView(adView,lp);
        }
    }


    public void hideNativeBanner(){
        if(!isEnableBanner){
            return;
        }
        if(mVivoBannerContainer!=null){
            mVivoBannerContainer.setVisibility(View.GONE);
        }
    }

    public void showNativeBanner(){
        if(!isEnableBanner){
            return;
        }
        if(mVivoBannerContainer!=null){
            mVivoBannerContainer.setVisibility(View.VISIBLE);
        }
    }



    public void showBanner(){

    }



    long mFloatTime;
    UnifiedVivoFloatIconAd mVivoFloatIconAd;
    public void showFloatIcon(Activity activity){


        long currTime=System.currentTimeMillis();
        if(currTime-mFloatTime<30*1000){
            return;
        }
        mFloatTime=currTime;

        if(mVivoFloatIconAd!=null){
            try {
                mVivoFloatIconAd.destroy();
            }catch(Throwable ex){}
        }

        String posId = VivoConstans.FLOAT_ICON_ID;
        AdParams adParams = new AdParams.Builder(posId).build();
        mVivoFloatIconAd = new UnifiedVivoFloatIconAd(activity, adParams, new UnifiedVivoFloatIconAdListener() {
            @Override
            public void onAdShow() {
                Log.e(TAG, "onAdShowfloat");


            }

            @Override
            public void onAdFailed(@NonNull com.vivo.mobilead.unified.base.VivoAdError vivoAdError) {
                Log.e(TAG, "onAdFailedfloat: " + vivoAdError.toString());

            }

            @Override
            public void onAdReady() {
                Log.e(TAG, "onAdReadyfloat");
                if(mVivoFloatIconAd!=null){
                    mVivoFloatIconAd.showAd(activity);
                }
            }

            @Override
            public void onAdClick() {
                Log.e(TAG, "onAdClickfloat");

            }

            @Override
            public void onAdClose() {
                Log.e(TAG, "onAdClosefloat");
                mFloatTime=System.currentTimeMillis();
            }
        });
        Log.e(TAG, "onAdShowload");
        mVivoFloatIconAd.loadAd();
    }

    public void hideFloatIcon(){
        if(mVivoFloatIconAd!=null){
            mVivoFloatIconAd.destroy();

        }
    }




    public void showNativeBanner(Activity activity, final OnBannerListener listener) {
        if (mBannerContainer == null) {
            mBannerContainer = new RelativeLayout(activity);

            FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            activity.addContentView(mBannerContainer, param);
        }

        // 显示banner把上次的view清除
        mBannerContainer.removeAllViews();
        // 容器一定要先设置为 GONE, 不然无法产生收入
        mBannerContainer.setVisibility(View.GONE);

        mVivoBannerView = null;
        if (mVivoBanner != null) {
            mVivoBanner.destroy();
        }

        // 新的bannerView
        BannerAdParams.Builder builder = new BannerAdParams.Builder(VivoConstans.VIVO_BANNER_ID);
        builder.setRefreshIntervalSeconds(30);

//        BackUrlInfo backUrlInfo = new BackUrlInfo("", "我是Banner的 btn_Name");
//        builder.setBackUrlInfo(backUrlInfo);


        mVivoBanner = new VivoBannerAd(activity, builder.build(), new IAdListener() {

            @Override
            public void onAdShow() {
                VOpenLog.d("banner", "onAdShow");
            }

            @Override
            public void onAdFailed(VivoAdError vivoAdError) {
                VOpenLog.d("banner", vivoAdError.getErrorCode() + " " + vivoAdError.getErrorMsg());
            }

            @Override
            public void onAdReady() {
                VOpenLog.d("banner", "onAdReady");
                if (mBannerContainer!=null) {
                    mBannerContainer.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAdClick() {
                VOpenLog.d("banner", "onAdClick");
            }

            @Override
            public void onAdClosed() {
                if (mVivoBanner != null) {
                    mVivoBanner.destroy();
                }

                if (mBannerContainer!=null) {
                    mBannerContainer.removeAllViews();
                }

                if (listener != null) {
                    listener.onClose();
                }
            }

        });


        mVivoBannerView = mVivoBanner.getAdView();
        VOpenLog.d("banner", (mVivoBannerView == null) ? "is null" : "is not null");
        if (mVivoBannerView != null) {

            RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            adParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            adParams.rightMargin = dip2px(activity, 80);
            mBannerContainer.addView(mVivoBannerView, adParams);
        }

    }

    private VivoNativeAd mVivoNativeAd;

    public void showBottomAd(final Activity activity, final boolean needClick) {
        if (mBottomContainer == null) {
            mBottomContainer = new RelativeLayout(activity);

            FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            param.gravity = Gravity.BOTTOM | Gravity.CENTER;
            param.bottomMargin = dip2px(activity, 10);
            activity.addContentView(mBottomContainer, param);

            View rootView = LayoutInflater.from(activity).inflate(R.layout.view_native_ad, null);
            mAppDownloadAdView = rootView.findViewById(R.id.app_layout);
            mWebSiteAdView = rootView.findViewById(R.id.website_ad_layout);

            RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(dip2px(activity, 720), RelativeLayout.LayoutParams.WRAP_CONTENT);
            adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            mBottomContainer.addView(rootView, adParams);
        }


        if (mVivoNativeAd == null) {
            NativeAdParams.Builder builder = new NativeAdParams.Builder(VivoConstans.NATIVE_POSITION_ID);

            mVivoNativeAd = new VivoNativeAd(activity, builder.build(), new NativeAdListener() {
                @Override
                public void onADLoaded(List<NativeResponse> list) {
                    if (list != null && !list.isEmpty()) {
                        showBottomAdView(activity, list);
                    }
                }

                @Override
                public void onNoAD(AdError adError) {

                }

                @Override
                public void onClick(NativeResponse nativeResponse) {

                }

                @Override
                public void onAdShow(NativeResponse nativeResponse) {

                }
            });

        }
        mNeedClick = needClick;
        mVivoNativeAd.loadAd();
    }

    private RelativeLayout mBottomContainer;
    private ViewGroup mAppDownloadAdView;
    private ViewGroup mWebSiteAdView;
    private boolean mNeedClick;

    private void removeListener(Object object) {

        try {
            Method m = getDeclareMethod(object, "getListenerInfo");
            m.setAccessible(true);
            Object li = m.invoke(object);

            Class lic = li.getClass();
            Field f = lic.getDeclaredField("mOnClickListener");
            f.setAccessible(true);
            f.set(li, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Method getDeclareMethod(Object object, String methodName) {
        for (Class v = object.getClass(); v != Object.class; v = v.getSuperclass()) {
            try {
                Method m = v.getDeclaredMethod(methodName);
                return m;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public interface OnNativeListener {
        void onNativeShown(NativeResponse response);

        void onNativeFailed(String msg);
    }

    public interface OnBannerListener {
        void onClose();
    }


    public void showNative(final Activity activity, final OnNativeListener listener) {
        NativeAdParams.Builder builder = new NativeAdParams.Builder(VivoConstans.NATIVE_POSITION_ID);
        new VivoNativeAd(activity, builder.build(), new NativeAdListener() {
            @Override
            public void onADLoaded(List<NativeResponse> list) {
                if (list == null || list.isEmpty()) {
                    if (listener != null) {
                        listener.onNativeFailed("native item is null");
                    }
                    return;
                }

                final NativeResponse adItem = list.get(0);
                if (adItem == null) {
                    if (listener != null) {
                        listener.onNativeFailed("native item is null");
                    }
                    return;
                }

                final RelativeLayout layout = new RelativeLayout(activity);
                final NativeDialogView view = new NativeDialogView(activity, false);
                layout.setBackgroundColor(0x77000000);
                RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT);
                rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
                layout.addView(view, rlp);
                layout.setPadding(30, 0, 30, 30);

                final AQuery aq = new AQuery(layout);

/*
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (checkAutoClick(v, adItem)) {
                            closeAdView(v);
                            aq.clear();
                        }
                    }
                });
                */

                view.getCloseLayout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        checkAutoClick(layout, adItem);
                        closeAdView(layout);
                        aq.clear();
                    }
                });

                if (!TextUtils.isEmpty(adItem.getAdMarkUrl())) {
                    aq.id(NativeDialogView.IMAGE_ID_LOGO).image(adItem.getAdMarkUrl()).getView().setVisibility(View.VISIBLE);
//                    aq.id(NativeDialogView.IMAGE_ID_LOGO).image(adItem.getAdMarkUrl());
                } else {
                    String adMark;
                    if (!TextUtils.isEmpty(adItem.getAdMarkText())) {
                        adMark = adItem.getAdMarkText();

//                    mAQuery.id(p).getView().setVisibility(View.GONE);
                    } else if (!TextUtils.isEmpty(adItem.getAdTag())) {
                        adMark = adItem.getAdTag();
                    } else {
                        adMark = "广告";
                    }
                    aq.id(NativeDialogView.IMAGE_ID_LOGO).text(adMark).getView().setVisibility(View.VISIBLE);
                }

                if (adItem.getAdType() == 1) {

                    // 网址类广告

                    aq.id(NativeDialogView.IMAGE_ID).image(adItem.getImgUrl().get(0), false, true);

                } else if (adItem.getAdType() == 2) {
                    aq.id(NativeDialogView.IMAGE_ID).image(TextUtils.isEmpty(adItem.getImgUrl().get(0)) ?
                                    adItem.getIconUrl() :
                                    adItem.getImgUrl().get(0),
                            false, true);

                }
                view.getTextView().setText(adItem.getDesc());
                adItem.registerView(new VivoNativeAdContainer(activity), null,view);

                if (listener != null) {
                    listener.onNativeShown(adItem);
                }
                activity.addContentView(layout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }

            @Override
            public void onNoAD(AdError adError) {
                if (listener != null) {
                    listener.onNativeFailed(adError.getErrorMsg()+" "+adError.getErrorCode());
                }

                Log.i(TAG,"native:"+adError.getErrorCode() + " " + adError.getErrorMsg());
                VOpenLog.d("inner", adError.getErrorCode() + " " + adError.getErrorMsg());
            }

            @Override
            public void onClick(NativeResponse nativeResponse) {

            }

            @Override
            public void onAdShow(NativeResponse nativeResponse) {

            }
        }).loadAd();

    }

    public static void closeAdView(View view) {
        ViewParent viewParent = view.getParent();
        if (viewParent != null && viewParent instanceof ViewGroup) {
            ((ViewGroup) viewParent).removeView(view);
        }
    }




    long mExpressTime;
    FrameLayout mNativeExpresContainer;
    UnifiedVivoNativeExpressAd mNativeExpressAd;
    private VivoNativeExpressView mNativeExpressView;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)

    public void loadNativeExpressAd(Activity activity){
        loadNativeExpressAd(activity,5000);
    }
    public void loadNativeExpressAd(Activity activity,int timeLimit){

        long time=System.currentTimeMillis();
        if(time-mExpressTime<timeLimit){
            return;
        }
        mExpressTime=time;

        boolean islandScreen=false;
        if(mNativeExpresContainer==null){
            mNativeExpresContainer=new FrameLayout(activity);
            int layout_height=islandScreen?FrameLayout.LayoutParams.MATCH_PARENT:DimenUtils.getScreenHeight(activity)-DimenUtils.getScreenHeight(activity)/4;
            FrameLayout.LayoutParams containerLp=new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,layout_height);
            mNativeExpresContainer.setLayoutParams(containerLp);
            containerLp.gravity=Gravity.BOTTOM;
            activity.addContentView(mNativeExpresContainer,containerLp);


        }



        if (mNativeExpressView != null) {

            if(mNativeExpressView.isAttachedToWindow()){
                return;
            }
            mNativeExpressView.destroy();
            mNativeExpresContainer.removeAllViews();
        }

        Utils.hideSoftInput(activity);
        int width = 300;
        int height = 0;
        AdParams.Builder builder = new AdParams.Builder(VivoConstans.NATIVE_EXPRESS_MATERIAL_ID);
        /**
         * 用户角度视频播放策略，取值可以参考{@link com.vivo.mobilead.unified.base.VideoPolicy}
         * VideoPolicy.MANUAL           WIFI、流量环境下始终需要用户手动点击开始播放
         * VideoPolicy.AUTO_AWAYS       WIFI、流量环境下始终自动开始播放
         * VideoPolicy.MANUAL           WIFI环境下自动开始播放,流量环境下需要用户手动点击开始播放
         */
        builder.setVideoPolicy(VideoPolicy.AUTO_AWAYS);

        /**
         * 如何设置广告的尺寸：
         * 方法一：
         * 接入阶段可以通过setNativeExpressWidth、setNativeExpressHegiht方法设置最终生成的模板视图尺寸，根据实际广告展示场景找到适合尺寸,
         * 发布时在构造UnifiedVivoNativeExpressAd的时候传入预定尺寸 和 固定广告位id 进行展示
         *
         * 方法二：
         * 根据实际广告展示场景需要，设置一个固定宽度，高度不设置让广告的高度根据宽度去自适应。
         *
         * 小于0的值都会被SDK忽略而不生效，没有设置视图尺寸时将使用模板的默认尺寸进行渲染。
         * 一旦同时设置了视图尺寸，SDK会按实际设置大小进行渲染，如果设置尺寸比例、大小不合适，会出现视图被裁减的情况。
         */
//        if (!autoWidth && width > 0) {
//            builder.setNativeExpressWidth(width);
//        }
//
//        if (!autoHeight && height > 0) {
//            builder.setNativeExpressHegiht(height);
//        }

        //builder.setNativeExpressHegiht(DimenUtils.getScreenWidth(activity));
        builder.setNativeExpressWidth(DimenUtils.getScreenWidth(activity)-50);

        mNativeExpressAd = new UnifiedVivoNativeExpressAd(activity, builder.build(), new UnifiedVivoNativeExpressAdListener() {
            @Override
            public void onAdReady(VivoNativeExpressView vivoNativeExpressView) {


                if (vivoNativeExpressView != null) {

                    mNativeExpressView = vivoNativeExpressView;
                    mNativeExpressView.setMediaListener(mediaListener);
                    mNativeExpresContainer.removeAllViews();
                    mNativeExpresContainer.setVisibility(View.VISIBLE);
                    FrameLayout.LayoutParams lp=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    lp.gravity=Gravity.CENTER;
                    mNativeExpresContainer.addView(vivoNativeExpressView, lp);

                }
            }

            @Override
            public void onAdFailed(com.vivo.mobilead.unified.base.VivoAdError error) {

                Log.i(TAG, "onAdFailed................"+error.getMsg()+" code"+error.getCode());

            }

            @Override
            public void onAdClick(VivoNativeExpressView view) {
                Log.i(TAG, "onAdClick................");


            }

            @Override
            public void onAdShow(VivoNativeExpressView view) {
                Log.i(TAG, "onAdShow................");

            }



            @Override
            public void onAdClose(VivoNativeExpressView vivoNativeExpressView) {
                Log.i(TAG, "onAdClose................");
                mNativeExpresContainer.removeAllViews();
                mNativeExpresContainer.setVisibility(View.INVISIBLE);
            }
        });
        mNativeExpressAd.loadAd();


    }


    public void closeNativeExpressAd(){
        if(mNativeExpresContainer!=null){
            mNativeExpresContainer.removeAllViews();
            mNativeExpresContainer.setVisibility(View.INVISIBLE);
        }
    }

    private void showBottomAdView(Activity activity, List<NativeResponse> list) {

        boolean needClick = mNeedClick;
        final NativeResponse data = list.get(0);
        if (data != null) {
            if (needClick) {

            } else {
                removeListener(mBannerContainer);
                mBottomContainer.setClickable(false);
//                mBottomContainer.setOnClickListener(null);
            }

            if (data.getAdType() == 1) { // 网址类广告
                mWebSiteAdView.setVisibility(View.VISIBLE);
                mAppDownloadAdView.setVisibility(View.GONE);
                mBottomContainer.setVisibility(View.VISIBLE);

                TextView title = mWebSiteAdView.findViewById(R.id.text_name);
                title.setText(data.getTitle());

                TextView desc = mWebSiteAdView.findViewById(R.id.text_desc);
                desc.setText(data.getDesc());

                ImageView logo = mWebSiteAdView.findViewById(R.id.img_logo);
                Glide.with(activity).load(data.getIconUrl()).into(logo);

                ImageView poster = mWebSiteAdView.findViewById(R.id.img_poster);
                Glide.with(activity).load(data.getImgUrl()).into(poster);

                data.registerView(new VivoNativeAdContainer(activity),null,mBottomContainer);


                /*
                mWebSiteAdView.findViewById(R.id.website_ad_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        data.onClicked(view, mRawX, mRawY);
                    }
                });
                */


                mWebSiteAdView.findViewById(R.id.web_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mWebSiteAdView.setVisibility(View.GONE);
                        mBottomContainer.setVisibility(View.GONE);
                    }
                });
            } else if (data.getAdType() == 2) { // 应用下载类广告
                mAppDownloadAdView.setVisibility(View.VISIBLE);
                mWebSiteAdView.setVisibility(View.GONE);
                mBottomContainer.setVisibility(View.VISIBLE);

                TextView title = mAppDownloadAdView.findViewById(R.id.app_title_view);
                title.setText(data.getTitle());

                TextView desc = mAppDownloadAdView.findViewById(R.id.app_desc_view);
                desc.setText(data.getDesc());

                ImageView icon = mAppDownloadAdView.findViewById(R.id.app_icon_view);
                Glide.with(activity).load(data.getIconUrl()).into(icon);

                Button installBtn = mAppDownloadAdView.findViewById(R.id.install_btn);
                Drawable installBtnRes = activity.getResources().getDrawable(R.drawable.bg_detail_btn);
                switch (data.getAPPStatus()) {
                    case 0:
                        // 未安装
                        installBtnRes = activity.getResources().getDrawable(R.drawable.bg_install_btn);
                        break;
                    case 1:
                        // 已安装
                        installBtnRes = activity.getResources().getDrawable(R.drawable.bg_detail_btn);
                        break;
                    default:
                        break;
                }
                installBtn.setBackgroundDrawable(installBtnRes);

                data.registerView( new VivoNativeAdContainer(activity),null,mBottomContainer,mAppDownloadAdView.findViewById(R.id.install_btn));

                /*
                mAppDownloadAdView.findViewById(R.id.install_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        data.onClicked(view, mRawX, mRawY);
                    }
                });

                 */
                mAppDownloadAdView.findViewById(R.id.app_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mAppDownloadAdView.setVisibility(View.GONE);
                        mBottomContainer.setVisibility(View.GONE);
                    }
                });
            }
        }
    }







    private static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public void onDestroy() {
        if (mVivoBanner != null) {
            mVivoBanner.destroy();
        }
    }


    View mJumpperView;
    public void initContact(Activity activity){



        if (mJumpperView == null) {
            mJumpperView = LayoutInflater.from(activity).inflate(R.layout.layout_bottom, null);

            RelativeLayout parent = new RelativeLayout(activity);
            RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, dip2px(activity, 40));
            adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //adParams.rightMargin = dip2px(activity, 80);
            parent.addView(mJumpperView, adParams);

            FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            param.bottomMargin = dip2px(activity, 5);
            activity.addContentView(parent, param);
//            mJumpperView.findViewById(R.id.iv_go_oppo).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    GameCenterSDK.getInstance().jumpLeisureSubject();
//                }
//            });

//            mJumpperView.findViewById(R.id.btn_goto_exit).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.e("exit","oppo_game_exit");
//                    AdManager.getInstance().exitApp(mActivity);
//
//                }
//            });

            mJumpperView.findViewById(R.id.btn_privacy).setClickable(true);
            mJumpperView.findViewById(R.id.btn_privacy).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    PrivacyDialog dialog = new PrivacyDialog(activity, "隐私政策");
//                    dialog.setCallback(new PrivacyDialog.ProtocolDialogCallback() {
//                        @Override
//                        public void onOk() {
//                        }
//                        @Override
//                        public void onCancel() {
//                            exitApp(activity);
//                        }
//                    });
//                    dialog.show();
//                    dialog.setCancelButtonText("退出游戏");
                    showProtocol(activity);
                }
            });

            mJumpperView.findViewById(R.id.tv_contact).setClickable(true);
            mJumpperView.findViewById(R.id.tv_contact).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showContactDialog();
                }
            });
        }
        hideContact();
    }


    public void showContactDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        //2.设置属性 icon图标 title标题 message内容

        builder.setTitle("");
        builder.setMessage(VivoConstans.EMAIL);
        //设置按钮 PositiveButton确定按钮（参数:"显示内容"，点击监听）
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            }
        });

        //3.使用建造者创建对话框
        AlertDialog dialog = builder.create();
        //4.显示
        dialog.show();

    }


//    public void showPrivacy(Activity activity){
//        PrivacyDialog dialog = new PrivacyDialog(activity, "隐私政策");
//        dialog.setCallback(new PrivacyDialog.ProtocolDialogCallback() {
//            @Override
//            public void onOk() {
//            }
//            @Override
//            public void onCancel() {
//                exitApp(activity);
//            }
//        });
//        dialog.show();
//        dialog.setCancelButtonText("退出游戏");
//    }

    public  void showProtocol(Activity activity) {
        com.igame.protocol.ProtocolDialog dialog = new com.igame.protocol.ProtocolDialog(activity, "隐私政策",activity.getString(R.string.protocol_content));
        dialog.setOnlyOk(true);
        dialog.setOkStr("返回");
        dialog.show();
    }



    public void exitApp(Activity activity){
        VivoUnionSDK.exit(activity, new VivoExitCallback() {
            @Override
            public void onExitCancel() {

            }

            @Override
            public void onExitConfirm() {
                activity.finish();
                android.os.Process.killProcess(android.os.Process.myPid());   //获取PID
                System.exit(0);

            }
        });
    }



    public void hideContact(){
        if(mJumpperView!=null){
            mJumpperView.setVisibility(View.INVISIBLE);
        }
    }

    public void showContact(){
        if(mJumpperView!=null){
            mJumpperView.setVisibility(View.VISIBLE);
        }
    }


}
