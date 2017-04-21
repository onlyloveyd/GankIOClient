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
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.MultiRecyclerAdapter;
import onlyloveyd.com.gankioclient.decorate.Visitable;
import onlyloveyd.com.gankioclient.gsonbean.DataBean;
import onlyloveyd.com.gankioclient.http.HttpMethods;
import rx.Subscriber;
import rx.exceptions.OnErrorFailedException;

/**
 * 文 件 名: GankFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：分类数据界面下每个数据显示界面
 */
public class GankFragment extends BaseFragment{
    int pagenum = 1;

    public static GankFragment newInstance(String arg) {
        Bundle args = new Bundle();
        args.putString("ARG", arg);
        GankFragment fragment = new GankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initBGAData() {
        bgaRefreshLayout.beginRefreshing();
        getContent(arg, 1);
    }

    public void getContent(final String category, int pagenum) {
        Subscriber subscriber = new Subscriber<DataBean>() {
            @Override
            public void onCompleted() {
                if (bgaRefreshLayout.isLoadingMore()) {
                    bgaRefreshLayout.endLoadingMore();
                } else {
                    bgaRefreshLayout.endRefreshing();
                }
            }

            @Override
            public void onError(Throwable e) {
                try {
                    Snackbar.make(rvContent, "网络请求错误", Snackbar.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (OnErrorFailedException errorFailedException) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNext(DataBean httpBean) {
                if (bgaRefreshLayout.isLoadingMore()) {
                } else {
                    mVisitableList.clear();
                }
                mVisitableList.addAll(httpBean.getResults());
                mMultiRecyclerAdapter.setData(mVisitableList);
            }
        };
        HttpMethods.getInstance().getData(subscriber, category, "10", pagenum);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getContent(arg, 1);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        getContent(arg, ++pagenum);
        return true;
    }
}
