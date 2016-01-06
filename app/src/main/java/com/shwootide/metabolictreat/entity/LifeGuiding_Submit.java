package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 运动处方+饮食处方
 * Created by GMY on 2015/11/18 15:18.
 * Contact me via email gmyboy@qq.com.
 */
public class LifeGuiding_Submit implements Parcelable {

    /**
     * TitleName : I级	25分钟	散步、家务、购物、拔草
     * Type : 运动处方
     * FileName : null
     * id : 201
     * bak :
     */
    private String LifeGuidingID;
    private String MedicalRecordID;
    private String id;
    private String bak="";

    public String getLifeGuidingID() {
        return LifeGuidingID;
    }

    public void setLifeGuidingID(String lifeGuidingID) {
        LifeGuidingID = lifeGuidingID;
    }

    public String getMedicalRecordID() {
        return MedicalRecordID;
    }

    public void setMedicalRecordID(String medicalRecordID) {
        MedicalRecordID = medicalRecordID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.LifeGuidingID);
        dest.writeString(this.MedicalRecordID);
        dest.writeString(this.id);
        dest.writeString(this.bak);
    }

    public LifeGuiding_Submit() {
    }

    protected LifeGuiding_Submit(Parcel in) {
        this.LifeGuidingID = in.readString();
        this.MedicalRecordID = in.readString();
        this.id = in.readString();
        this.bak = in.readString();
    }

    public static final Parcelable.Creator<LifeGuiding_Submit> CREATOR = new Parcelable.Creator<LifeGuiding_Submit>() {
        public LifeGuiding_Submit createFromParcel(Parcel source) {
            return new LifeGuiding_Submit(source);
        }

        public LifeGuiding_Submit[] newArray(int size) {
            return new LifeGuiding_Submit[size];
        }
    };
}
