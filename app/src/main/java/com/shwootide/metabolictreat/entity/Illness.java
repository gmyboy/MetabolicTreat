package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 疾病信息
 * Created by GMY on 2015/9/8 12:34.
 * Contact me via email gmyboy@qq.com.
 */
public class Illness implements Parcelable {
    /**
     * other : null
     * HospitalID : 1
     * name : 糖尿病
     * id : 1
     * DepartmentID : 1001
     * info : null
     */
    private String other;
    private String HospitalID;
    private String name="";
    private String id;
    private String DepartmentID;
    private String info;

    public void setOther(String other) {
        this.other = other;
    }

    public void setHospitalID(String HospitalID) {
        this.HospitalID = HospitalID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDepartmentID(String DepartmentID) {
        this.DepartmentID = DepartmentID;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getOther() {
        return other;
    }

    public String getHospitalID() {
        return HospitalID;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.other);
        dest.writeString(this.HospitalID);
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeString(this.DepartmentID);
        dest.writeString(this.info);
    }

    public Illness() {
    }

    protected Illness(Parcel in) {
        this.other = in.readString();
        this.HospitalID = in.readString();
        this.name = in.readString();
        this.id = in.readString();
        this.DepartmentID = in.readString();
        this.info = in.readString();
    }

    public static final Parcelable.Creator<Illness> CREATOR = new Parcelable.Creator<Illness>() {
        public Illness createFromParcel(Parcel source) {
            return new Illness(source);
        }

        public Illness[] newArray(int size) {
            return new Illness[size];
        }
    };

    @Override
    public String toString() {
        return name.equals("") ? super.toString() : name;
    }
}
