/**
 * Copyright 2017 yidong
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package onlyloveyd.com.gankioclient.utils;

import static onlyloveyd.com.gankioclient.utils.Constant.ONE_DAY;
import static onlyloveyd.com.gankioclient.utils.Constant.ONE_HOUR;
import static onlyloveyd.com.gankioclient.utils.Constant.ONE_MINUTE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import onlyloveyd.com.gankioclient.activity.WebActivity;

/**
 * 文 件 名: PublicTools
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：工具方法类
 */
public class PublicTools {

    /**
     * whether network is available
     *
     * @return true network is available
     * false network is not available
     */
    public static boolean isNetWorkAvailable(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 获取目标时间和当前时间之间的差距
     *
     * @param date date
     * @return String
     */
    public static String getTimestampString(Date date) {
        Date curDate = new Date();
        long splitTime = curDate.getTime() - date.getTime();
        if (splitTime < (30 * ONE_DAY)) {
            if (splitTime < ONE_MINUTE) {
                return "刚刚";
            }
            if (splitTime < ONE_HOUR) {
                return String.format("%d分钟前", splitTime / ONE_MINUTE);
            }

            if (splitTime < ONE_DAY) {
                return String.format("%d小时前", splitTime / ONE_HOUR);
            }

            return String.format("%d天前", splitTime / ONE_DAY);
        }
        String result;
        result = "M月d日 HH:mm";
        return (new SimpleDateFormat(result, Locale.CHINA)).format(date);
    }

    /**
     * Date（long） 转换 String
     *
     * @param time   time
     * @param format format
     * @return String
     */
    public static String date2String(long time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(time);
    }

    /**
     * start WebActivity
     */
    public static void startWebActivity(Context context, String url) {
        context.startActivity(getWebIntent(context, url));
    }

    /**
     * get intent by url
     */
    public static Intent getWebIntent(Context context, String url) {
        Intent intent = new Intent();
        intent.setClass(context, WebActivity.class);
        intent.putExtra("URL", url);
        return intent;
    }

    /**
     * hide keyboard
     * @param context
     * @param view
     */
    public static void hide_keyboard_from(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * show keyboard
     * @param context
     * @param view
     */
    public static void show_keyboard_from(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 保存Bitmap为图片
     * @param bitmap
     * @param picPath
     */
    public static void saveBitmap(Bitmap bitmap, String picPath) {
        File f = new File(picPath + Constant.SUFFIX_JPEG);
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            bitmap.recycle();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bitmap.recycle();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bitmap.recycle();
        }

    }
}
