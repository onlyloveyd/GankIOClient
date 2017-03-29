package onlyloveyd.com.gankioclient.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        AboutView view = AboutBuilder.with(getContext())
                .setPhoto(R.mipmap.about_avatar)
                .setCover(R.mipmap.profile_cover)
                .setName("onlyloveyd")
                .setSubTitle("Android Developer")
                .setBrief("Just keep going and work fine. Focus on Android for 100 years. ")
                .setAppIcon(R.drawable.ic_my_launcher)
                .setAppName(R.string.app_name)
                .addGitHubLink(R.string.about_github)
                .addEmailLink(R.string.about_email)
                .addWebsiteLink(R.string.about_website)
                .addAndroidLink(R.string.about_android_csdn)
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();
        return view;
    }
}
