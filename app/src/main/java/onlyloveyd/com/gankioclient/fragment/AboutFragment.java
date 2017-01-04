package onlyloveyd.com.gankioclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    @BindView(R.id.bgabanner)
    BGABanner bgabanner;
    @BindView(R.id.civ_profile)
    ImageView civProfile;
    @BindView(R.id.tv_github)
    TextView tvGithub;
    @BindView(R.id.tv_blog)
    TextView tvBlog;

    public static AboutFragment newInstance() {

        Bundle args = new Bundle();

        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, linearLayout);
        bgabanner.setData(R.mipmap.banner_one,R.mipmap.banner_two, R.mipmap.banner_three,
                          R.mipmap.banner_four, R.mipmap.banner_five);
        return linearLayout;
    }


}
