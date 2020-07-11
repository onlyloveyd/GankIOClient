package onlyloveyd.com.gankioclient.utils;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;


public class RxPermissionUtils {
    private static RxPermissions mRxPermissions = null;

    public static void createInstance(Activity activity) {
        if (mRxPermissions == null) {
            mRxPermissions = new RxPermissions(activity);
        }
    }

    public static RxPermissions getInstance() {
        return mRxPermissions;
    }
}
