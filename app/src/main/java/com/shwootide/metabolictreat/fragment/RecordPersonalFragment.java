package com.shwootide.metabolictreat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.widget.switchbutton.SwitchButton;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 个人史
 * Created by GMY on 2015/9/8 11:09.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordPersonalFragment extends Fragment {

    @Bind(R.id.ll_personal_xy)
    LinearLayout llPersonalXy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_recordpersonal, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
