package com.gmy.dragdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements MyTimer.OnTimeChangeListener, MyTimer.OnSecondChangListener, MyTimer.OnMinChangListener, MyTimer.OnHourChangListener {

    private MyTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (MyTimer) findViewById(R.id.tv2);
        timer.setOnTimeChangeListener(this);
        timer.setSecondChangListener(this);
        timer.setMinChangListener(this);
        timer.setHourChangListener(this);
        timer.setModel(Model.Timer);
        timer.setStartTime(1, 30, 30);
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

    @Override
    public void onTimerStart(long timeStart) {

    }

    @Override
    public void onTimeChange(long timeStart, long timeRemain) {

    }

    @Override
    public void onTimeStop(long timeStart, long timeRemain) {

    }

    @Override
    public void onSecondChange(int second) {

    }

    @Override
    public void onMinChange(int minute) {

    }

    @Override
    public void onHourChange(int hour) {

    }
}
