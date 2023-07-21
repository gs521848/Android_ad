package com.igame.protocol;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

import com.njsr.mnhtfj.nearme.gamecenter.R;
import com.unity3d.start.DimenUtils;


/**
 * 统一风格 的Dialog。用于 Activity中弹出对话框
 */
public class ProtocolBaseDialog extends Dialog {
    private final static String TAG = "ProtocolBaseDialog";

    private TextView titleTv;
    protected Button confirmButton;
    protected Button cancelButton;
    protected BaseDialogClickListener onclickListener;
    protected LinearLayout contentLayout;
    protected Context mContext;
    private String tileContent;
    protected String content;
    protected String okStr;

    protected boolean onlyOk;

    private final static int centerLaoutLeftMarging = 20;
    private final static int centerLaoutTopMarging = 2;
    private final static int centerLaoutRightMarging = 20;
    private final static int centerLaoutBottomMarging = 4;

    /**
     * 对话框内容文字的透明度
     */
    private float contentAlpha = -1;

    private LayoutInflater inflater;

    public ProtocolBaseDialog(Context context, String tileContent, String content) {
        // 统一设置自定义Dialog样式
        super(context, R.style.dialog);
        mContext = context;
        this.tileContent = tileContent;
        this.content = content;
    }

    @Override
    public void cancel() {
        super.cancel();

    }

    public void setOnlyOk(boolean onlyOk) {
        this.onlyOk = onlyOk;
    }

    public void setOkStr(String okStr) {
        this.okStr = okStr;
    }

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window dialogWindow = getWindow();
        dialogWindow.requestFeature(Window.FEATURE_NO_TITLE);

        inflater = LayoutInflater.from(mContext);
        LinearLayout rootView = (LinearLayout) inflater.inflate(R.layout.dialog_protocol, null);
        setContentView(rootView);

        titleTv = (TextView) findViewById(R.id.uniform_dialog_title);
        titleTv.setText(tileContent);

        contentLayout = (LinearLayout) findViewById(R.id.center_layout);
        if (null == contentLayout) {
            return;
        }

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.weight = 50;
        params.setMargins(
                DimenUtils.dip2px(mContext, centerLaoutLeftMarging),
                DimenUtils.dip2px(mContext, centerLaoutTopMarging),
                DimenUtils.dip2px(mContext, centerLaoutRightMarging),
                DimenUtils.dip2px(mContext, centerLaoutBottomMarging));

        contentLayout.setLayoutParams(params);

        View bottom = (View) inflater.inflate(R.layout.protocol_base_dialog_bottom, null);
        params = new LayoutParams(LayoutParams.MATCH_PARENT, DimenUtils.dip2px(mContext, 40));
        params.weight = 1;
        params.setMargins(
                DimenUtils.dip2px(mContext, 15),
                DimenUtils.dip2px(mContext, 12),
                DimenUtils.dip2px(mContext, 15),
                DimenUtils.dip2px(mContext, 12));
        bottom.setLayoutParams(params);
        rootView.addView(bottom);

        confirmButton = (Button) rootView.findViewById(R.id.base_okBtn);
        cancelButton = (Button) rootView.findViewById(R.id.base_cancelBtn);

        if (!TextUtils.isEmpty(okStr)) {
            confirmButton.setText(okStr);
        }

        if (onlyOk) {
            cancelButton.setVisibility(View.GONE);
        }
        if (confirmButton != null) {
            confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (null != onclickListener) {
                        onclickListener.performConfirm(v);
                    }
                }
            });
        }

        if (cancelButton != null) {
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (null != onclickListener) {
                        onclickListener.performCancel(v);
                    }
                }
            });
        }
        addContentView();
    }

    /**
     * @param listener 对进行赋值
     */
    public void setOnclickListener(BaseDialogClickListener listener) {
        this.onclickListener = listener;
    }

    /**
     * 对外回调的接口
     */
    public interface BaseDialogClickListener {
        public void performConfirm(View v);

        public void performCancel(View v);
    }

    @SuppressLint("InflateParams")
    protected void addContentView() {
        View view = inflater.inflate(R.layout.protocol_dialog_content, null);
        TextView centerContent = (TextView) view.findViewById(R.id.center_content);
        centerContent.setText(content);
        centerContent.setPadding(0, 0, 0, DimenUtils.dip2px(mContext, 12));
        if (contentAlpha >= 0) {
            // 用来设置对话框内容的文字的透明度
            centerContent.setAlpha(contentAlpha);
        }
        contentLayout.addView(view);
    }

    protected void addCenterView(View view) {
        if (null != contentLayout && null != view) {
            contentLayout.addView(view);
        }
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            // 宽度设置为屏幕的0.9
            lp.width = (int) (DimenUtils.getScreenWidth(mContext) * 0.9);
            dialogWindow.setAttributes(lp);
        }
    }

    /**
     * 设置对话框标题 <功能详细描述>
     *
     * @param title
     * @see [类、类#方法、类#成员]
     */
    public void setTitle(String title) {
        if (titleTv != null) {
            titleTv.setText(title);
        } else {
            Log.d(TAG, "titleTv ==null");
        }
    }

    public void setCancelButtonText(String content) {
        if (cancelButton != null) {
            cancelButton.setText(content);
        }
    }

    public void setTitle(int titleResID) {
        if (titleTv != null) {
            titleTv.setText(titleResID);
        } else {
            Log.d(TAG, "titleTv ==null");
        }
    }

}