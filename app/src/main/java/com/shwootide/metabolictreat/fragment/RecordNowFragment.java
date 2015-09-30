package com.shwootide.metabolictreat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shwootide.metabolictreat.DiagnosisActivity;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.RecordnowAdapter;
import com.shwootide.metabolictreat.entity.MHistory_Now;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.PreferenceUtil;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 现病史
 * Created by GMY on 2015/9/9 13:18.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordNowFragment extends BaseFragment {

    private RecordnowAdapter adapter;
    private String illnessid = "";

    public static RecordNowFragment newInstance(String illnessid) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        RecordNowFragment fragment = new RecordNowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        switch (illnessid) {
            case "1"://糖尿病
                return R.layout.frag_recordnow1;
            case "2"://dm
                return R.layout.frag_recordnow2;
            case "3"://pcos
                return R.layout.frag_recordnow3;
            case "9000"://other
                return R.layout.frag_recordnow4;
            default:
                return R.layout.frag_recordnow1;
        }
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
        if (event.what.equals("GetAllIllnessMHistoryType01")) {
            adapter = new RecordnowAdapter(getActivity(), event.getObjects(), PreferenceUtil.getUser(getActivity(), "UserInfo"));
//            lvRecordnow1.setAdapter(adapter);
            mHasLoadedOnce = true;
        } else if (event.what.equals("AddMedicalRecordHistoryAll")) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
            } else {
                CommonUtil.showToast(getActivity(), "提交失败");
            }
        }
    }

    @Override
    public void lazyLoad() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("IllnessID", illnessid);
//        new MutiFetcher(MHistory_Now[].class).fetch(getActivity(), "GetAllIllnessMHistoryType01", "正在加载...", params);
    }
}
