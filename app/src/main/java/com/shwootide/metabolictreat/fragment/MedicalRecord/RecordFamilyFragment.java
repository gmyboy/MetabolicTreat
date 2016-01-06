package com.shwootide.metabolictreat.fragment.MedicalRecord;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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

import butterknife.Bind;

/**
 * 家族史
 * Created by GMY on 2015/9/8 11:09.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordFamilyFragment extends BaseFragment {

    @Bind(R.id.ns_recordfamily_1)
    NiceSpinner nsRecordfamily1;
    @Bind(R.id.ns_recordfamily_2)
    NiceSpinner nsRecordfamily2;
    @Bind(R.id.ns_recordfamily_3)
    NiceSpinner nsRecordfamily3;
    @Bind(R.id.ns_recordfamily_4)
    NiceSpinner nsRecordfamily4;
    @Bind(R.id.ns_recordfamily_5)
    NiceSpinner nsRecordfamily5;
    @Bind(R.id.ns_recordfamily_6)
    NiceSpinner nsRecordfamily6;
    @Bind(R.id.ns_recordfamily_7)
    NiceSpinner nsRecordfamily7;
    @Bind(R.id.ns_recordfamily_8)
    NiceSpinner nsRecordfamily8;
    @Bind(R.id.ns_recordfamily_9)
    NiceSpinner nsRecordfamily9;
    @Bind(R.id.ns_recordfamily_10)
    NiceSpinner nsRecordfamily10;
    @Bind(R.id.et_recordfamily_11)
    TextView etRecordfamily11;
    //对话框编辑框
    private EditText etDialogFamilyZhongliu;
    private MHistory_Now mHistory_now;

    public static RecordFamilyFragment newInstance(String illnessid, int mPosition, int mSequenceNumber) {
        Bundle args = new Bundle();
        args.putString("IllnessID", illnessid);
        args.putInt("Position", mPosition);
        args.putInt("SequenceNumber", mSequenceNumber);
        RecordFamilyFragment fragment = new RecordFamilyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_recordfamily;
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
            mHistory_now.setType("3");
        }
        mHistory_now.setOther(mEtOther.getText().toString());
        mHistory_now.setInputDate(CommonUtil.getSysDate());
        mHistory_now.setFields1(nsRecordfamily1.getSelectedIndex());
        mHistory_now.setFields2(nsRecordfamily2.getSelectedIndex());
        mHistory_now.setFields3(nsRecordfamily3.getSelectedIndex());
        mHistory_now.setFields4(nsRecordfamily4.getSelectedIndex());
        mHistory_now.setFields5(nsRecordfamily5.getSelectedIndex());
        mHistory_now.setFields6(nsRecordfamily6.getSelectedIndex());
        mHistory_now.setFields7(nsRecordfamily7.getSelectedIndex());
        mHistory_now.setFields8(nsRecordfamily8.getSelectedIndex());
        mHistory_now.setFields9(nsRecordfamily9.getSelectedIndex());
        mHistory_now.setFields10(nsRecordfamily10.getSelectedIndex());
        mHistory_now.setFields11(etRecordfamily11.getText().toString());
        if (isAdd) {
            new SingleFetcher(String.class).addMedicalRecord_Now(getActivity(), "正在提交...", mHistory_now);
        } else {//更新数据
            new SingleFetcher(String.class).updateMedicalRecord_Now(getActivity(), "正在更新数据...", mHistory_now);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nsRecordfamily10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int mPosition, long id) {
                if (mPosition == 1) {//解决病历编辑进来edittext获取焦点
                    CommonUtil.showEditDialog(getActivity(), etRecordfamily11, TextUtils.isEmpty(etRecordfamily11.getText()) ? "" : etRecordfamily11.getText().toString());
                } else {
                    etRecordfamily11.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void lazyLoad() {
        if (position == 2) {//编辑
            //获取家族史信息
            Map<String, String> params = new WeakHashMap<>();
            params.put("MedicalRecordID", SysApplication.getInstance().getMedicalRecord().getId());
            new MutiFetcher(MHistory_Now[].class).fetch(getActivity(), "FindMedicalRecordHistoryNew3", "正在查询...", params);
        }
    }

    @Override
    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.what.equals("FindMedicalRecordHistoryNew3") && isVisible) {
            if (event.getCode().equals("200") && !CommonUtil.isEmpty(event.getObjects())) {
                mHistory_now = (MHistory_Now) event.getObjects().get(0);
                initData(mHistory_now);
            }
        } else if (event.what.equals("AddMedicalRecordHistoryNew") && isVisible) {
            if (event.getCode().equals("200")) {
                CommonUtil.showToast(getActivity(), "提交成功");
                //跳转到个人史
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
        nsRecordfamily1.setSelectedIndex(CommonUtil.parserInt(now.getFields1()));
        nsRecordfamily2.setSelectedIndex(CommonUtil.parserInt(now.getFields2()));
        nsRecordfamily3.setSelectedIndex(CommonUtil.parserInt(now.getFields3()));
        nsRecordfamily4.setSelectedIndex(CommonUtil.parserInt(now.getFields4()));
        nsRecordfamily5.setSelectedIndex(CommonUtil.parserInt(now.getFields5()));
        nsRecordfamily6.setSelectedIndex(CommonUtil.parserInt(now.getFields6()));
        nsRecordfamily7.setSelectedIndex(CommonUtil.parserInt(now.getFields7()));
        nsRecordfamily8.setSelectedIndex(CommonUtil.parserInt(now.getFields8()));
        nsRecordfamily9.setSelectedIndex(CommonUtil.parserInt(now.getFields9()));
        nsRecordfamily10.setSelectedIndex(CommonUtil.parserInt(now.getFields10()));
        etRecordfamily11.setText(now.getFields11());
        mHasLoadedOnce = true;
    }

}
