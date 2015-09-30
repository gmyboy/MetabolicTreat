package com.shwootide.metabolictreat.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 * Created by GMY on 2015/8/25 10:08.
 * Contact me via email gmyboy@qq.com.
 */
public class CommonUtil {
    /**
     * 显示Toast信息
     *
     * @param context 调用者
     * @param msg     信息内容
     */
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 用过身高体重获得BMI值
     *
     * @param str_tz kg
     * @param str_sg cm
     * @return
     */
    public static int getBMI(String str_tz, String str_sg) {
        double tz = Double.parseDouble(str_tz);
        double sg = Double.parseDouble(str_sg);
        double d = Math.pow(sg / 100, 2);
        return (int) (tz / d);
    }

    /**
     * 格式化医院编号   0001
     *
     * @param format eg.  "00"
     */
    public static String formatInt(int i, String format) {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(i);
    }

    /**
     * 获取yyyy-MM-ddTHH:mm:ss格式的系统日期
     *
     * @return
     */
    public static String getSysDate() {
        /* 默认取服务器日期 */
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = simpledateformat.format(Calendar.getInstance().getTime());
        return s;
    }
    /**
     * 获取yyyy年MM月dd日格式的系统日期
     *
     * @return
     */
    public static String getSysDate2() {
		/* 默认取服务器日期 */
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy年MM月dd日");
        String s = simpledateformat.format(Calendar.getInstance().getTime());
        return s;
    }
    /**
     * 生成GUID，用户提交数据
     *
     * @return
     */
    public static String generateGUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 拆分字符串
     *
     * @param str 待拆分的字符串
     */
    public static List<String> splitStr(String str) {
        return Arrays.asList(str.split("/"));
    }

    /**
     * 替换制表符
     *
     * @param str 待替换的字符串
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 隐藏软键盘
     */
    public static void hideSoftInputView(Activity activity,View view) {
        InputMethodManager manager = ((InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (activity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (activity.getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 显示软键盘
     */
    public static void showSoftInputView(Activity activity, View view) {
        InputMethodManager manager = ((InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (activity.getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            manager.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

}
