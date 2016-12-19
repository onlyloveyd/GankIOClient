package onlyloveyd.com.gankioclient.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.BonusAdapter;
import onlyloveyd.com.gankioclient.adapter.GankAdapter;
import onlyloveyd.com.gankioclient.gsonbean.HttpBean;
import onlyloveyd.com.gankioclient.http.HttpMethods;
import rx.Subscriber;

public class GankActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_content)
    RecyclerView recyclerView;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    GankAdapter gankAdapter;
    BonusAdapter bonusAdapter;

    LinearLayoutManager linearLayoutManager = null;
    StaggeredGridLayoutManager staggeredGridLayoutManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        gankAdapter = new GankAdapter();
        bonusAdapter = new BonusAdapter();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setAdapter(gankAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        getContent("all");

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
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
        getMenuInflater().inflate(R.menu.gank, menu);
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
        swipeRefreshLayout.setRefreshing(true);
        if (id == R.id.nav_bonus) {
            recyclerView.setAdapter(bonusAdapter);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            getContent("福利");
        } else {
            recyclerView.setAdapter(gankAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
            if (id == R.id.nav_android) {
                getContent("Android");
            } else if (id == R.id.nav_ios) {
                getContent("iOS");
            } else if (id == R.id.nav_front) {
                getContent("前端");
            } else if (id == R.id.nav_resource) {
                getContent("拓展资源");
            } else if (id == R.id.nav_video) {
                getContent("休息视频");
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getContent(final String category) {
        Subscriber subscriber = new Subscriber<HttpBean>() {
            @Override
            public void onCompleted() {
                if(swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                toolbar.setTitle(category);
            }

            @Override
            public void onError(Throwable e) {
                if(swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                toolbar.setTitle("加载错误");
                e.printStackTrace();
            }

            @Override
            public void onNext(HttpBean httpBean) {
                toolbar.setTitle("正在加载...");
                gankAdapter.clearAll();
                if (category.equals("福利")) {
                    bonusAdapter.setGankData(httpBean);
                } else {
                    gankAdapter.setGankData(httpBean);
                }
            }
        };
        HttpMethods.getInstance().getData(subscriber, category, "20", "1");
    }
}
