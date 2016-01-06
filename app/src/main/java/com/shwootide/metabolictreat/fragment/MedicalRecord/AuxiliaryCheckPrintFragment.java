package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.MedicalRecordCheckup;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.widget.ChangeButton;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 辅助检查
 * Created by GMY on 2015/9/8 12:29.
 * Contact me via email gmyboy@qq.com.
 */
public class AuxiliaryCheckPrintFragment extends BaseFragment implements View.OnClickListener {
    //dm
    TextView etAux2Date1;

    TextView etAux21;
    TextView etAux22;
    TextView etAux23;
    TextView etAux24;
    ChangeButton cbAux25;
    TextView etAux27;
    ChangeButton cbAux28;
    //pcos
    TextView etAux3Date1;
    TextView etAux31;
    NiceSpinner nsAux32;
    private MedicalRecordCheckup checkup;

    public static AuxiliaryCheckPrintFragment newInstance(String illnessid, int mPosition, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", mPosition);
        args.putInt("SequenceNumber", mSequenceNumber);
        AuxiliaryCheckPrintFragment fragment = new AuxiliaryCheckPrintFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch (illnessid) {
            case "1"://糖尿病
            case "9000"://other
                init1(view);
                break;
            case "2"://dm
                init2(view);
                break;
            case "3"://pcos
                init3(view);
                break;
        }
    }

    @Override
    public void certainSubmit() {
        boolean isAdd = false;//标志位，用来区分是新增还是更新
        if (checkup == null) {//添加
            isAdd = true;
            checkup = new MedicalRecordCheckup();
            checkup.setId(CommonUtil.generateGUID());
            checkup.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
            checkup.setInputUser(String.valueOf(getmUserInfo().getUserID()));
            checkup.setIllnessID(illnessid);
            checkup.setType("3");
        }
        checkup.setOther(mEtOther.getText().toString());
        checkup.setInputDate(CommonUtil.getSysDate());
        switch (illnessid) {
            case "2":
                checkup.setFieldsDate1(etAux2Date1.getText().toString());
                checkup.setFields1(etAux21.getText().toString());
                checkup.setFields2(etAux22.getText().toString());
                checkup.setFields3(etAux23.getText().toString());
                checkup.setFields4(etAux24.getText().toString());
                checkup.setFields5(cbAux25.getChecked());
                checkup.setFields6(etAux27.getText().toString());
                checkup.setFields7(cbAux28.getChecked());
                break;
            case "3":
                checkup.setFieldsDate1(etAux3Date1.getText().toString());
                checkup.setFields1(etAux31.getText().toString());
                checkup.setFields2(nsAux32.getSelectedIndex());
                break;
        }
        if (isAdd) {//添加
            new SingleFetcher(String.class).addMedicalRecord_checkup(getActivity(), "正在提交...", checkup);
        } else {//更新数据
            new SingleFetcher(String.class).updateMedicalRecord_checkup(getActivity(), "正在更新数据...", checkup);
        }
    }

    /**
     * 初始化pcos相关view
     *
     * @param view
     */
    private void init3(View view) {
        etAux3Date1 = (TextView) view.findViewById(R.id.et_aux3_date_1);
        etAux3Date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        etAux31 = (TextView) view.findViewById(R.id.et_aux3_1);
        etAux31.setOnClickListener(this);
        etAux31.addTextChangedListener(CommonUtil.getTextWatch());
        nsAux32 = (NiceSpinner) view.findViewById(R.id.ns_aux3_2);
    }

    /**
     * 初始化甲状腺相关view
     *
     * @param view
     */
    private void init2(View view) {
        etAux2Date1 = (TextView) view.findViewById(R.id.et_aux2_date_1);
        etAux2Date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        etAux21 = (TextView) view.findViewById(R.id.et_aux2_1);
        etAux21.setOnClickListener(this);
        etAux22 = (TextView) view.findViewById(R.id.et_aux2_2);
        etAux22.setOnClickListener(this);
        etAux23 = (TextView) view.findViewById(R.id.et_aux2_3);
        etAux23.setOnClickListener(this);
        etAux24 = (TextView) view.findViewById(R.id.et_aux2_4);
        etAux24.setOnClickListener(this);
        cbAux25 = (ChangeButton) view.findViewById(R.id.cb_aux2_5);
        etAux27 = (TextView) view.findViewById(R.id.et_aux2_7);
        etAux27.setOnClickListener(this);
        cbAux28 = (ChangeButton) view.findViewById(R.id.cb_aux2_8);

        etAux21.addTextChangedListener(CommonUtil.getTextWatch());
        etAux22.addTextChangedListener(CommonUtil.getTextWatch());
        etAux23.addTextChangedListener(CommonUtil.getTextWatch());
        etAux24.addTextChangedListener(CommonUtil.getTextWatch());
        etAux27.addTextChangedListener(CommonUtil.getTextWatch());
    }

    /**
     * 初始化糖尿病相关view
     *
     * @param view
     */
    private void init1(View view) {

    }

    @Override
    public void lazyLoad() {
        if (position == 2) {//编辑
            //获取辅助检查
            Map<String, String> params = new WeakHashMap<>();
            params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
            new MutiFetcher(MedicalRecordCheckup[].class).fetch(getActivity(), "FindMedicalRecordCheckup3", "正在加载...", params);
        }
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.what.equals("FindMedicalRecordCheckup3") && isVisible) {
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                checkup = (MedicalRecordCheckup) event.getObjects().get(0);
                initData(checkup);
            }
        } else if (event.what.equals("AddMedicalRecordCheckup") && isVisible) {//提交数据
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
            } else {
                CommonUtil.showToast(getActivity(), "提交失败");
            }
        } else if (event.getWhat().equals("UpdateMedicalRecordCheckup") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "更新成功");
                //跳转到诊断
            } else {
                CommonUtil.showToast(getActivity(), "更新失败");
            }
        }
    }

    /**
     * 填充已有数据
     *
     * @param checkup
     */
    private void initData(MedicalRecordCheckup checkup) {
        mEtOther.setText(checkup.getOther());
        switch (illnessid) {
            case "1"://糖尿病
            case "9000"://other
                initData1(checkup);
                break;
            case "2"://dm
                initData2(checkup);
                break;
            case "3"://pcos
                initData3(checkup);
                break;
        }
        mHasLoadedOnce = true;
    }

    private void initData3(MedicalRecordCheckup check) {
        etAux3Date1.setText(CommonUtil.parseDateStr(check.getFieldsDate1()));

        etAux31.setText(check.getFields1());
        nsAux32.setText(check.getFields2());
    }

    private void initData2(MedicalRecordCheckup check) {
        etAux2Date1.setText(CommonUtil.parseDateStr(check.getFieldsDate1()));
        etAux21.setText(check.getFields1());
        etAux22.setText(check.getFields2());
        etAux23.setText(check.getFields3());
        etAux24.setText(check.getFields4());
        cbAux25.setChecked(check.getFields5().equals("true") ? true : false);
        etAux27.setText(check.getFields6());
        cbAux28.setChecked(check.getFields7().equals("true") ? true : false);
    }

    private void initData1(MedicalRecordCheckup check) {
    }

    @Override
    public int bindViewById() {
        switch (illnessid) {
            case "1"://糖尿病
            case "9000"://other
                return R.layout.frag_auxiliarycheck_print1;
            case "2"://dm
                return R.layout.frag_auxiliarycheck_print2;
            case "3"://pcos
                return R.layout.frag_auxiliarycheck_print3;
            default:
                return R.layout.frag_auxiliarycheck_print1;
        }
    }

    @Override
    public void onClick(View v) {
        CommonUtil.showNumberPickerDialog(getActivity(), v);
    }
}
