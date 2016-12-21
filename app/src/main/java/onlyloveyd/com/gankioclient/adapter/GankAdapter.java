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
    public View mFooterView = null;
    private static final int FOOTER_TYPE = 1;

    public GankAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType != FOOTER_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_left_image, parent, false);
            return (new LeftImageViewHolder(view));
        } else {
            return new EmptyViewHolder(mFooterView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mGankData != null && holder instanceof LeftImageViewHolder) {
            LeftImageViewHolder leftimageholder = (LeftImageViewHolder) holder;
            final HttpBean.ResultsBean resultsBean = mGankData.get(position);
            leftimageholder.tvTitle.setText(resultsBean.getDesc());
            leftimageholder.tvAuthor.setText(resultsBean.getWho());
            leftimageholder.tvDate.setText(resultsBean.getPublishedAt());
            List<String> images = resultsBean.getImages();
//            if (images != null && images.size() != 0) {
//                //采用官网说的处理办法，出现的图片清晰度不高
//                String image = images.get(0);//+ "?imageView2/0/w/100";
//                leftimageholder.leftThumbnail.setVisibility(View.VISIBLE);
//                Glide.with(mContext).load(image).crossFade().into(leftimageholder.leftThumbnail);
//            } else {
//                leftimageholder.leftThumbnail.setVisibility(View.GONE);
//            }
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
        if(mFooterView== null) {
            return (mGankData != null ? mGankData.size() : 0);
        }
        return (mGankData != null ? mGankData.size() + 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if(mGankData!= null && position == mGankData.size()) return FOOTER_TYPE;
        return 0;
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
     * 设置底部Item
     * @param resId
     */
    public void setFooterViewLayout(int resId) {
        mFooterView = LayoutInflater.from(mContext).inflate(resId, null, false);
    }


    /**
     * 左侧缩略图，右侧标题，作者和时间
     */
    class LeftImageViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.iv_item_pic1)
//        ImageView leftThumbnail;
        @BindView(R.id.tv_item_title)
        TextView tvTitle;
        @BindView(R.id.tv_item_author)
        TextView tvAuthor;
        @BindView(R.id.tv_item_date)
        TextView tvDate;

        public LeftImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    /**
     * 空ViewHolder
     *
     */
    class EmptyViewHolder extends RecyclerView.ViewHolder{
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
