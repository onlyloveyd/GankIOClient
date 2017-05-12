package onlyloveyd.com.gankioclient.utils;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

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
public class RxPermissionUtils {
    private static RxPermissions mRxPermissions = null;

    public static void createInstance(Activity activity) {
        if (mRxPermissions == null) {
            mRxPermissions = new RxPermissions(activity);
        }
    }

    public static RxPermissions getInstance() {
        return mRxPermissions;
    }
}
