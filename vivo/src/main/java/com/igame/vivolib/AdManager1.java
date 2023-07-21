package com.igame.vivolib;

//import com.vivo.mobilead.banner.BannerAdParams;


public class AdManager1 {

//    public static final int AD_CASE_STAGE_COMPLETE = 1;
//    public static final int AD_CASE_STAGE_FAILED = 2;
//    public static final int AD_CASE_PAY_FOR_MONEY_FAILED = 3;
//    public static final int AD_CASE_PAY_FOR_GEM_FAILED =4;
//    public static final int AD_CASE_SETTING = 5;
//    public static final int AD_CASE_DAY_LOGIN = 6;
//    public static final int AD_CASE_PAUSE = 7;
//    public static final int AD_CASE_3_MINITUE = 8;
//    public static final int AD_CASE_CHOOSE_STAGE = 9;
//    public static final int AD_CASE_BANNER = 10;
//    public static final int AD_CASE_SPLASH = 11;
//    public static final int AD_REVERSE_CARD = 12;
//
//    int completeTimes;
//    private Activity activity;
//    private Handler handler = new Handler(Looper.getMainLooper());
//    static AdManager sInstance;
//    public static AdManager getInstance(Activity activity){
//        if (sInstance == null){
//            sInstance = new AdManager(activity);
//        }
//        return sInstance;
//    }
//    public AdManager(Activity activity){
//        this.activity = activity;
//        handler.postDelayed(repeatShowAd,1 * 60 * 1000);
//    }
//    View adView;
//    VivoBannerAd mVivoBanner;
//    long lastAdTime;
//    private Runnable repeatShowAd = new Runnable() {
//        @Override
//        public void run() {
//            if (decode.getNumber() == 0 )return;
//            VivoAdUtil.showNative(activity, Constans.NATIVE_POSITION_ID, new VivoAdUtil.OnAdListener() {
//                @Override
//                public void onNativeShown(NativeResponse response) {
//                    handler.postDelayed(repeatShowAd,60*1000);
//                }
//
//                @Override
//                public void onNativeFailed(String msg) {
//
//                }
//
//                @Override
//                public void onVideoPlayComplete() {
//
//                }
//            } ,checkAdShow(decode.getNumber(),AD_CASE_3_MINITUE).checkAutoF);
//        }
//    };
//
////    public void showBanner(final ViewGroup mContainer) {
////
////        if(adView!=null){
////            //if(adView.getParent()!=null){
////            mContainer.removeView(adView);
////            adView=null;
////            //}
////        }
////        if (mVivoBanner != null) {
////            mVivoBanner.destroy();
////        }
////        /**
////         * 请求Banner广告。
////         */
//////        BannerAdParams.Builder builder = new BannerAdParams.Builder(Constans.VIVO_BANNER_ID);
////        mVivoBanner = new VivoBannerAd(activity,Constans.VIVO_BANNER_ID, new IAdListener() {
////            @Override
////            public void onAdShow() {
//////                try {
//////                    ViewGroup.LayoutParams lp = mVivoBanner.getAdView().getLayoutParams();
//////                    Log.e("ADMANAGER","adview gravity" + mVivoBanner.getAdView().getLayoutParams());
//////                    if (lp instanceof FrameLayout.LayoutParams){
//////                        FrameLayout.LayoutParams  flp = (FrameLayout.LayoutParams) lp;
//////                        if ((flp.gravity & Gravity.CENTER_HORIZONTAL )== Gravity.CENTER_HORIZONTAL){
//////                            flp.gravity &= ~Gravity.CENTER_HORIZONTAL;
//////
//////                        };
//////                        flp.gravity |= Gravity.LEFT;
//////                        mVivoBanner.getAdView().setLayoutParams(flp);
//////                        mVivoBanner.getAdView().requestLayout();
//////
//////                    }
//////                }catch (Exception e){
//////
//////                }
////
////            }
////
////            @Override
////            public void onAdFailed(VivoAdError vivoAdError) {
////                handler.postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        showBanner(mContainer);
////                    }
////                },30*1000);
////            }
////
////            @Override
////            public void onAdReady() {
////
////            }
////
////            @Override
////            public void onAdClick() {
////
////            }
////
////
////            @Override
////            public void onAdClosed() {
////                long delay = 30*1000;
////                if (decode.getNumber() == 0){
////                    delay = 5 * 60 * 1000;
////                }
////                handler.postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        showBanner(mContainer);
////                    }
////                },delay);
////            }
////        });
////
////        /**
////         * 设置Banner显示关闭按钮。
////         */
////        mVivoBanner.setShowClose(true);
////        /**
////         * 设置Banner刷新频率。
////         */
////        mVivoBanner.setRefresh(30);
////        /**
////         * 获取Banner广告View。
////         */
////
////        adView = mVivoBanner.getAdView();
////        /**
////         * 注意：只有adView不等于null时，才能把View添加到你的布局中显示。
////         * 创建完广告必须调用addView添加广告视图，不然会导致曝光率低。
////         */
////        if (null != adView) {
////            /**
////             * 创建广告后必须调用改方法
////             */
////
////            FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
////            param.gravity = Gravity.TOP | Gravity.LEFT;
////            adView.setLayoutParams(param);
////            mContainer.addView(adView);
////        }
////    }
//
//
//    public void checkComplete(int decode,int time){
//       M m = checkAdShow(decode,AD_CASE_STAGE_COMPLETE);
//        if (completeTimes == 0){
//            completeTimes +=1;
//        }else{
//            completeTimes = 0;
//            VivoAdUtil.showNative(activity, Constans.NATIVE_POSITION_ID, new VivoAdUtil.OnAdListener() {
//                @Override
//                public void onNativeShown(NativeResponse response) {
//
//                }
//
//                @Override
//                public void onNativeFailed(String msg) {
//
//                }
//
//                @Override
//                public void onVideoPlayComplete() {
//
//                }
//            },m.checkAutoF);
//        }
//
//    }
//    private M checkAdShow(int decode,int adCase){
//        Log.e("AD_MANAGER","decode : "+decode);
//        M m = new M();
//        if (decode == 0){
//            switch (adCase){
//                case AD_CASE_STAGE_COMPLETE :
//                case AD_CASE_DAY_LOGIN:
//                case AD_CASE_CHOOSE_STAGE:
//                case AD_REVERSE_CARD :
//                    m.checkShowF = 0.0f;
//                    break;
//
//            }
//        }
//        if (decode == 1){
//            switch (adCase){
//                case AD_CASE_STAGE_COMPLETE :
//                case AD_CASE_SETTING:
//                case AD_CASE_DAY_LOGIN:
//                    m.checkShowF = 0.3f;
//                    break;
//                case AD_CASE_CHOOSE_STAGE:
//                    m.checkShowF = 0.2f;
//                    break;
//                case AD_REVERSE_CARD :
//                    m.checkShowF = 0.0f;
//                    break;
//
//            }
//
//        }
//        if (decode == 2){
//            m.checkAutoF = 0.25f;
//            switch (adCase){
//                case AD_CASE_STAGE_COMPLETE :
//                case AD_CASE_SETTING:
//                case AD_CASE_DAY_LOGIN:
//                    m.checkShowF = 0.7f;
//                    break;
//                case AD_CASE_CHOOSE_STAGE:
//                    m.checkShowF = 0.4f;
//                    break;
//                case AD_REVERSE_CARD :
//                    m.checkShowF = 0.7f;
//                    break;
//            }
//
//
//        }
//        if (decode == 3){
//            m.checkAutoF = 0.25f;
//            switch (adCase){
//                case AD_CASE_STAGE_COMPLETE :
//                case AD_CASE_SETTING:
//                case AD_CASE_DAY_LOGIN:
//                    m.checkShowF = 0.7f;
//                    break;
//                case AD_CASE_CHOOSE_STAGE:
//                    m.checkShowF = 0.4f;
//                    break;
//                case AD_REVERSE_CARD :
//                    m.checkShowF = 1.0f;
//                    break;
//            }
//
//        }
//        return m;
//    }
//    public void showNative(final int adCase){
//        showNative(adCase,null);
//    }
//    public void showNative(final int adCase,final VivoAdUtil.OnAdListener listener){
//        final M m = checkAdShow(decode.getNumber(),adCase);
//        float f = new Random().nextFloat();
//        Log.e("AD_MANAGER","show ad ? " + decode.getNumber() + " " + f + " " + m.checkShowF + " " + m.checkAutoF);
//        if (f <= m.checkShowF){
//            VivoAdUtil.showNative(activity, Constans.NATIVE_POSITION_ID, new VivoAdUtil.OnAdListener() {
//                @Override
//                public void onNativeShown(NativeResponse response) {
//                    handler.removeCallbacks(repeatShowAd);
//                    handler.postDelayed(repeatShowAd,3 * 60 * 1000);
//                    if (listener != null){
//                        listener.onNativeShown(response);
//                    }
//                }
//
//                @Override
//                public void onNativeFailed(String msg) {
//                    if (listener != null){
//                        listener.onNativeFailed(msg);
//                    }
//
//                }
//
//                @Override
//                public void onVideoPlayComplete() {
//
//                }
//            }, m.checkAutoF);
//        }
//
//    }
//    class M {
//        public float checkAutoF = 0.0f;
//        public float checkShowF = 1.0f;
//    }


}
