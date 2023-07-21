package player;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.igame.ProxyApplication;
import com.vivo.ad.model.AdError;
import com.vivo.ad.splash.SplashAdListener;
import com.vivo.mobilead.model.BackUrlInfo;
import com.vivo.mobilead.splash.SplashAdParams;
import com.vivo.mobilead.splash.VivoSplashAd;
import com.vivo.mobilead.util.VADLog;
import com.igame.vivolib.VivoConstans;
import com.xmmy.fjmnfx.vivo.R;

public class SplashActivity extends Activity {
    private static final String TAG = "zzzz";
    protected SplashAdParams.Builder builder;
    public boolean canJump = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProxyApplication.getInstance().initFromActivity();
        initProtraitParams();
    }

    public void showTip(String info) {
        Log.e("xxx", info);
    }

    private void initProtraitParams() {
        String splashId = "自己媒体申请的 id";
        // 拉取广告的超时时长：即开屏广告从请求到展示所花的最大时长（并不是指广告曝光时长）取值范围[3000, 5000]
        builder = new SplashAdParams.Builder(VivoConstans.SPLASH_POSITION_ID);
        builder.setFetchTimeout(3000);
        /**
         * 标题最长 5 个中文字符 描述最长 8 个中文字符
         */
        builder.setAppTitle(getString(R.string.app_name));
        /**
         * 广告下面半屏的应用标题+应用描述:应用标题和应用描述是必传字段，不传将抛出异常
         */
        builder.setAppDesc("欢迎使用");
        String backUrl = "vivobrowser://browser.vivo.com?i=12";
        String btnName = "test";
        builder.setBackUrlInfo(new BackUrlInfo(backUrl, btnName));
/**
 * 可以根据需要配置横竖屏
 **/
        builder.setSplashOrientation(SplashAdParams.ORIENTATION_PORTRAIT);

        vivoSplashAd = new VivoSplashAd(this, new SplashAdListener() {
            @Override
            public void onADDismissed() {
                VADLog.d(TAG, "onADDismissed");
                showTip("广告消失");
                next();
            }

            @Override
            public void onNoAD(AdError adError) {
                VADLog.d(TAG, "onNoAD:" + adError.getErrorMsg());
                showTip("没有广告：" + adError.getErrorMsg());
                vivoSplashAd.close();
                toNextActivity();
            }

            @Override
            public void onADPresent() {
                VADLog.d(TAG, "onADPresent");
                showTip("广告展示成功");
            }

            @Override
            public void onADClicked() {
                VADLog.d(TAG, "onADClicked");
                showTip("广告被点击");
            }
        }, builder.build());

        //加载开屏
        vivoSplashAd.loadAd();

    }

    VivoSplashAd vivoSplashAd;

    @Override
    protected void onStart() {
        super.onStart();
    }

    //退出当前页面逻辑
    private boolean isForceMain = false;

    @Override
    protected void onPause() {
        super.onPause();
        isForceMain = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isForceMain) {
            next();
        }
        isForceMain = true;
    }

    /**
     * 此处逻辑可自行定义
     */
    private void goToMainActivity() {
        toNextActivity();
    }


    /**
     * 设置一个变量来控制当前开屏页面是否可以跳转，当广告被点击，会跳转其他页面，此时开发者还不能打开自己的App主页。当从其他页面返回以后， 才可以跳转到开发者自己的App主页；
     */
    private void next() {
        if (isForceMain) {
            toNextActivity();
        } else {
            isForceMain = true;
        }
    }

    //到下一个页面的代码。
    private void toNextActivity() {
        startActivity(new Intent(SplashActivity.this, ProxyApplication.getInstance().gameClass));
        this.finish();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        canJump = false;
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (canJump) {
//            next();
//        }
//        canJump = true;
//    }

    /**
     * 开屏页一定要禁止用户对返回按钮的控制，
     * 否则将可能导致用户手动退出了App而广告无法正常曝光和计费
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 过滤掉 冷启动界面 从后台切回到前台时的计数统计
     * @return
     */
//    @Override
//    public boolean needStatistics(boolean isOnStartCall) {
//        return isOnStartCall ? mStartedCount < 1 : mStartedCount < 2;
//    }
}
