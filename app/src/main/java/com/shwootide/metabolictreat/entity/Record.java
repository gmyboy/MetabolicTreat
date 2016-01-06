package com.shwootide.metabolictreat.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 病历基本信息
 * Created by GMY on 2015/8/25 09:36.
 * Contact me via email gmyboy@qq.com.
 */
public class Record implements Parcelable {

    /**
     * flg :
     * address : 上海浦东
     * MedicalRecordNo : RJ201508010001
     * Sex : 男
     * HospitalID : 1
     * birth : 1985年2月
     * weixin : 15555555555
     * name : pName
     * bz :
     * Tel : 15000000000
     * CreatDate : 2014-08-01T13:16:36.323
     * id : e68a0dcd-d9ae-4e02-bd0c-002bf04fe5bb
     * HospitalName : 仁济医院
     * Age :
     */
    private String flg = "";
    private String address = "";
    private String MedicalRecordNo = "";
    private String Sex = "";
    private String HospitalID = "";
    private String birth = "";
    private String weixin = "";
    private String name = "";
    private String bz = "";
    private String Tel = "";
    private String CreatDate = "";
    private String id = "";
    private String Age = "";
    private String IllnessID = "";
    private String StaffID = "";
    private String IllnessName;
    private String USERREALNAME;
    private String HospitalName;

    public String getIllnessName() {
        return IllnessName;
    }

    public void setIllnessName(String illnessName) {
        IllnessName = illnessName;
    }

    public String getUSERREALNAME() {
        return USERREALNAME;
    }

    public void setUSERREALNAME(String USERREALNAME) {
        this.USERREALNAME = USERREALNAME;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
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

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getHospitalID() {
        return HospitalID;
    }

    public void setHospitalID(String hospitalID) {
        HospitalID = hospitalID;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getCreatDate() {
        return CreatDate;
    }

    public void setCreatDate(String creatDate) {
        CreatDate = creatDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getIllnessID() {
        return IllnessID;
    }

    public void setIllnessID(String illnessID) {
        IllnessID = illnessID;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String staffID) {
        StaffID = staffID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.flg);
        dest.writeString(this.address);
        dest.writeString(this.MedicalRecordNo);
        dest.writeString(this.Sex);
        dest.writeString(this.HospitalID);
        dest.writeString(this.birth);
        dest.writeString(this.weixin);
        dest.writeString(this.name);
        dest.writeString(this.bz);
        dest.writeString(this.Tel);
        dest.writeString(this.CreatDate);
        dest.writeString(this.id);
        dest.writeString(this.HospitalName);
        dest.writeString(this.Age);
        dest.writeString(this.IllnessID);
        dest.writeString(this.StaffID);
    }

    public Record() {
    }

    protected Record(Parcel in) {
        this.flg = in.readString();
        this.address = in.readString();
        this.MedicalRecordNo = in.readString();
        this.Sex = in.readString();
        this.HospitalID = in.readString();
        this.birth = in.readString();
        this.weixin = in.readString();
        this.name = in.readString();
        this.bz = in.readString();
        this.Tel = in.readString();
        this.CreatDate = in.readString();
        this.id = in.readString();
        this.HospitalName = in.readString();
        this.Age = in.readString();
        this.IllnessID = in.readString();
        this.StaffID = in.readString();
    }

    public static final Creator<Record> CREATOR = new Creator<Record>() {
        public Record createFromParcel(Parcel source) {
            return new Record(source);
        }

        public Record[] newArray(int size) {
            return new Record[size];
        }
    };
}
