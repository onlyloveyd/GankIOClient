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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import onlyloveyd.com.gankioclient.adapter.TabAdapter;
import onlyloveyd.com.gankioclient.databinding.FragmentSortBinding;
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
    private TabAdapter tabAdapter = null;
    private String mCurrentTag = "all";

    private FragmentSortBinding mBinding;

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
        mBinding = FragmentSortBinding.inflate(inflater, container, false);
        tabAdapter = new TabAdapter(getChildFragmentManager());
        mBinding.vpView.setAdapter(tabAdapter);
        mBinding.indicator.setViewPager(mBinding.vpView);
        return mBinding.getRoot();
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
            mBinding.vpView.removeAllViews();
            mBinding.vpView.setAdapter(tabAdapter);
            mBinding.indicator.setViewPager(mBinding.vpView);
            for (int i = 0; i < Constant.sCategoryList.size(); i++) {
                if (Constant.sCategoryList.get(i).equals(mCurrentTag)) {
                    mBinding.vpView.setCurrentItem(i, true);
                }
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mCurrentTag = Constant.sCategoryList.get(mBinding.vpView.getCurrentItem());
        Constant.sCategryListChanged = false;
    }
}
