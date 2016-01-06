package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.MHistory_Now;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.widget.ChangeButton;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 现病史
 * Created by GMY on 2015/9/9 13:18.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordNowFragment extends BaseFragment implements View.OnClickListener {
    //糖尿病
    TextView etRecordnow11_1;
    NiceSpinner nsRecordnow11_2;
    TextView etRecordnow12;
    TextView etRecordnow122;
    NiceSpinner nsRecordnow12;
    TextView etRecordnow13;
    TextView etRecordnow132;
    NiceSpinner nsRecordnow13;
    ChangeButton cbRecordnow14;
    ChangeButton cbRecordnow15;
    ChangeButton cbRecordnow16;
    ChangeButton cbRecordnow17;
    ChangeButton cbRecordnow18;
    TextView etRecordnow19;
    ChangeButton cbRecordnow110;
    ChangeButton cbRecordnow111;
    ChangeButton cbRecordnow112;
    //甲状腺
    TextView etRecordnow21_1;
    NiceSpinner nsRecordnow21_2;
    TextView etRecordnow22_1;
    NiceSpinner nsRecordnow22_2;
    NiceSpinner nsRecordnow23;
    ChangeButton cbRecordnow24;
    ChangeButton cbRecordnow25;
    ChangeButton cbRecordnow26;
    NiceSpinner nsRecordnow272;
    TextView etRecordnow271;
    ChangeButton cbRecordnow28;
    ChangeButton cbRecordnow29;
    TextView etRecordnow210_1;
    NiceSpinner nsRecordnow210_2;
    ChangeButton cbRecordnow211;
    TextView etRecordnow212_1;
    NiceSpinner nsRecordnow212_2;
    TextView etRecordnow213_1;
    NiceSpinner nsRecordnow213_2;
    ChangeButton cbRecordnow214;
    NiceSpinner nsRecordnow215;
    //pcos
    TextView etRecordnow31_1;
    NiceSpinner nsRecordnow31_2;
    ChangeButton cbRecordnow32;
    ChangeButton cbRecordnow33;
    TextView etRecordnow34_1;
    ChangeButton cbRecordnow35;
    TextView etRecordnow36;
    NiceSpinner nsRecordnow37;
    ChangeButton cbRecordnow38;
    ChangeButton cbRecordnow39;
    ChangeButton cbRecordnow310;
    TextView etRecordnow311;
    ChangeButton cbRecordnow312;
    //其他
    TextView etRecordnow41_1;
    NiceSpinner nsRecordnow41_2;
    TextView etRecordnow42_1;
    NiceSpinner nsRecordnow42_2;
    TextView etRecordnow43_1;
    NiceSpinner nsRecordnow43_2;
    TextView etRecordnow44_1;
    NiceSpinner nsRecordnow44_2;
    TextView etRecordnow45_1;
    NiceSpinner nsRecordnow45_2;
    TextView etRecordnow46_1;
    NiceSpinner nsRecordnow46_2;
    TextView etRecordnow47_1;
    NiceSpinner nsRecordnow47_2;
    TextView etRecordnow48_1;
    NiceSpinner nsRecordnow48_2;
    TextView etRecordnow49_1;
    NiceSpinner nsRecordnow49_2;

    private MHistory_Now mHistory_now;//记录数据，用于更新

    public MHistory_Now getmHistory_now() {
        return mHistory_now;
    }

    public void setmHistory_now(MHistory_Now mHistory_now) {
        this.mHistory_now = mHistory_now;
    }

    public static RecordNowFragment newInstance(String illnessid, int position, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", position);
        args.putInt("SequenceNumber", mSequenceNumber);
        RecordNowFragment fragment = new RecordNowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        switch (illnessid) {
            case "1"://糖尿病
                return R.layout.frag_recordnow1;
            case "2"://dm
                return R.layout.frag_recordnow2;
            case "3"://pcos
                return R.layout.frag_recordnow3;
            case "9000"://other
                return R.layout.frag_recordnow4;
            default:
                return R.layout.frag_recordnow1;
        }
    }

    @Override
    public void certainSubmit() {
        boolean isAdd = false;//标志位，用来区分是新增还是更新
        if (mHistory_now == null) {//添加
            isAdd = true;
            mHistory_now = new MHistory_Now();
            mHistory_now.setId(CommonUtil.generateGUID());
            mHistory_now.setIllnessID(illnessid);
            mHistory_now.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
            mHistory_now.setInputUser(String.valueOf(getmUserInfo().getUserID()));
            mHistory_now.setType("1");
        }
        mHistory_now.setOther(mEtOther.getText().toString());
        mHistory_now.setInputDate(CommonUtil.getSysDate());
        switch (illnessid) {
            case "1"://糖尿病
                mHistory_now.setFields1(etRecordnow11_1.getText().toString() + "-" + nsRecordnow11_2.getSelectedIndex());
                mHistory_now.setFields2(etRecordnow12.getText().toString() + "-" + etRecordnow122.getText().toString() + "-" + nsRecordnow12.getSelectedIndex());
                mHistory_now.setFields3(etRecordnow13.getText().toString() + "-" + etRecordnow132.getText().toString() + "-" + nsRecordnow13.getSelectedIndex());
                mHistory_now.setFields4(cbRecordnow14.getChecked());
                mHistory_now.setFields5(cbRecordnow15.getChecked());
                mHistory_now.setFields6(cbRecordnow16.getChecked());
                mHistory_now.setFields7(cbRecordnow17.getChecked());
                mHistory_now.setFields8(cbRecordnow18.getChecked());
                mHistory_now.setFields9(etRecordnow19.getText().toString());
                mHistory_now.setFields10(cbRecordnow110.getChecked());
                mHistory_now.setFields11(cbRecordnow111.getChecked());
                mHistory_now.setFields12(cbRecordnow112.getChecked());
                break;
            case "2"://dm
                mHistory_now.setFields1(etRecordnow21_1.getText().toString() + "-" + nsRecordnow21_2.getSelectedIndex());
                mHistory_now.setFields2(etRecordnow22_1.getText().toString() + "-" + nsRecordnow22_2.getSelectedIndex());
                mHistory_now.setFields3(nsRecordnow23.getSelectedIndex());
                mHistory_now.setFields4(cbRecordnow24.getChecked());
                mHistory_now.setFields5(cbRecordnow25.getChecked());
                mHistory_now.setFields6(cbRecordnow26.getChecked());
                mHistory_now.setFields7(etRecordnow271.getText().toString() + "-" + nsRecordnow272.getSelectedIndex());
                mHistory_now.setFields8(cbRecordnow28.getChecked());
                mHistory_now.setFields9(cbRecordnow29.getChecked());
                mHistory_now.setFields10(etRecordnow210_1.getText().toString() + "-" + nsRecordnow210_2.getSelectedIndex());
                mHistory_now.setFields11(cbRecordnow211.getChecked());
                mHistory_now.setFields12(etRecordnow212_1.getText().toString() + "-" + nsRecordnow212_2.getSelectedIndex());
                mHistory_now.setFields13(etRecordnow213_1.getText().toString() + "-" + nsRecordnow213_2.getSelectedIndex());
                mHistory_now.setFields14(cbRecordnow214.getChecked());
                mHistory_now.setFields15(nsRecordnow215.getSelectedIndex());
                break;
            case "3"://pcos
                mHistory_now.setFields1(etRecordnow31_1.getText().toString() + "-" + nsRecordnow31_2.getSelectedIndex());
                mHistory_now.setFields2(cbRecordnow32.getChecked());
                mHistory_now.setFields3(cbRecordnow33.getChecked());
                mHistory_now.setFields4(etRecordnow34_1.getText().toString());
                mHistory_now.setFields5(cbRecordnow35.getChecked());
                mHistory_now.setFields6(etRecordnow36.getText().toString());
                mHistory_now.setFields7(nsRecordnow37.getSelectedIndex());
                mHistory_now.setFields8(cbRecordnow38.getChecked());
                mHistory_now.setFields9(cbRecordnow39.getChecked());
                mHistory_now.setFields10(cbRecordnow310.getChecked());
                mHistory_now.setFields11(etRecordnow311.getText().toString());
                mHistory_now.setFields12(cbRecordnow312.getChecked());
                break;
            case "9000"://other
                mHistory_now.setFields1(etRecordnow41_1.getText().toString() + "-" + nsRecordnow41_2.getSelectedIndex());
                mHistory_now.setFields2(etRecordnow42_1.getText().toString() + "-" + nsRecordnow42_2.getSelectedIndex());
                mHistory_now.setFields3(etRecordnow43_1.getText().toString() + "-" + nsRecordnow43_2.getSelectedIndex());
                mHistory_now.setFields4(etRecordnow44_1.getText().toString() + "-" + nsRecordnow44_2.getSelectedIndex());
                mHistory_now.setFields5(etRecordnow45_1.getText().toString() + "-" + nsRecordnow45_2.getSelectedIndex());
                mHistory_now.setFields6(etRecordnow46_1.getText().toString() + "-" + nsRecordnow46_2.getSelectedIndex());
                mHistory_now.setFields7(etRecordnow47_1.getText().toString() + "-" + nsRecordnow47_2.getSelectedIndex());
                mHistory_now.setFields8(etRecordnow48_1.getText().toString() + "-" + nsRecordnow48_2.getSelectedIndex());
                mHistory_now.setFields9(etRecordnow49_1.getText().toString() + "-" + nsRecordnow49_2.getSelectedIndex());
                break;
        }
        if (isAdd) {
            new SingleFetcher(String.class).addMedicalRecord_Now(getActivity(), "正在提交...", mHistory_now);
        } else {//更新数据
            new SingleFetcher(String.class).updateMedicalRecord_Now(getActivity(), "正在更新数据...", mHistory_now);
        }
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

    /**
     * 初始化其他疾病相关view
     *
     * @param view
     */
    private void init9000(View view) {
        etRecordnow41_1 = (TextView) view.findViewById(R.id.et_recordnow4_1_1);
        etRecordnow41_1.setOnClickListener(this);
        nsRecordnow41_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow4_1_2);
        etRecordnow42_1 = (TextView) view.findViewById(R.id.et_recordnow4_2_1);
        etRecordnow42_1.setOnClickListener(this);
        nsRecordnow42_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow4_2_2);
        etRecordnow43_1 = (TextView) view.findViewById(R.id.et_recordnow4_3_1);
        etRecordnow43_1.setOnClickListener(this);
        nsRecordnow43_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow4_3_2);
        etRecordnow44_1 = (TextView) view.findViewById(R.id.et_recordnow4_4_1);
        etRecordnow44_1.setOnClickListener(this);
        nsRecordnow44_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow4_4_2);
        etRecordnow45_1 = (TextView) view.findViewById(R.id.et_recordnow4_5_1);
        etRecordnow45_1.setOnClickListener(this);
        nsRecordnow45_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow4_5_2);
        etRecordnow46_1 = (TextView) view.findViewById(R.id.et_recordnow4_6_1);
        etRecordnow46_1.setOnClickListener(this);
        nsRecordnow46_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow4_6_2);
        etRecordnow47_1 = (TextView) view.findViewById(R.id.et_recordnow4_7_1);
        etRecordnow47_1.setOnClickListener(this);
        nsRecordnow47_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow4_7_2);
        etRecordnow48_1 = (TextView) view.findViewById(R.id.et_recordnow4_8_1);
        etRecordnow48_1.setOnClickListener(this);
        nsRecordnow48_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow4_8_2);
        etRecordnow49_1 = (TextView) view.findViewById(R.id.et_recordnow4_9_1);
        etRecordnow49_1.setOnClickListener(this);
        nsRecordnow49_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow4_9_2);
    }

    /**
     * 初始化pcos相关view
     *
     * @param view
     */
    private void init3(View view) {
        etRecordnow31_1 = (TextView) view.findViewById(R.id.et_recordnow3_1_1);
        etRecordnow31_1.setOnClickListener(this);
        etRecordnow31_1.setInputType(InputType.TYPE_CLASS_NUMBER);
        nsRecordnow31_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow3_1_2);
        cbRecordnow32 = (ChangeButton) view.findViewById(R.id.cb_recordnow3_2);
        cbRecordnow33 = (ChangeButton) view.findViewById(R.id.cb_recordnow3_3);
        etRecordnow34_1 = (TextView) view.findViewById(R.id.et_recordnow3_4_1);
        etRecordnow34_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });

        cbRecordnow35 = (ChangeButton) view.findViewById(R.id.cb_recordnow3_5);
        etRecordnow36 = (TextView) view.findViewById(R.id.et_recordnow3_6);
        etRecordnow36.setOnClickListener(this);
        nsRecordnow37 = (NiceSpinner) view.findViewById(R.id.ns_recordnow3_7);
        cbRecordnow38 = (ChangeButton) view.findViewById(R.id.cb_recordnow3_8);
        cbRecordnow39 = (ChangeButton) view.findViewById(R.id.cb_recordnow3_9);
        cbRecordnow310 = (ChangeButton) view.findViewById(R.id.cb_recordnow3_10);
        etRecordnow311 = (TextView) view.findViewById(R.id.et_recordnow3_11);
        etRecordnow311.setOnClickListener(this);
        cbRecordnow312 = (ChangeButton) view.findViewById(R.id.cb_recordnow3_12);
    }

    /**
     * 初始化甲状腺相关view
     *
     * @param view
     */
    private void init2(View view) {
        etRecordnow21_1 = (TextView) view.findViewById(R.id.et_recordnow2_1_1);
        etRecordnow21_1.setOnClickListener(this);
        nsRecordnow21_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow2_1_2);
        etRecordnow22_1 = (TextView) view.findViewById(R.id.et_recordnow2_2_1);
        etRecordnow22_1.setOnClickListener(this);
        nsRecordnow22_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow2_2_2);
        nsRecordnow23 = (NiceSpinner) view.findViewById(R.id.ns_recordnow2_3);
        cbRecordnow24 = (ChangeButton) view.findViewById(R.id.cb_recordnow2_4);
        cbRecordnow25 = (ChangeButton) view.findViewById(R.id.cb_recordnow2_5);
        cbRecordnow26 = (ChangeButton) view.findViewById(R.id.cb_recordnow2_6);
        etRecordnow271 = (TextView) view.findViewById(R.id.et_recordnow2_7_1);
        etRecordnow271.setOnClickListener(this);
        nsRecordnow272 = (NiceSpinner) view.findViewById(R.id.ns_recordnow2_7_2);
        cbRecordnow28 = (ChangeButton) view.findViewById(R.id.cb_recordnow2_8);
        cbRecordnow29 = (ChangeButton) view.findViewById(R.id.cb_recordnow2_9);
        etRecordnow210_1 = (TextView) view.findViewById(R.id.et_recordnow2_10_1);
        etRecordnow210_1.setOnClickListener(this);
        nsRecordnow210_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow2_10_2);
        cbRecordnow211 = (ChangeButton) view.findViewById(R.id.cb_recordnow2_11);
        etRecordnow212_1 = (TextView) view.findViewById(R.id.et_recordnow2_12_1);
        etRecordnow212_1.setOnClickListener(this);
        nsRecordnow212_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow2_12_2);
        etRecordnow213_1 = (TextView) view.findViewById(R.id.et_recordnow2_13_1);
        etRecordnow213_1.setOnClickListener(this);
        nsRecordnow213_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow2_13_2);
        cbRecordnow214 = (ChangeButton) view.findViewById(R.id.cb_recordnow2_14);
        nsRecordnow215 = (NiceSpinner) view.findViewById(R.id.ns_recordnow2_15);
    }

    /**
     * 初始化糖尿病相关view
     *
     * @param view
     */
    private void init1(View view) {
        etRecordnow11_1 = (TextView) view.findViewById(R.id.et_recordnow1_1_1);
        etRecordnow11_1.setOnClickListener(this);
        nsRecordnow11_2 = (NiceSpinner) view.findViewById(R.id.ns_recordnow1_1_2);
        etRecordnow12 = (TextView) view.findViewById(R.id.et_recordnow1_2);
        etRecordnow122 = (TextView) view.findViewById(R.id.et_recordnow1_2_2);
        nsRecordnow12 = (NiceSpinner) view.findViewById(R.id.ns_recordnow1_2);
        etRecordnow12.setOnClickListener(this);
        etRecordnow122.setOnClickListener(this);
        etRecordnow12.addTextChangedListener(CommonUtil.getTextWatch());
        etRecordnow13 = (TextView) view.findViewById(R.id.et_recordnow1_3);
        etRecordnow132 = (TextView) view.findViewById(R.id.et_recordnow1_3_2);
        nsRecordnow13 = (NiceSpinner) view.findViewById(R.id.ns_recordnow1_3);
        etRecordnow13.setOnClickListener(this);
        etRecordnow132.setOnClickListener(this);
        etRecordnow13.addTextChangedListener(CommonUtil.getTextWatch());
        cbRecordnow14 = (ChangeButton) view.findViewById(R.id.cb_recordnow1_4);
        cbRecordnow15 = (ChangeButton) view.findViewById(R.id.cb_recordnow1_5);
        cbRecordnow16 = (ChangeButton) view.findViewById(R.id.cb_recordnow1_6);
        cbRecordnow17 = (ChangeButton) view.findViewById(R.id.cb_recordnow1_7);
        cbRecordnow18 = (ChangeButton) view.findViewById(R.id.cb_recordnow1_8);
        etRecordnow19 = (TextView) view.findViewById(R.id.et_recordnow1_9);
        etRecordnow19.setOnClickListener(this);
        etRecordnow19.addTextChangedListener(CommonUtil.getTextWatch());
        cbRecordnow110 = (ChangeButton) view.findViewById(R.id.cb_recordnow1_10);
        cbRecordnow111 = (ChangeButton) view.findViewById(R.id.cb_recordnow1_11);
        cbRecordnow112 = (ChangeButton) view.findViewById(R.id.cb_recordnow1_12);
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.getWhat().equals("FindMedicalRecordHistoryNew1") && isVisible) {
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                mHistory_now = (MHistory_Now) event.getObjects().get(0);
                initData(mHistory_now);
            }
        } else if (event.getWhat().equals("AddMedicalRecordHistoryNew") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
                //跳转到既往史
            } else {
                showMsgDialog("提交失败", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            btnCertain.performClick();
                        }
                    }
                });
            }
        } else if (event.getWhat().equals("UpdateMedicalRecordHistoryNew") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "更新成功");
                //跳转到既往史
            } else {
                showMsgDialog("提交失败", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (which == DialogAction.POSITIVE) {
                            btnCertain.performClick();
                        }
                    }
                });
            }
        }
    }

    private void initData(MHistory_Now now) {
        mEtOther.setText(now.getOther());
        switch (illnessid) {
            case "1"://糖尿病
                initData1(now);
                break;
            case "2"://dm
                initData2(now);
                break;
            case "3"://pcos
                initData3(now);
                break;
            case "9000"://other
                initData900(now);
                break;
        }
        mHasLoadedOnce = true;
    }

    private void initData900(MHistory_Now now) {
        etRecordnow41_1.setText(now.getFields1().split("-")[0]);
        nsRecordnow41_2.setSelectedIndex(CommonUtil.parserInt(now.getFields1(), 1));
        etRecordnow42_1.setText(now.getFields2().split("-")[0]);
        nsRecordnow42_2.setSelectedIndex(CommonUtil.parserInt(now.getFields2(), 1));
        etRecordnow43_1.setText(now.getFields3().split("-")[0]);
        nsRecordnow43_2.setSelectedIndex(CommonUtil.parserInt(now.getFields3(), 1));
        etRecordnow44_1.setText(now.getFields4().split("-")[0]);
        nsRecordnow44_2.setSelectedIndex(CommonUtil.parserInt(now.getFields4(), 1));
        etRecordnow45_1.setText(now.getFields5().split("-")[0]);
        nsRecordnow45_2.setSelectedIndex(CommonUtil.parserInt(now.getFields5(), 1));
        etRecordnow46_1.setText(now.getFields6().split("-")[0]);
        nsRecordnow46_2.setSelectedIndex(CommonUtil.parserInt(now.getFields6(), 1));
        etRecordnow47_1.setText(now.getFields7().split("-")[0]);
        nsRecordnow47_2.setSelectedIndex(CommonUtil.parserInt(now.getFields7(), 1));
        etRecordnow48_1.setText(now.getFields8().split("-")[0]);
        nsRecordnow48_2.setSelectedIndex(CommonUtil.parserInt(now.getFields8(), 1));
        etRecordnow49_1.setText(now.getFields9().split("-")[0]);
        nsRecordnow49_2.setSelectedIndex(CommonUtil.parserInt(now.getFields9(), 1));
    }

    private void initData3(MHistory_Now now) {
        etRecordnow31_1.setText(now.getFields1().split("-")[0]);
        nsRecordnow31_2.setSelectedIndex(CommonUtil.parserInt(now.getFields1(), 1));
        cbRecordnow32.setChecked(now.getFields2().equals("true") ? true : false);
        cbRecordnow33.setChecked(now.getFields3().equals("true") ? true : false);
        etRecordnow34_1.setText(now.getFields4());
        cbRecordnow35.setChecked(now.getFields5().equals("true") ? true : false);
        etRecordnow36.setText(now.getFields6());
        nsRecordnow37.setSelectedIndex(CommonUtil.parserInt(now.getFields7()));
        cbRecordnow38.setChecked(now.getFields8().equals("true") ? true : false);
        cbRecordnow39.setChecked(now.getFields9().equals("true") ? true : false);
        cbRecordnow310.setChecked(now.getFields10().equals("true") ? true : false);
        etRecordnow311.setText(now.getFields11());
        cbRecordnow312.setChecked(now.getFields12().equals("true") ? true : false);
    }

    private void initData2(MHistory_Now now) {
        etRecordnow21_1.setText(now.getFields1().split("-")[0]);
        nsRecordnow21_2.setSelectedIndex(CommonUtil.parserInt(now.getFields1(), 1));
        etRecordnow22_1.setText(now.getFields2().split("-")[0]);
        nsRecordnow22_2.setSelectedIndex(CommonUtil.parserInt(now.getFields2(), 1));
        nsRecordnow23.setSelectedIndex(CommonUtil.parserInt(now.getFields3()));
        cbRecordnow24.setChecked(now.getFields4().equals("true") ? true : false);
        cbRecordnow25.setChecked(now.getFields5().equals("true") ? true : false);
        cbRecordnow26.setChecked(now.getFields6().equals("true") ? true : false);
        nsRecordnow272.setSelectedIndex(CommonUtil.parserInt(now.getFields7(), 1));
        etRecordnow271.setText(now.getFields7().split("-")[0]);
        cbRecordnow28.setChecked(now.getFields8().equals("true") ? true : false);
        cbRecordnow29.setChecked(now.getFields9().equals("true") ? true : false);
        etRecordnow210_1.setText(now.getFields10().split("-")[0]);
        nsRecordnow210_2.setSelectedIndex(CommonUtil.parserInt(now.getFields10(), 1));
        cbRecordnow211.setChecked(now.getFields11().equals("true") ? true : false);
        etRecordnow212_1.setText(now.getFields12().split("-")[0]);
        nsRecordnow212_2.setSelectedIndex(CommonUtil.parserInt(now.getFields12(), 1));
        etRecordnow213_1.setText(now.getFields13().split("-")[0]);
        nsRecordnow213_2.setSelectedIndex(CommonUtil.parserInt(now.getFields13(), 1));
        cbRecordnow214.setChecked(now.getFields14().equals("true") ? true : false);
        nsRecordnow215.setSelectedIndex(CommonUtil.parserInt(now.getFields15()));
    }

    private void initData1(MHistory_Now now) {
        etRecordnow11_1.setText(now.getFields1().split("-")[0]);
        nsRecordnow11_2.setSelectedIndex(CommonUtil.parserInt(now.getFields1(), 1));
        etRecordnow12.setText(now.getFields2().split("-")[0]);
        etRecordnow122.setText(now.getFields2().split("-")[1]);
        nsRecordnow12.setSelectedIndex(CommonUtil.parserInt(now.getFields2(), 2));
        etRecordnow13.setText(now.getFields3().split("-")[0]);
        etRecordnow132.setText(now.getFields3().split("-")[1]);
        nsRecordnow13.setSelectedIndex(CommonUtil.parserInt(now.getFields3(), 2));
        cbRecordnow14.setChecked(now.getFields4().equals("true") ? true : false);
        cbRecordnow15.setChecked(now.getFields5().equals("true") ? true : false);
        cbRecordnow16.setChecked(now.getFields6().equals("true") ? true : false);
        cbRecordnow17.setChecked(now.getFields7().equals("true") ? true : false);
        cbRecordnow18.setChecked(now.getFields8().equals("true") ? true : false);
        etRecordnow19.setText(now.getFields9());
        cbRecordnow110.setChecked(now.getFields10().equals("true") ? true : false);
        cbRecordnow111.setChecked(now.getFields11().equals("true") ? true : false);
        cbRecordnow112.setChecked(now.getFields12().equals("true") ? true : false);
    }

    @Override
    public void lazyLoad() {
        if (position == 2) {//编辑
            //获取现病史信息
            Map<String, String> params = new WeakHashMap<>();
            params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
            new MutiFetcher(MHistory_Now[].class).fetch(getActivity(), "FindMedicalRecordHistoryNew1", "正在查询...", params);
        }
    }

    @Override
    public void onClick(View v) {
        CommonUtil.showNumberPickerDialog(getActivity(), v);
    }
}
