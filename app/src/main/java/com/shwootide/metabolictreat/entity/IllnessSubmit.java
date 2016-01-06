package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 疾病信息待提交
 * Created by GMY on 2015/9/8 12:34.
 * Contact me via email gmyboy@qq.com.
 */
public class IllnessSubmit implements Parcelable {

    private String id="";
    private String MTypeParentID="";//药物大类ID
    private String MTypeParentName="";
    private String MTypeID="";//药物小类ID
    private String MTTradeID="";//药物ID
    private String MTTradeName="";
    private String MedicalRecordID="";//就诊id
    private String Dosage="";//剂量
    private String TimesaDay="";//服用次数
    private String other="";//其他
    private String Box="";//盒数
    private String way="";
    private String bak="";
    private String flg="";//Flg=1 目前用药 Flg =2 治疗用药

    public String getBox() {
        return Box;
    }

    public void setBox(String box) {
        Box = box;
    }

    public String getMTTradeName() {
        return MTTradeName;
    }

    public void setMTTradeName(String MTTradeName) {
        this.MTTradeName = MTTradeName;
    }

    public String getMTypeParentName() {
        return MTypeParentName;
    }

    public void setMTypeParentName(String MTypeParentName) {
        this.MTypeParentName = MTypeParentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMTypeParentID() {
        return MTypeParentID;
    }

    public void setMTypeParentID(String MTypeParentID) {
        this.MTypeParentID = MTypeParentID;
    }

    public String getMTypeID() {
        return MTypeID;
    }

    public void setMTypeID(String MTypeID) {
        this.MTypeID = MTypeID;
    }

    public String getMTTradeID() {
        return MTTradeID;
    }

    public void setMTTradeID(String MTTradeID) {
        this.MTTradeID = MTTradeID;
    }

    public String getMedicalRecordID() {
        return MedicalRecordID;
    }

    public void setMedicalRecordID(String medicalRecordID) {
        MedicalRecordID = medicalRecordID;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String dosage) {
        Dosage = dosage;
    }

    public String getTimesaDay() {
        return TimesaDay;
    }

    public void setTimesaDay(String timesaDay) {
        TimesaDay = timesaDay;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.MTypeParentID);
        dest.writeString(this.MTypeParentName);
        dest.writeString(this.MTypeID);
        dest.writeString(this.MTTradeID);
        dest.writeString(this.MTTradeName);
        dest.writeString(this.MedicalRecordID);
        dest.writeString(this.Dosage);
        dest.writeString(this.TimesaDay);
        dest.writeString(this.other);
        dest.writeString(this.Box);
        dest.writeString(this.way);
        dest.writeString(this.bak);
        dest.writeString(this.flg);
    }

    public IllnessSubmit() {
    }

    protected IllnessSubmit(Parcel in) {
        this.id = in.readString();
        this.MTypeParentID = in.readString();
        this.MTypeParentName = in.readString();
        this.MTypeID = in.readString();
        this.MTTradeID = in.readString();
        this.MTTradeName = in.readString();
        this.MedicalRecordID = in.readString();
        this.Dosage = in.readString();
        this.TimesaDay = in.readString();
        this.other = in.readString();
        this.Box = in.readString();
        this.way = in.readString();
        this.bak = in.readString();
        this.flg = in.readString();
    }

    public static final Creator<IllnessSubmit> CREATOR = new Creator<IllnessSubmit>() {
        public IllnessSubmit createFromParcel(Parcel source) {
            return new IllnessSubmit(source);
        }

        public IllnessSubmit[] newArray(int size) {
            return new IllnessSubmit[size];
        }
    };
}
