package player;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

public class UiHelper {
    public static final String TAG = "UiHelper";

    /**
     * 获取屏幕尺寸
     *
     * @param context 上下文
     * @return 屏幕尺寸
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (null != context) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (null != windowManager) {
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            }
        }
        return displayMetrics;
    }

    /**
     * 获取当前上下文的屏幕宽度
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        return displayMetrics.widthPixels;
    }

    /**
     * <一句话功能简述>
     * dp转换为px
     *
     * @param context
     * @param dp
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static float dp2px(Context context, int dp) {
        return dp * getDisplayMetrics(context).density;
    }

    /**
     * 是否是横屏
     *
     * @param context
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static boolean isLandscape(Context context) {
        if (null == context) {
            return true;
        }
        android.content.res.Configuration config = context.getResources().getConfiguration();
        if (config.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
            Log.i(TAG, "landscape");
            return true;
        }
        Log.i(TAG, "portrait");
        return false;
    }

//    /**
//     * 加载图片
//     */
//    public static void loadImg(ImageInfo imgInfo, ImageView image) {
//        try {
//            String url = imgInfo.getUrl();
//            Context context = image.getContext();
//
//            RequestOptions options = new RequestOptions()
//                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
//
//            Glide.with(context)
//                    .load(url)
//                    .apply(options)
//                    .listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            Log.i(TAG, "onLoadFailed");
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            Log.i(TAG, "onResourceReady");
//                            return false;
//                        }
//                    })
//                    .into(image);
//        } catch (Exception e) {
//            Log.e(TAG, "loadImg Exception", e);
//        }
//    }

}
