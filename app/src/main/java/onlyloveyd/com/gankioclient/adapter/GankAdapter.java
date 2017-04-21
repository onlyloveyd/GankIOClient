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
package onlyloveyd.com.gankioclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import onlyloveyd.com.gankioclient.fragment.AboutFragment;
import onlyloveyd.com.gankioclient.fragment.BonusFragment;
import onlyloveyd.com.gankioclient.fragment.DailyFragment;
import onlyloveyd.com.gankioclient.fragment.SortFragment;
import onlyloveyd.com.gankioclient.utils.Constant;
import onlyloveyd.com.gankioclient.utils.PublicTools;

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
                return BonusFragment.newInstance(Constant.BONUS);
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
