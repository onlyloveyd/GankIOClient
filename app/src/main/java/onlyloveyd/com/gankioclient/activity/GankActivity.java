package onlyloveyd.com.gankioclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.ViewPagerAdapter;
import onlyloveyd.com.gankioclient.utils.Constant;
import onlyloveyd.com.gankioclient.view.TabEntity;

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
public class GankActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.vp_main)
    ViewPager mVpMain;
    @BindView(R.id.t2_2)
    CommonTabLayout mT22;


    private Menu mainMenu = null;

    private int[] mIconUnselectIds = {
            R.mipmap.tab_daily_unselect, R.mipmap.tab_sort_unselect,
            R.mipmap.tab_bonus_unselect, R.mipmap.tab_about_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_daily_select, R.mipmap.tab_sort_select,
            R.mipmap.tab_bonus_select, R.mipmap.tab_about_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        for (int i = 0; i < Constant.sTabTitles.length; i++) {
            mTabEntities.add(new TabEntity( Constant.sTabTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mVpMain.setAdapter(viewPagerAdapter);

        mT22.setTabData(mTabEntities);
        mT22.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mVpMain.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if(position!= 1) {
                    hideFilter(mainMenu);
                } else {
                    showFilter(mainMenu);
                }
                mT22.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
        int id = item.getItemId();
        if (id == R.id.action_filter) {
            Intent intent = new Intent();
            intent.setClass(this, OrderActivity.class);
            this.startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideFilter(Menu menu) {
        if(menu!= null) {
            menu.findItem(R.id.action_filter).setVisible(false);
        }
    }

    private void showFilter(Menu menu) {
        if(menu!= null) {
            menu.findItem(R.id.action_filter).setVisible(true);
        }
    }
}
