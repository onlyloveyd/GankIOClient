package onlyloveyd.com.gankioclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import onlyloveyd.com.gankioclient.fragment.GankDetailsFragment;

/**
 * Desc: {}
 * Project Name:GankIOClient
 * Package Name:onlyloveyd.com.gankioclient.adapter
 * Author : Mraz
 * Created at 2017/3/16 21:31
 */

public class TabAdapter extends FragmentPagerAdapter {
    //福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
    public static String[] titles = {"Android","iOS","前端","拓展资源","休息视频"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GankDetailsFragment.newInstance(titles[position]);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
