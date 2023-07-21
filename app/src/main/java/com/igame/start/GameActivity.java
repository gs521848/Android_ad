package com.igame.start;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import androidx.annotation.RequiresApi;

import com.example.myapplication.R;
import com.igame.BaseGameActivity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GameActivity extends BaseGameActivity {

    WebView mWebview;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        init();
    }

    @SuppressLint("JavascriptInterface")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    void initView() {

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }

        this.getWindow().getDecorView().setSystemUiVisibility(2050);
        this.getWindow().addFlags(1024);

        mWebview = findViewById(R.id.webview);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setDomStorageEnabled(true);// 打开本地缓存提供JS调用,至关重要
        mWebview.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);// 实现8倍缓存
        mWebview.getSettings().setAllowFileAccess(true);
        mWebview.getSettings().setAppCacheEnabled(true);
        String appCachePath = getApplication().getCacheDir().getAbsolutePath();
        mWebview.getSettings().setAppCachePath(appCachePath);
        mWebview.getSettings().setDatabaseEnabled(true);
        mWebview.addJavascriptInterface(this,"android");

        mWebview.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        WebView.setWebContentsDebuggingEnabled(true);


        try {
            if (Build.VERSION.SDK_INT >= 16) {
                Class<?> clazz = mWebview.getSettings().getClass();
                Method method = clazz.getMethod(
                        "setAllowUniversalAccessFromFileURLs", boolean.class);//利用反射机制去修改设置对象
                if (method != null) {
                    method.invoke(mWebview.getSettings(), true);//修改设置
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        mWebview.loadUrl("file:///android_asset/bin/index.html");

    }

    @Override
    protected void onResume() {
        super.onResume();
        isResume=true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResume=false;
    }

    @Override
    public void runRreward() {
        mWebview.evaluateJavascript("javascript:callOnReward();", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                //此处为 js 返回的结果
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)   {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            doAction("key_back");
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }


    boolean isResume=false;
    @JavascriptInterface
    public boolean isUiActive(){
        return isResume;
    }



    @JavascriptInterface
    public void doJava(String action){
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if(action.equals("action_help")){
                    showHelpDialog();
                }

                doAction(action);
                //Toast.makeText(GameActivity.this,"action:"+action,Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void showHelpDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //2.设置属性 icon图标 title标题 message内容
        builder.setIcon(R.mipmap.app_icon);
        builder.setTitle("玩法说明");
        builder.setMessage("过关规则：黑点出发,按住不放,穿过所有的 + 号格子，直到空心圆");
        //设置按钮 PositiveButton确定按钮（参数:"显示内容"，点击监听）
        builder.setPositiveButton("返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(GameActivity.this, "你点击了确定", Toast.LENGTH_SHORT).show();
                // UnityPlayer.UnitySendMessage("Ads","OnRewardedAdExpired","3x");
            }
        });

        //3.使用建造者创建对话框
        AlertDialog dialog = builder.create();
        //4.显示
        dialog.show();

    }

}