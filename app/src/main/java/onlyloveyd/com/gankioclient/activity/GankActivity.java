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

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Calendar;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.GankAdapter;
import onlyloveyd.com.gankioclient.databinding.ActivityGankBinding;
import onlyloveyd.com.gankioclient.decorate.OnDatePickedListener;
import onlyloveyd.com.gankioclient.utils.Constant;
import onlyloveyd.com.gankioclient.utils.RxPermissionUtils;
import onlyloveyd.com.gankioclient.view.TabEntity;

/**
 * 文 件 名: GankActivity
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:22
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：home Activity
 */
public class GankActivity extends AppCompatActivity {
    /**
     * 再次返回键退出程序
     */
    private long lastBack = 0;

    private Menu mainMenu = null;

    private OnDatePickedListener mOnDatePickedListener;

    private int[] mIconUnselectIds = {
            R.mipmap.tab_daily_unselect, R.mipmap.tab_sort_unselect,
            R.mipmap.tab_bonus_unselect, R.mipmap.tab_about_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_daily_select, R.mipmap.tab_sort_select,
            R.mipmap.tab_bonus_select, R.mipmap.tab_about_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private ActivityGankBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_gank);

        setSupportActionBar(mBinding.toolbar);

        Slide slide = new Slide();
        slide.setDuration(200);
        getWindow().setEnterTransition(slide);

        Fade fade = new Fade();
        fade.setDuration(200);
        getWindow().setExitTransition(fade);

        for (int i = 0; i < Constant.sTabTitles.length; i++) {
            mTabEntities.add(
                    new TabEntity(Constant.sTabTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        GankAdapter gankAdapter = new GankAdapter(getSupportFragmentManager());
        mBinding.vpMain.setAdapter(gankAdapter);

        mBinding.t22.setTabData(mTabEntities);
        mBinding.t22.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mBinding.vpMain.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mBinding.vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position != 0) {
                    hideDateMenu(mainMenu);
                } else {
                    showDateMenu(mainMenu);
                }

                if (position != 1) {
                    hideFilter(mainMenu);
                } else {
                    showFilter(mainMenu);
                }
                mBinding.t22.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        RxPermissionUtils.createInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        showDateMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_filter) {
            Intent intent = new Intent();
            intent.setClass(this, OrderActivity.class);
            this.startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else if (id == R.id.action_search) {
            Intent intent = new Intent();
            intent.setClass(this, SearchActivity.class);
            this.startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else if (id == R.id.action_datepicker) {
            // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
            if (Constant.YEAR == -1 && Constant.MONTH == -1 && Constant.DAY == -1) {
                Calendar c = Calendar.getInstance();
                showDatePickerDialog(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));
            } else {
                showDatePickerDialog(Constant.YEAR, Constant.MONTH, Constant.DAY);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideFilter(Menu menu) {
        if (menu != null) {
            menu.findItem(R.id.action_filter).setVisible(false);
        }
    }

    private void showFilter(Menu menu) {
        if (menu != null) {
            menu.findItem(R.id.action_filter).setVisible(true);
        }
    }

    private void hideDateMenu(Menu menu) {
        if (menu != null) {
            menu.findItem(R.id.action_datepicker).setVisible(false);
        }
    }

    private void showDateMenu(Menu menu) {
        if (menu != null) {
            menu.findItem(R.id.action_datepicker).setVisible(true);
        }
    }

    private void showDatePickerDialog(int year, int month, int day) {

        new DatePickerDialog(GankActivity.this,
                // 绑定监听器
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month,
                                          int dayOfMonth) {
                        Constant.YEAR = year;
                        Constant.MONTH = month;
                        Constant.DAY = dayOfMonth;
                        if (mOnDatePickedListener != null) {
                            mOnDatePickedListener.onDatePicked(year, month, dayOfMonth);
                        }
                    }
                }
                // 设置初始日期
                , year, month, day).show();
    }

    public void setOnDatePickedListener(OnDatePickedListener onDatePickedListener) {
        mOnDatePickedListener = onDatePickedListener;
    }

    @Override
    public void onBackPressed() {
        if (lastBack == 0 || System.currentTimeMillis() - lastBack > 2000) {
            Toast.makeText(this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
            lastBack = System.currentTimeMillis();
            return;
        }
        super.onBackPressed();
    }
}
