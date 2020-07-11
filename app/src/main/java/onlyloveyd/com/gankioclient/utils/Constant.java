
package onlyloveyd.com.gankioclient.utils;

import java.util.ArrayList;
import java.util.HashMap;

import onlyloveyd.com.gankioclient.R;

/**
 * 文 件 名: Constant
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：常量
 */
public class Constant {

    public static final long ONE_SECOND = 1000;
    public static final long ONE_MINUTE = ONE_SECOND * 60;
    public static final long ONE_HOUR = ONE_MINUTE * 60;
    public static final long ONE_DAY = ONE_HOUR * 24;

    public static final String BONUS = "福利";
    public static final String APP_NAME = "技术船";
    public static final String SUFFIX_JPEG = ".jpg";
    public static final String FIR_API_TOKEN = "3dc58a8e3aafb6a54a72c279b8584b36";
    public static final String APP_FIR_IM_URL = "https://fir.im/md5y";
    public static final String GITHUB_LATEST_APK =
            "https://github.com/onlyloveyd/GankIOClient/raw/master/latestversion/latestversion.apk";

    public static String[] sTabTitles = {"每日干货", "分类阅读", "匠心写作", "关于"};

    public static HashMap<String, Integer> sTypeColor = new HashMap<String, Integer>() {
        {
            put("Android", R.drawable.bg_android_tag);
            put("iOS", R.drawable.bg_ios_tag);
            put("瞎推荐", R.drawable.bg_rec_tag);
            put("拓展资源", R.drawable.bg_res_tag);
            put("App", R.drawable.bg_app_tag);
            put("福利", R.drawable.bg_bonus_tag);
            put("前端", R.drawable.bg_js_tag);
            put("休息视频", R.drawable.bg_video_tag);
        }
    };

    public static ArrayList<String> sCategoryList = new ArrayList<String>() {
        {
            add("all");
            add("Android");
            add("瞎推荐");
            add("iOS");
            add("前端");
            add("拓展资源");
            add("App");
            add("休息视频");
            add("福利");
        }
    };

    public static boolean sCategryListChanged = false;

    public static int YEAR = -1;
    public static int MONTH = -1;
    public static int DAY = -1;
}
