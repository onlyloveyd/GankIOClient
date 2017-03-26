package onlyloveyd.com.gankioclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import onlyloveyd.com.gankioclient.fragment.GankDetailsFragment;
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
        System.out.println("yidong -- positon = " + position);
        return GankDetailsFragment.newInstance(Constant.sCategoryList.get(position));
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
        System.out.println("yidong -- getItemPosition");
        return POSITION_NONE;
    }


}
