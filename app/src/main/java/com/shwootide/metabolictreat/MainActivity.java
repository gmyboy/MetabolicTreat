package com.shwootide.metabolictreat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.shwootide.metabolictreat.adapter.MainPagerAdapter;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.utils.CommonUtil;

import butterknife.Bind;

/**
 * 主界面
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.tv_main_avator)
    ImageView tvMainAvator;
    @Bind(R.id.fl_container)
    FrameLayout flContainer;
    @Bind(R.id.tv_main_name)
    TextView tvMainName;

    private MainPagerAdapter pagerAdapter;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setTitleBarOption() {

    }

    @Override
    public void wentTo(RadioGroup group, int checkedId) {
        Fragment fragment;
        switch (checkedId) {
            case R.id.rb_add:
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent);
                break;
            case R.id.rb_search:
                fragment = (Fragment) pagerAdapter.instantiateItem(flContainer, 1);
                pagerAdapter.setPrimaryItem(flContainer, 0, fragment);
                pagerAdapter.finishUpdate(flContainer);
                break;
            case R.id.rb_remind:
                fragment = (Fragment) pagerAdapter.instantiateItem(flContainer, 2);
                pagerAdapter.setPrimaryItem(flContainer, 0, fragment);
                pagerAdapter.finishUpdate(flContainer);
                break;
            case R.id.rb_schedule:
                fragment = (Fragment) pagerAdapter.instantiateItem(flContainer, 3);
                pagerAdapter.setPrimaryItem(flContainer, 0, fragment);
                pagerAdapter.finishUpdate(flContainer);
                break;
            case R.id.rb_help:
                fragment = (Fragment) pagerAdapter.instantiateItem(flContainer, 4);
                pagerAdapter.setPrimaryItem(flContainer, 0, fragment);
                pagerAdapter.finishUpdate(flContainer);
                break;
            case R.id.rb_setting:
                fragment = (Fragment) pagerAdapter.instantiateItem(flContainer, 5);
                pagerAdapter.setPrimaryItem(flContainer, 0, fragment);
                pagerAdapter.finishUpdate(flContainer);
                break;
        }
    }

    /**
     * 接收list消息
     *
     * @param event
     */
    public void onEventMainThread(MessageEvent event) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        rbSearch.setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTwiceExit(true);
        setRegisterEvent(true);
        super.onCreate(savedInstanceState);
        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        tvMainName.setText(getmUserInfo().getUserName() + "    " + CommonUtil.getSysDate3());
        int flag = getIntent().getIntExtra("FLAG", 1);
        switch (flag) {
            case 0:
                rbAdd.setChecked(true);
                break;
            case 1:
                rbSearch.setChecked(true);
                break;
            case 2:
                rbSchedule.setChecked(true);
                break;
            case 3:
                rbRemind.setChecked(true);
                break;
            case 4:
                rbSetting.setChecked(true);
                break;
            case 5:
                rbHelp.setChecked(true);
                break;
        }
    }

}
