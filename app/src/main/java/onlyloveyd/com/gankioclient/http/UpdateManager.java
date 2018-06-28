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
package onlyloveyd.com.gankioclient.http;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.utils.RxPermissionUtils;

/**
 * 文 件 名: UpdateManager
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：应用更新
 */
public class UpdateManager {
    private static final int DOWNLOAD = 1;  // 下载中
    private static final int DOWNLOAD_FINISH = 2;   // 下载结束
    private Context mContext;
    private String mSavePath;  // 下载保存路径
    private int progress;      // 记录进度条数量
    private boolean cancelUpdate = false;   // 是否取消更新
    private ProgressBar mProgress;            // 更新进度条
    private Dialog mDownloadDialog;          // 下载对话框
    private String apkName;                   // 程序名
    private String downUrl;                   // 程序下载地址

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD:  // 正在下载
                    mProgress.setProgress(progress);  // 设置进度条位置
                    break;
                case DOWNLOAD_FINISH:
                    installApk();  // 安装文件
                    break;
                default:
                    break;
            }
        }
    };

    public UpdateManager(Context context) {
        this.mContext = context;
    }

    public void setDownUrl(String url) {
        downUrl = url;
    }

    public void setApkName(String name) {
        apkName = name;
    }


    /**
     * 显示软件下载对话框
     */
    public void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("正在更新...");

        // 给下载对话框增加进度条
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.dialog_update_progress, null);
        mProgress = v.findViewById(R.id.update_progress);
        builder.setView(v);
        // 取消更新
        builder.setNegativeButton("取消更新", (dialog, which) -> {
            dialog.dismiss();
            cancelUpdate = true;
        });
        builder.setCancelable(false);
        mDownloadDialog = builder.create();
        mDownloadDialog.show();

        downloadApk();   // 下载文件文件
    }

    /**
     * 下载apk文件
     */
    private void downloadApk() {
        RxPermissions rxPermissions = RxPermissionUtils.getInstance();
        if (rxPermissions != null) {
            // 启动新线程下载软件
            rxPermissions.request(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Observer<Boolean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Boolean value) {
                            if (value) {
                                new downloadApkThread().start();
                            } else {
                                Toast.makeText(mContext, "请在设置中开启存储权限后再试",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }
    }

    /**
     * 安装APK文件
     */
    private void installApk() {
        final String authority = "onlyloveyd.com.gankioclient.fileprovider";
        boolean b = Build.VERSION.SDK_INT >= 24;
        File file = new File(Environment.getExternalStorageDirectory().getPath());
        Uri uri = b
                ? FileProvider.getUriForFile(mContext, authority, file)
                : Uri.fromFile(file);


        File apkfile = new File(mSavePath, apkName);
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(uri,
                "application/vnd.android.package-archive");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(i);
    }

    /**
     * 下载文件线程
     *
     * @author coolszy
     * @date 2012-4-26
     * @blog http://blog.92coding.com
     */
    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {
                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    // 获得存储卡的路径
                    String sdpath = Environment.getExternalStorageDirectory() + "/";
                    mSavePath = sdpath + "download";
                    URL url = new URL(downUrl);

                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    File apkFile = new File(mSavePath, apkName);
                    if (!apkFile.exists()) {
                        apkFile.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOAD);
                        if (numread <= 0) {
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);  // 下载完成
                            break;
                        }
                        fos.write(buf, 0, numread);  // 写入文件
                    } while (!cancelUpdate);// 点击取消就停止下载.
                    fos.close();
                    is.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mDownloadDialog.dismiss();  // 取消下载对话框显示
        }
    }
}
