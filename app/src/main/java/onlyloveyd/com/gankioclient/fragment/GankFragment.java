package onlyloveyd.com.gankioclient.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import butterknife.BindView;
import butterknife.ButterKnife;
import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.adapter.GankPageAdapter;

/**
 * Created by lisa on 2016/12/22.
 * Email: 457420045@qq.com
 */

public class GankFragment extends Fragment {

    @BindView(R.id.psts_title)
    PagerSlidingTabStrip pstsTitle;
    @BindView(R.id.vp_gank)
    ViewPager vpGank;

    public static GankFragment newInstance() {
        Bundle args = new Bundle();
        GankFragment fragment = new GankFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (inflater.inflate(R.layout.fragment_gank, container, false));
        ButterKnife.bind(this, view);

        vpGank.setAdapter(new GankPageAdapter(getFragmentManager(), getContext()));
        pstsTitle.setViewPager(vpGank);
        pstsTitle.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
        pstsTitle.setTextColor(0xFF000000);
        pstsTitle.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               updateTextStyle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        updateTextStyle(0);
        return view;
    }

    private void updateTextStyle(int position) {

        //获取tabsContainer
        LinearLayout ll = (LinearLayout) pstsTitle.getChildAt(0);
        //获取Tab，这里因为我们自己知道只使用了TextView所以不处理其他情况
        for(int i = 0; i< ll.getChildCount(); i++) {
            TextView tv = (TextView) ll.getChildAt(i);
            if(i== position) {
                tv.setTextColor(0xFFFFFFFF);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            } else {
                tv.setTextColor(0xFF000000);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            }
        }

    }


}
