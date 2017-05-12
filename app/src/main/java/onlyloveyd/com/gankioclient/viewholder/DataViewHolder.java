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
import onlyloveyd.com.gankioclient.gsonbean.ResultsBean;
import onlyloveyd.com.gankioclient.utils.Constant;
import onlyloveyd.com.gankioclient.utils.PublicTools;

/**
 * 文 件 名: DataViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：单类数据ViewHolder
 */
public class DataViewHolder extends BaseViewHolder<ResultsBean> {

    public DataViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(final ResultsBean data) {

        if (data != null) {
            TextView tvTitle = (TextView) getView(R.id.tv_title);
            TextView tvAuthor = (TextView) getView(R.id.tv_author);
            TextView tvDate = (TextView) getView(R.id.tv_date);
            TextView tvType = (TextView) getView(R.id.tv_type);
            // 标题
            if (TextUtils.isEmpty(data.getDesc())) {
                tvTitle.setText("");
            } else {
                tvTitle.setText(data.getDesc().trim());
            }
            // 时间
            if (data.getPublishedAt() == null) {
                tvDate.setText("");
            } else {
                String time = data.getPublishedAt();
                time = time.substring(0, 19).replace("T", " ");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                try {
                    date = sdf.parse(time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (date == null) {
                    tvDate.setText("");
                } else {
                    tvDate.setText(
                            PublicTools.getTimestampString(date));
                }
            }

            // 作者
            if (TextUtils.isEmpty(data.getWho())) {
                tvAuthor.setText("");
            } else {
                tvAuthor.setText(data.getWho());
            }

            if (TextUtils.isEmpty(data.getType())) {
                tvType.setText("");
            } else {
                tvType.setText(data.getType());
                tvType.setBackgroundResource(
                        Constant.sTypeColor.get(data.getType()));
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
