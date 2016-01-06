package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.MedicalRecordCheckup;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 实验室检查
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class LabCheckPrintFragment extends BaseFragment implements View.OnClickListener {
    //糖尿病
    TextView etLab1Date11;

    TextView etLab11;
    TextView etLab12;
    TextView etLab13;
    TextView etLab14;
    TextView etLab15;
    TextView etLab16;
    TextView etLab17;
    TextView etLab18;
    TextView etLab19;
    TextView etLab110;
    TextView etLab111;
    TextView etLab112;
    TextView etLab113;
    TextView etLab114;
    TextView etLab115;
    TextView etLab1Date21;
    TextView etLab1Chart11;
    TextView etLab1Chart12;
    TextView etLab1Chart13;
    TextView etLab1Chart14;
    TextView etLab1Chart15;
    TextView etLab1Chart16;
    TextView etLab1Chart17;
    TextView etLab1Chart18;
    TextView etLab1Chart19;
    TextView etLab1Chart110;
    TextView etLab1Chart111;
    TextView etLab1Chart112;
    TextView etLab1Chart113;
    TextView etLab1Chart114;
    TextView etLab1Chart115;
    //dm
    TextView etLab2Date11;

    TextView etLab21;
    TextView etLab22;
    TextView etLab23;
    TextView etLab24;
    TextView etLab25;
    TextView etLab26;
    TextView etLab2Date21;

    TextView etLab27;
    TextView etLab28;
    TextView etLab29;
    TextView etLab2Date31;

    TextView etLab210;
    TextView etLab211;
    TextView etLab212;
    //pcos
    TextView etLab3Date11;

    TextView etLab31;
    TextView etLab32;
    TextView etLab33;
    TextView etLab34;
    TextView etLab35;
    TextView etLab36;
    TextView etLab37;
    TextView etLab38;
    TextView etLab39;
    TextView etLab310;
    TextView etLab311;
    TextView etLab312;
    TextView etLab313;
    TextView etLab314;
    TextView etLab315;
    TextView etLab3Date21;

    TextView etLab3Chart11;
    TextView etLab3Chart12;
    TextView etLab3Chart13;
    TextView etLab3Chart14;
    TextView etLab3Chart15;
    TextView etLab3Chart16;
    TextView etLab3Chart17;
    TextView etLab3Chart18;
    TextView etLab3Chart19;
    TextView etLab3Chart110;
    TextView etLab3Date31;

    TextView etLab3Chart21;
    TextView etLab3Chart22;
    TextView etLab3Chart23;
    TextView etLab3Chart24;
    TextView etLab3Chart25;
    TextView etLab3Chart26;
    //other
    TextView etLab4Date11;

    TextView etLab41;
    TextView etLab42;
    TextView etLab43;
    TextView etLab44;
    TextView etLab45;
    TextView etLab46;
    TextView etLab47;
    TextView etLab48;
    TextView etLab49;
    TextView etLab410;
    TextView etLab411;
    TextView etLab412;
    TextView etLab413;
    TextView etLab414;
    TextView etLab415;
    TextView etLab4Date21;

    TextView etLab4Chart11;
    TextView etLab4Chart12;
    TextView etLab4Chart13;
    TextView etLab4Chart14;
    TextView etLab4Chart15;
    TextView etLab4Chart16;
    TextView etLab4Chart17;
    TextView etLab4Chart18;
    TextView etLab4Chart19;
    TextView etLab4Chart110;

    private MedicalRecordCheckup checkup;

    public static LabCheckPrintFragment newInstance(String illnessid, int mPosition, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", mPosition);
        args.putInt("SequenceNumber", mSequenceNumber);
        LabCheckPrintFragment fragment = new LabCheckPrintFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        illnessid = "1";//统一到糖尿病
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switch (illnessid) {
            case "1"://糖尿病
                init1(view);
                break;
            case "2"://dm
                init2(view);
                break;
            case "3"://pcos
                init3(view);
                break;
            case "9000"://other
                init9000(view);
                break;
        }
    }

    @Override
    public void certainSubmit() {
        boolean isAdd = false;//标志位，用来区分是新增还是更新
        if (checkup == null) {
            isAdd = true;
            checkup = new MedicalRecordCheckup();
            checkup.setId(CommonUtil.generateGUID());
            checkup.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
            checkup.setInputUser(String.valueOf(getmUserInfo().getUserID()));
            checkup.setIllnessID(illnessid);
            checkup.setType("2");
        }
        checkup.setOther(mEtOther.getText().toString());
        checkup.setInputDate(CommonUtil.getSysDate());
        switch (illnessid) {
            case "1":
                if (!TextUtils.isEmpty(etLab1Date11.getText().toString())) {
                    checkup.setFieldsDate1(etLab1Date11.getText().toString());
                }
                checkup.setFields1(etLab11.getText().toString());
                checkup.setFields2(etLab12.getText().toString());
                checkup.setFields3(etLab13.getText().toString());
                checkup.setFields4(etLab14.getText().toString());
                checkup.setFields5(etLab15.getText().toString());
                checkup.setFields6(etLab16.getText().toString());
                checkup.setFields7(etLab17.getText().toString());
                checkup.setFields8(etLab18.getText().toString());
                checkup.setFields9(etLab19.getText().toString());
                checkup.setFields10(etLab110.getText().toString());
                checkup.setFields11(etLab111.getText().toString());
                checkup.setFields12(etLab112.getText().toString());
                checkup.setFields13(etLab113.getText().toString());
                checkup.setFields14(etLab114.getText().toString());
                checkup.setFields15(etLab115.getText().toString());
                if (!TextUtils.isEmpty(etLab1Date21.getText().toString())) {
                    checkup.setFieldsDate2(etLab1Date21.getText().toString());
                }
                checkup.setFields16(etLab1Chart11.getText().toString());
                checkup.setFields17(etLab1Chart12.getText().toString());
                checkup.setFields18(etLab1Chart13.getText().toString());
                checkup.setFields19(etLab1Chart14.getText().toString());
                checkup.setFields20(etLab1Chart15.getText().toString());
                checkup.setFields21(etLab1Chart16.getText().toString());
                checkup.setFields22(etLab1Chart17.getText().toString());
                checkup.setFields23(etLab1Chart18.getText().toString());
                checkup.setFields24(etLab1Chart19.getText().toString());
                checkup.setFields25(etLab1Chart110.getText().toString());
                checkup.setFields26(etLab1Chart111.getText().toString());
                checkup.setFields27(etLab1Chart112.getText().toString());
                checkup.setFields28(etLab1Chart113.getText().toString());
                checkup.setFields29(etLab1Chart114.getText().toString());
                checkup.setFields30(etLab1Chart115.getText().toString());
                break;
            case "2":
                if (!TextUtils.isEmpty(etLab2Date11.getText().toString())) {
                    checkup.setFieldsDate1(etLab2Date11.getText().toString());
                }
                checkup.setFields1(etLab21.getText().toString());
                checkup.setFields2(etLab22.getText().toString());
                checkup.setFields3(etLab23.getText().toString());
                checkup.setFields4(etLab24.getText().toString());
                checkup.setFields5(etLab25.getText().toString());
                checkup.setFields6(etLab26.getText().toString());
                if (!TextUtils.isEmpty(etLab2Date21.getText().toString())) {
                    checkup.setFieldsDate2(etLab2Date21.getText().toString());
                }
                checkup.setFields7(etLab27.getText().toString());
                checkup.setFields8(etLab28.getText().toString());
                checkup.setFields9(etLab29.getText().toString());
                if (!TextUtils.isEmpty(etLab2Date31.getText().toString())) {
                    checkup.setFieldsDate3(etLab2Date31.getText().toString());
                }
                checkup.setFields10(etLab210.getText().toString());
                checkup.setFields11(etLab211.getText().toString());
                checkup.setFields12(etLab212.getText().toString());
                break;
            case "3":
                if (!TextUtils.isEmpty(etLab3Date11.getText().toString())) {
                    checkup.setFieldsDate1(etLab3Date11.getText().toString());
                }
                checkup.setFields1(etLab31.getText().toString());
                checkup.setFields2(etLab32.getText().toString());
                checkup.setFields3(etLab33.getText().toString());
                checkup.setFields4(etLab34.getText().toString());
                checkup.setFields5(etLab35.getText().toString());
                checkup.setFields6(etLab36.getText().toString());
                checkup.setFields7(etLab37.getText().toString());
                checkup.setFields8(etLab38.getText().toString());
                checkup.setFields9(etLab39.getText().toString());
                checkup.setFields10(etLab310.getText().toString());
                checkup.setFields11(etLab311.getText().toString());
                checkup.setFields12(etLab312.getText().toString());
                checkup.setFields13(etLab313.getText().toString());
                checkup.setFields14(etLab314.getText().toString());
                checkup.setFields15(etLab315.getText().toString());
                if (!TextUtils.isEmpty(etLab3Date21.getText().toString())) {
                    checkup.setFieldsDate2(etLab3Date21.getText().toString());
                }
                checkup.setFields16(etLab3Chart11.getText().toString());
                checkup.setFields17(etLab3Chart12.getText().toString());
                checkup.setFields18(etLab3Chart13.getText().toString());
                checkup.setFields19(etLab3Chart14.getText().toString());
                checkup.setFields20(etLab3Chart15.getText().toString());
                checkup.setFields21(etLab3Chart16.getText().toString());
                checkup.setFields22(etLab3Chart17.getText().toString());
                checkup.setFields23(etLab3Chart18.getText().toString());
                checkup.setFields24(etLab3Chart19.getText().toString());
                checkup.setFields25(etLab3Chart110.getText().toString());
                if (!TextUtils.isEmpty(etLab3Date31.getText().toString())) {
                    checkup.setFieldsDate3(etLab3Date31.getText().toString());
                }
                checkup.setFields26(etLab3Chart21.getText().toString());
                checkup.setFields27(etLab3Chart22.getText().toString());
                checkup.setFields28(etLab3Chart23.getText().toString());
                checkup.setFields29(etLab3Chart24.getText().toString());
                checkup.setFields30(etLab3Chart25.getText().toString());
                checkup.setFields31(etLab3Chart26.getText().toString());
                break;
            case "9000":
                if (!TextUtils.isEmpty(etLab4Date11.getText().toString())) {
                    checkup.setFieldsDate1(etLab4Date11.getText().toString());
                }
                checkup.setFields1(etLab41.getText().toString());
                checkup.setFields2(etLab42.getText().toString());
                checkup.setFields3(etLab43.getText().toString());
                checkup.setFields4(etLab44.getText().toString());
                checkup.setFields5(etLab45.getText().toString());
                checkup.setFields6(etLab46.getText().toString());
                checkup.setFields7(etLab47.getText().toString());
                checkup.setFields8(etLab48.getText().toString());
                checkup.setFields9(etLab49.getText().toString());
                checkup.setFields10(etLab410.getText().toString());
                checkup.setFields11(etLab411.getText().toString());
                checkup.setFields12(etLab412.getText().toString());
                checkup.setFields13(etLab413.getText().toString());
                checkup.setFields14(etLab414.getText().toString());
                checkup.setFields15(etLab415.getText().toString());
                if (!TextUtils.isEmpty(etLab4Date21.getText().toString())) {
                    checkup.setFieldsDate2(etLab4Date21.getText().toString());
                }
                checkup.setFields16(etLab4Chart11.getText().toString());
                checkup.setFields17(etLab4Chart12.getText().toString());
                checkup.setFields18(etLab4Chart13.getText().toString());
                checkup.setFields19(etLab4Chart14.getText().toString());
                checkup.setFields20(etLab4Chart15.getText().toString());
                checkup.setFields21(etLab4Chart16.getText().toString());
                checkup.setFields22(etLab4Chart17.getText().toString());
                checkup.setFields23(etLab4Chart18.getText().toString());
                checkup.setFields24(etLab4Chart19.getText().toString());
                checkup.setFields25(etLab4Chart110.getText().toString());
                break;
        }
        if (isAdd) {//初诊+复诊
            new SingleFetcher(String.class).addMedicalRecord_checkup(getActivity(), "正在提交...", checkup);
        } else {
            new SingleFetcher(String.class).updateMedicalRecord_checkup(getActivity(), "正在更新数据...", checkup);
        }
    }

    /**
     * 初始化其他疾病相关view
     *
     * @param view
     */
    private void init9000(View view) {
        etLab4Date11 = (TextView) view.findViewById(R.id.et_lab4_date1_1);
        etLab4Date11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        etLab41 = (TextView) view.findViewById(R.id.et_lab4_1);
        etLab42 = (TextView) view.findViewById(R.id.et_lab4_2);
        etLab43 = (TextView) view.findViewById(R.id.et_lab4_3);
        etLab44 = (TextView) view.findViewById(R.id.et_lab4_4);
        etLab45 = (TextView) view.findViewById(R.id.et_lab4_5);
        etLab46 = (TextView) view.findViewById(R.id.et_lab4_6);
        etLab47 = (TextView) view.findViewById(R.id.et_lab4_7);
        etLab48 = (TextView) view.findViewById(R.id.et_lab4_8);
        etLab49 = (TextView) view.findViewById(R.id.et_lab4_9);
        etLab410 = (TextView) view.findViewById(R.id.et_lab4_10);
        etLab411 = (TextView) view.findViewById(R.id.et_lab4_11);
        etLab412 = (TextView) view.findViewById(R.id.et_lab4_12);
        etLab413 = (TextView) view.findViewById(R.id.et_lab4_13);
        etLab414 = (TextView) view.findViewById(R.id.et_lab4_14);
        etLab415 = (TextView) view.findViewById(R.id.et_lab4_15);
        etLab41.setOnClickListener(this);
        etLab42.setOnClickListener(this);
        etLab43.setOnClickListener(this);
        etLab44.setOnClickListener(this);
        etLab45.setOnClickListener(this);
        etLab46.setOnClickListener(this);
        etLab47.setOnClickListener(this);
        etLab48.setOnClickListener(this);
        etLab49.setOnClickListener(this);
        etLab410.setOnClickListener(this);
        etLab411.setOnClickListener(this);
        etLab412.setOnClickListener(this);
        etLab413.setOnClickListener(this);
        etLab414.setOnClickListener(this);
        etLab415.setOnClickListener(this);
        etLab41.addTextChangedListener(CommonUtil.getTextWatch());
        etLab42.addTextChangedListener(CommonUtil.getTextWatch());
        etLab43.addTextChangedListener(CommonUtil.getTextWatch());
        etLab44.addTextChangedListener(CommonUtil.getTextWatch());
        etLab45.addTextChangedListener(CommonUtil.getTextWatch());
        etLab46.addTextChangedListener(CommonUtil.getTextWatch());
        etLab47.addTextChangedListener(CommonUtil.getTextWatch());
        etLab48.addTextChangedListener(CommonUtil.getTextWatch());
        etLab49.addTextChangedListener(CommonUtil.getTextWatch());
        etLab410.addTextChangedListener(CommonUtil.getTextWatch());
        etLab411.addTextChangedListener(CommonUtil.getTextWatch());
        etLab412.addTextChangedListener(CommonUtil.getTextWatch());
        etLab413.addTextChangedListener(CommonUtil.getTextWatch());
        etLab414.addTextChangedListener(CommonUtil.getTextWatch());
        etLab415.addTextChangedListener(CommonUtil.getTextWatch());

        etLab4Date21 = (TextView) view.findViewById(R.id.et_lab4_date2_1);
        etLab4Date21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        etLab4Chart11 = (TextView) view.findViewById(R.id.et_lab4_chart1_1);
        etLab4Chart12 = (TextView) view.findViewById(R.id.et_lab4_chart1_2);
        etLab4Chart13 = (TextView) view.findViewById(R.id.et_lab4_chart1_3);
        etLab4Chart14 = (TextView) view.findViewById(R.id.et_lab4_chart1_4);
        etLab4Chart15 = (TextView) view.findViewById(R.id.et_lab4_chart1_5);
        etLab4Chart16 = (TextView) view.findViewById(R.id.et_lab4_chart1_6);
        etLab4Chart17 = (TextView) view.findViewById(R.id.et_lab4_chart1_7);
        etLab4Chart18 = (TextView) view.findViewById(R.id.et_lab4_chart1_8);
        etLab4Chart19 = (TextView) view.findViewById(R.id.et_lab4_chart1_9);
        etLab4Chart110 = (TextView) view.findViewById(R.id.et_lab4_chart1_10);
        etLab4Chart11.setOnClickListener(this);
        etLab4Chart12.setOnClickListener(this);
        etLab4Chart13.setOnClickListener(this);
        etLab4Chart14.setOnClickListener(this);
        etLab4Chart15.setOnClickListener(this);
        etLab4Chart16.setOnClickListener(this);
        etLab4Chart17.setOnClickListener(this);
        etLab4Chart18.setOnClickListener(this);
        etLab4Chart19.setOnClickListener(this);
        etLab4Chart110.setOnClickListener(this);
        etLab4Chart11.addTextChangedListener(CommonUtil.getTextWatch());
        etLab4Chart12.addTextChangedListener(CommonUtil.getTextWatch());
        etLab4Chart13.addTextChangedListener(CommonUtil.getTextWatch());
        etLab4Chart14.addTextChangedListener(CommonUtil.getTextWatch());
        etLab4Chart15.addTextChangedListener(CommonUtil.getTextWatch());
        etLab4Chart16.addTextChangedListener(CommonUtil.getTextWatch());
        etLab4Chart17.addTextChangedListener(CommonUtil.getTextWatch());
        etLab4Chart18.addTextChangedListener(CommonUtil.getTextWatch());
        etLab4Chart19.addTextChangedListener(CommonUtil.getTextWatch());
        etLab4Chart110.addTextChangedListener(CommonUtil.getTextWatch());
    }

    /**
     * 初始化pcos相关view
     *
     * @param view
     */
    private void init3(View view) {
        etLab3Date11 = (TextView) view.findViewById(R.id.et_lab3_date1_1);
        etLab3Date11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        etLab31 = (TextView) view.findViewById(R.id.et_lab3_1);
        etLab32 = (TextView) view.findViewById(R.id.et_lab3_2);
        etLab33 = (TextView) view.findViewById(R.id.et_lab3_3);
        etLab34 = (TextView) view.findViewById(R.id.et_lab3_4);
        etLab35 = (TextView) view.findViewById(R.id.et_lab3_5);
        etLab36 = (TextView) view.findViewById(R.id.et_lab3_6);
        etLab37 = (TextView) view.findViewById(R.id.et_lab3_7);
        etLab38 = (TextView) view.findViewById(R.id.et_lab3_8);
        etLab39 = (TextView) view.findViewById(R.id.et_lab3_9);
        etLab310 = (TextView) view.findViewById(R.id.et_lab3_10);
        etLab311 = (TextView) view.findViewById(R.id.et_lab3_11);
        etLab312 = (TextView) view.findViewById(R.id.et_lab3_12);
        etLab313 = (TextView) view.findViewById(R.id.et_lab3_13);
        etLab314 = (TextView) view.findViewById(R.id.et_lab3_14);
        etLab315 = (TextView) view.findViewById(R.id.et_lab3_15);

        etLab31.setOnClickListener(this);
        etLab32.setOnClickListener(this);
        etLab33.setOnClickListener(this);
        etLab34.setOnClickListener(this);
        etLab35.setOnClickListener(this);
        etLab36.setOnClickListener(this);
        etLab37.setOnClickListener(this);
        etLab38.setOnClickListener(this);
        etLab39.setOnClickListener(this);
        etLab310.setOnClickListener(this);
        etLab311.setOnClickListener(this);
        etLab313.setOnClickListener(this);
        etLab314.setOnClickListener(this);
        etLab315.setOnClickListener(this);

        etLab31.addTextChangedListener(CommonUtil.getTextWatch());
        etLab32.addTextChangedListener(CommonUtil.getTextWatch());
        etLab33.addTextChangedListener(CommonUtil.getTextWatch());
        etLab34.addTextChangedListener(CommonUtil.getTextWatch());
        etLab35.addTextChangedListener(CommonUtil.getTextWatch());
        etLab36.addTextChangedListener(CommonUtil.getTextWatch());
        etLab37.addTextChangedListener(CommonUtil.getTextWatch());
        etLab38.addTextChangedListener(CommonUtil.getTextWatch());
        etLab39.addTextChangedListener(CommonUtil.getTextWatch());
//        etLab310.addTextChangedListener(CommonUtil.getTextWatch());
//        etLab311.addTextChangedListener(CommonUtil.getTextWatch());
        etLab312.addTextChangedListener(CommonUtil.getTextWatch());
        etLab313.addTextChangedListener(CommonUtil.getTextWatch());
        etLab314.addTextChangedListener(CommonUtil.getTextWatch());
        etLab315.addTextChangedListener(CommonUtil.getTextWatch());

        etLab3Date21 = (TextView) view.findViewById(R.id.et_lab3_date2_1);
        etLab3Date21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        etLab3Chart11 = (TextView) view.findViewById(R.id.et_lab3_chart1_1);
        etLab3Chart12 = (TextView) view.findViewById(R.id.et_lab3_chart1_2);
        etLab3Chart13 = (TextView) view.findViewById(R.id.et_lab3_chart1_3);
        etLab3Chart14 = (TextView) view.findViewById(R.id.et_lab3_chart1_4);
        etLab3Chart15 = (TextView) view.findViewById(R.id.et_lab3_chart1_5);
        etLab3Chart16 = (TextView) view.findViewById(R.id.et_lab3_chart1_6);
        etLab3Chart17 = (TextView) view.findViewById(R.id.et_lab3_chart1_7);
        etLab3Chart18 = (TextView) view.findViewById(R.id.et_lab3_chart1_8);
        etLab3Chart19 = (TextView) view.findViewById(R.id.et_lab3_chart1_9);
        etLab3Chart110 = (TextView) view.findViewById(R.id.et_lab3_chart1_10);

        etLab3Chart11.setOnClickListener(this);
        etLab3Chart12.setOnClickListener(this);
        etLab3Chart13.setOnClickListener(this);
        etLab3Chart14.setOnClickListener(this);
        etLab3Chart15.setOnClickListener(this);
        etLab3Chart16.setOnClickListener(this);
        etLab3Chart17.setOnClickListener(this);
        etLab3Chart18.setOnClickListener(this);
        etLab3Chart19.setOnClickListener(this);
        etLab3Chart110.setOnClickListener(this);

        etLab3Chart11.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart12.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart13.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart14.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart15.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart16.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart17.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart18.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart19.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart110.addTextChangedListener(CommonUtil.getTextWatch());

        etLab3Date31 = (TextView) view.findViewById(R.id.et_lab3_date3_1);
        etLab3Date31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        etLab3Chart21 = (TextView) view.findViewById(R.id.et_lab3_chart2_1);
        etLab3Chart22 = (TextView) view.findViewById(R.id.et_lab3_chart2_2);
        etLab3Chart23 = (TextView) view.findViewById(R.id.et_lab3_chart2_3);
        etLab3Chart24 = (TextView) view.findViewById(R.id.et_lab3_chart2_4);
        etLab3Chart25 = (TextView) view.findViewById(R.id.et_lab3_chart2_5);
        etLab3Chart26 = (TextView) view.findViewById(R.id.et_lab3_chart2_6);

        etLab3Chart21.setOnClickListener(this);
        etLab3Chart22.setOnClickListener(this);
        etLab3Chart23.setOnClickListener(this);
        etLab3Chart24.setOnClickListener(this);
        etLab3Chart25.setOnClickListener(this);
        etLab3Chart26.setOnClickListener(this);

        etLab3Chart21.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart22.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart23.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart24.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart25.addTextChangedListener(CommonUtil.getTextWatch());
        etLab3Chart26.addTextChangedListener(CommonUtil.getTextWatch());

        etLab310.addTextChangedListener(new AutoText());
        etLab311.addTextChangedListener(new AutoText());
    }

    /**
     * 初始化甲状腺相关view
     *
     * @param view
     */
    private void init2(View view) {
        etLab2Date11 = (TextView) view.findViewById(R.id.et_lab2_date1_1);
        etLab2Date11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        etLab21 = (TextView) view.findViewById(R.id.et_lab2_1);
        etLab22 = (TextView) view.findViewById(R.id.et_lab2_2);
        etLab23 = (TextView) view.findViewById(R.id.et_lab2_3);
        etLab24 = (TextView) view.findViewById(R.id.et_lab2_4);
        etLab25 = (TextView) view.findViewById(R.id.et_lab2_5);
        etLab26 = (TextView) view.findViewById(R.id.et_lab2_6);

        etLab21.setOnClickListener(this);
        etLab22.setOnClickListener(this);
        etLab23.setOnClickListener(this);
        etLab24.setOnClickListener(this);
        etLab25.setOnClickListener(this);
        etLab26.setOnClickListener(this);

        etLab21.addTextChangedListener(CommonUtil.getTextWatch());
        etLab22.addTextChangedListener(CommonUtil.getTextWatch());
        etLab23.addTextChangedListener(CommonUtil.getTextWatch());
        etLab24.addTextChangedListener(CommonUtil.getTextWatch());
        etLab25.addTextChangedListener(CommonUtil.getTextWatch());
        etLab26.addTextChangedListener(CommonUtil.getTextWatch());

        etLab2Date21 = (TextView) view.findViewById(R.id.et_lab2_date2_1);
        etLab2Date21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        etLab27 = (TextView) view.findViewById(R.id.et_lab2_7);
        etLab28 = (TextView) view.findViewById(R.id.et_lab2_8);
        etLab29 = (TextView) view.findViewById(R.id.et_lab2_9);
        etLab27.setOnClickListener(this);
        etLab28.setOnClickListener(this);
        etLab29.setOnClickListener(this);
        etLab27.addTextChangedListener(CommonUtil.getTextWatch());
        etLab28.addTextChangedListener(CommonUtil.getTextWatch());
        etLab29.addTextChangedListener(CommonUtil.getTextWatch());

        etLab2Date31 = (TextView) view.findViewById(R.id.et_lab2_date3_1);
        etLab2Date31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        etLab210 = (TextView) view.findViewById(R.id.et_lab2_10);
        etLab211 = (TextView) view.findViewById(R.id.et_lab2_11);
        etLab212 = (TextView) view.findViewById(R.id.et_lab2_12);
        etLab210.setOnClickListener(this);
        etLab211.setOnClickListener(this);
        etLab212.setOnClickListener(this);
        etLab210.addTextChangedListener(CommonUtil.getTextWatch());
        etLab211.addTextChangedListener(CommonUtil.getTextWatch());
        etLab212.addTextChangedListener(CommonUtil.getTextWatch());
    }

    /**
     * 初始化糖尿病相关view
     *
     * @param view
     */
    private void init1(View view) {
        etLab1Date11 = (TextView) view.findViewById(R.id.et_lab1_date1_1);
        etLab1Date11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        etLab11 = (TextView) view.findViewById(R.id.et_lab1_1);
        etLab12 = (TextView) view.findViewById(R.id.et_lab1_2);
        etLab13 = (TextView) view.findViewById(R.id.et_lab1_3);
        etLab14 = (TextView) view.findViewById(R.id.et_lab1_4);
        etLab15 = (TextView) view.findViewById(R.id.et_lab1_5);
        etLab16 = (TextView) view.findViewById(R.id.et_lab1_6);
        etLab17 = (TextView) view.findViewById(R.id.et_lab1_7);
        etLab18 = (TextView) view.findViewById(R.id.et_lab1_8);
        etLab19 = (TextView) view.findViewById(R.id.et_lab1_9);
        etLab110 = (TextView) view.findViewById(R.id.et_lab1_10);
        etLab111 = (TextView) view.findViewById(R.id.et_lab1_11);
        etLab112 = (TextView) view.findViewById(R.id.et_lab1_12);
        etLab113 = (TextView) view.findViewById(R.id.et_lab1_13);
        etLab114 = (TextView) view.findViewById(R.id.et_lab1_14);
        etLab115 = (TextView) view.findViewById(R.id.et_lab1_15);

        etLab11.setOnClickListener(this);
        etLab12.setOnClickListener(this);
        etLab13.setOnClickListener(this);
        etLab14.setOnClickListener(this);
        etLab15.setOnClickListener(this);
        etLab16.setOnClickListener(this);
        etLab17.setOnClickListener(this);
        etLab18.setOnClickListener(this);
        etLab19.setOnClickListener(this);
        etLab110.setOnClickListener(this);
        etLab111.setOnClickListener(this);
        etLab112.setOnClickListener(this);
        etLab113.setOnClickListener(this);
        etLab114.setOnClickListener(this);
        etLab115.setOnClickListener(this);

        etLab11.addTextChangedListener(CommonUtil.getTextWatch());
        etLab12.addTextChangedListener(CommonUtil.getTextWatch());
        etLab13.addTextChangedListener(CommonUtil.getTextWatch());
        etLab14.addTextChangedListener(CommonUtil.getTextWatch());
        etLab15.addTextChangedListener(CommonUtil.getTextWatch());
        etLab16.addTextChangedListener(CommonUtil.getTextWatch());
        etLab17.addTextChangedListener(CommonUtil.getTextWatch());
        etLab18.addTextChangedListener(CommonUtil.getTextWatch());
        etLab19.addTextChangedListener(CommonUtil.getTextWatch());
        etLab110.addTextChangedListener(CommonUtil.getTextWatch());
        etLab111.addTextChangedListener(CommonUtil.getTextWatch());
        etLab112.addTextChangedListener(CommonUtil.getTextWatch());
        etLab113.addTextChangedListener(CommonUtil.getTextWatch());
        etLab114.addTextChangedListener(CommonUtil.getTextWatch());
        etLab115.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Date21 = (TextView) view.findViewById(R.id.et_lab1_date2_1);
        etLab1Date21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });

        etLab1Chart11 = (TextView) view.findViewById(R.id.et_lab1_chart1_1);
        etLab1Chart12 = (TextView) view.findViewById(R.id.et_lab1_chart1_2);
        etLab1Chart13 = (TextView) view.findViewById(R.id.et_lab1_chart1_3);
        etLab1Chart14 = (TextView) view.findViewById(R.id.et_lab1_chart1_4);
        etLab1Chart15 = (TextView) view.findViewById(R.id.et_lab1_chart1_5);
        etLab1Chart16 = (TextView) view.findViewById(R.id.et_lab1_chart1_6);
        etLab1Chart17 = (TextView) view.findViewById(R.id.et_lab1_chart1_7);
        etLab1Chart18 = (TextView) view.findViewById(R.id.et_lab1_chart1_8);
        etLab1Chart19 = (TextView) view.findViewById(R.id.et_lab1_chart1_9);
        etLab1Chart110 = (TextView) view.findViewById(R.id.et_lab1_chart1_10);
        etLab1Chart111 = (TextView) view.findViewById(R.id.et_lab1_chart1_11);
        etLab1Chart112 = (TextView) view.findViewById(R.id.et_lab1_chart1_12);
        etLab1Chart113 = (TextView) view.findViewById(R.id.et_lab1_chart1_13);
        etLab1Chart114 = (TextView) view.findViewById(R.id.et_lab1_chart1_14);
        etLab1Chart115 = (TextView) view.findViewById(R.id.et_lab1_chart1_15);

        etLab1Chart11.setOnClickListener(this);
        etLab1Chart12.setOnClickListener(this);
        etLab1Chart13.setOnClickListener(this);
        etLab1Chart14.setOnClickListener(this);
        etLab1Chart15.setOnClickListener(this);
        etLab1Chart16.setOnClickListener(this);
        etLab1Chart17.setOnClickListener(this);
        etLab1Chart18.setOnClickListener(this);
        etLab1Chart19.setOnClickListener(this);
        etLab1Chart110.setOnClickListener(this);
        etLab1Chart111.setOnClickListener(this);
        etLab1Chart112.setOnClickListener(this);
        etLab1Chart113.setOnClickListener(this);
        etLab1Chart114.setOnClickListener(this);
        etLab1Chart115.setOnClickListener(this);

        etLab1Chart11.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart11.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart12.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart12.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart13.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart13.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart14.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart14.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart15.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart15.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart16.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart16.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart17.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart17.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart18.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart18.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart19.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart19.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart110.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart110.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart111.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart111.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart112.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart112.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart113.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart113.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart114.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart114.addTextChangedListener(CommonUtil.getTextWatch());
        etLab1Chart115.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etLab1Chart115.addTextChangedListener(CommonUtil.getTextWatch());
    }

    @Override
    public void lazyLoad() {
        if (position == 2) {//编辑
            //获取实验室检查
            Map<String, String> params = new WeakHashMap<>();
            params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
            new MutiFetcher(MedicalRecordCheckup[].class).fetch(getActivity(), "FindMedicalRecordCheckup2", "正在加载...", params);
        }
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.what.equals("FindMedicalRecordCheckup2") && isVisible) {
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                checkup = (MedicalRecordCheckup) event.getObjects().get(0);
                initData(checkup);
            }
        } else if (event.what.equals("AddMedicalRecordCheckup") && isVisible) {//提交数据
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
            } else if (event.getCode().equals("600")) {
                CommonUtil.showToast(getActivity(), "提交失败,请输入正确的日期格式");
            } else {
                CommonUtil.showToast(getActivity(), "提交失败");
            }
        } else if (event.getWhat().equals("UpdateMedicalRecordCheckup") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "更新成功");
                //跳转到辅助检查
            } else if (event.getCode().equals("600")) {
                CommonUtil.showToast(getActivity(), "提交失败,请输入正确的日期格式");
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
                initData1(checkup);
                break;
            case "2"://dm
                initData2(checkup);
                break;
            case "3"://pcos
                initData3(checkup);
                break;
            case "9000"://other
                initData900(checkup);
                break;
        }
        mHasLoadedOnce = true;
    }

    private void initData900(MedicalRecordCheckup check) {
        etLab4Date11.setText(CommonUtil.parseForminnerStr(check.getFieldsDate1()));
        etLab41.setText(check.getFields1());
        etLab42.setText(check.getFields2());
        etLab43.setText(check.getFields3());
        etLab44.setText(check.getFields4());
        etLab45.setText(check.getFields5());
        etLab46.setText(check.getFields6());
        etLab47.setText(check.getFields7());
        etLab48.setText(check.getFields8());
        etLab49.setText(check.getFields9());
        etLab410.setText(check.getFields10());
        etLab411.setText(check.getFields11());
        etLab412.setText(check.getFields12());
        etLab413.setText(check.getFields13());
        etLab414.setText(check.getFields14());
        etLab415.setText(check.getFields15());
        etLab4Date21.setText(CommonUtil.parseForminnerStr(check.getFieldsDate2()));
        etLab4Chart11.setText(check.getFields16());
        etLab4Chart12.setText(check.getFields17());
        etLab4Chart13.setText(check.getFields18());
        etLab4Chart14.setText(check.getFields19());
        etLab4Chart15.setText(check.getFields20());
        etLab4Chart16.setText(check.getFields21());
        etLab4Chart17.setText(check.getFields22());
        etLab4Chart18.setText(check.getFields23());
        etLab4Chart19.setText(check.getFields24());
        etLab4Chart110.setText(check.getFields25());
    }

    private void initData3(MedicalRecordCheckup check) {
        etLab3Date11.setText(CommonUtil.parseForminnerStr(check.getFieldsDate1()));
        etLab31.setText(check.getFields1());
        etLab32.setText(check.getFields2());
        etLab33.setText(check.getFields3());
        etLab34.setText(check.getFields4());
        etLab35.setText(check.getFields5());
        etLab36.setText(check.getFields6());
        etLab37.setText(check.getFields7());
        etLab38.setText(check.getFields8());
        etLab39.setText(check.getFields9());
        etLab310.setText(check.getFields10());
        etLab311.setText(check.getFields11());
        etLab312.setText(check.getFields12());
        etLab313.setText(check.getFields13());
        etLab314.setText(check.getFields14());
        etLab315.setText(check.getFields15());
        etLab3Date21.setText(CommonUtil.parseForminnerStr(check.getFieldsDate2()));
        etLab3Chart11.setText(check.getFields16());
        etLab3Chart12.setText(check.getFields17());
        etLab3Chart13.setText(check.getFields18());
        etLab3Chart14.setText(check.getFields19());
        etLab3Chart15.setText(check.getFields20());
        etLab3Chart16.setText(check.getFields21());
        etLab3Chart17.setText(check.getFields22());
        etLab3Chart18.setText(check.getFields23());
        etLab3Chart19.setText(check.getFields24());
        etLab3Chart110.setText(check.getFields25());
        etLab3Date31.setText(CommonUtil.parseForminnerStr(check.getFieldsDate3()));
        etLab3Chart21.setText(check.getFields26());
        etLab3Chart22.setText(check.getFields27());
        etLab3Chart23.setText(check.getFields28());
        etLab3Chart24.setText(check.getFields29());
        etLab3Chart25.setText(check.getFields30());
        etLab3Chart26.setText(check.getFields31());
    }

    private void initData2(MedicalRecordCheckup check) {
        etLab2Date11.setText(CommonUtil.parseForminnerStr(check.getFieldsDate1()));
        etLab21.setText(check.getFields1());
        etLab22.setText(check.getFields2());
        etLab23.setText(check.getFields3());
        etLab24.setText(check.getFields4());
        etLab25.setText(check.getFields5());
        etLab26.setText(check.getFields6());
        etLab2Date21.setText(CommonUtil.parseForminnerStr(check.getFieldsDate2()));
        etLab27.setText(check.getFields7());
        etLab28.setText(check.getFields8());
        etLab29.setText(check.getFields9());
        etLab2Date31.setText(CommonUtil.parseForminnerStr(check.getFieldsDate3()));
        etLab210.setText(check.getFields10());
        etLab211.setText(check.getFields11());
        etLab212.setText(check.getFields12());
    }

    private void initData1(MedicalRecordCheckup check) {
        etLab1Date11.setText(CommonUtil.parseForminnerStr(check.getFieldsDate1()));
        etLab11.setText(check.getFields1());
        etLab12.setText(check.getFields2());
        etLab13.setText(check.getFields3());
        etLab14.setText(check.getFields4());
        etLab15.setText(check.getFields5());
        etLab16.setText(check.getFields6());
        etLab17.setText(check.getFields7());
        etLab18.setText(check.getFields8());
        etLab19.setText(check.getFields9());
        etLab110.setText(check.getFields10());
        etLab111.setText(check.getFields11());
        etLab112.setText(check.getFields12());
        etLab113.setText(check.getFields13());
        etLab114.setText(check.getFields14());
        etLab115.setText(check.getFields15());
        etLab1Date21.setText(CommonUtil.parseForminnerStr(check.getFieldsDate2()));
        etLab1Chart11.setText(check.getFields16());
        etLab1Chart12.setText(check.getFields17());
        etLab1Chart13.setText(check.getFields18());
        etLab1Chart14.setText(check.getFields19());
        etLab1Chart15.setText(check.getFields20());
        etLab1Chart16.setText(check.getFields21());
        etLab1Chart17.setText(check.getFields22());
        etLab1Chart18.setText(check.getFields23());
        etLab1Chart19.setText(check.getFields24());
        etLab1Chart110.setText(check.getFields25());
        etLab1Chart111.setText(check.getFields26());
        etLab1Chart112.setText(check.getFields27());
        etLab1Chart113.setText(check.getFields28());
        etLab1Chart114.setText(check.getFields29());
        etLab1Chart115.setText(check.getFields30());
    }

    @Override
    public int bindViewById() {
        switch (illnessid) {
            case "1"://糖尿病
                return R.layout.frag_labcheck_print1;
            case "2"://dm
                return R.layout.frag_labcheck_print2;
            case "3"://pcos
                return R.layout.frag_labcheck_print3;
            case "9000"://other
                return R.layout.frag_labcheck_print4;
            default:
                return R.layout.frag_labcheck_print1;
        }
    }

    @Override
    public void onClick(View v) {
        CommonUtil.showNumberPickerDialog(getActivity(), v);
    }

    /**
     * 文字改变自动填充
     */
    class AutoText implements TextWatcher {
        public AutoText() {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String str_lh;
            String str_fsh;
            if (illnessid.equals("3")) {
                str_lh = etLab310.getText().toString().trim();
                str_fsh = etLab311.getText().toString().trim();
                if (!TextUtils.isEmpty(str_lh) && !TextUtils.isEmpty(str_fsh) && !str_fsh.equals("0")) {
                    etLab312.setText(CommonUtil.getLHFSH(str_lh, str_fsh));
                }
            }
        }
    }
}
