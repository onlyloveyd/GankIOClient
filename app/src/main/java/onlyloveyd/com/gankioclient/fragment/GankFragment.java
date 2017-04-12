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
 * Created by lisa on 2016/12/22.
 * Email: 457420045@qq.com
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
