package com.shwootide.metabolictreat.app;

import android.app.Activity;
import android.app.Application;

import com.shwootide.metabolictreat.entity.MedicalRecord;
import com.shwootide.metabolictreat.entity.Record;
import com.shwootide.metabolictreat.handler.ErrorHandler;

import java.io.File;
import java.util.Stack;

/**
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class SysApplication extends Application {
    public static final String INTENT_DISPLAYERROR = "INTENT_DISPLAYERROR";
    private File dir;
    //堆栈，用来管理activity
    private static Stack<Activity> mStack;
    private static SysApplication instance;
    private Record mRecord;//记录患者基本信息
    private MedicalRecord medicalRecord;//保存就诊其本主表信息
    private String address;//暂存看病地点

    public synchronized static SysApplication getInstance() {
        if (null == instance) {
            instance = new SysApplication();
        }
        return instance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取主页面点击的患者基本信息
     *
     * @return
     */
    public Record getmRecord() {
        if (null == mRecord) {
            mRecord = new Record();
        }
        return mRecord;
    }

    public void setmRecord(Record record) {
        mRecord = record;
    }

    /**
     * 获取点选疾病之后的就诊基本主表信息
     *
     * @return
     */
    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
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
//        CrashWoodpecker.fly().to(this);
        ErrorHandler crashHandler = ErrorHandler.getInstance();
        crashHandler.init(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
