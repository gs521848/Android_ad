package com.igame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.glgame.wjfjzc.huawei.R;
import com.huawei.hms.ads.reward.Reward;
import com.huawei.hms.ads.reward.RewardAdStatusListener;

public abstract class BaseGameActivity extends BaseHuaweiActivity {
    int mGamePressCount=0;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        ProxyApplication.getInstance().initFromActivity();
        Comm.getInstance().init(this);
        //isTimeAllow();
        init();
        mStartTime=System.currentTimeMillis();
    }

    boolean isPause=true;
    boolean isEnterMainMenu=false;

    public Handler  mHandler=new Handler(Looper.getMainLooper());


    public abstract void runRreward();




    long mStartTime;
    public void init(){


        imageView= new ImageView(this);
        imageView.setImageResource(R.drawable.splash);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //mUnityPlayer.addViewToPlayer(imageView,false);
        this.addContentView(imageView,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


//                imageView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.GONE);

                //mUnityPlayer.removeViewFromPlayer(imageView);


                Comm.getInstance().showBanner(BaseGameActivity.this);
                Comm.getInstance().loadAd(BaseGameActivity.this);
            }

        },3000);

        //AdManager.getInstance().initOppoJumper(this);
        //initAD();

    }






    boolean is3xclick=false;

    int mPressCount=0;


    int insert_ResetCubes=0;

    int insert_ShowLevel=0;

    int insert_PassLevel=0;

    int insert_result=0;

    int ingameCount;

    String lastAction="";
    public void doAction(String action){

        Log.e("action","action:"+action);
        if(action==null) return;
        //showDialog();

//        AdManager.getInstance().showNative1(this,new AdManager.OnNativeListener(){
//            @Override
//            public void onNativeShown(INativeAdData response) {
//
//            }
//
//            @Override
//            public void onNativeFailed(String msg) {
//
//            }
//        });




        if(action.equals("PrivacyPolicyButton")){
         showPrivacy();
        }
        else if(action.startsWith("reward_")){

            initAD("reward_do");
            //AdManager.getInstance().showJumpper();
        }
        else if(action.startsWith("insert_")){

//            if(TextUtils.equals(action,"insert_PauseButton")){
//                Comm.getInstance().loadInterstitialAd(this);
//            }else {
//                Comm.getInstance().loadAd(this);
//            }





            if("insert_fail".equals(action)){


                initAD("reward_null");
            }else if("insert_success".equals(action)){
                insert_result++;
                if(insert_result%3==0){
                    initAD("reward_null");
                }else {
                    Comm.getInstance().loadAd(this);
                }

            }
            else if("insert_ShowLevel".equals(action)){


                lastAction="insert_ShowLevel";

                insert_ShowLevel++;
                if(insert_ShowLevel%2==0) {
                    Comm.getInstance().loadAd(this);
                    //Comm.getInstance().loadInterstitialAd(this);
                }else if(insert_ShowLevel>1){

                }
            }else {
                Comm.getInstance().loadAd(this);
            }
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
        }else if(action.startsWith("ingame_")){

            if(action.startsWith("Button-WeaponSwitch")) {
                ingameCount++;
                if (ingameCount % 5 == 0) {
                    Comm.getInstance().loadAd(this);
                }
            }else if(action.startsWith("ingame_Button-MedicKit")){
                Comm.getInstance().loadAd(this);
            }else if(action.startsWith("ingame_Button-Grenade")){
                Comm.getInstance().loadAd(this);
            }

        }
        else if(action.equals("key_back")){
            showExitDialog();
        }

    }

    public void showPrivacy(){

            ProtocolDialog v1 = new ProtocolDialog(this, this.getString(R.string.protocol_title), LayoutInflater.from(this).inflate(R.layout.layout_protocol_content, null));
            v1.setCallback(new ProtocolDialog.ProtocalDialogCallback() {
                @Override
                public void cancelCallback() {
                    BaseGameActivity.this.finish();
                    System.exit(0);
                }

                @Override
                public void okCallback(boolean arg1) {

                }
            });
            v1.setICloseDlgListener(new ProtocolBaseDialog.ICloseDlgListener() {
                @Override
                public void onCloseDlg() {

                }
            });
            v1.setCanceledOnTouchOutside(false);
            v1.show();

    }

    public void showToast(String string){
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }

    boolean isVideoComplete=false;

    String rewardType="";

    RewardAdStatusListener mRewardAdListener=new RewardAdStatusListener() {
        @Override
        public void onRewardAdClosed() {
            if(isVideoComplete==true){
//                if("3x".equals(rewardType)) {
//                    UnityPlayer.UnitySendMessage("Ads","OnRewardedAdExpired","3x");
//                }else if("unlockplane".equals(rewardType)) {
//                    UnityPlayer.UnitySendMessage("Ads", "OnRewardedAdExpired", "unlockplane");
//                }else if("skiplevel".equals(rewardType)){
//                    hasSkipLevel=true;
//                    SharedInfoService.getInstance(BaseGameActivity.this).updateSkipLevelTime();
//                    UnityPlayer.UnitySendMessage("Ads", "OnRewardedAdExpired", "skiplevel");
//                }else if("bonusLevel".equals(rewardType)){
//                    UnityPlayer.UnitySendMessage("Ads", "OnRewardedAdExpired", "bonusLevel");
//                }else if("reward_vehicle_claimed".equals(rewardType)){
//                    UnityPlayer.UnitySendMessage("Ads", "OnRewardedAdExpired", "reward_vehicle_claimed");
//                }else if("retryAfterFail".equals(rewardType)){
//                    UnityPlayer.UnitySendMessage("Ads", "OnRewardedAdExpired", "retryAfterFail");
//                }
                BaseGameActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
//                        Iterator v0 = AdsProperties.getListeners().iterator();
//                        while(v0.hasNext()) {
//                            ((IUnityAdsListener)v0.next()).onUnityAdsFinish("rewardedVideoZone", UnityAds.FinishState.COMPLETED);
//                        }
                       // GameDas.getListener().onUnityAdsFinish("rewardedVideoZone", GameDas.FinishState.COMPLETED);

                         if("reward_null".equals(rewardType)){
                             return;
                         }
                        runRreward();
                    }
                });
            }else{
                showToast("请完整观看视频领取奖励");
                failedToShow();
            }
            //showToast("onRewarded");
            Log.e("oppo_ad","oppo_ad：onAdClose");
        }

        @Override
        public void onRewardAdFailedToShow(int i) {
            failedToShow();
        }

        @Override
        public void onRewardAdOpened() {

        }

        @Override
        public void onRewarded(Reward reward) {
            isVideoComplete=true;
            //showToast("onRewarded");
        }
    };


    public void failedToShow(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //UnityPlayer.UnitySendMessage("UnityAdsManager", "GetDeviceExclusion", "sssss_xxx");
                    //UnityPlayer.UnitySendMessage("UnityAdsManager", "OnUnityAdsDidFinish", "null");

                    BaseGameActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
//                            Iterator v0 = AdsProperties.getListeners().iterator();
//                            while(v0.hasNext()) {
//                                ((IUnityAdsListener)v0.next()).onUnityAdsFinish("rewardedVideoZone", UnityAds.FinishState.SKIPPED);
//                            }


                        }
                    });
                }catch (Exception ex){

                }
            }
        });

        thread.run();
    }

    void initAD(String rewardType){
        this.rewardType=rewardType;
        isVideoComplete=false;
        Comm.getInstance().showReward(this, mRewardAdListener);

    }


    boolean hasSkipLevel=false;









    public void showDialog(){

        hasSkipLevel=false;
        if(hasSkipLevel==false&&SharedInfoService.getInstance(this).canSkipLevel()) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //2.设置属性 icon图标 title标题 message内容
            //builder.setIcon(com.al.airport.R.mipmap.app_icon);
            builder.setTitle("关卡菜单");

            builder.setMessage("客官您有1次看告广告跳过关卡的机会，请选择下列操作");
            //设置按钮 PositiveButton确定按钮（参数:"显示内容"，点击监听）
            builder.setPositiveButton("跳过关卡\n(看广告)", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Toast.makeText(BaseGameActivity.this, "你点击了确定", Toast.LENGTH_SHORT).show();
                    //UnityPlayer.UnitySendMessage("Ads","OnRewardedAdExpired","3x");
                    dialogInterface.dismiss();
                    initAD("skiplevel");

                }
            });
            builder.setNeutralButton("退出游戏\n", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Comm.getInstance().exitApp();
                }
            });
            //设置按钮 NegativeButton取消按钮（参数:"显示内容"，点击监听）
            builder.setNegativeButton("继续游戏\n", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Toast.makeText(BaseGameActivity.this, "你点击了取消", Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            });
            //3.使用建造者创建对话框
            AlertDialog dialog = builder.create();
            //4.显示
            dialog.show();
        }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //2.设置属性 icon图标 title标题 message内容
            //builder.setIcon(com.al.airport.R.mipmap.app_icon);
            builder.setTitle("关卡菜单");

            builder.setMessage("您跳过关卡的机会已经使用完了，是否继续挑战本关卡？\n(温馨提示:每次游戏启动可获取一次机会，离上次使用间隔需15分钟)");
            //设置按钮 PositiveButton确定按钮（参数:"显示内容"，点击监听）
            builder.setPositiveButton("退出游戏", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Comm.getInstance().exitApp();
                }
            });
            //设置按钮 NegativeButton取消按钮（参数:"显示内容"，点击监听）
            builder.setNegativeButton("继续游戏", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Toast.makeText(BaseGameActivity.this, "你点击了取消", Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            });
            //3.使用建造者创建对话框
            AlertDialog dialog = builder.create();
            //4.显示
            dialog.show();


        }

    }


    public void showExitDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //2.设置属性 icon图标 title标题 message内容
        //builder.setIcon(com.al.airport.R.mipmap.app_icon);
        builder.setTitle("是否退出游戏？");

        builder.setMessage("");
        //设置按钮 PositiveButton确定按钮（参数:"显示内容"，点击监听）
        builder.setPositiveButton("退出游戏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Comm.getInstance().exitApp();
            }
        });

        //设置按钮 NegativeButton取消按钮（参数:"显示内容"，点击监听）
        builder.setNegativeButton("继续游戏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(BaseGameActivity.this, "你点击了取消", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        //3.使用建造者创建对话框
        AlertDialog dialog = builder.create();
        //4.显示
        dialog.show();
    }








    @Override
    protected void onPause() {
        super.onPause();






        Comm.getInstance().removeNativeAd();
    }



    @Override
    protected void onResume() {
        super.onResume();


    Comm.getInstance().reloadBanner();

        //Log.e(TAG, "onResume");
    }




}
