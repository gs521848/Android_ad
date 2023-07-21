package com.igame;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.huawei.hms.ads.HwAds;
import com.huawei.hms.api.HuaweiMobileServicesUtil;
import com.umeng.commonsdk.UMConfigure;


public class ProxyApplication  {
    boolean isInited;
    public static Class toGame;

    //@Override
    public void onCreate(Context context) {

    }

    static ProxyApplication mApplication;


    public static ProxyApplication getInstance(){
        if(mApplication==null){
            mApplication=new ProxyApplication();
        }
        return mApplication;
    }

    Context mContext;
    Runnable mRunnable;
    public void init(Context context,Class gameClass){

        mContext=context;
        toGame=gameClass;
        try {
            UMConfigure.preInit(mContext.getApplicationContext(), umengkey, umengchannel);
        }catch (Throwable throwable){
            Log.e("ex","ex",throwable);
        }

    }


    public void initFromActivity(){
        if (!isInited){
            //initUmeng(mContext);
            //UMConfigure.init(this, UM_KEY, Constants.UMENG_CHANNEL, UMConfigure.DEVICE_TYPE_PHONE, null);
            ProxyApplication.getInstance().initUmeng();
            HwAds.init(mContext.getApplicationContext());
            HuaweiMobileServicesUtil.setApplication((Application)mContext);
            isInited=true;
        }
    }



    String umengkey="626279fe30a4f67780b2af23";//"61add850e0f9bb492b81ba6d";
    String umengchannel="huawei";

    boolean isInitedUmeng=false;
     void initUmeng(){
         if(!isInitedUmeng) {
             try {

                 Log.e("ex","ex:umeng start");
                 UMConfigure.init(mContext.getApplicationContext(), umengkey, umengchannel, UMConfigure.DEVICE_TYPE_PHONE, null);
                 //UMConfigure.setLogEnabled(true);
                 isInitedUmeng=true;
             } catch (Exception ex) {
                 Log.e("ex","ex",ex);
             }
         }
    }

}



