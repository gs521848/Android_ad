package com.igame.vivolib;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.xmmy.fjmnfx.vivo.R;


public class PrivacyActivity extends FragmentActivity implements View.OnClickListener {
    CheckBox checkBox;
    Button button;
    TextView userPrivacy,gamePrivacy;
    SharedPreferences sp ;

    //FragmentActivity activity;
    //protected PrivacyActivity(FragmentActivity activity){
      //  this.activity=activity;
    //}


    Handler handler=new Handler();
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_privacy);
        sp = this.getSharedPreferences("privacy.db", MODE_PRIVATE);
        if (sp.getBoolean("apply_privacy", false)) {
            //this.startActivity(new Intent(this, SplashAdActivit y.class));
            Toast.makeText(this,"已同意隐私政策",Toast.LENGTH_SHORT).show();
            goNext();
            this.finish();



            return;
        }
        checkBox = this.findViewById(R.id.cb_apply_cri);
        this.findViewById(R.id.btn_start).setOnClickListener(this);
        this.findViewById(R.id.tv_user_pri).setOnClickListener(this);
        this.findViewById(R.id.tv_game_pri).setOnClickListener(this);
        this.findViewById(R.id.btn_exit).setOnClickListener(this);

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
            } else if(id==R.id.tv_game_pri) {
                Bundle args = new Bundle();
                args.putInt("res_id", R.string.game_frg_privacy_content);
                Fragment fragment = UserPrivacyFragment.instantiate(this, UserPrivacyFragment.class.getName(), args);
                FragmentUtils.replace(this.getSupportFragmentManager(), fragment, R.id.content);
            } else if(id==R.id.tv_user_pri) {
                Bundle arg1 = new Bundle();
                arg1.putInt("res_id", R.string.user_frg_privacy_content);
                Fragment fragment1 = UserPrivacyFragment.instantiate(this, UserPrivacyFragment.class.getName(), arg1);
                FragmentUtils.replace(this.getSupportFragmentManager(), fragment1, R.id.content);
            } else if(id==R.id.btn_exit){
                this.finish();
            }
    }


    public void goNext(){
        this.startActivity(new Intent(this, SplashAdActivity.class));

        //this.startActivity(new Intent(this, ProxyApplication.getInstance().gameClass));
    }
}
