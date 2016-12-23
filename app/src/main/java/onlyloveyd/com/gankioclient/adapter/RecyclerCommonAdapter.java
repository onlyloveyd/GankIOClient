package onlyloveyd.com.gankioclient.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lisa on 2016/12/23.
 * Email: 457420045@qq.com
 */
public abstract class RecyclerCommonAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> data;
    protected LayoutInflater mInflater;

    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setLongClickListener(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }


    public RecyclerCommonAdapter(Context context, List<T> data,int layoutId)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        this.data = data;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(final ViewGroup parent, int viewType)
    {
        RecyclerViewHolder viewHolder = RecyclerViewHolder.get(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position)
    {
        //判断是否设置了监听器
        if(clickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = holder.getLayoutPosition(); // 1
                    clickListener.onItemClick(holder.itemView,id); // 2
                }
            });
        }
        if(longClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int id = holder.getLayoutPosition();
                    longClickListener.onItemLongClick(holder.itemView, id);
                    return true;
                }
            });
        }
        convert(holder, data.get(position));
    }

    public abstract void convert(RecyclerViewHolder holder, T t);

    @Override
    public int getItemCount()
    {
        return data.size();
    }



}
