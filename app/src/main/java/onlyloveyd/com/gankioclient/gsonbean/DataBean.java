package onlyloveyd.com.gankioclient.gsonbean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import onlyloveyd.com.gankioclient.decorate.Visitable;
import onlyloveyd.com.gankioclient.factory.TypeFactory;

/**
 * Created by lisa on 2016/12/19.
 * Email: 457420045@qq.com
 */

public class DataBean implements Visitable {

    /**
     * error : false
     * results : [{"_id":"584fbd41421aa93437406724","createdAt":"2016-12-13T17:20:01.704Z",
     * "desc":"简单的Android社会化分享登录库，一行代码搞定","images":["http://img.gank
     * .io/f0e08927-9797-4728-821e-ebe36c3b95fc","http://img.gank
     * .io/72fc2885-b61a-42fc-8a3a-cf8d4706fa8d"],"publishedAt":"2016-12-14T11:39:22.686Z",
     * "source":"web","type":"Android","url":"https://github.com/shaohui10086/ShareUtil",
     * "used":true,"who":"邵辉Vista"},{"_id":"5850b313421aa97237bca8a3",
     * "createdAt":"2016-12-14T10:48:51.805Z","desc":"Webview
     * 伸缩 Toolbar 效果","images":["http://img.gank.io/3add8949-b19b-454e-9ace-cc9f7dd39cd9",
     * "http://img.gank.io/faa3362a-b7af-44a6-8941-27d2a49bba21"],
     * "publishedAt":"2016-12-14T11:39:22.686Z","source":"chrome","type":"Android",
     * "url":"https://github.com/RameshBhupathi/CollapsingToolbar-With-Webview","used":true,
     * "who":"代码家"},{"_id":"584172ca421aa939befafafe","createdAt":"2016-12-02T21:10:34.47Z",
     * "desc":"Android路由动态配置方案\u2014\u2014可能是最简单的热更新实现","publishedAt":"2016-12-13T11:42:38.536Z",
     * "source":"web","type":"Android","url":"http://www.sixwolf
     * .net/blog/2016/12/02/%E7%83%AD%E6%9B%B4%E6%96%B0%E6%96%B9%E6%A1%88%E4%B9%8B%E8%B7%AF%E7%94
     * %B1%E5%8A%A8%E6%80%81%E9%85%8D%E7%BD%AE/","used":true,"who":"Tang
     * Likang"},{"_id":"584a5e3f421aa963eaaee153","createdAt":"2016-12-09T15:33:19.136Z",
     * "desc":"类似微博标签的文本控件","images":["http://img.gank.io/da4abdc8-c25d-4559-9f63-b5327e89a3b8"],
     * "publishedAt":"2016-12-13T11:42:38.536Z","source":"web","type":"Android",
     * "url":"https://github.com/limedroid/TagEditText","used":true,"who":null},
     * {"_id":"584a65d1421aa963f321b049","createdAt":"2016-12-09T16:05:37.390Z","desc":"Android
     * google中文开发网站","publishedAt":"2016-12-13T11:42:38.536Z","source":"web","type":"Android",
     * "url":"https://developer.android.google.cn/index.html","used":true,"who":"Stefan
     * Cheung"},{"_id":"584e3afe421aa963ed506514","createdAt":"2016-12-12T13:51:58.913Z",
     * "desc":"TwinklingRefreshLayout
     * 小而强大的刷新控件，自带顺滑的越界回弹，v1.04 版精心重构，完美发布","images":["http://img.gank
     * .io/b11249e0-87bf-44b3-8c07-6ee3d4d2ba41","http://img.gank
     * .io/8398b673-e920-4639-924f-4e315115e85b","http://img.gank
     * .io/52efd8c3-699d-4500-8576-95e54bcb9de7"],"publishedAt":"2016-12-13T11:42:38.536Z",
     * "source":"web","type":"Android","url":"https://github
     * .com/lcodecorex/TwinklingRefreshLayout","used":true,"who":null},
     * {"_id":"584e3b86421aa963efd90dde","createdAt":"2016-12-12T13:54:14.747Z",
     * "desc":"仿最美应用的底栏，使用recyclerView实现","images":["http://img.gank
     * .io/0024dda7-b534-44ab-9e8f-85112da0f84c"],"publishedAt":"2016-12-13T11:42:38.536Z",
     * "source":"web","type":"Android","url":"https://github.com/huage2580/ZuiMeiTAG","used":true,
     * "who":"狸小华"},{"_id":"584f6af1421aa9343afb70f5","createdAt":"2016-12-13T11:28:49.889Z",
     * "desc":"Android
     * 图片拼图效果","images":["http://img.gank.io/5b432874-f994-4801-b8fd-2f3b9716670e"],
     * "publishedAt":"2016-12-13T11:42:38.536Z","source":"chrome","type":"Android",
     * "url":"https://github.com/lopei/collageview","used":true,"who":"代码家"},
     * {"_id":"584f6b8a421aa9343afb70f7","createdAt":"2016-12-13T11:31:22.417Z","desc":"Android
     * 手写体数字识别","images":["http://img.gank.io/b0f8d182-9e76-41b7-81ef-35c80bcf7f83"],
     * "publishedAt":"2016-12-13T11:42:38.536Z","source":"chrome","type":"Android",
     * "url":"https://github.com/harjot-oberai/AndroidDigitClassifier","used":true,"who":"代码家"},
     * {"_id":"584a6b99421aa963efd90db6","createdAt":"2016-12-09T16:30:17.652Z","desc":"谷歌开发者中文博客",
     * "publishedAt":"2016-12-12T11:30:54.254Z","source":"web","type":"Android",
     * "url":"http://developers.googleblog.cn/","used":true,"who":"Stefan
     * Cheung"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public static DataBean objectFromData(String str) {

        return new Gson().fromJson(str, DataBean.class);
    }

    public static List<DataBean> arrayHttpBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<DataBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "DataBean{" + "error=" + error + ", results=" + results.toString() + '}';
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

}
