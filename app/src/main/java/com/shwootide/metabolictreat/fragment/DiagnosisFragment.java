package com.shwootide.metabolictreat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.DiagnosisAdapter;
import com.shwootide.metabolictreat.adapter.StickyListAdapter;
import com.shwootide.metabolictreat.adapter.TestAdapter;
import com.shwootide.metabolictreat.entity.MHistory_Past;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.utils.GLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * 诊断
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class DiagnosisFragment extends BaseFragment {
    @Bind(R.id.lv_diagnosis)
    ListView lvDiagnosis;

    private String illnessid = "";
    //用于存放所有选中的item对应的数据
    private List<MHistory_Past> item_selected = new ArrayList<>();
    private DiagnosisAdapter adapter;

    public static DiagnosisFragment newInstance(String illnessid) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        DiagnosisFragment fragment = new DiagnosisFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void lazyLoad() {
        Map<String, String> params = new WeakHashMap<>();
        new MutiFetcher(MHistory_Past[].class).fetch(getActivity(), "GetAllMHistoryTypeInfo", "正在加载...", params);
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        if (event.what.equals("GetAllMHistoryTypeInfo") && isVisible) {
            if (event.getCode().equals("200")) {
                showDialog(event);
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illnessid = getArguments().getString("IllnessID");
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_diagnosis;
    }

    @Override
    public void certainSubmit() {

    }

    private void showDialog(final MessageEvent event) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_past, null);
        item_selected = event.getObjects();
        final ExpandableListView stickyListHeadersListView = (ExpandableListView) v.findViewById(R.id.slhlv_dialog_past);
        final TestAdapter adapter = new TestAdapter(getActivity(), event.getObjects());
        stickyListHeadersListView.setAdapter(adapter);
//        stickyListHeadersListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        stickyListHeadersListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //关闭其他
        stickyListHeadersListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0, count = stickyListHeadersListView.getExpandableListAdapter().getGroupCount(); i < count; i++) {
                    if (groupPosition != i) {// 关闭其他分组
                        stickyListHeadersListView.collapseGroup(i);
                    }
                }
            }
        });
        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .customView(v, false)
                .positiveText(R.string.dialog_positive)
//                .neutralText(R.string.dialog_neutral)
                .autoDismiss(false)
                .negativeText(android.R.string.cancel)
                .callback(new MaterialDialog.ButtonCallback() {
//                    @Override
//                    public void onNeutral(MaterialDialog dialog) {
//                        super.onNeutral(dialog);
//                        if (stickyListHeadersListView.getCheckedItemCount() == 0) {
//                            for (int i = 0; i < adapter.getCount(); i++)
//                                stickyListHeadersListView.setItemChecked(i, true);
//                        } else {
//                            for (int i = 0; i < adapter.getCount(); i++)
//                                stickyListHeadersListView.setItemChecked(i, false);
//                        }
//                    }

                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        dialog.dismiss();
                        long[] checkedItemIds = stickyListHeadersListView.getCheckedItemIds();
                        GLog.e(checkedItemIds.length + "");
//                        for (int i = 0; i < checkedItemIds.length; i++) {
//                            item_selected.add(adapter.getItem((int) checkedItemIds[i]));
//                        }
                        initAdapter();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                        dialog.dismiss();
                    }
                }).build();
        dialog.show();
        //价款dialog的宽度
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        dialog.getWindow().setAttributes(lp);
    }

    /**
     * 设置主界面的数据
     */
    private void initAdapter() {
        if (item_selected.size() == 0) {
            adapter = new DiagnosisAdapter(getActivity());
        } else {
            adapter = new DiagnosisAdapter(getActivity(), item_selected);
            lvDiagnosis.setAdapter(adapter);
            mHasLoadedOnce = true;
        }
    }
}
