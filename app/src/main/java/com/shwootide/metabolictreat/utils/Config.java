package com.shwootide.metabolictreat.utils;

import android.os.Environment;

/**
 * 所有配置项+常量
 * Created by GMY on 2015/8/25 09:56.
 * Contact me via email gmyboy@qq.com.
 */
public class Config {
    /**
     * webservice相关配置
     */
    /*命名空间*/
    public static final String NAMESPACE = "http://tempuri.org/";
    public static final String ENDPOINT = "http://test11.mypatroller.com/AppService/AppServiceForAndroid.asmx";
    /**
     * sharepreference相关配置
     */
    /*用户信息*/
    public static final String USERINFO = "UserInfo";
    /**
     * 路径相关配置
     */
    /*主目录*/
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().toString() + "/metabolictreat";
    /*   拍照图片保存路径*/
    public static final String IMG_PATH = BASE_PATH + "/img/";

}
