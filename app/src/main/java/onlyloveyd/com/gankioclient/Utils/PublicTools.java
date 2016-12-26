package onlyloveyd.com.gankioclient.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telecom.ConnectionService;

/**
 * Created by lisa on 2016/12/22.
 * Email: 457420045@qq.com
 */

public class PublicTools {
    public static final String KEY_BUNDLE_CATEGORY="CATEGORY";
    public static final String KEY_BUNDLE_URL="URL";
    public static final String BONUS = "福利";
    public static final String ABOUT = "关于";

    /**
     * whether network is available
     * @param context
     * @return true network is available
     *         false network is not available
     */
    public static boolean isNetWorkAvailable(Context context) {
        if(context!= null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }


}
