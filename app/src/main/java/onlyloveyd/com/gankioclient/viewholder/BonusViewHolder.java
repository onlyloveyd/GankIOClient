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

import android.graphics.Bitmap;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.File;

import onlyloveyd.com.gankioclient.BuildConfig;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.gsonbean.ResultsBean;
import onlyloveyd.com.gankioclient.utils.Constant;
import onlyloveyd.com.gankioclient.utils.PublicTools;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 文 件 名: BonusViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：福利ViewHolder
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
                    Toast.makeText(itemView.getContext(), data.getDesc() +
                                    Constant.SUFFIX_JPEG + "开始下载",
                            Toast.LENGTH_SHORT).show();
                    Glide.with(itemView.getContext()).load(data.getUrl()).asBitmap().into(
                            new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(final Bitmap resource,
                                        GlideAnimation<? super Bitmap> glideAnimation) {
                                    Subscriber<Bitmap> subscriber = new Subscriber<Bitmap>() {
                                        @Override
                                        public void onNext(Bitmap s) {
                                        }

                                        @Override
                                        public void onCompleted() {
                                            Toast.makeText(itemView.getContext(), data.getDesc() +
                                                            Constant.SUFFIX_JPEG + "下载成功",
                                                    Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Toast.makeText(itemView.getContext(), data.getDesc() +
                                                            Constant.SUFFIX_JPEG + "下载失败",
                                                    Toast.LENGTH_SHORT).show();
                                            e.printStackTrace();
                                        }
                                    };

                                    Observable observable = Observable.create(
                                            new Observable.OnSubscribe<Bitmap>() {
                                                @Override
                                                public void call(
                                                        Subscriber<? super Bitmap> subscriber) {
                                                    try {
                                                        PublicTools.saveBitmap(resource,
                                                                Environment
                                                                        .getExternalStorageDirectory().getAbsolutePath()
                                                                        + File.separator
                                                                        + itemView.getResources().getString(R.string.app_name)
                                                                        + File.separator
                                                                        + data.getDesc());
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                        subscriber.onError(e);
                                                    }
                                                    subscriber.onCompleted();
                                                }
                                            });

                                    observable.subscribeOn(Schedulers.io())
                                            .unsubscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(subscriber);
                                }
                            });
                }
            });
        }
    }
}
