package com.shwootide.metabolictreat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.event.MessageEvent;

/**
 * 辅助检查
 * Created by GMY on 2015/9/8 12:29.
 * Contact me via email gmyboy@qq.com.
 */
public class AuxiliaryCheckPrintFragment extends BaseFragment {

    private String illnessid = "";

    public static AuxiliaryCheckPrintFragment newInstance(String illnessid) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        AuxiliaryCheckPrintFragment fragment = new AuxiliaryCheckPrintFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illnessid = getArguments().getString("IllnessID");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void lazyLoad() {

    }

    @Override
    public void onEventMainThread(MessageEvent event) {

    }

    @Override
    public int bindViewById() {
        switch (illnessid) {
            case "1"://糖尿病
                return R.layout.frag_auxiliarycheck_print1;
            case "2"://dm
                return R.layout.frag_auxiliarycheck_print2;
            case "3"://pcos
                return R.layout.frag_auxiliarycheck_print3;
            case "9000"://other
                return R.layout.frag_auxiliarycheck_print1;
            default:
                return R.layout.frag_auxiliarycheck_print1;
        }
    }

    @Override
    public void certainSubmit() {

    }
}
