package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GMY on 2015/11/10 14:22.
 * Contact me via email gmyboy@qq.com.
 */
public class Sheet_Submit implements Parcelable {

    /**
     * id : 1ec5d796-de2b-4c87-bb7b-1937d4e69293
     * CheckValues : 10033
     * type3 : null
     * type2 : 血糖监测项
     * nametype1 : 4
     * nametype3 : 400
     * type1 : 血糖监测录入
     * nametype2 : 400
     * InputDate : 2014-08-11T13:16:51.84
     * InputUser : Jie33
     * bz : 备注33
     * MedicalRecordID : 8ebba12d-7a94-4e51-be37-4c404fb2c410
     * flg : null
     */
    private String CheckValues="";
    private String flg="";
    private String type3;
    private String type2;
    private String InputUser="";
    private String type1;
    private String InputDate="";
    private String nametype2="";
    private String bz="";
    private String MedicalRecordID="";
    private String nametype1="";
    private String id="";
    private String nametype3="";

    public void setCheckValues(String CheckValues) {
        this.CheckValues = CheckValues;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public void setType3(String type3) {
        this.type3 = type3;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public void setInputUser(String InputUser) {
        this.InputUser = InputUser;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public void setInputDate(String InputDate) {
        this.InputDate = InputDate;
    }

    public void setNametype2(String nametype2) {
        this.nametype2 = nametype2;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setMedicalRecordID(String MedicalRecordID) {
        this.MedicalRecordID = MedicalRecordID;
    }

    public void setNametype1(String nametype1) {
        this.nametype1 = nametype1;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNametype3(String nametype3) {
        this.nametype3 = nametype3;
    }

    public String getCheckValues() {
        return CheckValues;
    }

    public String getFlg() {
        return flg;
    }

    public String getType3() {
        return type3;
    }

    public String getType2() {
        return type2;
    }

    public String getInputUser() {
        return InputUser;
    }

    public String getType1() {
        return type1;
    }

    public String getInputDate() {
        return InputDate;
    }

    public String getNametype2() {
        return nametype2;
    }

    public String getBz() {
        return bz;
    }

    public String getMedicalRecordID() {
        return MedicalRecordID;
    }

    public String getNametype1() {
        return nametype1;
    }

    public String getId() {
        return id;
    }

    public String getNametype3() {
        return nametype3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.CheckValues);
        dest.writeString(this.flg);
        dest.writeString(this.type3);
        dest.writeString(this.type2);
        dest.writeString(this.InputUser);
        dest.writeString(this.type1);
        dest.writeString(this.InputDate);
        dest.writeString(this.nametype2);
        dest.writeString(this.bz);
        dest.writeString(this.MedicalRecordID);
        dest.writeString(this.nametype1);
        dest.writeString(this.id);
        dest.writeString(this.nametype3);
    }

    public Sheet_Submit() {
    }

    protected Sheet_Submit(Parcel in) {
        this.CheckValues = in.readString();
        this.flg = in.readString();
        this.type3 = in.readString();
        this.type2 = in.readString();
        this.InputUser = in.readString();
        this.type1 = in.readString();
        this.InputDate = in.readString();
        this.nametype2 = in.readString();
        this.bz = in.readString();
        this.MedicalRecordID = in.readString();
        this.nametype1 = in.readString();
        this.id = in.readString();
        this.nametype3 = in.readString();
    }

    public static final Parcelable.Creator<Sheet_Submit> CREATOR = new Parcelable.Creator<Sheet_Submit>() {
        public Sheet_Submit createFromParcel(Parcel source) {
            return new Sheet_Submit(source);
        }

        public Sheet_Submit[] newArray(int size) {
            return new Sheet_Submit[size];
        }
    };
}
