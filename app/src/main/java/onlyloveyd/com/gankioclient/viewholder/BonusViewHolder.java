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
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.gsonbean.ResultsBean;
import onlyloveyd.com.gankioclient.utils.Constant;
import onlyloveyd.com.gankioclient.utils.PublicTools;
import onlyloveyd.com.gankioclient.utils.RxPermissionUtils;

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
            Glide.with(itemView.getContext()).load(data.getUrl()).placeholder(
                    R.mipmap.empty_data).into(ivPic);

            final RxPermissions rxPermissions = RxPermissionUtils.getInstance();
            ibDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (rxPermissions != null) {
                        rxPermissions
                                .request(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                .subscribe(new Observer<Boolean>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Boolean granted) {
                                        if (granted) { // Always true pre-M
                                            // I can control the camera now
                                            Toast.makeText(itemView.getContext(), data.getDesc() +
                                                            Constant.SUFFIX_JPEG + "开始下载",
                                                    Toast.LENGTH_SHORT).show();
                                            Glide.with(itemView.getContext()).load(
                                                    data.getUrl()).asBitmap().into(
                                                    new SimpleTarget<Bitmap>() {
                                                        @Override
                                                        public void onResourceReady(
                                                                final Bitmap resource,
                                                                GlideAnimation<? super Bitmap>
                                                                        glideAnimation) {

                                                            Observer<Bitmap> subscriber =
                                                                    new Observer<Bitmap>() {
                                                                        @Override
                                                                        public void onSubscribe(
                                                                                @NonNull
                                                                                        Disposable d) {

                                                                        }

                                                                        @Override
                                                                        public void onNext(
                                                                                Bitmap s) {
                                                                        }

                                                                        @Override
                                                                        public void onError(
                                                                                Throwable e) {
                                                                            Toast.makeText(
                                                                                    itemView.getContext(),
                                                                                    data.getDesc() +
                                                                                            Constant.SUFFIX_JPEG
                                                                                            +
                                                                                            "下载失败",
                                                                                    Toast.LENGTH_SHORT).show();
                                                                            e.printStackTrace();
                                                                        }

                                                                        @Override
                                                                        public void onComplete() {
                                                                            Toast.makeText(
                                                                                    itemView.getContext(),
                                                                                    data.getDesc() +
                                                                                            Constant.SUFFIX_JPEG
                                                                                            +
                                                                                            "下载成功",
                                                                                    Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    };

                                                            Observable<Bitmap> observable =
                                                                    Observable.create(
                                                                            new ObservableOnSubscribe<Bitmap>() {
                                                                                @Override
                                                                                public void
                                                                                subscribe(
                                                                                        @NonNull
                                                                                                ObservableEmitter<Bitmap> emitter)
                                                                                        throws
                                                                                        Exception {
                                                                                    try {
                                                                                        PublicTools.saveBitmap(
                                                                                                resource,
                                                                                                Environment
                                                                                                        .getExternalStorageDirectory().getAbsolutePath()
                                                                                                        + File.separator
                                                                                                        + data.getDesc());
                                                                                    } catch (Exception e) {
                                                                                        e.printStackTrace();
                                                                                        emitter.onError(
                                                                                                e);
                                                                                    }
                                                                                    emitter.onComplete();
                                                                                }
                                                                            });

                                                            observable.subscribeOn(Schedulers.io())
                                                                    .unsubscribeOn(Schedulers.io())
                                                                    .observeOn(
                                                                            AndroidSchedulers
                                                                                    .mainThread())
                                                                    .subscribe(subscriber);
                                                        }
                                                    });
                                        } else {
                                            Toast.makeText(itemView.getContext(), "请在设置中开启存储权限后再试",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {

                                    }
                                });
                    }
                }
            });
        }
    }
}
