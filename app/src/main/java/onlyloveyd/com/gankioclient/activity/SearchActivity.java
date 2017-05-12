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
package onlyloveyd.com.gankioclient.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.MultiRecyclerAdapter;
import onlyloveyd.com.gankioclient.decorate.Visitable;
import onlyloveyd.com.gankioclient.gsonbean.SearchBean;
import onlyloveyd.com.gankioclient.http.HttpMethods;
import onlyloveyd.com.gankioclient.utils.PublicTools;

/**
 * 文 件 名: SearchActivity
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：搜索Activity
 */
public class SearchActivity extends AppCompatActivity implements
        BGARefreshLayout.BGARefreshLayoutDelegate {
    @BindView(R.id.sp_category)
    Spinner mSpCategory;
    @BindView(R.id.tl_search)
    Toolbar mTlSearch;
    @BindView(R.id.rv_content)
    RecyclerView mRvContent;
    @BindView(R.id.rl_search_content)
    BGARefreshLayout mRlSearchContent;
    @BindView(R.id.et_search)
    EditText mEtSearch;
    @BindView(R.id.tv_search)
    TextView mTvSearch;

    MultiRecyclerAdapter mMultiRecyclerAdapter;
    List<Visitable> mVisitableList = new ArrayList<>();

    private int pageindex = 1;
    private String keyword = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setSupportActionBar(mTlSearch);
        mTlSearch.setNavigationIcon(R.drawable.back);

        initBGALayout();
        initRvContent();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dummy_items, R.layout.spinner_item_text);
        adapter.setDropDownViewResource(R.layout.spinner_item_dropdown_list);

        mSpCategory.setAdapter(adapter);
    }

    private void initBGALayout() {
        // 为BGARefreshLayout 设置代理
        mRlSearchContent.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGANormalRefreshViewHolder refreshViewHolder =
                new BGANormalRefreshViewHolder(this, true);
        refreshViewHolder.setLoadingMoreText("加载更多");
        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.white);
        refreshViewHolder.setRefreshViewBackgroundColorRes(R.color.white);
        mRlSearchContent.setRefreshViewHolder(refreshViewHolder);
    }

    private void initRvContent() {
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        mMultiRecyclerAdapter = new MultiRecyclerAdapter(null);
        mRvContent.setLayoutManager(llm);
        mRvContent.setAdapter(mMultiRecyclerAdapter);
    }

    @OnClick({R.id.tv_search})
    public void onClick() {
        PublicTools.hide_keyboard_from(this, mEtSearch);
        refreshData();
    }

    private void queryGanks(String keyword, final String category, int pageindex) {
        Observer<SearchBean> subscriber = new Observer<SearchBean>() {
            @Override
            public void onComplete() {
                if (mRlSearchContent.isLoadingMore()) {
                    mRlSearchContent.endLoadingMore();
                } else {
                    mRlSearchContent.endRefreshing();
                }
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(mRvContent, "网络请求错误", Snackbar.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(SearchBean httpBean) {
                if (mRlSearchContent.isLoadingMore()) {
                } else {
                    mVisitableList.clear();
                }
                mVisitableList.addAll(httpBean.getResults());
                mMultiRecyclerAdapter.setData(mVisitableList);
            }
        };
        HttpMethods.getInstance().searchData(subscriber, keyword, category, pageindex);
    }

    @OnTextChanged(R.id.et_search)
    public void onTextChange(CharSequence text) {
        keyword = text.toString();
        if (text.toString() == null || text.length() == 0) {
            mTvSearch.setTextColor(getResources().getColor(R.color.colorPrimary));
            mTvSearch.setClickable(false);
        } else {
            mTvSearch.setTextColor(getResources().getColor(R.color.white));
            mTvSearch.setClickable(true);
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        refreshData();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        if (keyword != null && keyword.length() > 0) {
            String category = (String) mSpCategory.getSelectedItem();
            queryGanks(keyword, category, ++pageindex);
        }
        return true;
    }

    private void refreshData() {
        pageindex = 1;
        mRlSearchContent.beginRefreshing();
        if (keyword != null && keyword.length() > 0) {
            String category = (String) mSpCategory.getSelectedItem();
            queryGanks(keyword, category, pageindex);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
            }
            break;
            default:
                break;
        }
        return true;
    }
}
