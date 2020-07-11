
package onlyloveyd.com.gankioclient.fragment;

import android.os.Bundle;

import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import onlyloveyd.com.gankioclient.gsonbean.MindBean;

/**
 * 文 件 名: MindFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/5/11 16:39
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
public class MindFragment extends BaseFragment {

    public static MindFragment newInstance() {

        Bundle args = new Bundle();

        MindFragment fragment = new MindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {
        mBinding.rlGankRefresh.autoRefresh();
    }

    public void getContent(final String category, int pagenum) {
        Observer<ArrayList<MindBean>> observer = new Observer<ArrayList<MindBean>>() {
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                endLoading();
                onNetworkError();
            }

            @Override
            public void onComplete() {
                endLoading();
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ArrayList<MindBean> mindBeanArrayList) {
                if (mBinding.rlGankRefresh.isLoading()) {
                } else {
                    mVisitableList.clear();
                }
                if (mindBeanArrayList.size() == 0) {
                    onDataEmpty();
                } else {
                    mVisitableList.addAll(mindBeanArrayList);
                }
                mMultiRecyclerAdapter.setData(mVisitableList);
            }
        };

        Observable<ArrayList<MindBean>> observable = Observable.create(
                new ObservableOnSubscribe<ArrayList<MindBean>>() {
                    @Override
                    public void subscribe(ObservableEmitter<ArrayList<MindBean>> emitter)
                            throws Exception {
                        ArrayList<MindBean> mindBeanArrayList = new ArrayList<MindBean>();
                        try {
                            Document doc = null;
                            try {
                                doc = Jsoup.connect("https://gank.io/post/published").get();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Elements trs = doc.select("table").select("tr");
                            for (int i = 0; i < trs.size(); i++) {
                                MindBean bean = new MindBean();
                                Element time = trs.get(i).select("td").get(1);
                                bean.setTime(time.text());

                                Element detail = trs.get(i).select("td").get(0);
                                String url = detail.select("a").attr("href");
                                bean.setUrl(url);
                                bean.setTitle(detail.select("a").text());
                                bean.setAuthor(detail.select("small").text());
                                mindBeanArrayList.add(bean);
                                System.err.println("yidong bean = " + bean);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            emitter.onError(e);
                        }
                        emitter.onNext(mindBeanArrayList);
                        emitter.onComplete();
                    }
                });

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        getContent(arg, 1);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshLayout) {
    }
}
