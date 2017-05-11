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

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.gsonbean.MindBean;
import onlyloveyd.com.gankioclient.gsonbean.ResultsBean;
import onlyloveyd.com.gankioclient.utils.Constant;
import onlyloveyd.com.gankioclient.utils.PublicTools;

/**
 * 文 件 名: MindViewHoder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：单类数据ViewHolder
 */
public class MindViewHolder extends BaseViewHolder<MindBean> {

    public MindViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(final MindBean data) {

        if (data != null) {
            TextView tvTitle = (TextView) getView(R.id.tv_title);
            TextView tvAuthor = (TextView) getView(R.id.tv_author);
            TextView tvDate = (TextView) getView(R.id.tv_date);
            // 标题
            if (TextUtils.isEmpty(data.getTitle())) {
                tvTitle.setText("");
            } else {
                tvTitle.setText(data.getTitle().trim());
            }
            // 时间
            if (data.getTime() == null) {
                tvDate.setText("");
            } else {
                String time = data.getTime();
                if(time == null){
                    tvDate.setText("");
                } else {
                    tvDate.setText(time);
                }
            }

            // 作者
            if (TextUtils.isEmpty(data.getAuthor())) {
                tvAuthor.setText("");
            } else {
                tvAuthor.setText(data.getAuthor());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PublicTools.startWebActivity(itemView.getContext(), data.getUrl());
                }
            });
        }
    }
}
