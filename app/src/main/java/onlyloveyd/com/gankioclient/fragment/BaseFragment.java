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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.MultiRecyclerAdapter;
import onlyloveyd.com.gankioclient.databinding.FragmentGankBinding;
import onlyloveyd.com.gankioclient.decorate.Visitable;
import onlyloveyd.com.gankioclient.gsonbean.EmptyBean;

/**
 * 文 件 名: BaseFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：Fragment基类
 */
public class BaseFragment extends Fragment implements OnRefreshLoadmoreListener {

    MultiRecyclerAdapter mMultiRecyclerAdapter;
    List<Visitable> mVisitableList = new ArrayList<>();

    LinearLayoutManager llm;
    String arg;

    protected FragmentGankBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = FragmentGankBinding.inflate(inflater, container, false);
        Bundle args = getArguments();
        if (args != null) {
            arg = args.getString("ARG");
        }

        initRefreshLayout();
        initRvContent();
        initData();
        return mBinding.getRoot();
    }


    public void initRefreshLayout() {
        mBinding.rlGankRefresh.setOnRefreshLoadmoreListener(this);
    }

    private void initRvContent() {
        llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mMultiRecyclerAdapter = new MultiRecyclerAdapter(null);
        mBinding.rvContent.setLayoutManager(llm);
        mBinding.rvContent.setAdapter(mMultiRecyclerAdapter);
    }

    public void initData() {
        mBinding.rlGankRefresh.autoRefresh();
    }

    /**
     * 处理网络请求错误
     */
    public void onNetworkError() {
        mVisitableList.clear();
        EmptyBean emptyBean = new EmptyBean();
        emptyBean.setMessage(getString(R.string.network_error));
        mVisitableList.add(0, emptyBean);
        mMultiRecyclerAdapter.setData(mVisitableList);
    }

    /**
     * 处理请求数据为空
     */
    public void onDataEmpty() {
        EmptyBean emptyBean = new EmptyBean();
        emptyBean.setMessage(getString(R.string.empty_data));
        mVisitableList.add(0, emptyBean);
    }

    /**
     * 停止刷新或者加载更多
     */
    public void endLoading() {
        if (mBinding.rlGankRefresh.isLoading()) {
            mBinding.rlGankRefresh.finishLoadmore();
        } else {
            mBinding.rlGankRefresh.finishRefresh();
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }
}
