
package onlyloveyd.com.gankioclient.decorate;

import onlyloveyd.com.gankioclient.factory.TypeFactory;

/**
 * 文 件 名: Visitable
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：bean实现接口，方便统一处理
 */
public interface Visitable {
    int type(TypeFactory typeFactory);
}
