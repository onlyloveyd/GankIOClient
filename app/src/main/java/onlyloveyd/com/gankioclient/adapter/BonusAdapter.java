package onlyloveyd.com.gankioclient.adapter;

import android.content.Intent;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.activity.WebActivity;
import onlyloveyd.com.gankioclient.gsonbean.HttpBean;

/**
 * Created by lisa on 2016/12/19.
 * Email: 457420045@qq.com
 */

public class BonusAdapter extends GankAdapter{
    @Override
    public BonusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_image, parent, false);
        return (new BonusViewHolder(view));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(mGankData!= null && holder instanceof  BonusViewHolder) {
            BonusViewHolder bonusViewHolder = (BonusViewHolder)holder;
            final HttpBean.ResultsBean resultsBean = mGankData.get(position);
            final String url = resultsBean.getUrl();
            if(url!= null) {
                Glide.with(mContext).load(url).into(bonusViewHolder.mainPic);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(mContext, WebActivity.class);
                    intent.putExtra("URL", resultsBean.getUrl());
                    mContext.startActivity(intent);
                }
            });
            bonusViewHolder.downloadBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Glide.with(mContext).load(url).downloadOnly(new SimpleTarget<File>() {
                        @Override
                        public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                            //System.err.println("yidong -- resource =" + resource.getAbsolutePath());
                        }
                    });
                }
            });
        }
    }

    /**
     * bonus单纯图片
     */
    class BonusViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imgPicture)
        ImageView mainPic;
        @BindView(R.id.ib_download)
        ImageButton downloadBt;

        public BonusViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
