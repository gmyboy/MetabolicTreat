package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 诊断提交数据类型
 * Created by GMY on 2015/11/6 12:47.
 * Contact me via email gmyboy@qq.com.
 */
public class Diagnosis_Submit implements Parcelable {
    /**
     * InputUser : 016907ce-d298-46d8-bb45-d17118532ce2
     * maxTypeName : 糖尿病
     * minTypeid : 021501
     * maxTypeid : 0201
     * type : 02
     * CheckValues5 :
     * InputDate : 2014-08-01T13:16:36.323
     * CheckValues4 :
     * isCheck : 有
     * CheckValues3 :
     * CheckValues2 :
     * MedicalRecordID : 8ebba12d-7a94-4e51-be37-4c404fb2c410
     * bz : bz001
     * minTypeName : 1型糖尿病
     * id : 26eb6d34-5763-43e6-ab59-18aeccfaa501
     * CheckValues1 : 2015-05-02
     * MHTypeID : 021501
     */
    private String InputUser = "";
    private String maxTypeName;
    private String minTypeid;
    private String maxTypeid;
    private String type = "02";
    private String CheckValues5 = "";
    private String InputDate = "";
    private String CheckValues4 = "";
    private String isCheck = "";
    private String CheckValues3 = "";
    private String CheckValues2 = "";
    private String MedicalRecordID = "";
    private String bz = "";
    private String minTypeName;
    private String id = "";
    private String CheckValues1 = "";
    private String MHTypeID = "";

    public void setInputUser(String InputUser) {
        this.InputUser = InputUser;
    }

    public void setMaxTypeName(String maxTypeName) {
        this.maxTypeName = maxTypeName;
    }

    public void setMinTypeid(String minTypeid) {
        this.minTypeid = minTypeid;
    }

    public void setMaxTypeid(String maxTypeid) {
        this.maxTypeid = maxTypeid;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCheckValues5(String CheckValues5) {
        this.CheckValues5 = CheckValues5;
    }

    public void setInputDate(String InputDate) {
        this.InputDate = InputDate;
    }

    public void setCheckValues4(String CheckValues4) {
        this.CheckValues4 = CheckValues4;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }

    public void setCheckValues3(String CheckValues3) {
        this.CheckValues3 = CheckValues3;
    }

    public void setCheckValues2(String CheckValues2) {
        this.CheckValues2 = CheckValues2;
    }

    public void setMedicalRecordID(String MedicalRecordID) {
        this.MedicalRecordID = MedicalRecordID;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setMinTypeName(String minTypeName) {
        this.minTypeName = minTypeName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCheckValues1(String CheckValues1) {
        this.CheckValues1 = CheckValues1;
    }

    public void setMHTypeID(String MHTypeID) {
        this.MHTypeID = MHTypeID;
    }

    public String getInputUser() {
        return InputUser;
    }

    public String getMaxTypeName() {
        return maxTypeName;
    }

    public String getMinTypeid() {
        return minTypeid;
    }

    public String getMaxTypeid() {
        return maxTypeid;
    }

    public String getType() {
        return type;
    }

    public String getCheckValues5() {
        return CheckValues5;
    }

    public String getInputDate() {
        return InputDate;
    }

    public String getCheckValues4() {
        return CheckValues4;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public String getCheckValues3() {
        return CheckValues3;
    }

    public String getCheckValues2() {
        return CheckValues2;
    }

    public String getMedicalRecordID() {
        return MedicalRecordID;
    }

    public String getBz() {
        return bz;
    }

    public String getMinTypeName() {
        return minTypeName;
    }

    public String getId() {
        return id;
    }

    public String getCheckValues1() {
        return CheckValues1;
    }

    public String getMHTypeID() {
        return MHTypeID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.InputUser);
        dest.writeString(this.maxTypeName);
        dest.writeString(this.minTypeid);
        dest.writeString(this.maxTypeid);
        dest.writeString(this.type);
        dest.writeString(this.CheckValues5);
        dest.writeString(this.InputDate);
        dest.writeString(this.CheckValues4);
        dest.writeString(this.isCheck);
        dest.writeString(this.CheckValues3);
        dest.writeString(this.CheckValues2);
        dest.writeString(this.MedicalRecordID);
        dest.writeString(this.bz);
        dest.writeString(this.minTypeName);
        dest.writeString(this.id);
        dest.writeString(this.CheckValues1);
        dest.writeString(this.MHTypeID);
    }

    public Diagnosis_Submit() {
    }

    protected Diagnosis_Submit(Parcel in) {
        this.InputUser = in.readString();
        this.maxTypeName = in.readString();
        this.minTypeid = in.readString();
        this.maxTypeid = in.readString();
        this.type = in.readString();
        this.CheckValues5 = in.readString();
        this.InputDate = in.readString();
        this.CheckValues4 = in.readString();
        this.isCheck = in.readString();
        this.CheckValues3 = in.readString();
        this.CheckValues2 = in.readString();
        this.MedicalRecordID = in.readString();
        this.bz = in.readString();
        this.minTypeName = in.readString();
        this.id = in.readString();
        this.CheckValues1 = in.readString();
        this.MHTypeID = in.readString();
    }

    public static final Parcelable.Creator<Diagnosis_Submit> CREATOR = new Parcelable.Creator<Diagnosis_Submit>() {
        public Diagnosis_Submit createFromParcel(Parcel source) {
            return new Diagnosis_Submit(source);
        }

        public Diagnosis_Submit[] newArray(int size) {
            return new Diagnosis_Submit[size];
        }
    };
}
