package com.igame.vivolib;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.igame.ProxyApplication;
import com.vivo.ad.model.AdError;
import com.vivo.ad.splash.SplashAdListener;
import com.vivo.mobilead.model.BackUrlInfo;
import com.vivo.mobilead.splash.SplashAdParams;
import com.vivo.mobilead.splash.VivoSplashAd;
import com.xmmy.fjmnfx.vivo.R;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashAdActivity extends Activity {

    private String[] permissions = new String[]{
            READ_PHONE_STATE
			, ACCESS_COARSE_LOCATION
			, ACCESS_FINE_LOCATION
            , WRITE_EXTERNAL_STORAGE
            , READ_EXTERNAL_STORAGE
            , ACCESS_WIFI_STATE
            , Manifest.permission.ACCESS_NETWORK_STATE
            , INTERNET
//			, READ_CONTACTS
//			, CALL_PHONE
//			, READ_SMS
//			, READ_CALL_LOG
			, CAMERA
//          , REQUEST_INSTALL_PACKAGES
    };

    private static final int REQUEST_CODE_PERMISSIONS = 2;
    Handler handler = new Handler();



    @Override
    protected void onCreate(final Bundle savedInstanceState) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        }
        super.onCreate(savedInstanceState);
        //
        setContentView(R.layout.splash_activity);
        //
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        /*
         * Android Q 暗色模式兼容：
         * 如果需要自行兼容Android Q以上的暗色模式，则注释以下代码
         * 如默认采用暗色模式下保持与正常模式一样，则保留以下代码，并在activity_splash.xml添加白色背景splash_bg_color
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ((ViewGroup) this.getWindow().getDecorView().findViewById(android.R.id.content)).setForceDarkAllowed(false);
        }


//        decode.init(this, this, "800014", "2020");

        //ProxyApplication.getInstance().initFromActivity();

        requestMorePermissions(savedInstanceState);
//        ccavjmdiejrsa.tinicsadfe(VivoApplication.getInstance(), this, "100010", "2020", "", new CallBackIniTheNew() {
//            @Override
//            public void initEnd(int i) {
//
//            }
//        });


    }

    private void requestMorePermissions(final Bundle bundle){
        PermissionUtils.checkAndRequestMorePermissions(SplashAdActivity.this, permissions, REQUEST_CODE_PERMISSIONS,
                new PermissionUtils.PermissionRequestSuccessCallBack() {
                    @Override
                    public void onHasPermission() {
                        // 权限已被授予
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
    private void init(){

       ProxyApplication.getInstance().initFromActivity();
       handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               goNext();
           }
       },500);

    }

    private void goNext(){
        try {
            handler.removeCallbacks(mainTask);
            handler.postDelayed(mainTask,5000);
//                ((VivoApplication)VivoApplication.getInstance()).init();
//            builder.setSplashOrientation(SplashAdParams.ORIENTATION_LANDSCAPE);

            ViewGroup container = findViewById(R.id.ad_content);
            fetchSplashAD(this,
                    VivoConstans.SPLASH_POSITION_ID  , new SplashAdListener() {
                        @Override
                        public void onADDismissed() {
                            finish();
                            jumpToMain();

                        }

                        @Override
                        public void onNoAD(AdError adError) {
                            Log.e("zaga_ad","get splash item " + adError.getErrorMsg()
                                    + " " + adError.getErrorCode());
                            finish();
                            jumpToMain();

                        }

                        @Override
                        public void onADPresent() {
                            handler.removeCallbacks(mainTask);
                            Log.e("zaga_ad","get Native item splash" );
                        }

                        @Override
                        public void onADClicked() {

                        }
                    });
        }catch (Exception e){
            jumpToMain();
        }
    }
    Runnable mainTask = new Runnable() {
        @Override
        public void run() {
            jumpToMain();
            finish();
        }
    };


    private void jumpToMain(){
        try {
            handler.removeCallbacks(mainTask);
        }catch (Throwable throwable){
        }


        Intent intent = new Intent(this, ProxyApplication.getInstance().gameClass);
        startActivity(intent);
    }

    private void fetchSplashAD(Activity activity, String posId, SplashAdListener listener) {
        SplashAdParams.Builder builder = new SplashAdParams.Builder(posId);
// 拉取广告的超时时长：即开屏广告从请求到展示所花的最大时长（并不是指广告曝光时长）取值
        builder.setFetchTimeout(3000);
// 广告下面半屏的应用标题+应用描述:应用标题和应用描述是必传字段，不传将抛出异常
// 标题最长5个中文字符描述最长8个中文字符
        String appName=getString(activity.getResources().getIdentifier("app_name","string",getPackageName()));
        builder.setAppTitle(appName);
        builder.setAppDesc(appName+"!");
//backUrl为针对特殊广告主要求而设，接入方可以不设置这块
        String backUrl = "vivobrowser://browser.vivo.com?i=12";
        String btnName = "广告";
        builder.setBackUrlInfo(new BackUrlInfo(backUrl, btnName));

// 3.0.0.0版本新增支持横屏广告，通过如下api设置横竖屏广告，默认竖屏（还需要注意的是，此处设
        builder.setSplashOrientation(SplashAdParams.ORIENTATION_PORTRAIT);
        VivoSplashAd vivoSplashAd= new VivoSplashAd(activity, listener, builder.build());
//一定要主动 load 广告
        vivoSplashAd.loadAd();
    }
}
