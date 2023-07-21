package com.unity3d.start;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
//import android.support.annotation.NonNull;
import android.util.Log;

import com.heytap.msp.mobad.api.InitParams;
import com.heytap.msp.mobad.api.ad.LandSplashAd;
import com.heytap.msp.mobad.api.listener.ISplashAdListener;
import com.heytap.msp.mobad.api.params.SplashAdParams;
import com.igame.ProxyApplication;


import androidx.annotation.NonNull;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashAdActivity extends Activity {
    Handler handler = new Handler();
    private String[] permissions = {
            READ_PHONE_STATE
//			, ACCESS_COARSE_LOCATION
//			, ACCESS_FINE_LOCATION
            , WRITE_EXTERNAL_STORAGE
            , READ_EXTERNAL_STORAGE
            , ACCESS_WIFI_STATE
            , ACCESS_NETWORK_STATE
//			, READ_CONTACTS
//			, CALL_PHONE
//			, READ_SMS
//			, READ_CALL_LOG
//			, CAMERA
//          , REQUEST_INSTALL_PACKAGES
    };
    private static final int REQUEST_CODE_PERMISSIONS = 2;

    @Override
    protected void onCreate( final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        boolean isFirst = getSharedPreferences("preference_check_permission",Context.MODE_PRIVATE)
                .getBoolean("is_first_show_permission_dialog",true);
        if (isFirst)
        {
            new AlertDialog.Builder(this)
                    .setTitle("应用权限提示：")
                    .setMessage("我们需要一些权限才能正常使用，如果拒绝，可能会出现应用异常退出等现像。")
                    .setNegativeButton("拒绝授权", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getSharedPreferences("preference_check_permission",Context.MODE_PRIVATE)
                                    .edit().putBoolean("is_first_show_permission_dialog",false).commit();
                            init();
                        }
                    })
                    .setPositiveButton("前往授权", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            getSharedPreferences("preference_check_permission",Context.MODE_PRIVATE)
                                    .edit().putBoolean("is_first_show_permission_dialog",false).commit();
                            requestMorePermissions(savedInstanceState);
                        }
                    })
                    .create().show();
        }else{
            requestMorePermissions(savedInstanceState);
        }

    }
    private void init(){
        //ProxyApplication.getInstance().init();
        showOppoSplashAd(this);

    }
    /**
     * 请求权限
     */
    private void requestMorePermissions(final Bundle bundle){
        PermissionUtils.checkAndRequestMorePermissions(SplashAdActivity.this, permissions, REQUEST_CODE_PERMISSIONS,
                new PermissionUtils.PermissionRequestSuccessCallBack() {
                    @Override
                    public void onHasPermission() {
                        // 权限已被授予
                        //LogUti.log("has permission ");
                        init();


                    }
                });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSIONS:
                PermissionUtils.onRequestMorePermissionsResult( SplashAdActivity.this, permissions, new PermissionUtils.PermissionCheckCallBack() {
                    @Override
                    public void onHasPermission() {
                        init();
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDown(String... permission) {
                        init();
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                        init();
                    }
                });


        }
    }
    private void jumpToMain(){
        Intent intent = new Intent(this, ProxyApplication.toGame);
        //Intent intent = new Intent(this,SplashAdActivity.class);
        startActivity(intent);
    }
    LandSplashAd oppoSplash;

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private void showOppoSplashAd(final Activity activity){
        try {
            InitParams params = new InitParams.Builder()
                    .setDebug(true)
                    .build();

            SplashAdParams splashAdParams = new SplashAdParams.Builder()
                    .setFetchTimeout(5000)
                    .setShowPreLoadPage(false)
                    .build();
            oppoSplash = new LandSplashAd(this
                    , Constants.SPLASH
                    , new ISplashAdListener() {
                @Override
                public void onAdFailed(int i, String s) {

                }

                @Override
                public void onAdDismissed() {
                    Log.e("AD_MANAGER","onAdDismissed");
                    jumpToMain();
                    activity.finish();
                }

                @Override
                public void onAdShow(String s) {

                }

                @Override
                public void onAdShow() {
                }

                @Override
                public void onAdFailed(String s) {
                    Log.e("AD_MANAGER",s);
                    jumpToMain();
                    finish();
                }

                @Override
                public void onAdClick() {
                }
            },splashAdParams);
        }catch (Exception e){
            e.printStackTrace();
            Log.e("AD_MANAGER",e.toString());
            jumpToMain();
            finish();
        }
    }


}
