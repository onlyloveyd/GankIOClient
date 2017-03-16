package onlyloveyd.com.gankioclient.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import onlyloveyd.com.gankioclient.R;

public class WelcomeActivity extends AppCompatActivity {

    private final Handler splashHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            directToHome();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        TextView textViewSkip = (TextView) findViewById(R.id.tv_skip);
        textViewSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                splashHandler.removeMessages(0);
                directToHome();
            }
        });
        splashHandler.sendEmptyMessageDelayed(0, 3000);
    }

    private void directToHome() {
        Intent intent = new Intent();
        intent.setClass(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        //overridePendingTransition(R.anim.scalein, R.anim.scaleout);
        finish();
    }
}
