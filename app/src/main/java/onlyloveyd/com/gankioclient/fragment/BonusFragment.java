package onlyloveyd.com.gankioclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.MultiRecyclerAdapter;
import onlyloveyd.com.gankioclient.decorate.Visitable;
import onlyloveyd.com.gankioclient.gsonbean.DataBean;
import onlyloveyd.com.gankioclient.http.HttpMethods;
import onlyloveyd.com.gankioclient.utils.Constant;
import onlyloveyd.com.gankioclient.utils.PublicTools;
import rx.Subscriber;
import rx.exceptions.OnErrorFailedException;

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
        getContent(Constant.BONUS, 1);
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
