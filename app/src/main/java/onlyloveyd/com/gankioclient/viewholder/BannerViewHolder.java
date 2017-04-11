package onlyloveyd.com.gankioclient.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cn.bingoogolapple.bgabanner.BGABanner;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.gsonbean.DailyBean;
import onlyloveyd.com.gankioclient.gsonbean.DataBean;
import onlyloveyd.com.gankioclient.gsonbean.ResultsBean;

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
public class BannerViewHolder extends BaseViewHolder<DataBean> {
    public BannerViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(DataBean data) {
        BGABanner bgaBanner = (BGABanner)getView(R.id.banner_bonus);
        ImageButton imageButton = (ImageButton)getView(R.id.ib_refresh);



    }


    class BannerAdapter implements BGABanner.Adapter<ImageView, ResultsBean> {

        @Override
        public void fillBannerItem(BGABanner banner, ImageView itemView, ResultsBean model,
                int position) {
            Glide.with(itemView.getContext())
                    .load(model.getUrl())
                    .placeholder(R.drawable.ic_reorder_grey_500_24dp)
                    .error(R.drawable.ic_reorder_grey_500_24dp)
                    .centerCrop()
                    .dontAnimate()
                    .into(itemView);
        }
    }

}
