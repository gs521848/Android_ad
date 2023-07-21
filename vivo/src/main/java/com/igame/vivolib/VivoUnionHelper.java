package com.igame.vivolib;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.vivo.unionsdk.open.MissOrderEventHandler;
import com.vivo.unionsdk.open.VivoPayCallback;
import com.vivo.unionsdk.open.VivoPayInfo;
import com.vivo.unionsdk.open.VivoRoleInfo;
import com.vivo.unionsdk.open.VivoUnionSDK;

import java.util.ArrayList;
import java.util.List;


public class VivoUnionHelper {
    private static final String TAG = "VivoUnionHelper";
    private static final String APP_ID = "296c919deb7010470435fc39a7658e34";

    /**
     * 初始化sdk
     *
     * @param context
     * @param debug
     */
    public static void initSdk(Context context, boolean debug) {
        VivoUnionSDK.initSdk(context, APP_ID, debug);
    }

    /**
     * 查询订单状态
     *
     * @param openid 帐号唯一标识，登录后获取
     */
    public static void queryMissOrderResult(String openid) {
        VivoUnionSDK.queryMissOrderResult(openid);
    }

    /**
     * 登录成功后上报角色信息
     *
     * @param vivoRoleInfo 角色信息
     */
    public static void reportRoleInfo(VivoRoleInfo vivoRoleInfo) {
        VivoUnionSDK.reportRoleInfo(vivoRoleInfo);
    }

    /**
     * 查询是否有调单
     *
     * @param missOrderEventHandler
     */
    public static void registerMissOrderEventHandler(final Context context, MissOrderEventHandler missOrderEventHandler) {
        VivoUnionSDK.registerMissOrderEventHandler(context, missOrderEventHandler);
    }


    /**
     * 直接吊起微信与支付宝
     *
     * @param activity
     * @param mVivoPayCallback 支付结果回调
     * @param code             1表示微信，2表示支付宝
     */
    public static void payNowV2(Activity activity, VivoPayInfo vivoPayInfo, VivoPayCallback mVivoPayCallback, int code) {
        VivoUnionSDK.payNowV2(activity, vivoPayInfo, mVivoPayCallback, code);
    }

    /**
     * 吊起支付面板
     *
     * @param activity
     * @param mVivoPayCallback 支付结果回调
     */
    public static void payV2(Activity activity, VivoPayInfo vivoPayInfo, VivoPayCallback mVivoPayCallback) {
        VivoUnionSDK.payV2(activity, vivoPayInfo, mVivoPayCallback);
    }


    /**
     * 登录
     *
     * @param activity
     */
    public static void login(Activity activity) {
        VivoUnionSDK.login(activity);
    }

    /**
     * 游戏方发放商品成功主动通知联运
     *
     * @param list  vivo订单号列表
     */
    public static void reportOrderComplete(List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        VivoUnionSDK.reportOrderComplete(list, true);
    }

    public static void reportOrderComplete(String transNo) {
        if (TextUtils.isEmpty(transNo)) {
            return;
        }
        List<String> list = new ArrayList<>();
        list.add(transNo);
        VivoUnionSDK.reportOrderComplete(list, false);
    }

    public static void reportOrderComplete(String transNo, boolean b) {
        if (TextUtils.isEmpty(transNo)) {
            return;
        }
        List<String> list = new ArrayList<>();
        list.add(transNo);
        VivoUnionSDK.reportOrderComplete(list, b);
    }

//


}
