package com.unity3d.start;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.bumptech.glide.Glide;
import com.heytap.msp.mobad.api.ad.BannerAd;
import com.heytap.msp.mobad.api.ad.InterstitialAd;
import com.heytap.msp.mobad.api.ad.InterstitialVideoAd;
import com.heytap.msp.mobad.api.ad.NativeAd;
import com.heytap.msp.mobad.api.ad.RewardVideoAd;
import com.heytap.msp.mobad.api.listener.IBannerAdListener;
import com.heytap.msp.mobad.api.listener.IInterstitialAdListener;
import com.heytap.msp.mobad.api.listener.IInterstitialVideoAdListener;
import com.heytap.msp.mobad.api.listener.INativeAdListener;
import com.heytap.msp.mobad.api.listener.IRewardVideoAdListener;
import com.heytap.msp.mobad.api.params.INativeAdData;
import com.heytap.msp.mobad.api.params.INativeAdFile;
import com.heytap.msp.mobad.api.params.NativeAdError;
import com.heytap.msp.mobad.api.params.RewardVideoAdParams;
import com.igame.protocol.PrivacyDialog;
import com.igame.protocol.ProtocolDialog;
import com.nearme.game.sdk.GameCenterSDK;
import com.nearme.game.sdk.callback.GameExitCallback;
import com.njsr.mnhtfj.nearme.gamecenter.R;

import java.util.List;

//import com.bumptech.glide.Glide;


public class AdManager {

    private static final String TAG = AdManager.class.getSimpleName();


    private Handler mHandler=new Handler(Looper.getMainLooper());

    private static AdManager sInstance;
    Handler handler = new Handler(Looper.getMainLooper());


    public static AdManager getInstance() {
        if (sInstance == null) {
            sInstance = new AdManager();
        }

        return sInstance;
    }



    boolean isEnableBanner=true;




    private NativeAd mNativeAd;
    private View mBannerAdView;
    Activity mActivity;
    public void init(Activity activity){
        this.mActivity=activity;
    }
    public void showInterstitial(Activity activity, final AdListener listener) {

        final InterstitialAd interstitialAd = new InterstitialAd(activity, Constants.INTER);

        interstitialAd.setAdListener(new IInterstitialAdListener() {
            @Override
            public void onAdReady() {
                interstitialAd.showAd();
            }

            @Override
            public void onAdClose() {

            }

            @Override
            public void onAdShow() {
                if (listener != null) {
                    listener.onSuccess();
                }
            }

            @Override
            public void onAdFailed(String s) {

            }

            @Override
            public void onAdFailed(int i, String s) {
                if (listener != null) {
                    listener.onFailed(s);
                }
                Log.e(TAG, s);
            }

            @Override
            public void onAdClick() {

            }
        });
        interstitialAd.loadAd();
    }

    public void jumpLeisureSubject(final Activity activity) {

        ImageView imageView = new ImageView(activity);
        imageView.setImageResource(R.drawable.jump);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameCenterSDK.getInstance().jumpLeisureSubject();
            }
        });
        FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(dip2px(activity, 48), dip2px(activity, 48));
        param.leftMargin = dip2px(activity, 8);
        param.topMargin = dip2px(activity, 8);

        activity.addContentView(imageView, param);
    }

    View mJumpperView;
    public void initOppoJumper(final Activity activity){



        if (mJumpperView == null) {
            mJumpperView = LayoutInflater.from(activity).inflate(R.layout.layout_bottom, null);

            RelativeLayout parent = new RelativeLayout(activity);
            RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, dip2px(activity, 80));
            adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            adParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            //adParams.rightMargin = dip2px(activity, 80);
            parent.addView(mJumpperView, adParams);

            FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            param.bottomMargin = dip2px(activity, 5);
            activity.addContentView(parent, param);
            mJumpperView.findViewById(R.id.iv_go_oppo).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GameCenterSDK.getInstance().jumpLeisureSubject();
                }
            });

            mJumpperView.findViewById(R.id.btn_privacy).setClickable(true);
            mJumpperView.findViewById(R.id.btn_privacy).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    showPrivacyDetail();
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
                }
            });
            mJumpperView.findViewById(R.id.tv_contact).setClickable(true);
            mJumpperView.findViewById(R.id.tv_contact).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showContactDialog(activity);
                }
            });
//            mJumpperView.findViewById(R.id.btn_goto_exit).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.e("exit","oppo_game_exit");
//                        exitApp(activity);
//
//                }
//            });



        }
        hideContact();
    }

    public void showPrivacyDetail()
    {
        ProtocolDialog dialog = new ProtocolDialog(mActivity, "用户隐私政策",mActivity.getString(R.string.user_frg_privacy_content));
        dialog.setOnlyOk(true);
        dialog.show();

    }

    public void showContactDialog(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //2.设置属性 icon图标 title标题 message内容

        builder.setTitle("");
        builder.setMessage(Constants.EMAIL);
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

    public void showPrivacy(Activity activity){
        PrivacyDialog dialog = new PrivacyDialog(activity, "隐私政策");
        dialog.setCallback(new PrivacyDialog.ProtocolDialogCallback() {
            @Override
            public void onOk() {
            }
            @Override
            public void onCancel() {
                exitApp(activity);
            }
        });
        dialog.show();
        dialog.setCancelButtonText("退出游戏");
    }



    public void exitApp(Activity activity){
        GameCenterSDK.getInstance().onExit(activity, new GameExitCallback() {

            @Override
            public void exitGame() {
                try {
                    android.os.Process.killProcess(android.os.Process.myPid());   //获取PID
                    System.exit(0);
                } catch (Exception ex) {
                }
            }
        });
    }


    public void hideContact(){
        if(mJumpperView!=null){
            Log.e("jumpper","hideJumpper");
            mJumpperView.setVisibility(View.GONE);
        }
    }

    public void showContact(){
        if(mJumpperView!=null){
            Log.e("jumpper","hideJumpper");
            mJumpperView.setVisibility(View.VISIBLE);
        }
    }




//    public void hideBanner(){
//
//        if(mBannerAdView!=null){
//            mBannerAdView.setVisibility(View.GONE);
//        }
//
////        if(mNativeAd!=null) {
////            mNativeAd.destroyAd();
////            mNativeAd=null;
////        }
//
//
//
//    }
//
//    public void showBanner(final Activity activity) {
//        //int number = ccavjmdiejrsa.opppNumber;
//        int number=1;
//        if (number == 0) {
//            // 不显示
//        } else {
//            if (mNativeAd == null) {
//                mNativeAd = new NativeAd(activity, Constants.BANNER, new INativeAdListener() {
//                    @Override
//                    public void onAdSuccess(List<INativeAdData> list) {
//                        if (list != null && !list.isEmpty()) {
//                            initView(activity, list);
//                        }
//                    }
//
//                    @Override
//                    public void onAdFailed(NativeAdError nativeAdError) {
//                        Log.e(TAG, nativeAdError.code + nativeAdError.msg);
//                    }
//
//                    @Override
//                    public void onAdError(NativeAdError nativeAdError, INativeAdData iNativeAdData) {
//                        Log.e(TAG, nativeAdError.code + nativeAdError.msg);
//                    }
//                });
//            }
//            mNativeAd.loadAd();
//
//        }
//    }


    RelativeLayout mOppoBannerContainer ;

    BannerAd mBannerAd;
    public void initOppoBanner(Activity activity) {


        /**
         * 构造 bannerAd
         */

        if (mOppoBannerContainer == null) {
            mOppoBannerContainer = new RelativeLayout(activity);

            FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            param.leftMargin=DimenUtils.dip2px(activity,20);
//            param.rightMargin=DimenUtils.dip2px(activity,20);

            param.gravity= Gravity.BOTTOM|Gravity.CENTER_VERTICAL;
            activity.addContentView(mOppoBannerContainer, param);
        }

         mBannerAd = new BannerAd(activity, Constants.BANNER);
        /**
         * 设置Banner广告行为监听器
         */
        mBannerAd.setAdListener(new IBannerAdListener() {
            @Override
            public void onAdReady() {

            }

            @Override
            public void onAdClose() {
                reloadBanner();
            }

            @Override
            public void onAdShow() {

            }

            @Override
            public void onAdFailed(String s) {

            }

            @Override
            public void onAdFailed(int i, String s) {
                Log.e("banner","banner:fall:"+i+":"+s);
                reloadBanner();
            }

            @Override
            public void onAdClick() {

            }
        });
        /**
         * 获取Banner广告View，将View添加到你的页面上去
         *
         */
        View adView = mBannerAd.getAdView();
        /**
         * mBannerAd.getAdView()返回可能为空，判断后在添加
         */
        if (null != adView) {
            /**
             * 这里addView是可以自己指定Banner广告的放置位置【一般是页面顶部或者底部】
             */
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            mOppoBannerContainer.addView(adView, layoutParams);
        }
        /**
         * 调用loadAd()方法请求广告.
         */
        mBannerAd.loadAd();

    }


    public void showOppoBanner(){

        if(!isEnableBanner){
            return;
        }

        if(mOppoBannerContainer!=null){
            mOppoBannerContainer.setVisibility(View.VISIBLE);
        }
    }

    public void hideOppoBanner(){
        if(!isEnableBanner){
            return;
        }
        if(mOppoBannerContainer!=null){
            mOppoBannerContainer.setVisibility(View.GONE);
        }
    }

    public void reloadBanner(){

        mHandler.removeCallbacks(r);
        mHandler.postDelayed(r,40*1000);


    }

    Runnable r=new Runnable() {
        @Override
        public void run() {
            if(mBannerAd!=null){

                mBannerAd.loadAd();
            }
        }
    };



//    public void repeatTask(Activity activity) {
//        if (mRepeatTask == null) {
//            mRepeatTask = new RepeatTask(activity);
//        }
//        handler.postDelayed(mRepeatTask, 1 * 60 * 1000);
//    }

    private void initView(Activity activity, List<INativeAdData> list) {
        final INativeAdData data = list.get(0);
        if (data.isAdValid()) {

            if (mBannerAdView == null) {
                mBannerAdView = LayoutInflater.from(activity).inflate(R.layout.view_banner, null);

                RelativeLayout parent = new RelativeLayout(activity);
                RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(dip2px(activity, 360), dip2px(activity, 48));
                adParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                adParams.rightMargin = dip2px(activity, 80);
                parent.addView(mBannerAdView, adParams);

                FrameLayout.LayoutParams param = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                param.bottomMargin = dip2px(activity, 48);
                activity.addContentView(parent, param);
            }

//            if(mBannerAdView.getVisibility()==View.GONE){
//                mBannerAdView.setVisibility(View.VISIBLE);
//            }

//            mData = data;

            if (null != data.getImgFiles() && data.getImgFiles().size() == 3) {
//                Glide.with(activity).load(data.getImgFiles().get(0)).into((ImageView) mBannerAdView.findViewById(R.id.img_1_iv));
//                Glide.with(activity).load(data.getImgFiles().get(1)).into((ImageView) mBannerAdView.findViewById(R.id.img_2_iv));
//                Glide.with(activity).load(data.getImgFiles().get(2)).into((ImageView) mBannerAdView.findViewById(R.id.img_3_iv));
            }

            if (null != data.getLogoFile()) {
                //Log.e("url","url:"+data.getLogoFile().getUrl());
                Glide.with(activity).load(data.getLogoFile().getUrl()).into((ImageView) mBannerAdView.findViewById(R.id.logo_iv));
            }

            TextView title = mBannerAdView.findViewById(R.id.title_tv);
            title.setText(data.getDesc() == null ? "" : data.getDesc());
//            TextView desc = mBannerAdView.findViewById(R.id.desc_tv);
//            desc.setText(data.getDesc() == null ? "" : data.getDesc());

            mBannerAdView.findViewById(R.id.close_iv).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mBannerAdView.setVisibility(View.GONE);
                }
            });

//            Button click = mBannerAdView.findViewById(R.id.click_bn);
//            click.setText(null == data.getClickBnText() ? "" : data.getClickBnText());
            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    data.onAdClick(view);
                }
            });


            data.onAdShow(mBannerAdView);
            mBannerAdView.setVisibility(View.VISIBLE);
        } else {
            if (mBannerAdView != null) {
                mBannerAdView.setVisibility(View.GONE);
            }
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void removeParent(View view) {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
    }

    interface AdListener {
        void onSuccess();

        void onFailed(String s);

        void onComplete();
    }


//    private RepeatTask mRepeatTask;
//
//    class RepeatTask implements Runnable {
//        Activity activity;
//
//        public RepeatTask(Activity activity) {
//            this.activity = activity;
//        }
//
//        @Override
//        public void run() {
//            handler.postDelayed(mRepeatTask, 1 * 60 * 1000);
//
//            Bridge.showBanner((BaseGameActivity)activity);
//            if (!isShow) {
////                showNative1(activity, new OnNativeListener() {
////                    @Override
////                    public void onNativeShown(INativeAdData response) {
////                        PreferenceHelper.updateLastAdShowedTimestamp(activity);
////                    }
////
////                    @Override
////                    public void onNativeFailed(String msg) {
////
////                    }
////
////                });
//            }
//        }
//    }


    public interface OnNativeListener {
        void onNativeShown(INativeAdData response);

        void onNativeFailed(String msg);
    }

    long mNativeTime=0;
    public void showNative1(final Activity activity, final OnNativeListener listener) {
        long currTime=System.currentTimeMillis();
        long diff=currTime-mNativeTime;
        if(diff<3*1000) {
            return;
        }

        mNativeTime=currTime;

        showNative(activity, Constants.NATIVE, listener);
    }

//    public void showNative2(final Activity activity, final OnNativeListener listener) {
//        showNative(activity, Constants.OPPO_AD_NATIVE2, listener);
//    }
//
//    public void showNative3(final Activity activity, final OnNativeListener listener) {
//        showNative(activity, Constants.OPPO_AD_NATIVE3, listener);
//    }

    private NativeAd ad;
    private boolean isShow;

     RelativeLayout mNativeLayout;

    public void showNative(final Activity activity, String nativeId, final OnNativeListener listener) {

        if (ad != null) {
            ad.destroyAd();
        }

        ad = new NativeAd(activity, nativeId, new INativeAdListener() {
            @Override
            public void onAdSuccess(List<INativeAdData> list) {
                if (list == null || list.isEmpty()) {
                    if (listener != null) {
                        listener.onNativeFailed("native item is null");
                    }
                    return;
                }

                final INativeAdData adData = list.get(0);
                if (adData != null && adData.isAdValid()) {

                    if(mNativeLayout!=null){
                        closeAdView(mNativeLayout);
                    }

                    mNativeLayout= new RelativeLayout(activity);
                    final NativeDialogViewNew view = new NativeDialogViewNew(activity, false);
                    mNativeLayout.setBackgroundColor(0x77000000);
                    RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(DimenUtils.dip2px(activity,400)
                            , ViewGroup.LayoutParams.WRAP_CONTENT);
                    rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
                    mNativeLayout.addView(view, rlp);
                    mNativeLayout.setPadding(15, 0, 15, 15);


                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            adData.onAdClick(mNativeLayout);
                            //closeAdView(mNativeLayout);
                        }
                    });
                    view.getCloseLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            closeAdView(mNativeLayout);
                            isShow = false;
                        }
                    });

                    AQuery aq = new AQuery(mNativeLayout);
                    if (null != adData.getLogoFile()) {
                        //Log.e("image","image1:"+adData.getLogoFile().getUrl());
                        aq.id(R.id.logo_iv).image(adData.getLogoFile().getUrl());
                    }


                    if(null!=adData.getImgFiles()&&adData.getImgFiles().size()>0){
                        INativeAdFile iNativeAdFile = adData.getImgFiles().get(0);
                        aq.id(R.id.img_iv).image(iNativeAdFile.getUrl(), false, true);
                        Log.e("image","imageurl2:"+iNativeAdFile.getUrl());
                    }
                    else if (null != adData.getIconFiles() && adData.getIconFiles().size() > 0) {
                        INativeAdFile iNativeAdFile = adData.getIconFiles().get(0);
                        aq.id(R.id.img_iv).image(iNativeAdFile.getUrl(), false, true);
                        Log.e("image","imageurl1:"+iNativeAdFile.getUrl());
                    }

                    aq.id(R.id.click_bn).text(null != adData.getClickBnText() ? adData.getClickBnText() : "").clicked(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            /**
                             *原生广告被点击时必须调用onAdClick方法通知SDK进行点击统计；
                             * 注意：onAdClick方法必须在onAdShow方法之后再调用才有效，否则是无效点击。
                             */
                            adData.onAdClick(v);
                        }
                    });

                    view.getTextView().setText(adData.getDesc());

                    adData.onAdShow(view);
                    isShow = true;
                    PreferenceHelper.updateLastAdShowedTimestamp(activity);

                    if (listener != null) {
                        listener.onNativeShown(adData);
                    }
                    //
                    //DimenUtils.dip2px(activity,640)
                    activity.addContentView(mNativeLayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                } else {
                    if (listener != null) {
                        listener.onNativeFailed("native item is invalid");
                    }
                }
            }

            @Override
            public void onAdFailed(NativeAdError nativeAdError) {
                if (listener != null) {
                    listener.onNativeFailed(nativeAdError.code + ":" + nativeAdError.msg);
                }
            }

            @Override
            public void onAdError(NativeAdError nativeAdError, INativeAdData iNativeAdData) {
                if (listener != null) {
                    listener.onNativeFailed(nativeAdError.code + ":" + nativeAdError.msg);
                }
            }
        });

        ad.loadAd();
    }

    public static void closeAdView(View view) {
        if(view!=null) {
            ViewParent viewParent = view.getParent();
            if (viewParent != null && viewParent instanceof ViewGroup) {
                ((ViewGroup) viewParent).removeView(view);
            }
        }
    }

    public void closeAdView(){
        closeAdView(mNativeLayout);

    }


    private InterstitialVideoAd mInterstitialVideoAd;




    boolean isVideoAdReady=false;

    public void initInterstitialVideoAd(Activity context,IInterstitialVideoAdListener listener){
        if(mInterstitialVideoAd==null) {
            mInterstitialVideoAd = new InterstitialVideoAd(context, Constants.INTERSTITIAL_VIDEO_POS_ID, new IInterstitialVideoAdListener() {
                @Override
                public void onVideoPlayComplete() {
                    listener.onVideoPlayComplete();
                }

                @Override
                public void onAdReady() {
                    isVideoAdReady=true;
                    listener.onAdReady();
                    mInterstitialVideoAd.showAd();
                }

                @Override
                public void onAdClose() {
                    isVideoAdReady=false;
                    listener.onAdClose();
                }

                @Override
                public void onAdShow() {
                    isVideoAdReady=false;
                    listener.onAdShow();

                }

                @Override
                public void onAdFailed(String s) {
                    listener.onAdFailed(s);
                }

                @Override
                public void onAdFailed(int i, String s) {
                    listener.onAdFailed(i,s);
                }

                @Override
                public void onAdClick() {
                    listener.onAdClick();
                }
            });
        }
        /**
         * 调用 loadAd() 方法请求广告.
         */

        mInterstitialVideoAd.loadAd();
    }


    public void showInterstitialVideoAd(){
            isVideoAdReady=false;
        if(isVideoAdReady){


        }else{
            mInterstitialVideoAd.loadAd();
        }
    }



    RewardVideoAd mRewardVideoAd;
    public void showRewardVideoAd(Activity activity,IRewardVideoAdListener listener){
        if(mRewardVideoAd==null) {
            mRewardVideoAd=new RewardVideoAd(activity, Constants.REWARD_VIDEO_POS_ID, listener);
        }



        RewardVideoAdParams rewardVideoAdParams = new RewardVideoAdParams.Builder()
                .setFetchTimeout(3000)
                .build();

        if(mRewardVideoAd!=null){
        mRewardVideoAd.loadAd(rewardVideoAdParams);
        }
    }

    public void playRewardVideo() {
        /**
         * TODO 在播放广告时候，先调用isReady方法判断当前是否有广告可以播放；如果有、再调用showAd方法播放激励视频广告。
         */
        if (mRewardVideoAd.isReady()) {
            mRewardVideoAd.showAd();

        }
    }




}
