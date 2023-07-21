package com.igame.vivolib.adutis;

import android.content.Context;
import android.util.TypedValue;

/**
 * description ： TODO:类的作用
 * author : 72060925
 * date : 2019/09/21
 */
public class DensityUtils {
    /**
     * dp转px
     *
     * @param context
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
}
