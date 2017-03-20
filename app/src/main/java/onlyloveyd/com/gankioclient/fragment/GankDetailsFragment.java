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

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.GankAdapter;
import onlyloveyd.com.gankioclient.gsonbean.HttpBean;
import onlyloveyd.com.gankioclient.http.HttpMethods;
import rx.Subscriber;
import rx.exceptions.OnErrorFailedException;

/**
 * Created by lisa on 2016/12/22.
 * Email: 457420045@qq.com
 */

public class GankDetailsFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.rl_gank_refresh)
    BGARefreshLayout bgaRefreshLayout;

    GankAdapter gankAdapter;
    LinearLayoutManager llm;
    String category;
    int pagenum = 1;

    public static GankDetailsFragment newInstance(String category) {
        Bundle args = new Bundle();
        args.putString("CATEGORY", category);
        GankDetailsFragment fragment = new GankDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gank_details, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if (args != null) {
            category = args.getString("CATEGORY");
        }

        initBGALayout();
        initRvContent();
        //创建LayoutManager和Adapter

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        System.err.println("yidong -- isVisibleToUser");
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void initBGALayout() {
        // 为BGARefreshLayout 设置代理
        bgaRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能

        BGANormalRefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        refreshViewHolder.setLoadingMoreText("加载更多");
        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.white);
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.white);
        bgaRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    private void initRvContent() {
        llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        gankAdapter = new GankAdapter();
        rvContent.setLayoutManager(llm);
        rvContent.setAdapter(gankAdapter);
        bgaRefreshLayout.beginRefreshing();
        getContent(category, 1);
    }


    private void getContent(final String category, int pagenum) {
        Subscriber subscriber = new Subscriber<HttpBean>() {
            @Override
            public void onCompleted() {
                if(bgaRefreshLayout.isLoadingMore()) {
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
            public void onNext(HttpBean httpBean) {
                if(bgaRefreshLayout.isLoadingMore()) {
                    gankAdapter.addGankData(httpBean);
                } else {
                    gankAdapter.setGankData(httpBean);
                }
            }
        };
        HttpMethods.getInstance().getData(subscriber, category, "10", pagenum);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getContent(category, 1);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        getContent(category, ++pagenum);
        return true;
    }
}
