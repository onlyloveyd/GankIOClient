package onlyloveyd.com.gankioclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import onlyloveyd.com.gankioclient.R;

/**
 * Created by lisa on 2016/12/26.
 * Email: 457420045@qq.com
 */

public class AboutFragment extends Fragment {


    public static AboutFragment newInstance() {

        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, coordinatorLayout);
        return coordinatorLayout;
    }


}
