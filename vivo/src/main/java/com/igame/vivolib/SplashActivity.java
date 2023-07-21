package com.igame.vivolib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.Nullable;

import com.igame.ProxyApplication;
import com.vivo.ad.model.AdError;
import com.vivo.ad.splash.SplashAdListener;
import com.vivo.mobilead.splash.SplashAdParams;
import com.vivo.mobilead.splash.VivoSplashAd;
import com.vivo.mobilead.util.VOpenLog;
import com.igame.vivolib.adutis.Constants;
import com.xmmy.fjmnfx.vivo.R;


public class SplashActivity extends Activity {
    public boolean canJump = false;

    VivoSplashAd vivoSplashAd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProxyApplication.getInstance().initFromActivity();

        SplashAdParams.Builder builder = new SplashAdParams.Builder(Constants.AD_SPLASH_KEY);
        builder.setFetchTimeout(3_000);
        builder.setAppTitle(getString(R.string.app_name));
        builder.setAppDesc(getString(R.string.app_name)+"!");
        builder.setSplashOrientation(SplashAdParams.ORIENTATION_PORTRAIT);

        vivoSplashAd = new VivoSplashAd(this, new SplashAdListener() {
            @Override
            public void onADDismissed() {
                next();
            }

            @Override
            public void onNoAD(AdError adError) {
                VOpenLog.d("splash", adError.getErrorCode() + " " + adError.getErrorMsg());

                if (vivoSplashAd != null) {
                    vivoSplashAd.close();
                }
                startMainActivity();
            }

            @Override
            public void onADPresent() {

            }

            @Override
            public void onADClicked() {

            }
        }, builder.build());

        vivoSplashAd.loadAd();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (canJump) {
            next();
        }
        canJump = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        canJump = false;
    }


    private void next() {
        if (canJump) {
            //开屏与游戏页面共存 可以用下面的方法
            // vivoSplashAd.removeSplashView();
            startMainActivity();
        } else {
            canJump = true;
        }
    }

    private void startMainActivity() {
        finish();
        startActivity(new Intent(this, ProxyApplication.getInstance().gameClass));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
