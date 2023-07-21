package com.igame;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.glgame.wjfjzc.huawei.R;
import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.InterstitialAd;
import com.huawei.hms.ads.VideoConfiguration;
import com.huawei.hms.ads.banner.BannerView;
import com.huawei.hms.ads.nativead.DislikeAdListener;
import com.huawei.hms.ads.nativead.NativeAd;
import com.huawei.hms.ads.nativead.NativeAdConfiguration;
import com.huawei.hms.ads.nativead.NativeAdLoader;
import com.huawei.hms.ads.reward.RewardAd;
import com.huawei.hms.ads.reward.RewardAdLoadListener;
import com.huawei.hms.ads.reward.RewardAdStatusListener;

public class Comm {


    static boolean isRelease=false;
    static Comm comm;

    public static  Comm getInstance(){
        if(comm==null){
            comm=new Comm();
        }
        return comm;
    }

    public void exitApp(){
        try {
            if(mActivity!=null) {
                mActivity.finish();
            }

            System.exit(0);
        }catch (Throwable ex){

        }
    }


    Handler mHandler;
    public void init(Activity activity){
        this.mActivity=activity;mHandler=new Handler(Looper.getMainLooper());
    }

////测试 id
//    public static boolean isReleaseAd=false;
//    public static String AD_BANNER_KEY = "testw6vs28auh3";
//    public static String AD_NATIVE_AD = "testy63txaom86";
//    public static String AD_REDWARD_AD = "testx9dtjwj8hp";
//    public static String AD_SPLASH_AD = "testq6zq98hecj";
//    public static String AD_INSERT_AD="testb4znbuh3n2";
    public static boolean isReleaseAd=true;
    public static String AD_BANNER_KEY = "h8o7k7atm9";
    public static String AD_NATIVE_AD = "q8gia2tcqs";
    public static String AD_REDWARD_AD = "o48huffvvp";
    public static String AD_SPLASH_AD = "b492n31lyv";
    public static String AD_INSERT_AD="f2owowqkzw";  //不用


    BannerView mBannerView;
    FrameLayout mBannerAdView;
    Activity mActivity;
    RelativeLayout mBannerContainer;
    public void showBanner(Activity activity){

        mActivity=activity;

        if (mBannerAdView == null) {
            mBannerAdView = (FrameLayout) LayoutInflater.from(mActivity).inflate(R.layout.layout_bottom, null);

            RelativeLayout parent = new RelativeLayout(mActivity);
            mBannerContainer = parent;
            RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

            //adParams.rightMargin = dip2px(activity, 80);
            parent.addView(mBannerAdView, adParams);

            //ViewGroup.LayoutParams.MATCH_PARENT
            //ViewGroup.LayoutParams.WRAP_CONTENT

            boolean isLand=false;

            FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(DimenUtils.dip2px(activity,320), DimenUtils.dip2px(activity,50));
            if(!isLand){
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DimenUtils.dip2px(activity,90));
            }

            //param.bottomMargin = dip2px(activity, 10);

            boolean isBottom=true;
            if(isBottom) {
                param.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
            }else{
                param.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
            }
            mActivity.addContentView(parent, param);
        }


        if(mBannerView==null) {
            mBannerView = new BannerView(activity);

            // Set an ad slot ID.
            mBannerView.setAdId(AD_BANNER_KEY);

            // Set the background color and size based on user selection.
            BannerAdSize adSize = BannerAdSize.BANNER_SIZE_360_57;
            mBannerView.setBannerAdSize(adSize);


            int color = Color.TRANSPARENT;//getBannerViewBackground(colorRadioGroup.getCheckedRadioButtonId());
            mBannerView.setBackgroundColor(color);
            //FrameLayout.LayoutParams framelayoutparam = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

//            framelayoutparam.gravity=Gravity.CENTER|Gravity.TOP;
//            framelayoutparam.bottomMargin=DimenUtils.dip2px(mActivity,50);
            mBannerView.setAdListener(mBannerAdListener);
            mBannerView.setBannerRefresh(30);
        }

        mBannerAdView.removeAllViews();
        FrameLayout.LayoutParams framelayoutparam = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DimenUtils.dip2px(activity, 90));
        mBannerAdView.addView(mBannerView, framelayoutparam);

        mBannerView.loadAd(new AdParam.Builder().build());
    }


    public void hideBanner(){

        if(mBannerAdView!=null) {
            mBannerAdView.removeAllViews();
        }
    }

    public void reloadBanner(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //enableBanner();
                showBanner(mActivity);
            }
        },40*1000);

    }


    private AdListener mBannerAdListener = new AdListener() {
        @Override
        public void onAdLoaded() {
            // Called when an ad is loaded successfully.
            //showToast("Ad loaded.");
        }

        @Override
        public void onAdFailed(int errorCode) {
            // Called when an ad fails to be loaded.
            //showToast(String.format(Locale.ROOT, "Ad failed to load with error code %d.", errorCode));
        }

        @Override
        public void onAdOpened() {
            // Called when an ad is opened.
            //showToast(String.format("Ad opened "));
        }

        @Override
        public void onAdClicked() {
            // Called when a user taps an ad.
            //showToast("Ad clicked");
            hideBanner();
        }

        @Override
        public void onAdLeave() {
            // Called when a user has left the app.
            //showToast("Ad Leave");
        }

        @Override
        public void onAdClosed() {
            // Called when an ad is closed.
            //showToast("Ad closed");
        }
    };

    public void showToast(String str){
        Toast.makeText(mActivity,str,Toast.LENGTH_SHORT).show();
    }


//    private BannerAdSize getBannerAdSize(int checkedId) {
//        BannerAdSize adSize = null;
//        switch (checkedId) {
//            case R.id.size_320_50:
//                adSize = BannerAdSize.BANNER_SIZE_320_50;
//                break;
//            case R.id.size_320_100:
//                adSize = BannerAdSize.BANNER_SIZE_320_100;
//                break;
//            case R.id.size_300_250:
//                adSize = BannerAdSize.BANNER_SIZE_300_250;
//                break;
//            case R.id.size_smart:
//                adSize = BannerAdSize.BANNER_SIZE_SMART;
//                break;
//            case R.id.size_360_57:
//                adSize = BannerAdSize.BANNER_SIZE_360_57;
//                break;
//            case R.id.size_360_144:
//                adSize = BannerAdSize.BANNER_SIZE_360_144;
//                break;
//            default:
//                break;
//        }
//        return adSize;
//    }

    private RewardAd mRewardedAd;
    RewardAdStatusListener mRewardAdStatusListener;

    boolean isLoading=false;
    long mRewardAdLastTime=0;
    public void showReward(Activity activity,RewardAdStatusListener listener){

        long currTime=System.currentTimeMillis();
        long diff=currTime-mRewardAdLastTime;
        if(diff<2*1000) {
            showToast("广告加载中，请稍候");
            return;
        }
        mRewardAdLastTime=currTime;



        if(isLoading==true){
            showToast("广告加载中，请稍候");
            return;
        }
        isLoading=true;
        if(mRewardAdStatusListener==null){
            mRewardAdStatusListener=listener;
        }
        mActivity=activity;
        if (mRewardedAd == null) {
            mRewardedAd = new RewardAd(mActivity, AD_REDWARD_AD);
        }


        RewardAdLoadListener rewardAdLoadListener = new RewardAdLoadListener() {
            @Override
            public void onRewardAdFailedToLoad(int errorCode) {
                //showToast("onRewardAdFailedToLoad " + "errorCode is :" + errorCode);
                showToast("广告加载失败,请稍候再试");
                isLoading=false;
            }

            @Override
            public void onRewardedLoaded() {
                //showToast("onRewardedLoaded");
                isLoading=false;
                rewardShowInternal(activity);
            }
        };

        mRewardedAd.loadAd(new AdParam.Builder().build(), rewardAdLoadListener);
    }


    public void rewardShowInternal(Activity activity){
        if (mRewardedAd.isLoaded()) {
            mRewardedAd.show(activity,mRewardAdStatusListener);
//            mRewardedAd.show(activity, new RewardAdStatusListener() {
//                @Override
//                public void onRewardAdClosed() {
//                    showToast("onRewardAdClosed");
//
//                    //loadRewardAd();
//                }
//
//                @Override
//                public void onRewardAdFailedToShow(int errorCode) {
//                    showToast("onRewardAdFailedToShow " + "errorCode is :" + errorCode);
//                }
//
//                @Override
//                public void onRewardAdOpened() {
//                    showToast("onRewardAdOpened");
//                }
//
//                @Override
//                public void onRewarded(Reward reward) {
//                    // You are advised to grant a reward immediately and at the same time, check whether the reward
//                    // takes effect on the server. If no reward information is configured, grant a reward based on the
//                    // actual scenario.
//
//                    // loadRewardAd();
//                    showToast("onReward");
//                }
//            });
        }
    }



    long mNativeAdLastTime;
    int mNativeAddFail=0;
    public void loadAd(Activity activity) {

        long currTime=System.currentTimeMillis();
        long diff=currTime-mNativeAdLastTime;
        if(diff<2*1000) {
            return;
        }

        if(mNativeAdView!=null&&mNativeAdView.isAttachedToWindow()){
            return;
        }

        mActivity=activity;
        NativeAdLoader.Builder builder = new NativeAdLoader.Builder(mActivity, Comm.AD_NATIVE_AD);
        builder.setNativeAdLoadedListener(new NativeAd.NativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(NativeAd nativeAd) {
                // Call this method when an ad is successfully loaded.


                // Display native ad.
                showNativeAd(nativeAd);
                Log.e("hwad","onNativeAdLoaded");
            }
        }).setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
              Log.e("hwad","adonload");
            }

            @Override
            public void onAdFailed(int errorCode) {
                // Call this method when an ad fails to be loaded.
                Log.e("hwad","native onAdFailed :"+errorCode);
                //loadInterstitialAd(mActivity);
                mNativeAddFail++;
                if(mNativeAddFail%3==0) {
                    Log.e("hwad","native onAdFailed count "+mNativeAddFail);
                    ((BaseGameActivity) mActivity).initAD("reward_null");
                }
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                if(mNativelayout.getParent()!=null) {
                    ((ViewGroup) mNativelayout.getParent()).removeView(mNativelayout);
                }
            }
        });

        VideoConfiguration videoConfiguration = new VideoConfiguration.Builder()
                .setStartMuted(true)
                .build();

        NativeAdConfiguration adConfiguration = new NativeAdConfiguration.Builder()
                .setChoicesPosition(NativeAdConfiguration.ChoicesPosition.BOTTOM_RIGHT) // Set custom attributes.
                .setVideoConfiguration(videoConfiguration)
                .setRequestMultiImages(true)
                .build();

        NativeAdLoader nativeAdLoader = builder.setNativeAdOptions(adConfiguration).build();
        nativeAdLoader.loadAd(new AdParam.Builder().build());


    }

    int btnindex=0;

    public void removeNativeAd(){
        try {
            if (mNativelayout != null && mNativelayout.getParent() != null) {
                ((ViewGroup) mNativelayout.getParent()).removeView(mNativelayout);
            }
        }catch (Exception ex){

        }
    }


    private NativeAd globalNativeAd;
    private void showNativeAd(NativeAd nativeAd) {

        if (mNativelayout == null)
        {
            RelativeLayout layout = new RelativeLayout(mActivity);
            mNativelayout = layout;
        }

        if(mNativelayout.getParent()!=null) {
            ((ViewGroup) mNativelayout.getParent()).removeView(mNativelayout);
        }

        // Destroy the original native ad.
        if (null != globalNativeAd) {
            globalNativeAd.destroy();
        }
        globalNativeAd = nativeAd;

        final View nativeView = createNativeView(nativeAd, mNativelayout);
        if (nativeView != null) {
            globalNativeAd.setDislikeAdListener(new DislikeAdListener() {
                @Override
                public void onAdDisliked() {
                    // Call this method when an ad is closed.
                    //updateStatus(getString(R.string.ad_is_closed), true);
                    //adScrollView.removeView(nativeView);
                    mNativelayout.removeAllViews();
                    Log.e("hwad","onAdDisliked");
                }
            });

            showNativeInternal(nativeView);
            // Add NativeView to the app UI.
            //adScrollView.removeAllViews();
            //adScrollView.addView(nativeView);
        }
    }

    RelativeLayout mNativelayout;
    View mNativeAdView;
    public void  showNativeInternal(View adview) {

        mNativelayout.removeAllViews();
        mNativelayout.setBackgroundColor(0x77000000);

        int width=DimenUtils.getScreenHeight(mActivity);

        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(DimenUtils.dip2px(mActivity,350)
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
        mNativelayout.addView(adview, rlp);
        //mNativelayout.setPadding(10, 0, 10, 10);

        mNativeAdView=adview;


        View view=adview.findViewById(R.id.btn_ad_close);

        btnindex++;
        if(btnindex%3==0){

//            if(!isRelease){
//                ViewGroup.LayoutParams p = view.getLayoutParams();
//                p.height = DimenUtils.dip2px(mActivity, 15);
//                p.width = DimenUtils.dip2px(mActivity, 15);
//                view.setLayoutParams(p);
//            }

        }else {
            ViewGroup.LayoutParams p = view.getLayoutParams();
            p.height = DimenUtils.dip2px(mActivity, 15);
            p.width = DimenUtils.dip2px(mActivity, 15);
            view.setLayoutParams(p);
        }
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mNativelayout.getParent()!=null) {
                    ((ViewGroup) mNativelayout.getParent()).removeView(mNativelayout);
                }
            }
        });

        FrameLayout.LayoutParams params=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        params.leftMargin=DimenUtils.dip2px(mActivity,80);
//        params.rightMargin=DimenUtils.dip2px(mActivity,80);
        mActivity.addContentView(mNativelayout, params);
    }


    private void initCloseIcon(RelativeLayout parent)
    {
        int IMAGE_ID=1111;
        RelativeLayout closeLayout = new RelativeLayout(mActivity);
        ImageView closeIcon = new ImageView(mActivity);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(DimenUtils.dip2px(mActivity,40)
                , DimenUtils.dip2px(mActivity, 40 ));

        lp.addRule(RelativeLayout.ALIGN_RIGHT, IMAGE_ID);
        lp.addRule(RelativeLayout.ALIGN_TOP, IMAGE_ID);
        closeLayout.setLayoutParams(lp);
//        closeLayout.setBackgroundColor(0xff000000);
//        lp.rightMargin = DimenUtils.dip2px(context,5);
//        lp.topMargin = DimenUtils.dip2px(context,5);

        RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(DimenUtils.dip2px(mActivity,15)
                , DimenUtils.dip2px(mActivity,15));
        closeIcon.setLayoutParams(llp);
        llp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        llp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//        llp.rightMargin = DimenUtils.dip2px(context,5);
//        llp.topMargin = DimenUtils.dip2px(context,5);
//        closeIcon.setBackgroundColor(0xff000000);
        closeIcon.setImageDrawable(mActivity.getResources().getDrawable(android.R.drawable.ic_menu_close_clear_cancel));
//        closeIcon.setBackgroundDrawable(AssetsUtil.getInstance(context).getDrawable("btn_back_hemeng_sdk.png"));
        //17301560
        closeLayout.addView(closeIcon);
        closeIcon.setClickable(true);
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNativelayout.removeAllViews();
            }
        });
        parent.addView(closeLayout);


    }



    private View createNativeView(NativeAd nativeAd, ViewGroup parentView) {
        int createType = nativeAd.getCreativeType();
        Log.i("TAG", "Native ad createType is " + createType);
        if (createType == 2 || createType == 102) {
            // Large image
            return NativeViewFactory.createImageOnlyAdView(nativeAd, parentView);
        } else if (createType == 3 || createType == 6) {
            // Large image with text or video with text
            return NativeViewFactory.createMediumAdView(nativeAd, parentView);
        } else if (createType == 103 || createType == 106) {
            // Large image with text or Video with text, using AppDownloadButton template.
            return NativeViewFactory.createAppDownloadButtonAdView(nativeAd, parentView);
        } else if (createType == 7 || createType == 107) {
            // Small image with text-
            return NativeViewFactory.createSmallImageAdView(nativeAd, parentView);
        } else if (createType == 8 || createType == 108) {
            // Three small images with text
            return NativeViewFactory.createThreeImagesAdView(nativeAd, parentView);
        } else {
            // Undefined creative type
            return null;
        }
    }


    long mInsertLastTime=0;
    private InterstitialAd interstitialAd;
    public void loadInterstitialAd(Activity activity) {

        Log.i("hwad","loadInterstitialAd enter");
        long currTime=System.currentTimeMillis();

        long diff=currTime-mInsertLastTime;

        if(interstitialAd!=null&&interstitialAd.isLoading()){
            Log.i("hwad","interstitialAd isloading");
            return;
        }

        if(diff<4*1000) {
            Log.i("hwad","interstitialAd time quick");
            return;
        }

        mInsertLastTime=currTime;

        try {
            if (interstitialAd == null) {
                interstitialAd = new InterstitialAd(activity);
                interstitialAd.setAdId(Comm.AD_INSERT_AD);


                interstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        Log.i("hwad", "Ad show");

                        // Display an interstitial ad.
                        showInterstitial(activity);
                    }

                    @Override
                    public void onAdFailed(int errorCode) {
                        //showToast("Ad load failed with error code: " + errorCode);
                        Log.i("hwad", "Ad load failed with error code: " + errorCode);
                        //AdParam.ErrorCode.
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                        //showToast("Ad closed");
                        //Log.d(TAG, "onAdClosed");
                    }

                    @Override
                    public void onAdClicked() {
                        //Log.d(TAG, "onAdClicked");
                        super.onAdClicked();
                    }

                    @Override
                    public void onAdOpened() {
                        //Log.d(TAG, "onAdOpened");
                        super.onAdOpened();
                    }
                });
            }

            AdParam adParam = new AdParam.Builder().build();

            interstitialAd.loadAd(adParam);
        }catch (Exception ex){
            Log.e("ex","ex",ex);
        }
    }




    private void showInterstitial(Activity activity) {
        // Display an interstitial ad.
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show(activity);

        } else {
            //showToast("Ad did not load");
        }
    }




}
