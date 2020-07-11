package onlyloveyd.com.gankioclient.fragment;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

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
    public void initData() {
        mBinding.rlGankRefresh.autoRefresh();
    }


    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        getContent(Constant.BONUS, 1);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshLayout) {
        getContent(Constant.BONUS, ++pagenum);
    }
}
