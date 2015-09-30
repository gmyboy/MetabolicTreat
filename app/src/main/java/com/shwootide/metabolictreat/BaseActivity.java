package com.shwootide.metabolictreat;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.Toast;

import com.shwootide.metabolictreat.adapter.MainAdapter;
import com.shwootide.metabolictreat.app.SysApplication;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 所有activity 的父类
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public abstract class BaseActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 添加activity进堆栈
         */
        SysApplication.getInstance().addActivity(this);
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
