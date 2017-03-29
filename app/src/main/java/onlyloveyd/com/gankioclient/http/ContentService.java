package onlyloveyd.com.gankioclient.http;

import onlyloveyd.com.gankioclient.gsonbean.DailyBean;
import onlyloveyd.com.gankioclient.gsonbean.DataBean;
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

    @GET("data/{category}/{pagesize}/{pagenum}")
    Observable<DataBean> getContent(
            @Path("category") String category, @Path("pagesize") String pagesize,
            @Path("pagenum") int pagenum);

    /**
     * 获取某天的干货
     */
    @GET("day/{date}")
    Observable<DataBean> getRecentlyGanHuo(@Path("date") String date);

    /**
     * 搜索
     */
    @GET("search/query/{keyword}/category/{category}/count/20/page/{pageIndex}")
    Observable<DataBean> search(@Path("category") String category, @Path("keyword") String keyword,
            @Path("pageIndex") int pageIndex);

    @GET("history/content/10/{pageIndex}")
    Observable<DataBean> getRecently(
            @Path("pageIndex") int pageIndex);

    /**
     * @param year  year
     * @param month month
     * @param day   day
     * @return Observable<GankDaily>
     */
    @GET("day/{year}/{month}/{day}")
    Observable<DailyBean> getDaily(@Path("year") int year,
            @Path("month") int month, @Path("day") int day);
}
