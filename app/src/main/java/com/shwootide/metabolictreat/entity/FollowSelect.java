package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GMY on 2015/11/25 17:51.
 * Contact me via email gmyboy@qq.com.
 */
public class FollowSelect implements Parcelable {
    private String type;//区分是哪个模块下的Fields
    private String title;//fields的值
    private String name;//fields对应的值
    private String dos;//fields对应的单位值 可以为空

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.title);
        dest.writeString(this.name);
        dest.writeString(this.dos);
    }

    public FollowSelect() {
    }

    protected FollowSelect(Parcel in) {
        this.type = in.readString();
        this.title = in.readString();
        this.name = in.readString();
        this.dos = in.readString();
    }

    public static final Creator<FollowSelect> CREATOR = new Creator<FollowSelect>() {
        public FollowSelect createFromParcel(Parcel source) {
            return new FollowSelect(source);
        }

        public FollowSelect[] newArray(int size) {
            return new FollowSelect[size];
        }
    };
}
