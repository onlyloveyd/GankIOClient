package onlyloveyd.com.gankioclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import onlyloveyd.com.gankioclient.fragment.GankFragment;
import onlyloveyd.com.gankioclient.utils.Constant;

/**
 * Desc: {}
 * Project Name:GankIOClient
 * Package Name:onlyloveyd.com.gankioclient.adapter
 * Author : Mraz
 * Created at 2017/3/16 21:31
 */

public class TabAdapter extends FragmentStatePagerAdapter {
    //福利 | Android | iOS | 休息视频 | 拓展资源 | 前端

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return GankFragment.newInstance(Constant.sCategoryList.get(position));
    }

    @Override
    public int getCount() {
        return Constant.sCategoryList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constant.sCategoryList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        //return super.getItemPosition(object);
        return POSITION_NONE;
    }
}
