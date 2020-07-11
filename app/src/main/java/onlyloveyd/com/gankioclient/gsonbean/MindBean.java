
package onlyloveyd.com.gankioclient.gsonbean;

import onlyloveyd.com.gankioclient.decorate.Visitable;
import onlyloveyd.com.gankioclient.factory.TypeFactory;

/**
 * 文 件 名: MindBean
 * 创 建 人: 易冬
 * 创建日期: 2017/5/11 16:43
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：
 */
public class MindBean implements Visitable {
    String url;
    String title;
    String author;
    String time;

    public String getUrl() {
        return "http://gank.io" + url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MindBean{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
