package onlyloveyd.com.gankioclient.fragment;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import onlyloveyd.com.gankioclient.utils.Constant;

/**
 * 文 件 名: BonusFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：福利界面
 */
public class BonusFragment extends GankFragment {
    int pagenum = 1;

    @Override
    public void initBGAData() {
        bgaRefreshLayout.beginRefreshing();
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        getContent(Constant.BONUS, 1);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        getContent(Constant.BONUS, ++pagenum);
        return true;
    }
}
