package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.shwootide.metabolictreat.DiagnosisActivity;
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
 * 体格检查
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class PhysicalFragment extends BaseFragment implements View.OnClickListener {
    //糖尿病
    TextView etPhysical111;
    TextView etPhysical112;
    TextView etPhysical12;
    NiceSpinner nsPhysical13;
    TextView etPhysical14;
    TextView etPhysical15;
    TextView etPhysical16;
    TextView etPhysical17;
    TextView etPhysical18;
    TextView tvPhysical19;
    NiceSpinner nsPhysical110;
    ChangeButton cbPhysical111;
    NiceSpinner nsPhysical112;
    //dm
    TextView etPhysical211;
    TextView etPhysical212;
    TextView etPhysical22;
    NiceSpinner nsPhysical23;
    NiceSpinner nsPhysical24;
    ChangeButton cbPhysical25;
    ChangeButton cbPhysical26;
    NiceSpinner nsPhysical27;
    ChangeButton cbPhysical28;
    NiceSpinner nsPhysical29;
    //pcos
    TextView etPhysical311;
    TextView etPhysical312;
    TextView etPhysical32;
    NiceSpinner nsPhysical33;
    TextView etPhysical34;
    TextView etPhysical35;
    TextView etPhysical36;
    TextView etPhysical37;
    TextView etPhysical38;
    TextView tvPhysical39;
    ChangeButton cbPhysical310;
    ChangeButton cbPhysical311;
    ChangeButton cbPhysical312;
    //other
    TextView etPhysical411;
    TextView etPhysical412;
    TextView etPhysical42;
    NiceSpinner nsPhysical43;
    TextView etPhysical44;
    TextView etPhysical45;
    TextView etPhysical46;
    TextView etPhysical47;
    TextView etPhysical48;
    TextView tvPhysical49;
    ChangeButton cbPhysical410;
    ChangeButton cbPhysical411;
    ChangeButton cbPhysical412;
    private MedicalRecordCheckup checkup;

    public static PhysicalFragment newInstance(String illnessid, int mPosition, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", mPosition);
        args.putInt("SequenceNumber", mSequenceNumber);
        PhysicalFragment fragment = new PhysicalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
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
        boolean isAdd = false;//标志位，用来区分是新增还是更新
        if (checkup == null) {//  0/2
            isAdd = true;
            checkup = new MedicalRecordCheckup();
            checkup.setId(CommonUtil.generateGUID());
            checkup.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
            checkup.setInputUser(String.valueOf(getmUserInfo().getUserID()));
            checkup.setIllnessID(illnessid);
            checkup.setType("1");
        }
        checkup.setOther(mEtOther.getText().toString());
        checkup.setInputDate(CommonUtil.getSysDate());
        switch (illnessid) {
            case "1":
                checkup.setFields1(etPhysical111.getText().toString() + "-" + etPhysical112.getText().toString() + "-mmHg");
                checkup.setFields2(etPhysical12.getText().toString());
                checkup.setFields3(nsPhysical13.getSelectedIndex());
                checkup.setFields4(etPhysical14.getText().toString());
                checkup.setFields5(etPhysical15.getText().toString());
                checkup.setFields6(etPhysical16.getText().toString());
                checkup.setFields7(etPhysical17.getText().toString());
                checkup.setFields8(etPhysical18.getText().toString());
                checkup.setFields9(tvPhysical19.getText().toString());
                checkup.setFields10(nsPhysical110.getSelectedIndex());
                checkup.setFields11(cbPhysical111.getChecked());
                checkup.setFields12(nsPhysical112.getSelectedIndex());
                break;
            case "2":
                checkup.setFields1(etPhysical211.getText().toString() + "-" + etPhysical212.getText().toString() + "-mmHg");
                checkup.setFields2(etPhysical22.getText().toString());
                checkup.setFields3(nsPhysical23.getSelectedIndex());
                checkup.setFields4(nsPhysical24.getSelectedIndex());
                checkup.setFields5(cbPhysical25.getChecked());
                checkup.setFields6(cbPhysical26.getChecked());
                checkup.setFields7(nsPhysical27.getSelectedIndex());
                checkup.setFields8(cbPhysical28.getChecked());
                checkup.setFields9(nsPhysical29.getSelectedIndex());
                break;
            case "3":
                checkup.setFields1(etPhysical311.getText().toString() + "-" + etPhysical312.getText().toString() + "-mmHg");
                checkup.setFields2(etPhysical32.getText().toString());
                checkup.setFields3(nsPhysical33.getSelectedIndex());
                checkup.setFields4(etPhysical34.getText().toString());
                checkup.setFields5(etPhysical35.getText().toString());
                checkup.setFields6(etPhysical36.getText().toString());
                checkup.setFields7(etPhysical37.getText().toString());
                checkup.setFields8(etPhysical38.getText().toString());
                checkup.setFields9(tvPhysical39.getText().toString());
                checkup.setFields10(cbPhysical310.getChecked());
                checkup.setFields11(cbPhysical311.getChecked());
                checkup.setFields12(cbPhysical312.getChecked());
                break;
            case "9000":
                checkup.setFields1(etPhysical411.getText().toString() + "-" + etPhysical412.getText().toString() + "-mmHg");
                checkup.setFields2(etPhysical42.getText().toString());
                checkup.setFields3(nsPhysical43.getSelectedIndex());
                checkup.setFields4(etPhysical44.getText().toString());
                checkup.setFields5(etPhysical45.getText().toString());
                checkup.setFields6(etPhysical46.getText().toString());
                checkup.setFields7(etPhysical47.getText().toString());
                checkup.setFields8(etPhysical48.getText().toString());
                checkup.setFields9(tvPhysical49.getText().toString());
                checkup.setFields10(cbPhysical410.getChecked());
                checkup.setFields11(cbPhysical411.getChecked());
                checkup.setFields12(cbPhysical412.getChecked());
                break;
        }
        if (isAdd) {
            new SingleFetcher(String.class).addMedicalRecord_checkup(getActivity(), "正在提交...", checkup);
        } else {//更新数据
            new SingleFetcher(String.class).updateMedicalRecord_checkup(getActivity(), "正在更新数据...", checkup);
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

    @Override
    public void lazyLoad() {
        if (position == 2) {
            //获取体格检查信息
            Map<String, String> params = new WeakHashMap<>();
            params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
            new MutiFetcher(MedicalRecordCheckup[].class).fetch(getActivity(), "FindMedicalRecordCheckup1", "正在加载...", params);
        }
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.what.equals("AddMedicalRecordCheckup") && isVisible) {//提交数据
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
                if (position == 0) {//提交成功，只有出诊的时候才会跳转
                    Intent intent = new Intent(getActivity(), DiagnosisActivity.class);
                    intent.putExtra("IllnessID", illnessid);
                    intent.putExtra("Position", position);
                    startActivity(intent);
                }
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
        } else if (event.what.equals("FindMedicalRecordCheckup1") && isVisible) {//获取数据
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                checkup = (MedicalRecordCheckup) event.getObjects().get(0);
                initData(checkup);
            }
        } else if (event.getWhat().equals("UpdateMedicalRecordCheckup") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "更新成功");
                Intent intent = new Intent(getActivity(), DiagnosisActivity.class);
                intent.putExtra("IllnessID", illnessid);
                intent.putExtra("Position", position);
                startActivity(intent);
            } else {
                showMsgDialog("更新失败", new MaterialDialog.SingleButtonCallback() {
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
        etPhysical411.setText(check.getFields1().split("-")[0]);
        etPhysical412.setText(check.getFields1().split("-")[1]);
        etPhysical42.setText(check.getFields2());
        nsPhysical43.setSelectedIndex(CommonUtil.parserInt(check.getFields3()));
        etPhysical44.setText(check.getFields4());
        etPhysical45.setText(check.getFields5());
        etPhysical46.setText(check.getFields6());
        etPhysical47.setText(check.getFields7());
        etPhysical48.setText(check.getFields8());
        tvPhysical49.setText(check.getFields9());
        cbPhysical410.setChecked(check.getFields10().equals("true") ? true : false);
        cbPhysical411.setChecked(check.getFields11().equals("true") ? true : false);
        cbPhysical412.setChecked(check.getFields12().equals("true") ? true : false);
    }

    private void initData3(MedicalRecordCheckup check) {
        etPhysical311.setText(check.getFields1().split("-")[0]);
        etPhysical312.setText(check.getFields1().split("-")[1]);
        etPhysical32.setText(check.getFields2());
        nsPhysical33.setSelectedIndex(CommonUtil.parserInt(check.getFields3()));
        etPhysical34.setText(check.getFields4());
        etPhysical35.setText(check.getFields5());
        etPhysical36.setText(check.getFields6());
        etPhysical37.setText(check.getFields7());
        etPhysical38.setText(check.getFields8());
        tvPhysical39.setText(check.getFields9());
        cbPhysical310.setChecked(check.getFields10().equals("true") ? true : false);
        cbPhysical311.setChecked(check.getFields11().equals("true") ? true : false);
        cbPhysical312.setChecked(check.getFields12().equals("true") ? true : false);
    }

    private void initData2(MedicalRecordCheckup check) {
        etPhysical211.setText(check.getFields1().split("-")[0]);
        etPhysical212.setText(check.getFields1().split("-")[1]);
        etPhysical22.setText(check.getFields2());
        nsPhysical23.setSelectedIndex(CommonUtil.parserInt(check.getFields3()));
        nsPhysical24.setSelectedIndex(CommonUtil.parserInt(check.getFields4()));
        cbPhysical25.setChecked(check.getFields5().equals("true") ? true : false);
        cbPhysical26.setChecked(check.getFields6().equals("true") ? true : false);
        nsPhysical27.setSelectedIndex(CommonUtil.parserInt(check.getFields7()));
        cbPhysical28.setChecked(check.getFields8().equals("true") ? true : false);
        nsPhysical29.setSelectedIndex(CommonUtil.parserInt(check.getFields9()));
    }

    private void initData1(MedicalRecordCheckup check) {
        etPhysical111.setText(check.getFields1().split("-")[0]);
        etPhysical112.setText(check.getFields1().split("-")[1]);
        etPhysical12.setText(check.getFields2());
        nsPhysical13.setSelectedIndex(CommonUtil.parserInt(check.getFields3()));
        etPhysical14.setText(check.getFields4());
        etPhysical15.setText(check.getFields5());
        etPhysical16.setText(check.getFields6());
        etPhysical17.setText(check.getFields7());
        etPhysical18.setText(check.getFields8());
        tvPhysical19.setText(check.getFields9());
        nsPhysical110.setSelectedIndex(CommonUtil.parserInt(check.getFields10()));
        cbPhysical111.setChecked(check.getFields11().equals("true") ? true : false);
        nsPhysical112.setSelectedIndex(CommonUtil.parserInt(check.getFields12()));
    }

    /**
     * 初始化其他疾病相关view
     *
     * @param view
     */
    private void init9000(View view) {
        etPhysical411 = (TextView) view.findViewById(R.id.et_physical4_1_1);
        etPhysical411.setOnClickListener(this);
        etPhysical412 = (TextView) view.findViewById(R.id.et_physical4_1_2);
        etPhysical412.setOnClickListener(this);
        etPhysical42 = (TextView) view.findViewById(R.id.et_physical4_2);
        etPhysical42.setOnClickListener(this);
        nsPhysical43 = (NiceSpinner) view.findViewById(R.id.ns_physical4_3);
        etPhysical44 = (TextView) view.findViewById(R.id.et_physical4_4);
        etPhysical44.setOnClickListener(this);
        etPhysical45 = (TextView) view.findViewById(R.id.et_physical4_5);
        etPhysical45.setOnClickListener(this);
        etPhysical46 = (TextView) view.findViewById(R.id.et_physical4_6);
        etPhysical46.setOnClickListener(this);
        etPhysical47 = (TextView) view.findViewById(R.id.et_physical4_7);
        etPhysical47.setOnClickListener(this);
        etPhysical48 = (TextView) view.findViewById(R.id.et_physical4_8);
        etPhysical48.setOnClickListener(this);
        tvPhysical49 = (TextView) view.findViewById(R.id.tv_physical4_9);
        tvPhysical49.addTextChangedListener(CommonUtil.getTextWatch());
//        etPhysical44.addTextChangedListener(CommonUtil.getTextWatch());
//        etPhysical45.addTextChangedListener(CommonUtil.getTextWatch());
        etPhysical46.addTextChangedListener(CommonUtil.getTextWatch());
//        etPhysical47.addTextChangedListener(CommonUtil.getTextWatch());
//        etPhysical48.addTextChangedListener(CommonUtil.getTextWatch());
        cbPhysical410 = (ChangeButton) view.findViewById(R.id.cb_physical4_10);
        cbPhysical411 = (ChangeButton) view.findViewById(R.id.cb_physical4_11);
        cbPhysical412 = (ChangeButton) view.findViewById(R.id.cb_physical4_12);
        etPhysical44.addTextChangedListener(new AutoText(0));
        etPhysical45.addTextChangedListener(new AutoText(0));
        etPhysical47.addTextChangedListener(new AutoText(1));
        etPhysical48.addTextChangedListener(new AutoText(1));
    }

    /**
     * 初始化pcos相关view
     *
     * @param view
     */
    private void init3(View view) {
        etPhysical311 = (TextView) view.findViewById(R.id.et_physical3_1_1);
        etPhysical311.setOnClickListener(this);
        etPhysical312 = (TextView) view.findViewById(R.id.et_physical3_1_2);
        etPhysical312.setOnClickListener(this);
        etPhysical32 = (TextView) view.findViewById(R.id.et_physical3_2);
        etPhysical32.setOnClickListener(this);
        nsPhysical33 = (NiceSpinner) view.findViewById(R.id.ns_physical3_3);
        etPhysical34 = (TextView) view.findViewById(R.id.et_physical3_4);
        etPhysical34.setOnClickListener(this);
//        etPhysical34.addTextChangedListener(CommonUtil.getTextWatch());
        etPhysical35 = (TextView) view.findViewById(R.id.et_physical3_5);
        etPhysical35.setOnClickListener(this);
//        etPhysical35.addTextChangedListener(CommonUtil.getTextWatch());
        etPhysical36 = (TextView) view.findViewById(R.id.et_physical3_6);
        etPhysical36.setOnClickListener(this);
        etPhysical36.addTextChangedListener(CommonUtil.getTextWatch());
        etPhysical37 = (TextView) view.findViewById(R.id.et_physical3_7);
        etPhysical37.setOnClickListener(this);
//        etPhysical37.addTextChangedListener(CommonUtil.getTextWatch());
        etPhysical38 = (TextView) view.findViewById(R.id.et_physical3_8);
        etPhysical38.setOnClickListener(this);
//        etPhysical38.addTextChangedListener(CommonUtil.getTextWatch());
        tvPhysical39 = (TextView) view.findViewById(R.id.tv_physical3_9);
        tvPhysical39.addTextChangedListener(CommonUtil.getTextWatch());
        cbPhysical310 = (ChangeButton) view.findViewById(R.id.cb_physical3_10);
        cbPhysical311 = (ChangeButton) view.findViewById(R.id.cb_physical3_11);
        cbPhysical312 = (ChangeButton) view.findViewById(R.id.cb_physical3_12);
        etPhysical34.addTextChangedListener(new AutoText(0));
        etPhysical35.addTextChangedListener(new AutoText(0));
        etPhysical37.addTextChangedListener(new AutoText(1));
        etPhysical38.addTextChangedListener(new AutoText(1));
    }

    /**
     * 初始化甲状腺相关view
     *
     * @param view
     */
    private void init2(View view) {
        etPhysical211 = (TextView) view.findViewById(R.id.et_physical2_1_1);
        etPhysical211.setOnClickListener(this);
        etPhysical212 = (TextView) view.findViewById(R.id.et_physical2_1_2);
        etPhysical212.setOnClickListener(this);
        etPhysical22 = (TextView) view.findViewById(R.id.et_physical2_2);
        etPhysical22.setOnClickListener(this);
        nsPhysical23 = (NiceSpinner) view.findViewById(R.id.ns_physical2_3);
        nsPhysical24 = (NiceSpinner) view.findViewById(R.id.ns_physical2_4);
        cbPhysical25 = (ChangeButton) view.findViewById(R.id.cb_physical2_5);
        cbPhysical26 = (ChangeButton) view.findViewById(R.id.cb_physical2_6);
        nsPhysical27 = (NiceSpinner) view.findViewById(R.id.ns_physical2_7);
        cbPhysical28 = (ChangeButton) view.findViewById(R.id.cb_physical2_8);
        nsPhysical29 = (NiceSpinner) view.findViewById(R.id.ns_physical2_9);
    }

    /**
     * 初始化糖尿病相关view
     *
     * @param view
     */
    private void init1(View view) {
        etPhysical111 = (TextView) view.findViewById(R.id.et_physical1_1_1);
        etPhysical111.setOnClickListener(this);
        etPhysical112 = (TextView) view.findViewById(R.id.et_physical1_1_2);
        etPhysical112.setOnClickListener(this);
        etPhysical12 = (TextView) view.findViewById(R.id.et_physical1_2);
        etPhysical12.setOnClickListener(this);
        nsPhysical13 = (NiceSpinner) view.findViewById(R.id.ns_physical1_3);
        etPhysical14 = (TextView) view.findViewById(R.id.et_physical1_4);
        etPhysical14.setOnClickListener(this);
//        etPhysical14.addTextChangedListener(CommonUtil.getTextWatch());
        etPhysical15 = (TextView) view.findViewById(R.id.et_physical1_5);
        etPhysical15.setOnClickListener(this);
//        etPhysical15.addTextChangedListener(CommonUtil.getTextWatch());
        etPhysical16 = (TextView) view.findViewById(R.id.et_physical1_6);
        etPhysical16.addTextChangedListener(CommonUtil.getTextWatch());
        etPhysical17 = (TextView) view.findViewById(R.id.et_physical1_7);
        etPhysical17.setOnClickListener(this);
//        etPhysical17.addTextChangedListener(CommonUtil.getTextWatch());
        etPhysical18 = (TextView) view.findViewById(R.id.et_physical1_8);
        etPhysical18.setOnClickListener(this);
//        etPhysical18.addTextChangedListener(CommonUtil.getTextWatch());
        tvPhysical19 = (TextView) view.findViewById(R.id.tv_physical1_9);
        tvPhysical19.addTextChangedListener(CommonUtil.getTextWatch());
        nsPhysical110 = (NiceSpinner) view.findViewById(R.id.ns_physical1_10);
        cbPhysical111 = (ChangeButton) view.findViewById(R.id.cb_physical1_11);
        nsPhysical112 = (NiceSpinner) view.findViewById(R.id.ns_physical1_12);
        etPhysical14.addTextChangedListener(new AutoText(0));
        etPhysical15.addTextChangedListener(new AutoText(0));
        etPhysical17.addTextChangedListener(new AutoText(1));
        etPhysical18.addTextChangedListener(new AutoText(1));
    }

    @Override
    public void onClick(View v) {
        CommonUtil.showNumberPickerDialog(getActivity(), v);
    }

    /**
     * 文字改变自动填充
     */
    class AutoText implements TextWatcher {
        private int flag; //bmi 0   ,腰臀比 1

        public AutoText(int flag) {
            this.flag = flag;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String str_tz;
            String str_sg;
            String str_yw;
            String str_tw;
            if (this.flag == 1) {
                switch (illnessid) {
                    case "1"://糖尿病
                        str_tw = etPhysical18.getText().toString().trim();
                        str_yw = etPhysical17.getText().toString().trim();
                        if (!TextUtils.isEmpty(str_tw) && !TextUtils.isEmpty(str_yw) && !str_tw.equals("0")) {
                            tvPhysical19.setText(CommonUtil.getYTB(str_tw, str_yw));
                        }
                        break;
                    case "3"://pcos
                        str_tw = etPhysical38.getText().toString().trim();
                        str_yw = etPhysical37.getText().toString().trim();
                        if (!TextUtils.isEmpty(str_tw) && !TextUtils.isEmpty(str_yw) && !str_tw.equals("0")) {
                            tvPhysical39.setText(CommonUtil.getYTB(str_tw, str_yw));
                        }
                        break;
                    case "9000"://other
                        str_tw = etPhysical48.getText().toString().trim();
                        str_yw = etPhysical47.getText().toString().trim();
                        if (!TextUtils.isEmpty(str_tw) && !TextUtils.isEmpty(str_yw) && !str_tw.equals("0")) {
                            tvPhysical49.setText(CommonUtil.getYTB(str_tw, str_yw));
                        }
                        break;
                }
            } else {
                switch (illnessid) {
                    case "1"://糖尿病
                        str_tz = etPhysical15.getText().toString().trim();
                        str_sg = etPhysical14.getText().toString().trim();
                        if (!TextUtils.isEmpty(str_tz) && !TextUtils.isEmpty(str_sg) && !str_sg.equals("0")) {
                            etPhysical16.setText(String.valueOf(CommonUtil.getBMI(str_tz, str_sg)));
                        }
                        break;
                    case "3"://pcos
                        str_tz = etPhysical35.getText().toString().trim();
                        str_sg = etPhysical34.getText().toString().trim();
                        if (!TextUtils.isEmpty(str_tz) && !TextUtils.isEmpty(str_sg) && !str_sg.equals("0")) {
                            etPhysical36.setText(String.valueOf(CommonUtil.getBMI(str_tz, str_sg)));
                        }
                        break;
                    case "9000"://other
                        str_tz = etPhysical45.getText().toString().trim();
                        str_sg = etPhysical44.getText().toString().trim();
                        if (!TextUtils.isEmpty(str_tz) && !TextUtils.isEmpty(str_sg) && !str_sg.equals("0")) {
                            etPhysical46.setText(String.valueOf(CommonUtil.getBMI(str_tz, str_sg)));
                        }
                        break;
                }
            }
        }
    }

}
