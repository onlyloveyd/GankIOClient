
package onlyloveyd.com.gankioclient.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

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

    public List<Visitable> getData() {
        return mData;
    }

    public void setData(List<Visitable> data) {
        mData = data;
        notifyDataSetChanged();
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
