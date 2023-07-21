package player.protocol;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.xmmy.fjmnfx.vivo.R;

import player.SharedInfoService;

//import com.unity3d.player.R;
//import com.unity3d.player.SharedInfoService;

//import com.huawei.openalliance.ad.inter.HiAd;


/**
 * 用户协议弹出框，同意或取消时，保存同意用户协议标识
 */
public class ProtocolDialog extends ProtocolBaseDialog {
    private View contentView;

    private ProtocalDialogCallback mCallback;

    public interface ProtocalDialogCallback {
        void okCallback(boolean showAd);

        void cancelCallback();
    }

    /**
     * <默认构造函数>
     */
    public ProtocolDialog(Context context, String title, View contentView) {
        super(context, title, null);
        this.contentView = contentView;
    }

    @Override
    public void cancel() {
        super.cancel();
    }

    @Override
    protected void addContentView() {
        addCenterView(contentView);
    }

    public void setCallback(ProtocalDialogCallback mCallback) {
        this.mCallback = mCallback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置可以滑动
        TextView tips = (TextView) contentView.findViewById(R.id.center_content);
        tips.setMovementMethod(ScrollingMovementMethod.getInstance());

//        TextView agreeTip = (TextView) contentView.findViewById(R.id.agree_tip);
//        agreeTip.setText(Html.fromHtml(mContext.getString(R.string.agree_tip)));

//        final CheckBox notPromptBox = (CheckBox) contentView.findViewById(R.id.prompt_check_box);
        this.setOnclickListener(new BaseDialogClickListener() {

            /** 同意用户协议 */
            @Override
            public void performConfirm(View v) {
                //HiAd.getInstance(mContext).enableUserInfo(true);  // TODO 保存同意用户协议标识

//                boolean agree = notPromptBox.isChecked() ? true : false;
                SharedInfoService shareInfoService = SharedInfoService.getInstance(mContext);
                shareInfoService.setIsAgreeProtocl(true);
                if (mCallback != null) {
                    // 有协议框处理不显示广告
                    mCallback.okCallback(false);
                }
            }

            /** 不同意用户协议 */
            @Override
            public void performCancel(View v) {
                //HiAd.getInstance(mContext).enableUserInfo(false); // TODO 保存同意用户协议标识

                if (mCallback != null) {
                    mCallback.cancelCallback();
                }
            }
        });
    }
}
