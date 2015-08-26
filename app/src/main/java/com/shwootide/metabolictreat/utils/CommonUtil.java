package com.shwootide.metabolictreat.utils;

import android.content.Context;
import android.widget.Toast;

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
}
