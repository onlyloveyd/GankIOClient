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

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import onlyloveyd.com.gankioclient.gsonbean.DataBean;
import onlyloveyd.com.gankioclient.http.HttpMethods;

/**
 * 文 件 名: GankFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：分类数据界面下每个数据显示界面
 */
public class GankFragment extends BaseFragment {
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
    }

    public void getContent(final String category, int pagenum) {
        Observer<DataBean> observer = new Observer<DataBean>() {
            @Override
            public void onComplete() {
                endLoading();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                endLoading();
                onNetworkError();
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DataBean httpBean) {
                if (bgaRefreshLayout.isLoadingMore()) {
                } else {
                    mVisitableList.clear();
                }
                if (httpBean.getResults() == null || httpBean.getResults().size() == 0) {
                    onDataEmpty();
                } else {
                    mVisitableList.addAll(httpBean.getResults());
                }
                mMultiRecyclerAdapter.setData(mVisitableList);
            }
        };
        HttpMethods.getInstance().getData(observer, category, "10", pagenum);
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
