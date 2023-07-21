package com.unity3d.start;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.igame.protocol.PrivacyDialog;
import com.njsr.mnhtfj.nearme.gamecenter.R;

public class PrivacyActivity extends FragmentActivity implements View.OnClickListener {
    CheckBox checkBox;
    Button button;
    TextView userPrivacy,gamePrivacy;
    SharedPreferences sp ;

    //FragmentActivity activity;
    //protected PrivacyActivity(FragmentActivity activity){
      //  this.activity=activity;
    //}



    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_privacy_new);
        sp = this.getSharedPreferences("privacy.db", MODE_PRIVATE);
        if (sp.getBoolean("apply_privacy", false)) {
            //this.startActivity(new Intent(this, SplashAdActivit y.class));
            goNext();

            return;
        }else{
            showPrivacy(this);

        }
//        checkBox = this.findViewById(R.id.cb_apply_cri);
//        this.findViewById(R.id.btn_start).setOnClickListener(this);
//        this.findViewById(R.id.tv_user_pri).setOnClickListener(this);
//        this.findViewById(R.id.tv_game_pri).setOnClickListener(this);
//        this.findViewById(R.id.btn_exit).setOnClickListener(this);

    }


    public void showPrivacy(Activity activity){
        PrivacyDialog dialog = new PrivacyDialog(activity, "用户须知");
        dialog.setCallback(new PrivacyDialog.ProtocolDialogCallback() {
            @Override
            public void onOk() {
                goNext();
                sp.edit().putBoolean("apply_privacy",true).commit();
            }
            @Override
            public void onCancel() {
                PrivacyActivity.this.finish();
                System.exit(0);
            }
        });
        dialog.setOkStr("同意");
        dialog.setCancelButtonText("拒绝");
        dialog.show();
        dialog.setCancelButtonText("拒绝");
        dialog.setCancelable(false);
    }


    public void onClick(View view){
        int id = view.getId();

            if(id== R.id.btn_start){
                if (checkBox.isChecked()){
                    goNext();
                    sp.edit().putBoolean("apply_privacy",true).commit();
                    this.finish();
                }else{
                    Toast.makeText(this,"请先阅读并同意用户协议与隐私政策",Toast.LENGTH_LONG).show();
                }
            } else if(id== R.id.tv_game_pri) {
                Bundle args = new Bundle();
                args.putInt("res_id", R.string.game_frg_privacy_content);
                Fragment fragment = UserPrivacyFragment.instantiate(this, UserPrivacyFragment.class.getName(), args);
                FragmentUtils.replace(this.getSupportFragmentManager(), fragment, R.id.content);
            } else if(id== R.id.tv_user_pri) {
                Bundle arg1 = new Bundle();
                arg1.putInt("res_id", R.string.user_frg_privacy_content);
                Fragment fragment1 = UserPrivacyFragment.instantiate(this, UserPrivacyFragment.class.getName(), arg1);
                FragmentUtils.replace(this.getSupportFragmentManager(), fragment1, R.id.content);
            }else if(id== R.id.btn_exit){
                this.finish();
            }
    }


    public void goNext(){
        this.startActivity(new Intent(this, SplashActivity.class));
        this.finish();
        //this.startActivity(new Intent(this,ProxyApplication.toGame));
    }
}
