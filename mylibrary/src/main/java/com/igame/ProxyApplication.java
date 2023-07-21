package com.igame;

import android.app.Application;


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




    }




    public void initFromActivity(){

    }



}

