package com.igame.vivolib;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PreferenceHelper {
    private static final String PREFE = "PREFERENCE.XML";
    public static void setGetMoneyFreeTimes(Context context,int value){
        SharedPreferences sp = context.getSharedPreferences(PREFE,Context.MODE_PRIVATE);
        sp.edit().putInt("get_money_free",value).commit();
    }
    public static int getMoneyFreeTimes(Context context){
        SharedPreferences sp = context.getSharedPreferences(PREFE,Context.MODE_PRIVATE);
        int old = sp.getInt("get_money_free",0);
        return old;
    }
    public static boolean isGetMoneyCompleteToday(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        SharedPreferences sp = context.getSharedPreferences(PREFE,Context.MODE_PRIVATE);
        String old = sp.getString("get_money_free_day","20000101");
        return date.equals(old);
    }
    public static void setGetMoneyCompleteToday(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        SharedPreferences sp = context.getSharedPreferences(PREFE,Context.MODE_PRIVATE);
        sp.edit().putString("get_money_free_day",date).commit();
    }
    public static void setGetGemFreeTimes(Context context,int value){
        SharedPreferences sp = context.getSharedPreferences(PREFE,Context.MODE_PRIVATE);
        sp.edit().putInt("get_gem_free",value).commit();
    }
    public static int getGemFreeTimes(Context context){
        SharedPreferences sp = context.getSharedPreferences(PREFE,Context.MODE_PRIVATE);
        int old = sp.getInt("get_gem_free",0);
        return old;
    }
    public static boolean isGetGemCompleteToday(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        SharedPreferences sp = context.getSharedPreferences(PREFE,Context.MODE_PRIVATE);
        String old = sp.getString("get_gem_free_day","20000101");
        return date.equals(old);
    }
    public static void setGetGemCompleteToday(Context context){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());
        SharedPreferences sp = context.getSharedPreferences(PREFE,Context.MODE_PRIVATE);
        sp.edit().putString("get_gem_free_day",date).commit();
    }

}
