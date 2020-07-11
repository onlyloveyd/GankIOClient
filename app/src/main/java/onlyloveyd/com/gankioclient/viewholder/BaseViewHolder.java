

package onlyloveyd.com.gankioclient.viewholder;

import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


/**
 * 文 件 名: BaseViewHolder
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：ViewHolder 泛型基类
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    SparseArray<View> mViews;
    View mItemView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        mViews = new SparseArray<>();
    }

    public View getView(int resId) {
        View view = mViews.get(resId);

        if (view == null) {
            view = mItemView.findViewById(resId);
            mViews.put(resId, view);
        }
        return view;
    }

    public abstract void bindViewData(T data);
}