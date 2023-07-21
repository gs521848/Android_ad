package com.igame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.heytap.msp.mobad.api.listener.IInterstitialVideoAdListener;
import com.heytap.msp.mobad.api.listener.IRewardVideoAdListener;
import com.heytap.msp.mobad.api.params.INativeAdData;
import com.njsr.mnhtfj.nearme.gamecenter.R;
import com.unity3d.start.AdManager;
import com.unity3d.start.DimenUtils;

public abstract class BaseGameActivity extends AppCompatActivity {

    ImageView imageView;


//    public abstract  void setUnityCallComplete(String str);
//
//    public abstract  void setUnityCallSkip(String str);

    boolean isPause=true;
    boolean isEnterMainMenu=false;

    public Handler  mHandler=new Handler(Looper.getMainLooper());






    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //init();
    }

    @Override
    protected void onPause() {
        super.onPause();





    }

    public abstract void runRreward();

    @Override
    protected void onResume() {
        super.onResume();


        AdManager.getInstance().closeAdView();

    }

    public void init(){

        //ProxyApplication.getInstance().initGameCenter(this);


        ProxyApplication.getInstance().initFromActivity(this);
        AdManager.getInstance().init(this);
        imageView= new ImageView(this);
        imageView.setImageResource(R.drawable.splash);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //mUnityPlayer.addViewToPlayer(imageView,false);
        this.addContentView(imageView,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                imageView.setVisibility(View.GONE);
                //mUnityPlayer.removeViewFromPlayer(imageView);
                AdManager.getInstance().initOppoBanner(BaseGameActivity.this);
                 AdManager.getInstance().hideOppoBanner();
                AdManager.getInstance().showContact();
            }

        },3000);

        AdManager.getInstance().initOppoJumper(this);

        doLogin();
        //initAD();
    }

    void doLogin(){



//        GameCenterSDK.getInstance().doLogin(this, new ApiCallback() {
//
//            @Override
//            public void onSuccess(String resultMsg) {
//
////                Toast.makeText(DemoActivity.this, resultMsg, Toast.LENGTH_LONG)
////                        .show();
//            }
//
//            @Override
//            public void onFailure(String resultMsg, int resultCode) {
//                Toast.makeText(BaseGameActivity.this, resultMsg+resultCode, Toast.LENGTH_LONG)
//                        .show();
//
//
//            }
//        });
    }

    boolean is3xclick=false;

    int insert_ShowLevel=0;

    boolean isGame=false;
    public void doAction(String action){



//        if(action.equals("action_backhome")){
//            AdManager.getInstance().showContact();
//
//        }else {
//            if(!action.equals("insert_ResetCubes")&&!action.equals("key_back")) {
//
//
//                AdManager.getInstance().hideContact();
//            }
//        }

        if("insert_bottom".equals(action)){
            return;
        }


        Log.e("action","action:"+action);

        if(action.equals("action_showbanner")){
            AdManager.getInstance().hideContact();
            AdManager.getInstance().showOppoBanner();
            return;
        }else if(action.equals("action_hidebanner")){
            AdManager.getInstance().showContact();
            AdManager.getInstance().hideOppoBanner();
            return;
        }

        if(action.startsWith("reward_")){

            //showReward("reward_do");

            //showRewardDialog("reward_hint");

            showReward("reward_do");
            //showRewardDialog(action);

            //AdManager.getInstance().showJumpper();
        }
        else if(action.startsWith("insert_")){

//            if(TextUtils.equals(action,"insert_PauseButton")){
//                Comm.getInstance().loadInterstitialAd(this);
//            }else {
//                Comm.getInstance().loadAd(this);
//            }




                showNative();

//            if(TextUtils.equals(action,"insert_play")){
//                Comm.getInstance().showBanner(this);
//            }

//            AdManager.getInstance().showNative1(this, new AdManager.OnNativeListener() {
//                @Override
//                public void onNativeShown(INativeAdData response) {
//
//                }
//
//                @Override
//                public void onNativeFailed(String msg) {
//
//                }
//            });
        }

        else if(action.equals("key_back")){
            //showExitDialog();
            AdManager.getInstance().exitApp(this);
        }




    }


    Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            showNative();
        }
    };

    static String messageToken="message";

    public void showRewardDialog(String reward){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //2.设置属性 icon图标 title标题 message内容


        int id=this.getResources().getIdentifier("hint_icon","mipmap",this.getPackageName());
        //com.al.airport.R.mipmap.app_icon
        builder.setIcon(id);
        builder.setTitle("");
        if(reward.startsWith("reward_do_0")||reward.startsWith("reward_do_100")) {
            builder.setMessage(" 看视频免费得 100个金币");
        }else{
            builder.setMessage(" 看视频免费得 200 个金币");
        }
        //设置按钮 PositiveButton确定按钮（参数:"显示内容"，点击监听）



        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showReward("test");
                dialogInterface.dismiss();
                //DimenUtils.hideSystemUI(getWindow());
            }
        });
        //设置按钮 NegativeButton取消按钮（参数:"显示内容"，点击监听）
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //DimenUtils.hideSystemUI(getWindow());
            }
        });
        //3.使用建造者创建对话框
        AlertDialog dialog = builder.create();
        //4.显示
        dialog.show();
        //DimenUtils.hideSystemUI(dialog.getWindow());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if( hasFocus ) {
            DimenUtils.hideSystemUI(getWindow());
        }
    }

    public void showNative(){
        AdManager.getInstance().showNative1(this, new AdManager.OnNativeListener() {
            @Override
            public void onNativeShown(INativeAdData response) {

                Log.e("oppoad","oppoad onNativeShown");
            }

            @Override
            public void onNativeFailed(String msg) {
                Log.e("oppoad","oppoad fail"+msg);
            }
        });
    }



    public void showToast(String string){
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }

    boolean isVideoComplete=false;

    String rewardType="";

    void initAD(String rewardType){
        this.rewardType=rewardType;
        isVideoComplete=false;
        AdManager.getInstance().initInterstitialVideoAd(this, new IInterstitialVideoAdListener() {
            @Override
            public void onVideoPlayComplete() {

                isVideoComplete=true;
                Log.e("oppo_ad","oppo_ad：onVideoPlayComplete");
            }

            @Override
            public void onAdReady() {
                Log.e("oppo_ad","oppo_ad：ready");
            }

            @Override
            public void onAdClose() {
                if(isVideoComplete==true){
//                    if("3x".equals(rewardType)) {
//                        UnityPlayer.UnitySendMessage("Ads","OnRewardedAdExpired","3x");
//                    }else if("unlockplane".equals(rewardType)) {
//                        UnityPlayer.UnitySendMessage("Ads", "OnRewardedAdExpired", "unlockplane");
//                    }else if("bonusLevel".equals(rewardType)){
//                        UnityPlayer.UnitySendMessage("Ads", "OnRewardedAdExpired", "bonusLevel");
//                    }else if("reward_vehicle_claimed".equals(rewardType)){
//                        UnityPlayer.UnitySendMessage("Ads", "OnRewardedAdExpired", "reward_vehicle_claimed");
//                    }else if("retryAfterFail".equals(rewardType)){
//                        UnityPlayer.UnitySendMessage("Ads", "OnRewardedAdExpired", "retryAfterFail");
//                    }
                }else{
                    showToast("请完整观看视频领取奖励");
                }
                Log.e("oppo_ad","oppo_ad：onAdClose");
            }

            @Override
            public void onAdShow() {
                Log.e("oppo_ad","oppo_ad：onAdShow");
            }

            @Override
            public void onAdFailed(String s) {

                Log.e("oppo_ad","oppo_ad：fail:"+s);
            }

            @Override
            public void onAdFailed(int i, String s) {
                Log.e("oppo_ad","oppo_ad："+i+"fail:"+s);
                if(i==1003){
                    showToast("暂时无广告,请先玩会儿游戏,稍候再试");
                }else if(i==11003){
                    showToast("您请求广告太频繁了,请先玩会儿游戏,稍候再试");
                }
            }

            @Override
            public void onAdClick() {
                //UnityPlayer.UnitySendMessage("Ads","OnRewardedAdExpired","3x");
                Log.e("oppo_ad","oppo_ad：click");
            }
        });
    }


    public void showReward(String type){

        isVideoComplete=false;
        AdManager.getInstance().showRewardVideoAd(this, new IRewardVideoAdListener() {
            @Override
            public void onReward(Object... objects) {
                isVideoComplete=true;
                //setUnityCallComplete("rewardedVideoZone");
                Log.e("oppoad","oppoad_reward");

                runRreward();
            }

            @Override
            public void onAdSuccess() {
                Log.e("oppoad","oppoad_success");
                AdManager.getInstance().playRewardVideo();
            }

            @Override
            public void onAdFailed(String s) {
                Log.e("oppoad","oppoad_onAdFailed");
                //setUnityCallSkip("rewardedVideoZone");
            }

            @Override
            public void onAdFailed(int i, String s) {
                //setUnityCallSkip("rewardedVideoZone");

                if(i==1003){
                    showToast("暂时无广告,请先玩会儿游戏,稍候再试");
                }else if(i==11003){
                    showToast("您请求广告太频繁了,请先玩会儿游戏,稍候再试");
                }
                Log.e("oppoad","oppoad_onAdFailed2"+"code:"+i+" msg:"+s);
            }

            @Override
            public void onAdClick(long l) {

            }

            @Override
            public void onVideoPlayStart() {
                Log.e("oppoad","oppoad_onVideoPlayStart");
            }

            @Override
            public void onVideoPlayComplete() {
                Log.e("oppoad","oppoad_onVideoPlayComplete");
            }

            @Override
            public void onVideoPlayError(String s) {
                Log.e("oppoad","oppoad_onVideoPlayError");
              //  setUnityCallSkip("rewardedVideoZone");
            }

            @Override
            public void onVideoPlayClose(long l) {
               // setUnityCallSkip("rewardedVideoZone");

                Log.e("oppoad","oppoad_onVideoPlayClose");
            }

            @Override
            public void onLandingPageOpen() {
                Log.e("oppoad","onLandingPageOpen");
            }

            @Override
            public void onLandingPageClose() {
                Log.e("oppoad","onLandingPageClose");
            }
        });
    }


    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //2.设置属性 icon图标 title标题 message内容
        builder.setIcon(R.mipmap.app_icon);
        builder.setTitle("标题");
        builder.setMessage("您看完了广告，可获取金币");
        //设置按钮 PositiveButton确定按钮（参数:"显示内容"，点击监听）
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(BaseGameActivity.this, "你点击了确定", Toast.LENGTH_SHORT).show();
               // UnityPlayer.UnitySendMessage("Ads","OnRewardedAdExpired","3x");
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
}
