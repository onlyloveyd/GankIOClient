package onlyloveyd.com.gankioclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.BonusAdapter;
import onlyloveyd.com.gankioclient.adapter.GankAdapter;
import onlyloveyd.com.gankioclient.gsonbean.HttpBean;
import onlyloveyd.com.gankioclient.http.HttpMethods;
import rx.Subscriber;

/**
 * Created by lisa on 2016/12/22.
 * Email: 457420045@qq.com
 */

public class GankDetailsFragment extends Fragment {

    @BindView(R.id.rv_content)
    RecyclerView rvContent;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    GankAdapter gankAdapter;
    BonusAdapter bonusAdapter;
    LinearLayoutManager llm;
    String category;
    int pagenum = 1;
    public final int MAX_PAGENUM = 10;

    public static GankDetailsFragment newInstance(String category) {
        Bundle args = new Bundle();
        args.putString("CATEGORY", category);
        GankDetailsFragment fragment = new GankDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public GankDetailsFragment() {
        super();
        Bundle args = getArguments();
        if (args != null) {
            category = args.getString("CATEGORY");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gank_details, container, false);
        ButterKnife.bind(this, view);

        //创建LayoutManager和Adapter
        llm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        gankAdapter = new GankAdapter(getContext());
        bonusAdapter = new BonusAdapter(getContext());

        gankAdapter.setFooterViewRes(R.layout.rv_footer);
        bonusAdapter.setFooterViewRes(R.layout.rv_footer);

        rvContent.setLayoutManager(llm);
        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isSlideToBottom(rvContent)) {
                    loadMoreData();
                }
            }
        });
        Bundle args = getArguments();
        if (args != null) {
            category = args.getString("CATEGORY");
            if (category.equals("福利")) {
                rvContent.setAdapter(bonusAdapter);
            } else {
                rvContent.setAdapter(gankAdapter);
            }
        }
        swipeRefreshLayout.setColorSchemeResources(R.color.colorBlack, R.color.colorBottomSelected, R.color.colorAccent, R.color.colorComment);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getContent(category,1);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getContent(category, pagenum);
    }

    private void getContent(final String category, int pagenum) {
        if(swipeRefreshLayout!= null && !swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
        Subscriber subscriber = new Subscriber<HttpBean>() {
            @Override
            public void onCompleted() {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onError(Throwable e) {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                Snackbar.make(rvContent,"网络请求错误",Snackbar.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onNext(HttpBean httpBean) {
                if (category.equals("福利")) {
                    bonusAdapter.setGankData(httpBean);
                } else {
                    gankAdapter.setGankData(httpBean);
                }
            }
        };
        HttpMethods.getInstance().getData(subscriber, category, "10", pagenum);
    }

    public static boolean isSlideToBottom(RecyclerView recyclerView) {
        if (recyclerView == null) return false;
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset()
                >= recyclerView.computeVerticalScrollRange())
            return true;
        return false;
    }

    public void loadMoreData() {
        if(pagenum == MAX_PAGENUM -1){
            if (category.equals("福利")) {
                bonusAdapter.setFooterViewRes(R.layout.rv_footer_deadline);
            } else {
                gankAdapter.setFooterViewRes(R.layout.rv_footer_deadline);
            }
        }
        if(pagenum == MAX_PAGENUM) {
            return;
        }
        pagenum +=1;
        getContent(category,pagenum);
    }
}
