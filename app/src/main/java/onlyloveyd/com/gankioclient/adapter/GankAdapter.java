package onlyloveyd.com.gankioclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
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
import onlyloveyd.com.gankioclient.utils.PublicTools;

/**
 * Created by lisa on 2016/12/19.
 * Email: 457420045@qq.com
 */

public class GankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<HttpBean.ResultsBean> mGankData = null;
    public Context mContext = null;

    public GankAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_thumbnail, parent, false);
        return (new LeftImageViewHolder(view));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mGankData != null && holder instanceof LeftImageViewHolder) {
            LeftImageViewHolder leftimageholder = (LeftImageViewHolder) holder;
            final HttpBean.ResultsBean resultsBean = mGankData.get(position);
            leftimageholder.tvTitle.setText(resultsBean.getDesc());
            leftimageholder.tvAuthor.setText(resultsBean.getWho());
            leftimageholder.tvDate.setText(resultsBean.getPublishedAt().replace("T", "  ").replace("Z", ""));
            List<String> images = resultsBean.getImages();
            if (images != null && images.size() != 0) {
                String thumbnail = images.get(0);
                AnimationDrawable animationDrawable = (AnimationDrawable) mContext.getResources().getDrawable(R.drawable.image_loading);
                animationDrawable.start();
                Glide.with(mContext).load(thumbnail).crossFade().placeholder(animationDrawable).into(leftimageholder.ivThumbnail);
            } else {
                leftimageholder.ivThumbnail.setVisibility(View.GONE);
            }
            //direct ro web activity with extra data
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, WebActivity.class);
                    intent.putExtra(PublicTools.KEY_BUNDLE_URL, resultsBean.getUrl());
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
     * Set Gank data
     *
     * @param httpBean http data
     */
    public void setGankData(HttpBean httpBean) {
        if (mGankData != null) mGankData.clear();
        this.mGankData = httpBean.getResults();
        notifyDataSetChanged();
    }

    /**
     * add Gank data
     *
     * @param httpBean http data
     */
    public void addGankData(HttpBean httpBean) {
        if (mGankData == null) {
            setGankData(httpBean);
        }
        this.mGankData.addAll(httpBean.getResults());
    }


    /**
     * left:thumbnail
     * right-top: description
     * right-bottom-left: author
     * right-bottom-right: date
     */
    class LeftImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_thumbnail)
        ImageView ivThumbnail;
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
}
