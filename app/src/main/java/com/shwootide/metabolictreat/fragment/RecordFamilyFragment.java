package com.shwootide.metabolictreat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shwootide.metabolictreat.R;

/**
 * 家族史
 * Created by GMY on 2015/9/8 11:09.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordFamilyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_recordfamily, null);
        return view;
    }
}
