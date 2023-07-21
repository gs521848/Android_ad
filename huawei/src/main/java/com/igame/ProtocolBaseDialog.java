package com.igame;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.glgame.wjfjzc.huawei.R;

public class ProtocolBaseDialog extends Dialog {
    public interface BaseDialogClickListener {
        void performCancel(View arg1);

        void performConfirm(View arg1);
    }
    public interface ICloseDlgListener {
        void onCloseDlg();
    }


       public enum  ButtonType { CANCEL,CONFIRM}


    private static final String TAG = "ProtocolBaseDialog";
    protected Button cancelButton;
    private static final int centerLaoutBottomMarging = 4;
    private static final int centerLaoutLeftMarging = 20;
    private static final int centerLaoutRightMarging = 20;
    private static final int centerLaoutTopMarging = 2;
    protected Button confirmButton;
    protected String content;
    private float contentAlpha;
    protected LinearLayout contentLayout;
    private ICloseDlgListener iCloseDlgListener;
    private LayoutInflater inflater;
    protected Context mContext;
    protected BaseDialogClickListener onclickListener;
    private String tileContent;
    private TextView titleTv;

    public ProtocolBaseDialog(Context context, String title, String content) {
        super(context, R.style.dialog);
        this.iCloseDlgListener = null;
        this.contentAlpha = -1f;
        this.mContext = context;
        this.tileContent = title;
        this.content = content;
    }

    protected void addCenterView(View arg2) {
        LinearLayout v0 = this.contentLayout;
        if(v0 != null && arg2 != null) {
            v0.addView(arg2);
        }
    }

    @SuppressLint(value={"InflateParams"}) protected void addContentView() {
        View rootView = this.inflater.inflate(R.layout.layout_protocol_content, null);
        View view = rootView.findViewById(R.id.center_content);
        ((TextView)view).setText(this.content);
        ((TextView)view).setPadding(0, 0, 0, ((int)DimenUtils.dip2px(this.mContext, 12)));
        float v2 = this.contentAlpha;
        if(v2 >= 0f) {
            ((TextView)view).setAlpha(v2);
        }

        this.contentLayout.addView(rootView);
    }

    public void cancel() {
        super.cancel();
        ICloseDlgListener v0 = this.iCloseDlgListener;
        if(v0 != null) {
            v0.onCloseDlg();
        }
    }

    public void onContentChanged() {
        super.onContentChanged();
        Window v0 = this.getWindow();
        if(v0 != null) {
            WindowManager.LayoutParams v1 = v0.getAttributes();
            double v2 = ((double)DimenUtils.getScreenWidth(this.mContext));
            Double.isNaN(v2);
            v1.width = ((int)(v2 * 0.9));
            v0.setAttributes(v1);
        }
    }

    @SuppressLint(value={"InflateParams"}) protected void onCreate(Bundle arg9) {
        super.onCreate(arg9);
        this.getWindow().requestFeature(1);
        this.inflater = LayoutInflater.from(this.mContext);
        ViewGroup v0 = null;
        View rootView = this.inflater.inflate(R.layout.dialog_protocol, v0);
        this.setContentView(rootView);
        this.titleTv = this.findViewById(R.id.uniform_dialog_title);
        this.titleTv.setText(this.tileContent);
        this.contentLayout = this.findViewById(R.id.center_layout);
        if(this.contentLayout == null) {
            return;
        }


        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.weight = 50f;
        layoutParams.setMargins(((int)DimenUtils.dip2px(this.mContext, 20)), ((int)DimenUtils.dip2px(this.mContext, 2)), ((int)DimenUtils.dip2px(this.mContext, 20)), ((int)DimenUtils.dip2px(this.mContext, 4)));
        this.contentLayout.setLayoutParams(((ViewGroup.LayoutParams)layoutParams));
        View v0_1 = this.inflater.inflate(R.layout.layout_protocol_bottom, v0);
        layoutParams = new TableLayout.LayoutParams(-1, ((int)DimenUtils.dip2px(this.mContext, 40)));
        layoutParams.weight = 1f;
        layoutParams.setMargins(((int)DimenUtils.dip2px(this.mContext, 15)), ((int)DimenUtils.dip2px(this.mContext, 12)), ((int)DimenUtils.dip2px(this.mContext, 15)), ((int)DimenUtils.dip2px(this.mContext, 12)));
        v0_1.setLayoutParams(((ViewGroup.LayoutParams)layoutParams));
        ((LinearLayout)rootView).addView(v0_1);
        this.confirmButton = ((LinearLayout)rootView).findViewById(R.id.base_okBtn);
        this.cancelButton = ((LinearLayout)rootView).findViewById(R.id.base_cancelBtn);
        Button confirmButton = this.confirmButton;
        if(confirmButton != null) {
            confirmButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg2) {
                    ProtocolBaseDialog.this.dismiss();
                    if(ProtocolBaseDialog.this.onclickListener != null) {
                        ProtocolBaseDialog.this.onclickListener.performConfirm(arg2);
                    }
                }
            });
        }

        confirmButton = this.cancelButton;
        if(confirmButton != null) {
            confirmButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg2) {
                    ProtocolBaseDialog.this.dismiss();
                    if(ProtocolBaseDialog.this.onclickListener != null) {
                        ProtocolBaseDialog.this.onclickListener.performCancel(arg2);
                    }
                }
            });
        }

        this.addContentView();
    }

    public void setICloseDlgListener(ICloseDlgListener arg1) {
        this.iCloseDlgListener = arg1;
    }

    public void setOnclickListener(BaseDialogClickListener arg1) {
        this.onclickListener = arg1;
    }

    public void setTitle(int arg2) {
        TextView v0 = this.titleTv;
        if(v0 != null) {
            v0.setText(arg2);
        }
        else {
            Log.d("ProtocolBaseDialog", "titleTv ==null");
        }
    }

    public void setTitle(String arg2) {
        TextView v0 = this.titleTv;
        if(v0 != null) {
            v0.setText(((CharSequence)arg2));
        }
        else {
            Log.d("ProtocolBaseDialog", "titleTv ==null");
        }
    }
}
