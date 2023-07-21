package com.igame;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.igame.vivolib.VivoConstans;
import com.umeng.commonsdk.UMConfigure;
import com.vivo.mobilead.manager.VInitCallback;
import com.vivo.mobilead.manager.VivoAdManager;
import com.vivo.mobilead.unified.base.VivoAdError;
import com.vivo.unionsdk.open.VivoUnionSDK;

public class ProxyApplication  {

    static ProxyApplication sInstance;
    boolean isInited;



    public Class gameClass;

    public static ProxyApplication getInstance() {

        if(sInstance==null){
            sInstance=new ProxyApplication();
        }
        return sInstance;
    }
    public Application mContext;

    public void init(Application context,Class toClass){

        this.gameClass=toClass;
        mContext=context;

        preInitUmeng(context);





    }



    public void initFromActivity(){
        if (!isInited ){


            initUmeng();



            VivoAdManager.getInstance().init(mContext, VivoConstans.AD_MEDIA_ID,
                    new VInitCallback() {
                        @Override
                        public void suceess() {
                            Log.e("SDKInit", "suceess");
                        }
                        @Override
                        public void failed(@NonNull VivoAdError adError) {
                            Log.e("SDKInit", "failed: " + adError.toString());
                        }
                    });


            VivoUnionSDK.initSdk(mContext, VivoConstans.VIVO_APPID,false);

//            UMConfigure.init(this, UM_KEY, "vivo", UMConfigure.DEVICE_TYPE_PHONE
//                    , null);
            //VivoUnionSDK.initSdk(mContext, VivoConstans.VIVO_APPID,true);
            //VivoUnionHelper.initSdk(mContext.getApplicationContext(),  true);
            //VivoUnionHelper.registerMissOrderEventHandler(mContext, mMissOrderEventHandler);





//            VOpenLog.setEnableLog(true);


//            int splashOrientation = SettingSp.getInstance().getInt(Constants.ConfigureKey.HOT_SPLASH
//                    , Constants.DefaultConfigValue.HOT_SPLASH);
//            if (splashOrientation > 0) {
//                /**
//                 *
//                 * 若需要使用热启动开屏，请设置这里，具体使用方式请对照文档和当前demo
//                 */
//                VivoAdManager.getInstance()
//                        .enableHotSplash(mContext, FileUtil.from().getSplashId(), splashOrientation);
//            }
//            appActivityLifecycleCallbacks = new AppActivityLifecycleCallbacks();
//            context.registerActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks) appActivityLifecycleCallbacks);
//            BaseLib.init(this, "");
//            if (splashOrientation > 0) {
//                /**
//                 * 若需要使用热启动开屏，请设置这里，具体使用方式请对照文档和当前demo
//                 */
//                VivoAdManager.getInstance().enableHotSplash(this, Constans.SPLASH_POSITION_ID, splashOrientation);
//            }

            isInited = true;
        }
    }



    String umengkey=VivoConstans.UMENG_KEY;
    String umengchannel="vivo";

    boolean isInitedUmeng;
    public void initUmeng(){
        if(!isInitedUmeng) {
            try {
                UMConfigure.init(mContext, umengkey, umengchannel, UMConfigure.DEVICE_TYPE_PHONE, null);
                isInitedUmeng=true;
            } catch (Exception ex) {

            }
        }
    }

    public void preInitUmeng(Context context){
        try {
            UMConfigure.preInit(context, umengkey, umengchannel);
        }catch (Throwable throwable){
        }
    }




}

