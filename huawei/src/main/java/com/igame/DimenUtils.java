package com.igame;

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
    public static DisplayMetrics getDisplayMetrics(Context arg2) {
        DisplayMetrics v0 = new DisplayMetrics();
        if(arg2 != null) {
            Object v2 = arg2.getSystemService(Context.WINDOW_SERVICE);
            if(v2 != null) {
                ((WindowManager)v2).getDefaultDisplay().getMetrics(v0);
            }
        }

        return v0;
    }

    public static int getScreenWidth(Context arg0) {
        return getDisplayMetrics(arg0).widthPixels;
    }

    public static int getScreenHeight(Context arg0) {
        return getDisplayMetrics(arg0).heightPixels;
    }

}
