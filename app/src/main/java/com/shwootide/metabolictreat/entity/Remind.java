package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 提醒get数据
 * Created by GMY on 2015/12/28 14:04.
 * Contact me via email igmyboy@gmail.com.
 */
public class Remind implements Parcelable {

    /**
     * name : 凤飞飞
     * Sex : 男
     * birth : 1988年08月
     * Tel : 12233333333
     * weixin :
     * address :
     * MedicalRecordNo : RJ201512220003
     * id : 08d3013f-9059-4424-9036-024630005893
     * PersonID : c35ed555-4d21-419c-8b41-3540ca49a235
     * RecordDate : 2015-12-22T11:24:32
     * RecordNo : RJ201512220003
     * HospitalID : 1
     * DepartmentID : 1001
     * InputDate : 2015-12-22T11:24:32
     * InputUser : majing
     * bz :
     * StaffID : b3550c7f-9183-4ce4-995e-a215db40ab69
     * diagnose :
     * complication :
     * SequenceNumber : 2
     * IllnessID : 9000
     * NextDate : 2015-12-22T11:24:32
     * NextEndDate : null
     * Online : null
     * Place : null
     * Other1 : null
     * Other2 : null
     * Other3 : null
     * isVisit : 1
     * way : null
     * counttime : -6
     */

    private String name;
    private String Sex;
    private String birth;
    private String Tel;
    private String weixin;
    private String address;
    private String MedicalRecordNo;
    private String id;
    private String PersonID;
    private String RecordDate;
    private String RecordNo;
    private String HospitalID;
    private String DepartmentID;
    private String InputDate;
    private String InputUser;
    private String bz;
    private String StaffID;
    private String diagnose;
    private String complication;
    private String SequenceNumber;
    private String IllnessID;
    private String NextDate;
    private String NextEndDate;
    private String Online;
    private String Place;
    private String Other1;
    private String Other2;
    private String Other3;
    private String isVisit;
    private String way;
    private int counttime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicalRecordNo() {
        return MedicalRecordNo;
    }

    public void setMedicalRecordNo(String medicalRecordNo) {
        MedicalRecordNo = medicalRecordNo;
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

    public void setHospitalID(String hospitalID) {
        HospitalID = hospitalID;
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

    public String getIsVisit() {
        return isVisit;
    }

    public void setIsVisit(String isVisit) {
        this.isVisit = isVisit;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public int getCounttime() {
        return counttime;
    }

    public void setCounttime(int counttime) {
        this.counttime = counttime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.Sex);
        dest.writeString(this.birth);
        dest.writeString(this.Tel);
        dest.writeString(this.weixin);
        dest.writeString(this.address);
        dest.writeString(this.MedicalRecordNo);
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
        dest.writeString(this.NextEndDate);
        dest.writeString(this.Online);
        dest.writeString(this.Place);
        dest.writeString(this.Other1);
        dest.writeString(this.Other2);
        dest.writeString(this.Other3);
        dest.writeString(this.isVisit);
        dest.writeString(this.way);
        dest.writeInt(this.counttime);
    }

    public Remind() {
    }

    protected Remind(Parcel in) {
        this.name = in.readString();
        this.Sex = in.readString();
        this.birth = in.readString();
        this.Tel = in.readString();
        this.weixin = in.readString();
        this.address = in.readString();
        this.MedicalRecordNo = in.readString();
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
        this.NextEndDate = in.readString();
        this.Online = in.readString();
        this.Place = in.readString();
        this.Other1 = in.readString();
        this.Other2 = in.readString();
        this.Other3 = in.readString();
        this.isVisit = in.readString();
        this.way = in.readString();
        this.counttime = in.readInt();
    }

    public static final Parcelable.Creator<Remind> CREATOR = new Parcelable.Creator<Remind>() {
        public Remind createFromParcel(Parcel source) {
            return new Remind(source);
        }

        public Remind[] newArray(int size) {
            return new Remind[size];
        }
    };
}
