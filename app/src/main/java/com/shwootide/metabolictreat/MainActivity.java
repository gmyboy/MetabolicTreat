package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.gmy.segmentedgroup.SegmentedGroup;
import com.shwootide.metabolictreat.adapter.MainAdapter;
import com.shwootide.metabolictreat.entity.Record;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.MutiFetcher;

import java.util.Calendar;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * 主界面,用户可以新建病历+选择已有病例编辑
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.btn_main_new)
    Button btnMainNew;
    @Bind(R.id.lv_main)
    ListView lvMain;
    @Bind(R.id.btn_main_search)
    Button btnMainSearch;
    @Bind(R.id.et_main_name)
    EditText etMainName;
    @Bind(R.id.sg_main_sex)
    SegmentedGroup sgMainSex;
    @Bind(R.id.tv_main_nian)
    EditText tvMainNian;
    @Bind(R.id.tv_main_yue)
    EditText tvMainYue;
    private MainAdapter adapter;

    @OnClick(R.id.btn_main_new)
    void newRecord() {
        Intent intent = new Intent(mContext, RecordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_main_search)
    void search() {
        String name = etMainName.getText().toString().trim();
        String sex = sgMainSex.getCheckedRadioButtonId() == R.id.rb_main_man ? "男" : "女";
        String nian = tvMainNian.getText().toString().trim();
        String yue = tvMainYue.getText().toString().trim();

        Map<String, String> params = new WeakHashMap<>();
        params.put("name", name);
        params.put("sex", sex);
        params.put("birth", TextUtils.isEmpty(nian) ? (TextUtils.isEmpty(yue) ? "" : yue + "月") : (TextUtils.isEmpty(yue) ? nian + "年" : nian + "年" + yue + "月"));
        new MutiFetcher(Record[].class).fetch(mContext, "PatientQuery", "正在查询...", params);
    }

    @OnItemClick(R.id.lv_main)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, RecordActivity.class);
        intent.putExtra("RECORD", (Parcelable) lvMain.getAdapter().getItem(position));
        intent.putExtra("TYPE", 1);//0 新建   1  点击
        startActivity(intent);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setTitleBarOption() {

    }

    /**
     * 接收list消息
     *
     * @param event
     */
    public void onEventMainThread(MessageEvent event) {
        if (event.what.equals("PatientQuery")) {
            if (event.getCode().equals("200")) {
                adapter = new MainAdapter(mContext, event.getObjects());
                lvMain.setAdapter(adapter);
            } else {
                showToast("空空如也");
                adapter = new MainAdapter(mContext);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTwiceExit(true);
        setRegisterEvent(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
