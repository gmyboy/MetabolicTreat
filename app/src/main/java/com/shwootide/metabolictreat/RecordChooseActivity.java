package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shwootide.metabolictreat.adapter.RecordChooseAdapter;

import butterknife.Bind;
import butterknife.OnItemClick;

/**
 * 科室->疾病->诊断类型   选择页
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordChooseActivity extends BaseActivity {
    @Bind(R.id.lv_recordchoose)
    ListView lvRecordchoose;

    @OnItemClick(R.id.lv_recordchoose)
    void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, RecordFullActivity.class);
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lvRecordchoose.setAdapter(new RecordChooseAdapter(mContext));
    }
}
