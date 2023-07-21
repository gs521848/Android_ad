package com.igame;

import android.content.Context;
import android.util.Log;

import com.heytap.msp.mobad.api.InitParams;
import com.heytap.msp.mobad.api.MobAdManager;
import com.heytap.msp.mobad.api.listener.IInitListener;
import com.nearme.game.sdk.GameCenterSDK;
import com.umeng.commonsdk.UMConfigure;
import com.unity3d.start.Constants;

public class ProxyApplication {
    boolean isInited;
    public static Class toGame;


    //@Override
    public void onCreate(Context context) {

//        String appSecret = "854530bd6f6347b790823e559d0e01a9";
//
//        GameCenterSDK.init(appSecret, context);
//        //super.onCreate();
//        //checkInit();
//        //a.initId();
//        initId();



    }

//    private void checkInit() {
//        boolean hasPermission = true;
//        for (String p : permissions){
//            if (!PermissionUtils.checkPermission(this,p)){
//                hasPermission = false;
//                break;
//            }
//        }
//        if (hasPermission){
//            init();
//        }
//    }


    static ProxyApplication mApplication;

    public static ProxyApplication getInstance(){
        if(mApplication==null){
            mApplication=new ProxyApplication();
        }
        return mApplication;
    }
    


    public void initSInstance() {
        //sInstance = this;
    }

    Context mContext;
    public void init(Context context,Class gameClass){

        mContext=context;
        toGame=gameClass;

        try {

            UMConfigure.preInit(context.getApplicationContext(), umengkey, umengchannel);
        }catch (Throwable throwable){
        }






    }


    static String umengkey=Constants.UM_KEY;
    static String umengchannel=Constants.UM_CHANNEL;


    public void initFromActivity(Context context){
        if (!isInited){

            ProxyApplication.getInstance().initGameCenter(context);
            initUmeng(mContext);
            //UMConfigure.init(this, UM_KEY, Constants.UMENG_CHANNEL, UMConfigure.DEVICE_TYPE_PHONE, null);







            InitParams initParams = new InitParams.Builder()
                    .setDebug(true)
                    .build();
            MobAdManager.getInstance().init(mContext, Constants.APP_ID, initParams, new IInitListener() {
                @Override
                public void onSuccess() {
                    Log.e("mob_ad_init","init success");
                }

                @Override
                public void onFailed(String s) {
                    Log.e("mob_ad_init","init fail:"+s);
                }
            });
            //AC.initAdManager(new AdManager());
            isInited = true;
        }
    }

    static boolean isCenterInit=false;
    public  void initGameCenter(Context context){
        if(isCenterInit==false) {
            String appSecret = Constants.AppSecret;
            GameCenterSDK.init(appSecret, mContext.getApplicationContext());


            isCenterInit=true;
        }
    }


    static void initUmeng(Context context){
        try {
            UMConfigure.init(context.getApplicationContext(), umengkey, umengchannel, UMConfigure.DEVICE_TYPE_PHONE, null);
        }catch (Exception ex){

        }
    }


}



