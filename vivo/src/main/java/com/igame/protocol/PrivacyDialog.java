package com.igame.protocol;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ScaleXSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xmmy.fjmnfx.vivo.R;


public class PrivacyDialog extends ProtocolBaseDialog {

    public interface ProtocolDialogCallback {
        void onOk();

        void onCancel();
    }

    private View contentView;
    private ProtocolDialogCallback callback;

    public PrivacyDialog(Context context, String tileContent) {
        super(context, tileContent, null);

        this.contentView = LayoutInflater.from(context).inflate(R.layout.protocol_dialog_content, null);
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


        TextView textView = contentView.findViewById(R.id.center_content);

        String text=getContext().getString(R.string.privacy_dialog_content);
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
//        String find1="服务协议";
//        int start1=text.indexOf(find1);
//        int end1=start1+find1.length();
//        spannable.setSpan(new ForegroundColorSpan(Color.BLUE), start1, end1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //这个一定要记得设置，不然点击不生效textView.setMovementMethod(LinkMovementMethod.getInstance());
//        spannable.setSpan(new TextClick(), start1, end1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        String find2="《隐私政策》";
        int start2=text.indexOf(find2);
        int end2=start2+find2.length();


        spannable.setSpan(new ScaleXSpan(1.5f), start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(Color.BLUE), start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE); //这个一定要记得设置，不然点击不生效textView.setMovementMethod(LinkMovementMethod.getInstance());
        spannable.setSpan(new TextClick2(), start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
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


    private class TextClick extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            privacy_usage();
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
//            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }

    private class TextClick2 extends ClickableSpan {
        @Override
        public void onClick(View widget) {
            privacy_policy();
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
//            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }

    public void privacy_usage() {
//        ProtocolDialog dialog = new ProtocolDialog(getContext(),
//                getContext().getString(R.string.game_frg_privacy_content),
//                getContext().getString(R.string.privacy_usage_license3));
//        dialog.setOnlyOk(true);
//        dialog.show();
    }

    public void privacy_policy()
    {
        ProtocolDialog dialog = new ProtocolDialog(getContext(), "隐私政策",getContext().getString(R.string.protocol_content));
        dialog.setOnlyOk(true);
        dialog.setOkStr("返回");
        dialog.show();

    }
}
