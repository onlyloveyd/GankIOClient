/**
 * Copyright 2017 yidong
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package onlyloveyd.com.gankioclient.http;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import onlyloveyd.com.gankioclient.gsonbean.DailyBean;
import onlyloveyd.com.gankioclient.gsonbean.DataBean;
import onlyloveyd.com.gankioclient.gsonbean.SearchBean;
import onlyloveyd.com.gankioclient.gsonbean.VersionBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 文 件 名: HttpMethods
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：单例Retrofit
 */

public class HttpMethods {

    public static final String BASE_URL = "http://gank.io/api/";

    public static final String VERSION_CHECK_URL =
            "http://api.fir.im/apps/latest/58d37b7cca87a813140000ac?api_token"
                    + "=3dc58a8e3aafb6a54a72c279b8584b36";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private ContentService contentService;
    private OkHttpClient mOkHttpClient;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mOkHttpClient = httpClientBuilder.build();

        retrofit = new Retrofit.Builder().client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        contentService = retrofit.create(ContentService.class);
    }


    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取干货数据
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param category   类别
     * @param pagesize   请求数据个数
     * @param pagenum    页码
     */
    public void getData(Observer<DataBean> subscriber, String category, String pagesize,
            int pagenum) {
        contentService.getContent(category, pagesize, pagenum)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 查询干货数据
     */
    public void searchData(Observer<SearchBean> subscriber, String keyword, String category,
            int pageindex) {
        contentService.search(category, keyword, pageindex)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取每日数据
     */
    public void getDailyData(Observer<DailyBean> subscriber, int year, int month, int day) {
        contentService.getDaily(year, month, day)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 下载应用
     */
    public void downloadApk(Observer<ResponseBody> subscriber, String url) {
        contentService.downloadUrl(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 获取最新版本信息
     */
    public void getVersionInfoFromFIR(Observer<VersionBean> subscriber, String url) {
        contentService.getVersionInfoFromFIR(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }
}