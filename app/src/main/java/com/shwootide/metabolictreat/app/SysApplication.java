package com.shwootide.metabolictreat.app;

import java.io.File;
import java.util.Stack;

import android.app.Activity;
import android.app.Application;


/**
 * app
 *
 * @author GMY
 * @mail 2275964276@qq.com
 * @date 2015年6月2日
 */
public class SysApplication extends Application {

    private File dir;
    //堆栈，用来管理activity
    private static Stack<Activity> mStack;
    private static SysApplication instance;

    public synchronized static SysApplication getInstance() {
        if (null == instance) {
            instance = new SysApplication();
        }
        return instance;
    }

    /**
     * 添加activity到后台堆栈中
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (mStack == null) {
            mStack = new Stack<>();
        }
        mStack.add(activity);
    }

    /**
     * get current Activity 获取当前Activity（栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = mStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = mStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            mStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : mStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void exit() {
        try {
            for (int i = 0, size = mStack.size(); i < size; i++) {
                if (null != mStack.get(i)) {
                    mStack.get(i).finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mStack.clear();
            System.exit(0);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//		ErrorHandler crashHandler = ErrorHandler.getInstance();
//		crashHandler.init(getApplicationContext());
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}