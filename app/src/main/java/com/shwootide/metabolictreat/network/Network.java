package com.shwootide.metabolictreat.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.shwootide.metabolictreat.utils.CommonUtil;

/**
 * 网络请求+工具类
 * Created by GMY on 2015/8/25 10:05.
 * Contact me via email gmyboy@qq.com.
 */
public class Network {
    /**
     * 判断当前网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            CommonUtil.showToast(context, "网络不可用");
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        CommonUtil.showToast(context, "网络不可用");
        return false;
    }
}
