
package onlyloveyd.com.gankioclient.gsonbean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: DataBean
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：单类数据Pojo
 */
public class DataBean {
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


}
