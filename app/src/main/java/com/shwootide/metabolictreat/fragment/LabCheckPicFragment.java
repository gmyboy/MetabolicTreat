package com.shwootide.metabolictreat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.LabCheckAdapter;
import com.shwootide.metabolictreat.entity.LabCheck;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * 实验室检查
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class LabCheckPicFragment extends BaseFragment {
    @Bind(R.id.slhlv_labcheck)
    StickyListHeadersListView slhlvLabcheck;

    private String illnessid = "";
    private LabCheckAdapter adapter;

    public static LabCheckPicFragment newInstance(String illnessid) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        LabCheckPicFragment fragment = new LabCheckPicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illnessid = getArguments().getString("IllnessID");
    }

    @Override
    public void lazyLoad() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("IllnessID", illnessid);
        params.put("type", "2"); // 1 体格检查  2实验室检查  4 血糖监测
        new MutiFetcher(LabCheck[].class).fetch(getActivity(), "GetAllIllnessStandardInfo", "正在加载...", params);
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        if (event.what.equals("GetAllIllnessStandardInfo") && isVisible) {
            if (event.getCode().equals("200")) {
                adapter = new LabCheckAdapter(getActivity(), event.getObjects());
                slhlvLabcheck.setAdapter(adapter);
                mHasLoadedOnce=true;
            }
        }
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_labcheck_pic;
    }

    @Override
    public void certainSubmit() {

    }

}
