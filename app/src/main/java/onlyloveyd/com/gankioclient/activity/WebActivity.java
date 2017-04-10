package onlyloveyd.com.gankioclient.activity;

import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;

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

        wvContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    progressbar.setVisibility(View.GONE);
                } else {
                    // 加载中
                    progressbar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

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
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this).addNextIntentWithParentStack(
                            upIntent).startActivities();
                } else {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    NavUtils.navigateUpTo(this, upIntent);
                }
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
            default:
                break;
        }
        return true;
    }

    private void initWebViewSettings() {
        WebSettings webSettings = wvContent.getSettings();

        //支持获取手势焦点，输入用户名、密码或其他
        wvContent.requestFocusFromTouch();
        webSettings.setJavaScriptEnabled(true);  //支持js
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);  //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setSupportZoom(true);  //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。
        //若上面是false，则该WebView不可缩放，这个不管设置什么都不能缩放。
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows();  //多窗口
        // webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        webSettings.setAllowFileAccess(true);  //设置可以访问文件
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }
}