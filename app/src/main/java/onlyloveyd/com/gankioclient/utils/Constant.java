package onlyloveyd.com.gankioclient.utils;

import java.util.ArrayList;
import java.util.HashMap;

import onlyloveyd.com.gankioclient.R;

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
public class Constant {

    public static String[] sTabTitles = {"每日干货", "分类阅读", "福利", "关于"};

    public static HashMap<String, Integer> sTypeColor = new HashMap<String, Integer>() {
        {
            put("Android", R.drawable.bg_android_tag);
            put("iOS", R.drawable.bg_ios_tag);
            put("瞎推荐", R.drawable.bg_rec_tag);
            put("拓展资源", R.drawable.bg_res_tag);
            put("App", R.drawable.bg_app_tag);
            put("福利", R.drawable.bg_bonus_tag);
            put("前端", R.drawable.bg_js_tag);
            put("休息视频", R.drawable.bg_video_tag);
        }
    };

    public static ArrayList<String> sCategoryList = new ArrayList<String>() {
        {
            add("all");
            add("Android");
            add("瞎推荐");
            add("iOS");
            add("前端");
            add("拓展资源");
            add("App");
            add("休息视频");
        }
    };
}
