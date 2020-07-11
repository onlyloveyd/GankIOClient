
package onlyloveyd.com.gankioclient.activity;

import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.databinding.ActivityWebBinding;

/**
 * 文 件 名: WebActivity
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：内部网页显示Activity
 */
public class WebActivity extends AppCompatActivity {

    private String URL = null;

    private ActivityWebBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            URL = bundle.getString("URL");
        }

        setSupportActionBar(mBinding.tlWeb);
        mBinding.tlWeb.setNavigationIcon(R.drawable.back);
        mBinding.tlWeb.setTitleTextAppearance(this, R.style.ToolBarTextAppearance);
        initWebViewSettings();

        mBinding.wvContent.removeJavascriptInterface("searchBoxJavaBridge_");
        mBinding.wvContent.removeJavascriptInterface("accessibilityTraversal");
        mBinding.wvContent.removeJavascriptInterface("accessibility");
        mBinding.wvContent.loadUrl(URL);
    }

    @Override
    public void onPause() {
        super.onPause();
        mBinding.wvContent.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.wvContent.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding.wvContent.destroy();
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
                if (clipboardManager != null) {
                    clipboardManager.setText(URL);
                }
                Snackbar.make(mBinding.wvContent, "已复制到剪切板", Snackbar.LENGTH_SHORT).show();
            }
            break;
            case R.id.refresh: {
                mBinding.wvContent.reload();
            }
            break;
            default:
                break;
        }
        return true;
    }

    private void initWebViewSettings() {
        WebSettings settings = mBinding.wvContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        settings.setSavePassword(false);
        mBinding.wvContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mBinding.progressbar.setProgress(newProgress);
                if (newProgress == 100) {
                    mBinding.progressbar.setVisibility(View.GONE);
                } else {
                    mBinding.progressbar.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                setTitle(title);
            }
        });
        mBinding.wvContent.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) view.loadUrl(url);
                return true;
            }
        });
    }
}