package onlyloveyd.com.gankioclient.viewholder;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.gsonbean.DataBean;
import onlyloveyd.com.gankioclient.gsonbean.ResultsBean;

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
public class BonusViewHolder extends BaseViewHolder<ResultsBean> {

    public BonusViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindViewData(final ResultsBean data) {

        if (data != null) {
            ImageView ivPic = (ImageView) getView(R.id.imgPicture);
            ImageButton ibDownload = (ImageButton) getView(R.id.ib_download);

            Glide.with(itemView.getContext()).load(data.getUrl()).into(ivPic);

            ibDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "start downloading ...",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
