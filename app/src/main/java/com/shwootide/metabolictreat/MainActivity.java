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

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemClick;

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
        lvMain.setAdapter(new MainAdapter(MainActivity.this));
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setTwiceExit(true);
//        lvMain.setAdapter(new TestAdapter(MainActivity.this));
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, RecordActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
