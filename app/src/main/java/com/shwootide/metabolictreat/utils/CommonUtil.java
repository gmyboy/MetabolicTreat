package com.shwootide.metabolictreat.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.text.DecimalFormat;

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
     */
    public static String formatInt(int i) {
        DecimalFormat df = new DecimalFormat("0000");
        return df.format(i);
    }
}
