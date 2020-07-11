
package onlyloveyd.com.gankioclient.viewholder;

import android.view.View;
import android.widget.TextView;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.gsonbean.EmptyBean;

/**
 * 文 件 名: EmptyViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 14:19
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：处理空数据和异常信息
 */
public class EmptyViewHolder extends BaseViewHolder<EmptyBean> {

    public EmptyViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(EmptyBean data) {
        TextView textView = (TextView) getView(R.id.tv_empty);
        if (textView != null) {
            textView.setText(data.getMessage());
        }
    }
}
