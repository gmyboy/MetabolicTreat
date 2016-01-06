package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.shwootide.metabolictreat.DiagnosisActivity;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.RecordsugarAdapter;
import com.shwootide.metabolictreat.event.MessageEvent;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;

/**
 * 弃
 * 现病史->血糖监测
 * Created by GMY on 2015/9/9 13:18.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordSugarFragment extends BaseFragment {
    @Bind(R.id.ll_now1)
    LinearLayout llNow1;
//    @Bind(R.id.lv_recordnow2)
//    ListView lvRecordnow2;


    private RecordsugarAdapter adapter;

    public static RecordSugarFragment newInstance(String illnessid) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        RecordSugarFragment fragment = new RecordSugarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_recordsugar;
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
        if (illnessid.equals("1"))llNow1.setVisibility(View.VISIBLE);
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.what.equals("GetAllIllnessStandardInfo") && isVisible) {
            if (event.getCode().equals("200")) {
                adapter = new RecordsugarAdapter(getActivity(), event.getObjects());
//                lvRecordnow2.setAdapter(adapter);
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
