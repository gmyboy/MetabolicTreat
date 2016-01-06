package com.shwootide.metabolictreat.network;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.shwootide.metabolictreat.entity.Diagnosis_Submit;
import com.shwootide.metabolictreat.entity.IllnessSubmit;
import com.shwootide.metabolictreat.entity.LifeGuiding_Submit;
import com.shwootide.metabolictreat.entity.MHistory_Now;
import com.shwootide.metabolictreat.entity.MedicalRecord;
import com.shwootide.metabolictreat.entity.MedicalRecordCheckup;
import com.shwootide.metabolictreat.entity.PicInfo;
import com.shwootide.metabolictreat.entity.Record;
import com.shwootide.metabolictreat.entity.Sheet_Submit;
import com.shwootide.metabolictreat.event.MessageEvent;
import com.shwootide.metabolictreat.utils.Config;
import com.shwootide.metabolictreat.utils.GLog;
import com.shwootide.metabolictreat.utils.GsonUtil;
import com.shwootide.metabolictreat.utils.ImageUtil;
import com.shwootide.metabolictreat.widget.CustomProgressDialog;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;
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
                messageEvent.what = msg.getData().getString("WHAT");//must
                if (!TextUtils.isEmpty(json) && !json.equals("-99")) {
                    try {
                        JSONObject jb = new JSONObject(json);
                        if (jb.has("code")) {
                            messageEvent.setCode(jb.getString("code"));//200  //must
                        }
                        if (jb.has("info")) {
                            messageEvent.setObject(GsonUtil.gsonToBean(jb.getString("info"), cls));
                        }
                        if (jb.has("Message")) {
                            messageEvent.setMessage(String.valueOf(jb.get("Message")));
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

    public void upload(Context context, final String function, String message, final PicInfo picInfo) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    Map<String, Object> params = new HashMap<>();
                    params.put("InputData", GsonUtil.gsonString(picInfo));
                    GLog.e("path" + "--------" + picInfo.getLocal_path());
                    params.put("FileContent", ImageUtil.bytesTobase64(ImageUtil.bitmapToBytes(picInfo.getLocal_path())));//进行Base64编码
                    String json = postFile(function, params);
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

    public void updatePic(Context context, final String function, String message, final PicInfo picInfo) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    Map<String, Object> params = new HashMap<>();
                    params.put("InputData", GsonUtil.gsonString(picInfo));
                    GLog.e("path" + "--------" + picInfo.getLocal_path());
                    params.put("FileContent", ImageUtil.bytesTobase64(ImageUtil.bitmapToBytes(picInfo.getLocal_path())));//进行Base64编码
                    String json = postFile(function, params);
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
     * 修改就诊基本信息主表
     *
     * @param context
     * @param message
     * @param medicalRecord
     */
    public void updateMedicalRecord(Context context, String message, final MedicalRecord medicalRecord) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    Map<String, String> params = new WeakHashMap<>();
                    params.put("id", medicalRecord.getId());
                    params.put("InputData", GsonUtil.gsonString(medicalRecord));
                    String json = post("UpdateMedicalRecord", params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", "UpdateMedicalRecord");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }

    /**
     * 添加就诊 现病史+既往史+家族史+个人史
     *
     * @param context
     * @param message
     * @param mHistories
     */
    public void addMedicalRecord_Now(Context context, String message, final MHistory_Now mHistories) {
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
                    String json = post("AddMedicalRecordHistoryNew", params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", "AddMedicalRecordHistoryNew");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }

    /**
     * 更新 现病史+既往史+家族史+个人史
     *
     * @param context
     * @param message
     * @param mHistories
     */
    public void updateMedicalRecord_Now(Context context, String message, final MHistory_Now mHistories) {
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
                    params.put("id", mHistories.getId());
                    String json = post("UpdateMedicalRecordHistoryNew", params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", "UpdateMedicalRecordHistoryNew");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }

    /**
     * 添加就诊 用药信息
     *
     * @param context
     * @param message
     * @param submit
     */
    public void addMedicalRecord_cure(Context context, String message, final IllnessSubmit submit) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    Map<String, String> params = new WeakHashMap<>();
                    params.put("InputData", GsonUtil.gsonString(submit));
                    String json = post("AddMedicalRecordCure", params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", "AddMedicalRecordCure");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }

    /**
     * 添加处方
     *
     * @param context
     * @param message
     * @param submit
     */
    public void addMedicalRecord_lifeguiding(Context context, String message, final LifeGuiding_Submit submit) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    Map<String, String> params = new WeakHashMap<>();
                    params.put("InputData", GsonUtil.gsonString(submit));
                    String json = post("AddMedicalRecordLifeGuiding", params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", "AddMedicalRecordLifeGuiding");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }

    /**
     * 添加检查信息 1 体格检查 2 实验室检查 3 辅助检查 4 血糖监测 5 甲功能及抗体
     *
     * @param context
     * @param message
     * @param submit
     */
    public void addMedicalRecord_checkup(Context context, String message, final MedicalRecordCheckup submit) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    Map<String, String> params = new WeakHashMap<>();
                    params.put("InputData", GsonUtil.gsonString(submit));
                    String json = post("AddMedicalRecordCheckup", params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", "AddMedicalRecordCheckup");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }

    /**
     * 更新检查信息 1 体格检查 2 实验室检查 3 辅助检查 4 血糖监测 5 甲功能及抗体
     *
     * @param context
     * @param message
     * @param submit
     */
    public void updateMedicalRecord_checkup(Context context, String message, final MedicalRecordCheckup submit) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    Map<String, String> params = new WeakHashMap<>();
                    params.put("InputData", GsonUtil.gsonString(submit));
                    params.put("id", submit.getId());
                    String json = post("UpdateMedicalRecordCheckup", params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", "UpdateMedicalRecordCheckup");
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
            }.start();
        }
    }

    /**
     * 批量添加诊断信息
     *
     * @param context
     * @param message
     * @param submits
     */
    public void addMedicalHistory(Context context, String message, final List<Diagnosis_Submit> submits, final Map<String, String> params) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    params.put("InputData", GsonUtil.gsonString(submits));
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
     * 批量添加就诊检查及化验单详细
     *
     * @param context
     * @param message
     * @param submits
     */
    public void addMedicalRecordInfo(Context context, String message, final List<Sheet_Submit> submits, final Map<String, String> params) {
        if (Network.isAvailable(context)) {
            if (!TextUtils.isEmpty(message)) {
                mDialog = CustomProgressDialog.show(context, message);
                isShowing = true;
            }
            new Thread() {
                @Override
                public void run() {
                    params.put("InputData", GsonUtil.gsonString(submits));
                    String json = post("AddAllMedicalRecordInfo", params);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("JSON", json);
                    bundle.putString("WHAT", "AddAllMedicalRecordInfo");
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
            GLog.e(function + "-------" + function);
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

    /**
     * 设置调用的相关配置+参数
     *
     * @param
     * @param function
     * @param
     * @return
     */
    private String postFile(String function, Map<String, Object> params) {
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
