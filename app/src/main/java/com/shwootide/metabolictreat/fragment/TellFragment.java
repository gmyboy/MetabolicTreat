package com.shwootide.metabolictreat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 体格检查
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class TellFragment extends Fragment {

    @Bind(R.id.ll_tell1)
    LinearLayout llTell1;
    @Bind(R.id.ll_tell2)
    LinearLayout llTell2;
    @Bind(R.id.spinner_tell1)
    NiceSpinner spinnerTell1;
    @Bind(R.id.spinner_tell2)
    NiceSpinner spinnerTell2;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_tell, null);
        ButterKnife.bind(this, view);
        List<String> dataset1 = new LinkedList<>(Arrays.asList("正常体型者参考食谱一", "正常体型者参考食谱二"));
        spinnerTell1.attachDataSource(dataset1);
        List<String> dataset2 = new LinkedList<>(Arrays.asList("I级", "II级", "III级", "IV级"));
        spinnerTell2.attachDataSource(dataset2);
        return view;
    }

    public void onEventMainThread(MessageEvent event) {
        if (event.what.equals("TellFragment")) {
            switch (event.getMeta()) {
                case 0:
                    llTell1.setVisibility(View.VISIBLE);
                    llTell2.setVisibility(View.GONE);
                    break;
                case 1:
                    llTell2.setVisibility(View.VISIBLE);
                    llTell1.setVisibility(View.GONE);
                    break;
                case 3:
                    new MaterialDialog.Builder(getActivity())
                            .customView(R.layout.dialog_calendar, true)
                            .show();
                    break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
