package com.shwootide.metabolictreat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shwootide.metabolictreat.DiagnosisActivity;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.RecordPersonalAdapter;
import com.shwootide.metabolictreat.entity.MHistory_Personal;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 个人史
 * Created by GMY on 2015/9/8 11:09.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordPersonalFragment extends BaseFragment {
    //    @Bind(R.id.ns_personal1)
    NiceSpinner nsPersonal1;
    //    @Bind(R.id.ns_personal2)
    NiceSpinner nsPersonal2;
    //    @Bind(R.id.ns_personal3)
    NiceSpinner nsPersonal3, nsPersonal4, nsPersonal5, nsPersonal6;

    @Bind(R.id.tv_personal_yinjiu_time)
    TextView tvPersonalYinjiuTime;
    @Bind(R.id.ll_personal_yinjiu_time)
    LinearLayout llPersonalYinjiuTime;
    @Bind(R.id.tv_personal_yinjiu_count)
    TextView tvPersonalYinjiuCount;
    @Bind(R.id.ll_personal_yinjiu_count)
    LinearLayout llPersonalYinjiuCount;

    @Bind(R.id.tv_personal_smoke_time)
    TextView tvPersonalSmokeTime;
    @Bind(R.id.ll_personal_smoke_time)
    LinearLayout llPersonalSmokeTime;
    @Bind(R.id.tv_personal_smoke_count)
    TextView tvPersonalSmokeCount;
    @Bind(R.id.ll_personal_smoke_count)
    LinearLayout llPersonalSmokeCount;

    TextView tvPersonalSportTime;
    LinearLayout llPersonalSportTime;
    TextView tvPersonalSportCount;
    LinearLayout llPersonalSportCount;

//    @Bind(R.id.lv_recordpersonal)
//    ListView lvRecordpersonal;

    private RecordPersonalAdapter adapter;
    private String illnessid = "";

    public static RecordPersonalFragment newInstance(String illnessid) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        RecordPersonalFragment fragment = new RecordPersonalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        switch (illnessid) {
            case "2"://dm
                return R.layout.frag_recordpersonal2;
            case "1"://糖尿病
            case "3"://pcos
            case "9000"://other
                return R.layout.frag_recordpersonal1;
            default:
                return R.layout.frag_recordpersonal2;
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
        if (event.what.equals("GetMHistoryType06") && isVisible) {
            if (event.getCode().equals("200")) {
                adapter = new RecordPersonalAdapter(getActivity(), event.getObjects());
//                lvRecordpersonal.setAdapter(adapter);
                mHasLoadedOnce = true;
            }
        }
    }

    @Override
    public void lazyLoad() {
        Map<String, String> params = new WeakHashMap<>();
//        new MutiFetcher(MHistory_Personal[].class).fetch(getActivity(), "GetMHistoryType06", "正在加载...", params);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        switch (illnessid) {
            case "1"://糖尿病
            case "3"://pcos
            case "9000"://other
                nsPersonal1 = (NiceSpinner) rootView.findViewById(R.id.ns_personal1);
                nsPersonal2 = (NiceSpinner) rootView.findViewById(R.id.ns_personal2);
                nsPersonal3 = (NiceSpinner) rootView.findViewById(R.id.ns_personal3);
                nsPersonal4 = (NiceSpinner) rootView.findViewById(R.id.ns_personal4);
                nsPersonal5 = (NiceSpinner) rootView.findViewById(R.id.ns_personal5);
                nsPersonal6 = (NiceSpinner) rootView.findViewById(R.id.ns_personal6);
                llPersonalSportCount = (LinearLayout) rootView.findViewById(R.id.ll_personal_sport_count);
                llPersonalSportTime = (LinearLayout) rootView.findViewById(R.id.ll_personal_sport_time);
                tvPersonalSportCount = (TextView) rootView.findViewById(R.id.tv_personal_sport_count);
                tvPersonalSportTime = (TextView) rootView.findViewById(R.id.tv_personal_sport_time);
                init1();
                break;
            case "2"://dm
                nsPersonal1 = (NiceSpinner) rootView.findViewById(R.id.ns_personal1);
                nsPersonal2 = (NiceSpinner) rootView.findViewById(R.id.ns_personal2);
                nsPersonal3 = (NiceSpinner) rootView.findViewById(R.id.ns_personal3);
                init2();
                break;
        }

        return rootView;
    }

    /**
     * 初始化dm
     */
    private void init2() {
        nsPersonal1.attachDataSource(Arrays.asList("无", "偶有", "有"));
        nsPersonal2.attachDataSource(Arrays.asList("无", "偶有", "有"));
        nsPersonal3.attachDataSource(Arrays.asList("好", "一般", "不好"));
        nsPersonal1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    tvPersonalYinjiuCount.setVisibility(View.INVISIBLE);
                    tvPersonalYinjiuTime.setVisibility(View.INVISIBLE);
                    llPersonalYinjiuCount.setVisibility(View.INVISIBLE);
                    llPersonalYinjiuTime.setVisibility(View.INVISIBLE);
                } else {
                    tvPersonalYinjiuCount.setVisibility(View.VISIBLE);
                    tvPersonalYinjiuTime.setVisibility(View.VISIBLE);
                    llPersonalYinjiuCount.setVisibility(View.VISIBLE);
                    llPersonalYinjiuTime.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        nsPersonal2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    tvPersonalSmokeCount.setVisibility(View.INVISIBLE);
                    tvPersonalSmokeTime.setVisibility(View.INVISIBLE);
                    llPersonalSmokeCount.setVisibility(View.INVISIBLE);
                    llPersonalSmokeTime.setVisibility(View.INVISIBLE);
                } else {
                    tvPersonalSmokeCount.setVisibility(View.VISIBLE);
                    tvPersonalSmokeTime.setVisibility(View.VISIBLE);
                    llPersonalSmokeCount.setVisibility(View.VISIBLE);
                    llPersonalSmokeTime.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 初始化 糖尿病 pcos other
     */
    private void init1() {
        nsPersonal1.attachDataSource(Arrays.asList("普通", "嗜荤", "嗜素"));
        nsPersonal2.attachDataSource(Arrays.asList("无", "偶有", "有"));
        nsPersonal3.attachDataSource(Arrays.asList("无", "偶有", "有"));
        nsPersonal4.attachDataSource(Arrays.asList("无", "偶有", "有"));
        nsPersonal5.attachDataSource(Arrays.asList("散步", "快走", "游泳", "羽毛球", "乒乓球", "自行车", "其他"));
        nsPersonal6.attachDataSource(Arrays.asList("好", "一般", "不好"));
        nsPersonal2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    tvPersonalYinjiuCount.setVisibility(View.INVISIBLE);
                    tvPersonalYinjiuTime.setVisibility(View.INVISIBLE);
                    llPersonalYinjiuCount.setVisibility(View.INVISIBLE);
                    llPersonalYinjiuTime.setVisibility(View.INVISIBLE);
                } else {
                    tvPersonalYinjiuCount.setVisibility(View.VISIBLE);
                    tvPersonalYinjiuTime.setVisibility(View.VISIBLE);
                    llPersonalYinjiuCount.setVisibility(View.VISIBLE);
                    llPersonalYinjiuTime.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        nsPersonal3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    tvPersonalSmokeCount.setVisibility(View.INVISIBLE);
                    tvPersonalSmokeTime.setVisibility(View.INVISIBLE);
                    llPersonalSmokeCount.setVisibility(View.INVISIBLE);
                    llPersonalSmokeTime.setVisibility(View.INVISIBLE);
                } else {
                    tvPersonalSmokeCount.setVisibility(View.VISIBLE);
                    tvPersonalSmokeTime.setVisibility(View.VISIBLE);
                    llPersonalSmokeCount.setVisibility(View.VISIBLE);
                    llPersonalSmokeTime.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        nsPersonal4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    tvPersonalSportCount.setVisibility(View.INVISIBLE);
                    tvPersonalSportTime.setVisibility(View.INVISIBLE);
                    llPersonalSportCount.setVisibility(View.INVISIBLE);
                    llPersonalSportTime.setVisibility(View.INVISIBLE);
                } else {
                    tvPersonalSportCount.setVisibility(View.VISIBLE);
                    tvPersonalSportTime.setVisibility(View.VISIBLE);
                    llPersonalSportCount.setVisibility(View.VISIBLE);
                    llPersonalSportTime.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
