package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.shwootide.metabolictreat.adapter.IllnessAdapter;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.Illness;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;

import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;

/**
 * 疾病选择界面
 * Created by Administrator on 2015/8/19.
 */
public class IllnessChooseActivity extends BaseActivity {
    @Bind(R.id.lv_illnesschoose)
    ListView lvIllnesschoose;
    @Bind(R.id.et_illnesschoose_address)
    EditText etIllnesschooseAddress;

    private IllnessAdapter adapter;
    public static int position;//记录点击的位置
    public static String illnessid;//记录疾病编号
    private String num = "0";//记录病历总条数，判断有没有初诊

    @Override
    public void setLayout() {
        setContentView(R.layout.activcity_illnesschoose);
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
        getAllIllness();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getAllIllness();
    }

    /**
     * 从后台获得所有疾病列表
     */
    private void getAllIllness() {
        Map<String, String> params = new WeakHashMap<>();
        new MutiFetcher(Illness[].class).fetch(mContext, "GetAllIllness", "正在加载...", params);
    }

    /**
     * 接收返回数据
     *
     * @param event
     */
    public void onEventMainThread(MessageEvent event) {
        if (event.getWhat().equals("GetAllIllness")) {
            if (event.getCode().equals("200")) {
                adapter = new IllnessAdapter(mContext, event.getObjects());
                lvIllnesschoose.setAdapter(adapter);
            }
        } else if (event.getWhat().equals("FindMedicalRecordOne")) {
            if (event.getCode().equals("200")) {
                if (event.getObject() != null) {
                    num = (String) event.getObject();
                    Intent intent = new Intent();
                    switch (position) {
                        case R.id.btn_popup1:
                            if (!num.equals("0")) {
                                showDialog("您已经有一条初诊记录了");
                            } else {
                                SysApplication.getInstance().setAddress(etIllnesschooseAddress.getText().toString());
                                intent.setClass(mContext, RecordFullActivity.class);
                                intent.putExtra("Position", 0);//初诊
                                intent.putExtra("IllnessID", illnessid);
                                startActivity(intent);
                            }
                            break;
                        case R.id.btn_popup2:
                            if (num.equals("0")) {
                                showDialog("必须先创建初诊记录哦");
                            } else {
                                SysApplication.getInstance().setAddress(etIllnesschooseAddress.getText().toString());
                                intent.setClass(mContext, ReDiagnosisActivity.class);
                                intent.putExtra("Position", 1);//复诊
                                intent.putExtra("IllnessID", illnessid);
                                startActivity(intent);
                            }
                            break;
                    }
                }
            }
        }
    }
}
