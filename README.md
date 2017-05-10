# 技术船
![icon](/app/src/main/res/mipmap-xxhdpi/ic_header.png "")

每日提供技术干货的 App

## 下载
[fir](https://fir.im/md5y) 

[酷安](http://www.coolapk.com/apk/onlyloveyd.com.gankioclient) 

## 干货数据源
应用中的所有干货数据均来自[干货集中营](http://gank.io/)。

## App设计
色调：远州鼠、落栗、苏芳、石竹、枯草、柳煤竹茶、锖青磁、鸠羽紫八种淡雅的颜色，分别对应不同的干货数据。
布局：简约风格，极简主义，去繁就简。
排版：最简单的Toolbar + RecyclerView

## UI设计
1. 黑白灰
2. 圆角
3. 材料设计

## 代码设计
1. Retrofit+RxJava 的组合获取网络数据;
2. FlycoTabLayout作为主界面结构，配合Fragment，实现“每日干货”，“分类数据”，“妹纸图片”和关于四个界面;
3. BGARefreshLayout配合RecyclerView和自定义多类型Item结构实现干货数据的展示;
4. MaterialAbout作为“关于”界面的基础；
5. Glide作为图片加载框架；
6. RxJava+Retrofit配合fir.im的接口实现版本更新功能；
7. Share功能全部采用系统自带，基本可以满足需求。
8. ButterKnife注解库
9. Gson作为json数据解析库

   
## 依赖库   
* [Glide](https://github.com/bumptech/glide)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [RxJava](https://github.com/ReactiveX/RxJava)
* [Retrofit](https://github.com/square/retrofit)
* [okhttp3](https://github.com/square/okhttp)
* [Gson](https://github.com/google/gson)
* [ButterKnife](https://github.com/JakeWharton/butterknife)
* [BGARefreshLayout-Android](https://github.com/bingoogolapple/BGARefreshLayout-Android)
* [FlycoTabLayout](https://github.com/H07000223/FlycoTabLayout)
* [MaterialAbout](https://github.com/jrvansuita/MaterialAbout)


## Author
* [CSDN](http://blog.csdn.net/poorkick)
* [Website](http://www.onlyloveyd.cn/)
* [掘金](https://juejin.im/user/583e860867f356006bbedb90)
* ![个人公众号](/app/src/main/res/mipmap-xxhdpi/qrcode.jpg "")

## 效果图
![](/screenshot/Screenshot_20170510-093041.png)![](/screenshot/Screenshot_20170510-093046.png)
![](/screenshot/Screenshot_20170510-093056.png)![](/screenshot/Screenshot_20170510-093102.png)
![](/screenshot/Screenshot_20170510-093108.png)![](/screenshot/Screenshot_20170510-093123.png)
![](/screenshot/Screenshot_20170510-093130.png)![](/screenshot/Screenshot_20170510-093150.png)

