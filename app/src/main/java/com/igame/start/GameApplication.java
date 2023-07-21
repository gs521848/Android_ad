package com.igame.start;

import android.app.Application;

import com.igame.ProxyApplication;

public class GameApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ProxyApplication.getInstance().init(this, GameActivity.class);
    }
}
