package onlyloveyd.com.gankioclient.gsonbean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import onlyloveyd.com.gankioclient.decorate.Visitable;
import onlyloveyd.com.gankioclient.factory.TypeFactory;

/**
 * Created by lingling on 2017/4/11.
 */

public class ResultsBean implements Visitable {
    private String desc;
    private String ganhuo_id;
    private String publishedAt;
    private String readability;
    private String type;
    private String url;
    private String who;

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }

    public static ResultsBean objectFromData(String str) {

        return new Gson().fromJson(str, ResultsBean.class);
    }

    public static ResultsBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), ResultsBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<ResultsBean> arrayResultsBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<ResultsBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<ResultsBean> arrayResultsBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<ResultsBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getGanhuo_id() {
        return ganhuo_id;
    }

    public void setGanhuo_id(String ganhuo_id) {
        this.ganhuo_id = ganhuo_id;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getReadability() {
        return readability;
    }

    public void setReadability(String readability) {
        this.readability = readability;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}