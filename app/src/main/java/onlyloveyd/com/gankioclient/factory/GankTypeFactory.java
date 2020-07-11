

package onlyloveyd.com.gankioclient.factory;

import android.view.View;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.gsonbean.DailyBean;
import onlyloveyd.com.gankioclient.gsonbean.EmptyBean;
import onlyloveyd.com.gankioclient.gsonbean.MindBean;
import onlyloveyd.com.gankioclient.gsonbean.ResultsBean;
import onlyloveyd.com.gankioclient.utils.Constant;
import onlyloveyd.com.gankioclient.viewholder.BaseViewHolder;
import onlyloveyd.com.gankioclient.viewholder.BonusViewHolder;
import onlyloveyd.com.gankioclient.viewholder.DailyViewHolder;
import onlyloveyd.com.gankioclient.viewholder.DataViewHolder;
import onlyloveyd.com.gankioclient.viewholder.EmptyViewHolder;
import onlyloveyd.com.gankioclient.viewholder.MindViewHolder;

/**
 * 文 件 名: GankTypeFactory
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：MultiType数据工厂实现
 */
public class GankTypeFactory implements TypeFactory {

    public static final int DAILY_ITEM_LAYOUT = R.layout.rv_item_daily;
    public static final int DATA_ITEM_LAYOUT = R.layout.rv_item_text;
    public static final int BONUS_ITEM_LAYOUT = R.layout.rv_item_image;
    public static final int EMPTY_ITEM_LAYOUT = R.layout.rv_item_empty;
    public static final int MIND_ITEM_LAYOUT = R.layout.rv_item_mind;

    @Override
    public int type(DailyBean.ResultsBean.DetailsBean dailyBean) {
        return DAILY_ITEM_LAYOUT;
    }

    @Override
    public int type(ResultsBean contentBean) {
        if (contentBean.getType().equals(Constant.BONUS)) {
            return BONUS_ITEM_LAYOUT;
        } else {
            return DATA_ITEM_LAYOUT;
        }
    }

    @Override
    public int type(EmptyBean emptyBean) {
        return EMPTY_ITEM_LAYOUT;
    }

    public int type(MindBean mindBean) {
        return MIND_ITEM_LAYOUT;
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {
        switch (type) {
            case DAILY_ITEM_LAYOUT:
                return new DailyViewHolder(itemView);
            case DATA_ITEM_LAYOUT:
                return new DataViewHolder(itemView);
            case BONUS_ITEM_LAYOUT:
                return new BonusViewHolder(itemView);
            case EMPTY_ITEM_LAYOUT:
                return new EmptyViewHolder(itemView);
            case MIND_ITEM_LAYOUT:
                return new MindViewHolder(itemView);
            default:
                return null;
        }
    }
}
