package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.adapter.RecordMedicineAdapter;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.IllnessInfo;
import com.shwootide.metabolictreat.entity.IllnessSubmit;
import com.shwootide.metabolictreat.entity.MedicalRecordCheckup;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 现病史->现治疗用药
 * Created by GMY on 2015/9/9 13:18.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordMedicineFragment extends BaseFragment implements View.OnClickListener {
    public static List<String> defaultDost = new ArrayList<>();

    static {
        defaultDost.add("g");
        defaultDost.add("mg");
        defaultDost.add("ug");
        defaultDost.add("片");
        defaultDost.add("粒");
    }

    //糖尿病
    ListView lvRecordmedicine1;
    Spinner nsRecordmedicine11;
    Spinner nsRecordmedicine12;
    Spinner nsRecordmedicine13;
    TextView etRecordmedicine14;
    TextView tvRecordmedicine15;
    Spinner nsRecordmedicine16;
    TextView tvRecordmedicine17;
    TextView etRecordmedicine110;
    TextView etRecordmedicine111;
    TextView tvRecordmedicine112;
    TextView etRecordmedicine115;
    //dm
    ListView lvRecordmedicine2;
    Spinner nsRecordmedicine21;
    Spinner nsRecordmedicine22;
    Spinner nsRecordmedicine23;
    TextView etRecordmedicine24;
    TextView tvRecordmedicine25;
    Spinner nsRecordmedicine26;
    TextView tvRecordmedicine27;
    TextView etRecordmedicine210;
    TextView etRecordmedicine211;
    TextView etRecordmedicine212;
    TextView etRecordmedicine213;
    TextView etRecordmedicine214;
    TextView etRecordmedicine215;
    //pcos+其他
    ListView lvRecordmedicine3;
    Spinner nsRecordmedicine31;
    Spinner nsRecordmedicine32;
    Spinner nsRecordmedicine33;
    TextView etRecordmedicine34;
    TextView tvRecordmedicine35;
    Spinner nsRecordmedicine36;

    private MedicalRecordCheckup checkup;
    private boolean isFirst = true;
    private PopupMenu menu;

    public MedicalRecordCheckup getCheckup() {
        return checkup;
    }

    public void setCheckup(MedicalRecordCheckup checkup) {
        this.checkup = checkup;
    }

    public static RecordMedicineFragment newInstance(String illnessid, int mPosition, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", mPosition);
        args.putInt("SequenceNumber", mSequenceNumber);
        RecordMedicineFragment fragment = new RecordMedicineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        switch (illnessid) {
            case "1"://糖尿病
                return R.layout.frag_recordmedicine1;
            case "2"://dm
                return R.layout.frag_recordmedicine2;
            case "3"://pcos
            case "9000"://other
                return R.layout.frag_recordmedicine3;
            default:
                return R.layout.frag_recordmedicine3;
        }
    }

    @Override
    public void certainSubmit() {
        switch (illnessid) {
            case "1":
                if (!etRecordmedicine14.getText().toString().equals("")) {
                    addNewMedicine();
                }
                if (!tvRecordmedicine17.getText().toString().equals("") || !etRecordmedicine110.getText().toString().equals("")
                        || !etRecordmedicine111.getText().toString().equals("") || !tvRecordmedicine112.getText().toString().equals("")
                        || !etRecordmedicine115.getText().toString().equals("")) {
                    addNewMedicineCheckUp();
                }
                break;
            case "2":
                if (!etRecordmedicine24.getText().toString().equals("")) {
                    addNewMedicine();
                }
                if (!tvRecordmedicine27.getText().toString().equals("") || !etRecordmedicine210.getText().toString().equals("")
                        || !etRecordmedicine211.getText().toString().equals("") || !etRecordmedicine212.getText().toString().equals("")
                        || !etRecordmedicine213.getText().toString().equals("") || !etRecordmedicine214.getText().toString().equals("")
                        || !etRecordmedicine215.getText().toString().equals("")) {
                    addNewMedicineCheckUp();
                }
                break;
            case "3":
            case "9000"://other
                if (!etRecordmedicine34.getText().toString().equals("")) {
                    addNewMedicine();
                }
                break;
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
            case "9000"://other
                init3(view);
                break;
        }
//        menu = new PopupMenu(getActivity(), nsRecordmedicine11);
//        menu.getMenuInflater().inflate(R.menu.pop_diagnosis2, menu.getMenu());
    }

    private void init3(View view) {
        lvRecordmedicine3 = (ListView) view.findViewById(R.id.lv_recordmedicine3);
        nsRecordmedicine31 = (Spinner) view.findViewById(R.id.ns_recordmedicine3_1);
        nsRecordmedicine32 = (Spinner) view.findViewById(R.id.ns_recordmedicine3_2);
        nsRecordmedicine33 = (Spinner) view.findViewById(R.id.ns_recordmedicine3_3);
        etRecordmedicine34 = (TextView) view.findViewById(R.id.et_recordmedicine3_4);
        tvRecordmedicine35 = (TextView) view.findViewById(R.id.tv_recordmedicine3_5);
        nsRecordmedicine36 = (Spinner) view.findViewById(R.id.ns_recordmedicine3_6);
        nsRecordmedicine36.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, getResources().getStringArray(R.array.medic_blfy)));
        etRecordmedicine34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(v, tvRecordmedicine35, nsRecordmedicine36);
            }
        });
    }

    private void init2(View view) {
        lvRecordmedicine2 = (ListView) view.findViewById(R.id.lv_recordmedicine2);
        nsRecordmedicine21 = (Spinner) view.findViewById(R.id.ns_recordmedicine2_1);
        nsRecordmedicine22 = (Spinner) view.findViewById(R.id.ns_recordmedicine2_2);
        nsRecordmedicine23 = (Spinner) view.findViewById(R.id.ns_recordmedicine2_3);
        etRecordmedicine24 = (TextView) view.findViewById(R.id.et_recordmedicine2_4);
        tvRecordmedicine25 = (TextView) view.findViewById(R.id.tv_recordmedicine2_5);
        nsRecordmedicine26 = (Spinner) view.findViewById(R.id.ns_recordmedicine2_6);
        tvRecordmedicine27 = (TextView) view.findViewById(R.id.tv_recordmedicine2_7);
        etRecordmedicine210 = (TextView) view.findViewById(R.id.et_recordmedicine2_10);
        etRecordmedicine211 = (TextView) view.findViewById(R.id.et_recordmedicine2_11);
        etRecordmedicine212 = (TextView) view.findViewById(R.id.et_recordmedicine2_12);
        etRecordmedicine213 = (TextView) view.findViewById(R.id.et_recordmedicine2_13);
        etRecordmedicine214 = (TextView) view.findViewById(R.id.et_recordmedicine2_14);
        etRecordmedicine215 = (TextView) view.findViewById(R.id.et_recordmedicine2_15);
        etRecordmedicine24.setOnClickListener(this);
        etRecordmedicine210.setOnClickListener(this);
        etRecordmedicine211.setOnClickListener(this);
        etRecordmedicine212.setOnClickListener(this);
        etRecordmedicine213.setOnClickListener(this);
        etRecordmedicine214.setOnClickListener(this);
        etRecordmedicine215.setOnClickListener(this);
        tvRecordmedicine27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        nsRecordmedicine26.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, getResources().getStringArray(R.array.medic_blfy)));
        etRecordmedicine24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(v, tvRecordmedicine25, nsRecordmedicine26);
            }
        });
    }

    private void init1(View view) {
        lvRecordmedicine1 = (ListView) view.findViewById(R.id.lv_recordmedicine1);
        nsRecordmedicine11 = (Spinner) view.findViewById(R.id.ns_recordmedicine1_1);
        nsRecordmedicine12 = (Spinner) view.findViewById(R.id.ns_recordmedicine1_2);
        nsRecordmedicine13 = (Spinner) view.findViewById(R.id.ns_recordmedicine1_3);
        etRecordmedicine14 = (TextView) view.findViewById(R.id.et_recordmedicine1_4);
        tvRecordmedicine15 = (TextView) view.findViewById(R.id.tv_recordmedicine1_5);
        nsRecordmedicine16 = (Spinner) view.findViewById(R.id.ns_recordmedicine1_6);
        tvRecordmedicine17 = (TextView) view.findViewById(R.id.tv_recordmedicine1_7);
        etRecordmedicine110 = (TextView) view.findViewById(R.id.et_recordmedicine1_10);
        etRecordmedicine110.setOnClickListener(this);
        etRecordmedicine111 = (TextView) view.findViewById(R.id.et_recordmedicine1_11);
        etRecordmedicine111.setOnClickListener(this);
        tvRecordmedicine112 = (TextView) view.findViewById(R.id.tv_recordmedicine1_12);
        etRecordmedicine115 = (TextView) view.findViewById(R.id.et_recordmedicine1_15);
        etRecordmedicine115.setOnClickListener(this);
        tvRecordmedicine17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        tvRecordmedicine112.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        nsRecordmedicine16.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, getResources().getStringArray(R.array.medic_blfy)));
        etRecordmedicine14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditDialog(v, tvRecordmedicine15, nsRecordmedicine16);
            }
        });
    }

    /**
     * 添加一条用药记录
     */
    private void addNewMedicine() {
        IllnessSubmit illnessSubmit = new IllnessSubmit();
        illnessSubmit.setId(CommonUtil.generateGUID());
        illnessSubmit.setFlg("1");
        illnessSubmit.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
        switch (illnessid) {
            case "1":
                illnessSubmit.setMTypeParentID(((IllnessInfo) nsRecordmedicine11.getSelectedItem()).getId());
                illnessSubmit.setMTypeID(((IllnessInfo) nsRecordmedicine12.getSelectedItem()).getId());
                illnessSubmit.setMTTradeID(((IllnessInfo) nsRecordmedicine13.getSelectedItem()).getId());
                illnessSubmit.setDosage(etRecordmedicine14.getText().toString() + tvRecordmedicine15.getText().toString());
                illnessSubmit.setTimesaDay(nsRecordmedicine16.getSelectedItem().toString());
                break;
            case "2":
                illnessSubmit.setMTypeParentID(((IllnessInfo) nsRecordmedicine21.getSelectedItem()).getId());
                illnessSubmit.setMTypeID(((IllnessInfo) nsRecordmedicine22.getSelectedItem()).getId());
                illnessSubmit.setMTTradeID(((IllnessInfo) nsRecordmedicine23.getSelectedItem()).getId());
                illnessSubmit.setDosage(etRecordmedicine24.getText().toString() + tvRecordmedicine25.getText().toString());
                illnessSubmit.setTimesaDay(nsRecordmedicine26.getSelectedItem().toString());
                break;
            case "3":
            case "9000"://other
                illnessSubmit.setMTypeParentID(((IllnessInfo) nsRecordmedicine31.getSelectedItem()).getId());
                illnessSubmit.setMTypeID(((IllnessInfo) nsRecordmedicine32.getSelectedItem()).getId());
                illnessSubmit.setMTTradeID(((IllnessInfo) nsRecordmedicine33.getSelectedItem()).getId());
                illnessSubmit.setDosage(etRecordmedicine34.getText().toString() + tvRecordmedicine35.getText().toString());
                illnessSubmit.setTimesaDay(nsRecordmedicine36.getSelectedItem().toString());
                break;
        }
        new SingleFetcher(String.class).addMedicalRecord_cure(getActivity(), "正在提交...", illnessSubmit);
    }

    /**
     * 添加一条血糖监测记录
     */
    private void addNewMedicineCheckUp() {
        boolean isAdd = false;//标志位，用来区分是新增还是更新
        if (checkup == null) {
            isAdd = true;
            checkup = new MedicalRecordCheckup();
            checkup.setId(CommonUtil.generateGUID());
            checkup.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
            checkup.setInputUser(String.valueOf(getmUserInfo().getUserID()));
            checkup.setIllnessID(illnessid);
        }
        checkup.setOther(mEtOther.getText().toString());
        checkup.setInputDate(CommonUtil.getSysDate());
        switch (illnessid) {
            case "1":
                if (!TextUtils.isEmpty(tvRecordmedicine17.getText().toString())) {
                    checkup.setFieldsDate1(tvRecordmedicine17.getText().toString());
                }
                checkup.setFields1(etRecordmedicine110.getText().toString());
                checkup.setFields2(etRecordmedicine111.getText().toString());
                if (!TextUtils.isEmpty(tvRecordmedicine112.getText().toString())) {
                    checkup.setFieldsDate2(tvRecordmedicine112.getText().toString());
                }
                checkup.setFields3(etRecordmedicine115.getText().toString());
                checkup.setType("4");
                break;
            case "2":
                if (!TextUtils.isEmpty(tvRecordmedicine27.getText().toString())) {
                    checkup.setFieldsDate1(tvRecordmedicine27.getText().toString());
                }
                checkup.setFields1(etRecordmedicine210.getText().toString());
                checkup.setFields2(etRecordmedicine211.getText().toString());
                checkup.setFields3(etRecordmedicine212.getText().toString());
                checkup.setFields4(etRecordmedicine213.getText().toString());
                checkup.setFields5(etRecordmedicine214.getText().toString());
                checkup.setFields6(etRecordmedicine215.getText().toString());
                checkup.setType("5");
                break;
        }
        if (isAdd) {
            new SingleFetcher(String.class).addMedicalRecord_checkup(getActivity(), "正在提交...", checkup);
        } else {
            new SingleFetcher(String.class).updateMedicalRecord_checkup(getActivity(), "正在更新...", checkup);
        }
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.what.equals("GetMedicineMaxTypeNew") && isVisible) {//获取疾病大类
            if (event.getCode().equals("200")) {
                switch (illnessid) {
                    case "1"://糖尿病
                        nsRecordmedicine11.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, addTitle(event.getObjects())));
                        nsRecordmedicine11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    //获取所选疾病大类对应疾病小类
                                    Map<String, String> params = new WeakHashMap<>();
//                                    params.put("IllnessID", illnessid);
                                    params.put("pid1", ((IllnessInfo) nsRecordmedicine11.getAdapter().getItem(position)).getId());
                                    new MutiFetcher(IllnessInfo[].class).fetch(getActivity(), "GetMedicineMinTypeNew", "正在加载...", params);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case "2"://dm
                        nsRecordmedicine21.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, addTitle(event.getObjects())));
                        nsRecordmedicine21.performClick();
                        nsRecordmedicine21.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    //获取所选疾病小类对应疾病
                                    Map<String, String> params = new WeakHashMap<>();
//                                    params.put("IllnessID", illnessid);
                                    params.put("pid1", ((IllnessInfo) nsRecordmedicine21.getAdapter().getItem(position)).getId());
                                    new MutiFetcher(IllnessInfo[].class).fetch(getActivity(), "GetMedicineMinTypeNew", "正在加载...", params);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case "3"://pcos
                    case "9000"://other
                        nsRecordmedicine31.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, addTitle(event.getObjects())));
                        nsRecordmedicine31.performClick();
                        nsRecordmedicine31.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    //获取所选疾病大类对应疾病小类
                                    Map<String, String> params = new WeakHashMap<>();
//                                    params.put("IllnessID", illnessid);
                                    params.put("pid1", ((IllnessInfo) nsRecordmedicine31.getAdapter().getItem(position)).getId());
                                    new MutiFetcher(IllnessInfo[].class).fetch(getActivity(), "GetMedicineMinTypeNew", "正在加载...", params);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                }
            }
        } else if (event.what.equals("GetMedicineMinTypeNew") && isVisible) {
            if (event.getCode().equals("200")) {
                switch (illnessid) {
                    case "1"://糖尿病
                        nsRecordmedicine12.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, addTitle(event.getObjects())));
                        nsRecordmedicine12.performClick();
                        nsRecordmedicine12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    //获取所选小类之后的药物名称
                                    Map<String, String> params = new WeakHashMap<>();
//                                    params.put("IllnessID", illnessid);
                                    params.put("pid2", ((IllnessInfo) nsRecordmedicine12.getAdapter().getItem(position)).getId());
                                    new MutiFetcher(IllnessInfo[].class).fetch(getActivity(), "GetMedicineTypeTradeNew", "正在加载...", params);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case "2"://dm
                        nsRecordmedicine22.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, addTitle(event.getObjects())));
                        nsRecordmedicine22.performClick();
                        nsRecordmedicine22.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    //获取所选小类之后的药物名称
                                    Map<String, String> params = new WeakHashMap<>();
//                                    params.put("IllnessID", illnessid);
                                    params.put("pid2", ((IllnessInfo) nsRecordmedicine22.getAdapter().getItem(position)).getId());
                                    new MutiFetcher(IllnessInfo[].class).fetch(getActivity(), "GetMedicineTypeTradeNew", "正在加载...", params);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case "3"://pcos
                    case "9000"://other
                        nsRecordmedicine32.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, addTitle(event.getObjects())));
                        nsRecordmedicine32.performClick();
                        nsRecordmedicine32.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    //获取所选小类之后的药物名称
                                    Map<String, String> params = new WeakHashMap<>();
//                                    params.put("IllnessID", illnessid);
                                    params.put("pid2", ((IllnessInfo) nsRecordmedicine32.getAdapter().getItem(position)).getId());
                                    new MutiFetcher(IllnessInfo[].class).fetch(getActivity(), "GetMedicineTypeTradeNew", "正在加载...", params);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                }
            }
        } else if (event.what.equals("GetMedicineTypeTradeNew") && isVisible) {
            if (event.getCode().equals("200")) {
                switch (illnessid) {
                    case "1"://糖尿病
                        nsRecordmedicine13.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, addTitle(event.getObjects())));
                        nsRecordmedicine13.performClick();
                        nsRecordmedicine13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    tvRecordmedicine15.setText(((IllnessInfo) nsRecordmedicine13.getAdapter().getItem(position)).getUnit());
                                    etRecordmedicine14.requestFocus();
                                    showEditDialog(etRecordmedicine14, tvRecordmedicine15, nsRecordmedicine16);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        break;
                    case "2"://dm
                        nsRecordmedicine23.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, addTitle(event.getObjects())));
                        nsRecordmedicine23.performClick();
                        nsRecordmedicine23.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    tvRecordmedicine25.setText(((IllnessInfo) nsRecordmedicine23.getAdapter().getItem(position)).getUnit());
                                    etRecordmedicine24.requestFocus();
                                    showEditDialog(etRecordmedicine24, tvRecordmedicine25, nsRecordmedicine26);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        break;
                    case "3"://pcos
                    case "9000"://other
                        nsRecordmedicine33.setAdapter(new ArrayAdapter<>(getActivity(), R.layout.spinner_list_item, addTitle(event.getObjects())));
                        nsRecordmedicine33.performClick();
                        nsRecordmedicine33.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (position != 0) {
                                    tvRecordmedicine35.setText(((IllnessInfo) nsRecordmedicine33.getAdapter().getItem(position)).getUnit());
                                    etRecordmedicine34.requestFocus();
                                    showEditDialog(etRecordmedicine34, tvRecordmedicine35, nsRecordmedicine36);
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        break;
                }
                mHasLoadedOnce = true;
            }
        } else if (event.what.equals("AddMedicalRecordCure") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
                //刷新显示数据
                getCure();
                //成功时候清空已填数据
                switch (illnessid) {
                    case "1":
                        etRecordmedicine14.setText("");
                        break;
                    case "2":
                        etRecordmedicine24.setText("");
                        break;
                    case "3":
                    case "9000"://other
                        etRecordmedicine34.setText("");
                        break;
                }
            } else {
                CommonUtil.showToast(getActivity(), "用药添加失败");
            }
        } else if (event.what.equals("GetMedicalRecordCure3") && isVisible) {//获取已有的用药信息
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                RecordMedicineAdapter adapter = new RecordMedicineAdapter(getActivity(), event.getObjects());
                switch (illnessid) {
                    case "1"://糖尿病
                        lvRecordmedicine1.setAdapter(adapter);
                        break;
                    case "2"://dm
                        lvRecordmedicine2.setAdapter(adapter);
                        break;
                    case "3"://pcos
                    case "9000"://other
                        lvRecordmedicine3.setAdapter(adapter);
                        break;
                }
            }
        } else if (event.what.equals("AddMedicalRecordCheckup") && isVisible) {//提交血糖+甲功数据
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
            } else if (event.getCode().equals("500")) {
                CommonUtil.showToast(getActivity(), "请正确填写日期格式");
            } else {
                CommonUtil.showToast(getActivity(), "血糖监测提交失败");
            }
        } else if (event.what.equals("FindMedicalRecordCheckup4") && isVisible) {//获取血糖监测数据
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                checkup = (MedicalRecordCheckup) event.getObjects().get(0);
                initData(checkup);
            }
        } else if (event.what.equals("FindMedicalRecordCheckup5") && isVisible) {//获取甲功数据
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                checkup = (MedicalRecordCheckup) event.getObjects().get(0);
                initData(checkup);
            }
        } else if (event.getWhat().equals("UpdateMedicalRecordCheckup") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "更新成功");
            } else {
                CommonUtil.showToast(getActivity(), "更新失败");
            }
        }
    }

    /**
     * 给spinner添加默认的数据
     *
     * @param event
     * @return
     */
    private ArrayList<IllnessInfo> addTitle(List event) {
        IllnessInfo illnessInfo = new IllnessInfo();
        illnessInfo.setName("点击选择");
        ArrayList<IllnessInfo> objects = new ArrayList(event);
        objects.add(0, illnessInfo);
        return objects;
    }

    /**
     * 填充血糖监测+甲功数据
     *
     * @param checkup
     */
    private void initData(MedicalRecordCheckup checkup) {
        mEtOther.setText(checkup.getOther());
        switch (illnessid) {
            case "1":
                tvRecordmedicine17.setText(CommonUtil.parseForminnerStr(checkup.getFieldsDate1()));
                etRecordmedicine110.setText(checkup.getFields1());
                etRecordmedicine111.setText(checkup.getFields2());
                tvRecordmedicine112.setText(CommonUtil.parseForminnerStr(checkup.getFieldsDate2()));
                etRecordmedicine115.setText(checkup.getFields3());
                break;
            case "2":
                tvRecordmedicine27.setText(CommonUtil.parseForminnerStr(checkup.getFieldsDate1()));
                etRecordmedicine210.setText(checkup.getFields1());
                etRecordmedicine211.setText(checkup.getFields2());
                etRecordmedicine212.setText(checkup.getFields3());
                etRecordmedicine213.setText(checkup.getFields5());
                etRecordmedicine214.setText(checkup.getFields6());
                etRecordmedicine215.setText(checkup.getFields7());
                break;
        }
    }

    @Override
    public void lazyLoad() {
        //获取所有疾病大类
        Map<String, String> params = new WeakHashMap<>();
//        params.put("IllnessID", illnessid);
        new MutiFetcher(IllnessInfo[].class).fetch(getActivity(), "GetMedicineMaxTypeNew", null, params);

        if (position == 2) {//编辑
            //获取血糖监测+甲功
            getCure();
            getMore();
        }
    }

    /**
     * 获取已有用药
     */
    private void getCure() {
        Map<String, String> params2 = new WeakHashMap<>();
        params2.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
        params2.put("flg", "1");
        new MutiFetcher(IllnessSubmit[].class).fetch(getActivity(), "GetMedicalRecordCure3", null, params2);
    }

    /**
     * 获取血糖监测+甲功数据
     */
    private void getMore() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
        switch (illnessid) {
            case "1":
                new MutiFetcher(MedicalRecordCheckup[].class).fetch(getActivity(), "FindMedicalRecordCheckup4", null, params);
                break;
            case "2":
                new MutiFetcher(MedicalRecordCheckup[].class).fetch(getActivity(), "FindMedicalRecordCheckup5", null, params);
                break;
        }

    }

    /**
     * 显示剂量编辑框的dialog
     *
     * @param view
     */
    public void showEditDialog(final View view, final View viewdost, final Spinner spinner) {
        if (!(view instanceof TextView) || !(viewdost instanceof TextView)) {
            return;
        }
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.dialog_number_four, null);
        final NumberPicker np1 = (NumberPicker) rootView.findViewById(R.id.np_dialog_one1);
        final NumberPicker np2 = (NumberPicker) rootView.findViewById(R.id.np_dialog_one2);
        final NumberPicker np3 = (NumberPicker) rootView.findViewById(R.id.np_dialog_one3);
        final TextView tvDian = (TextView) rootView.findViewById(R.id.tv_dialog_complex_dian);
        final NumberPicker little = (NumberPicker) rootView.findViewById(R.id.np_dialog_complex_little);
        final NumberPicker np4 = (NumberPicker) rootView.findViewById(R.id.np_dialog_one4);
        final EditText et = (EditText) rootView.findViewById(R.id.et_dialog_four);

        IllnessInfo illnessInfo = null;
        switch (illnessid) {
            case "1":
                illnessInfo = (IllnessInfo) nsRecordmedicine13.getSelectedItem();
                break;
            case "2":
                illnessInfo = (IllnessInfo) nsRecordmedicine23.getSelectedItem();
                break;
            case "3":
            case "9000"://other
                illnessInfo = (IllnessInfo) nsRecordmedicine33.getSelectedItem();
                break;
        }
        //诺和力剂量需要小数点
        if (illnessInfo.getId().equals("1022")) {
            tvDian.setVisibility(View.VISIBLE);
            little.setVisibility(View.VISIBLE);
        }
        np1.setMaxValue(5);
        np1.setMinValue(0);
        np2.setMaxValue(9);
        np2.setMinValue(0);
        np3.setMaxValue(9);
        np3.setMinValue(0);
        little.setMinValue(0);
        little.setMaxValue(9);

        if (CommonUtil.isStr2Num(((TextView) view).getText().toString())) {
            final double defaultValues = TextUtils.isEmpty(((TextView) view).getText()) ? 000.00 : Double.parseDouble(((TextView) view).getText().toString());
            np1.setValue((int) (defaultValues / 100));
            np2.setValue((int) (defaultValues * 100 % 10000 / 1000));
            np3.setValue((int) (defaultValues * 100 % 1000 / 100));
            little.setValue((int) (defaultValues * 100 % 100 / 10));
        } else {
            et.setText(((TextView) view).getText());
        }
        //如果固定单位数组中没有新的，则添加
        if (!defaultDost.contains(((TextView) viewdost).getText())) {
            defaultDost.add(((TextView) viewdost).getText().toString());
        }
        //默认的索引
        int index = defaultDost.indexOf(((TextView) viewdost).getText().toString());
        np4.setDisplayedValues(defaultDost.toArray(new String[defaultDost.size()]));
        np4.setMinValue(0);
        np4.setMaxValue(defaultDost.size() - 1);
        np4.setValue(index);

        MaterialDialog dialog = new MaterialDialog.Builder(getActivity())
                .customView(rootView, false)
                .autoDismiss(false)
                .cancelable(false)
                .positiveText("确定")
                .negativeText("取消")
                .neutralText("重置")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        String aValue, aValue1_dian = "";
                        if (view instanceof TextView) {
                            //格式化小数点之前的数
                            if (np1.getValue() == 0) {
                                if (np2.getValue() == 0) {
                                    aValue = String.valueOf(np3.getValue());
                                } else {
                                    aValue = String.valueOf(np2.getValue()) + String.valueOf(np3.getValue());
                                }
                            } else {
                                aValue = String.valueOf(np1.getValue()) + String.valueOf(np2.getValue()) + String.valueOf(np3.getValue());
                            }
                            //格式化小数点之后的数
                            if (little.getValue() != 0) {
                                aValue1_dian = "." + little.getValue();
                            }
                            ((TextView) view).setText(aValue + aValue1_dian);
                            ((TextView) viewdost).setText(defaultDost.get(np4.getValue()));
                            CommonUtil.hideSoftInputView(getActivity(), np1);
                            CommonUtil.hideSoftInputView(getActivity(), np2);
                            CommonUtil.hideSoftInputView(getActivity(), np3);
                            CommonUtil.hideSoftInputView(getActivity(), np4);
                            CommonUtil.hideSoftInputView(getActivity(), et);
                            dialog.dismiss();
                            if (!TextUtils.isEmpty(et.getText())) {
                                ((TextView) view).setText(et.getText());
                            } else {
                                spinner.performClick();
                            }
                        }
                    }
                })
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        np1.setValue(0);
                        np2.setValue(0);
                        np3.setValue(0);
                        np4.setValue(0);
                        et.setText("");
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        CommonUtil.hideSoftInputView(getActivity(), np1);
                        CommonUtil.hideSoftInputView(getActivity(), np2);
                        CommonUtil.hideSoftInputView(getActivity(), np3);
                        CommonUtil.hideSoftInputView(getActivity(), np4);
                        CommonUtil.hideSoftInputView(getActivity(), et);
                        dialog.dismiss();
                    }
                })
                .build();
        dialog.show();
        //关闭自动弹出输入法
        CommonUtil.resetDialogParam(getActivity(),dialog);
    }

    @Override
    public void onClick(View v) {
        CommonUtil.showNumberPickerDialog(getActivity(), v);
    }
}
