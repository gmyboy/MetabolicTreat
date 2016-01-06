package com.shwootide.metabolictreat.fragment.Common;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.event.MessageEvent;

import butterknife.Bind;

/**
 * 设置
 * Created by GMY on 2015/12/17 16:07.
 * Contact me via email igmyboy@gmail.com.
 */
public class SettingsFragment extends MenuFragment {
    @Bind(R.id.btn_setting_update)
    Button btnSettingUpdate;
    @Bind(R.id.btn_setting_about)
    Button btnSettingAbout;
    @Bind(R.id.btn_setting_exit)
    Button btnSettingExit;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            btnSettingUpdate.setText("软件更新(当前版本:V" + getActivity().getPackageManager().getPackageInfo("com.shwootide.metabolictreat", 0).versionName + ")");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_setting;
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
    }

}
