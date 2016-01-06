package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gmy.segmentedgroup.SegmentedGroup;
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
 * 病历基本信息页
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordActivity extends BaseActivity {
    @Bind(R.id.btn_reocrd_certain)
    Button btnReocrdCertain;
    @Bind(R.id.btn_record_back)
    Button btnRecordBack;
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
                CommonUtil.showToast(mContext, "姓名不能为空");
            } else if (TextUtils.isEmpty(tvMainNian.getText().toString().trim()) || TextUtils.isEmpty(tvMainYue.getText().toString().trim())) {
                CommonUtil.showToast(mContext, "出生年月不能为空");
            } else if (tvMainNian.getText().toString().trim().length() != 4 || tvMainYue.getText().toString().trim().length() > 2
                    || Integer.parseInt(tvMainYue.getText().toString().trim()) > 12
                    || Integer.parseInt(tvMainNian.getText().toString().trim()) > Integer.parseInt(CommonUtil.getSysYear())) {
                CommonUtil.showToast(mContext, "出生年月格式不对");
            } else if (!TextUtils.isEmpty(tel) && tel.length() != 11) {
                CommonUtil.showToast(mContext, "联系方式格式不对");
            } else {
                recordInfo.setName(name);
                recordInfo.setSex(sex);
                recordInfo.setBirth(birth);
                recordInfo.setTel(tel);
                recordInfo.setWeixin(weixin);
                recordInfo.setMedicalRecordNo(medicalRecordNo);

                recordInfo.setIllnessID("");
                recordInfo.setStaffID("");
                recordInfo.setHospitalID(hospitalID);
                recordInfo.setCreatDate(CommonUtil.getSysDate());
                recordInfo.setFlg("");
                recordInfo.setAge("");
                recordInfo.setAddress("");
                recordInfo.setBz("");
                recordInfo.setAddress(adress);
                recordInfo.setId(CommonUtil.generateGUID());
                new SingleFetcher(String.class).addPersonInfo(mContext, "正在提交...", recordInfo);
            }
        } else if (type == 1) {//点击
            if (name.equals(recordInfo.getName()) && sex.equals(recordInfo.getSex())
                    && birth.equals(recordInfo.getBirth()) && tel.equals(recordInfo.getTel())
                    && weixin.equals(recordInfo.getWeixin()) && adress.equals(recordInfo.getAddress())) {
                Intent intent = new Intent(mContext, IllnessChooseActivity.class);
                startActivity(intent);
            } else {//修改
                Record record = recordInfo;
                if (TextUtils.isEmpty(name)) {
                    CommonUtil.showToast(mContext, "姓名不能为空");
                } else if (TextUtils.isEmpty(birth)) {
                    CommonUtil.showToast(mContext, "出生年月不能为空");
                } else if (tvMainNian.getText().toString().trim().length() != 4 || tvMainYue.getText().toString().trim().length() > 2
                        || Integer.parseInt(tvMainYue.getText().toString().trim()) > 12
                        || Integer.parseInt(tvMainNian.getText().toString().trim()) > Integer.parseInt(CommonUtil.getSysYear())) {
                    CommonUtil.showToast(mContext, "出生年月格式不对");
                } else if (!TextUtils.isEmpty(tel) && tel.length() != 11) {
                    CommonUtil.showToast(mContext, "联系方式格式不对");
                } else {
                    record.setName(name);
                    record.setSex(sex);
                    record.setBirth(birth);
                    record.setTel(tel);
                    record.setWeixin(weixin);
                    record.setAddress(adress);
                    new SingleFetcher(String.class).updatePersonInfo(mContext, "正在保存...", record);
                }
            }
        }
    }

    /**
     * 添加一次后跳到疾病列表，此时返回后提交应该设type  0->1 更新
     * 防止重复添加
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        if (type == 0) {
            type = 1;
            forbidden();
        }
    }

    @OnClick(R.id.btn_record_back)
    void back() {
        finish();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_record);
    }

    @Override
    public void setTitleBarOption() {

    }

    @Override
    public void wentTo(RadioGroup group, int checkedId) {
        Intent intent = new Intent(RecordActivity.this, MainActivity.class);
        switch (checkedId) {
            case R.id.rb_add:
                break;
            case R.id.rb_search:
                break;
            case R.id.rb_schedule:
                intent.putExtra("FLAG", 2);
                startActivity(intent);
                break;
            case R.id.rb_remind:
                intent.putExtra("FLAG", 3);
                startActivity(intent);
                break;
            case R.id.rb_setting:
                intent.putExtra("FLAG", 4);
                startActivity(intent);
                break;
            case R.id.rb_help:
                intent.putExtra("FLAG", 5);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRegisterEvent(true);
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra("TYPE", 0);
        //获得系统当前日期 用于拼接处病历编号
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DATE);
        if (type == 0) {//新建
            rbAdd.setChecked(true);
            etRecordHospital.setText(getmUserInfo().getOrganizationName());
            //默认为男
            sgRecordSex.check(R.id.rb_record_man);
            rbSearch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) finish();
                }
            });
            getMaxNo();
        } else {//点击
            rbSearch.setChecked(true);
            rbAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        Intent intent = new Intent(RecordActivity.this, RecordActivity.class);
                        intent.putExtra("TYPE", 0);//0 新建   1  点击
                        startActivity(intent);
                        finish();
                    }
                }
            });
            forbidden();
            recordInfo = SysApplication.getInstance().getmRecord();
            if (recordInfo != null) {
                etRecordName.setText(recordInfo.getName());
                etRecordHospital.setText(recordInfo.getHospitalName());
                if (recordInfo.getSex().equals("男")) sgRecordSex.check(R.id.rb_record_man);
                else sgRecordSex.check(R.id.rb_record_woman);
                setBirth(recordInfo.getBirth());
                etRecordPhone.setText(recordInfo.getTel());
                etRecordWeixin.setText(recordInfo.getWeixin());
                etRecordBinlino.setText(recordInfo.getMedicalRecordNo());
                etRecordAdress.setText(recordInfo.getAddress());
            }
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

    /**
     * 根据2015年9月  格式的日期，分别给 nian yue 赋值
     *
     * @param birth
     */
    private void setBirth(String birth) {
        if (!TextUtils.isEmpty(birth)) {
            tvMainNian.setText(birth.substring(0, 4));
            tvMainYue.setText(birth.substring(5).split("月")[0]);
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
        new SingleFetcher(String.class).fetch(mContext, "GetMaxNo", "", params);
    }

    /**
     * 接收简单数据
     *
     * @param event 病历单
     */
    public void onEventMainThread(MessageEvent event) {
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
                Intent intent = new Intent(mContext, IllnessChooseActivity.class);
                startActivity(intent);
                this.finish();//注销掉当前activity
            } else {
                showToast("提交失败");
            }
        } else if (event.getWhat().equals("UpdatePersonInfo")) {
            if (event.getCode().equals("200")) {
                showToast("保存成功");
                Intent intent = new Intent(mContext, IllnessChooseActivity.class);
                startActivity(intent);
                this.finish();//注销掉当前activity
            } else {
                showToast("保存失败");
            }
        }
    }
}
