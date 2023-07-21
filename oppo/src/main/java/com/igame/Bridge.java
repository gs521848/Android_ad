package com.igame;

import android.content.Context;
import android.util.Log;


import java.util.Random;

public class Bridge {
    static String mScreen="";
    public static void onAction(Context context, String action){



    }

//    public static void showInter(BaseGameActivity activity){
//        activity.showInter();
//    }


    static Random random=new Random();
    public static void showNative(BaseGameActivity activity){

//        if(isRandomShow()) {
//            activity.showNative();
//        }
        //AdManager.getInstance().randomShowInterstitial()
    }

//    public static void showBanner(BaseGameActivity activity){
//
//        if(!"game".equals(mScreen)){
//            return;
//        }
//
////        int deco = com.njzlzs.yzdcg.nearme.gamecenter.a.b();
////        if(deco<=0){
////            return;
////        }
//        //activity.showNative();
//        AdManager.getInstance().showBanner(activity);
//    }
//
//    static void hideBanner(BaseGameActivity activity){
//        AdManager.getInstance().hideBanner();
//    }


    static boolean isRandomShow(){
        //int deco = com.njzlzs.yzdcg.nearme.gamecenter.a.b();
        //Log.e("oppo_deco","deco:"+deco+":"+com.njzlzs.yzdcg.nearme.gamecenter.a.c());
        int deco=3;
        int randomInt =  random.nextInt(101);
        Log.e("oppo_deco","deco:int:"+randomInt);
        switch (deco){
            case 0: return false;
            case 1: return randomInt <= 15;
            case 2: return randomInt <= 35;
            case 3: return randomInt <= 80;
        }
        return  false;
    }


}
