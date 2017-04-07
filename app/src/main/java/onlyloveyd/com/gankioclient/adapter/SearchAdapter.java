package onlyloveyd.com.gankioclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.gsonbean.DataBean;
import onlyloveyd.com.gankioclient.gsonbean.SearchBean;
import onlyloveyd.com.gankioclient.utils.Constant;
import onlyloveyd.com.gankioclient.utils.PublicTools;

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
public class SearchAdapter extends GankAdapter {
    List<SearchBean.ResultsBean> mSearchDatas = null;

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_text, parent,
                        false);
        return (new GankAdapter.TextViewHolder(view));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mSearchDatas != null && holder instanceof TextViewHolder) {
            final TextViewHolder textViewHolder = (TextViewHolder) holder;
            final SearchBean.ResultsBean resultsBean = mSearchDatas.get(position);
            // 标题
            if (TextUtils.isEmpty(resultsBean.getDesc())) {
                textViewHolder.tvTitle.setText("");
            } else {
                textViewHolder.tvTitle.setText(resultsBean.getDesc().trim());
            }
            // 时间
            if (resultsBean.getPublishedAt() == null) {
                textViewHolder.tvDate.setText("");
            } else {
                textViewHolder.tvDate.setText(resultsBean.getPublishedAt());
            }

            // 作者
            if (TextUtils.isEmpty(resultsBean.getWho())) {
                textViewHolder.tvAuthor.setText("");
            } else {
                textViewHolder.tvAuthor.setText(resultsBean.getWho());
            }

            if (TextUtils.isEmpty(resultsBean.getType())) {
                textViewHolder.tvType.setText("");
            } else {
                textViewHolder.tvType.setText(resultsBean.getType());
                textViewHolder.tvType.setBackgroundResource(
                        Constant.sTypeColor.get(resultsBean.getType()));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PublicTools.startWebActivity(mContext, resultsBean.getUrl());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (mSearchDatas != null ? mSearchDatas.size() : 0);
    }

    /**
     * 设置干货数据
     *
     * @param dataBean 网络请求数据
     */
    public void setGankData(SearchBean dataBean) {
        this.mSearchDatas = dataBean.getResults();
        notifyDataSetChanged();
    }

    /**
     * 添加干货数据
     *
     * @param bean 网络请求数据
     */
    public void addGankData(SearchBean bean) {
        if (mSearchDatas == null) {
            this.mSearchDatas = bean.getResults();
        } else {
            mSearchDatas.addAll(bean.getResults());
        }
    }
}
