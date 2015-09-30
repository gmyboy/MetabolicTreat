package com.shwootide.metabolictreat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.shwootide.metabolictreat.DiagnosisActivity;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.RecordnowAdapter;
import com.shwootide.metabolictreat.adapter.RecordsugarAdapter;
import com.shwootide.metabolictreat.entity.MHistory_Now;
import com.shwootide.metabolictreat.entity.MHistory_sugar;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.PreferenceUtil;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 现病史->血糖监测
 * Created by GMY on 2015/9/9 13:18.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordSugarFragment extends BaseFragment {
    @Bind(R.id.lv_recordnow2)
    ListView lvRecordnow2;

    private String illnessid = "";
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
    public void onEventMainThread(MessageEvent event) {
        if (event.what.equals("GetAllIllnessStandardInfo") && isVisible) {
            if (event.getCode().equals("200")) {
                adapter = new RecordsugarAdapter(getActivity(), event.getObjects());
                lvRecordnow2.setAdapter(adapter);
            }
        }
    }

    @Override
    public void lazyLoad() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("IllnessID", illnessid);
        params.put("type", "4"); // 1 体格检查  2实验室检查  4 血糖监测
        new MutiFetcher(MHistory_sugar[].class).fetch(getActivity(), "GetAllIllnessStandardInfo", "正在加载...", params);
    }

}
