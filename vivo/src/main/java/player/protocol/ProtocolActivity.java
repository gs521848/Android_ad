package player.protocol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;


import com.igame.protocol.PrivacyDialog;
import com.xmmy.fjmnfx.vivo.R;

import player.PermissionUtil;
import player.SharedInfoService;
import player.SplashActivity;


/**
 * 用户协议和动态权限界面。
 * 获取广告之前需要同意用户协议和授权READ_PHONE_STATE权限
 */
public class ProtocolActivity extends Activity implements ProtocolDialog.ProtocalDialogCallback, ICloseDlgListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProtocol();
    }

    private void initProtocol() {
        // 是否同意用户协议
        SharedInfoService shareInfoService = SharedInfoService.getInstance(this);

        // 未同意用户协议，则显示用户协议弹框
        if (!shareInfoService.getIsAgreeProtocol()) {
            //展示用户协议
            //showProtocol();
            showPrivacy(this);
        } else {
            //进入开屏
            enterSplash();
        }
    }

    /**
     * 显示协议框
     */
    private void showProtocol() {
        LayoutInflater inflate = LayoutInflater.from(this);
        View view = inflate.inflate(R.layout.protocol_dialog_content, null);

        // 显示用户协议弹框
        ProtocolDialog dialog = new ProtocolDialog(this, "隐私政策", view);
        dialog.setCallback(this);
        dialog.setICloseDlgListener(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void showPrivacy(Activity activity){
        PrivacyDialog dialog = new PrivacyDialog(activity, "用户须知");
        dialog.setCallback(new PrivacyDialog.ProtocolDialogCallback() {
            @Override
            public void onOk() {
                enterSplash();
                SharedInfoService shareInfoService = SharedInfoService.getInstance(ProtocolActivity.this);
                shareInfoService.setIsAgreeProtocl(true);
            }
            @Override
            public void onCancel() {
                ProtocolActivity.this.finish();
                System.exit(0);
            }
        });
        dialog.setOkStr("同意");
        dialog.setCancelButtonText("拒绝");
        dialog.show();
        dialog.setCancelButtonText("拒绝");
        dialog.setCancelable(false);
    }

    /**
     * 同意用户协议，重新进入开屏界面
     */
    private void enterSplash() {
        JumpToNextActivity();
        this.finish();
    }

    @Override
    public void okCallback(boolean showAd) {
        // 同意用户协议后，继续动态权限弹框
        //hanldeRuntimePermission();
        enterSplash();
    }

    @Override
    public void cancelCallback() {
        finish();
    }

    @Override
    public void onCloseDlg() {
        finish();
    }

    private void hanldeRuntimePermission() {
        // PPS需要采集IMEI，申请READ_PHONE_STATE权限
        if (PermissionUtil.verifyPermissions(this, PermissionUtil.PERMISSIONS)) {
            initProtocol();
        } else {
            String[] pl = PermissionUtil.getDenyPermissions(this, PermissionUtil.PERMISSIONS);
            if (null == pl || 0 == pl.length) {
                finish();
            } else {
                PermissionUtil.requestPermissions(this, pl, PermissionUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS_INIT);
            }
        }
    }

    // 处理权限回调结果
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PermissionUtil.REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS_INIT: {
                JumpToNextActivity();
                break;
            }
            default: {
                break;
            }
        }
        finish();
    }

    private void JumpToNextActivity()
    {
        //TODO 跳转到开屏
        Log.e("xxx","跳转到开屏页面");
        startActivity(new Intent(this, SplashActivity.class));
//        startActivity(new Intent(ProtocolActivity.this, UnityPlayerActivity.class));
    }

}
