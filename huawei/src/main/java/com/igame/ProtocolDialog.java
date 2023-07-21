package com.igame;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.glgame.wjfjzc.huawei.R;

public class ProtocolDialog extends ProtocolBaseDialog {

    public interface ProtocalDialogCallback {
        void cancelCallback();

        void okCallback(boolean arg1);
    }

    private View contentView;
    private ProtocalDialogCallback mCallback;

    public ProtocolDialog(Context arg2, String arg3, View arg4) {
        super(arg2, arg3, null);
        this.contentView = arg4;
    }



    protected void addContentView() {
        this.addCenterView(this.contentView);
    }

    public void cancel() {
        super.cancel();
    }

    protected void onCreate(Bundle arg2) {
        super.onCreate(arg2);
        ((TextView)this.contentView.findViewById(R.id.center_content)).setMovementMethod(ScrollingMovementMethod.getInstance());
        this.setOnclickListener(new BaseDialogClickListener() {
            public void performCancel(View arg2) {
                //HiAd.getInstance(ProtocolDialog.this.mContext).enableUserInfo(false);
                if(ProtocolDialog.this.mCallback != null) {
                    ProtocolDialog.this.mCallback.cancelCallback();
                }
            }

            public void performConfirm(View arg2) {
                //HiAd.getInstance(ProtocolDialog.this.mContext).enableUserInfo(true);

                if(ProtocolDialog.this.mCallback != null) {
                    ProtocolDialog.this.mCallback.okCallback(false);
                }
            }
        });
    }

    public void setCallback(ProtocalDialogCallback arg1) {
        this.mCallback = arg1;
    }
}
