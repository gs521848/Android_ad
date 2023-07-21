package com.unity3d.start;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceHelper {
    public static final String PREFERENCE = "ArmoredSquad";
    public static final String OTHER_REWARD_KEY = "other_reward_key";
    public static final String AD_DAY_LIMIT = "ad_day_limit";
    public static final String LAST_AD_SHOWED_TIMESTAMP = "last_ad_showed_timestamp";

    public static final int AD_DAY_SHOW_LIMIT = 5;
    public static final long REWARD_INTERVAL = 2 * 60_000; // 2 min

    public static long getTimestamp(Context context, String key) {
        return context.getSharedPreferences(PREFERENCE, MODE_PRIVATE).getLong(key, 0);
    }

    public static void updateTimestamp(Context context, String key) {
        long timestamp = System.currentTimeMillis();
        context.getSharedPreferences(PREFERENCE, MODE_PRIVATE).edit().putLong(key, timestamp).commit();
    }

    public static void updateLastAdShowedTimestamp(Context context) {
        updateTimestamp(context, LAST_AD_SHOWED_TIMESTAMP);
    }

    public static boolean canGetReward(Context context, String key) {
        long timestamp = System.currentTimeMillis();
        long lastTimeStamp = getTimestamp(context, key);
        return (timestamp - lastTimeStamp) >= REWARD_INTERVAL;
//        return true;
    }

    public static int getAdDaysShowedCount(Context context) {
        return context.getSharedPreferences(PREFERENCE, MODE_PRIVATE).getInt(AD_DAY_LIMIT, 0);
    }

    public static void updateAdDaysShowedCount(Context context, int count) {
        context.getSharedPreferences(PREFERENCE, MODE_PRIVATE).edit().putInt(AD_DAY_LIMIT, count).commit();
    }

    public static boolean isTodayGetReward(Context context, String key) {
        long lastTimestamp = getTimestamp(context, key);

        if (isSameDate(System.currentTimeMillis(), lastTimestamp)) {
            int count = getAdDaysShowedCount(context);
            if (count < AD_DAY_SHOW_LIMIT) {
                updateAdDaysShowedCount(context, count + 1);
                return true;
            } else {
                return false;
            }
        } else {
            updateAdDaysShowedCount(context, 0);
            return true;
        }

//        return isSameDate(System.currentTimeMillis(), lastTimestamp);
    }

    public static boolean isSameDate(long currentTime, long lastTime) {
        if (lastTime > currentTime) {
            return false;
        }

        try {
            Calendar nowCal = Calendar.getInstance();
            Calendar dataCal = Calendar.getInstance();
            SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
            SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");

            String data1 = df1.format(currentTime);
            String data2 = df2.format(lastTime);
            Date now = df1.parse(data1);
            Date date = df2.parse(data2);
            nowCal.setTime(now);
            dataCal.setTime(date);
            return isSameDay(nowCal, dataCal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 != null && cal2 != null) {
            return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA)
                    && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                    && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        } else {
            return false;
        }
    }

}
