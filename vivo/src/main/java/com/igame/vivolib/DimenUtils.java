package com.igame.vivolib;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DimenUtils {

    public static int dip2px(Context context, int dip){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }
    public static int dip2px(Context context, float dip){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        return displayMetrics.heightPixels;
    }

    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (null != context) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (null != windowManager) {
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            }
        }
        return displayMetrics;
    }
}
