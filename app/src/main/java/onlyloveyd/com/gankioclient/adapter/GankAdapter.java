package onlyloveyd.com.gankioclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.activity.WebActivity;
import onlyloveyd.com.gankioclient.gsonbean.HttpBean;

/**
 * Created by lisa on 2016/12/19.
 * Email: 457420045@qq.com
 */

public class GankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<HttpBean.ResultsBean> mGankData = null;
    public Context mContext = null;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_text, parent, false);
        return (new TextViewHolder(view));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mGankData != null && holder instanceof TextViewHolder) {
            TextViewHolder textViewHolder = (TextViewHolder) holder;
            final HttpBean.ResultsBean resultsBean = mGankData.get(position);
            textViewHolder.tvTitle.setText(resultsBean.getDesc());
            textViewHolder.tvAuthor.setText(resultsBean.getWho());
            textViewHolder.tvTime.setText(resultsBean.getPublishedAt());
            textViewHolder.tvType.setText(resultsBean.getType());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, WebActivity.class);
                    intent.putExtra("URL", resultsBean.getUrl());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (mGankData != null ? mGankData.size() : 0);
    }

    /**
     * 设置干货数据
     *
     * @param httpBean 网络请求数据
     */
    public void setGankData(HttpBean httpBean) {
        this.mGankData = httpBean.getResults();
        notifyDataSetChanged();
    }

    /**
     * 添加干货数据
     *
     * @param bean 网络请求数据
     */
    public void addGankData(HttpBean bean) {
        if(mGankData== null) {
            this.mGankData = bean.getResults();
        } else {
            mGankData.addAll(bean.getResults());
        }
    }
    /**
     * 清空数据
     *
     * @param
     */
    public void clearAll() {
        if (mGankData != null) {
            mGankData.clear();
            notifyDataSetChanged();
        }
    }


    /**
     * 纯文本ViewHolder
     */
    class TextViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView tvTitle;
        @BindView(R.id.author)
        TextView tvAuthor;
        @BindView(R.id.time)
        TextView tvTime;
        @BindView(R.id.type)
        TextView tvType;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
