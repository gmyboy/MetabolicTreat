package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.GridView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.DiagnosisAdapter;
import com.shwootide.metabolictreat.adapter.ExpandDiagnosisAdapter;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.Diagnosis;
import com.shwootide.metabolictreat.entity.Diagnosis_Submit;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;

/**
 * 诊断
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class DiagnosisFragment extends BaseFragment {
    @Bind(R.id.gv_diagnosis)
    GridView lvDiagnosis;
    @Bind(R.id.slhlv_dialog_past)
    ExpandableListView slhlvDialogPast;
    @Bind(R.id.ns_diagnosis)
    NiceSpinner nsDiagnosis;

    //用于存放所有选中的item对应的数据
    private DiagnosisAdapter adapter_r;
    private ExpandDiagnosisAdapter adapter_l;

    public static DiagnosisFragment newInstance(String illnessid, int mPosition, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", mPosition);
        args.putInt("SequenceNumber", mSequenceNumber);
        DiagnosisFragment fragment = new DiagnosisFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illnessid = getArguments().getString("IllnessID");
        position = getArguments().getInt("Position");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int index = 0;
        switch (illnessid) {
            case "1"://糖尿病
                index = 0;
                break;
            case "2"://dm
                index = 1;
                break;
            case "3"://pcos
                index = 2;
                break;
            case "9000"://other
                index = 3;
                break;
        }
        nsDiagnosis.setSelectedIndex(index);
        nsDiagnosis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String illId = "1";//默认为糖尿病
                switch (position) {
                    case 0:
                        illId = "1";
                        break;
                    case 1:
                        illId = "2";
                        break;
                    case 2:
                        illId = "3";
                        break;
                    case 3:
                        illId = "9000";
                        break;
                }
                //刷新数据
                Map<String, String> params = new WeakHashMap<>();
                params.put("IllnessID", illId);
                new MutiFetcher(Diagnosis[].class).fetch(getActivity(), "GetMHistoryTypeByIllnessID2", "正在加载...", params);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter_r = new DiagnosisAdapter(getActivity());
        lvDiagnosis.setAdapter(adapter_r);
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_diagnosis;
    }

    @Override
    public void lazyLoad() {
        //获得左边初始化
        Map<String, String> params = new WeakHashMap<>();
        params.put("IllnessID", illnessid);
        new MutiFetcher(Diagnosis[].class).fetch(getActivity(), "GetMHistoryTypeByIllnessID2", null, params);

        if (position == 2) {//编辑
            //获取已有诊断数据
            params = new WeakHashMap<>();
            params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
            params.put("flg", "02");
            new MutiFetcher(Diagnosis_Submit[].class).fetch(getActivity(), "GetMedicalRecordHistory02", "正在加载...", params);
        }
    }

    @Override
    public void onEventMainThread(final MessageEvent event) {
        super.onEventMainThread(event);
        if (event.getWhat().equals("GetMHistoryTypeByIllnessID2") && isVisible) {
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {

                adapter_l = new ExpandDiagnosisAdapter(getActivity(), event.getObjects());
                adapter_l.setChoiceMode(ExpandDiagnosisAdapter.CHOICE_MODE_MULTIPLE);
                slhlvDialogPast.setAdapter(adapter_l);

                mHasLoadedOnce = true;
                slhlvDialogPast.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        adapter_l.setClicked(groupPosition, childPosition);
                        adapter_r.upate(CommonUtil.fromDiagnosis(adapter_l.getChild(groupPosition, childPosition)));
                        return false;
                    }
                });
            }
        } else if (event.getWhat().equals("AddMedicalRecordHistoryAll") && isVisible) {//添加
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
                //refresh
            } else {
                CommonUtil.showToast(getActivity(), "提交失败");
            }
        } else if (event.getWhat().equals("GetMedicalRecordHistory02") && isVisible) {//获取
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                initDate(event);//应该包含其他信息
            }
        }
    }

    /**
     * 现实已有数据
     *
     * @param event
     */
    private void initDate(MessageEvent event) {
        mEtOther.setText(event.getOtherinfo());
        adapter_r = new DiagnosisAdapter(getActivity(), event.getObjects());
        lvDiagnosis.setAdapter(adapter_r);

//        adapter_l.setClicked(groupPosition, childPosition);
    }

    @Override
    public void certainSubmit() {
        if (null != adapter_r && adapter_r.getCount() != 0) {
            //取得所有数据
            List<Diagnosis_Submit> submits = new ArrayList<>();
            Diagnosis_Submit submit;
            for (int i = 0; i < adapter_r.getCount(); i++) {
                submit = adapter_r.getItem(i);
                submit.setId(CommonUtil.generateGUID());
                submit.setInputUser(String.valueOf(getmUserInfo().getUserID()));
                submit.setInputDate(CommonUtil.getSysDate());
                submit.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
                submits.add(submit);
            }
            Map<String, String> params = new WeakHashMap<>();
            params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
            params.put("OhterInfo", mEtOther.getText().toString().equals("") ? "" : mEtOther.getText().toString());
            params.put("flg", "02");
            new SingleFetcher(Diagnosis[].class).addMedicalHistory(getActivity(), "正在提交...", submits, params);
        }
    }

  /*  private void showDialog(final MessageEvent event) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_past, null);
        item_selected = event.getObjects();

//        stickyListHeadersListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


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
    }*/
}
