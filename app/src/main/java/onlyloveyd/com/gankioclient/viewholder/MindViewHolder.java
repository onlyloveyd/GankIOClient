
package onlyloveyd.com.gankioclient.viewholder;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.gsonbean.MindBean;
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
                if (time == null) {
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
