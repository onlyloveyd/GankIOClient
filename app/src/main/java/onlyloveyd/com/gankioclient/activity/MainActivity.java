package onlyloveyd.com.gankioclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.ViewPagerAdapter;
import onlyloveyd.com.gankioclient.utils.Constant;
import onlyloveyd.com.gankioclient.view.CustomViewPager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp_main)
    CustomViewPager vpMain;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    private Menu mainMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //make toolbar title center_horizontal
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        //add listener
        navigationView.setNavigationItemSelectedListener(this);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        vpMain.setAdapter(viewPagerAdapter);
        vpMain.setPagingEnabled(false);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        mainMenu = menu;
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        hideFilter(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            Intent intent = new Intent();
            intent.setClass(this, SearchActivity.class);
            this.startActivity(intent);
        } else if (id == R.id.action_filter) {
            Intent intent = new Intent();
            intent.setClass(this, OrderActivity.class);
            //this.startActivityForResult(intent, Constant.MAINTOORDER_REQUEST_CODE);
            this.startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.nav_latest:
                hideFilter(mainMenu);
                vpMain.setCurrentItem(0);
                break;
            case R.id.nav_about:
                hideFilter(mainMenu);
                vpMain.setCurrentItem(1);
                break;
            case R.id.nav_bonus:
                hideFilter(mainMenu);
                vpMain.setCurrentItem(2);
                break;
            case R.id.nav_category:
                showFilter(mainMenu);
                vpMain.setCurrentItem(3);
                break;
            default:
                break;
        }
        return true;
    }

    private void hideFilter(Menu menu) {
        menu.findItem(R.id.action_filter).setVisible(false);
    }

    private void showFilter(Menu menu) {
        menu.findItem(R.id.action_filter).setVisible(true);
    }
}
