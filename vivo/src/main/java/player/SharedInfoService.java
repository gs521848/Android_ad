package player;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedInfoService {
    private static final String PROTOCL = "protocl";
    private SharedPreferences mPreferences;

    private static SharedInfoService util;
    public static synchronized SharedInfoService getInstance(Context context) {
        if (util == null) {
            util = new SharedInfoService(context);
        }
        return util;
    }

    private SharedInfoService(Context context) {
        mPreferences = context.getSharedPreferences("HiAdDemoSharedPreferences", Context.MODE_PRIVATE);
    }

    /**
     * 保存同意用户协议标识
     * @param agree
     */
    public void setIsAgreeProtocl(boolean agree) {
        Editor editor = mPreferences.edit();
        editor.putBoolean(PROTOCL, agree).commit();
    }

    /**
     * 获取同意用户协议标识
     * @return true：已同意，false：未同意
     */
    public boolean getIsAgreeProtocol() {
        return mPreferences.getBoolean(PROTOCL, false);
    }
}
