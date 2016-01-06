package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 运动处方+饮食处方
 * Created by GMY on 2015/11/18 15:18.
 * Contact me via email gmyboy@qq.com.
 */
public class LifeGuiding implements Parcelable {


    /**
     * Type : 饮食处方
     * LifeGuidingID : 101
     * LifeGuidingName : 正常体型者参考食谱(1)
     * FileName : normal1.png
     * MedicalRecordID : a09b9f45-fe80-4365-87c3-55f96663d9db
     * id : c73a19f6-899d-4ab9-901a-4f981dabdb33
     * LifeGuidingBak : null
     * bak :
     */
    private String Type;
    private String LifeGuidingID;
    private String LifeGuidingName;
    private String FileName;
    private String TitleName;
    private String MedicalRecordID;
    private String id;
    private String LifeGuidingBak;
    private String bak;

    public LifeGuiding(String id, String titleName) {
        this.id = id;
        TitleName = titleName;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getLifeGuidingID() {
        return LifeGuidingID;
    }

    public void setLifeGuidingID(String lifeGuidingID) {
        LifeGuidingID = lifeGuidingID;
    }

    public String getLifeGuidingName() {
        return LifeGuidingName;
    }

    public void setLifeGuidingName(String lifeGuidingName) {
        LifeGuidingName = lifeGuidingName;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getTitleName() {
        return TitleName;
    }

    public void setTitleName(String titleName) {
        TitleName = titleName;
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

    public String getLifeGuidingBak() {
        return LifeGuidingBak;
    }

    public void setLifeGuidingBak(String lifeGuidingBak) {
        LifeGuidingBak = lifeGuidingBak;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public LifeGuiding() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Type);
        dest.writeString(this.LifeGuidingID);
        dest.writeString(this.LifeGuidingName);
        dest.writeString(this.FileName);
        dest.writeString(this.TitleName);
        dest.writeString(this.MedicalRecordID);
        dest.writeString(this.id);
        dest.writeString(this.LifeGuidingBak);
        dest.writeString(this.bak);
    }

    protected LifeGuiding(Parcel in) {
        this.Type = in.readString();
        this.LifeGuidingID = in.readString();
        this.LifeGuidingName = in.readString();
        this.FileName = in.readString();
        this.TitleName = in.readString();
        this.MedicalRecordID = in.readString();
        this.id = in.readString();
        this.LifeGuidingBak = in.readString();
        this.bak = in.readString();
    }

    public static final Creator<LifeGuiding> CREATOR = new Creator<LifeGuiding>() {
        public LifeGuiding createFromParcel(Parcel source) {
            return new LifeGuiding(source);
        }

        public LifeGuiding[] newArray(int size) {
            return new LifeGuiding[size];
        }
    };

    @Override
    public String toString() {
        return getTitleName();
    }
}
