package onlyloveyd.com.gankioclient.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import onlyloveyd.com.gankioclient.gsonbean.HttpBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lisa on 2016/12/19.
 * Email: 457420045@qq.com
 */

public class HttpMethods {

    public static final String BASE_URL = "http://gank.io/api/data/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private ContentService contentService;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        contentService = retrofit.create(ContentService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取干货数据
     * @param subscriber 由调用者传过来的观察者对象
     * @param category 类别
     * @param pagesize 请求数据个数
     * @param pagenum  页码
     */
    public void getData(Subscriber<HttpBean> subscriber, String category, String pagesize, int pagenum){
        contentService.getContent(category, pagesize, pagenum)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}