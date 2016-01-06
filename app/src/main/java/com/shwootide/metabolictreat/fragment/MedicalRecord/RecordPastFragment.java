package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

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
 * 既往史
 * Created by GMY on 2015/9/8 11:09.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordPastFragment extends BaseFragment {
    //糖尿病
    NiceSpinner nsRecordpast11;
    NiceSpinner nsRecordpast12;
    NiceSpinner nsRecordpast13;
    NiceSpinner nsRecordpast14;
    NiceSpinner nsRecordpast15;
    NiceSpinner nsRecordpast16;
    NiceSpinner nsRecordpast17;
    ChangeButton cbRecordpast18;
    ChangeButton cbRecordpast19;
    NiceSpinner nsRecordpast110;
    NiceSpinner nsRecordpast111;
    //甲状腺
    NiceSpinner nsRecordpast21;
    NiceSpinner nsRecordpast22;
    NiceSpinner nsRecordpast23;
    NiceSpinner nsRecordpast24;
    NiceSpinner nsRecordpast25;
    ChangeButton cbRecordpast26;
    ChangeButton cbRecordpast27;
    ChangeButton cbRecordpast28;
    ChangeButton cbRecordpast29;
    //pcos
    NiceSpinner nsRecordpast31;
    NiceSpinner nsRecordpast32;
    NiceSpinner nsRecordpast33;
    NiceSpinner nsRecordpast34;
    NiceSpinner nsRecordpast35;
    NiceSpinner nsRecordpast36;
    NiceSpinner nsRecordpast37;
    ChangeButton cbRecordpast38;
    NiceSpinner nsRecordpast39;
    //其他
    NiceSpinner nsRecordpast41;
    NiceSpinner nsRecordpast42;
    NiceSpinner nsRecordpast43;
    NiceSpinner nsRecordpast44;
    NiceSpinner nsRecordpast45;
    NiceSpinner nsRecordpast46;
    NiceSpinner nsRecordpast47;
    NiceSpinner nsRecordpast48;
    NiceSpinner nsRecordpast49;
    NiceSpinner nsRecordpast410;
    ChangeButton cbRecordpast411;
    NiceSpinner nsRecordpast412;

    private MHistory_Now mHistory_now;

    public static RecordPastFragment newInstance(String illnessid, int mPosition, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", mPosition);
        args.putInt("SequenceNumber", mSequenceNumber);
        RecordPastFragment fragment = new RecordPastFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        switch (illnessid) {
            case "1"://糖尿病
                return R.layout.frag_recordpast1;
            case "2"://dm
                return R.layout.frag_recordpast2;
            case "3"://pcos
                return R.layout.frag_recordpast3;
            case "9000"://other
                return R.layout.frag_recordpast4;
            default:
                return R.layout.frag_recordpast1;
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
            mHistory_now.setType("2");
        }
        mHistory_now.setOther(mEtOther.getText().toString());
        mHistory_now.setInputDate(CommonUtil.getSysDate());
        switch (illnessid) {
            case "1"://糖尿病
                mHistory_now.setFields1(nsRecordpast11.getSelectedIndex());
                mHistory_now.setFields2(nsRecordpast12.getSelectedIndex());
                mHistory_now.setFields3(nsRecordpast13.getSelectedIndex());
                mHistory_now.setFields4(nsRecordpast14.getSelectedIndex());
                mHistory_now.setFields5(nsRecordpast15.getSelectedIndex());
                mHistory_now.setFields6(nsRecordpast16.getSelectedIndex());
                mHistory_now.setFields7(nsRecordpast17.getSelectedIndex());
                mHistory_now.setFields8(cbRecordpast18.getChecked());
                mHistory_now.setFields9(cbRecordpast19.getChecked());
                mHistory_now.setFields10(nsRecordpast110.getSelectedIndex());
                mHistory_now.setFields11(nsRecordpast111.getSelectedIndex());
                break;
            case "2"://dm
                mHistory_now.setFields1(nsRecordpast21.getSelectedIndex());
                mHistory_now.setFields2(nsRecordpast22.getSelectedIndex());
                mHistory_now.setFields3(nsRecordpast23.getSelectedIndex());
                mHistory_now.setFields4(nsRecordpast24.getSelectedIndex());
                mHistory_now.setFields5(nsRecordpast25.getSelectedIndex());
                mHistory_now.setFields6(cbRecordpast26.getChecked());
                mHistory_now.setFields7(cbRecordpast27.getChecked());
                mHistory_now.setFields8(cbRecordpast28.getChecked());
                mHistory_now.setFields9(cbRecordpast29.getChecked());
                break;
            case "3"://pcos
                mHistory_now.setFields1(nsRecordpast31.getSelectedIndex());
                mHistory_now.setFields2(nsRecordpast32.getSelectedIndex());
                mHistory_now.setFields3(nsRecordpast33.getSelectedIndex());
                mHistory_now.setFields4(nsRecordpast34.getSelectedIndex());
                mHistory_now.setFields5(nsRecordpast35.getSelectedIndex());
                mHistory_now.setFields6(nsRecordpast36.getSelectedIndex());
                mHistory_now.setFields7(nsRecordpast37.getSelectedIndex());
                mHistory_now.setFields8(cbRecordpast38.getChecked());
                mHistory_now.setFields9(nsRecordpast39.getSelectedIndex());
                break;
            case "9000"://other
                mHistory_now.setFields1(nsRecordpast41.getSelectedIndex());
                mHistory_now.setFields2(nsRecordpast42.getSelectedIndex());
                mHistory_now.setFields3(nsRecordpast43.getSelectedIndex());
                mHistory_now.setFields4(nsRecordpast44.getSelectedIndex());
                mHistory_now.setFields5(nsRecordpast45.getSelectedIndex());
                mHistory_now.setFields6(nsRecordpast46.getSelectedIndex());
                mHistory_now.setFields7(nsRecordpast47.getSelectedIndex());
                mHistory_now.setFields8(nsRecordpast48.getSelectedIndex());
                mHistory_now.setFields9(nsRecordpast49.getSelectedIndex());
                mHistory_now.setFields10(nsRecordpast410.getSelectedIndex());
                mHistory_now.setFields11(cbRecordpast411.getChecked());
                mHistory_now.setFields12(nsRecordpast41.getSelectedIndex());
                break;
        }
        if (isAdd) {//添加
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
        nsRecordpast41 = (NiceSpinner) view.findViewById(R.id.ns_recordpast4_1);
        nsRecordpast42 = (NiceSpinner) view.findViewById(R.id.ns_recordpast4_2);
        nsRecordpast43 = (NiceSpinner) view.findViewById(R.id.ns_recordpast4_3);
        nsRecordpast44 = (NiceSpinner) view.findViewById(R.id.ns_recordpast4_4);
        nsRecordpast45 = (NiceSpinner) view.findViewById(R.id.ns_recordpast4_5);
        nsRecordpast46 = (NiceSpinner) view.findViewById(R.id.ns_recordpast4_6);
        nsRecordpast47 = (NiceSpinner) view.findViewById(R.id.ns_recordpast4_7);
        nsRecordpast48 = (NiceSpinner) view.findViewById(R.id.ns_recordpast4_8);
        nsRecordpast49 = (NiceSpinner) view.findViewById(R.id.ns_recordpast4_9);
        nsRecordpast410 = (NiceSpinner) view.findViewById(R.id.ns_recordpast4_10);
        cbRecordpast411 = (ChangeButton) view.findViewById(R.id.cb_recordpast4_11);
        nsRecordpast412 = (NiceSpinner) view.findViewById(R.id.ns_recordpast4_12);
    }

    /**
     * 初始化pcos相关view
     *
     * @param view
     */
    private void init3(View view) {
        nsRecordpast31 = (NiceSpinner) view.findViewById(R.id.ns_recordpast3_1);
        nsRecordpast32 = (NiceSpinner) view.findViewById(R.id.ns_recordpast3_2);
        nsRecordpast33 = (NiceSpinner) view.findViewById(R.id.ns_recordpast3_3);
        nsRecordpast34 = (NiceSpinner) view.findViewById(R.id.ns_recordpast3_4);
        nsRecordpast35 = (NiceSpinner) view.findViewById(R.id.ns_recordpast3_5);
        nsRecordpast36 = (NiceSpinner) view.findViewById(R.id.ns_recordpast3_6);
        nsRecordpast37 = (NiceSpinner) view.findViewById(R.id.ns_recordpast3_7);
        cbRecordpast38 = (ChangeButton) view.findViewById(R.id.cb_recordpast3_8);
        nsRecordpast39 = (NiceSpinner) view.findViewById(R.id.ns_recordpast3_9);
    }

    /**
     * 初始化甲状腺相关view
     *
     * @param view
     */
    private void init2(View view) {
        nsRecordpast21 = (NiceSpinner) view.findViewById(R.id.ns_recordpast2_1);
        nsRecordpast22 = (NiceSpinner) view.findViewById(R.id.ns_recordpast2_2);
        nsRecordpast23 = (NiceSpinner) view.findViewById(R.id.ns_recordpast2_3);
        nsRecordpast24 = (NiceSpinner) view.findViewById(R.id.ns_recordpast2_4);
        nsRecordpast25 = (NiceSpinner) view.findViewById(R.id.ns_recordpast2_5);
        cbRecordpast26 = (ChangeButton) view.findViewById(R.id.cb_recordpast2_6);
        cbRecordpast27 = (ChangeButton) view.findViewById(R.id.cb_recordpast2_7);
        cbRecordpast28 = (ChangeButton) view.findViewById(R.id.cb_recordpast2_8);
        cbRecordpast29 = (ChangeButton) view.findViewById(R.id.cb_recordpast2_9);
    }

    /**
     * 初始化糖尿病相关view
     *
     * @param view
     */
    private void init1(View view) {
        nsRecordpast11 = (NiceSpinner) view.findViewById(R.id.ns_recordpast1_1);
        nsRecordpast12 = (NiceSpinner) view.findViewById(R.id.ns_recordpast1_2);
        nsRecordpast13 = (NiceSpinner) view.findViewById(R.id.ns_recordpast1_3);
        nsRecordpast14 = (NiceSpinner) view.findViewById(R.id.ns_recordpast1_4);
        nsRecordpast15 = (NiceSpinner) view.findViewById(R.id.ns_recordpast1_5);
        nsRecordpast16 = (NiceSpinner) view.findViewById(R.id.ns_recordpast1_6);
        nsRecordpast17 = (NiceSpinner) view.findViewById(R.id.ns_recordpast1_7);
        cbRecordpast18 = (ChangeButton) view.findViewById(R.id.cb_recordpast1_8);
        cbRecordpast19 = (ChangeButton) view.findViewById(R.id.cb_recordpast1_9);
        nsRecordpast110 = (NiceSpinner) view.findViewById(R.id.ns_recordpast1_10);
        nsRecordpast111 = (NiceSpinner) view.findViewById(R.id.ns_recordpast1_11);
    }

    @Override
    public void lazyLoad() {
        if (position == 2) {//编辑
            //获取现病史信息
            Map<String, String> params = new WeakHashMap<>();
            params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
            new MutiFetcher(MHistory_Now[].class).fetch(getActivity(), "FindMedicalRecordHistoryNew2", "正在查询...", params);
        }
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.what.equals("FindMedicalRecordHistoryNew2") && isVisible) {
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
        nsRecordpast41.setSelectedIndex(CommonUtil.parserInt(now.getFields1()));
        nsRecordpast42.setSelectedIndex(CommonUtil.parserInt(now.getFields2()));
        nsRecordpast43.setSelectedIndex(CommonUtil.parserInt(now.getFields3()));
        nsRecordpast44.setSelectedIndex(CommonUtil.parserInt(now.getFields4()));
        nsRecordpast45.setSelectedIndex(CommonUtil.parserInt(now.getFields5()));
        nsRecordpast46.setSelectedIndex(CommonUtil.parserInt(now.getFields6()));
        nsRecordpast47.setSelectedIndex(CommonUtil.parserInt(now.getFields7()));
        nsRecordpast48.setSelectedIndex(CommonUtil.parserInt(now.getFields8()));
        nsRecordpast49.setSelectedIndex(CommonUtil.parserInt(now.getFields9()));
        nsRecordpast410.setSelectedIndex(CommonUtil.parserInt(now.getFields10()));
        cbRecordpast411.setChecked(now.getFields11().equals("true") ? true : false);
        nsRecordpast412.setSelectedIndex(CommonUtil.parserInt(now.getFields12()));
    }

    private void initData3(MHistory_Now now) {
        nsRecordpast31.setSelectedIndex(CommonUtil.parserInt(now.getFields1()));
        nsRecordpast32.setSelectedIndex(CommonUtil.parserInt(now.getFields2()));
        nsRecordpast33.setSelectedIndex(CommonUtil.parserInt(now.getFields3()));
        nsRecordpast34.setSelectedIndex(CommonUtil.parserInt(now.getFields4()));
        nsRecordpast35.setSelectedIndex(CommonUtil.parserInt(now.getFields5()));
        nsRecordpast36.setSelectedIndex(CommonUtil.parserInt(now.getFields6()));
        nsRecordpast37.setSelectedIndex(CommonUtil.parserInt(now.getFields7()));
        cbRecordpast38.setChecked(now.getFields8().equals("true") ? true : false);
        nsRecordpast39.setSelectedIndex(CommonUtil.parserInt(now.getFields9()));
    }

    private void initData2(MHistory_Now now) {
        nsRecordpast21.setSelectedIndex(CommonUtil.parserInt(now.getFields1()));
        nsRecordpast22.setSelectedIndex(CommonUtil.parserInt(now.getFields2()));
        nsRecordpast23.setSelectedIndex(CommonUtil.parserInt(now.getFields3()));
        nsRecordpast24.setSelectedIndex(CommonUtil.parserInt(now.getFields4()));
        nsRecordpast25.setSelectedIndex(CommonUtil.parserInt(now.getFields5()));
        cbRecordpast26.setChecked(now.getFields6().equals("true") ? true : false);
        cbRecordpast27.setChecked(now.getFields7().equals("true") ? true : false);
        cbRecordpast28.setChecked(now.getFields8().equals("true") ? true : false);
        cbRecordpast29.setChecked(now.getFields9().equals("true") ? true : false);
    }

    private void initData1(MHistory_Now now) {
        nsRecordpast11.setSelectedIndex(CommonUtil.parserInt(now.getFields1()));
        nsRecordpast12.setSelectedIndex(CommonUtil.parserInt(now.getFields2()));
        nsRecordpast13.setSelectedIndex(CommonUtil.parserInt(now.getFields3()));
        nsRecordpast14.setSelectedIndex(CommonUtil.parserInt(now.getFields4()));
        nsRecordpast15.setSelectedIndex(CommonUtil.parserInt(now.getFields5()));
        nsRecordpast16.setSelectedIndex(CommonUtil.parserInt(now.getFields6()));
        nsRecordpast17.setSelectedIndex(CommonUtil.parserInt(now.getFields7()));
        cbRecordpast18.setChecked(now.getFields8().equals("true") ? true : false);
        cbRecordpast19.setChecked(now.getFields9().equals("true") ? true : false);
        nsRecordpast110.setSelectedIndex(CommonUtil.parserInt(now.getFields10()));
        nsRecordpast111.setSelectedIndex(CommonUtil.parserInt(now.getFields11()));
    }
}
