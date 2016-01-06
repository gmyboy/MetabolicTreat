package com.shwootide.metabolictreat.fragment.Common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.gmy.segmentedgroup.SegmentedGroup;
import com.shwootide.metabolictreat.IllnessChooseActivity;
import com.shwootide.metabolictreat.R;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.Record;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;

import java.util.Calendar;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 添加新患者
 * Created by GMY on 2015/12/17 16:07.
 * Contact me via email igmyboy@gmail.com.
 */
public class AddFragment extends MenuFragment {
    @Bind(R.id.btn_reocrd_certain)
    Button btnReocrdCertain;
    @Bind(R.id.et_record_name)
    EditText etRecordName;
    @Bind(R.id.et_record_hospital)
    TextView etRecordHospital;

    @Bind(R.id.et_record_phone)
    EditText etRecordPhone;
    @Bind(R.id.et_record_weixin)
    EditText etRecordWeixin;
    @Bind(R.id.et_record_binlino)
    TextView etRecordBinlino;
    @Bind(R.id.sg_record_sex)
    SegmentedGroup sgRecordSex;
    @Bind(R.id.tv_main_nian)
    EditText tvMainNian;
    @Bind(R.id.tv_main_yue)
    EditText tvMainYue;
    @Bind(R.id.rb_record_man)
    RadioButton rbRecordMan;
    @Bind(R.id.rb_record_woman)
    RadioButton rbRecordWoman;
    @Bind(R.id.et_record_adress)
    EditText etRecordAdress;

    private int year, month, day;
    private int maxMedicalRecordNo = 0;//获取当前医院的今天所有的编号，之后+1就是新建病例的编号
    private int type = 0; //标识是从哪里跳转的 0 新建   1  点击
    private Record recordInfo;//患者病历基本信息

    /**
     * 添加返回时再点击确认出现姓名重复数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (type == 0) {
            type = 1;
            forbidden();
        }
    }

    /**
     * 禁止修改姓名、性别、出生年月
     */
    private void forbidden() {
        etRecordName.setFocusable(false);
        tvMainNian.setFocusable(false);
        tvMainYue.setFocusable(false);
        rbRecordMan.setClickable(false);
        rbRecordWoman.setClickable(false);
    }

    @OnClick(R.id.btn_reocrd_certain)
    void certain() {
        String name = etRecordName.getText().toString().trim();
        String sex = sgRecordSex.getCheckedRadioButtonId() == R.id.rb_record_man ? "男" : "女";
        String birth = tvMainNian.getText().toString().trim() + "年" + (TextUtils.isEmpty(tvMainYue.getText().toString().trim()) ? "00" : CommonUtil.formatInt(Integer.parseInt(tvMainYue.getText().toString().trim()), "00")) + "月";
        String tel = etRecordPhone.getText().toString().trim();
        String weixin = etRecordWeixin.getText().toString().trim();
        String medicalRecordNo = etRecordBinlino.getText().toString().trim();
        String adress = etRecordAdress.getText().toString().trim();
        String hospitalID = getmUserInfo().getHospitalID();
        String staffId = getmUserInfo().getStaffID();
        //新建
        if (type == 0) {
            recordInfo = new Record();
            if (TextUtils.isEmpty(name)) {
                CommonUtil.showToast(getActivity(), "姓名不能为空");
            } else if (TextUtils.isEmpty(tvMainNian.getText().toString().trim()) || TextUtils.isEmpty(tvMainYue.getText().toString().trim())) {
                CommonUtil.showToast(getActivity(), "出生年月不能为空");
            } else if (tvMainNian.getText().toString().trim().length() != 4 || tvMainYue.getText().toString().trim().length() > 2
                    || Integer.parseInt(tvMainYue.getText().toString().trim()) > 12
                    || Integer.parseInt(tvMainNian.getText().toString().trim()) > Integer.parseInt(CommonUtil.getSysYear())) {
                CommonUtil.showToast(getActivity(), "出生年月格式不对");
            } else if (!TextUtils.isEmpty(tel) && tel.length() != 11) {
                CommonUtil.showToast(getActivity(), "联系方式格式不对");
            } else {
                recordInfo.setName(name);
                recordInfo.setSex(sex);
                recordInfo.setBirth(birth);
                recordInfo.setTel(tel);
                recordInfo.setWeixin(weixin);

                recordInfo.setIllnessID("");
                recordInfo.setStaffID(staffId);
                recordInfo.setHospitalID(hospitalID);
                recordInfo.setMedicalRecordNo(medicalRecordNo);
                recordInfo.setCreatDate(CommonUtil.getSysDate());
                recordInfo.setFlg("");
                recordInfo.setAge("");
                recordInfo.setAddress("");
                recordInfo.setBz("");
                recordInfo.setAddress(adress);
                recordInfo.setId(CommonUtil.generateGUID());
                new SingleFetcher(String.class).addPersonInfo(getActivity(), "正在提交...", recordInfo);
            }
        } else if (type == 1) {//点击
            if (name.equals(recordInfo.getName()) && sex.equals(recordInfo.getSex())
                    && birth.equals(recordInfo.getBirth()) && tel.equals(recordInfo.getTel())
                    && weixin.equals(recordInfo.getWeixin()) && adress.equals(recordInfo.getAddress())) {
                Intent intent = new Intent(getActivity(), IllnessChooseActivity.class);
                startActivity(intent);
            } else {//修改
                Record record = recordInfo;
                if (TextUtils.isEmpty(name)) {
                    CommonUtil.showToast(getActivity(), "姓名不能为空");
                } else if (TextUtils.isEmpty(birth)) {
                    CommonUtil.showToast(getActivity(), "出生年月不能为空");
                } else if (tvMainNian.getText().toString().trim().length() != 4 || tvMainYue.getText().toString().trim().length() > 2
                        || Integer.parseInt(tvMainYue.getText().toString().trim()) > 12
                        || Integer.parseInt(tvMainNian.getText().toString().trim()) > Integer.parseInt(CommonUtil.getSysYear())) {
                    CommonUtil.showToast(getActivity(), "出生年月格式不对");
                } else if (!TextUtils.isEmpty(tel) && tel.length() != 11) {
                    CommonUtil.showToast(getActivity(), "联系方式格式不对");
                } else {
                    record.setName(name);
                    record.setSex(sex);
                    record.setBirth(birth);
                    record.setTel(tel);
                    record.setWeixin(weixin);
                    record.setAddress(adress);
                    new SingleFetcher(String.class).updatePersonInfo(getActivity(), "正在保存...", record);
                }
            }
        }
    }

    @Override
    public int bindViewById() {
        return R.layout.frag_add;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //获得系统当前日期 用于拼接处病历编号
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DATE);
        etRecordHospital.setText(getmUserInfo().getOrganizationName());
        //默认为男
        sgRecordSex.check(R.id.rb_record_man);
        getMaxNo();
    }

    @Override
    public void update() {
        super.update();
        maxMedicalRecordNo = 0;//获取当前医院的今天所有的编号，之后+1就是新建病例的编号
        type = 0; //标识是从哪里跳转的 0 新建   1  点击
        recordInfo = null;//患者病历基本信息
        etRecordHospital.setText(getmUserInfo().getOrganizationName());
        tvMainNian.setText("");
        tvMainYue.setText("");
        etRecordName.setText("");
        //默认为男
        sgRecordSex.check(R.id.rb_record_man);
        getMaxNo();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        showToast("onHiddenChanged");
        if (this.getView() != null) {
            this.update();
        }
    }

    /**
     * 获取当天某个医院的最大序号
     *
     * @return
     */
    private void getMaxNo() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("HospitalID", getmUserInfo().getHospitalID());
        new SingleFetcher(String.class).fetch(getActivity(), "GetMaxNo", "", params);
    }

    public void onEventMainThread(MessageEvent event) {
        super.onEventMainThread(event);
        if (event.getWhat().equals("GetMaxNo")) {
            if (event.getCode().equals("200")) {
                maxMedicalRecordNo = Integer.parseInt((String) event.getObject());
                //格式化  月 日 +（max+1）
                etRecordBinlino.setText(getmUserInfo().getCode() + year + CommonUtil.formatInt(month, "00") + CommonUtil.formatInt(day, "00") + CommonUtil.formatInt(maxMedicalRecordNo + 1, "0000"));
                btnReocrdCertain.setClickable(true);
            } else {
                btnReocrdCertain.setClickable(false);
                btnReocrdCertain.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_common_disable_bg));
            }
        } else if (event.getWhat().equals("AddPersonInfo")) {
            if (event.getCode().equals("200")) {
                showToast("提交成功");
                //更新保存的record数据
                SysApplication.getInstance().setmRecord(recordInfo);
                Intent intent = new Intent(getActivity(), IllnessChooseActivity.class);
                startActivity(intent);
            } else {
                showToast("提交失败");
            }
        }
    }

}
