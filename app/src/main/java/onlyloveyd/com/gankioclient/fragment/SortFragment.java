package onlyloveyd.com.gankioclient.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.TabAdapter;
import onlyloveyd.com.gankioclient.utils.Constant;

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
public class SortFragment extends Fragment {

    @BindView(R.id.indicator)
    SlidingTabLayout indicator;
    @BindView(R.id.vp_view)
    ViewPager vpView;

    private TabAdapter tabAdapter = null;

    public static SortFragment newInstance() {
        Bundle args = new Bundle();
        SortFragment fragment = new SortFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        System.out.println("yidong -- isVisible = " + isVisibleToUser);
//        if(isVisibleToUser) {
//            if(tabAdapter!=null) {
//                tabAdapter.notifyDataSetChanged();
//            }
//        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    public SortFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort, null, false);
        ButterKnife.bind(this, view);
        tabAdapter = new TabAdapter(getChildFragmentManager());
        vpView.setAdapter(tabAdapter);
        indicator.setViewPager(vpView);
        return view;
    }

    @Override
    public void onDestroy() {
        System.out.println("yidong -- destory");
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("yidong -- onResume");
        if(tabAdapter== null ) {
            System.out.println("yidong -- tabadapter == null");
            tabAdapter = new TabAdapter(getChildFragmentManager());
            vpView.removeAllViews();
            vpView.setAdapter(tabAdapter);
            indicator.setViewPager(vpView);
        }
    }

    @Override
    public void onPause() {
        System.out.println("yidong -- onPause");
        super.onPause();
        tabAdapter = null;
    }
}
