package onlyloveyd.com.gankioclient.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.fragment.AboutFragment;
import onlyloveyd.com.gankioclient.fragment.BonusFragment;
import onlyloveyd.com.gankioclient.fragment.GankDetailsFragment;
import onlyloveyd.com.gankioclient.utils.PublicTools;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //view bind
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_main)
    RelativeLayout contentMain;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //make toolbar title center_horizontal
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        //add listener
        navView.setNavigationItemSelectedListener(this);
        switchFragmentByMenu("首页");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            switchFragmentByMenu("首页");
        } else if (id == R.id.nav_android) {
            switchFragmentByMenu("Android");
        } else if (id == R.id.nav_ios) {
            switchFragmentByMenu("iOS");
        } else if (id == R.id.nav_front) {
            switchFragmentByMenu("前端");
        } else if (id == R.id.nav_resource) {
            switchFragmentByMenu("拓展资源");
        } else if (id == R.id.nav_video) {
            switchFragmentByMenu("休息视频");
        } else if (id == R.id.nav_bonus) {
            switchFragmentByMenu("福利");
        } else if (id== R.id.nav_about) {
            switchFragmentByMenu("关于");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchFragmentByMenu(String category) {
        tvToolbarTitle.setText(category);
        if (category.equals("首页")) {
            category = "all";//bad code for category switching
        }
        FragmentManager fm = getSupportFragmentManager();
        if (!category.equals(PublicTools.BONUS) && !category.equals(PublicTools.ABOUT)) {
            fm.beginTransaction().replace(R.id.content_main, GankDetailsFragment.newInstance(category)).commit();
        } else if(category.equals(PublicTools.BONUS)){
            fm.beginTransaction().replace(R.id.content_main, BonusFragment.newInstance()).commit();
        } else if(category.equals(PublicTools.ABOUT)) {
            fm.beginTransaction().replace(R.id.content_main, AboutFragment.newInstance()).commit();
        }
    }
}
