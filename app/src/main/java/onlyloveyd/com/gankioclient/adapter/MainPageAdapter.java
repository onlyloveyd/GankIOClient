package onlyloveyd.com.gankioclient.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.fragment.GankFragment;
import onlyloveyd.com.gankioclient.fragment.SearchFragment;

/**
 * Created by lisa on 2016/12/22.
 * Email: 457420045@qq.com
 */

public class MainPageAdapter extends FragmentPagerAdapter {
    String[] mTitles;
    public MainPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        mTitles = context.getResources().getStringArray(R.array.home_page);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return GankFragment.newInstance();
            case 1:
                return SearchFragment.newInstance();
            case 2:
                return SearchFragment.newInstance();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }
}
