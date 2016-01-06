package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 就诊基本信息主表
 * Created by GMY on 2015/9/22 10:14.
 * Contact me via email gmyboy@qq.com.
 */
public class MedicalRecord implements Parcelable {
    private String id;
    private String PersonID;
    private String RecordDate;
    private String RecordNo;
    private String HospitalID;
    private String DepartmentID;
    private String InputDate;
    private String InputUser;
    private String bz;
    private String diagnose;
    private String complication;
    private String SequenceNumber;
    private String IllnessID;
    private String StaffID;
    private String NextDate;
    private String USERREALNAME;//返回字段 == InputUser
    private String NextEndDate;
    private String Online="";
    private String Place="";
    private String Other1 = "";
    private String Other2 = "";
    private String Other3 = "";
    private String isVisit="";

    public String getIsVisit() {
        return isVisit;
    }

    public void setIsVisit(String isVisit) {
        this.isVisit = isVisit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String personID) {
        PersonID = personID;
    }

    public String getUSERREALNAME() {
        return USERREALNAME;
    }

    public void setUSERREALNAME(String USERREALNAME) {
        this.USERREALNAME = USERREALNAME;
    }

    public String getRecordDate() {
        return RecordDate;
    }

    public void setRecordDate(String recordDate) {
        RecordDate = recordDate;
    }

    public String getRecordNo() {
        return RecordNo;
    }

    public void setRecordNo(String recordNo) {
        RecordNo = recordNo;
    }

    public String getHospitalID() {
        return HospitalID;
    }

    public void setHospitalID(String hospital) {
        HospitalID = hospital;
    }

    public String getDepartmentID() {
        return DepartmentID;
    }

    public void setDepartmentID(String departmentID) {
        DepartmentID = departmentID;
    }

    public String getInputDate() {
        return InputDate;
    }

    public void setInputDate(String inputDate) {
        InputDate = inputDate;
    }

    public String getInputUser() {
        return InputUser;
    }

    public void setInputUser(String inputUser) {
        InputUser = inputUser;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String staffID) {
        StaffID = staffID;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getComplication() {
        return complication;
    }

    public void setComplication(String complication) {
        this.complication = complication;
    }

    public String getSequenceNumber() {
        return SequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        SequenceNumber = sequenceNumber;
    }

    public String getIllnessID() {
        return IllnessID;
    }

    public void setIllnessID(String illnessID) {
        IllnessID = illnessID;
    }

    public String getNextDate() {
        return NextDate;
    }

    public void setNextDate(String nextDate) {
        NextDate = nextDate;
    }

    public String getNextEndDate() {
        return NextEndDate;
    }

    public void setNextEndDate(String nextEndDate) {
        NextEndDate = nextEndDate;
    }

    public String getOnline() {
        return Online;
    }

    public void setOnline(String online) {
        Online = online;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getOther1() {
        return Other1;
    }

    public void setOther1(String other1) {
        Other1 = other1;
    }

    public String getOther2() {
        return Other2;
    }

    public void setOther2(String other2) {
        Other2 = other2;
    }

    public String getOther3() {
        return Other3;
    }

    public void setOther3(String other3) {
        Other3 = other3;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.PersonID);
        dest.writeString(this.RecordDate);
        dest.writeString(this.RecordNo);
        dest.writeString(this.HospitalID);
        dest.writeString(this.DepartmentID);
        dest.writeString(this.InputDate);
        dest.writeString(this.InputUser);
        dest.writeString(this.bz);
        dest.writeString(this.StaffID);
        dest.writeString(this.diagnose);
        dest.writeString(this.complication);
        dest.writeString(this.SequenceNumber);
        dest.writeString(this.IllnessID);
        dest.writeString(this.NextDate);
        dest.writeString(this.USERREALNAME);
        dest.writeString(this.NextEndDate);
        dest.writeString(this.Online);
        dest.writeString(this.Place);
        dest.writeString(this.Other1);
        dest.writeString(this.Other2);
        dest.writeString(this.Other3);
    }

    public MedicalRecord() {
    }

    protected MedicalRecord(Parcel in) {
        this.id = in.readString();
        this.PersonID = in.readString();
        this.RecordDate = in.readString();
        this.RecordNo = in.readString();
        this.HospitalID = in.readString();
        this.DepartmentID = in.readString();
        this.InputDate = in.readString();
        this.InputUser = in.readString();
        this.bz = in.readString();
        this.StaffID = in.readString();
        this.diagnose = in.readString();
        this.complication = in.readString();
        this.SequenceNumber = in.readString();
        this.IllnessID = in.readString();
        this.NextDate = in.readString();
        this.USERREALNAME = in.readString();
        this.NextEndDate = in.readString();
        this.Online = in.readString();
        this.Place = in.readString();
        this.Other1 = in.readString();
        this.Other2 = in.readString();
        this.Other3 = in.readString();
    }

    public static final Creator<MedicalRecord> CREATOR = new Creator<MedicalRecord>() {
        public MedicalRecord createFromParcel(Parcel source) {
            return new MedicalRecord(source);
        }

        public MedicalRecord[] newArray(int size) {
            return new MedicalRecord[size];
        }
    };
}
