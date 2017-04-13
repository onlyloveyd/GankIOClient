基于Gank.io的Android客户端，主要使用的是分类数据。最近利用业余时间抽空整理了一下这个应用。配色采用的是坚果手机的8种颜色，分别对应不同的Gank数据，不知颜色出处，只觉得挺好看。

*********************
![这里写图片描述](http://img.blog.csdn.net/20170413220349937)
*********************
![这里写图片描述](http://img.blog.csdn.net/20170413220410730)
*********************
![这里写图片描述](http://img.blog.csdn.net/20170413220424449)
*********************
![这里写图片描述](http://img.blog.csdn.net/20170413220733876)
*********************
![这里写图片描述](http://img.blog.csdn.net/20170413220449922)
*********************
![这里写图片描述](http://img.blog.csdn.net/20170413220506297)
*********************

## 开源库

```
//图片加载
compile 'com.github.bumptech.glide:glide:3.7.0'
//响应式编程和网络请求处理 RxAndroid + Retrofit
compile 'io.reactivex:rxandroid:1.2.1'
compile 'io.reactivex:rxjava:1.1.6'
compile 'com.squareup.retrofit2:retrofit:2.1.0'
compile 'com.squareup.retrofit2:converter-gson:2.1.0'
compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
compile 'com.squareup.okhttp3:okhttp:3.5.0'
//gson 数据解析
compile 'com.google.code.gson:gson:2.8.0'
//butterknife 注解
compile 'com.jakewharton:butterknife:8.4.0'
annotationProcessor 'com.jakewharton:butterknife-compiler:8.4.0'

//TabLyout升级版
compile 'com.flyco.tablayout:FlycoTabLayout_Lib:2.1.2@aar'
//下拉刷新
compile 'cn.bingoogolapple:bga-refreshlayout:1.1.7@aar'
//关于界面
compile 'com.github.jrvansuita:MaterialAbout:0.1.2'
```
