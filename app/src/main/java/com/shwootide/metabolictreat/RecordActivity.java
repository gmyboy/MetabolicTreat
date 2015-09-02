package com.shwootide.metabolictreat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnFocusChange;

/**
 * 病历基本信息页
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class RecordActivity extends BaseActivity {
    @Bind(R.id.et_record_birthday)
    EditText etRecordBirthday;
    @Bind(R.id.rb_record1)
    RadioButton rbRecord1;
    @Bind(R.id.rb_record2)
    RadioButton rbRecord2;
    @Bind(R.id.btn_reocrd_certain)
    Button btnReocrdCertain;
    private int year, month, day;

    @OnFocusChange(R.id.et_record_birthday)
    void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus)
            new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    etRecordBirthday.setText(year + "年" + (monthOfYear + 1) + "月");
                }
            }, year, month, day).show();
    }

    @OnClick(R.id.btn_reocrd_certain)
    void certain() {
        Intent intent = new Intent(mContext, DepartmentChooseActivity.class);
        startActivity(intent);
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
        super.onCreate(savedInstanceState);
        //初始化Calendar日历对象
        Calendar mycalendar = Calendar.getInstance(Locale.CHINA);
        Date mydate = new Date(); //获取当前日期Date对象
        mycalendar.setTime(mydate);////为Calendar对象设置时间为当前日期
        year = mycalendar.get(Calendar.YEAR); //获取Calendar对象中的年
        month = mycalendar.get(Calendar.MONTH);//获取Calendar对象中的月
        day = mycalendar.get(Calendar.DAY_OF_MONTH);//获取这个月的第几天

        rbRecord1.setChecked(true);

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
