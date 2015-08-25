package com.shwootide.metabolictreat.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shwootide.metabolictreat.utils.Config;
import com.shwootide.metabolictreat.utils.GLog;

import org.ksoap2.SoapEnvelope;

import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * 提取websevice
 * Created by GMY on 2015/8/25 10:10.
 * Contact me via email gmyboy@qq.com.
 */
public class WebServiceFetcher<T> {
    private Gson gson;

    public WebServiceFetcher() {
        gson = new Gson();
    }

    /**
     * 取到返回单层数据
     *
     * @param function 方法名
     * @param params   参数集合
     * @return
     */
    public void fetch(Context context, final String function, final Map<String, String> params) {
        if (Network.isAvailable(context)) {
            new Thread() {
                @Override
                public void run() {
                    T t = null;
                    String json = post(function, params);
                    if (!TextUtils.isEmpty(json)) {
                        t = gson.fromJson(json, new TypeToken<T>() {
                        }.getType());
                    }
                    EventBus.getDefault().post(t);
                }
            }.start();
        }
    }

    /**
     * 设置调用的相关配置+参数
     *
     * @param function
     * @param params
     * @return
     */
    private String post(String function, Map<String, String> params) {
        String json = "-99";
        Runtime.getRuntime().gc();
        String endPoint = Config.ENDPOINT;
        String soapAction = Config.NAMESPACE + "/" + function;
        try {
            /**
             * 为webService指定方法名为命名空间
             */
            SoapObject rpc = new SoapObject(Config.NAMESPACE, function);
            /**
             * 添加所有参数
             */
            for (String strKey : params.keySet()) {
                GLog.d(strKey + "-------" + params.get(strKey));
                rpc.addProperty(strKey, params.get(strKey));
            }
            System.setProperty("http.keepAlive", "false");
            /**
             * 生成调用 webService的方法的soap请求信息，并指定soap版本
             */
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            /**
             * 设置是否调用的是.net开发的webservice
             */
            envelope.dotNet = false;
            envelope.setOutputSoapObject(rpc);
            HttpTransportSE mtransport = new HttpTransportSE(endPoint, 3000000);
            try {
                mtransport.call(soapAction, envelope);
            } catch (Exception e) {
                e.printStackTrace();
            }

            /**
             * debug
             */
//            SoapFault error = (SoapFault) envelope.bodyIn;
//            GLog.d(error.toString());
            /**
             * 获取返回数据
             */
            SoapObject object = (SoapObject) envelope.bodyIn;

            if (object == null) {
                GLog.d("object is null");
            } else {
                for (int i = 0; i < object.getPropertyCount(); i++) {
                    SoapObject soapChilds = (SoapObject) object.getProperty(i);
                    Log.i("out", soapChilds.getProperty(0).toString());
                }
                json = object.getProperty(0).toString();
            }
            GLog.d(json);
        } catch (Exception e) {
            GLog.d(e.getMessage());
        }
        if (json.equals("anyType{}"))
            json = "-99";

        return json;
    }


}
