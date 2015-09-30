package com.shwootide.metabolictreat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.PhysicalAdapter;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.utils.GLog;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 体格检查
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class PhysicalFragment extends BaseFragment {
    //    @Bind(R.id.ns_physical1)
    NiceSpinner nsPhysical1;
    //    @Bind(R.id.ns_physical2)
    NiceSpinner nsPhysical2;
    //    @Bind(R.id.ns_physical3)
    NiceSpinner nsPhysical3;
    //    @Bind(R.id.ns_physical4)
    NiceSpinner nsPhysical4;

//    @Bind(R.id.lv_physical)
//    ListView lvPhysical;

    private String illnessid = "";
    private PhysicalAdapter adapter;

    public static PhysicalFragment newInstance(String illnessid) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        PhysicalFragment fragment = new PhysicalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        GLog.e("123" + illnessid);
        switch (illnessid) {
            case "1"://糖尿病
                return R.layout.frag_physical1;
            case "2"://dm
                return R.layout.frag_physical2;
            case "3"://pcos
                return R.layout.frag_physical3;
            case "9000"://other
                return R.layout.frag_physical4;
            default:
                return R.layout.frag_physical1;
        }
    }

    @Override
    public void certainSubmit() {

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
        params.put("type", "1"); // 1 体格检查  2实验室检查  4 血糖监测
//        new MutiFetcher(Physical[].class).fetch(getActivity(), "GetAllIllnessStandardInfo", "正在加载...", params);
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        if (event.what.equals("GetAllIllnessStandardInfo") && isVisible) {
            if (event.getCode().equals("200")) {
                adapter = new PhysicalAdapter(getActivity(), event.getObjects());
//                lvPhysical.setAdapter(adapter);
                mHasLoadedOnce = true;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        switch (illnessid) {
            case "2"://dm
                nsPhysical1 = (NiceSpinner) rootView.findViewById(R.id.ns_physical1);
                nsPhysical2 = (NiceSpinner) rootView.findViewById(R.id.ns_physical2);
                nsPhysical3 = (NiceSpinner) rootView.findViewById(R.id.ns_physical3);
                nsPhysical4 = (NiceSpinner) rootView.findViewById(R.id.ns_physical4);
                nsPhysical1.attachDataSource(Arrays.asList("齐", "不齐", "早搏"));
                nsPhysical2.attachDataSource(Arrays.asList("未见肿大", "1度肿大", "2度肿大", "3度肿大"));
                nsPhysical3.attachDataSource(Arrays.asList("无", "左", "右", "双侧"));
                nsPhysical4.attachDataSource(Arrays.asList("无", "左", "右", "双侧"));
                break;
            case "1"://糖尿病
                nsPhysical1 = (NiceSpinner) rootView.findViewById(R.id.ns_physical1);
                nsPhysical2 = (NiceSpinner) rootView.findViewById(R.id.ns_physical2);
                nsPhysical3 = (NiceSpinner) rootView.findViewById(R.id.ns_physical3);
                nsPhysical1.attachDataSource(Arrays.asList("齐", "不齐", "早搏"));
                nsPhysical2.attachDataSource(Arrays.asList("正常", "色斑", "破溃"));
                nsPhysical3.attachDataSource(Arrays.asList("未见异常", "左侧减弱", "右侧减弱", "双侧减弱", "左侧消失", "右侧消失", "双侧消失"));
                break;
            case "3"://pcos
            case "9000":
                nsPhysical1 = (NiceSpinner) rootView.findViewById(R.id.ns_physical1);
                nsPhysical1.attachDataSource(Arrays.asList("齐", "不齐", "早搏"));
                break;
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }



    //    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//    }
//
//    @Override
//    public void afterTextChanged(Editable s) {
//        String str_tz = etPhysicalTz.getText().toString().trim();
//        String str_sg = etPhysicalSg.getText().toString().trim();
//        if (!TextUtils.isEmpty(str_tz) && !TextUtils.isEmpty(str_sg)) {
//            tvPhysicalBmi.setText(String.valueOf(CommonUtil.getBMI(str_tz, str_sg)));
//        }
//    }
}
