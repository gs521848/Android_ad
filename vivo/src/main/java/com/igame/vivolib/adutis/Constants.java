package com.igame.vivolib.adutis;

/**
 * @author 11101035
 */
public final class Constants {

    public static String AD_SPLASH_KEY = "1c667ee82e9a425a8118313fef72d8c3";
    public interface IntentKey {
        String POSITION_ID = "position_id";
    }

    public interface ConfigureKey {
        String SERVER_URL = "server_url";
        String MEDIA_ID = "media_id";
        String SPLASH_POSITION_ID = "splash_position_id";
        String BANNER_POSITION_ID = "banner_position_id";
        String INTERSTITIAL_POSITION_ID = "interstitial_position_id";
        String VIDEO_INTERSTITIAL_POSITION_ID = "interstitial_video_position_id";
        String NATIVE_POSITION_ID = "native_position_id";
        String NATIVE_STREAM_POSITION_ID = "native_stream_position_id";
        String VIDEO_POSITION_ID = "video_position_id";
        String SPLASH_AD_TIME = "splash_ad_time";
        String BANNER_AD_TIME = "banner_ad_time";
        String APP_TITLE = "app_title";
        String APP_DESC = "app_desc";
        String HOT_SPLASH = "hot_splash";
        String FLOAT_ICON = "float_icon";
    }


    public interface DefaultConfigValue {
        /**
         * 测试用，接入方置空即可
         */
        String SERVER_URL = "http://10.101.19.148";
        /**
         * 以下ID需填入自己在广告后台申请的相关id
         */
        String MEDIA_ID = "fc6520303ecc4cd18d4f9481251cbcbf";
        String SPLASH_POSITION_ID = "1c667ee82e9a425a8118313fef72d8c3";
        String BANNER_POSITION_ID = "9e35cfb66f0d4a9cbd0243196d46db45";
        String INTERSTITIAL_POSITION_ID = "747f550e01eb4b87878264f293bfefe5";
        String VIDEO_INTERSTITIAL_POSITION_ID = "4b8329e4004d4e9dbb457d979225d2de";
        String NATIVE_STREAM_POSITION_ID = "f3762d223d0b4d6c9e204c7eb8069746";
        String VIDEO_POSITION_ID = "8c2303d1bdfc4994a7489cd24bc27b42";
        String FLOAT_ICON = "9954870b8b2f4d729ccaffab31241ad7";
        /**
         * 原生模板化-横板纯图
         **/
        String NATIVE_EXPRESS_MATERIAL_ID = "359f63373ba34df89d569e7090718d04";

        /**
         * 原生模板化-三图模板
         **/
        String NATIVE_EXPRESS_MATERIAL_GROUP_ID = "47adb3b4b4b541f1a8b6c3ea587ff2c8";

        /**
         * 原生模板化-左文右图
         **/
        String NATIVE_EXPRESS_MATERIAL_RIGHT_ID = "70d40dfdb7944ad7a0e827487d50ab9c";

        /**
         * 原生模板化-左图右文
         **/
        String NATIVE_EXPRESS_MATERIAL_LEFT_ID = "233d61e29feb43b3ab957c39cc88879a";

        /**
         * 原生模板化-上图下文
         **/
        String NATIVE_EXPRESS_MATERIAL_TOP_ID = "9802782c25904370b6ebd51954088422";

        /**
         * 原生模板化-上文下图
         **/
        String NATIVE_EXPRESS_MATERIAL_BOTTOM_ID = "389db372d5314f99b54addd351b394f8";

        int SPLASH_AD_TIME = 3;
        int BANNER_AD_TIME = 15;
        String APP_TITLE = "开心消消乐";
        String APP_DESC = "娱乐休闲首选游戏";

        int HOT_SPLASH = 1; //-1: 关、1:竖屏、2:横屏
    }
}
