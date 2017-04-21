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
package onlyloveyd.com.gankioclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import onlyloveyd.com.gankioclient.decorate.Visitable;
import onlyloveyd.com.gankioclient.factory.GankTypeFactory;
import onlyloveyd.com.gankioclient.factory.TypeFactory;
import onlyloveyd.com.gankioclient.viewholder.BaseViewHolder;

/**
 * 文 件 名: MultiRecyclerAdapter
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：通用Adapter
 */
public class MultiRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    List<Visitable> mData;
    TypeFactory typeFactory;

    public MultiRecyclerAdapter(List<Visitable> mData) {
        this.mData = mData;
        this.typeFactory = new GankTypeFactory();
    }


    public void setData(List<Visitable> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public List<Visitable> getData() {
        return mData;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return typeFactory.createViewHolder(viewType, view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindViewData(mData.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).type(typeFactory);
    }

    @Override
    public int getItemCount() {
        return (mData != null ? mData.size() : 0);
    }

}
