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
public class DailyBean {

    /**
     * category : ["Android","iOS","前端","瞎推荐","App","福利","休息视频","拓展资源"]
     * error : false
     * results : {"Android":[{"_id":"58c264e6421aa90f13178640","createdAt":"2017-03-10T16:33:42.304Z","desc":"基于 RecyclerView 实现的横向滑动组件，超级漂亮和实用。","images":["http://img.gank.io/3eaa6a41-b7bc-44ba-8663-818c34e636af","http://img.gank.io/64a30537-7ef8-4d0a-a188-1ec1bd2e7e1b"],"publishedAt":"2017-03-13T12:37:59.782Z","source":"web","type":"Android","url":"https://github.com/yarolegovich/DiscreteScrollView","used":true,"who":"Yaroslav"},{"_id":"58c5ff58421aa90efc4fb6b1","createdAt":"2017-03-13T10:09:28.493Z","desc":"自定义LayoutAnimationController，一行代码为ViewGroup设置定制顺序的布局动画！","images":["http://img.gank.io/9e530e56-24a7-4bec-88b8-d4dc0c21a5d1"],"publishedAt":"2017-03-13T12:37:59.782Z","source":"web","type":"Android","url":"https://github.com/HuanHaiLiuXin/ILayoutAnimationController","used":true,"who":"幻海流心"},{"_id":"58c60adb421aa90f13178652","createdAt":"2017-03-13T10:58:35.821Z","desc":"VirtualLayout是一个针对RecyclerView的LayoutManager扩展, 主要提供一整套布局方案和布局间的组件复用的问题","images":["http://img.gank.io/6822e846-cf2d-4649-a5ed-5414bd4b7cce"],"publishedAt":"2017-03-13T12:37:59.782Z","source":"chrome","type":"Android","url":"https://github.com/alibaba/vlayout/blob/master/README-ch.md","used":true,"who":"Dear宅学长"},{"_id":"58c6138c421aa90f13178655","createdAt":"2017-03-13T11:35:40.3Z","desc":"Android开发安全手册","publishedAt":"2017-03-13T12:37:59.782Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/500f1fd13b9b","used":true,"who":"Tang Likang"},{"_id":"58c613c5421aa95810795c52","createdAt":"2017-03-13T11:36:37.299Z","desc":"Atlas - 带你重返App开发的田园时代","publishedAt":"2017-03-13T12:37:59.782Z","source":"chrome","type":"Android","url":"http://atlas.taobao.org/","used":true,"who":"wuzheng"},{"_id":"58c62000421aa90f13178657","createdAt":"2017-03-13T12:28:48.583Z","desc":"一款精致漂亮的日历组件","images":["http://img.gank.io/cac8c663-fe34-476b-99f1-e6025541156a"],"publishedAt":"2017-03-13T12:37:59.782Z","source":"chrome","type":"Android","url":"https://github.com/prolificinteractive/material-calendarview","used":true,"who":"带马甲"}],"App":[{"_id":"58c56618421aa95810795c49","createdAt":"2017-03-12T23:15:36.502Z","desc":"📱一款追求全新用户体验的干货集中营 iOS客户端","images":["http://img.gank.io/f772bc0b-c973-42c9-bfdb-3b16be2886f4","http://img.gank.io/66ab873a-6050-45c5-ac00-1a31e7cf5621"],"publishedAt":"2017-03-13T12:37:59.782Z","source":"web","type":"App","url":"https://github.com/iphone5solo/Gank","used":true,"who":"CoderKo1o"}],"iOS":[{"_id":"58c2907e421aa90efc4fb6a6","createdAt":"2017-03-10T19:39:42.361Z","desc":"在Mac的菜单栏显示Github的总星数、followers数和通知数，同时可以在图标中看到自己每天Github数据的变化","images":["http://img.gank.io/673864b2-1762-412f-80fc-e0af9ebb1d4f"],"publishedAt":"2017-03-13T12:37:59.782Z","source":"web","type":"iOS","url":"https://github.com/Nightonke/Gitee","used":true,"who":"Weiping Huang"},{"_id":"58c2dc2c421aa90efc4fb6a8","createdAt":"2017-03-11T01:02:36.171Z","desc":"iOS 开发利器之 PaintCode","images":["http://img.gank.io/369a4163-fe36-4a86-bae7-54d1bd6bf15e"],"publishedAt":"2017-03-13T12:37:59.782Z","source":"chrome","type":"iOS","url":"http://www.imlifengfeng.com/blog/?p=602","used":true,"who":"feng"},{"_id":"58c5246b421aa95810795c47","createdAt":"2017-03-12T18:35:23.130Z","desc":"iOS开发优秀技术博客和软件推荐","publishedAt":"2017-03-13T12:37:59.782Z","source":"chrome","type":"iOS","url":"https://github.com/imlifengfeng/iOSBlogAndTools","used":true,"who":"feng"}],"休息视频":[{"_id":"58c5f5be421aa95810795c4d","createdAt":"2017-03-13T09:28:30.339Z","desc":"腹黑小孩疯狂报复短片《我的生涯规划》","publishedAt":"2017-03-13T12:37:59.782Z","source":"chrome","type":"休息视频","url":"http://www.vmovier.com/51291","used":true,"who":"lxxself"}],"前端":[{"_id":"58c2d88a421aa90f13178646","createdAt":"2017-03-11T00:47:06.634Z","desc":"ReactRouter-V4 构建之道与源码分析","publishedAt":"2017-03-13T12:37:59.782Z","source":"chrome","type":"前端","url":"https://zhuanlan.zhihu.com/p/25696969","used":true,"who":"王下邀月熊"},{"_id":"58c50a0c421aa90efc4fb6ae","createdAt":"2017-03-12T16:42:52.106Z","desc":"本篇文章主要介绍一下测试覆盖率的概念以及如何将测试覆盖率的 badge 添加到 README.md 中。","images":["http://img.gank.io/114a9e4c-3f37-41b1-ac6b-65d5c7514f69"],"publishedAt":"2017-03-13T12:37:59.782Z","source":"web","type":"前端","url":"http://blog.xcatliu.com/2017/03/04/test_coverage_for_github/","used":true,"who":"xcatliu"}],"拓展资源":[{"_id":"58c61d88421aa90efc4fb6b5","createdAt":"2017-03-13T12:18:16.940Z","desc":"最全的面试试题集合，覆盖各个领域","images":["http://img.gank.io/87ff8d32-38bb-427f-84f6-c81db2e959a4"],"publishedAt":"2017-03-13T12:37:59.782Z","source":"chrome","type":"拓展资源","url":"https://github.com/kdn251/interviews","used":true,"who":"带马甲"}],"瞎推荐":[{"_id":"58c55648421aa90efc4fb6b0","createdAt":"2017-03-12T22:08:08.496Z","desc":"面试算法实践与国外大厂习题指南","publishedAt":"2017-03-13T12:37:59.782Z","source":"chrome","type":"瞎推荐","url":"https://zhuanlan.zhihu.com/p/25719965","used":true,"who":"王下邀月熊"}],"福利":[{"_id":"58c5e184421aa90f1317864d","createdAt":"2017-03-13T08:02:12.179Z","desc":"3-13","publishedAt":"2017-03-13T12:37:59.782Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-13-17265708_396005157434387_3099040288153272320_n.jpg","used":true,"who":"dmj"}]}
     */

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
        } catch ( JSONException e) {
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

        public static class DetailsBean {
            @Override
            public String toString() {
                return "DetailsBean{" +
                        "_id='" + _id + '\'' +
                        ", createdAt='" + createdAt + '\'' +
                        ", desc='" + desc + '\'' +
                        ", publishedAt=" + publishedAt +
                        ", source='" + source + '\'' +
                        ", type='" + type + '\'' +
                        ", url='" + url + '\'' +
                        ", used=" + used +
                        ", who='" + who + '\'' +
                        ", images=" + images +
                        '}';
            }

            /**
             * _id : 58c264e6421aa90f13178640
             * createdAt : 2017-03-10T16:33:42.304Z
             * desc : 基于 RecyclerView 实现的横向滑动组件，超级漂亮和实用。
             * images : ["http://img.gank.io/3eaa6a41-b7bc-44ba-8663-818c34e636af","http://img.gank.io/64a30537-7ef8-4d0a-a188-1ec1bd2e7e1b"]
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
        }

        @Override
        public String toString() {
            return "ResultsBean{" +
                    "Android=" + Android +
                    ", App=" + App +
                    ", iOS=" + iOS +
                    ", Video=" + Video +
                    ", Js=" + Js +
                    ", Res=" + Res +
                    ", Rec=" + Rec +
                    ", Bonus=" + Bonus +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DailyBean{" +
                "error=" + error +
                ", results=" + results +
                ", category=" + category +
                '}';
    }
}

