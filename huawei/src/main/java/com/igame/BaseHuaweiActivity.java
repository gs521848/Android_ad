package com.igame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.glgame.wjfjzc.huawei.R;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.framework.common.NetworkUtil;
import com.huawei.hms.jos.AntiAddictionCallback;
import com.huawei.hms.jos.AppParams;
import com.huawei.hms.jos.AppUpdateClient;
import com.huawei.hms.jos.JosApps;
import com.huawei.hms.jos.JosAppsClient;
import com.huawei.hms.jos.JosStatusCodes;
import com.huawei.hms.jos.games.Games;
import com.huawei.hms.jos.games.PlayersClient;
import com.huawei.hms.jos.games.player.Player;
import com.huawei.hms.jos.games.player.PlayerExtraInfo;
import com.huawei.hms.jos.games.player.PlayersClientImpl;
import com.huawei.hms.support.account.request.AccountAuthParams;
import com.huawei.hms.support.hwid.HuaweiIdAuthManager;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams;
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper;
import com.huawei.hms.support.hwid.result.AuthHuaweiId;
import com.huawei.hms.support.hwid.result.HuaweiIdAuthResult;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack;
import com.igame.common.SignInCenter;

import org.json.JSONException;

import java.io.Serializable;

public class BaseHuaweiActivity extends AppCompatActivity {

    private String playerId;
    private String sessionId = null;
    private boolean hasInit = false;
    private final static int SIGN_IN_INTENT = 3000;
    boolean isLoginSuccess=false;
    void initLogin(){
        try {
            showLog("start logining");

            signIn();
        }catch (Exception ex){
            Log.e("ex","ex",ex);
        }
    }

    public static void showLog(String logLine) {
        //show(logLine);
        Log.e("hwlog","log:"+logLine);
    }




    public void gameEnd() {
        if (TextUtils.isEmpty(playerId)) {
            showLog("GetCurrentPlayer first.");
            return;
        }
        if (TextUtils.isEmpty(sessionId)) {
            showLog("SessionId is empty.");
            return;
        }
        PlayersClient client = Games.getPlayersClient(this);
        Task<String> task = client.submitPlayerEvent(playerId, sessionId, "GAMEEND");
        task.addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                showLog("submitPlayerEvent traceId: " + s);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                if (e instanceof ApiException) {
                    String result = "rtnCode:" + ((ApiException) e).getStatusCode();
                    showLog(result);
                }
            }
        });
    }







    /**
     * Login authorization result response processing method.
     * *
     * 登录授权的结果响应处理方法
     *
     * @param data Data
     */
    private void handleSignInResult(Intent data) {
        if (null == data) {
            showLog("signIn inetnt is null");
            return;
        }
        // HuaweiIdSignIn.getSignedInAccountFromIntent(data);
        String jsonSignInResult = data.getStringExtra("HUAWEIID_SIGNIN_RESULT");
        if (TextUtils.isEmpty(jsonSignInResult)) {
            showLog("SignIn result is empty");
            return;
        }
        try {
            HuaweiIdAuthResult signInResult = new HuaweiIdAuthResult().fromJson(jsonSignInResult);
            if (0 == signInResult.getStatus().getStatusCode()) {
                showLog("Sign in success.");
                showLog("Sign in result: " + signInResult.toJson());

                SignInCenter.get().updateAuthHuaweiId(signInResult.getHuaweiId());
                getCurrentPlayer();
            } else {
                showLog("Sign in failed: " + signInResult.getStatus().getStatusCode());
                showLog("Sign in failed: " + signInResult.getStatus().getStatusMessage());
                changeLoginState();
            }
        } catch (JSONException var7) {
            showLog("Failed to convert json from signInResult.");
        }
    }


    public void getCurrentPlayer() {
        PlayersClientImpl client = (PlayersClientImpl) Games.getPlayersClient(this);

        Task<Player> task = client.getCurrentPlayer();
        task.addOnSuccessListener(new OnSuccessListener<Player>() {
            @Override
            public void onSuccess(Player player) {
                String result = "display:" + player.getDisplayName() + "\n" + "playerId:" + player.getPlayerId() + "\n"
                        + "playerLevel:" + player.getLevel() + "\n" + "timestamp:" + player.getSignTs() + "\n"
                        + "playerSign:" + player.getPlayerSign();
                showLog(result);
                String myplayid = player.getPlayerId();

                if(TextUtils.isEmpty(playerId)){
                    playerId=myplayid;
                }else if(playerId.equals(myplayid)){

                }else{
                    loginByClick();
                    return;
                }

                showLog("playId:"+playerId);

                removeLoginView();
                gameBegin();
//                handler = new Handler() {
//                    @Override
//                    public void handleMessage(Message msg) {
//                        super.handleMessage(msg);
//                        gamePlayExtra();
//                    }
//                };

                gamePlayExtra();
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        Message message = new Message();
//                        handler.sendMessage(message);
//                    }
//                }, HEARTBEAT_TIME, HEARTBEAT_TIME);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                if (e instanceof ApiException) {
                    String result = "rtnCode:" + ((ApiException) e).getStatusCode();
                    showLog(result);
                }

                loginByClick();
            }
        });
    }

    /**
     * Get additional player information.
     * *
     * 获取玩家附加信息。
     */
    public void gamePlayExtra() {
        if (TextUtils.isEmpty(playerId)) {
            showLog("GetCurrentPlayer first.");
            return;
        }


        PlayersClient client = Games.getPlayersClient(this);

//        Task<Player> playerTask=client.getCurrentPlayer();
//        playerTask.addOnSuccessListener(new OnSuccessListener<Player>() {
//            @Override
//            public void onSuccess(Player player) {
//                if(player.)
//            }
//        });
        Task<PlayerExtraInfo> task = client.getPlayerExtraInfo(sessionId);
        task.addOnSuccessListener(new OnSuccessListener<PlayerExtraInfo>() {
            @Override
            public void onSuccess(PlayerExtraInfo extra) {
                if (extra != null) {
                    showLog("IsRealName: " + extra.getIsRealName() + ", IsAdult: " + extra.getIsAdult() + ", PlayerId: "
                            + extra.getPlayerId() + ", PlayerDuration: " + extra.getPlayerDuration());
                    if(!extra.getIsAdult()){

                        //玩家未成年，进行后续处理
                        showLog("The player is underage");
//                        if(!isTimeAllow()){
//                            showChildrenDialog();
//                        }
                    }else{
                        //showChildrenDialog();
                    }

                } else {
                    showLog("Player extra info is empty.");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                if (e instanceof ApiException) {
                    String result = "rtnCode:" + ((ApiException) e).getStatusCode();
                    showLog(result);
                    int rtnCode=((ApiException) e).getStatusCode();
                    //返回7022时，表示该玩家已经成年或者未实名认证，此时可以放通处理
                    if (rtnCode == 7022) {
                        showLog("The player is an adult or has not been authenticated by real name");
                        return;
                    }
                    //返回7002且当前网络正常，或者直接返回7006，均表示该帐号未在中国大陆注册，请直接放通
                    if ((rtnCode == 7002 && NetworkUtil.isNetworkAvailable(BaseHuaweiActivity.this)) || rtnCode == 7006) {
                        showLog("Allow the player to enter the game without checking the remaining time");
                        return;
                    }
                }
            }
        });
    }



    /**
     * Show the game buoy.
     * *
     * 显示游戏浮标。
     */
    private void showFloatWindowNewWay() {
        if (!hasInit) {
            initHms();
        }else{

        }
        Games.getBuoyClient(this).showFloatWindow();
    }

    /**
     * Hide the displayed game buoy.
     * *
     * 隐藏已经显示的游戏浮标。
     */
    private void hideFloatWindowNewWay() {
        Games.getBuoyClient(this).hideFloatWindow();
    }

    public void initHms() {
        AccountAuthParams params = AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM_GAME;
        AppParams appParams=new AppParams(params);
        ResourceLoaderUtil.setmContext(this);
        appParams.setAntiAddictionCallback(new AntiAddictionCallback() {
            @Override
            public void onExit() {
                // showChildrenDialog();
                Comm.getInstance().exitApp();
            }
        });

        JosAppsClient appsClient = JosApps.getJosAppsClient(this);
        Task<Void> initTask=appsClient.init(appParams);
        showLog("init success");

        initTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showLog("init success");
                hasInit = true;
                // Make sure that the interface of showFloatWindow() is successfully called once after the game has been initialized successfully
                // 游戏初始化成功后务必成功调用过一次浮标显示接口
                showFloatWindowNewWay();
                initLogin();
                // 一定要在init成功后，才可以调用登录接口
                // signIn();
                // if(!isLoginSuccess) {
                //   initLogin();
                //}
            }
        }).addOnFailureListener(
                new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        if (e instanceof ApiException) {
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            // Error code 7401 indicates that the user did not agree to Huawei joint operations privacy agreement
                            // 错误码为7401时表示用户未同意华为联运隐私协议
                            if (statusCode == JosStatusCodes.JOS_PRIVACY_PROTOCOL_REJECTED) {
                                showLog("has reject the protocol");
                                // You can exit the game or re-call the init interface.
                                // 在此处实现退出游戏或者重新调用初始化接口
                                // Toast.makeText(base"用户未同意华为联运隐私协议")
                                initHms();
                                return;
                            }
                            else if (statusCode == 907135003) {
                                // 907135003表示玩家取消HMS Core升级或组件升级
                                // 907135003 indicates that user rejected the installation or upgrade of HMS Core.
                                showLog("init statusCode=" + statusCode);
                                //initLogin();
                                initHms();
                                return;
                            }
                        }

                        showHmsErrDialog();

                    }
                });


        // hasInit = true;
        /**
         * Games released in the Chinese mainland: The update API provided by Huawei must be called upon game launch.
         * Games released outside the Chinese mainland: It is optional for calling the update API provided by Huawei
         * upon
         * game launch.
         * *
         * 检测应用新版本，中国大陆发布的应用：应用启动时必须使用华为升级接口进行应用升级。
         * 中国大陆以外发布的应用：不强制要求。
         */
        checkUpdate();
    }

    /**
     * Games released in the Chinese mainland: The update API provided by Huawei must be called upon game launch.
     * Games released outside the Chinese mainland: It is optional for calling the update API provided by Huawei upon
     * game launch.
     * *
     * 检测应用新版本，中国大陆发布的应用：应用启动时必须使用华为升级接口进行应用升级。
     * 中国大陆以外发布的应用：不强制要求。
     */
    public void checkUpdate() {
        AppUpdateClient client = JosApps.getAppUpdateClient(this);
        client.checkAppUpdate(this, new UpdateCallBack(this));
    }

    public void showHmsErrDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //2.设置属性 icon图标 title标题 message内容
        //builder.setIcon(com.al.airport.R.mipmap.app_icon);
        builder.setTitle("登录提示");

        builder.setMessage("华为移动服务初始化失败，请确认网络是否正常！");
        builder.setPositiveButton("重试", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(BaseGameActivity.this, "你点击了确定", Toast.LENGTH_SHORT).show();
                //UnityPlayer.UnitySendMessage("Ads","OnRewardedAdExpired","3x");
                dialogInterface.dismiss();
                initHms();

            }
        });
        builder.setNeutralButton("退出游戏\n", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Comm.getInstance().exitApp();
            }
        });

        builder.setCancelable(false);
        builder.show();


    }


    public void gameBegin() {

        if (TextUtils.isEmpty(playerId)) {
            showLog("GetCurrentPlayer first.");
            return;
        }
//        String uid = UUID.randomUUID().toString();
//        PlayersClient client = Games.getPlayersClient(this);
//        Task<String> task = client.submitPlayerEvent(playerId, uid, "GAMEBEGIN");
//        task.addOnSuccessListener(new OnSuccessListener<String>() {
//            @Override
//            public void onSuccess(String jsonRequest) {
//                if (jsonRequest == null) {
//                    showLog("jsonRequest is null");
//                    return;
//                }
//                try {
//                    JSONObject data = new JSONObject(jsonRequest);
//                    sessionId = data.getString("transactionId");
//                } catch (JSONException e) {
//                    showLog("parse jsonArray meet json exception");
//                    return;
//                }
//                showLog("submitPlayerEvent traceId: " + jsonRequest);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(Exception e) {
//                if (e instanceof ApiException) {
//                    String result = "rtnCode:" + ((ApiException) e).getStatusCode();
//                    showLog(result);
//                }
//            }
//        });
    }



    private static class UpdateCallBack implements CheckUpdateCallBack {
        private BaseHuaweiActivity apiActivity;

        private UpdateCallBack(BaseHuaweiActivity apiActivity) {
            this.apiActivity = apiActivity;
        }

        /**
         * Get update info from appmarket
         * *
         * 从应用市场获取的更新状态信息
         *
         * @param intent see detail:
         *        https://developer.huawei.com/consumer/cn/doc/development/HMS-References/appupdateclient#intent
         */
        @Override
        public void onUpdateInfo(Intent intent) {
            if (intent != null) {
                Serializable info = intent.getSerializableExtra("updatesdk_update_info");
                if (info instanceof ApkUpgradeInfo) {
                    showLog("check update success");
                    AppUpdateClient client = JosApps.getAppUpdateClient(apiActivity);
                    /**
                     * show update dialog
                     * *
                     * 弹出升级提示框
                     */
                    client.showUpdateDialog(apiActivity, (ApkUpgradeInfo) info, false);
                } else {
                    apiActivity.showLog("check update failed");
                }
            }
        }

        // ignored
        // 预留, 无需处理
        @Override
        public void onMarketInstallInfo(Intent intent) {
            Log.w("AppUpdateManager", "info not instanceof ApkUpgradeInfo");
            apiActivity.showLog("check update failed");
        }

        // ignored
        // 预留, 无需处理
        @Override
        public void onMarketStoreError(int responseCode) {
            apiActivity.showLog("check update failed");
        }

        // ignored
        // 预留, 无需处理
        @Override
        public void onUpdateStoreError(int responseCode) {
            apiActivity.showLog("check update failed");
        }
    }






    public void loginByClick(){
        login();
        changeLoginState();
    }

    public void signIn() {

        login();
        if(mloginButton!=null) {
            mloginButton.setText("正在登录");
            mloginButton.setEnabled(false);
        }
        Task<AuthHuaweiId> authHuaweiIdTask = HuaweiIdAuthManager.getService(this, getHuaweiIdParams()).silentSignIn();
        authHuaweiIdTask.addOnSuccessListener(new OnSuccessListener<AuthHuaweiId>() {
            @Override
            public void onSuccess(AuthHuaweiId authHuaweiId) {

                showLog("signIn success======》");
                showLog("display:=======>" + authHuaweiId.getDisplayName());


                SignInCenter.get().updateAuthHuaweiId(authHuaweiId);
                getCurrentPlayer();
                isLoginSuccess=true;
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {

                isLoginSuccess=false;
                showLog("signIn fail:"+e.getMessage());

                showLog("signIn failed:error exception");


                if (e instanceof ApiException) {
                    ApiException apiException = (ApiException) e;
                    showLog("signIn failed:" + apiException.getStatusCode());
                    showLog("start getSignInIntent");
                    signInNewWay();

                }else{

                    changeLoginState();
                }


            }
        });
    }


    public void login(){
        showLoginView(this);

    }



    RelativeLayout mloginlayout;
    ViewGroup mLoginView;
    Button mloginButton;
    public void  showLoginView(Activity activity) {

        showLog("showLoginView");
        if(mLoginView!=null){
            mloginlayout.setVisibility(View.VISIBLE);

            return;
        }

        if(mloginlayout==null){
            mloginlayout=new RelativeLayout(activity);
        }

        mloginlayout.removeAllViews();



        if(mLoginView==null){
            mLoginView=createLoginView(activity);
        }
        mLoginView.setVisibility(View.VISIBLE);
        mloginlayout.removeAllViews();
        mloginlayout.setBackgroundColor(0x77000000);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
        mloginlayout.addView(mLoginView, rlp);
        mloginlayout.setPadding(10, 0, 10, 10);
        mloginlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        mloginButton=mLoginView.findViewById(R.id.btn_login);
        mloginButton.setClickable(true);
        mloginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();
            }
        });
        Log.e("log","log:login:addContentView");
        activity.addContentView(mloginlayout, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public ViewGroup createLoginView(Activity activity){
        View view=activity.getLayoutInflater().inflate(R.layout.layout_login,null);
        return (ViewGroup)view;
    }

    public void removeLoginView(){

        isLoginSuccess=true;
        if(mloginlayout!=null) {
            showLog("removeLoginView");
            mloginlayout.setVisibility(View.GONE);
        }else{
            showLog("mloginlayout is null");
        }



//        if(mloginlayout!=null&&mloginlayout.getParent()!=null) {
//            showLog("removeLoginView:mloginlayout");
//            ((ViewGroup) mloginlayout.getParent()).removeView(mloginlayout);
//        }
    }


    public void changeLoginState(){

        login();
        mloginButton.setText("登录");
        mLoginView.setVisibility(View.VISIBLE);
        mloginButton.setEnabled(true);
    }




    /**
     * Obtain the Intent of the Huawei account login authorization page, and open the Huawei account
     * login authorization page by calling startActivityForResult(Intent, int).
     * *
     * 获取到华为帐号登录授权页面的Intent，并通过调用startActivityForResult(Intent, int)打开华为帐号登录授
     * 权页面。
     */
    public void signInNewWay() {
        try {
            Intent intent = HuaweiIdAuthManager.getService(this, getHuaweiIdParams()).getSignInIntent();
            startActivityForResult(intent, SIGN_IN_INTENT);
        }catch (Exception ex){
            changeLoginState();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (SIGN_IN_INTENT == requestCode) {
            handleSignInResult(data);
        } else {
            showLog("unknown requestCode in onActivityResult");
        }
    }

    public HuaweiIdAuthParams getHuaweiIdParams() {
        return new HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM_GAME).createParams();
    }




    @Override
    protected void onStop() {
        super.onStop();
        gameEnd();
        //Log.e(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();





        //this.mUnityPlayer.pause();
        hideFloatWindowNewWay();
        System.currentTimeMillis();
        Comm.getInstance().removeNativeAd();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // front
        gameBegin();


        //Log.e(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        showFloatWindowNewWay();

        if(isLoginSuccess) {
            getCurrentPlayer();
        }

        Log.e("game", "onResume");
    }



}
