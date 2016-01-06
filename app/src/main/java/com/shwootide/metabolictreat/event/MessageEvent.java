package com.shwootide.metabolictreat.event;

import java.util.List;

/**
 * 所有请求返回数据集合
 * Created by Administrator on 2015/8/18.
 */
public class MessageEvent<T> {
    public String code = "";
    public T object;
    public String message = "";//涵盖的消息
    public String what = "";//区分event
    public List<T> objects;//info
    public int meta;
    public String otherinfo;

    public MessageEvent() {
    }

    public MessageEvent(String what, int meta) {
        this.what = what;
        this.meta = meta;
    }

    public MessageEvent(String what, String message) {
        this.what = what;
        this.message = message;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }


    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getOtherinfo() {
        return otherinfo;
    }

    public void setOtherinfo(String otherinfo) {
        this.otherinfo = otherinfo;
    }
}
