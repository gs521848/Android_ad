package com.igame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseGameActivity extends AppCompatActivity {

    ImageView imageView;

    boolean isPause=true;
    boolean isEnterMainMenu=false;

    public Handler  mHandler=new Handler(Looper.getMainLooper());




    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    public void init(){

    }


    public abstract void runRreward();

    @Override
    protected void onResume() {
        super.onResume();



        //AdManager.getInstance().closeAdView();
        //AdManager.getInstance().closeNativeExpressAd();
    }


    public void doAction(String action){
        if("key_back".equals(action)){
            showExitDialog();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //2.设置属性 icon图标 title标题 message内容

        builder.setTitle("标题");
        builder.setMessage("您看完了广告，可获取金币");
        //设置按钮 PositiveButton确定按钮（参数:"显示内容"，点击监听）
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(BaseGameActivity.this, "你点击了确定", Toast.LENGTH_SHORT).show();

            }
        });
        //设置按钮 NegativeButton取消按钮（参数:"显示内容"，点击监听）
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(BaseGameActivity.this, "你点击了取消", Toast.LENGTH_SHORT).show();
            }
        });
        //3.使用建造者创建对话框
        AlertDialog dialog = builder.create();
        //4.显示
        dialog.show();

    }


    public void showExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //2.设置属性 icon图标 title标题 message内容

        builder.setTitle(" 退出游戏");
        builder.setMessage("是否要退出游戏？");
        //设置按钮 PositiveButton确定按钮（参数:"显示内容"，点击监听）
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();
                System.exit(0);
            }
        });
        //设置按钮 NegativeButton取消按钮（参数:"显示内容"，点击监听）
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        //3.使用建造者创建对话框
        AlertDialog dialog = builder.create();
        //4.显示
        dialog.show();

    }
}
