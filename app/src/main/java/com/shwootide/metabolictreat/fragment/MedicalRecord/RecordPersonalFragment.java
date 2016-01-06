package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.MHistory_Now;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * 个人史
 * Created by GMY on 2015/9/8 11:09.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordPersonalFragment extends BaseFragment implements View.OnClickListener {
    //糖尿病+pcos+其他
    NiceSpinner nsRecordpersonal11;
    EditText etRecordpersonal12;
    TextView etRecordpersonal13;
    NiceSpinner nsRecordpersonal14;
    TextView tvRecordpersonal15;
    TextView etRecordpersonal15;
    LinearLayout llRecordpersonal15;
    TextView tvRecordpersonal16;
    TextView etRecordpersonal16;
    LinearLayout llRecordpersonal16;
    NiceSpinner nsRecordpersonal17;
    TextView tvRecordpersonal18;
    TextView etRecordpersonal18;
    LinearLayout llRecordpersonal18;
    TextView tvRecordpersonal19;
    TextView etRecordpersonal19;
    LinearLayout llRecordpersonal19;
    NiceSpinner nsRecordpersonal110;
    TextView tvRecordpersonal111;
    TextView etRecordpersonal111;
    LinearLayout llRecordpersonal111;
    TextView tvRecordpersonal112;
    NiceSpinner nsRecordpersonal112;
    LinearLayout llRecordpersonal112;
    TextView etRecordpersonal114;
    NiceSpinner nsRecordpersonal115;
    //甲状腺
    TextView etRecordpersonal22;
    TextView etRecordpersonal23;
    NiceSpinner nsRecordpersonal24;
    TextView tvRecordpersonal25;
    TextView etRecordpersonal25;
    LinearLayout llRecordpersonal25;
    TextView tvRecordpersonal26;
    TextView etRecordpersonal26;
    LinearLayout llRecordpersonal26;
    NiceSpinner nsRecordpersonal27;
    TextView tvRecordpersonal28;
    TextView etRecordpersonal28;
    LinearLayout llRecordpersonal28;
    TextView tvRecordpersonal29;
    TextView etRecordpersonal29;
    LinearLayout llRecordpersonal29;
    TextView etRecordpersonal211;
    NiceSpinner nsRecordpersonal212;

    private MHistory_Now mHistory_now;

    public static RecordPersonalFragment newInstance(String illnessid, int mPosition, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", mPosition);
        args.putInt("SequenceNumber", mSequenceNumber);
        RecordPersonalFragment fragment = new RecordPersonalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        switch (illnessid) {
            case "2"://dm
                return R.layout.frag_recordpersonal2;
            case "1"://糖尿病
            case "3"://pcos
            case "9000"://other
                return R.layout.frag_recordpersonal1;
            default:
                return R.layout.frag_recordpersonal2;
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
            mHistory_now.setType("4");
        }
        mHistory_now.setOther(mEtOther.getText().toString());
        mHistory_now.setInputDate(CommonUtil.getSysDate());
        switch (illnessid) {
            case "2"://dm
//                mHistory_now.setFields1("");
                mHistory_now.setFields2(etRecordpersonal22.getText().toString());
                mHistory_now.setFields3(etRecordpersonal23.getText().toString());
                mHistory_now.setFields4(nsRecordpersonal24.getSelectedIndex());
                mHistory_now.setFields5(etRecordpersonal25.getText().toString());
                mHistory_now.setFields6(etRecordpersonal26.getText().toString());
                mHistory_now.setFields7(nsRecordpersonal27.getSelectedIndex());
                mHistory_now.setFields8(etRecordpersonal28.getText().toString());
                mHistory_now.setFields9(etRecordpersonal29.getText().toString());
//                mHistory_now.setFields10("");
                mHistory_now.setFields11(etRecordpersonal211.getText().toString());
                mHistory_now.setFields12(nsRecordpersonal212.getSelectedIndex());
                break;
            case "1"://糖尿病
            case "3"://pcos
            case "9000"://other
                mHistory_now.setFields1(nsRecordpersonal11.getSelectedIndex());
//                mHistory_now.setFields2(etRecordpersonal12.getText().toString());
                mHistory_now.setFields3(etRecordpersonal13.getText().toString());
                mHistory_now.setFields4(nsRecordpersonal14.getSelectedIndex());
                mHistory_now.setFields5(etRecordpersonal15.getText().toString());
                mHistory_now.setFields6(etRecordpersonal16.getText().toString());

                mHistory_now.setFields7(nsRecordpersonal17.getSelectedIndex());
                mHistory_now.setFields8(etRecordpersonal18.getText().toString());
                mHistory_now.setFields9(etRecordpersonal19.getText().toString());

                mHistory_now.setFields10(nsRecordpersonal110.getSelectedIndex());
                mHistory_now.setFields11(etRecordpersonal111.getText().toString());
                mHistory_now.setFields12(nsRecordpersonal112.getSelectedIndex());

//                mHistory_now.setFields13("");
                mHistory_now.setFields14(etRecordpersonal114.getText().toString());
                mHistory_now.setFields15(nsRecordpersonal115.getSelectedIndex());
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
            case "2"://dm
                init2(view);
                break;
            case "1"://糖尿病
            case "3"://pcos
            case "9000"://other
                init1(view);
                break;
        }
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.what.equals("FindMedicalRecordHistoryNew4") && isVisible) {
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                mHistory_now = (MHistory_Now) event.getObjects().get(0);
                initData(mHistory_now);
            }
        } else if (event.what.equals("AddMedicalRecordHistoryNew") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
//           跳转到家族史
            } else {
                CommonUtil.showToast(getActivity(), "提交失败");
            }
        } else if (event.getWhat().equals("UpdateMedicalRecordHistoryNew") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "更新成功");
                //跳转到既往史
            } else {
                CommonUtil.showToast(getActivity(), "更新失败");
            }
        }
    }

    private void initData(MHistory_Now now) {
        mEtOther.setText(now.getOther());
        switch (illnessid) {
            case "2"://dm
                initData2(now);
                break;
            case "1"://糖尿病
            case "3"://pcos
            case "9000"://other
                initData1(now);
                break;
        }
        mHasLoadedOnce = true;
    }

    private void initData2(MHistory_Now now) {
        etRecordpersonal22.setText(now.getFields2());
        etRecordpersonal23.setText(now.getFields3());
        nsRecordpersonal24.setSelectedIndex(CommonUtil.parserInt(now.getFields4()));
        tvRecordpersonal25.setVisibility(View.VISIBLE);
        tvRecordpersonal26.setVisibility(View.VISIBLE);
        llRecordpersonal25.setVisibility(View.VISIBLE);
        llRecordpersonal26.setVisibility(View.VISIBLE);
        etRecordpersonal25.setText(now.getFields5());
        etRecordpersonal26.setText(now.getFields6());
//        if (nsRecordpersonal24.getSelectedIndex() != 0) {
//
//        }
        nsRecordpersonal27.setSelectedIndex(CommonUtil.parserInt(now.getFields7()));
        tvRecordpersonal28.setVisibility(View.VISIBLE);
        tvRecordpersonal29.setVisibility(View.VISIBLE);
        llRecordpersonal28.setVisibility(View.VISIBLE);
        llRecordpersonal29.setVisibility(View.VISIBLE);
        etRecordpersonal28.setText(now.getFields8());
        etRecordpersonal29.setText(now.getFields9());
//        if (nsRecordpersonal27.getSelectedIndex() != 0) {
//
//        }
        etRecordpersonal211.setText(now.getFields11());
        nsRecordpersonal212.setSelectedIndex(CommonUtil.parserInt(now.getFields12()));
    }

    private void initData1(MHistory_Now now) {
        nsRecordpersonal11.setSelectedIndex(CommonUtil.parserInt(now.getFields1()));
//         etRecordpersonal12.setText(now.getFields6());
        etRecordpersonal13.setText(now.getFields3());

        nsRecordpersonal14.setSelectedIndex(CommonUtil.parserInt(now.getFields4()));
        if (!nsRecordpersonal14.getSelectedIndex().equals("0")) {
            tvRecordpersonal15.setVisibility(View.VISIBLE);
            tvRecordpersonal16.setVisibility(View.VISIBLE);
            llRecordpersonal15.setVisibility(View.VISIBLE);
            llRecordpersonal16.setVisibility(View.VISIBLE);
            etRecordpersonal15.setText(now.getFields5());
            etRecordpersonal16.setText(now.getFields6());
        }
        nsRecordpersonal17.setSelectedIndex(CommonUtil.parserInt(now.getFields7()));
        if (!nsRecordpersonal17.getSelectedIndex().equals("0")) {
            tvRecordpersonal18.setVisibility(View.VISIBLE);
            tvRecordpersonal19.setVisibility(View.VISIBLE);
            llRecordpersonal18.setVisibility(View.VISIBLE);
            llRecordpersonal19.setVisibility(View.VISIBLE);
            etRecordpersonal18.setText(now.getFields8());
            etRecordpersonal19.setText(now.getFields9());
        }
        nsRecordpersonal110.setSelectedIndex(CommonUtil.parserInt(now.getFields10()));
        if (!nsRecordpersonal110.getSelectedIndex().equals("0")) {
            tvRecordpersonal111.setVisibility(View.VISIBLE);
            tvRecordpersonal112.setVisibility(View.VISIBLE);
            llRecordpersonal111.setVisibility(View.VISIBLE);
            llRecordpersonal112.setVisibility(View.VISIBLE);
            etRecordpersonal111.setText(now.getFields11());
            nsRecordpersonal112.setSelectedIndex(CommonUtil.parserInt(now.getFields12()));
        }

        etRecordpersonal114.setText(now.getFields14());
        nsRecordpersonal115.setSelectedIndex(CommonUtil.parserInt(now.getFields15()));
    }

    @Override
    public void lazyLoad() {
        if (position == 2) {//编辑
            //获取个人史信息
            Map<String, String> params = new WeakHashMap<>();
            params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
            new MutiFetcher(MHistory_Now[].class).fetch(getActivity(), "FindMedicalRecordHistoryNew4", "正在查询...", params);
        }
    }

    /**
     * 初始化dm
     *
     * @param view
     */
    private void init2(View view) {
        etRecordpersonal22 = (TextView) view.findViewById(R.id.et_recordpersonal2_2);
        etRecordpersonal22.setOnClickListener(this);
        etRecordpersonal23 = (TextView) view.findViewById(R.id.et_recordpersonal2_3);
        etRecordpersonal23.setOnClickListener(this);
        nsRecordpersonal24 = (NiceSpinner) view.findViewById(R.id.ns_recordpersonal2_4);
        tvRecordpersonal25 = (TextView) view.findViewById(R.id.tv_recordpersonal2_5);
        etRecordpersonal25 = (TextView) view.findViewById(R.id.et_recordpersonal2_5);
        etRecordpersonal25.setOnClickListener(this);
        llRecordpersonal25 = (LinearLayout) view.findViewById(R.id.ll_recordpersonal2_5);
        tvRecordpersonal26 = (TextView) view.findViewById(R.id.tv_recordpersonal2_6);
        etRecordpersonal26 = (TextView) view.findViewById(R.id.et_recordpersonal2_6);
        etRecordpersonal26.setOnClickListener(this);
        llRecordpersonal26 = (LinearLayout) view.findViewById(R.id.ll_recordpersonal2_6);
        nsRecordpersonal27 = (NiceSpinner) view.findViewById(R.id.ns_recordpersonal2_7);
        tvRecordpersonal28 = (TextView) view.findViewById(R.id.tv_recordpersonal2_8);
        etRecordpersonal28 = (TextView) view.findViewById(R.id.et_recordpersonal2_8);
        etRecordpersonal28.setOnClickListener(this);
        llRecordpersonal28 = (LinearLayout) view.findViewById(R.id.ll_recordpersonal2_8);
        tvRecordpersonal29 = (TextView) view.findViewById(R.id.tv_recordpersonal2_9);
        etRecordpersonal29 = (TextView) view.findViewById(R.id.et_recordpersonal2_9);
        etRecordpersonal29.setOnClickListener(this);
        llRecordpersonal29 = (LinearLayout) view.findViewById(R.id.ll_recordpersonal2_9);
        etRecordpersonal211 = (TextView) view.findViewById(R.id.et_recordpersonal2_11);
        etRecordpersonal211.setOnClickListener(this);
        nsRecordpersonal212 = (NiceSpinner) view.findViewById(R.id.ns_recordpersonal2_12);
        nsRecordpersonal24.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    tvRecordpersonal25.setVisibility(View.INVISIBLE);
                    tvRecordpersonal26.setVisibility(View.INVISIBLE);
                    llRecordpersonal25.setVisibility(View.INVISIBLE);
                    llRecordpersonal26.setVisibility(View.INVISIBLE);
                } else {
                    tvRecordpersonal25.setVisibility(View.VISIBLE);
                    tvRecordpersonal26.setVisibility(View.VISIBLE);
                    llRecordpersonal25.setVisibility(View.VISIBLE);
                    llRecordpersonal26.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        nsRecordpersonal27.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    tvRecordpersonal28.setVisibility(View.INVISIBLE);
                    tvRecordpersonal29.setVisibility(View.INVISIBLE);
                    llRecordpersonal28.setVisibility(View.INVISIBLE);
                    llRecordpersonal29.setVisibility(View.INVISIBLE);
                } else {
                    tvRecordpersonal28.setVisibility(View.VISIBLE);
                    tvRecordpersonal29.setVisibility(View.VISIBLE);
                    llRecordpersonal28.setVisibility(View.VISIBLE);
                    llRecordpersonal29.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 初始化 糖尿病 pcos other
     *
     * @param view
     */
    private void init1(View view) {
        nsRecordpersonal11 = (NiceSpinner) view.findViewById(R.id.ns_recordpersonal1_1);
        etRecordpersonal12 = (EditText) view.findViewById(R.id.et_recordpersonal1_2);
        etRecordpersonal13 = (TextView) view.findViewById(R.id.et_recordpersonal1_3);
        etRecordpersonal13.setOnClickListener(this);
        nsRecordpersonal14 = (NiceSpinner) view.findViewById(R.id.ns_recordpersonal1_4);
        tvRecordpersonal15 = (TextView) view.findViewById(R.id.tv_recordpersonal1_5);
        etRecordpersonal15 = (TextView) view.findViewById(R.id.et_recordpersonal1_5);
        etRecordpersonal15.setOnClickListener(this);
        llRecordpersonal15 = (LinearLayout) view.findViewById(R.id.ll_recordpersonal1_5);
        tvRecordpersonal16 = (TextView) view.findViewById(R.id.tv_recordpersonal1_6);
        etRecordpersonal16 = (TextView) view.findViewById(R.id.et_recordpersonal1_6);
        etRecordpersonal16.setOnClickListener(this);
        llRecordpersonal16 = (LinearLayout) view.findViewById(R.id.ll_recordpersonal1_6);
        nsRecordpersonal17 = (NiceSpinner) view.findViewById(R.id.ns_recordpersonal1_7);
        tvRecordpersonal18 = (TextView) view.findViewById(R.id.tv_recordpersonal1_8);
        etRecordpersonal18 = (TextView) view.findViewById(R.id.et_recordpersonal1_8);
        etRecordpersonal18.setOnClickListener(this);
        llRecordpersonal18 = (LinearLayout) view.findViewById(R.id.ll_recordpersonal1_8);
        tvRecordpersonal19 = (TextView) view.findViewById(R.id.tv_recordpersonal1_9);
        etRecordpersonal19 = (TextView) view.findViewById(R.id.et_recordpersonal1_9);
        etRecordpersonal19.setOnClickListener(this);
        llRecordpersonal19 = (LinearLayout) view.findViewById(R.id.ll_recordpersonal1_9);
        nsRecordpersonal110 = (NiceSpinner) view.findViewById(R.id.ns_recordpersonal1_10);
        tvRecordpersonal111 = (TextView) view.findViewById(R.id.tv_recordpersonal1_11);
        etRecordpersonal111 = (TextView) view.findViewById(R.id.et_recordpersonal1_11);
        etRecordpersonal111.setOnClickListener(this);
        llRecordpersonal111 = (LinearLayout) view.findViewById(R.id.ll_recordpersonal1_11);
        tvRecordpersonal112 = (TextView) view.findViewById(R.id.tv_recordpersonal1_12);
        nsRecordpersonal112 = (NiceSpinner) view.findViewById(R.id.ns_recordpersonal1_12);
        llRecordpersonal112 = (LinearLayout) view.findViewById(R.id.ll_recordpersonal1_12);
        etRecordpersonal114 = (TextView) view.findViewById(R.id.et_recordpersonal1_14);
        etRecordpersonal114.setOnClickListener(this);
        nsRecordpersonal115 = (NiceSpinner) view.findViewById(R.id.ns_recordpersonal1_15);
        nsRecordpersonal14.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    tvRecordpersonal15.setVisibility(View.INVISIBLE);
                    tvRecordpersonal16.setVisibility(View.INVISIBLE);
                    llRecordpersonal15.setVisibility(View.INVISIBLE);
                    llRecordpersonal16.setVisibility(View.INVISIBLE);
                } else {
                    tvRecordpersonal15.setVisibility(View.VISIBLE);
                    tvRecordpersonal16.setVisibility(View.VISIBLE);
                    llRecordpersonal15.setVisibility(View.VISIBLE);
                    llRecordpersonal16.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        nsRecordpersonal17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    tvRecordpersonal18.setVisibility(View.INVISIBLE);
                    tvRecordpersonal19.setVisibility(View.INVISIBLE);
                    llRecordpersonal18.setVisibility(View.INVISIBLE);
                    llRecordpersonal19.setVisibility(View.INVISIBLE);
                } else {
                    tvRecordpersonal18.setVisibility(View.VISIBLE);
                    tvRecordpersonal19.setVisibility(View.VISIBLE);
                    llRecordpersonal18.setVisibility(View.VISIBLE);
                    llRecordpersonal19.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        nsRecordpersonal110.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    tvRecordpersonal111.setVisibility(View.INVISIBLE);
                    tvRecordpersonal112.setVisibility(View.INVISIBLE);
                    llRecordpersonal111.setVisibility(View.INVISIBLE);
                    llRecordpersonal112.setVisibility(View.INVISIBLE);
                } else {
                    tvRecordpersonal111.setVisibility(View.VISIBLE);
                    tvRecordpersonal112.setVisibility(View.VISIBLE);
                    llRecordpersonal111.setVisibility(View.VISIBLE);
                    llRecordpersonal112.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        CommonUtil.showNumberPickerDialog(getActivity(), v);
    }
}
