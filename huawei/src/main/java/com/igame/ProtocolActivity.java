package com.igame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.glgame.wjfjzc.huawei.R;

public class ProtocolActivity extends Activity {

    Handler mHandler=new Handler( Looper.getMainLooper());
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privaty);

        //SharedInfoService.getInstance(this).setIsAgreeProtocl(true);
        if(SharedInfoService.getInstance(this).getIsAgreeProtocol()){
            next();
        }else {
            showProtocol();
        }
    }

    private void showProtocol() {
        ProtocolDialog v1 = new ProtocolDialog(this, this.getString(R.string.protocol_title), LayoutInflater.from(this).inflate(R.layout.layout_protocol_content, null));
        v1.setCallback(new ProtocolDialog.ProtocalDialogCallback() {
            @Override
            public void cancelCallback() {
                SharedInfoService.getInstance(getBaseContext()).setIsAgreeProtocl(false);
                ProtocolActivity.this.finish();
                System.exit(0);
            }

            @Override
            public void okCallback(boolean arg1) {
                next();
            }
        });
        v1.setICloseDlgListener(new ProtocolBaseDialog.ICloseDlgListener() {
            @Override
            public void onCloseDlg() {
                ProtocolActivity.this.finish();
            }
        });
        v1.setCanceledOnTouchOutside(false);
        v1.show();
    }

    private void next(){
        delayToNext();
    }


    public void delayToNext(){
        this.mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goNext();
            }
        },500);
    }

    public void goNext(){
        SharedInfoService.getInstance(this).setIsAgreeProtocl(true);
        Intent intent=new Intent();
        intent.setClass(this,SplashActivity.class);
        this.startActivity(intent);
        this.finish();
    }

}
