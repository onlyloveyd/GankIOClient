package onlyloveyd.com.gankioclient.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import onlyloveyd.com.gankioclient.R;
import onlyloveyd.com.gankioclient.utils.Constant;

/**
 * 文 件 名: AboutFragment
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：关于界面
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, Constant.APP_FIR_IM_URL);


        AboutView view = AboutBuilder.with(getContext())
                .setPhoto(R.mipmap.ic_launcher)
                .setCover(R.mipmap.profile_cover)
                .setName(getString(R.string.about_name))
                .setSubTitle(getString(R.string.about_subtitle))
                .setBrief(getString(R.string.about_brief))
//                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGitHubLink(R.string.about_github)
                .addEmailLink(R.string.about_email)
                .addAndroidLink(R.string.about_android_csdn)
//                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addAction(com.vansuita.materialabout.R.mipmap.share,
                        com.vansuita.materialabout.R.string.share_app, Intent.createChooser(intent,
                                getString(com.vansuita.materialabout.R.string.share_app)))
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();
        return view;
    }


}
