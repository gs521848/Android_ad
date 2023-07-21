package com.igame;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedInfoService {
    private static final String PROTOCL = "protocl";
    private SharedPreferences mPreferences;
    private static SharedInfoService util;

    private SharedInfoService(Context arg3) {
        super();
        this.mPreferences = arg3.getSharedPreferences("HiSharedPreferences", 0);
    }

    public static SharedInfoService getInstance(Context arg2) {
        SharedInfoService v2_1=null;

        synchronized (SharedInfoService.class) {
            try {
                if (SharedInfoService.util == null) {
                    SharedInfoService.util = new SharedInfoService(arg2);
                }

                v2_1 = SharedInfoService.util;
            } catch (Throwable th) {

            }
        }

        return v2_1;
    }

    public boolean getIsAgreeProtocol() {
        return this.mPreferences.getBoolean("protocl", false);
    }

    public void setIsAgreeProtocl(boolean arg3) {
        this.mPreferences.edit().putBoolean("protocl", arg3).commit();
    }


    public boolean canSkipLevel(){

        if(true){
            return true;
        }
        //this.mPreferences.edit().putLong("skipleveltime",System.currentTimeMillis());
        long levelTime=this.mPreferences.getLong("skipleveltime",0);
        return System.currentTimeMillis()-levelTime>1000*60*15;
    }

    public void updateSkipLevelTime(){
        this.mPreferences.edit().putLong("skipleveltime",System.currentTimeMillis()).commit();
    }

}
