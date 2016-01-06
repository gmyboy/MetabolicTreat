package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.MHistory_Now;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.widget.ChangeButton;
import com.shwootide.metabolictreat.widget.nicespinner.NiceSpinner;

import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 复诊病史界面
 * Created by GMY on 2015/10/15 12:37.
 * Contact me via email gmyboy@qq.com.
 */
public class ReDiagnosisRecordFragment extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.rl_rediagnosis)
    RelativeLayout rlRediagnosis;
    //糖尿病
    CheckBox cRe11;
    CheckBox cRe12;
    CheckBox cRe13;
    CheckBox cRe14;
    CheckBox cRe15;
    CheckBox cRe16;
    CheckBox cRe17;
    ChangeButton cbRe11;
    ChangeButton cbRe12;
    ChangeButton cbRe13;
    ChangeButton cbRe14;
    ChangeButton cbRe15;
    TextView tvRe11;
    TextView etRe11;
    LinearLayout llRe11;
    ChangeButton cbRe16;
    ChangeButton cbRe17;
    TextView tvRe12;
    TextView etRe12;
    LinearLayout llRe12;
    //dm
    CheckBox cRe21;
    CheckBox cRe22;
    CheckBox cRe23;
    CheckBox cRe24;
    CheckBox cRe25;
    CheckBox cRe26;
    NiceSpinner nsRe21;
    ChangeButton cbRe22;
    ChangeButton cbRe23;
    NiceSpinner nsRe24;
    NiceSpinner nsRe25;
    //pcos
    CheckBox cRe31;
    CheckBox cRe32;
    CheckBox cRe33;
    CheckBox cRe34;
    CheckBox cRe35;
    CheckBox cRe36;
    CheckBox cRe37;
    NiceSpinner nsRe31;
    NiceSpinner nsRe32;
    TextView etRe331;
    ChangeButton cbRe34;
    TextView tvRe35;
    TextView etRe35;
    LinearLayout llRe35;
    ChangeButton cbRe36;
    TextView tvRe37;
    TextView etRe37;
    LinearLayout llRe37;
    //other
    CheckBox cRe41;
    CheckBox cRe42;
    CheckBox cRe43;
    CheckBox cRe44;
    CheckBox cRe45;
    CheckBox cRe46;
    CheckBox cRe47;
    CheckBox cRe48;
    NiceSpinner nsRe41;
    NiceSpinner nsRe42;
    TextView etRe431;

    ChangeButton cbRe44;
    TextView tvRe45;
    TextView etRe45;
    LinearLayout llRe45;
    NiceSpinner nsRe46;
    ChangeButton cbRe47;
    TextView tvRe48;
    TextView etRe48;
    LinearLayout llRe48;

    private PopupWindow pop;//查询结果点击之后显示的view
    private View popView;//pop的布局
    private MHistory_Now mHistory_now;//记录数据，用于更新

    @OnClick(R.id.btn_history_look)
    void showHistory() {
        showPop();
    }

    public static ReDiagnosisRecordFragment newInstance(String illnessid, int position, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", position);
        args.putInt("SequenceNumber", mSequenceNumber);
        ReDiagnosisRecordFragment fragment = new ReDiagnosisRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnHistory.setVisibility(View.VISIBLE);
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
                init4(view);
                break;
            default:
                break;
        }
        rlRediagnosis.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rlRediagnosis.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                initPop(rlRediagnosis.getMeasuredWidth(), rlRediagnosis.getMeasuredHeight());
            }
        });
    }

    /**
     * 初始化弹出框相关的view
     *
     * @param measuredWidth
     * @param measuredHeight
     */
    private void initPop(int measuredWidth, int measuredHeight) {
        popView = getActivity().getLayoutInflater().inflate(R.layout.view_popup_main, null);
        TextView tvName = (TextView) popView.findViewById(R.id.tv_pop_main1);
        ImageButton ibClose = (ImageButton) popView.findViewById(R.id.ib_back_pop_main);
        tvName.setText("姓名：" + tvFullBingren.getText().toString());
        CombinedChart combinedChart1 = (CombinedChart) popView.findViewById(R.id.chart1);
        CombinedChart combinedChart2 = (CombinedChart) popView.findViewById(R.id.chart2);
        TextView txt1 = (TextView) popView.findViewById(R.id.txt1);
        TextView txt2 = (TextView) popView.findViewById(R.id.txt2);
        txt1.setText(tvFullBingren.getText().toString() + "的FPG指标变化曲线");
        txt2.setText(tvFullBingren.getText().toString() + "的HbA1c指标变化条图");
        initChart(combinedChart1, 1);
        initChart(combinedChart2, 2);
        pop = new PopupWindow(popView, measuredWidth - 50, measuredHeight);
        pop.setAnimationStyle(R.style.PopRightAnimation);
        pop.setBackgroundDrawable(new BitmapDrawable());
        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pop.isShowing()) pop.dismiss();
            }
        });
    }

    private void initChart(CombinedChart mChart, int i) {
        mChart.setDescription("");
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        // draw bars behind lines
        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        String[] mMonths = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        CombinedData data = new CombinedData(mMonths);
        if (i == 1) {
            data.setData(generateLineData());
        } else {
            data.setData(generateBarData());
        }
//        data.setData(generateBubbleData());
//        data.setData(generateScatterData());
//        data.setData(generateCandleData());

        mChart.setData(data);
        mChart.invalidate();
    }

    private LineData generateLineData() {
        LineData d = new LineData();
        ArrayList<Entry> entries = new ArrayList<>();
        for (int index = 0; index < 12; index++)
            entries.add(new Entry((float) (Math.random() * 15) + 10, index));
        LineDataSet set = new LineDataSet(entries, "FPG");
        set.setColor(Color.rgb(240, 238, 70));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleSize(5f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setDrawCubic(true);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);
        return d;
    }

    private BarData generateBarData() {
        BarData d = new BarData();
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int index = 0; index < 12; index++)
            entries.add(new BarEntry((float) (Math.random() * 15) + 10, index));
        BarDataSet set = new BarDataSet(entries, "HbA1c");
        set.setColor(Color.rgb(60, 220, 78));
        set.setValueTextColor(Color.rgb(60, 220, 78));
        set.setValueTextSize(10f);
        d.addDataSet(set);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        return d;
    }

    protected ScatterData generateScatterData() {

        ScatterData d = new ScatterData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < 12; index++)
            entries.add(new Entry((float) (Math.random() * 20) + 15, index));

        ScatterDataSet set = new ScatterDataSet(entries, "Scatter DataSet");
        set.setColor(Color.GREEN);
        set.setScatterShapeSize(7.5f);
        set.setDrawValues(false);
        set.setValueTextSize(10f);
        d.addDataSet(set);

        return d;
    }

    protected CandleData generateCandleData() {

        CandleData d = new CandleData();

        ArrayList<CandleEntry> entries = new ArrayList<>();

        for (int index = 0; index < 12; index++)
            entries.add(new CandleEntry(index, 20f, 10f, 13f, 17f));

        CandleDataSet set = new CandleDataSet(entries, "Candle DataSet");
        set.setColor(Color.rgb(80, 80, 80));
        set.setBodySpace(0.3f);
        set.setValueTextSize(10f);
        set.setDrawValues(false);
        d.addDataSet(set);

        return d;
    }

    protected BubbleData generateBubbleData() {

        BubbleData bd = new BubbleData();

        ArrayList<BubbleEntry> entries = new ArrayList<>();

        for (int index = 0; index < 12; index++) {
            float rnd = (float) (Math.random() * 20) + 30;
            entries.add(new BubbleEntry(index, rnd, rnd));
        }

        BubbleDataSet set = new BubbleDataSet(entries, "Bubble DataSet");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.WHITE);
        set.setHighlightCircleWidth(1.5f);
        set.setDrawValues(true);
        bd.addDataSet(set);
        return bd;
    }

    /**
     * 显示pop
     */
    private void showPop() {
        int[] location = new int[2];
        rlRediagnosis.getLocationOnScreen(location);
        pop.showAtLocation(rlRediagnosis, Gravity.RIGHT | Gravity.BOTTOM, 0, 0);
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.update();
        if (pop.isShowing()) {
        }
    }

    @Override
    public void certainSubmit() {
        boolean isAdd = false;//标志位，用来区分是新增还是更新
        if (mHistory_now == null) {
            isAdd = true;
            mHistory_now = new MHistory_Now();
            mHistory_now.setId(CommonUtil.generateGUID());
            mHistory_now.setIllnessID(illnessid);
            mHistory_now.setMedicalRecordID(SysApplication.getInstance().getMedicalRecord().getId());
            mHistory_now.setInputUser(String.valueOf(getmUserInfo().getUserID()));
            mHistory_now.setType("5");//1 现病史 2 既往史 3 家族史 4 个人史  5 复诊病史
        }
        mHistory_now.setOther(mEtOther.getText().toString());
        mHistory_now.setInputDate(CommonUtil.getSysDate());
        switch (illnessid) {
            case "1"://糖尿病
                mHistory_now.setFields1(String.valueOf(cRe11.isChecked()));
                mHistory_now.setFields2(String.valueOf(cRe12.isChecked()));
                mHistory_now.setFields3(String.valueOf(cRe13.isChecked()));
                mHistory_now.setFields4(String.valueOf(cRe14.isChecked()));
                mHistory_now.setFields5(String.valueOf(cRe15.isChecked()));
                mHistory_now.setFields6(String.valueOf(cRe16.isChecked()));
                mHistory_now.setFields7(String.valueOf(cRe17.isChecked()));
                mHistory_now.setFields8(cbRe11.getChecked());
                mHistory_now.setFields9(cbRe12.getChecked());
                mHistory_now.setFields10(cbRe13.getChecked());
                mHistory_now.setFields11(cbRe14.getChecked());
                mHistory_now.setFields12(cbRe15.getChecked());
                mHistory_now.setFields13(etRe11.getText().toString());
                mHistory_now.setFields14(cbRe16.getChecked());
                mHistory_now.setFields15(cbRe17.getChecked());
                mHistory_now.setFields16(etRe12.getText().toString());
                break;
            case "2"://dm
                mHistory_now.setFields1(String.valueOf(cRe21.isChecked()));
                mHistory_now.setFields2(String.valueOf(cRe22.isChecked()));
                mHistory_now.setFields3(String.valueOf(cRe23.isChecked()));
                mHistory_now.setFields4(String.valueOf(cRe24.isChecked()));
                mHistory_now.setFields5(String.valueOf(cRe25.isChecked()));
                mHistory_now.setFields6(String.valueOf(cRe26.isChecked()));
                mHistory_now.setFields7(nsRe21.getSelectedIndex());
                mHistory_now.setFields8(cbRe22.getChecked());
                mHistory_now.setFields9(cbRe23.getChecked());
                mHistory_now.setFields10(nsRe24.getSelectedIndex());
                mHistory_now.setFields11(nsRe25.getSelectedIndex());
                break;
            case "3"://pcos
                mHistory_now.setFields1(String.valueOf(cRe31.isChecked()));
                mHistory_now.setFields2(String.valueOf(cRe32.isChecked()));
                mHistory_now.setFields3(String.valueOf(cRe33.isChecked()));
                mHistory_now.setFields4(String.valueOf(cRe34.isChecked()));
                mHistory_now.setFields5(String.valueOf(cRe35.isChecked()));
                mHistory_now.setFields6(String.valueOf(cRe36.isChecked()));
                mHistory_now.setFields7(String.valueOf(cRe37.isChecked()));
                mHistory_now.setFields8(nsRe31.getSelectedIndex());
                mHistory_now.setFields9(nsRe32.getSelectedIndex());
                mHistory_now.setFields10(etRe331.getText().toString());
                mHistory_now.setFields11(cbRe34.getChecked());
                mHistory_now.setFields12(etRe35.getText().toString());
                mHistory_now.setFields13(cbRe36.getChecked());
                mHistory_now.setFields14(etRe37.getText().toString());
                break;
            case "9000"://other
                mHistory_now.setFields1(String.valueOf(cRe41.isChecked()));
                mHistory_now.setFields2(String.valueOf(cRe42.isChecked()));
                mHistory_now.setFields3(String.valueOf(cRe43.isChecked()));
                mHistory_now.setFields4(String.valueOf(cRe44.isChecked()));
                mHistory_now.setFields5(String.valueOf(cRe45.isChecked()));
                mHistory_now.setFields6(String.valueOf(cRe46.isChecked()));
                mHistory_now.setFields7(String.valueOf(cRe47.isChecked()));
                mHistory_now.setFields8(String.valueOf(cRe48.isChecked()));
                mHistory_now.setFields9(nsRe41.getSelectedIndex());
                mHistory_now.setFields10(nsRe42.getSelectedIndex());
                mHistory_now.setFields11(etRe431.getText().toString());
                mHistory_now.setFields12(cbRe44.getChecked());
                mHistory_now.setFields13(etRe45.getText().toString());
                mHistory_now.setFields14(nsRe46.getSelectedIndex());
                mHistory_now.setFields15(cbRe47.getChecked());
                mHistory_now.setFields16(etRe48.getText().toString());
                break;
        }
        if (isAdd) {
            new SingleFetcher(String.class).addMedicalRecord_Now(getActivity(), "正在提交...", mHistory_now);
        } else {//更新数据
            new SingleFetcher(String.class).updateMedicalRecord_Now(getActivity(), "正在更新数据...", mHistory_now);
        }
    }

    /**
     * 初始化other相关view
     *
     * @param view
     */
    private void init4(View view) {
        cRe41 = (CheckBox) view.findViewById(R.id.c_re4_1);
        cRe42 = (CheckBox) view.findViewById(R.id.c_re4_2);
        cRe43 = (CheckBox) view.findViewById(R.id.c_re4_3);
        cRe44 = (CheckBox) view.findViewById(R.id.c_re4_4);
        cRe45 = (CheckBox) view.findViewById(R.id.c_re4_5);
        cRe46 = (CheckBox) view.findViewById(R.id.c_re4_6);
        cRe47 = (CheckBox) view.findViewById(R.id.c_re4_7);
        cRe48 = (CheckBox) view.findViewById(R.id.c_re4_8);
        nsRe41 = (NiceSpinner) view.findViewById(R.id.ns_re4_1);
        nsRe42 = (NiceSpinner) view.findViewById(R.id.ns_re4_2);
        etRe431 = (TextView) view.findViewById(R.id.et_re4_3_1);
        etRe431.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });

        cbRe44 = (ChangeButton) view.findViewById(R.id.cb_re4_4);
        tvRe45 = (TextView) view.findViewById(R.id.tv_re4_5);
        etRe45 = (TextView) view.findViewById(R.id.et_re4_5);
        etRe45.setOnClickListener(this);
        etRe45.addTextChangedListener(CommonUtil.getTextWatch());
        llRe45 = (LinearLayout) view.findViewById(R.id.ll_re4_5);

        nsRe46 = (NiceSpinner) view.findViewById(R.id.ns_re4_6);

        cbRe47 = (ChangeButton) view.findViewById(R.id.cb_re4_7);
        tvRe48 = (TextView) view.findViewById(R.id.tv_re4_8);
        etRe48 = (TextView) view.findViewById(R.id.et_re4_8);
        etRe48.setOnClickListener(this);
        etRe48.addTextChangedListener(CommonUtil.getTextWatch());

        llRe48 = (LinearLayout) view.findViewById(R.id.ll_re4_8);

        cbRe44.setChangeListener(new ChangeButton.OnCheckedChangeListener() {
            @Override
            public void checkedChange(View v, boolean isChecked) {
                if (isChecked) {
                    tvRe45.setVisibility(View.VISIBLE);
                    llRe45.setVisibility(View.VISIBLE);
                } else {
                    tvRe45.setVisibility(View.INVISIBLE);
                    llRe45.setVisibility(View.INVISIBLE);
                }
            }
        });
        cbRe47.setChangeListener(new ChangeButton.OnCheckedChangeListener() {
            @Override
            public void checkedChange(View v, boolean isChecked) {
                if (isChecked) {
                    tvRe48.setVisibility(View.VISIBLE);
                    llRe48.setVisibility(View.VISIBLE);
                } else {
                    tvRe48.setVisibility(View.INVISIBLE);
                    llRe48.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    /**
     * 初始化pcos相关view
     *
     * @param view
     */
    private void init3(View view) {
        cRe31 = (CheckBox) view.findViewById(R.id.c_re3_1);
        cRe32 = (CheckBox) view.findViewById(R.id.c_re3_2);
        cRe33 = (CheckBox) view.findViewById(R.id.c_re3_3);
        cRe34 = (CheckBox) view.findViewById(R.id.c_re3_4);
        cRe35 = (CheckBox) view.findViewById(R.id.c_re3_5);
        cRe36 = (CheckBox) view.findViewById(R.id.c_re3_6);
        cRe37 = (CheckBox) view.findViewById(R.id.c_re3_7);
        nsRe31 = (NiceSpinner) view.findViewById(R.id.ns_re3_1);
        nsRe32 = (NiceSpinner) view.findViewById(R.id.ns_re3_2);
        etRe331 = (TextView) view.findViewById(R.id.et_re3_3_1);
        etRe331.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.showDateDialog(getActivity(), v);
            }
        });
        cbRe34 = (ChangeButton) view.findViewById(R.id.cb_re3_4);
        tvRe35 = (TextView) view.findViewById(R.id.tv_re3_5);
        etRe35 = (TextView) view.findViewById(R.id.et_re3_5);
        etRe35.setOnClickListener(this);
        etRe35.addTextChangedListener(CommonUtil.getTextWatch());
        llRe35 = (LinearLayout) view.findViewById(R.id.ll_re3_5);

        cbRe36 = (ChangeButton) view.findViewById(R.id.cb_re3_6);
        tvRe37 = (TextView) view.findViewById(R.id.tv_re3_7);
        etRe37 = (TextView) view.findViewById(R.id.et_re3_7);
        etRe37.setOnClickListener(this);
        etRe37.addTextChangedListener(CommonUtil.getTextWatch());

        llRe37 = (LinearLayout) view.findViewById(R.id.ll_re3_7);

        cbRe34.setChangeListener(new ChangeButton.OnCheckedChangeListener() {
            @Override
            public void checkedChange(View v, boolean isChecked) {
                if (isChecked) {
                    tvRe35.setVisibility(View.VISIBLE);
                    llRe35.setVisibility(View.VISIBLE);
                } else {
                    tvRe35.setVisibility(View.INVISIBLE);
                    llRe35.setVisibility(View.INVISIBLE);
                }
            }
        });

        cbRe36.setChangeListener(new ChangeButton.OnCheckedChangeListener() {
            @Override
            public void checkedChange(View v, boolean isChecked) {
                if (isChecked) {
                    tvRe37.setVisibility(View.VISIBLE);
                    llRe37.setVisibility(View.VISIBLE);
                } else {
                    tvRe37.setVisibility(View.INVISIBLE);
                    llRe37.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    /**
     * 初始化dm相关view
     *
     * @param view
     */
    private void init2(View view) {
        cRe21 = (CheckBox) view.findViewById(R.id.c_re2_1);
        cRe22 = (CheckBox) view.findViewById(R.id.c_re2_2);
        cRe23 = (CheckBox) view.findViewById(R.id.c_re2_3);
        cRe24 = (CheckBox) view.findViewById(R.id.c_re2_4);
        cRe25 = (CheckBox) view.findViewById(R.id.c_re2_5);
        cRe26 = (CheckBox) view.findViewById(R.id.c_re2_6);
        nsRe21 = (NiceSpinner) view.findViewById(R.id.ns_re2_1);
        cbRe22 = (ChangeButton) view.findViewById(R.id.cb_re2_2);
        cbRe23 = (ChangeButton) view.findViewById(R.id.cb_re2_3);
        nsRe24 = (NiceSpinner) view.findViewById(R.id.ns_re2_4);
        nsRe25 = (NiceSpinner) view.findViewById(R.id.ns_re2_5);
    }

    /**
     * 初始化糖尿病相关view
     *
     * @param view
     */
    private void init1(View view) {
        cRe11 = (CheckBox) view.findViewById(R.id.c_re1_1);
        cRe12 = (CheckBox) view.findViewById(R.id.c_re1_2);
        cRe13 = (CheckBox) view.findViewById(R.id.c_re1_3);
        cRe14 = (CheckBox) view.findViewById(R.id.c_re1_4);
        cRe15 = (CheckBox) view.findViewById(R.id.c_re1_5);
        cRe16 = (CheckBox) view.findViewById(R.id.c_re1_6);
        cRe17 = (CheckBox) view.findViewById(R.id.c_re1_7);
        cbRe11 = (ChangeButton) view.findViewById(R.id.cb_re1_1);
        cbRe12 = (ChangeButton) view.findViewById(R.id.cb_re1_2);
        cbRe13 = (ChangeButton) view.findViewById(R.id.cb_re1_3);
        cbRe14 = (ChangeButton) view.findViewById(R.id.cb_re1_4);

        cbRe15 = (ChangeButton) view.findViewById(R.id.cb_re1_5);
        tvRe11 = (TextView) view.findViewById(R.id.tv_re1_1);
        etRe11 = (TextView) view.findViewById(R.id.et_re1_1);
        etRe11.setOnClickListener(this);
        etRe11.addTextChangedListener(CommonUtil.getTextWatch());
        llRe11 = (LinearLayout) view.findViewById(R.id.ll_re1_1);

        cbRe16 = (ChangeButton) view.findViewById(R.id.cb_re1_6);

        cbRe17 = (ChangeButton) view.findViewById(R.id.cb_re1_7);
        tvRe12 = (TextView) view.findViewById(R.id.tv_re1_2);
        etRe12 = (TextView) view.findViewById(R.id.et_re1_2);
        etRe12.setOnClickListener(this);
        etRe12.addTextChangedListener(CommonUtil.getTextWatch());
        llRe12 = (LinearLayout) view.findViewById(R.id.ll_re1_2);
        cbRe15.setChangeListener(new ChangeButton.OnCheckedChangeListener() {
            @Override
            public void checkedChange(View v, boolean isChecked) {
                if (isChecked) {
                    tvRe11.setVisibility(View.VISIBLE);
                    llRe11.setVisibility(View.VISIBLE);
                } else {
                    tvRe11.setVisibility(View.INVISIBLE);
                    llRe11.setVisibility(View.INVISIBLE);
                }
            }
        });
        cbRe17.setChangeListener(new ChangeButton.OnCheckedChangeListener() {
            @Override
            public void checkedChange(View v, boolean isChecked) {
                if (isChecked) {
                    tvRe12.setVisibility(View.VISIBLE);
                    llRe12.setVisibility(View.VISIBLE);
                } else {
                    tvRe12.setVisibility(View.INVISIBLE);
                    llRe12.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void lazyLoad() {
        if (position == 2) {//编辑
            //获取现病史信息
            Map<String, String> params = new WeakHashMap<>();
            params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
            params.put("type", "5");
            new MutiFetcher(MHistory_Now[].class).fetch(getActivity(), "FindMedicalRecordHistoryNew", "正在查询...", params);
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
                initData4(now);
                break;
        }
        mHasLoadedOnce = true;
    }

    private void initData4(MHistory_Now now) {
        cRe41.setChecked(now.getFields1().equals("true") ? true : false);
        cRe42.setChecked(now.getFields2().equals("true") ? true : false);
        cRe43.setChecked(now.getFields3().equals("true") ? true : false);
        cRe44.setChecked(now.getFields4().equals("true") ? true : false);
        cRe45.setChecked(now.getFields5().equals("true") ? true : false);
        cRe46.setChecked(now.getFields6().equals("true") ? true : false);
        cRe47.setChecked(now.getFields7().equals("true") ? true : false);
        cRe48.setChecked(now.getFields8().equals("true") ? true : false);
        nsRe41.setSelectedIndex(CommonUtil.parserInt(now.getFields9()));
        nsRe42.setSelectedIndex(CommonUtil.parserInt(now.getFields10()));
        etRe431.setText(CommonUtil.parserInt(now.getFields11()));
        cbRe44.setChecked(now.getFields12().equals("true") ? true : false);
        etRe45.setText(now.getFields13());
        nsRe46.setSelectedIndex(CommonUtil.parserInt(now.getFields14()));
        cbRe47.setChecked(now.getFields15().equals("true") ? true : false);
        etRe48.setText(now.getFields16());
    }

    private void initData3(MHistory_Now now) {
        cRe31.setChecked(now.getFields1().equals("true") ? true : false);
        cRe32.setChecked(now.getFields2().equals("true") ? true : false);
        cRe33.setChecked(now.getFields3().equals("true") ? true : false);
        cRe34.setChecked(now.getFields4().equals("true") ? true : false);
        cRe35.setChecked(now.getFields5().equals("true") ? true : false);
        cRe36.setChecked(now.getFields6().equals("true") ? true : false);
        cRe37.setChecked(now.getFields7().equals("true") ? true : false);
        nsRe31.setSelectedIndex(CommonUtil.parserInt(now.getFields8()));
        nsRe32.setSelectedIndex(CommonUtil.parserInt(now.getFields9()));
        etRe331.setText(CommonUtil.parserInt(now.getFields10()));
        cbRe34.setChecked(now.getFields11().equals("true") ? true : false);
        etRe35.setText(now.getFields12());
        cbRe36.setChecked(now.getFields13().equals("true") ? true : false);
        etRe37.setText(now.getFields14());
    }

    private void initData2(MHistory_Now now) {
        cRe21.setChecked(now.getFields1().equals("true") ? true : false);
        cRe22.setChecked(now.getFields2().equals("true") ? true : false);
        cRe23.setChecked(now.getFields3().equals("true") ? true : false);
        cRe24.setChecked(now.getFields4().equals("true") ? true : false);
        cRe25.setChecked(now.getFields5().equals("true") ? true : false);
        cRe26.setChecked(now.getFields6().equals("true") ? true : false);
        nsRe21.setSelectedIndex(CommonUtil.parserInt(now.getFields7()));
        cbRe22.setChecked(now.getFields8().equals("true") ? true : false);
        cbRe23.setChecked(now.getFields9().equals("true") ? true : false);
        nsRe24.setSelectedIndex(CommonUtil.parserInt(now.getFields10()));
        nsRe25.setSelectedIndex(CommonUtil.parserInt(now.getFields11()));
    }

    private void initData1(MHistory_Now now) {
        cRe11.setChecked(now.getFields1().equals("true") ? true : false);
        cRe12.setChecked(now.getFields2().equals("true") ? true : false);
        cRe13.setChecked(now.getFields3().equals("true") ? true : false);
        cRe14.setChecked(now.getFields4().equals("true") ? true : false);
        cRe15.setChecked(now.getFields5().equals("true") ? true : false);
        cRe16.setChecked(now.getFields6().equals("true") ? true : false);
        cRe17.setChecked(now.getFields7().equals("true") ? true : false);
        cbRe11.setChecked(now.getFields8().equals("true") ? true : false);
        cbRe12.setChecked(now.getFields9().equals("true") ? true : false);
        cbRe13.setChecked(now.getFields10().equals("true") ? true : false);
        cbRe14.setChecked(now.getFields11().equals("true") ? true : false);
        cbRe15.setChecked(now.getFields12().equals("true") ? true : false);
        etRe11.setText(now.getFields13());
        cbRe16.setChecked(now.getFields14().equals("true") ? true : false);
        cbRe17.setChecked(now.getFields15().equals("true") ? true : false);
        etRe12.setText(now.getFields16());
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.getWhat().equals("FindMedicalRecordHistoryNew") && isVisible) {
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                mHistory_now = (MHistory_Now) event.getObjects().get(0);
                initData(mHistory_now);
            }
        } else if (event.getWhat().equals("AddMedicalRecordHistoryNew") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
//              跳转到既往史
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

    @Override
    public int bindViewById() {
        switch (illnessid) {
            case "1"://糖尿病
                return R.layout.frag_rediagnosis_record1;
            case "2"://dm
                return R.layout.frag_rediagnosis_record2;
            case "3"://pcos
                return R.layout.frag_rediagnosis_record3;
            case "9000"://other
                return R.layout.frag_rediagnosis_record4;
            default:
                return R.layout.frag_rediagnosis_record1;
        }
    }

    @Override
    public void onClick(View v) {
        CommonUtil.showNumberPickerDialog(getActivity(), v);
    }
}
