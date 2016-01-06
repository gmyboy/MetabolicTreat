package com.shwootide.metabolictreat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.shwootide.metabolictreat.adapter.MainAdapter;
import com.shwootide.metabolictreat.app.SysApplication;
import com.shwootide.metabolictreat.entity.UserInfo;
import com.shwootide.metabolictreat.utils.Alerts;
import com.shwootide.metabolictreat.utils.PreferenceUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 所有activity 的父类
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Bind(R.id.rb_add)
    RadioButton rbAdd;
    @Bind(R.id.rb_search)
    RadioButton rbSearch;
    @Bind(R.id.rb_help)
    RadioButton rbHelp;
    @Bind(R.id.rb_setting)
    RadioButton rbSetting;
    @Bind(R.id.rb_schedule)
    RadioButton rbSchedule;
    @Bind(R.id.rb_remind)
    RadioButton rbRemind;
    @Bind(R.id.rg_mainRadio)
    RadioGroup rgMainRadio;

    /**
     * 是否按两次退出
     */
    private boolean isTwiceExit = false;
    /**
     * 是否注册事件
     */
    private boolean doRegisterEvent = false;
    private boolean doRegisterStickyEvent = false;
    public Context mContext = this;
    public Toolbar mToolbar;
    private Toast toast;
    private UserInfo mUserInfo;
    // 广播用来接收消息
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (SysApplication.INTENT_DISPLAYERROR.equals(intent.getAction())) {
                showDialog(intent.getStringExtra(Intent.EXTRA_TEXT));
            }
        }
    };

    public void showDialog(String msg) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title("提示信息")
                .content(msg)
                .positiveText("确定")
                .build();
        dialog.show();
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
//        p.height = (int) (d.getHeight() * 0.4);   //高度设置为屏幕的0.3
        p.width = (int) (d.getWidth() * 0.4);    //宽度设置为屏幕的0.5
        dialog.getWindow().setAttributes(p);     //设置生效
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 添加activity进堆栈
         */
        SysApplication.getInstance().addActivity(this);
        Alerts.register(this);
        if (doRegisterEvent)
            EventBus.getDefault().register(this);
        if (doRegisterStickyEvent)
            EventBus.getDefault().registerSticky(this);
        if (isTwiceExit)
            toast = Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT);
        setLayout();
        setTitleBarOption();
        /**
         * 注册ButterKnife
         */
        ButterKnife.bind(this);
        rgMainRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                wentTo(group, checkedId);
            }
        });
    }

    /**
     * 获取登录医师的信息
     *
     * @return
     */
    public UserInfo getmUserInfo() {
        if (mUserInfo == null)
            mUserInfo = PreferenceUtil.getUser(mContext, "UserInfo");
        return mUserInfo;
    }

    public void setmUserInfo(UserInfo mUserInfo) {
        this.mUserInfo = mUserInfo;
    }

    @Override
    protected void onPause() {
        unregisterReceiver(receiver);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SysApplication.INTENT_DISPLAYERROR);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Alerts.unregister(this);
        if (doRegisterEvent || doRegisterStickyEvent)
            EventBus.getDefault().unregister(mContext);
        super.onDestroy();
    }

    /**
     * 加载布局
     */
    public abstract void setLayout();

    /**
     * 设置TitleBar
     */
    public abstract void setTitleBarOption();

    public abstract void wentTo(RadioGroup group, int checkedId);

    /**
     * 在oncreate之前调用
     *
     * @param doRegisterEvent
     */
    public void setRegisterEvent(boolean doRegisterEvent) {
        this.doRegisterEvent = doRegisterEvent;
    }

    public void setRegisterStickyEvent(boolean doRegisterStickyEvent) {
        this.doRegisterStickyEvent = doRegisterStickyEvent;
    }

    /**
     * 设置是否可以按两次退出
     *
     * @param isTwiceExit
     */
    public void setTwiceExit(boolean isTwiceExit) {
        this.isTwiceExit = isTwiceExit;
    }


    /**
     * 实现联系按两次推出
     */
    @Override
    public void onBackPressed() {
        if (isTwiceExit)
            twiceexit();
        else
            super.onBackPressed();
    }

    private void twiceexit() {
        if (toast.getView().getParent() == null)
            toast.show();
        else
            exit();
    }

    /**
     * 退出应用
     */
    public void exit() {
        SysApplication.getInstance().exit();
    }

    /**
     * 显示toast
     *
     * @param massage
     */
    public void showToast(String massage) {
        Toast.makeText(this, massage, Toast.LENGTH_SHORT).show();
    }
}
