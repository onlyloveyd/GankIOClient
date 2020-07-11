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
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Fade;
import android.transition.Slide;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.MultiRecyclerAdapter;
import onlyloveyd.com.gankioclient.databinding.ActivitySearchBinding;
import onlyloveyd.com.gankioclient.decorate.Visitable;
import onlyloveyd.com.gankioclient.gsonbean.EmptyBean;
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
public class SearchActivity extends AppCompatActivity
        implements OnRefreshLoadmoreListener {

    MultiRecyclerAdapter mMultiRecyclerAdapter;
    List<Visitable> mVisitableList = new ArrayList<>();

    private int pageindex = 1;
    private String keyword = null;

    private ActivitySearchBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        mBinding.setPresenter(this);
        Slide slide = new Slide();
        slide.setDuration(200);
        getWindow().setEnterTransition(slide);

        Fade fade = new Fade();
        fade.setDuration(200);
        getWindow().setExitTransition(fade);

        setSupportActionBar(mBinding.tlSearch);
        mBinding.tlSearch.setNavigationIcon(R.drawable.back);

        initRvContent();
        initRefreshLayout();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dummy_items, R.layout.spinner_item_text);
        adapter.setDropDownViewResource(R.layout.spinner_item_dropdown_list);

        mBinding.spCategory.setAdapter(adapter);

        mBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keyword = s.toString();
                if (keyword.length() == 0) {
                    mBinding.tvSearch.setTextColor(getResources().getColor(R.color.colorPrimary));
                    mBinding.tvSearch.setClickable(false);
                } else {
                    mBinding.tvSearch.setTextColor(getResources().getColor(R.color.white));
                    mBinding.tvSearch.setClickable(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void initRvContent() {
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        mMultiRecyclerAdapter = new MultiRecyclerAdapter(null);
        mBinding.rvContent.setLayoutManager(llm);
        mBinding.rvContent.setAdapter(mMultiRecyclerAdapter);
    }

    private void initRefreshLayout() {
        mBinding.rlSearchContent.setOnRefreshLoadmoreListener(this);
    }

    public void doSearch() {
        PublicTools.hide_keyboard_from(this, mBinding.etSearch);
        //refreshData();
        mBinding.rlSearchContent.autoRefresh();
    }

    private void queryGanks(String keyword, final String category, int pageindex) {
        Observer<SearchBean> subscriber = new Observer<SearchBean>() {
            @Override
            public void onComplete() {
                if (mBinding.rlSearchContent.isLoading()) {
                    mBinding.rlSearchContent.finishLoadmore();
                } else {
                    mBinding.rlSearchContent.finishRefresh();
                }
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(mBinding.rvContent, "网络请求错误", Snackbar.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(SearchBean httpBean) {
                if (mBinding.rlSearchContent.isLoading()) {
                } else {
                    mVisitableList.clear();
                }

                if (httpBean.getResults() == null || httpBean.getResults().size() == 0) {
                    EmptyBean emptyBean = new EmptyBean();
                    emptyBean.setMessage(getString(R.string.empty_data));
                    mVisitableList.add(0, emptyBean);
                } else {
                    mVisitableList.addAll(httpBean.getResults());
                }
                mMultiRecyclerAdapter.setData(mVisitableList);
            }
        };
        HttpMethods.getInstance().searchData(subscriber, keyword, category, pageindex);
    }


    private void refreshData() {
        pageindex = 1;
        if (keyword != null && keyword.length() > 0) {
            String category = (String) mBinding.spCategory.getSelectedItem();
            queryGanks(keyword, category, pageindex);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        refreshData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        if (keyword != null && keyword.length() > 0) {
            String category = (String) mBinding.spCategory.getSelectedItem();
            queryGanks(keyword, category, ++pageindex);
        }
    }
}
