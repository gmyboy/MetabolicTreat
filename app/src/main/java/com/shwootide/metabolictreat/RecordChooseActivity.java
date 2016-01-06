package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.shwootide.metabolictreat.adapter.RecordChooseAdapter;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.MedicalRecord;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * 疾病选择->病历编辑
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordChooseActivity extends BaseActivity {

    @Bind(R.id.tv_main_name)
    TextView tvMainName;
    @Bind(R.id.tv_main_sex)
    TextView tvMainSex;
    @Bind(R.id.tv_main_birthday)
    TextView tvMainBirthday;
    @Bind(R.id.tv_main_no)
    TextView tvMainNo;
    @Bind(R.id.lv_recordchoose)
    ListView lvRecordchoose;
    @Bind(R.id.tv_num)
    TextView tvNum;//就诊次数标题
    @Bind(R.id.tv_full_jibing)
    TextView tvFullJibing;

    private RecordChooseAdapter adapter;
    private String illnessid = "";//疾病id
    private int mPosition;//默认为病历编辑

    @OnClick(R.id.tv_num)
    void sort() {
        adapter.changeType();
    }

    @OnItemClick(R.id.lv_recordchoose)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        if (adapter.getItem(position).getSequenceNumber().equals("1")) {
            intent.setClass(mContext, RecordFullActivity.class);
        } else {
            intent.setClass(mContext, ReDiagnosisActivity.class);
        }
        intent.putExtra("Position", mPosition);//病历编辑
        intent.putExtra("IllnessID", illnessid);
        intent.putExtra("MedicalRecord", adapter.getItem(position));
        startActivity(intent);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_recordchoose);
    }

    @Override
    public void setTitleBarOption() {

    }

    @Override
    public void wentTo(RadioGroup group, int checkedId) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (checkedId) {
            case R.id.rb_add:
                intent.putExtra("FLAG", 0);
                startActivity(intent);
                break;
            case R.id.rb_search:
                intent.putExtra("FLAG", 1);
                startActivity(intent);
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
        illnessid = getIntent().getStringExtra("IllnessID");
        mPosition = getIntent().getIntExtra("Position", 2);
        tvMainName.setText("  姓名:  " + SysApplication.getInstance().getmRecord().getName());
        tvMainSex.setText("  性别:  " + SysApplication.getInstance().getmRecord().getSex());
        tvMainBirthday.setText("  出生年月:  " + SysApplication.getInstance().getmRecord().getBirth());
        tvMainNo.setText("  病历编号:  " + SysApplication.getInstance().getmRecord().getMedicalRecordNo());
        switch (illnessid) {
            case "1":
                tvFullJibing.setText("糖 尿 病");
                break;
            case "2":
                tvFullJibing.setText("甲 状 腺 疾 病");
                break;
            case "3":
                tvFullJibing.setText("多 囊 卵 巢 综 合 症");
                break;
            case "9000":
                tvFullJibing.setText("其 他 疾 病");
                break;
        }
        adapter = new RecordChooseAdapter(mContext);
        lvRecordchoose.setAdapter(adapter);
        searchMedicalRecord();
    }

    /**
     * 查询某人对应的所有就诊记录
     */
    private void searchMedicalRecord() {
        Map<String, String> params = new WeakHashMap<>();
        params.put("PersonID", SysApplication.getInstance().getmRecord().getId());
        params.put("HospitalID", getmUserInfo().getHospitalID());
        params.put("IllnessID", illnessid);
        new MutiFetcher(MedicalRecord[].class).fetch(mContext, "FindMedicalRecord2", "正在加载...", params);
    }

    /**
     * 接收list消息
     *
     * @param event
     */
    public void onEventMainThread(MessageEvent event) {
        if (event.what.equals("FindMedicalRecord2")) {
            if (event.getCode().equals("200")) {
                adapter = new RecordChooseAdapter(mContext, event.getObjects());
                lvRecordchoose.setAdapter(adapter);
            } else {
                showToast("空空如也");
                adapter = new RecordChooseAdapter(mContext);
            }
        }
    }
}
