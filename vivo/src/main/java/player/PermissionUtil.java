package player;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class PermissionUtil {
    /**
     * 应用刚启动时请求申请权限
     */
    public static final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS_INIT = 0;

    /**
     * 定义需要使用的动态权限
     */
    private static final String[] PERMISSIONS_ARRAY = {Manifest.permission.READ_PHONE_STATE};
    public static final List<String> PERMISSIONS = Collections.unmodifiableList(Arrays.asList(PERMISSIONS_ARRAY));

    /**
     * 判断权限是否都已经授予，如果没有授予则进行请求权限
     *
     * @param activity    当前 Activity
     * @param permissions 权限列表
     */
    @TargetApi(23)
    public static String[] getDenyPermissions(Activity activity, List<String> permissions) {
        if (null == activity || 0 == permissions.size() || !greaterThanM())
            return null;

        List<String> denyList = new ArrayList<String>();
        for (String p : permissions) {
            if (PackageManager.PERMISSION_DENIED == activity.checkSelfPermission(p)) {
                denyList.add(p);
            }
        }

        if (denyList.isEmpty()) {
            return null;
        } else {
            return denyList.toArray(new String[denyList.size()]);
        }
    }

    /**
     * 判断权限是否都已经授予
     *
     * @param activity    当前 Activity
     * @param permissions 待校验的权限列表
     * @return true : 所有权限都被被允许
     * <br>
     * false: 有权限被禁止
     */
    @TargetApi(23)
    public static boolean verifyPermissions(Activity activity, List<String> permissions) {
        // 参数不正确，返回权限不足
        if (null == activity) {
            return false;
        }

        // 没有权限返回，则认为是都允许；低于6.0版本的自动拥有所有权限
        if (0 == permissions.size() || !greaterThanM()) {
            return true;
        }

        for (String p : permissions) {
            if (PackageManager.PERMISSION_DENIED == activity.checkSelfPermission(p)) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(23)
    public static void requestPermissions(Activity activity, String[] permissions, int requestCode) {
        if (greaterThanM()) {
            activity.requestPermissions(permissions, requestCode);
        }
    }

    private static boolean greaterThanM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}
