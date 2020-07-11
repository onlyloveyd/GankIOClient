
package onlyloveyd.com.gankioclient.fragment;

import android.os.Bundle;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import onlyloveyd.com.gankioclient.gsonbean.DataBean;
import onlyloveyd.com.gankioclient.http.HttpMethods;

/**
 * 文 件 名: GankFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：分类数据界面下每个数据显示界面
 */
public class GankFragment extends BaseFragment {
    int pagenum = 1;

    public static GankFragment newInstance(String arg) {
        Bundle args = new Bundle();
        args.putString("ARG", arg);
        GankFragment fragment = new GankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void getContent(final String category, int pagenum) {
        Observer<DataBean> observer = new Observer<DataBean>() {
            @Override
            public void onComplete() {
                endLoading();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                endLoading();
                onNetworkError();
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DataBean httpBean) {
                if (mBinding.rlGankRefresh.isLoading()) {
                } else {
                    mVisitableList.clear();
                }
                if (httpBean.getResults() == null || httpBean.getResults().size() == 0) {
                    onDataEmpty();
                } else {
                    mVisitableList.addAll(httpBean.getResults());
                }
                mMultiRecyclerAdapter.setData(mVisitableList);
            }
        };
        HttpMethods.getInstance().getData(observer, category, "10", pagenum);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        getContent(arg, 1);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshLayout) {
        getContent(arg, ++pagenum);
    }
}
