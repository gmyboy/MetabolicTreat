package com.shwootide.metabolictreat.network;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.utils.Config;
import com.shwootide.metabolictreat.utils.GLog;
import com.shwootide.metabolictreat.utils.GsonUtil;
import com.shwootide.metabolictreat.widget.CustomProgressDialog;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * 提取websevice 输入类型的数组形式+参数+请求函数——>输出解析好的对应类型的实体类的集合
 * Created by GMY on 2015/8/25 10:10.
 * Contact me via email gmyboy@qq.com.
 */
public class MutiFetcher {
    private Handler handler;
    private Dialog mDialog;
    private boolean isShowing;

    public <T> MutiFetcher(final Class<T[]> cls) {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String json = msg.getData().getString("JSON");
                //新建一个消息实体
                MessageEvent messageEvent = new MessageEvent();
                //关闭dialog
                if (isShowing) mDialog.dismiss();

                messageEvent.what = msg.getData().getString("WHAT");

                if (!TextUtils.isEmpty(json) && !json.equals("-99")) {
                    try {
                        JSONObject jb = new JSONObject(json);
                        if (jb.has("code")) {
                            messageEvent.setCode(jb.getString("code"));//200
                        }
                        if (jb.has("info")) {
                            messageEvent.setObjects(GsonUtil.gsonToList(jb.getString("info"), cls));
                        }
                        if (jb.has("Message")) {
                            messageEvent.setMessage(jb.getString("Message"));
                        }
                        if (jb.has("otherinfo")) {
                            messageEvent.setOtherinfo(jb.getString("otherinfo"));
                        }
                    } catch (Exception e) {
                        GLog.e(e.getMessage());
                        EventBus.getDefault().post(messageEvent);
                    }
                } else {
                    messageEvent.setCode("999");//返回为空
                }
                EventBus.getDefault().post(messageEvent);
            }
        };
    }


    /**
     * 取到返回多层数据
     *
     * @param context  上下文
     * @param function 请求函数
     * @param message  dialog显示的消息
     * @param params   请求的参数
     */
    public void fetch(Context context, final String function, String message, final Map<String, String> params) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.showCancelable(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    String json = post(function, params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", function);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
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
        String soapAction = Config.NAMESPACE + function;
        try {
            /**
             * 为webService指定方法名为命名空间
             */
            SoapObject rpc = new SoapObject(Config.NAMESPACE, function);
            /**
             * 添加所有参数
             */
            GLog.e("function -------" + function);
            for (String strKey : params.keySet()) {
                GLog.e(strKey + "-------" + params.get(strKey));
                rpc.addProperty(strKey, params.get(strKey));
            }
//            System.setProperty("http.keepAlive", "false");
            /**
             * 生成调用 webService的方法的soap请求信息，并指定soap版本
             */
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            /**
             * 设置是否调用的是.net开发的webservice(.net开发必须设置)
             */
            envelope.dotNet = true;
            if (!params.isEmpty())
                envelope.setOutputSoapObject(rpc);
            HttpTransportSE mtransport = new HttpTransportSE(endPoint, 30000);
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
                json = object.getProperty(0).toString();
            }
        } catch (Exception e) {
            GLog.d(e.getMessage());
        }
        if (json.equals("anyType{}"))
            json = "-99";
        GLog.e(json);
        return json;
    }


}
