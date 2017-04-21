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
package onlyloveyd.com.gankioclient.gsonbean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
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
 * 文 件 名: DailyBean
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：每日干货Pojo
 */
public class DailyBean {
    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public static DailyBean objectFromData(String str) {

        return new Gson().fromJson(str, DailyBean.class);
    }

    public static DailyBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), DailyBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<DailyBean> arrayDailyBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<DailyBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<DailyBean> arrayDailyBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<DailyBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "DailyBean{"
                + "error="
                + error
                + ", results="
                + results
                + ", category="
                + category
                + '}';
    }

    public static class ResultsBean {
        @SerializedName("Android")
        private List<DetailsBean> Android;
        @SerializedName("App")
        private List<DetailsBean> App;
        @SerializedName("iOS")
        private List<DetailsBean> iOS;
        @SerializedName("休息视频")
        private List<DetailsBean> Video;
        @SerializedName("前端")
        private List<DetailsBean> Js;
        @SerializedName("拓展资源")
        private List<DetailsBean> Res;
        @SerializedName("瞎推荐")
        private List<DetailsBean> Rec;
        @SerializedName("福利")
        private List<DetailsBean> Bonus;

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

        public List<DetailsBean> getAndroid() {
            return Android;
        }

        public void setAndroid(List<DetailsBean> Android) {
            this.Android = Android;
        }

        public List<DetailsBean> getApp() {
            return App;
        }

        public void setApp(List<DetailsBean> App) {
            this.App = App;
        }

        public List<DetailsBean> getIOS() {
            return iOS;
        }

        public void setIOS(List<DetailsBean> iOS) {
            this.iOS = iOS;
        }

        public List<DetailsBean> getVideo() {
            return Video;
        }

        public void setVideo(List<DetailsBean> video) {
            this.Video = video;
        }

        public List<DetailsBean> getJs() {
            return Js;
        }

        public void setJs(List<DetailsBean> js) {
            this.Js = js;
        }

        public List<DetailsBean> getRes() {
            return Res;
        }

        public void setRes(List<DetailsBean> res) {
            this.Res = res;
        }

        public List<DetailsBean> getRec() {
            return Rec;
        }

        public void setRec(List<DetailsBean> rec) {
            this.Rec = rec;
        }

        public List<DetailsBean> getBonus() {
            return Bonus;
        }

        public void setBonus(List<DetailsBean> bonus) {
            this.Bonus = bonus;
        }

        @Override
        public String toString() {
            return "ResultsBean{"
                    + "Android="
                    + Android
                    + ", App="
                    + App
                    + ", iOS="
                    + iOS
                    + ", Video="
                    + Video
                    + ", Js="
                    + Js
                    + ", Res="
                    + Res
                    + ", Rec="
                    + Rec
                    + ", Bonus="
                    + Bonus
                    + '}';
        }

        public static class DetailsBean implements Visitable {
            /**
             * _id : 58c264e6421aa90f13178640
             * createdAt : 2017-03-10T16:33:42.304Z
             * desc : 基于 RecyclerView 实现的横向滑动组件，超级漂亮和实用。
             * images : ["http://img.gank.io/3eaa6a41-b7bc-44ba-8663-818c34e636af","http://img
             * .gank.io/64a30537-7ef8-4d0a-a188-1ec1bd2e7e1b"]
             * publishedAt : 2017-03-13T12:37:59.782Z
             * source : web
             * type : Android
             * url : https://github.com/yarolegovich/DiscreteScrollView
             * used : true
             * who : Yaroslav
             */

            private String _id;
            private String createdAt;
            private String desc;
            private Date publishedAt;
            private String source;
            private String type;
            private String url;
            private boolean used;
            private String who;
            private List<String> images;

            public static DetailsBean objectFromData(String str) {

                return new Gson().fromJson(str, DetailsBean.class);
            }

            public static DetailsBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), DetailsBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<DetailsBean> arrayAndroidBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DetailsBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<DetailsBean> arrayAndroidBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<DetailsBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();
            }

            @Override
            public String toString() {
                return "DetailsBean{"
                        + "_id='"
                        + _id
                        + '\''
                        + ", createdAt='"
                        + createdAt
                        + '\''
                        + ", desc='"
                        + desc
                        + '\''
                        + ", publishedAt="
                        + publishedAt
                        + ", source='"
                        + source
                        + '\''
                        + ", type='"
                        + type
                        + '\''
                        + ", url='"
                        + url
                        + '\''
                        + ", used="
                        + used
                        + ", who='"
                        + who
                        + '\''
                        + ", images="
                        + images
                        + '}';
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public Date getPublishedAt() {
                return publishedAt;
            }

            public void setPublishedAt(Date publishedAt) {
                this.publishedAt = publishedAt;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
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

            public boolean isUsed() {
                return used;
            }

            public void setUsed(boolean used) {
                this.used = used;
            }

            public String getWho() {
                return who;
            }

            public void setWho(String who) {
                this.who = who;
            }

            public List<String> getImages() {
                return images;
            }

            public void setImages(List<String> images) {
                this.images = images;
            }

            @Override
            public int type(TypeFactory typeFactory) {
                return typeFactory.type(this);
            }
        }
    }
}

