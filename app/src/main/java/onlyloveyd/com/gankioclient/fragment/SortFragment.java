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
package onlyloveyd.com.gankioclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.TabAdapter;
import onlyloveyd.com.gankioclient.utils.Constant;

/**
 * 文 件 名: SortFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：分类数据界面
 */
public class SortFragment extends Fragment {

    @BindView(R.id.indicator)
    SlidingTabLayout indicator;
    @BindView(R.id.vp_view)
    ViewPager vpView;

    private TabAdapter tabAdapter = null;
    private String mCurrentTag = "all";

    public SortFragment() {
        super();
    }

    public static SortFragment newInstance() {
        Bundle args = new Bundle();
        SortFragment fragment = new SortFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort, null, false);
        ButterKnife.bind(this, view);
        tabAdapter = new TabAdapter(getChildFragmentManager());
        vpView.setAdapter(tabAdapter);
        indicator.setViewPager(vpView);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constant.sCategryListChanged) {
            tabAdapter = null;
            tabAdapter = new TabAdapter(getChildFragmentManager());
            vpView.removeAllViews();
            vpView.setAdapter(tabAdapter);
            indicator.setViewPager(vpView);
            for(int i=0;i<Constant.sCategoryList.size();i++) {
                if(Constant.sCategoryList.get(i).equals(mCurrentTag)) {
                    vpView.setCurrentItem(i,true);
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(vpView!=null) {
            mCurrentTag = Constant.sCategoryList.get(vpView.getCurrentItem());
        }
        Constant.sCategryListChanged = false;
    }
}
