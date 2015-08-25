package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.shwootide.metabolictreat.adapter.MainAdapter;
import com.shwootide.metabolictreat.entity.Record;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.network.WebServiceFetcher;
import com.shwootide.metabolictreat.utils.Common;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;
import de.greenrobot.event.EventBus;

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

    @OnClick(R.id.btn_main_new)
    void newRecord() {
        Intent intent = new Intent(mContext, RecordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_main_search)
    void search() {
        Map<String, String> params = new WeakHashMap<>();
        String function = "function";
//        params.put("userCode", userCode);
//        params.put("password", password);
//        params.put("deviceCode", deviceid);
//        params.put("phoneNumber", phoneNumber);
        new WebServiceFetcher<List<Record>>().fetch(mContext, function, params);
    }

    @OnItemClick(R.id.lv_main)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, RecordActivity.class);
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
     * @param records
     */
    public void onEventMainThread(List<Record> records) {
        if (records != null) {
            lvMain.setAdapter(new MainAdapter(mContext, records));
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
