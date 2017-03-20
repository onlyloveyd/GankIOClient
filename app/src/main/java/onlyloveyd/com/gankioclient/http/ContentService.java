package onlyloveyd.com.gankioclient.http;

import onlyloveyd.com.gankioclient.gsonbean.HttpBean;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lisa on 2016/12/19.
 * Email: 457420045@qq.com
 */

public interface ContentService {

    /* category:  福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * pagesize： 数字，大于0
     * pagenum ： 数字，大于0 */

    @GET("{category}/{pagesize}/{pagenum}")
    Observable<HttpBean> getContent(@Path("category") String category ,
                                    @Path("pagesize") String pagesize ,
                                    @Path("pagenum") int pagenum );


    /**
     * 获取某天的干货
     *
     * @param date
     * @return
     */
    @GET("day/{date}")
    Observable<HttpBean> getRecentlyGanHuo(@Path("date") String date);

    /**
     * 搜索
     *
     * @param keyword
     * @param pageIndex
     * @return
     */
    @GET("search/query/{keyword}/category/{category}/count/20/page/{pageIndex}")
    Observable<HttpBean> search(
            @Path("category") String category
            , @Path("keyword") String keyword
            , @Path("pageIndex") int pageIndex);

    @GET("history/content/10/{pageIndex}")
    Observable<HttpBean> getRecently(@Path("pageIndex") int pageIndex);
}
