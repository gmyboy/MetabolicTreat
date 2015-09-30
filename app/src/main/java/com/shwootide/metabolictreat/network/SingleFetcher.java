package com.shwootide.metabolictreat.network;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shwootide.metabolictreat.entity.MHistory;
import com.shwootide.metabolictreat.entity.MedicalRecord;
import com.shwootide.metabolictreat.entity.Record;
import com.shwootide.metabolictreat.entity.UserInfo;
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

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import de.greenrobot.event.EventBus;

/**
 * 提取websevice  输入类型+参数+请求函数——>输出解析好的单个对应类型的实体类
 * Created by GMY on 2015/8/25 10:10.
 * Contact me via email gmyboy@qq.com.
 */
public class SingleFetcher {
    private Handler handler;
    private Dialog mDialog;
    private boolean isShowing;

    public <T> SingleFetcher(final Class<T> cls) {
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
                        messageEvent.setCode(jb.getString("code"));//200
                        messageEvent.setObject(GsonUtil.gsonToBean(jb.getString("info"), cls));
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
     * 取到返回单层数据
     *
     * @param context  上下文
     * @param function 请求函数
     * @param message  dialog显示的消息
     * @param params   请求的参数
     */
    public void fetch(Context context, final String function, String message, final Map<String, String> params) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
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
     * 添加就诊基本信息表
     *
     * @param context
     * @param message
     * @param record
     */
    public void addPersonInfo(Context context, String message, final Record record) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    Map<String, String> params = new WeakHashMap<>();
                    params.put("InputData", GsonUtil.gsonString(record));
                    String json = post("AddPersonInfo", params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", "AddPersonInfo");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }

    /**
     * 修改就诊基本信息表
     *
     * @param context
     * @param message
     * @param record
     */
    public void updatePersonInfo(Context context, String message, final Record record) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    Map<String, String> params = new WeakHashMap<>();
                    params.put("InputData", GsonUtil.gsonString(record));
                    params.put("id", record.getId());
                    String json = post("UpdatePersonInfo", params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", "UpdatePersonInfo");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }

    /**
     * 添加一条就诊基本信息主表
     *
     * @param context
     * @param message
     * @param medicalRecord
     */
    public void addMedicalRecord(Context context, String message, final MedicalRecord medicalRecord) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    Map<String, String> params = new WeakHashMap<>();
                    params.put("InputData", GsonUtil.gsonString(medicalRecord));
                    String json = post("AddMedicalRecord", params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", "AddMedicalRecord");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }

    /**
     * 批量添加就诊病史
     *
     * @param context
     * @param message
     * @param mHistories
     */
    public void addMedicalRecordHistoryAll(Context context, String message, final List<MHistory> mHistories) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    Map<String, String> params = new WeakHashMap<>();
                    params.put("InputData", GsonUtil.gsonString(mHistories));
                    String json = post("AddMedicalRecordHistoryAll", params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", "AddMedicalRecordHistoryAll");
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
