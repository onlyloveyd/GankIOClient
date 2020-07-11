
package onlyloveyd.com.gankioclient.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import onlyloveyd.com.gankioclient.fragment.AboutFragment;
import onlyloveyd.com.gankioclient.fragment.DailyFragment;
import onlyloveyd.com.gankioclient.fragment.MindFragment;
import onlyloveyd.com.gankioclient.fragment.SortFragment;
import onlyloveyd.com.gankioclient.utils.Constant;

/**
 * 文 件 名: GankAdapter
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：主界面ViewPagerAdapter
 */
public class GankAdapter extends FragmentPagerAdapter {
    public GankAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DailyFragment.newInstance();
            case 1:
                return SortFragment.newInstance();
            case 2:
                return MindFragment.newInstance();
            case 3:
                return AboutFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return Constant.sTabTitles.length;
    }
}
