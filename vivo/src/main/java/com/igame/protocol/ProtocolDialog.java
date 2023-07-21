package com.igame.protocol;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.xmmy.fjmnfx.vivo.R;


/**
 * 用户协议弹出框，同意或取消时，保存同意用户协议标识
 */
public class ProtocolDialog extends ProtocolBaseDialog {
    private View contentView;
    private TextView content;
    private String str;
    private ProtocolDialogCallback callback;

    public interface ProtocolDialogCallback {
        void onOk();

        void onCancel();
    }

    /**
     * <默认构造函数>
     */
    public ProtocolDialog(Context context, String title, String content) {
        super(context, title, null);
        this.contentView = LayoutInflater.from(context).inflate(R.layout.protocol_dialog_content, null);
        str = content;
    }



    @Override
    public void cancel() {
        super.cancel();
    }

    @Override
    protected void addContentView() {
        addCenterView(contentView);
    }

    public void setCallback(ProtocolDialogCallback callback) {
        this.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置可以滑动
        content = contentView.findViewById(R.id.center_content);
        content.setMovementMethod(ScrollingMovementMethod.getInstance());
        content.setText(str);


        this.setOnclickListener(new BaseDialogClickListener() {

            @Override
            public void performConfirm(View v) {
                if (callback != null) {
                    callback.onOk();
                }
            }

            /** 不同意用户协议 */
            @Override
            public void performCancel(View v) {
                if (callback != null) {
                    callback.onCancel();
                }
            }
        });

    }
}
