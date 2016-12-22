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

    /*category:  福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     *pagesize： 数字，大于0
      pagenum ： 数字，大于0*/

    @GET("{category}/{pagesize}/{pagenum}")
    Observable<HttpBean> getContent(@Path("category") String category ,
                                    @Path("pagesize") String pagesize ,
                                    @Path("pagenum") int pagenum );
}
