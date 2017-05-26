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
package onlyloveyd.com.gankioclient.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.gsonbean.DailyBean;
import onlyloveyd.com.gankioclient.utils.Constant;
import onlyloveyd.com.gankioclient.utils.PublicTools;


/**
 * 文 件 名: DailyViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：每日干货ViewHolder
 */
public class DailyViewHolder extends BaseViewHolder<DailyBean.ResultsBean.DetailsBean> {

    public DailyViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(final DailyBean.ResultsBean.DetailsBean data) {
        if (data != null) {
            ImageView ivDaily = (ImageView) getView(R.id.iv_daily);
            TextView tvTitleDaily = (TextView) getView(R.id.tv_title_daily);
            TextView tvTypeDaily = (TextView) getView(R.id.tv_type_daily);
            TextView tvDateDaily = (TextView) getView(R.id.tv_date_daily);

            tvTitleDaily.setText(data.getDesc().trim());
            tvDateDaily.setText(
                    PublicTools.date2String(data.getPublishedAt().getTime(), "yyyy.MM.dd"));

            if (data.getImages() != null && data.getImages().size() > 0) {
                Glide.with(itemView.getContext())
                        .load(data.getImages().get(0))
                        .placeholder(R.mipmap.empty_data)
                        .into(ivDaily);
            } else {
                if (data.getType().equals("福利")) {
                    Glide.with(itemView.getContext())
                            .load(data.getUrl())
                            .placeholder(R.mipmap.empty_data)
                            .into(ivDaily);
                } else {
                    ivDaily.setVisibility(View.GONE);
                }
            }
            String type = data.getType();
            tvTypeDaily.setText(type);
            tvTypeDaily.setBackgroundResource(Constant.sTypeColor.get(type));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PublicTools.startWebActivity(itemView.getContext(), data.getUrl());
                }
            });
        }

    }
}
