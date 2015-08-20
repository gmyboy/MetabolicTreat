package com.shwootide.metabolictreat;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.Toast;

import com.shwootide.metabolictreat.app.SysApplication;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2015/8/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private long exitTime = 0;
    private boolean isTwiceExit = false;
    private boolean doRegisterEvent = false;
    public String TAG = this.getClass().getSimpleName();
    public Context mContext = this;
    public Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysApplication.getInstance().addActivity(this);
        if (doRegisterEvent)
            EventBus.getDefault().register(mContext);
        setLayout();
        setTitleBarOption();
        ButterKnife.bind(this);
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
        if (doRegisterEvent)
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


    /**
     * 在oncreate之前调用
     *
     * @param doRegisterEvent
     */
    public void setRegisterEvent(boolean doRegisterEvent) {
        this.doRegisterEvent = doRegisterEvent;
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isTwiceExit) {
            twiceexit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void twiceexit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            showToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            exit();
        }
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
