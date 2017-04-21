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
package onlyloveyd.com.gankioclient.viewholder;

import android.view.View;
import android.widget.TextView;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.gsonbean.EmptyBean;

/**
 * 文 件 名: EmptyViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 14:19
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：处理空数据和异常信息
 */
public class EmptyViewHolder extends BaseViewHolder<EmptyBean> {

    public EmptyViewHolder(View itemView) {
        super(itemView);
    }
    @Override
    public void bindViewData(EmptyBean data) {
        TextView textView =  (TextView)getView(R.id.tv_empty);
        if(textView!= null) {
            textView.setText(data.getMessage());
        }
    }
}
