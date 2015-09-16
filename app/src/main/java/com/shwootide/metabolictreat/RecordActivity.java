package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gmy.segmentedgroup.SegmentedGroup;
import com.shwootide.metabolictreat.entity.Record;
import com.shwootide.metabolictreat.entity.UserInfo;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.SingleFetcher;
import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.Config;
import com.shwootide.metabolictreat.utils.GLog;
import com.shwootide.metabolictreat.utils.PreferenceUtil;

import java.util.Calendar;
import java.util.Date;
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
    private int year, month, day;
    private int maxMedicalRecordNo = 0;//获取当前医院的今天所有的编号，之后+1就是新建病例的编号
    private int type = 0; //标识是从哪里跳转的 0 新建   1  点击
    private UserInfo userInfo;//保存医生信息


    @OnClick(R.id.btn_reocrd_certain)
    void certain() {
        if (type == 0) {
            Record record = new Record();
            String name = etRecordName.getText().toString().trim();
            String sex = sgRecordSex.getCheckedRadioButtonId() == R.id.rb_record_man ? "男" : "女";
            String birth = tvMainNian.getText().toString().trim() + "年" + tvMainYue.getText().toString().trim() + "月";
            String tel = etRecordPhone.getText().toString().trim();
            String weixin = etRecordWeixin.getText().toString().trim();
            String medicalRecordNo = userInfo.getCode() + year + month + day + CommonUtil.formatInt(maxMedicalRecordNo + 1);
            GLog.e(medicalRecordNo);
            String hospitalID = userInfo.getHospitalID();
            if (TextUtils.isEmpty(name)) {
                CommonUtil.showToast(mContext, "姓名不能为空");
            } else if (TextUtils.isEmpty(birth)) {
                CommonUtil.showToast(mContext, "出生年月不能为空");
            } else {
                record.setName(name);
                record.setSex(sex);
                record.setBirth(birth);
                record.setTel(tel);
                record.setWeixin(weixin);
                record.setMedicalRecordNo(medicalRecordNo);
                record.setHospitalID(hospitalID);
                record.setCreatDate(new Date().toString());
                record.setFlg("");
                record.setAge("");
                record.setAddress("");
                record.setBz("");
                record.setId("");
                new SingleFetcher(String.class).addPersonInfo(mContext, "正在提交...", record);
            }
        } else {
            Intent intent = new Intent(mContext, IllnessChooseActivity.class);
            startActivity(intent);
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
    protected void onCreate(Bundle savedInstanceState) {
        setRegisterEvent(true);
        super.onCreate(savedInstanceState);
        type = getIntent().getIntExtra("TYPE", 0);
        userInfo = PreferenceUtil.getUser(mContext, Config.USERINFO);
        if (type == 0) {//新建
            etRecordHospital.setText(userInfo.getOrganizationName());
            getMaxNo();
        } else {//点击
            Record record = getIntent().getParcelableExtra("RECORD");
            if (record != null) {
                etRecordName.setText(record.getName());
                etRecordHospital.setText(record.getHospitalName());
                if (record.getSex().equals("男")) sgRecordSex.check(R.id.rb_record_man);
                else sgRecordSex.check(R.id.rb_record_woman);
                setBirth(record.getBirth());
                etRecordPhone.setText(record.getTel());
                etRecordWeixin.setText(record.getWeixin());
                etRecordBinlino.setText(record.getMedicalRecordNo());
            }
        }
        //获得系统当前日期
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DATE);
    }

    /**
     * 根据2015年9月  格式的日期，分别给 nian yue 赋值
     *
     * @param birth
     */
    private void setBirth(String birth) {
        tvMainNian.setText(birth.substring(0, 4));
        tvMainYue.setText(birth.substring(5).split("月")[0]);
    }

    /**
     * 获取当天某个医院的最大序号
     *
     * @return
     */
    private void getMaxNo() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("HospitalID", userInfo.getHospitalID());
        new SingleFetcher(String.class).fetch(mContext, "GetMaxNo", "", params);
    }

    /**
     * 接收简单数据
     *
     * @param event 病历单
     */
    public void onEventMainThread(MessageEvent event) {
        if (event.what.equals("GetMaxNo")) {
            if (event.getCode().equals("200")) {
                maxMedicalRecordNo = Integer.parseInt((String) event.getObject());
                btnReocrdCertain.setClickable(true);
            } else {
                btnReocrdCertain.setClickable(false);
                btnReocrdCertain.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_common_disable_bg));
            }
        } else if (event.what.equals("AddPersonInfo")) {
            if (event.getCode().equals("200")) {
                showToast("提交成功");
                Intent intent = new Intent(mContext, IllnessChooseActivity.class);
                startActivity(intent);
            } else
                showToast("提交失败");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_certain) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
