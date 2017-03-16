# GankIOClient
利用[干货集中营](http://gank.io/api)的免费API，自制干货集中营应用。
主要是为了熟悉一些应用开发过程中常用的开源库，项目中使用到的依赖包
```
compile 'com.github.bumptech.glide:glide:3.7.0'
compile 'io.reactivex:rxandroid:1.2.1'
compile 'io.reactivex:rxjava:1.1.6'
compile 'com.squareup.retrofit2:retrofit:2.1.0'
compile 'com.squareup.retrofit2:converter-gson:2.1.0'
compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
compile 'com.squareup.okhttp3:okhttp:3.5.0'
compile 'com.android.support:cardview-v7:25.1.0'
compile 'com.google.code.gson:gson:2.8.0'
compile 'com.jakewharton:butterknife:8.4.0'
annotationProcessor 'com.jakewharton:butterknife-compiler:8.4.0'
compile 'com.astuetz:pagerslidingtabstrip:1.0.1'
compile 'cn.bingoogolapple:bga-refreshlayout:1.1.7@aar'
compile 'cn.bingoogolapple:bga-banner:2.1.4@aar'
```
应用中主要是用到<font color=red>干货集中营：分类数据</font></br>
分类数据: http://gank.io/api/data/数据类型/请求个数/第几页</br>
数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all</br>
请求个数： 数字，大于0</br>
第几页：数字，大于0</br>
例：</br>
[http://gank.io/api/data/Android/10/1](http://gank.io/api/data/Android/10/1)</br>
[http://gank.io/api/data/福利/10/1](http://gank.io/api/data/福利/10/1)</br>
[http://gank.io/api/data/iOS/20/2](http://gank.io/api/data/iOS/20/2)</br>
[http://gank.io/api/data/all/20/2](http://gank.io/api/data/iOS/20/2)</br>
</br>
搜索功能暂时没有集成，后续添加！~~</br>

# 效果图

![效果图1](https://github.com/onlyloveyd/GankIOClient/blob/master/gif/GIF1.gif)
![效果图2](https://github.com/onlyloveyd/GankIOClient/blob/master/gif/GIF2.gif)
![效果图3](https://github.com/onlyloveyd/GankIOClient/blob/master/gif/GIF3.gif)









