package com.shwootide.metabolictreat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shwootide.metabolictreat.R;

/**
 * 体格检查
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class DiagnosisFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_diagnosis, null);
        return view;
    }
}
