package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shwootide.metabolictreat.DiagnosisActivity;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.event.MessageEvent;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 现病史->血糖监测
 * Created by GMY on 2015/9/9 13:18.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordJiaGongFragment extends BaseFragment {

    public static RecordJiaGongFragment newInstance(String illnessid) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        RecordJiaGongFragment fragment = new RecordJiaGongFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_recordjiagong;
    }

    @Override
    public void certainSubmit() {
        Intent intent = new Intent(getActivity(), DiagnosisActivity.class);
        intent.putExtra("IllnessID", illnessid);
        startActivity(intent);
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
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.what.equals("GetAllIllnessStandardInfo") && isVisible) {
            if (event.getCode().equals("200")) {

            }
        }
    }

    @Override
    public void lazyLoad() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("IllnessID", illnessid);
        params.put("type", "4"); // 1 体格检查  2实验室检查  4 血糖监测
//        new MutiFetcher(MHistory_sugar[].class).fetch(getActivity(), "GetAllIllnessStandardInfo", "正在加载...", params);
    }

}
