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
package onlyloveyd.com.gankioclient.activity;

import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;

/**
 * 文 件 名: WebActivity
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：内部网页显示Activity
 */
public class WebActivity extends AppCompatActivity {

    @BindView(R.id.tl_web)
    Toolbar tlWeb;
    @BindView(R.id.wv_content)
    WebView wvContent;
    @BindView(R.id.activity_web)
    LinearLayout activityWeb;
    @BindView(R.id.progressbar)
    ProgressBar progressbar;

    private String URL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            URL = bundle.getString("URL");
        }

        setSupportActionBar(tlWeb);
        tlWeb.setNavigationIcon(R.drawable.back);
        tlWeb.setTitleTextAppearance(this, R.style.ToolBarTextAppearance);
        initWebViewSettings();

        wvContent.removeJavascriptInterface("searchBoxJavaBridge_");
        wvContent.removeJavascriptInterface("accessibilityTraversal");
        wvContent.removeJavascriptInterface("accessibility");
        wvContent.loadUrl(URL);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (wvContent != null) wvContent.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wvContent != null) wvContent.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (wvContent != null) wvContent.destroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
            }
            break;
            case R.id.share: {//share url with system share windows
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, URL);
                startActivity(Intent.createChooser(intent, getTitle()));
            }
            break;
            case R.id.openinbrowse: {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(URL));
                startActivity(intent);
            }
            break;
            case R.id.copyurl: {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(
                        CLIPBOARD_SERVICE);
                clipboardManager.setText(URL);
                Snackbar.make(tlWeb, "已复制到剪切板", Snackbar.LENGTH_SHORT).show();
            }
            break;
            case R.id.refresh: {
                wvContent.reload();
            }
            break;
            default:
                break;
        }
        return true;
    }

    private void initWebViewSettings() {
        WebSettings settings = wvContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        settings.setSavePassword(false);
        wvContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressbar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressbar.setVisibility(View.GONE);
                } else {
                    progressbar.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
        wvContent.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) view.loadUrl(url);
                return true;
            }
        });
    }
}