package com.shwootide.metabolictreat.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.shwootide.metabolictreat.utils.CommonUtil;
import com.shwootide.metabolictreat.utils.GLog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    /**
     * 检查手机是否能上网
     * @param context
     * @return
     */
    public static boolean canSurfTheInternet(Context context){
        String result = null;
        try {
            String ip = "www.baidu.com";// ping 的地址，可以换成任何一种可靠的外网
            Process p = Runtime.getRuntime().exec("ping -c 3 -w 100 " + ip);// ping网址3次
//            InputStream input = p.getInputStream();// 读取ping的内容，可以不加
//            BufferedReader in = new BufferedReader(new InputStreamReader(input));
//            StringBuffer stringBuffer = new StringBuffer();
//            String content = "";
//            while ((content = in.readLine()) != null) {
//                stringBuffer.append(content);
//            }
//            GLog.e("网络不可用 :ping result content : " + stringBuffer.toString());
            int status = p.waitFor();// ping的状态
            if (status == 0) {
                result = "success";
                return true;
            } else {
                result = "failed";
            }
        } catch (IOException e) {
            result = "IOException";
        } catch (InterruptedException e) {
            result = "InterruptedException";
        } finally {
            GLog.e("ping result = " + result);
        }
        CommonUtil.showToast(context, "网络不可用");
        return false;
    }
}
