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
import com.shwootide.metabolictreat.entity.MHistory_Now;
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
 * 现病史->现治疗用药
 * Created by GMY on 2015/9/9 13:18.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordMedicineFragment extends BaseFragment {
    @Bind(R.id.ll_now3)
    LinearLayout llNow3;

    private String illnessid = "";

    public static RecordMedicineFragment newInstance(String illnessid) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        RecordMedicineFragment fragment = new RecordMedicineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_recordmedicine;
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

    }

    @Override
    public void lazyLoad() {

    }

}
