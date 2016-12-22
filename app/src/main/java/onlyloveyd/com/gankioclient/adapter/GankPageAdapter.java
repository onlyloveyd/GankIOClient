package onlyloveyd.com.gankioclient.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.fragment.GankDetailsFragment;

/**
 * Created by lisa on 2016/12/22.
 * Email: 457420045@qq.com
 */

public class GankPageAdapter extends FragmentPagerAdapter {
    String[] mTitles;
    public GankPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        mTitles = context.getResources().getStringArray(R.array.gank_category);

    }

    @Override
    public Fragment getItem(int position) {
        return GankDetailsFragment.newInstance(mTitles[position]);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
