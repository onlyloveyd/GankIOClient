
package onlyloveyd.com.gankioclient.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

import static onlyloveyd.com.gankioclient.utils.Constant.ONE_DAY;
import static onlyloveyd.com.gankioclient.utils.Constant.ONE_HOUR;
import static onlyloveyd.com.gankioclient.utils.Constant.ONE_MINUTE;

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
                return String.format(Locale.CHINA, "%d分钟前", splitTime / ONE_MINUTE);
            }

            if (splitTime < ONE_DAY) {
                return String.format(Locale.CHINA, "%d小时前", splitTime / ONE_HOUR);
            }

            return String.format(Locale.CHINA, "%d天前", splitTime / ONE_DAY);
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
     */
    public static void hide_keyboard_from(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * show keyboard
     */
    public static void show_keyboard_from(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(
                Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 保存Bitmap为图片
     */
    public static void saveBitmap(Bitmap bitmap, String picPath) throws Exception {
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
            throw new FileNotFoundException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            bitmap.recycle();
            throw new IOException();
        }

    }
}
