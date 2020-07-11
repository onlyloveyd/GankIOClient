
package onlyloveyd.com.gankioclient.factory;

import android.view.View;

import onlyloveyd.com.gankioclient.gsonbean.DailyBean;
import onlyloveyd.com.gankioclient.gsonbean.EmptyBean;
import onlyloveyd.com.gankioclient.gsonbean.MindBean;
import onlyloveyd.com.gankioclient.gsonbean.ResultsBean;
import onlyloveyd.com.gankioclient.viewholder.BaseViewHolder;

/**
 * 文 件 名: TypeFactory
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：MultiType数据工厂接口
 */
public interface TypeFactory {
    int type(DailyBean.ResultsBean.DetailsBean dailyBean);

    int type(ResultsBean resultsBean);

    int type(EmptyBean emptyBean);

    int type(MindBean mindBean);

    BaseViewHolder createViewHolder(int type, View itemView);
}
