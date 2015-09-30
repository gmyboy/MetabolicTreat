package com.shwootide.metabolictreat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.shwootide.metabolictreat.DiagnosisActivity;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.RecordPastAdapter;
import com.shwootide.metabolictreat.adapter.StickyListAdapter;
import com.shwootide.metabolictreat.entity.MHistory_Past;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.utils.GLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * 既往史
 * Created by GMY on 2015/9/8 11:09.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordPastFragment extends BaseFragment {


    //用于存放所有选中的item对应的数据b
    private List<MHistory_Past> item_selected = new ArrayList<>();
    private RecordPastAdapter adapter;
    private String illnessid = "";

    public static RecordPastFragment newInstance(String illnessid) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        RecordPastFragment fragment = new RecordPastFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        switch (illnessid) {
            case "1"://糖尿病
                return R.layout.frag_recordpast1;
            case "2"://dm
                return R.layout.frag_recordpast2;
            case "3"://pcos
                return R.layout.frag_recordpast3;
            case "9000"://other
                return R.layout.frag_recordpast4;
            default:
                return R.layout.frag_recordpast1;
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
    public void lazyLoad() {
        Map<String, String> params = new WeakHashMap<>();
//        new MutiFetcher(MHistory_Past[].class).fetch(getActivity(), "GetAllMHistoryTypeInfo", "正在加载...", params);
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        if (event.what.equals("GetAllMHistoryTypeInfo") && isVisible) {
//            showDialog(event);
        }
    }

    private void showDialog(final MessageEvent event) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_past, null);
//        final  ExpandableListView stickyListHeadersListView= (ExpandableListView) v.findViewById(R.id.elv_dialog_past);
//        final  TestAdapter adapter=new TestAdapter(getActivity(),event.getObjects());
//        stickyListHeadersListView.setAdapter(adapter);
        final StickyListHeadersListView stickyListHeadersListView = (StickyListHeadersListView) v.findViewById(R.id.slhlv_dialog_past);
        final StickyListAdapter adapter = new StickyListAdapter(getActivity(), event.getObjects());
        stickyListHeadersListView.setAdapter(adapter);
        stickyListHeadersListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        new MaterialDialog.Builder(getActivity()).customView(v, false)
                .positiveText(R.string.dialog_positive)
//                .neutralText(R.string.dialog_neutral)
                .autoDismiss(false)
                .negativeText(android.R.string.cancel)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                        if (stickyListHeadersListView.getCheckedItemCount() == 0) {
                            for (int i = 0; i < adapter.getCount(); i++)
                                stickyListHeadersListView.setItemChecked(i, true);
                        } else {
                            for (int i = 0; i < adapter.getCount(); i++)
                                stickyListHeadersListView.setItemChecked(i, false);
                        }
                    }

                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        dialog.dismiss();
                        long[] checkedItemIds = stickyListHeadersListView.getCheckedItemIds();
                        GLog.e(checkedItemIds.length + "");
                        for (int i = 0; i < checkedItemIds.length; i++) {
                            item_selected.add(adapter.getItem((int) checkedItemIds[i]));
                        }
                        initAdapter();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                        dialog.dismiss();
                    }
                }).show();
    }

    /**
     * 设置主界面的数据
     */
    private void initAdapter() {
        if (item_selected.size() == 0) {
            adapter = new RecordPastAdapter(getActivity());
        } else {
            adapter = new RecordPastAdapter(getActivity(), item_selected);
//            lvRecordpast.setAdapter(adapter);
            mHasLoadedOnce = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
