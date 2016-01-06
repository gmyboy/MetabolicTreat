package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.ExpandSheetAdapter;
import com.shwootide.metabolictreat.adapter.SheetAdapter;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.Sheet;
import com.shwootide.metabolictreat.entity.Sheet_Submit;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;

/**
 * 开化验单-实验室检查
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class SheetFragment extends BaseFragment {
    @Bind(R.id.lv_diagnosis1)
    GridView gvDiagnosis1;
    @Bind(R.id.slhlv_dialog_past1)
    ExpandableListView slhlvDialogPast1;
    @Bind(R.id.tv_title_sheet)
    TextView tvTitleSheet;

    private SheetAdapter adapter_r1;
    private ExpandSheetAdapter adapter_l1;

    public static SheetFragment newInstance(String illnessid, int mPosition, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", mPosition);
        args.putInt("SequenceNumber", mSequenceNumber);
        SheetFragment fragment = new SheetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitleSheet.setText("实验室检查");
    }

    @Override
    public void lazyLoad() {
        //获得左边初始化
        Map<String, String> params = new WeakHashMap<>();
        new MutiFetcher(Sheet[].class).fetch(getActivity(), "GetAllStandardInfo56", null, params);

        if (position == 0) {//初诊
        } else if (position == 1) {//复诊
        } else {//编辑
            //获取已有化验单数据
            params = new WeakHashMap<>();
            params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
            params.put("flg", "12");
            new MutiFetcher(Sheet_Submit[].class).fetch(getActivity(), "GetAllMedicalRecordInfo", "正在加载...", params);
        }
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.what.equals("GetAllStandardInfo56") && isVisible) {
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                adapter_r1 = new SheetAdapter(getActivity());
                adapter_l1 = new ExpandSheetAdapter(getActivity(), 0, event.getObjects());
                slhlvDialogPast1.setAdapter(adapter_l1);
                gvDiagnosis1.setAdapter(adapter_r1);
                mHasLoadedOnce = true;
                slhlvDialogPast1.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        adapter_l1.setClicked(groupPosition, childPosition);
                        adapter_r1.upate(CommonUtil.fromSheet(adapter_l1.getChild(groupPosition, childPosition)));
                        return false;
                    }
                });
            }
        } else if (event.getWhat().equals("AddAllMedicalRecordInfo") && isVisible) {//添加
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
                //refresh
            } else {
                CommonUtil.showToast(getActivity(), "提交失败");
            }
        } else if (event.getWhat().equals("GetAllMedicalRecordInfo") && isVisible) {//获取
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                initDate(event);//应该包含其他信息
            }
        }
    }

    /**
     * 显示已有数据
     *
     * @param event
     */
    private void initDate(MessageEvent event) {
        mEtOther.setText(event.getOtherinfo());
        adapter_r1 = new SheetAdapter(getActivity(), getDatas(event.getObjects()));
        gvDiagnosis1.setAdapter(adapter_r1);
    }

    /**
     * 拆分获取到的数据
     *
     * @param mDates
     * @return
     */
    public List<Sheet_Submit> getDatas(List<Sheet_Submit> mDates) {
        List<Sheet_Submit> temp = new ArrayList<>();
        for (Sheet_Submit s : mDates) {
            if (s.getNametype1().equals("5")) {
                temp.add(s);
            }
        }

        return temp;
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_sheet1;
    }

    @Override
    public void certainSubmit() {
        //取得所有数据
        List<Sheet_Submit> submits = new ArrayList<>();
        if (adapter_r1 != null && adapter_r1.getCount() != 0) {
            Sheet_Submit submit;
            for (int i = 0; i < adapter_r1.getCount(); i++) {
                submit = adapter_r1.getItem(i);
                submit.setId(CommonUtil.generateGUID());
                submit.setInputUser(String.valueOf(getmUserInfo().getUserID()));
                submit.setInputDate(CommonUtil.getSysDate());
                submit.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
                submits.add(submit);
            }
        }
        Map<String, String> params = new WeakHashMap<>();
        params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
        params.put("OhterInfo", mEtOther.getText().toString().equals("") ? "" : mEtOther.getText().toString());
        params.put("flg", "12");
        new SingleFetcher(Sheet_Submit[].class).addMedicalRecordInfo(getActivity(), "正在提交...", submits, params);
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
