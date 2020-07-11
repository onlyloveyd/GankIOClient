
package onlyloveyd.com.gankioclient.gsonbean;

import onlyloveyd.com.gankioclient.decorate.Visitable;
import onlyloveyd.com.gankioclient.factory.TypeFactory;

/**
 * 文 件 名: EmptyBean
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 14:17
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述： 处理空数据和异常信息
 */
public class EmptyBean implements Visitable {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
